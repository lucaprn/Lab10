package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.List;
import org.jgrapht.graph.SimpleGraph;


import com.sun.corba.se.impl.orbutil.graph.Graph;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		model.creaGrafo();
		System.out.println("\n-----Stampo Numero Vertici-----\n");
		System.out.println(model.getGrafo().vertexSet().size());
		System.out.println("\n-----Stampo Numero Archi-----\n");
		System.out.println(model.getGrafo().edgeSet().size());
		System.out.println("\n-----Stampo Vicini-----\n");
		/*List<Author> tmp = new ArrayList<>(model.getCoautori(new Author(1847,"Lioy","Antonio")));
		for(Author a : tmp) {
			System.out.println(a);
		}*/
		System.out.println(model.toStringPercorso(new Author(27355,"Zhang","Linchao"),new Author(2185,"Taragna","Michele")));
	
		
	}

}
