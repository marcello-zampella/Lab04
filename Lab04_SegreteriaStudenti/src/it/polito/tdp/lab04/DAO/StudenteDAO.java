package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	public Studente getStudenteByMatricola(int matricola) {
		final String sql = "SELECT s.nome, s.cognome\n" + 
				"FROM studente s\n" + 
				"WHERE s.matricola=?";


		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			if(rs.next()==true) {
				Studente s=new Studente();

				s.setNome(rs.getString("nome"));
				s.setCognome(rs.getString("cognome"));
				s.setMatricola(matricola);
				return s;
			}
			else return null;
			

			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
		
	}

	public List<Corso> getCorsiByStudente(Studente s) {
		final String sql ="SELECT *\n" + 
				"FROM corso c,iscrizione i\n" + 
				"WHERE c.codins=i.codins AND i.matricola=?";


		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola());
			ResultSet rs = st.executeQuery();
			List<Corso> corsi= new LinkedList<Corso>();
			while(rs.next()) {
				Corso c=new Corso();

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				
				c.setCodins(codins);
				c.setCrediti(numeroCrediti);
				c.setNome(nome);
				c.setPeriodo(periodoDidattico);
				corsi.add(c);
			}
			return corsi;
			

			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	public boolean iscriviStudenteAlCorso(String corso, Studente studente) {
		final String sql ="INSERT INTO iscrizione (matricola, codins)\n" + 
				"VALUES (?, ?)";


		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso);
			
			
			st.executeUpdate(); //nel cas io debba manipolare i dati, devo usare executeUpdate()
		
			return true;
			

			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
		
		
	}


}
