package models;



import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class MejoraRendimiento extends Model {
	
	@Required
	public double mejora;
	
	@ManyToOne
	@Required
	public MedidaAdaptacion medida;
	
	@ManyToOne
	@Required	
	public ClasificacionCultivo clasificacionCultivo;

	public MejoraRendimiento(double mejora) {
		this.mejora = mejora;
	}
	
	@Override
	public String toString() {
	    return ("Medida: "+this.medida+" | Rendimiento del "+this.mejora+" para cultivos de "+this.clasificacionCultivo.nombre);
	}
}
