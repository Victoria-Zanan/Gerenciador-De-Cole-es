/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Album;
import model.dao.AlbumDao;
import model.dao.DaoFactory;
import start.App;

/**
 * FXML Controller class
 *
 * @author zzzexezzz
 */
public class EditarController implements Initializable {

    @FXML
    private TableView<Album> TblAlbum;
    
    @FXML
    private TableColumn<Album, String> colun_Titulo;
    
    @FXML
    private TableColumn<Album, String> colun_nomeArtista;
    
    @FXML
    private TableColumn<Album, String> colun_Gravadora;
    
    @FXML
    private TableColumn<Album, String> tImagem;
    
    @FXML
    private TableColumn<Album, String> colun_Estilo;
    
    @FXML
    private TableColumn<Album, String> colun_Ano;
    
    @FXML
    private TableColumn<Album, String> colun_PaisLancamento;
    
    @FXML
    private TableColumn<Album, String> colun_selo;
  
    private List<Album> ListaAlbum;
    private ObservableList<Album> ObservableListAlbum;
    
    
    @FXML
    private Button btnEditar;
    
    @FXML
    private Button btnExcluir;
    
    
    @FXML
    private Button btnFIltrar;
    
    @FXML
    private TextField txtFiltrar;
    @FXML
    private Button btnVoltar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarContatos("");
    }    

    @FXML
    private void btnVoltarOnAction(ActionEvent event) throws IOException {
        App.setRoot("Principal");
    }
    
    @FXML
    private void btnFIltrarOnAction(ActionEvent event) { //desenvolver botao filtrar
         carregarContatos(txtFiltrar.getText());
    }
    
    public void carregarContatos(String param) {
        // Configuração das colunas de texto
        colun_Titulo.setCellValueFactory(new PropertyValueFactory<>("Titulo")); // Certifique-se que o atributo da classe seja "titulo"
        colun_nomeArtista.setCellValueFactory(new PropertyValueFactory<>("nome_Artista")); // Certifique-se que o atributo da classe seja "nomeArtista"
        colun_Gravadora.setCellValueFactory(new PropertyValueFactory<>("Gravadora")); // Certifique-se que o atributo da classe seja "gravadora"
        colun_Ano.setCellValueFactory(new PropertyValueFactory<>("Ano"));
        colun_PaisLancamento.setCellValueFactory(new PropertyValueFactory<>("Pais_Lancamento"));
        colun_selo.setCellValueFactory(new PropertyValueFactory<>("Selo"));
        colun_Estilo.setCellValueFactory(new PropertyValueFactory<>("Estilo"));
        tImagem.setCellValueFactory(new PropertyValueFactory<>("CaminhoImg"));
        try {
            // Carregar os dados do banco de dados
            AlbumDao dao = DaoFactory.novoAlbumDAO();
            List<Album> listaAlbum = dao.listar(param);
            
            // Criar a ObservableList para a TableView
            ObservableList<Album> observableListAlbum = FXCollections.observableArrayList(listaAlbum);
            
            // Definir a lista de itens na TableView
            TblAlbum.setItems(observableListAlbum);
            
        } catch (Exception e) {
            System.out.println("Erro ao carregar os contatos: " + e.getMessage());
        }
    }


    @FXML
    private void btnEditarOnAction(ActionEvent event) throws IOException {
        Album albumSelecionado = TblAlbum.selectionModelProperty().getValue().getSelectedItem();
        InserirColecaoController.setAlbumSelecionado(albumSelecionado);
        App.setRoot("InserirColecao");
    }

    @FXML
    private void btnExcluirOnAction(ActionEvent event) throws Exception {
        Album albumSelecionado = TblAlbum.selectionModelProperty().getValue().getSelectedItem();
        AlbumDao dao = DaoFactory.novoAlbumDAO();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Aviso");
        alert.setContentText("Confirma exclusão de " + albumSelecionado.getTitulo()+ "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                dao.excluir(albumSelecionado);
            } catch (Exception e) {
                String mensagem = "Ocorreu um erro: " + e.getMessage();
                Alert alertErro = new Alert(Alert.AlertType.INFORMATION);
                alertErro.setTitle("Aviso");
                alertErro.setContentText(mensagem);
                alertErro.showAndWait();
            }
        }
        carregarContatos("");
    }

    @FXML
    private void txtFiltrar(ActionEvent event) { //filtrar
       carregarContatos(txtFiltrar.getText());
    }

    

   
}
