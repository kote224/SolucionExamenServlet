package es.salesianos.model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class Videogame {
	String console;
	String videogame;
	Date launch;
	int edad;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public String getId() {
		return videogame;
	}

	public void setId(String id) {
		this.videogame = id;
	}
	
	public String getTittle() {
		return videogame;
	}

	public void setTittle(String videogame) {
		this.videogame = videogame;
	}
	
	
	public Date getLanzamiento() {
		return launch;
	}

	public void setLanzamiento(String launch) {
		try {
			this.launch = sdf.parse(launch);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

}
