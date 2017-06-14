/**
 * Sample Skeleton for 'Artsmia.fxml' Controller Class
 */

package it.polito.tdp.artsmia;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.Model;
import it.polito.tdp.artsmia.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArtsmiaController {
	Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ChoiceBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtFieldStudenti"
    private TextField txtFieldStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void handleCreaGrafo(ActionEvent event) {
    	Integer anno = boxAnno.getValue();
    	model.setAnno(anno);
    	
    	int soli= model.creaGrafo(anno);
    	if(soli>0){
    		txtResult.appendText("Il grafo non è fortemente connesso\n");
    	}
    	if(model.getmax(anno)!= null){
    		txtResult.appendText("Maggior numero di opere d'arte nella mostra: "+model.getmax(anno).toString());
    	}
    }

    @FXML
    void handleSimula(ActionEvent event) {
    	String numero = txtFieldStudenti.getText();
    	
    	try{
    		int n= Integer.parseInt(numero);
    		if(n>0){
    			model.avviaSim(n);
    			
    			for(Studente s: model.getStatistiche()){
    				txtResult.appendText(s.getId()+" "+s.getMostre().size());
    			}
    			
    		} else{
    			txtResult.appendText("Inserire anno maggiore di 0\n");
    		}
    		
    	}catch(NumberFormatException e){
    		e.printStackTrace();
    	}
    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtFieldStudenti != null : "fx:id=\"txtFieldStudenti\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";

        

    }

	public void setModel(Model model) {
		this.model= model;
        this.boxAnno.getItems().addAll(model.getAnni());
	}
}
