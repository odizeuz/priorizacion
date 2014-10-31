package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class ReduccionCosto extends Model {
	
	@Required
	public double reduccion;
	
	/**
	 * El parámetro inputReducción me permite indicarle a la herramienta
	 * dónde exactamente debe ubicar la reducción de costos para el análisis Costo-Beneficio 
	 */
	
	@Required
	public int inputReduccion;
	
	@Required
	@ManyToOne	
	public MedidaAdaptacion medida;
	
	@Required
	@ManyToOne
	public ClasificacionCultivo clasificacionCultivo;

	public ReduccionCosto(double reduccion, int inputReduccion) {
		this.reduccion = reduccion;
		this.inputReduccion = inputReduccion;
	}
	
	@Override
	public String toString() {
	    return ("Reduccion de "+this.medida+" para cultivos de "+this.clasificacionCultivo.nombre+" en el input: "+this.inputReduccion);
	}
	
}