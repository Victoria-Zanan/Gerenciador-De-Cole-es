/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class TabelaViewController implements Initializable {

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
    @FXML
    private Button btnVoltar;
  
    private List<Album> ListaAlbum;
    private ObservableList<Album> ObservableListAlbum;
    @FXML
    private TextField txtFiltro;
    @FXML
    private Button btnFiltrar;
    @FXML
    private Button btnLimpar;
  
   
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
    
    public void carregarContatos(String param) {
    // Configuração das colunas de texto
    colun_Titulo.setCellValueFactory(new PropertyValueFactory<>("Titulo")); // Certifique-se que o atributo da classe seja "titulo"
    colun_nomeArtista.setCellValueFactory(new PropertyValueFactory<>("nome_Artista")); // Certifique-se que o atributo da classe seja "nomeArtista"
    colun_Gravadora.setCellValueFactory(new PropertyValueFactory<>("Gravadora")); // Certifique-se que o atributo da classe seja "gravadora"
    colun_Ano.setCellValueFactory(new PropertyValueFactory<>("Ano"));
   colun_Estilo.setCellValueFactory(new PropertyValueFactory<>("Estilo"));
    colun_selo.setCellValueFactory(new PropertyValueFactory<>("Selo"));
     colun_PaisLancamento.setCellValueFactory(new PropertyValueFactory<>("Pais_Lancamento"));
    
    
    tImagem.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCaminhoImg()));
    
    // Definir a célula da imagem
    tImagem.setCellFactory(col -> new TableCell<Album, String>() {
        private final ImageView imageView = new ImageView();

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            
            if (empty || item == null) {
                setGraphic(null);  // Caso a célula esteja vazia
            } else {
                try {
                    // Tentar carregar a imagem
                    Image image = new Image(new FileInputStream(item));  // Usando FileInputStream para o caminho local
                    imageView.setImage(image);
                    imageView.setFitWidth(50);  // Ajuste o tamanho conforme necessário
                    imageView.setFitHeight(50);
                    setGraphic(imageView);
                } catch (IOException e) {
                    System.out.println("Erro ao carregar a imagem: " + e.getMessage());
                    setGraphic(null);  // Se houver erro, não exibe a imagem
                }
            }
        }
    });

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
    private void txtFiltroOnAction(ActionEvent event) {
        carregarContatos(txtFiltro.getText());
    }

    @FXML
    private void btnFiltrarOnAction(ActionEvent event) {
        carregarContatos(txtFiltro.getText());
    }

    @FXML
    private void btnLimparOnAction(ActionEvent event) {
        txtFiltro.clear();
        carregarContatos("");
    }
}


    
            
