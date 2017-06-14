package it.polito.tdp.artsmia.model;

public class Evento implements Comparable<Evento>{
	public enum EventType{IN};
	
	private Studente Studente;
	private Exhibition mostra;
	private int annoInizioMostra;				//istante di tempo in cui si verifica l'evento
	private EventType tipo;
	
	

	public Evento(Studente studente, Exhibition mostra, int annoInizioMostra,EventType tipo) {
		super();
		Studente = studente;
		this.mostra = mostra;
		this.annoInizioMostra = annoInizioMostra;
		this.tipo = tipo;
	}



	public Studente getStudente() {
		return Studente;
	}



	public void setStudente(Studente studente) {
		Studente = studente;
	}



	public Exhibition getMostra() {
		return mostra;
	}



	public void setMostra(Exhibition mostra) {
		this.mostra = mostra;
	}



	public int getAnnoInizioMostra() {
		return annoInizioMostra;
	}



	public void setAnnoInizioMostra(int annoInizioMostra) {
		this.annoInizioMostra = annoInizioMostra;
	}



	public EventType getTipo() {
		return tipo;
	}



	public void setTipo(EventType tipo) {
		this.tipo = tipo;
	}



	@Override
	public int compareTo(Evento arg0) {
		return this.annoInizioMostra-arg0.annoInizioMostra;
	}
		
}
