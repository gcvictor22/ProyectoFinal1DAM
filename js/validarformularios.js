function validarCrearReservar() {
	let nombreClase = document.getElementById("nombreClase").value;
	let precio = document.getElementById("precio").value;
	let horario = document.getElementById("horario").value;
	let plazas = document.getElementById("plazas").vaule;
	let img = document.getElementById("imgClase").value;

	if (nombreClase.length == 0) {
		alert('Debe introducir el nombre de la clase');


	} else if (precio.length == 0 || precio < 0) {
		alert('No puede introducir ' + precio + ' como precio');


	} else if (horario.length == 0) {
		alert('Horario no válido');


	} else if (plazas <= 0) {
		alert('Debe introducir más plazas');


	} else if (img.length == 0) {
		alert('Introduzca una imagen para la foto');


	} else {

		
	}
}


function validarReservar() {
	let nombreReserva = document.getElementById("nombreReserva").value;
	let correoReserva = document.getElementById("correoReserva").value;
	let telefono = document.getElementById("telefono").value;

	if (nombreReserva.length == 0) {
		alert('El nombre introducido no es válido');



	} else if (correoReserva.length == 0) {
		alert('El correo introducido no es válido');



	} else if (telefono.length != 9) {
		alert('El telefono introducido no es válido');



	}
}