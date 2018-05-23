package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.AuthorIDMap;
import it.polito.tdp.porto.model.Paper;
import it.polito.tdp.porto.model.PaperIDMap;

public class PortoDAO {

	
	public Author getAutore(int id) {

		final String sql = "SELECT * FROM author where id=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				return autore;
			}
			conn.close();
			return null;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato l'id ottengo l'articolo.
	 */
	public Paper getArticolo(int eprintid) {

		final String sql = "SELECT * FROM paper where eprintid=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, eprintid);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				return paper;
			}
			conn.close();
			return null;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	public List<Author> getAllAutori(AuthorIDMap map){
		List<Author> result = new ArrayList<>();
		final String sql = "SELECT * FROM author";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				result.add(map.get(autore));

			}
			conn.close();

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		return result;	
	}
	
	public List<Paper> getAllPaper(PaperIDMap map){
		List<Paper> result = new ArrayList<>();
		final String sql = "SELECT * FROM paper";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				result.add(map.get(paper));
			}
			conn.close();

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		return result;	
	}
	
	public List<Author> getCoautori(int idpubblicazione,AuthorIDMap map){
		List<Author> coAutori = new ArrayList<>();
		final String sql = "SELECT a.id, a.lastname, a.firstname FROM creator AS c, author AS a WHERE c.authorid=a.id AND c.eprintid=?";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, idpubblicazione);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				coAutori.add(map.get(autore));
			}
			conn.close();
			return coAutori;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
		
	}

	public Paper getPaper(Author a1, Author a2) {
		
		final String sql = "SELECT p1.eprintid, p1.title, p1.issn, p1.publication, p1.type, p1.types  FROM creator AS c1, creator AS c2, paper AS p1 WHERE c1.eprintid=c2.eprintid AND p1.eprintid=c1.eprintid AND c1.authorid=? AND c2.authorid=?";
		Paper paper =null;
		try {
			
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, a1.getId());
			st.setInt(2, a2.getId());

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				
			}
			conn.close();
			return paper;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
	
	}
	
}