package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;


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
			List<Author> autori = new ArrayList<>(dao.getCoautori(p.getEprintid(),mapAuthor));
			for(int i =0 ; i<autori.size(); i++) {
				for(int j=i+1; j<autori.size(); j++) {
					grafo.addEdge(autori.get(i), autori.get(j));
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
		List<Author> tmp = new ArrayList<>(this.getCoautori(a));
		Collections.sort(tmp);
		for(Author aut : tmp) {
			sb.append(""+aut.toString()+"\n");
		}
		return sb.toString();
	}
	
	public List<Author> getArticoliComuni(Author a1,Author a2){
		List<Author> result;
		DijkstraShortestPath<Author, DefaultEdge> cammino = new DijkstraShortestPath<>(this.grafo);
		result = new ArrayList<>(cammino.getPath(a1, a2).getVertexList());
		return result;
	}
	
	public List<Paper> getPercorso(Author a1, Author a2) {
		List<Author> autori = new ArrayList<>(this.getArticoliComuni(a1, a2));
		List<Paper> papers = new ArrayList<>();
		for(int i=0 ; i<autori.size()-1; i++) {
			papers.add(dao.getPaper(autori.get(i),autori.get(i+1)));
			
		}
		return papers;
	}
	
	public String toStringPercorso(Author a1, Author a2) {
		StringBuilder sb=new StringBuilder();
		List<Paper> papers = new ArrayList(this.getPercorso(a1, a2));
		
		for(Paper p : papers) {
			sb.append(""+p.toString()+"\n");
		}
		return sb.toString();
	
	}
	
	public List<Author> getBox2(Author a){
		List<Author> result = new ArrayList<>();
		List<Author> coautori = new ArrayList<>(this.getCoautori(a));
		for(Author a1 : this.listaAuthor) {
			boolean presente = false;
			for(Author a2 : coautori) {
				if(a1.getId()==a2.getId() || a1.getId()==a.getId()) {
					presente = true;
				}
			}
			if(!presente) {
				result.add(a1);
			}
		}
		return result;
	}
	
	
}
