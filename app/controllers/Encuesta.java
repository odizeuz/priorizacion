package controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import models.Agricultor;
import models.AmenazaClimatica;
import models.AmenazaProductiva;
import models.Cultivo;
import models.MedidaAdaptacionImplementada;

import play.mvc.Controller;
import play.mvc.Scope.Params;

public class Encuesta extends Controller {

	public static void index(Long idEncuesta, Long idPais) {

		if (idEncuesta != null && idEncuesta == -1) {
			session.clear();
			flash.discard();
		}

    	List<models.Cultivo> cultivosanuales = models.Cultivo.find("byCicloAnual", true).fetch();
    	List<models.Cultivo> cultivosciclocorto = models.Cultivo.find("byCicloAnual", false).fetch();
    	List<models.Pais> paises = models.Pais.findAll();

    	if (idPais == null) {
    		idPais = 1L;
    	}

    	models.Pais paisSeleccionado = models.Pais.findById(idPais);
    	
        render(idEncuesta, paises, paisSeleccionado, cultivosanuales, cultivosciclocorto);
    }
	
	public static void calcular(){

		models.Encuesta nueva = null;

		if (params.get("idEncuesta") != null && !params.get("idEncuesta").equals("")) {
			nueva = (models.Encuesta) models.Encuesta.findById(params.get("id"));
		}

		Long idPais = params.get("idPais", Long.class);

		System.out.println("Ident " + idPais);

		int dni = params.get("dni", int.class);
		String nombre = params.get("nombre");
		int tipoCultivo = params.get("tipo_cultivo", int.class);
		double ingresosAdicionales = params.get("ingresos_adicionales", double.class);
		Long idCultivoAnual = params.get("nombre_cultivo_anual", Long.class);
		double rendimiento0 = ParseDouble(params.get("rendimiento0"));
		double precioVenta0 = ParseDouble(params.get("precio_venta0"));
		double rendimiento1 = ParseDouble(params.get("rendimiento1"));
		double precioVenta1 = ParseDouble(params.get("precio_venta1"));
		double rendimiento2 = ParseDouble(params.get("rendimiento2"));
		double precioVenta2 = ParseDouble(params.get("precio_venta2"));	
		
		
		Long idCultivoSemestre1 = params.get("nombre_cultivo_semestre1", Long.class);
		double rendimientoSemestre1 = ParseDouble(params.get("rendimiento_cultivo_semestre1"));
		double precioVentaSemestre1 = ParseDouble(params.get("precio_venta_cultivo_semestre1"));
		
		Long idCultivoSemestre2 = params.get("nombre_cultivo_semestre2", Long.class);
		double rendimientoSemestre2 = ParseDouble(params.get("rendimiento_cultivo_semestre2"));
		double precioVentaSemestre2 = ParseDouble(params.get("precio_venta_cultivo_semestre2"));
		
		double a1 = ParseDouble(params.get("a1"));
		double a2 = ParseDouble(params.get("a2"));
		double a3 = ParseDouble(params.get("a3"));
		double a4 = ParseDouble(params.get("a4"));
		double a5 = ParseDouble(params.get("a5"));
		double a6 = ParseDouble(params.get("a6"));
		double a7 = ParseDouble(params.get("a7"));
		double a8 = ParseDouble(params.get("a8"));
		double a9 = ParseDouble(params.get("a9"));
		double a10 = ParseDouble(params.get("a10"));
		double a11 = ParseDouble(params.get("a11"));
		double a12 = ParseDouble(params.get("a12"));
		double a13 = ParseDouble(params.get("a13"));
		double a14 = ParseDouble(params.get("a14"));
		double a15 = ParseDouble(params.get("a15"));
		double a16 = ParseDouble(params.get("a16"));
		double a17 = ParseDouble(params.get("a17"));
		double a18 = ParseDouble(params.get("a18"));
		double a19 = ParseDouble(params.get("a19"));
		double a20 = ParseDouble(params.get("a20"));
		double a21 = ParseDouble(params.get("a21"));
		double a22 = ParseDouble(params.get("a22"));
		double a23 = ParseDouble(params.get("a23"));
		double a24 = ParseDouble(params.get("a24"));
		double a25 = ParseDouble(params.get("a25"));
		double a26 = ParseDouble(params.get("a26"));
		double a27 = ParseDouble(params.get("a27"));
		double a28 = ParseDouble(params.get("a28"));
		double a29 = ParseDouble(params.get("a29"));
		double a30 = ParseDouble(params.get("a30"));
		double a31 = ParseDouble(params.get("a31"));
		double a32 = ParseDouble(params.get("a32"));
		double a33 = ParseDouble(params.get("a33"));
		
		double b1 = ParseDouble(params.get("b1"));
		double b2 = ParseDouble(params.get("b2"));
		double b3 = ParseDouble(params.get("b3"));
		double b4 = ParseDouble(params.get("b4"));
		double b5 = ParseDouble(params.get("b5"));
		double b6 = ParseDouble(params.get("b6"));
		double b7 = ParseDouble(params.get("b7"));
		double b8 = ParseDouble(params.get("b8"));
		double b9 = ParseDouble(params.get("b9"));
		double b10 = ParseDouble(params.get("b10"));
		double b11 = ParseDouble(params.get("b11"));
		double b12 = ParseDouble(params.get("b12"));
		double b13 = ParseDouble(params.get("b13"));
		double b14 = ParseDouble(params.get("b14"));
		double b15 = ParseDouble(params.get("b15"));
		double b16 = ParseDouble(params.get("b16"));
		double b17 = ParseDouble(params.get("b17"));
		double b18 = ParseDouble(params.get("b18"));
		double b19 = ParseDouble(params.get("b19"));
		double b20 = ParseDouble(params.get("b20"));
		double b21 = ParseDouble(params.get("b21"));
		double b22 = ParseDouble(params.get("b22"));
		double b23 = ParseDouble(params.get("b23"));
		double b24 = ParseDouble(params.get("b24"));
		double b25 = ParseDouble(params.get("b25"));
		double b26 = ParseDouble(params.get("b26"));
		double b27 = ParseDouble(params.get("b27"));
		double b28 = ParseDouble(params.get("b28"));
		double b29 = ParseDouble(params.get("b29"));
		double b30 = ParseDouble(params.get("b30"));
		double b31 = ParseDouble(params.get("b31"));
		double b32 = ParseDouble(params.get("b32"));
		double b33 = ParseDouble(params.get("b33"));
		
		
		double tasa = (params.get("tasa", double.class))/100;
		Agricultor agri;
		if (Agricultor.find("byDni", dni).fetch().isEmpty()){
			agri = new Agricultor(dni,nombre,ingresosAdicionales);
		}else{
			agri = (Agricultor) Agricultor.find("byDni", dni).fetch().get(0);
			agri.limpiarCultivos();
		}
		
		double valorJornalesPreparacion;
		double valorMaquinariaPreparacion;
		double valorInsumosPreparacion;
		double valorJornalesSiembra;
		double valorMaquinariaSiembra;
		double valorInsumosSiembra;
		double valorJornalesMantenimiento;
		double valorMaquinariaMantenimiento;
		double valorInsumosMantenimiento;
		double valorJornalesFertilizacion;
		double valorMaquinariaFertilizacion;
		double valorInsumosFertilizacion;
		double valorJornalesControl;
		double valorMaquinariaControl;
		double valorInsumosControl;
		double valorJornalesCosecha;
		double valorMaquinariaCosecha;
		double valorInsumosCosecha;
		double valorTransporteComercializacion;
		double valorInsumosComercializacion;
		
		valorJornalesPreparacion = a1*a2;
		valorMaquinariaPreparacion =  a3*a4;
		valorInsumosPreparacion = a5;
		valorJornalesSiembra = a6*a7;
		valorMaquinariaSiembra = a8*a9;
		valorInsumosSiembra= a10;
		valorJornalesMantenimiento = a11*a12;
		valorMaquinariaMantenimiento =a13*a14;
		valorInsumosMantenimiento = a15;
		valorJornalesFertilizacion = a16*a17;
		valorMaquinariaFertilizacion = a18*a19;
		valorInsumosFertilizacion = a20;
		valorJornalesControl = a21*a22;
		valorMaquinariaControl = a23*a24;
		valorInsumosControl = a25;
		valorJornalesCosecha = a26*a27;
		valorMaquinariaCosecha= a28*a29;
		valorInsumosCosecha= a30;
		valorTransporteComercializacion=a31*a32;
		valorInsumosComercializacion=a33;
		
		//El codigo de arriba aplica tanto para los de ciclo corto como largo, porque igual ambos tienen que agregar un "primer" cultivo
		
		if(tipoCultivo==1){
			agri.agregarCultivo((Cultivo)Cultivo.findById(idCultivoAnual), rendimiento0, precioVenta0, rendimiento1, precioVenta1, rendimiento2, precioVenta2, valorJornalesPreparacion, valorMaquinariaPreparacion, valorInsumosPreparacion, valorJornalesSiembra, valorMaquinariaSiembra, valorInsumosSiembra, valorJornalesMantenimiento, valorMaquinariaMantenimiento, valorInsumosMantenimiento, valorJornalesFertilizacion, valorMaquinariaFertilizacion, valorInsumosFertilizacion, valorJornalesControl, valorMaquinariaControl, valorInsumosControl, valorJornalesCosecha, valorMaquinariaCosecha, valorInsumosCosecha, valorTransporteComercializacion, valorInsumosComercializacion);
		}else{
			System.out.println("ENTRA AL AGREGAR CULTIVO CON CEROS");
			agri.agregarCultivo((Cultivo)Cultivo.findById(idCultivoSemestre1), rendimientoSemestre1, precioVentaSemestre1, rendimientoSemestre1, precioVentaSemestre1, rendimientoSemestre1, precioVentaSemestre1, valorJornalesPreparacion, valorMaquinariaPreparacion, valorInsumosPreparacion, valorJornalesSiembra, valorMaquinariaSiembra, valorInsumosSiembra, valorJornalesMantenimiento, valorMaquinariaMantenimiento, valorInsumosMantenimiento, valorJornalesFertilizacion, valorMaquinariaFertilizacion, valorInsumosFertilizacion, valorJornalesControl, valorMaquinariaControl, valorInsumosControl, valorJornalesCosecha, valorMaquinariaCosecha, valorInsumosCosecha, valorTransporteComercializacion, valorInsumosComercializacion);
			valorJornalesPreparacion = b1*b2;
			valorMaquinariaPreparacion =  b3*b4;
			valorInsumosPreparacion = b5;
			valorJornalesSiembra = b6*b7;
			valorMaquinariaSiembra = b8*b9;
			valorInsumosSiembra= b10;
			valorJornalesMantenimiento = b11*b12;
			valorMaquinariaMantenimiento = b13*b14;
			valorInsumosMantenimiento = b15;
			valorJornalesFertilizacion = b16*b17;
			valorMaquinariaFertilizacion = b18*b19;
			valorInsumosFertilizacion = b20;
			valorJornalesControl = b21*b22;
			valorMaquinariaControl = b23*b24;
			valorInsumosControl = b25;
			valorJornalesCosecha = b26*b27;
			valorMaquinariaCosecha= b28*b29;
			valorInsumosCosecha= b30;
			valorTransporteComercializacion= b31*b32;
			valorInsumosComercializacion= b33;
			
			agri.agregarCultivo((Cultivo)Cultivo.findById(idCultivoSemestre2), rendimientoSemestre2, precioVentaSemestre2, 0, 0, 0, 0,valorJornalesPreparacion, valorMaquinariaPreparacion, valorInsumosPreparacion, valorJornalesSiembra, valorMaquinariaSiembra, valorInsumosSiembra, valorJornalesMantenimiento, valorMaquinariaMantenimiento, valorInsumosMantenimiento, valorJornalesFertilizacion, valorMaquinariaFertilizacion, valorInsumosFertilizacion, valorJornalesControl, valorMaquinariaControl, valorInsumosControl, valorJornalesCosecha, valorMaquinariaCosecha, valorInsumosCosecha, valorTransporteComercializacion, valorInsumosComercializacion);
			
		}
		
		List<AmenazaClimatica> productorClimaticas = new ArrayList<AmenazaClimatica>();
		List<AmenazaProductiva> productorProductivas = new ArrayList<AmenazaProductiva>();
		
		if(params.get("amenazaclimatica-1")!=null){
			productorClimaticas.add((AmenazaClimatica) AmenazaClimatica.findById(Long.valueOf(1)));
		}
		if(params.get("amenazaclimatica-2")!=null){
			productorClimaticas.add((AmenazaClimatica) AmenazaClimatica.findById(Long.valueOf(2)));
		}
		if(params.get("amenazaclimatica-3")!=null){
			productorClimaticas.add((AmenazaClimatica) AmenazaClimatica.findById(Long.valueOf(3)));
		}
		if(params.get("amenazaclimatica-4")!=null){
			productorClimaticas.add((AmenazaClimatica) AmenazaClimatica.findById(Long.valueOf(4)));
		}
		if(params.get("amenazaclimatica-5")!=null){
			productorClimaticas.add((AmenazaClimatica) AmenazaClimatica.findById(Long.valueOf(5)));
		}
		if(params.get("amenazaclimatica-6")!=null){
			productorClimaticas.add((AmenazaClimatica) AmenazaClimatica.findById(Long.valueOf(6)));
		}
		if(params.get("amenazaclimatica-7")!=null){
			productorClimaticas.add((AmenazaClimatica) AmenazaClimatica.findById(Long.valueOf(7)));
		}
		if(params.get("amenazaclimatica-8")!=null){
			productorClimaticas.add((AmenazaClimatica) AmenazaClimatica.findById(Long.valueOf(8)));
		}
		if(params.get("amenazaclimatica-9")!=null){
			productorClimaticas.add((AmenazaClimatica) AmenazaClimatica.findById(Long.valueOf(9)));
		}
		
		
		if(params.get("amenazaproductiva-1")!=null){
			productorProductivas.add((AmenazaProductiva) AmenazaProductiva.findById(Long.valueOf(1)));
		}
		if(params.get("amenazaproductiva-2")!=null){
			productorProductivas.add((AmenazaProductiva) AmenazaProductiva.findById(Long.valueOf(2)));
		}
		if(params.get("amenazaproductiva-3")!=null){
			productorProductivas.add((AmenazaProductiva) AmenazaProductiva.findById(Long.valueOf(3)));
		}
		if(params.get("amenazaproductiva-4")!=null){
			productorProductivas.add((AmenazaProductiva) AmenazaProductiva.findById(Long.valueOf(4)));
		}
		if(params.get("amenazaproductiva-5")!=null){
			productorProductivas.add((AmenazaProductiva) AmenazaProductiva.findById(Long.valueOf(5)));
		}
		if(params.get("amenazaproductiva-6")!=null){
			productorProductivas.add((AmenazaProductiva) AmenazaProductiva.findById(Long.valueOf(6)));
		}
		if(params.get("amenazaproductiva-7")!=null){
			productorProductivas.add((AmenazaProductiva) AmenazaProductiva.findById(Long.valueOf(7)));
		}
		if(params.get("amenazaproductiva-8")!=null){
			productorProductivas.add((AmenazaProductiva) AmenazaProductiva.findById(Long.valueOf(8)));
		}
		if(params.get("amenazaproductiva-9")!=null){
			productorProductivas.add((AmenazaProductiva) AmenazaProductiva.findById(Long.valueOf(9)));
		}
		if(params.get("amenazaproductiva-10")!=null){
			productorProductivas.add((AmenazaProductiva) AmenazaProductiva.findById(Long.valueOf(10)));
		}
		
		if (nueva == null) {
			nueva = new models.Encuesta(tasa,50.0,25.0,25.0,0.0,agri,idPais).save();
		}
		 
		nueva.calcularCandidatas();
		nueva.calcularACB();
		nueva.calcularAmenazas(productorClimaticas,productorProductivas);
		nueva.calcularServiciosEcosistemicos();
		nueva.calcularfinal();
		nueva.calculoCapacidadAdaptativa = capacidadaadaptativa(params);		
		nueva.save();

		session.put("encuesta", nueva.id);
		
		priorizacion(nueva.id);
	}
	
