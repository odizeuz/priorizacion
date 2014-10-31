package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.data.validation.Required;
import play.db.jpa.GenericModel;
import play.db.jpa.Model;

@Entity
public class Agricultor extends Model {
	
	@Required
	public int dni;
	
	@Required
	public String nombre;
	
	@OneToMany(mappedBy="productor", cascade=CascadeType.ALL)	
	public List <CultivoImplementado> cultivos;
	

	@Required
	public double otrasFuentesIngreso;	
	

	public Agricultor(int dni, String nombre, double otrasFuentesIngreso) {
		this.dni = dni;
		this.nombre = nombre;
		this.cultivos = new ArrayList<CultivoImplementado>();
		this.otrasFuentesIngreso = otrasFuentesIngreso;
	}
	
	public Agricultor agregarCultivo(Cultivo cultivo, double rendimiento0, double precioVentaPromedio0, double rendimiento1, double precioVentaPromedio1, double rendimiento2, double precioVentaPromedio2,
		double valorJornalesPreparacion, double valorMaquinariaPreparacion, double valorInsumosPreparacion, double valorJornalesSiembra, double valorMaquinariaSiembra, double valorInsumosSiembra, double valorJornalesMantenimiento, double valorMaquinariaMantenimiento, double valorInsumosMantenimiento, double valorJornalesFertilizacion, double valorMaquinariaFertilizacion, double valorInsumosFertilizacion, double valorJornalesControl, double valorMaquinariaControl, double valorInsumosControl, double valorJornalesCosecha, double valorMaquinariaCosecha, double valorInsumosCosecha,double valorTransporteComercializacion,double valorInsumosComercializacion){
		CultivoImplementado implementado = new CultivoImplementado(rendimiento0, precioVentaPromedio0, rendimiento1, precioVentaPromedio1, rendimiento2, precioVentaPromedio2, valorJornalesPreparacion, valorMaquinariaPreparacion, valorInsumosPreparacion, valorJornalesSiembra, valorMaquinariaSiembra, valorInsumosSiembra, valorJornalesMantenimiento, valorMaquinariaMantenimiento, valorInsumosMantenimiento, valorJornalesFertilizacion, valorMaquinariaFertilizacion, valorInsumosFertilizacion, valorJornalesControl, valorMaquinariaControl, valorInsumosControl, valorJornalesCosecha, valorMaquinariaCosecha, valorInsumosCosecha, valorTransporteComercializacion, valorInsumosComercializacion, cultivo, this);
		this.cultivos.add(implementado);
		
		this.save();
		return this;
	}
	
	public void limpiarCultivos(){
		for(CultivoImplementado c: this.cultivos){
			((CultivoImplementado) CultivoImplementado.findById(c.id)).delete();
		}
		this.cultivos.clear();
	}
}
