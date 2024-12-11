/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Album;
/**
 *
 * @author zzzexezzz
 */
public class AlbumDao {
     private Connection conn;

    public AlbumDao() throws Exception {
        this.conn = ConnFactory.getConnection();
    }
      
    public void editar(Album entidade) throws Exception { //EDITAR
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE album SET TITULO=?, GRAVADORA=?, SELO=?, ESTILO=?,"
                    + " PAIS_LANCAMENTO=?, ANO_LANCAMENTO=?, NOME_ARTISTA=?, CAMINHO_IMG=? WHERE ID_ALBUM=?");
            ps.setString(1, entidade.getTitulo());
            ps.setString(2, entidade.getGravadora());
            ps.setString(3, entidade.getSelo());
            ps.setString(4, entidade.getPais_Lancamento());
            ps.setString(5, entidade.getEstilo());
            ps.setString(6, entidade.getAno());
            ps.setString(7, entidade.getNome_Artista());
            ps.setString(8, entidade.getCaminhoImg());
            ps.execute();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void incluir(Album entidade) throws Exception { //INCLUIR
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO album (TITULO, GRAVADORA, SELO, ESTILO, PAIS_LANCAMENTO, ANO_LANCAMENTO, NOME_ARTISTA, CAMINHO_IMG) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, entidade.getTitulo());
            ps.setString(2, entidade.getGravadora());
            ps.setString(3, entidade.getSelo());
            ps.setString(4, entidade.getPais_Lancamento());
            ps.setString(5, entidade.getEstilo());
            ps.setString(6, entidade.getAno());
            ps.setString(7, entidade.getNome_Artista());
            ps.setString(8, entidade.getCaminhoImg());
            ps.execute();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    public void excluir(Album entidade) throws Exception { //excluir
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Album WHERE ID_ALBUM=?");
            ps.setInt(1, entidade.getId_album());
            ps.execute();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
     public List<Album> listar(String param) throws Exception {
        PreparedStatement ps = null;
        
        if(param == ""){
          ps = conn.prepareStatement("SELECT * FROM album");
        } else {
          ps = conn.prepareStatement("SELECT * FROM album WHERE TITULO like  '%" + param + "%'");      
                }
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
            List<Album> lista = new ArrayList();
            while (rs.next()) {
                Album album = new Album();
                album.setId_album(rs.getInt("ID_ALBUM"));
                album.setTitulo(rs.getString("TITULO"));
                album.setNome_Artista(rs.getString("NOME_ARTISTA"));
                album.setGravadora(rs.getString("GRAVADORA"));
                album.setPais_Lancamento(rs.getString("PAIS_LANCAMENTO"));
                album.setEstilo(rs.getString("ESTILO"));
                album.setAno(rs.getString("ANO_LANCAMENTO"));
                album.setSelo(rs.getString("SELO"));
                album.setCaminhoImg(rs.getString("CAMINHO_IMG"));
                lista.add(album);
                
            }
            return lista;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

     }
}
