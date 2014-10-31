package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class AmenazaProductiva extends Model {

	public String name;
	
	@Required
	public String descripcion;
	
	public AmenazaProductiva(String name, String descripcion) {
		this.name = name;
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
	    return this.name;
	}
}
