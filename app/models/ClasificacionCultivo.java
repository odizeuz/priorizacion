package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class ClasificacionCultivo extends Model {

	
	
	@Required
	public String nombre;
	
	@OneToMany(mappedBy="clasificacion", cascade=CascadeType.ALL)
	public List <Cultivo> cultivosGenerales;
	
	@OneToMany(mappedBy="clasificacionCultivo", cascade=CascadeType.ALL)
	public List <ReduccionCosto> reducciones;
	
	@OneToMany(mappedBy="clasificacionCultivo", cascade=CascadeType.ALL)
	public List <MejoraRendimiento> mejoras;
	
	public ClasificacionCultivo(String nombre) {
		this.nombre = nombre;
	}
	
	public ClasificacionCultivo agregarCultivoGeneral(Cultivo c){
		this.cultivosGenerales.add(c);
		this.save();
		return this;
	}
	
	@Override
	public String toString() {
	    return this.nombre;
	}
	
}
