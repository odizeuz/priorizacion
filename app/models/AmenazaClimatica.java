package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class AmenazaClimatica extends Model{
	
	@Required
	public String nombre;
	
	@Required
	public String descripcion;
	
	public AmenazaClimatica(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}	
	
	@Override
	public String toString() {
	    return this.nombre;
	}
	
}
