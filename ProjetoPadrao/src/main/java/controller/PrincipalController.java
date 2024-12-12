/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import start.App;

/**
 * FXML Controller class
 *
 * @author zzzexezzz
 */
public class PrincipalController implements Initializable {

    @FXML
    private Button btnEditarColecao;
    @FXML
    private Button btnInserirColecao;
    @FXML
    private Button btnVisualizarColecao;
    @FXML
    private Button btnGrafico; //retirar

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnEditarColecaoOnAction(ActionEvent event) throws IOException {
         App.setRoot("Editar");
    }

    @FXML
    private void btnInserirColecaoOnAction(ActionEvent event) throws IOException {
        App.setRoot("InserirColecao"); 
    }

    @FXML
    private void btnVisualizarColecaoOnAction(ActionEvent event) throws IOException {
         App.setRoot("TabelaView");
    }

    @FXML
    private void btnGraficoOnAction(ActionEvent event) throws IOException { //retirar
        App.setRoot("GraficoView");
    }
    
}
