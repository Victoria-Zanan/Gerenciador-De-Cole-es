/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author zzzexezzz
 */
public class Album {
    private int Id_album;
    private String Titulo;
    private String Gravadora;
    private String Ano;
    private String Estilo;
    private String Selo;
    private String Pais_Lancamento;
    private String Nome_Artista;
    private String CaminhoImg;
    

    public Album() {
    }

    public Album(int Id_album, String Titulo, String Gravadora, String Ano, String Estilo,
            String Selo, String Pais_lancamento, String Nome_Artista, String CaminhoImg) {
        this.Id_album = Id_album;
        this.Titulo = Titulo;
        this.Gravadora = Gravadora;
        this.Ano = Ano;
        this.Estilo = Estilo;
        this.Selo = Selo;
        this.Pais_Lancamento = Pais_lancamento;
        this.Nome_Artista = Nome_Artista;
        this.CaminhoImg = CaminhoImg;
    }

    public int getId_album() {
        return Id_album;
    }

    public void setId_album(int Id_album) {
        this.Id_album = Id_album;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getGravadora() {
        return Gravadora;
    }

    public void setGravadora(String Gravadora) {
        this.Gravadora = Gravadora;
    }

    public String getAno() {
        return Ano;
    }

    public void setAno(String Ano) {
        this.Ano = Ano;
    }

    public String getEstilo() {
        return Estilo;
    }

    public String getCaminhoImg() {
        return CaminhoImg;
    }

    public void setCaminhoImg(String CaminhoImg) {
        this.CaminhoImg = CaminhoImg;
    }

     
    public void setEstilo(String Estilo) {
        this.Estilo = Estilo;
    }

    public String getSelo() {
        return Selo;
    }

    public void setSelo(String Selo) {
        this.Selo = Selo;
    }

    public String getPais_Lancamento() {
        return Pais_Lancamento;
    }

    public void setPais_Lancamento(String Pais_lancamento) {
        this.Pais_Lancamento = Pais_lancamento;
    }

    public String getNome_Artista() {
        return Nome_Artista;
    }

    public void setNome_Artista(String Nome_Artista) {
        this.Nome_Artista = Nome_Artista;
    }

}
