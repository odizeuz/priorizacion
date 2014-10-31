$( document ).ready(function() {
	// Handler for .ready() called.
	cambiarTitulos();

	$('.div-module-title').click(function(event) {
        var module_id = event.target.id.substr(3);
        $("#t1-" + module_id).toggleClass("collapsed")
		$("#block-" + module_id).toggle();
    });
	
	$('#form_encuesta').submit(function(event){
		
	});
	
	$('input[type=radio][name=selector_adicionales]').change(function() {
        if (this.value == 'Si') {
			$('#ingresos_adicionales').val('');
            $("#contenedor_ingresos_adicionales").show();
        }
        else if (this.value == 'No') {
			$('#ingresos_adicionales').val('0');
            $("#contenedor_ingresos_adicionales").hide();
        }
    });
	

	
	$('#precio_jornal').keyup(function() {
		$('#jornal2').val(this.value);
		$('#jornal3').val(this.value);
		$('#jornal4').val(this.value);
		$('#jornal5').val(this.value);
		$('#jornal6').val(this.value);
		$('#jornal7').val(this.value);
		$('#jornal8').val(this.value);
		$('#jornal9').val(this.value);
		$('#jornal10').val(this.value);
		$('#jornal11').val(this.value);
		$('#jornal12').val(this.value);
	});
	
	$('#precio_jornal').blur(function() {
		$('#jornal2').val(this.value);
		$('#jornal3').val(this.value);
		$('#jornal4').val(this.value);
		$('#jornal5').val(this.value);
		$('#jornal6').val(this.value);
		$('#jornal7').val(this.value);
		$('#jornal8').val(this.value);
		$('#jornal9').val(this.value);
		$('#jornal10').val(this.value);
		$('#jornal11').val(this.value);
		$('#jornal12').val(this.value);
	});
	
	
});

function definirTipoCultivo(){
	var opcion = $( "#tipo_cultivo" ).val();
	
	if(opcion==1){	
		$("#contenedor_cultivo_anual").show();
		$("#contenedor_cultivos_semestrales").hide();
		$("#contenedor_mantenimiento").show();
		$(".segundacolumna").hide();
		cambiarTitulos();
	}else{
		$(".segundacolumna").show();
		$("#contenedor_cultivo_anual").hide();
		$("#contenedor_cultivos_semestrales").show();
		$("#contenedor_mantenimiento").hide();
		cambiarTitulos();
	}
}

function cambiarTitulos(){
	var opcion = $( "#tipo_cultivo" ).val();
	if(opcion==1){	
		var cultivoTitulo1 = $("#nombre_cultivo_anual option:selected").text();
		
		$("#titulo_columna1").text(cultivoTitulo1);
		//$("#titulo_columna1").text(cultivoTitulo1+" - A\xf1o 0");
		//$("#titulo_columna2").text(cultivoTitulo1+" - A\xf1o 1");		
	}else{
			
		var cultivoTitulo1 = $("#nombre_cultivo_semestre1 option:selected").text();
		var cultivoTitulo2 = $("#nombre_cultivo_semestre2 option:selected").text();
		$("#titulo_columna1").text(cultivoTitulo1);
		$("#titulo_columna2").text(cultivoTitulo2);
	}
}

function validarEncuesta(){
	var opcion = $( "#tipo_cultivo" ).val();
	
	if(opcion==1){
		
	}else{
		
	}
}