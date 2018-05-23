package it.polito.tdp.porto.db;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.AuthorIDMap;
import it.polito.tdp.porto.model.Paper;
import it.polito.tdp.porto.model.PaperIDMap;

public class TestPortoDAO {
	
	public static void main(String args[]) {
		PortoDAO pd = new PortoDAO();
		AuthorIDMap map = new AuthorIDMap();
		PaperIDMap mapPaper = new PaperIDMap();
		
		/*System.out.println(pd.getAutore(85));
		System.out.println(pd.getArticolo(2293546));
		System.out.println(pd.getArticolo(1941144));
		 */
		System.out.println("\n----------Stampo tutti gli autori-------------\n");
		List<Author> lista = new ArrayList<>(pd.getAllAutori(map));
		for(Author a : lista ) {
			System.out.println(a);
		}
		System.out.println("Dimensione array : "+lista.size()+"\n");
		
		System.out.println("\n----------Stampo tutti i paper-------------\n");
		List<Paper> listaPaper = new ArrayList<>(pd.getAllPaper(mapPaper));
		for(Paper a : listaPaper ) {
			System.out.println(a);
		}
		System.out.println("Dimensione array : "+listaPaper.size()+"\n");
		
		
		System.out.println("\n--------Stampo Coatori-------\n");
		List<Author> coautori = new ArrayList<>(pd.getCoautori(2502162));
		for(Author a : coautori) {
			System.out.println(a);
		}
	}
	
	

}
