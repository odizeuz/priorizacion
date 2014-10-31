package models;

import java.util.ArrayList;
import java.util.List;
import java.math.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Encuesta extends Model {
	
	@Required
	public double tasaInteres;	
	
	/*
	 * POR DEFECTO LAS PONDERACIONES TIENEN LOS VALORES DEFINIDOS EN EL CONSTRUCTOR
	 * PERO SE PONEN COMO PARÁMETROS PARA QUE PUEDAN SER EDITADOS PARA CUALQUIER
	 * CASO PARTICULAR
	 */
	
	@Required
	public double ponderacionACB;
	
	@Required
	public double ponderacionAmenazas;
	
	@Required
	public double ponderacionServiciosEcosistemicos;
	
	@Required
	public double calculoCapacidadAdaptativa;

	@Required
	public Pais paisEncuesta;
	

	/*
	 * POR DEFECTO LAS PONDERACIONES TIENEN LOS VALORES DEFINIDOS EN EL CONSTRUCTOR
	 * PERO SE PONEN COMO PARÁMETROS PARA QUE PUEDAN SER EDITADOS PARA CUALQUIER
	 * CASO PARTICULAR
	 */
	
	@OneToMany(mappedBy="encuesta", cascade=CascadeType.ALL)
	public List <MedidaAdaptacionImplementada> medidasCandidatas;
	
	@OneToOne
	@Required
	public Agricultor productor;

	public Encuesta(double tasaInteres, double ponderacionACB,
			double ponderacionAmenazas, double ponderacionServiciosEcosistemicos,
			double calculoCapacidadAdaptativa, Agricultor productor, Long idPaisEncuesta) {
		this.tasaInteres = tasaInteres;
		this.ponderacionACB = ponderacionACB;
		this.ponderacionAmenazas = ponderacionAmenazas;
		this.ponderacionServiciosEcosistemicos = ponderacionServiciosEcosistemicos;
		this.calculoCapacidadAdaptativa = calculoCapacidadAdaptativa;
		this.productor = productor;
		this.paisEncuesta = models.Pais.findById(idPaisEncuesta);
		
		this.medidasCandidatas = new ArrayList<MedidaAdaptacionImplementada>();
	}
	
	public List<MedidaAdaptacionImplementada> calcularCandidatas(){
		this.medidasCandidatas.clear();
		List<MedidaAdaptacion> todas = MedidaAdaptacion.findAll();
		for(MedidaAdaptacion m:todas){
			for(CultivoImplementado c:this.productor.cultivos){
				loopclasificaciones:
				for(ClasificacionCultivo cc: m.cultivosAfectados){
					if(c.cultivo.clasificacion.id==cc.id){
						MedidaAdaptacionImplementada nueva = new MedidaAdaptacionImplementada(m, this);
						if(this.medidasCandidatas.contains(nueva)){
							System.out.println("Ya existe");
						}else{
							this.medidasCandidatas.add(nueva);
						}						
						break loopclasificaciones;
					}
				}
			}
		}
		return(this.medidasCandidatas);
	}
	
	public List<MedidaAdaptacionImplementada> calcularACB(){
		for(MedidaAdaptacionImplementada m: this.medidasCandidatas){
			double [] costosCultivo = new double[3];
			double [] ingresoCultivo = new double[3];
			double [] productividadPotencial =  new double[3];;
			double [] reduccionCostos=  new double[3];
			
			double costosCultivoTotal=0;
			double costosMedidaTotal=0;
			double ingresoCultivoTotal=0;
			double productividadPotencialTotal=0;
			double reduccionCostosTotal=0;
			
			//SE CONSTRUYE EL PROYECTO EN UN PERIODO QUE VA DEL AÑO 0 AL AÑO 2
			
			for(CultivoImplementado c:this.productor.cultivos){
				//PRIMERA PARTE : COSTOS DE CULTIVO				
				/*
				 * Recordar que los costos del cultivo en el constructor de CultivoImplementado
				 * en el año 0, 1 y 2 ya se han diferenciado tanto para los cultivos de ciclo 
				 * corto como para los de ciclo anual.
				 */
				
				costosCultivo[0] += c.costosPeriodo0;
				
				ingresoCultivo[0] += c.precioVentaPromedio0*c.rendimiento0;
				
				costosCultivo[1] += c.costosPeriodo1;
				ingresoCultivo[1] += c.precioVentaPromedio1*c.rendimiento1;
				
				costosCultivo[2] += c.costosPeriodo2;
				ingresoCultivo[2] += c.precioVentaPromedio2*c.rendimiento2;
				 
				//SEGUNDA PARTE : POTENCIAL MEJORA RENDIMIENTO Y REDUCCIONES DE COSTO				
				buscarRendimiento:
				 for(MejoraRendimiento mejora: m.medidaimplementada.mejoras){
					 if(mejora.clasificacionCultivo.id==c.cultivo.clasificacion.id){
						 productividadPotencial[0] = (mejora.mejora*c.rendimiento0)*c.precioVentaPromedio0;
						 productividadPotencial[1] = (mejora.mejora*c.rendimiento1)*c.precioVentaPromedio1;
						 productividadPotencial[2] = (mejora.mejora*c.rendimiento2)*c.precioVentaPromedio2;
						 break buscarRendimiento;
					 }
				 }
				 reduccionCostos[0]+=c.getValorDeducidoByReducciones(m.medidaimplementada.reducciones);
				 reduccionCostos[1]+=c.getValorDeducidoByReducciones(m.medidaimplementada.reducciones)*(0.5);
				 reduccionCostos[2]+=c.getValorDeducidoByReducciones(m.medidaimplementada.reducciones)*(0.5);
			}
			
			//AGREGAR A TOTALES LOS VALORES DEL AÑO 0
			
			costosCultivoTotal+=costosCultivo[0];
			costosMedidaTotal+=m.medidaimplementada.getCostoPorPeriodo(0, paisEncuesta.id);
			ingresoCultivoTotal+=ingresoCultivo[0];
			productividadPotencialTotal+=productividadPotencial[0];
			reduccionCostosTotal+=reduccionCostos[0];
			
			//AGREGAR A TOTALES LOS VALORES DEL AÑO 1
			
			costosCultivoTotal+=(costosCultivo[1])/(Math.pow(1+this.tasaInteres, 1));
			costosMedidaTotal+=(m.medidaimplementada.getCostoPorPeriodo(1, paisEncuesta.id))/(Math.pow(1+this.tasaInteres, 1));
			ingresoCultivoTotal+=(ingresoCultivo[1])/(Math.pow(1+this.tasaInteres, 1));
			productividadPotencialTotal+=(productividadPotencial[1])/(Math.pow(1+this.tasaInteres, 1));
			reduccionCostosTotal+=(reduccionCostos[1])/(Math.pow(1+this.tasaInteres, 1));
			
			//AGREGAR A TOTALES LOS VALORES DEL AÑO 2
			
			costosCultivoTotal+=(costosCultivo[2])/(Math.pow(1+this.tasaInteres, 2));
			costosMedidaTotal+=(m.medidaimplementada.getCostoPorPeriodo(2, paisEncuesta.id))/(Math.pow(1+this.tasaInteres, 2));
			ingresoCultivoTotal+=(ingresoCultivo[2])/(Math.pow(1+this.tasaInteres, 2));
			productividadPotencialTotal+=(productividadPotencial[2])/(Math.pow(1+this.tasaInteres, 2));
			reduccionCostosTotal+=(reduccionCostos[2])/(Math.pow(1+this.tasaInteres, 2));
			System.out.println("Costos Cultivo: "+costosCultivoTotal);
			System.out.println("Costos Medida: "+costosMedidaTotal);
			System.out.println("Ingreso Cultivo: "+ingresoCultivoTotal);
			System.out.println("Productividad Potencial: "+productividadPotencialTotal);
			System.out.println("Reduccion Costos Total: "+reduccionCostosTotal);
			
			m.acb=round((ingresoCultivoTotal+productividadPotencialTotal+reduccionCostosTotal+(this.productor.otrasFuentesIngreso*36))/(costosCultivoTotal+costosMedidaTotal),2);
			System.out.println("ACB de la medida "+m.medidaimplementada.nombre+" es: "+m.acb);
		
			
		}
		this.save();
		return this.medidasCandidatas;
	}
	
	public List<MedidaAdaptacionImplementada> calcularAmenazas(List<AmenazaClimatica> productorClimaticas, List<AmenazaProductiva> productorProductivas){
		
		for(MedidaAdaptacionImplementada m: this.medidasCandidatas){
			List <AmenazaClimatica> climaticasatendidas = m.medidaimplementada.amenazasClimaticas;
			List <AmenazaProductiva> productivasatendidas = m.medidaimplementada.amenazasProductivas;
			
			double denominador = Double.valueOf((productorClimaticas.size()+productorProductivas.size()));
			double numerador = 0;
			
			//for de climáticas
			for(AmenazaClimatica a: productorClimaticas){
				loopAmenazasMedida:
				for(AmenazaClimatica implementada: climaticasatendidas){
					if(implementada.id==a.id){
						numerador+=1;
						break loopAmenazasMedida;
					}
				}
			}
			
			//for de productivas
			for(AmenazaProductiva a: productorProductivas){
				loopAmenazasMedida:
				for(AmenazaProductiva implementada: productivasatendidas){
					if(implementada.id==a.id){
						numerador+=1;
						break loopAmenazasMedida;
					}
				}
			}
			
			double ponderacion = numerador/denominador;
			
			m.calificacionAmenazas=round(ponderacion,2);
			
		}
		this.save();
		return this.medidasCandidatas;
		
	}
	
	public List<MedidaAdaptacionImplementada> calcularServiciosEcosistemicos(){
		
		for(MedidaAdaptacionImplementada m: this.medidasCandidatas){
			double puntajeEcosistemico=0;
			List <ServicioEcosistemico> servicios= m.medidaimplementada.serviciosEco;
			for(ServicioEcosistemico s: servicios){
				puntajeEcosistemico+=s.puntaje;
			}
			m.calificacionServiciosEcosistemicos=round((puntajeEcosistemico/76),2);
			
		}
		this.save();
		return this.medidasCandidatas;
		
	}
	
	public List<MedidaAdaptacionImplementada> calcularfinal(){
		double mayorACB = 0;
		boolean primeraIteracion = true;
		
		for(MedidaAdaptacionImplementada m: this.medidasCandidatas){
			double valorTotal = 0;
			if(primeraIteracion){
				mayorACB = m.acb;
				primeraIteracion = false;
			}
			valorTotal+= ((m.acb)/mayorACB)*50;
			valorTotal+= (m.calificacionAmenazas*25);
			valorTotal+= (m.calificacionServiciosEcosistemicos*25);
			m.calificacionfinal = round(valorTotal,2);
		}
		this.save();
		return this.medidasCandidatas;
	}
	
	
	static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	    
	    try{
	    	BigDecimal bd = new BigDecimal(value);
		    bd = bd.setScale(places, RoundingMode.HALF_UP);
		    return bd.doubleValue();
	    }catch(Exception e){
	    	return 0;
	    }
	    
	}
	
}
