package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class MedidaAdaptacionImplementada extends Model{

	@ManyToOne
	@Required
	public MedidaAdaptacion medidaimplementada;
	
	public double acb;
	
	public double calificacionAmenazas;
	
	public double calificacionServiciosEcosistemicos;
	
	public double calificacionCapacidadAdaptativa;
	
	public double calificacionfinal;
	
	
	@ManyToOne
	@Required
	public Encuesta encuesta;

	public MedidaAdaptacionImplementada(MedidaAdaptacion medidaimplementada, Encuesta encuesta) {
		this.medidaimplementada = medidaimplementada;
		this.encuesta = encuesta;
	}
	
	@Override
	public int hashCode() {
        return new HashCodeBuilder(19, 29). // two randomly chosen prime numbers
            // if deriving: appendSuper(super.hashCode()).
            append(this.medidaimplementada.nombre).
            toHashCode();
    }

	@Override
    public boolean equals(Object obj) {
       if (!(obj instanceof MedidaAdaptacionImplementada))
            return false;
        if (obj == this)
            return true;

        MedidaAdaptacionImplementada rhs = (MedidaAdaptacionImplementada) obj;
        return new EqualsBuilder().
            // if deriving: appendSuper(super.equals(obj)).
            append(this.medidaimplementada.nombre, rhs.medidaimplementada.nombre).
            isEquals();
    }

	
}
