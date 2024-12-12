/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import start.App;

/**
 * FXML Controller class
 *
 * @author zzzexezzz
 */
public class GraficoViewController implements Initializable {

    @FXML
    private TextField searchField;

    @FXML
    private PieChart pieChart;
    @FXML
    private Button btnVoltar;

    /**
     * Método chamado ao clicar no botão "Pesquisar".
     */
    @FXML
    public void onPesquisar() {
        String searchTerm = searchField.getText().trim();
        if (!searchTerm.isEmpty()) {
            atualizarGrafico(searchTerm);
        }
    } 
    /**
     * Atualiza o gráfico de pizza com base na pesquisa realizada no banco de dados.
     */
    private void atualizarGrafico(String termo) {
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/disco_bc", "root", "mysql");) {
            
            // Consulta para contar a quantidade de itens do termo pesquisado
            String sqlTermo = "SELECT COUNT(*) FROM ALBUM WHERE NOME_ARTISTA = ?";
            PreparedStatement statementTermo = conn.prepareStatement(sqlTermo);
            statementTermo.setString(1, termo);
            ResultSet resultTermo = statementTermo.executeQuery(); 
            
            double quantidadeTermo = 0;
            if (resultTermo.next()) {
                quantidadeTermo = resultTermo.getInt(1);
            }
            
            // Consulta para contar o total de itens na tabela
            String sqlTotal = "SELECT COUNT(*) FROM ALBUM"; 
            PreparedStatement statementTotal = conn.prepareStatement(sqlTotal);
            ResultSet resultTotal = statementTotal.executeQuery();
            
            double quantidadeTotal = 0;
            if (resultTotal.next()) {
                quantidadeTotal = resultTotal.getInt(1);
            }

            // Cálculo das porcentagens
            double valorSub = quantidadeTotal - quantidadeTermo;
            double porcentagemTermo = 100 * (quantidadeTermo / quantidadeTotal );
            double porcentagemOutros = 100 - porcentagemTermo;
            // Atualizar o gráfico de pizza
            pieChart.getData().clear();
            pieChart.getData().add(new PieChart.Data(termo + " (" + String.format("%.1f", porcentagemTermo) + "%)", porcentagemTermo));
            pieChart.getData().add(new PieChart.Data("Outros (" + String.format("%.1f", porcentagemOutros) + "%)", porcentagemOutros));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    @FXML
    private void btnVoltarOnAction(ActionEvent event) throws IOException {
       App.setRoot("Principal");
    }

}

