package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class ServicioEcosistemico extends Model{
	
	@Required
	public String nombre;
	
	@Required
	public String descripcion;
	
	@Required
	public double puntaje;
	
	@Required
	@ManyToOne	
	public MedidaAdaptacion medida;
	
	public ServicioEcosistemico(String nombre, String descripcion, int puntaje) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.puntaje=puntaje;
	}
	
	@Override
	public String toString() {
	    return (this.nombre+" para la medida "+this.medida.nombre);
	}
	
}
