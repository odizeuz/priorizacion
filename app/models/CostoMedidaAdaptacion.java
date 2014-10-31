package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class CostoMedidaAdaptacion extends Model {

	@Required
	@ManyToOne	
	public Pais pais;

	@Required
	@ManyToOne	
	public MedidaAdaptacion medida;

	@Required
	public double costosPeriodo0;
	
	@Required
	public double costosPeriodo1;
	
	@Required
	public double costosPeriodo2;
	
	
	public CostoMedidaAdaptacion(Long idPais, double... costos) {
		this.pais = Pais.findById(idPais);
		costosPeriodo0 = costos[0];
		costosPeriodo1 = costos[1];
		costosPeriodo2 = costos[2];
	}
}
