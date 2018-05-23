package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	
	
	private AuthorIDMap mapAuthor;
	private PaperIDMap mapPaper;
	private PortoDAO dao;
	private List<Paper> listaPaper;
	private List<Author> listaAuthor;
	private Graph<Author,DefaultEdge> grafo;
	
	
	public Model() {
		mapAuthor = new AuthorIDMap();
		mapPaper = new PaperIDMap();
		dao = new PortoDAO();
		listaPaper = new ArrayList(dao.getAllPaper(mapPaper));
		listaAuthor = new ArrayList(dao.getAllAutori(mapAuthor));
		grafo = new SimpleGraph<>(DefaultEdge.class);
	}
	
	public void creaGrafo() {
		Graphs.addAllVertices(grafo, listaAuthor);
		this.addEdge();
		
	}

	private void addEdge() {
		for(Paper p : this.listaPaper){
			List<Author> autori = new ArrayList<>(dao.getCoautori(p.getEprintid()));
			if(autori.size()>0) {
				Author a = autori.get(0);
				for(int i =1 ; i<autori.size(); i++) {
					grafo.addEdge(a, autori.get(i));
				}
			}
		}
		
	}

	public Graph<Author, DefaultEdge> getGrafo() {
		return grafo;
	}
	
	public List<Author> getCoautori(Author a){
		List<Author> coAutori = new ArrayList<>(Graphs.neighborListOf(grafo, a));
		return coAutori;
	}
	
	public String toStringCoatori(Author a) {
		StringBuilder sb = new StringBuilder();
		for(Author aut : this.getCoautori(a)) {
			sb.append(""+aut.toString()+"\n");
		}
		return sb.toString();
		
	}

}
