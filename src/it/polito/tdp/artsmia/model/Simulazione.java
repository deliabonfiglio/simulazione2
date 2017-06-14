package it.polito.tdp.artsmia.model;

import java.util.*;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;

import it.polito.tdp.artsmia.model.Evento.EventType;

public class Simulazione {
	
	private PriorityQueue<Studente> codaStudenti;
	private List<Exhibition> listaMostre;
	private DirectedGraph<Exhibition, DefaultEdge> grafo;
	
	// Event queue
	private PriorityQueue<Evento> queue;	

	public Simulazione() {
		super();
		codaStudenti= new PriorityQueue<>();
		queue = new PriorityQueue<>();
		listaMostre= new ArrayList<Exhibition>();
	}
	
	public void avvia(List<Exhibition> mostre, Model model, int n, int anno) {
		this.aggiungiStudenti(n, mostre);
    	this.aggiungiMostre(mostre, model.getGrafo(anno));
    	this.run();
	}


	private void aggiungiMostre(List<Exhibition> mostre, DirectedGraph<Exhibition, DefaultEdge> graph) {
		this.listaMostre= new ArrayList<Exhibition> (mostre);
		this.grafo= graph;
	}

	private void aggiungiStudenti(int N, List<Exhibition> mostre) {
		int idmostra= (int)(1 + Math.random()*mostre.size());
		
		Exhibition mostra= mostre.get(idmostra);
			
		for(int i =1; i<=N; i++){
		
			Studente s = new Studente(i);
			
			this.codaStudenti.add(s);
			
				Evento e = new Evento(s,mostra, mostra.getBegin(), EventType.IN);
				this.queue.add(e);			
		}
	}
	

	public void run() {
		while (!queue.isEmpty()) {
			Evento e = queue.poll();
			//System.out.println(e);

			switch (e.getTipo()) {
			case IN:
				processInEvent(e);
				break;
			default:
				break;
			
			}
		}
	}

	private void processInEvent(Evento e) {
		Studente s = e.getStudente();
		s.addMostre(e.getMostra());
		
		Set<DefaultEdge> vicini = grafo.outgoingEdgesOf(e.getMostra());
		
			Exhibition nuova = vicini.get((int) (Math.random()*vicini.size()));
			
		Evento enuovo= new Evento(s, nuova, nuova.getBegin(), EventType.IN);
		queue.add(enuovo);	
		
	}
	
	public List<Studente> getStatistiche(){
		List<Studente> lista = new ArrayList<Studente>();
		
		for(Studente s : this.codaStudenti){
			if(!lista.contains(s.getId()))
				lista.add(s);
		}
		
		Collections.sort(lista);
		
		return lista;		
	}
	
}
