package it.polito.tdp.porto.model;

import java.util.Map;
import java.util.TreeMap;

public class PaperIDMap {

private Map<Integer,Paper> papers;

	public PaperIDMap() {
		papers=new TreeMap<>();
	}
	
	public Paper get(Paper p) {
		Paper old = papers.get(p.getEprintid());
		if(old==null) {
			papers.put(p.getEprintid(), p);
			return p;
		}else {
			return old;
		}
	}
	
	public void  put(Paper p) {
		this.papers.put(p.getEprintid(), p);
	}

}
