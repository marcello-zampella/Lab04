package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	private ArrayList<Corso>corsi;
	
	public List<Corso> getTuttiCorsi() {
		CorsoDAO c=new CorsoDAO();
		 corsi= new ArrayList(c.getTuttiICorsi());
		return c.getTuttiICorsi();
		
		
	}

	public Studente getStudenteByMatricola(int matricola) {
		StudenteDAO c=new StudenteDAO();
		return c.getStudenteByMatricola(matricola);
	}

	public ArrayList<Studente> getIscrittiByCorso(String value) {
		CorsoDAO c=new CorsoDAO();
		String[] temp=value.split(" ");
		Corso corsotemp=new Corso();
		corsotemp.setCodins(temp[0]);
		return c.getStudentiIscrittiAlCorso(corsi.get(corsi.indexOf(corsotemp)));
	}

	public List<Corso> getCorsiByIscritto(Studente s) {
		StudenteDAO sa=new StudenteDAO();
		return sa.getCorsiByStudente(s);
	}
	


	public boolean iscriviStudenteAlCorso(String value, Studente s) {
		StudenteDAO sa= new StudenteDAO();
		String[] temp=value.split(" ");
		return sa.iscriviStudenteAlCorso(temp[0],s );
	}

}
