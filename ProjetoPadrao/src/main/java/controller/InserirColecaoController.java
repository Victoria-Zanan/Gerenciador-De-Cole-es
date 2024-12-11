/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Album;
import model.dao.AlbumDao;
import model.dao.DaoFactory;
import start.App;

/**
 * FXML Controller class
 *
 * @author zzzexezzz
 */
public class InserirColecaoController implements Initializable {

    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtEstilo;
    @FXML
    private TextField txtAnoDeLancamento;
    @FXML
    private TextField txtPaisDeLancamento;
    @FXML
    private TextField txtGravadora;
    @FXML
    private TextField txtSelo;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnVoltar;
    @FXML
    private TextField txtNomeArtista;
    @FXML
    private TextField lblCaminhoImagem;

    private static Album albumSelecionado;
    @FXML
    private Button btnInserirFoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (albumSelecionado != null) {
            txtTitulo.setText(albumSelecionado.getTitulo());
            txtEstilo.setText(albumSelecionado.getEstilo());
            txtAnoDeLancamento.setText(albumSelecionado.getAno());
            txtPaisDeLancamento.setText(albumSelecionado.getPais_Lancamento());
            txtGravadora.setText(albumSelecionado.getGravadora());
            txtSelo.setText(albumSelecionado.getSelo());
            txtNomeArtista.setText(albumSelecionado.getNome_Artista());
            lblCaminhoImagem.setText(albumSelecionado.getCaminhoImg());
        }
    }    

    @FXML
    private void txtTituloOnAction(ActionEvent event) {
    }


    @FXML
    private void txtEstiloOnAction(ActionEvent event) {
    }

    @FXML
    private void txtAnoDeLancamentoOnAction(ActionEvent event) {
    }

    @FXML
    private void txtPaisDeLancamentoOnAction(ActionEvent event) {
    }

    @FXML
    private void txtGravadoraOnAction(ActionEvent event) {
    }

    @FXML
    private void txtSeloOnAction(ActionEvent event) {
    }

    @FXML
    private void txtNomeArtistaOnAction(ActionEvent event) {
    }

    @FXML
  private void btnSalvarOnAction(ActionEvent event) throws IOException, Exception {
        Album album = new Album();
       
        album.setTitulo(txtTitulo.getText());
        album.setGravadora(txtGravadora.getText());
        album.setAno(txtAnoDeLancamento.getText());
        album.setEstilo(txtEstilo.getText());
        album.setSelo(txtSelo.getText());
        album.setPais_Lancamento(txtPaisDeLancamento.getText());
        album.setNome_Artista(txtNomeArtista.getText());
        album.setCaminhoImg(lblCaminhoImagem.getText());  
        AlbumDao daoAlbum = DaoFactory.novoAlbumDAO(); 
        daoAlbum.incluir(album);
     
        App.setRoot("Principal");
    }

    @FXML
    private void btnVoltarOnAction(ActionEvent event) throws IOException {
        App.setRoot("Principal");
    }
    
    public static Album getAlbumSelecionado() {
        return albumSelecionado;
    }

    public static void setAlbumSelecionado(Album albumSelecionado) {
        InserirColecaoController.albumSelecionado = albumSelecionado;
    }

    @FXML
    private void btnInserirFotoOnAction(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
        
        // Configurações do FileChooser (opcional)
        fileChooser.setTitle("Selecione uma imagem");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg"));
        
        // Obtém o Stage da janela atual (a sub-janela)
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        // Exibe o diálogo de seleção de arquivos
        File selectedFile = fileChooser.showOpenDialog(stage);
        
        if (selectedFile != null) {
           
            String caminhoImagem = selectedFile.getAbsolutePath();
            lblCaminhoImagem.setText(caminhoImagem);
        } else {
            System.out.println("Nenhum arquivo foi selecionado.");
        }
    }
  }
    

