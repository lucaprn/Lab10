package it.polito.tdp.porto.model;

import java.util.Map;
import java.util.TreeMap;

public class AuthorIDMap {
	
	private Map<Integer,Author> autori;
	
	public AuthorIDMap() {
		autori=new TreeMap<>();
	}
	
	public Author get(Author a) {
		Author old = autori.get(a.getId());
		if(old==null) {
			autori.put(a.getId(), a);
			return a;
		}else {
			return old;
		}
	}
	
	public void put(Author a) {
		this.autori.put(a.getId(), a);
	}

}
