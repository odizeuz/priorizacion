package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Cultivo extends Model {
	
	@Required
	public String nombre;
	
	@Column(nullable=false, columnDefinition="boolean default false")  
	public boolean cicloAnual;
	
	@ManyToOne
	@Required
	public ClasificacionCultivo clasificacion;
	
	@OneToMany(mappedBy="cultivo", cascade=CascadeType.ALL)
	public List <CultivoImplementado> cultivos;

	public Cultivo(String nombre, ClasificacionCultivo clasificacion, boolean cicloAnual) {
		this.nombre = nombre;
		this.clasificacion = clasificacion;
		this.cultivos = new ArrayList<CultivoImplementado>();
		this.cicloAnual = cicloAnual;
	}
		
	@Override
	public String toString() {
	    return this.nombre;
	}
}
