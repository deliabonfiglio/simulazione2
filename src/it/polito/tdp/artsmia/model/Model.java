package it.polito.tdp.artsmia.model;

import java.util.*;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	private int anno;
	private List<Integer> anni;
	private DirectedGraph<Exhibition, DefaultEdge> graph;
	private List<Exhibition> mostre;
	private Simulazione sim;

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public List<Integer> getAnni(){
		if(anni==null){
			ArtsmiaDAO dao = new ArtsmiaDAO();
			anni = dao.listAnni();
		}
		return anni;
	}
	
	public int creaGrafo(Integer anno){
		
		graph = new SimpleDirectedGraph<>(DefaultEdge.class);
		
		ArtsmiaDAO dao = new ArtsmiaDAO();
		
		mostre = dao.getMostre(anno);
		
		System.out.println(mostre.toString());
		
		Graphs.addAllVertices(graph, mostre);
		

		for(Exhibition e1: graph.vertexSet()){
			for(Exhibition e2: graph.vertexSet()){
				if(!e1.equals(e2) && e1.getBegin()<e2.getBegin() && e1.getEnd()>e2.getBegin()){
					graph.addEdge(e1, e2);
				}
			}
		}
			
		int conta=0;
		
		for(Exhibition e : graph.vertexSet()){
			if(graph.inDegreeOf(e)==0 || graph.outDegreeOf(e)== 0){
				conta++;
			}
		}
		
		return conta;
		//System.out.println(graph.toString());	
	}
	
	public String getmax(Integer anno){
		ArtsmiaDAO dao = new ArtsmiaDAO();
		return dao.getmax(anno);
	}
	
	public void avviaSim(int N){
		sim = new Simulazione();
		sim.avvia(mostre, this, anno, N);
	}

	public DirectedGraph<Exhibition, DefaultEdge> getGrafo(Integer anno) {
		if(graph==null){
			this.creaGrafo(anno);
		}
		return graph;
	}
	
	public List<Studente> getStatistiche(){
		return sim.getStatistiche();
	}
	
}