	public static void priorizacion(Long idEncuesta){
		models.Encuesta en;
		if (idEncuesta == null) {
			en = (models.Encuesta) models.Encuesta.find("order by id DESC").fetch(1).get(0);
		} else {
			en = (models.Encuesta)models.Encuesta.findById(idEncuesta);
		}

		Agricultor prod = en.productor;
		double valorCA =en.calculoCapacidadAdaptativa;

		List<MedidaAdaptacionImplementada> porPuntajeTotal = models.MedidaAdaptacionImplementada.find("encuesta_id is "+en.id+" order by calificacionfinal DESC").fetch();

		/*List<MedidaAdaptacionImplementada> porACB = models.MedidaAdaptacionImplementada.find("encuesta_id is "+en.id+" order by acb DESC").fetch();
		List<MedidaAdaptacionImplementada> porAmenazas = models.MedidaAdaptacionImplementada.find("encuesta_id is "+en.id+" order by calificacionAmenazas DESC").fetch();
		List<MedidaAdaptacionImplementada> porServiciosEco = models.MedidaAdaptacionImplementada.find("encuesta_id is "+en.id+" order by calificacionServiciosEcosistemicos DESC").fetch();*/

		//render(prod,porACB,porAmenazas,porServiciosEco,idEncuesta,valorCA);
		render(idEncuesta, prod,porPuntajeTotal, valorCA);
	}
	
