package it.polito.tdp.lab04.controller;

import java.util.*;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SegreteriaStudentiController {

	private Model model;
	
	
    @FXML
    private ComboBox<String> menuCorsi;

    
    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;
    

    @FXML
    private TextArea txtArea;

    @FXML
    void doCompilaDati(ActionEvent event) {
    	if(this.txtMatricola.getText().equals("")) {
    		this.txtArea.setText("INSERIRE UNA MATRICOLA CORRETTA");
    		return;
    	}
    	int matricola=Integer.parseInt(this.txtMatricola.getText());
    	
    	Studente s=model.getStudenteByMatricola(matricola);
    	if(s==null) {
    		this.txtArea.setText("LA MATRICOLA NON ESISTE");
    		return;
    	}
    		
    	this.txtCognome.setText(s.getCognome());
    	this.txtNome.setText(s.getNome());
    }
    
    private boolean scelta =false;
    @FXML
    void doSceltaCorso(ActionEvent event) {
    	this.scelta=true;
    	
    }
    
    @FXML
    void doCercaIscritti(ActionEvent event) {
    	this.txtArea.clear();
    	if(this.scelta==false) {
    		this.txtArea.appendText("DEVI SCEGLIERE UN CORSO");
    		return ;
    	}
    	
    	ArrayList<Studente> studenti=this.model.getIscrittiByCorso(this.menuCorsi.getValue());
    	for(Studente s: studenti) {
    		this.txtArea.appendText(s.getMatricola()+" "+s.getCognome()+" "+s.getNome()+"\n");
    	}
    }
    

    
    @FXML
    void doReset(ActionEvent event) {
    	this.txtArea.clear();
    	this.txtCognome.clear();
    	this.txtMatricola.clear();
    	this.txtNome.clear();
    }


	public void setModel(Model model) {
		this.model=model;
		
	}
	
    @FXML
    void doCercaCorsi(ActionEvent event) {
    	this.txtArea.clear();
    	int matricola=Integer.parseInt(this.txtMatricola.getText());
    	Studente s=model.getStudenteByMatricola(matricola);
    	if(s==null) {
    		this.txtArea.setText("LA MATRICOLA NON ESISTE");
    		return;
    	}
    	
    	List<Corso> corsi= this.model.getCorsiByIscritto(s);
    	if(corsi.isEmpty()) {
    		this.txtArea.setText("LO STUDENTE NON E' ISCRITTO A NESSUN CORSO");
    		return;
    	}
    	for(Corso c: corsi) {
    		this.txtArea.appendText(c.getCodins()+" "+c.getNome()+"\n");
    	}
    	
    }
    

    @FXML
    void doIscriviAlCorso(ActionEvent event) {
    	this.txtArea.clear();
    	Studente s=model.getStudenteByMatricola(Integer.parseInt(this.txtMatricola.getText()));
    	if(this.model.getIscrittiByCorso(this.menuCorsi.getValue()).contains(s)) {
    		this.txtArea.setText("LO STUDENTE E' GIA' ISCRITTO AL CORSO");
    		return;
    	}
    	boolean iscrizione=this.model.iscriviStudenteAlCorso(this.menuCorsi.getValue(), s);
    	
    }

	public void popolaMenu() {
		
		for(Corso c: model.getTuttiCorsi()) {
			
			this.menuCorsi.getItems().add(c.getCodins()+" "+c.getNome());
		}
		
	    
		
		
		
		
		
	}

}
