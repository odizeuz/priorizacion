package models;

import java.util.*;

import javax.persistence.*;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class MedidaAdaptacion extends Model {
	
	@Required
	public String nombre;

	@OneToMany(mappedBy="medida", cascade=CascadeType.ALL)
	public List<CostoMedidaAdaptacion> costosMedida;

	@Transient 
	private HashMap<Long,Double[]> costosPorPeriodo;
		
	@ManyToMany(cascade=CascadeType.PERSIST)
	public List <AmenazaClimatica> amenazasClimaticas;
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	public List <AmenazaProductiva> amenazasProductivas;
	
	@OneToMany(mappedBy="medida", cascade=CascadeType.ALL)
	public List <ServicioEcosistemico> serviciosEco;
	
		
	@OneToMany(mappedBy="medida", cascade=CascadeType.ALL)
	public List <ReduccionCosto> reducciones;
	
	@OneToMany(mappedBy="medida", cascade=CascadeType.ALL)
	public List <MejoraRendimiento> mejoras;
	
	@OneToMany(mappedBy="medidaimplementada", cascade=CascadeType.ALL)
	public List <MedidaAdaptacionImplementada> medidasImplementadas;
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	public List <ClasificacionCultivo> cultivosAfectados;
	

	public MedidaAdaptacion(String nombre) {
		this.nombre = nombre;
	
		this.costosMedida = new ArrayList<CostoMedidaAdaptacion>();
		this.amenazasClimaticas = new ArrayList<AmenazaClimatica>();
		this.amenazasProductivas = new ArrayList<AmenazaProductiva>();
		this.serviciosEco = new ArrayList<ServicioEcosistemico>();
		this.reducciones= new ArrayList<ReduccionCosto>();
		this.cultivosAfectados = new ArrayList<ClasificacionCultivo>();
		this.mejoras = new ArrayList<MejoraRendimiento>();
	}

	public double getCostoPorPeriodo(int periodo, Long idPais) {
		if (costosPorPeriodo == null) {
			costosPorPeriodo = new HashMap<Long,Double[]>();
			for (CostoMedidaAdaptacion cost : costosMedida) {
				costosPorPeriodo.put(cost.pais.id, 
					new Double[] {cost.costosPeriodo0, cost.costosPeriodo1, cost.costosPeriodo2});	
			}	
		}

		if (costosPorPeriodo.containsKey(idPais)) {
			return costosPorPeriodo.get(idPais)[periodo].doubleValue();
		} else {
			return -1.0;
		}
	}

	public MedidaAdaptacion agregarCostoPorPeriodo(Long idPais, double... costos){
		CostoMedidaAdaptacion nuevoCosto = new CostoMedidaAdaptacion(idPais, costos);
		this.costosMedida.add(nuevoCosto);
		this.save();
		return this;
	}

	public MedidaAdaptacion agregarReduccionCosto(double valorreduccion, int inputReduccion){
		ReduccionCosto reduccion = new ReduccionCosto(valorreduccion, inputReduccion);
		this.reducciones.add(reduccion);
		this.save();
		return this;
		
	}
	
	public MedidaAdaptacion agregarMejoraRendimiento(double valorMejora){
		MejoraRendimiento mejora = new MejoraRendimiento(valorMejora);
		this.mejoras.add(mejora);
		this.save();
		return this;
		
	}
	
	public ReduccionCosto getReduccionByCultivo(ClasificacionCultivo c){
		for(ReduccionCosto r:this.reducciones){
			if(r.clasificacionCultivo.id==c.id){
				return(r);
			}
		}
		return null;
	}
	@Override
	public String toString() {
	    return this.nombre;
	}
		
	
}