	public static void resultados(Long idEncuesta){

		models.Encuesta en;
		if (idEncuesta == null) {
			en = (models.Encuesta) models.Encuesta.find("order by id DESC").fetch(1).get(0);
		} else {
			en = (models.Encuesta)models.Encuesta.findById(idEncuesta);
		}

		List<MedidaAdaptacionImplementada> porPuntajeTotal = models.MedidaAdaptacionImplementada.find("encuesta_id is "+idEncuesta+" order by calificacionfinal DESC").fetch();
		double valorCA =en.calculoCapacidadAdaptativa;
		Agricultor prod = en.productor;
		
		render(idEncuesta, prod,porPuntajeTotal, valorCA);
	}
		
	static double capacidadaadaptativa(Params params){
		
		if (!"true".equals(params.get("incluir_adaptativa"))) {
			return -1.0;
		}

		double puntajeTotal = 0.0;

		puntajeTotal += ParseDouble(params.get("grupoedad"));
		puntajeTotal += ParseDouble(params.get("niveleducativo"));
		puntajeTotal += ParseDouble(params.get("tipovivienda"));
		puntajeTotal += ParseDouble(params.get("antiguedad"));
		puntajeTotal += ParseDouble(params.get("dedicacionagricultura"));
		puntajeTotal += ParseDouble(params.get("estadovivienda"));		
		puntajeTotal += ParseDouble(params.get("personasacargo"));
		puntajeTotal += ParseDouble(params.get("estadovivienda"));
		
		if((params.get("preocupacion-8")!=null)||(params.get("preocupacion-9")!=null)||(params.get("preocupacion-10")!=null)){
			//CHECKED
			puntajeTotal += 5;
		}
		
		puntajeTotal -= ParseDouble(params.get("barreras-1"));
		puntajeTotal -= ParseDouble(params.get("barreras-2"));
		puntajeTotal -= ParseDouble(params.get("barreras-3"));
		puntajeTotal -= ParseDouble(params.get("barreras-4"));
		puntajeTotal -= ParseDouble(params.get("barreras-5"));
		puntajeTotal -= ParseDouble(params.get("barreras-6"));
		puntajeTotal -= ParseDouble(params.get("barreras-9"));
		puntajeTotal -= ParseDouble(params.get("barreras-10"));
		puntajeTotal -= ParseDouble(params.get("barreras-11"));
		puntajeTotal -= ParseDouble(params.get("barreras-12"));
				
		if((params.get("incentivos-1")!=null)||(params.get("preocupacion-4")!=null)||(params.get("preocupacion-7")!=null)||(params.get("preocupacion-8")!=null)||(params.get("preocupacion-9")!=null)){
			//CHECKED
			puntajeTotal += 5;
		}
		
		int importanciaNatural = params.get("capitalnatural", int.class);
		if((importanciaNatural==1)||(importanciaNatural==2)){
			puntajeTotal += 5;
		}
		double puntajeCapitalNatural = 0;
		puntajeCapitalNatural += ParseDouble(params.get("natural-1"));
		puntajeCapitalNatural += ParseDouble(params.get("natural-2"));
		puntajeCapitalNatural += ParseDouble(params.get("natural-3"));
		puntajeCapitalNatural += ParseDouble(params.get("natural-4"));
		puntajeCapitalNatural += ParseDouble(params.get("natural-5"));
		puntajeCapitalNatural += ParseDouble(params.get("natural-6"));
		
		puntajeCapitalNatural = round(((puntajeCapitalNatural/6)*5.0), 0);
		puntajeTotal+=puntajeCapitalNatural;
		
		if((params.get("humano-1")!=null)||(params.get("humano-2")!=null)||(params.get("humano-3")!=null)||(params.get("humano-5")!=null)){
			//CHECKED
			puntajeTotal += 5;
		}else{
			puntajeTotal += ParseDouble(params.get("humano-4"));
		}
		
		if((params.get("fisico-2")!=null)||(params.get("fisico-5")!=null)||(params.get("fisico-7")!=null)){
			//CHECKED
			puntajeTotal += 5;
		}else{
			double puntajeCapitalFisico = 0;
			puntajeCapitalFisico += ParseDouble(params.get("fisico-1"));
			puntajeCapitalFisico += ParseDouble(params.get("fisico-3"));
			puntajeCapitalFisico += ParseDouble(params.get("fisico-4"));
			puntajeCapitalFisico += ParseDouble(params.get("fisico-6"));
			
			puntajeCapitalFisico = round(((puntajeCapitalFisico/7)*5.0), 0);
			puntajeTotal+=puntajeCapitalNatural;
		}
		
		if((params.get("financiero-3")!=null)&&(params.get("financiero-10")!=null)){
			//CHECKED
			puntajeTotal += 5;
		}
		
		if((params.get("social-1")!=null)||(params.get("social-6")!=null)||(params.get("social-7")!=null)||(params.get("social-9")!=null)||(params.get("social-10")!=null)){
			//CHECKED
			puntajeTotal += 5;
		}

		return puntajeTotal;

		/*models.Encuesta en = models.Encuesta.findById(idEncuesta);
		en.calculoCapacidadAdaptativa = puntajeTotal;
		en.save();
		priorizacion(idEncuesta);*/
		
	}
	
	public static void recalcularACB(Long idEncuesta){
		
		models.Encuesta en = models.Encuesta.findById(idEncuesta);
		System.out.println(en.productor.cultivos.get(0).costosPeriodo1);
		en.calcularACB();
		en.save();
		priorizacion(idEncuesta);
	}
	

	
	static double ParseDouble(String strNumber) {
		   if (strNumber != null && strNumber.length() > 0) {
		       try {
		          return Double.parseDouble(strNumber);
		       } catch(Exception e) {
		          return 0;   // or some value to mark this field is wrong. or make a function validates field first ...
		       }
		   }
		   else return 0;
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
