package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class CultivoImplementado extends Model{
	
	
	public double rendimiento0;
	
	public double rendimiento1;
	
	public double rendimiento2;
	
	
	public double precioVentaPromedio0;
	
	public double precioVentaPromedio1;
	
	public double precioVentaPromedio2;
	
	/*
	 * En lugar de tener información agregada sobre los costos de producción,
	 * se detalla por actividades los costos de mano de obra, de maquinaria y de insumos.
	 * Adicionalmente se provee información sobre costos de transporte en la 
	 * actividad de COMERCIALIZACIÓN. 
	 */
	
	//PREPARACION DEL TERRENO
	
	@Required
	public double valorJornalesPreparacion;
	
	@Required
	public double valorMaquinariaPreparacion;
	
	@Required
	public double valorInsumosPreparacion;
	
	//SIEMBRA
	
	@Required
	public double valorJornalesSiembra;
	
	@Required
	public double valorMaquinariaSiembra;
	
	@Required
	public double valorInsumosSiembra;
	
	//MANTENIMIENTO
	
	public double valorJornalesMantenimiento;
	
	public double valorMaquinariaMantenimiento;
	
	public double valorInsumosMantenimiento;
	
	//FERTILIZACION
	
	@Required
	public double valorJornalesFertilizacion;
	
	@Required
	public double valorMaquinariaFertilizacion;
	
	@Required
	public double valorInsumosFertilizacion;
	
	
	//CONTROL DE MALEZA, PLAGAS Y ENFERMEDADES
	
	@Required
	public double valorJornalesControl;
	
	@Required
	public double valorMaquinariaControl;
	
	@Required
	public double valorInsumosControl;	
	
	
	//COSECHA
	
	@Required
	public double valorJornalesCosecha;
	
	@Required
	public double valorMaquinariaCosecha;
	
	@Required
	public double valorInsumosCosecha;
	
	
	//COMERCIALIZACION
	
	@Required
	public double valorTransporteComercializacion;
	
	@Required
	public double valorInsumosComercializacion;
	
	//COSTOS POR PERIODO
	
	public double costosPeriodo0;
	
	public double costosPeriodo1;

	public double costosPeriodo2;

	@ManyToOne
	@Required
	public Cultivo cultivo;
	
	@ManyToOne
	@Required
	public Agricultor productor;

	
	
	public CultivoImplementado(double rendimiento0, double precioVentaPromedio0, 
			double rendimiento1, double precioVentaPromedio1,
			double rendimiento2, double precioVentaPromedio2,
			double valorJornalesPreparacion, double valorMaquinariaPreparacion,
			double valorInsumosPreparacion, double valorJornalesSiembra,
			double valorMaquinariaSiembra, double valorInsumosSiembra,
			double valorJornalesMantenimiento,
			double valorMaquinariaMantenimiento,
			double valorInsumosMantenimiento,
			double valorJornalesFertilizacion,
			double valorMaquinariaFertilizacion,
			double valorInsumosFertilizacion, double valorJornalesControl,
			double valorMaquinariaControl, double valorInsumosControl,
			double valorJornalesCosecha, double valorMaquinariaCosecha,
			double valorInsumosCosecha, double valorTransporteComercializacion,
			double valorInsumosComercializacion, Cultivo cultivo,
			Agricultor productor) {
		this.rendimiento0 = rendimiento0;
		this.precioVentaPromedio0 = precioVentaPromedio0;
		this.rendimiento1 = rendimiento1;
		this.precioVentaPromedio1 = precioVentaPromedio1;
		this.rendimiento2 = rendimiento2;
		this.precioVentaPromedio2 = precioVentaPromedio2;		
			this.valorJornalesPreparacion = valorJornalesPreparacion;
			this.valorMaquinariaPreparacion = valorMaquinariaPreparacion;
			this.valorInsumosPreparacion = valorInsumosPreparacion;
			this.valorJornalesSiembra = valorJornalesSiembra;
			this.valorMaquinariaSiembra = valorMaquinariaSiembra;
			this.valorInsumosSiembra = valorInsumosSiembra;
			this.valorJornalesMantenimiento = valorJornalesMantenimiento;
			this.valorMaquinariaMantenimiento = valorMaquinariaMantenimiento;
			this.valorInsumosMantenimiento = valorInsumosMantenimiento;
			this.valorJornalesFertilizacion = valorJornalesFertilizacion;
			this.valorMaquinariaFertilizacion = valorMaquinariaFertilizacion;
			this.valorInsumosFertilizacion = valorInsumosFertilizacion;
			this.valorJornalesControl = valorJornalesControl;
			this.valorMaquinariaControl = valorMaquinariaControl;
			this.valorInsumosControl = valorInsumosControl;
			this.valorJornalesCosecha = valorJornalesCosecha;
			this.valorMaquinariaCosecha = valorMaquinariaCosecha;
			this.valorInsumosCosecha = valorInsumosCosecha;
		this.valorTransporteComercializacion = valorTransporteComercializacion;
		this.valorInsumosComercializacion = valorInsumosComercializacion;
		this.cultivo = cultivo;
		this.productor = productor;
		
		
		/*
		 * RECORDAR QUE EN CASO DE TRATARSE DE UN CULTIVO DE CICLO CORTO
		 * NO ES NECESARIO INCURRIR NE COSTOS DE MANTENIMIENTO
		 */
		if(cultivo.cicloAnual){
			this.costosPeriodo0=valorJornalesPreparacion+valorMaquinariaPreparacion+valorInsumosPreparacion+
					valorJornalesSiembra+valorMaquinariaSiembra+valorInsumosSiembra+
					valorJornalesMantenimiento+valorMaquinariaMantenimiento+valorInsumosMantenimiento+
					valorJornalesFertilizacion+valorMaquinariaFertilizacion+valorInsumosFertilizacion+
					valorJornalesControl+valorMaquinariaControl+valorInsumosControl+
					valorJornalesCosecha+valorMaquinariaCosecha+valorInsumosCosecha+
					valorTransporteComercializacion+valorInsumosComercializacion;	
					
			this.costosPeriodo1=this.costosPeriodo0*0.5;
			this.costosPeriodo2=this.costosPeriodo0*0.5;
		}else{
			this.costosPeriodo0=valorJornalesPreparacion+valorMaquinariaPreparacion+valorInsumosPreparacion+
					valorJornalesSiembra+valorMaquinariaSiembra+valorInsumosSiembra+					
					valorJornalesFertilizacion+valorMaquinariaFertilizacion+valorInsumosFertilizacion+
					valorJornalesControl+valorMaquinariaControl+valorInsumosControl+
					valorJornalesCosecha+valorMaquinariaCosecha+valorInsumosCosecha+
					valorTransporteComercializacion+valorInsumosComercializacion;
			this.costosPeriodo1=this.costosPeriodo0;
			this.costosPeriodo2=this.costosPeriodo0;
		}
	}

	public double getValorDeducidoByReducciones(List <ReduccionCosto> reducciones){
		double totalReduccion=0;
		for(ReduccionCosto r: reducciones ){
			if(r.clasificacionCultivo.id==this.cultivo.clasificacion.id){
				switch(r.inputReduccion){
					case 1:{
						totalReduccion+=this.valorJornalesPreparacion*r.reduccion;
						break;
					}
					case 2:{
						totalReduccion+=this.valorMaquinariaPreparacion*r.reduccion;
						break;
					}
					case 3:{
						totalReduccion+=this.valorInsumosPreparacion*r.reduccion;
						break;
					}
					case 4:{
						totalReduccion+=this.valorJornalesSiembra*r.reduccion;
						break;
					}
					case 5:{
						totalReduccion+=this.valorMaquinariaSiembra*r.reduccion;
						break;
					}
					case 6:{
						totalReduccion+=this.valorInsumosSiembra*r.reduccion;
						break;
					}
					case 7:{
						totalReduccion+=this.valorJornalesMantenimiento*r.reduccion;
						break;
					}
					case 8:{
						totalReduccion+=this.valorMaquinariaMantenimiento*r.reduccion;
						break;
					}
					case 9:{
						totalReduccion+=this.valorInsumosMantenimiento*r.reduccion;
						break;
					}
					case 10:{
						totalReduccion+=this.valorJornalesFertilizacion*r.reduccion;
						break;
					}
					case 11:{
						totalReduccion+=this.valorMaquinariaFertilizacion*r.reduccion;
						break;
					}
					case 12:{
						totalReduccion+=this.valorInsumosFertilizacion*r.reduccion;
						break;
					}
					case 13:{
						totalReduccion+=this.valorJornalesControl*r.reduccion;
						break;
					}
					case 14:{
						totalReduccion+=this.valorMaquinariaControl*r.reduccion;
						break;
					}
					case 15:{
						totalReduccion+=this.valorInsumosControl*r.reduccion;
						break;
					}
					case 16:{
						totalReduccion+=this.valorJornalesCosecha*r.reduccion;
						break;
					}
					case 17:{
						totalReduccion+=this.valorInsumosCosecha*r.reduccion;
						break;
					}
					case 18:{
						totalReduccion+=this.valorMaquinariaCosecha*r.reduccion;
						break;
					}
					default: break;
				}
			}
			
		}
		return totalReduccion;
	}
	
}
