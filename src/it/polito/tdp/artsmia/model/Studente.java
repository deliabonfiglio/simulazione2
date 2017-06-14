package it.polito.tdp.artsmia.model;

import java.util.*;

public class Studente implements Comparable<Studente>{
	private int id;
	private Set<Exhibition> mostre;
	
	
	public Studente(int id) {
		super();
		this.id = id;
		this.mostre = new HashSet<Exhibition>();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Set<Exhibition> getMostre() {
		return mostre;
	}


	public void addMostre(Exhibition mostr) {
		this.mostre.add(mostr);
	}


	@Override
	public int compareTo(Studente altro) {

		return this.mostre.size()-altro.mostre.size();
	}
}
