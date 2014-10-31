package models;

import javax.persistence.Entity;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Pais extends Model {

	@Required
	public String nombre;

	@Required
	public String moneda;

	@Required
	public String simbolo;
		
	public Pais(String nombre, String moneda, String simbolo) {
		this.nombre = nombre;
		this.moneda = moneda;
		this.simbolo = simbolo;
	}
}
