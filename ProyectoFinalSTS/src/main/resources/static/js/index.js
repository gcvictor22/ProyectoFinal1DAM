document.getElementById("siguiente").addEventListener("click", ampliar);
document.getElementById("atras").addEventListener("click", reducir);

let width = 0;
let cont = 0;
let comprobacion = /^([\da-z_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
let textoError = document.getElementById("textoError");
textoError.style.visibility = 'hidden';

urlParams = new URLSearchParams(window.location.search);

if (urlParams.get('menu') == 'clases') {
    let desplegable1 = document.getElementById("collapseOne");
    desplegable1.className = 'collapse show'
} else if (urlParams.get('menu') == 'reservas') {
    let desplegable2 = document.getElementById("collapseTwo");
    desplegable2.className = 'collapse show'
}

function ampliar() {

    let barra = document.getElementById("barra");
    barra.style.width = width + '%';

    let div1 = document.getElementById("seccion1");
    let div2 = document.getElementById("seccion2");
    let div3 = document.getElementById("seccion3");

    let nom = document.getElementById("nombre").value;
    let ap1 = document.getElementById("apellido1").value;
    let ap2 = document.getElementById("apellido2").value;
    let pass = document.getElementById("contrasenha1").value;
    let pass2 = document.getElementById("contrasenha2").value;
    let tarj = document.getElementById("numTarjeta").value.charAt(12) + document.getElementById("numTarjeta").value.charAt(13)
        + document.getElementById("numTarjeta").value.charAt(14) + document.getElementById("numTarjeta").value.charAt(15);
    let correo = document.getElementById("correo").value;
    let telefono = document.getElementById("telefono").value;
    let fechaCad = document.getElementById("fechaCaducidad").value;
    let cvv = document.getElementById("cvv").value;


    let compNom = nom + " " + ap1 + " " + ap2;
    let compTarjeta = tarj;
    let compCorreo = correo;
    let compTelefono = telefono;

    document.getElementById("nombreObtenido").innerHTML = "Nombre: " + compNom;
    document.getElementById("tarjObtenido").innerHTML = "Tarjeta: **** **** **** " + compTarjeta;
    document.getElementById("correoObtenido").innerHTML = "Correo: " + compCorreo;
    document.getElementById("telObtenido").innerHTML = "Teléfono: " + compTelefono;

    if (nom.length == 0 || ap1.length == 0 || ap2.length == 0 || telefono.length == 0 || correo.length == 0 || pass.length == 0 || pass2.length == 0) {
        document.getElementById("nombre").style.borderBottom = "2px solid #dc3545";
        document.getElementById("apellido1").style.borderBottom = "2px solid #dc3545";
        document.getElementById("apellido2").style.borderBottom = "2px solid #dc3545";
        document.getElementById("contrasenha1").style.borderBottom = "2px solid #dc3545";
        document.getElementById("contrasenha2").style.borderBottom = "2px solid #dc3545";
        document.getElementById("correo").style.borderBottom = "2px solid #dc3545";
        document.getElementById("telefono").style.borderBottom = "2px solid #dc3545";
        textoError.style.visibility = "visible";

    } else if (telefono.length != 9) {
        alert('El teléfono introducido no cumple con el formato esperado');

    } else if (!comprobacion.exec(correo) && correo.length == 0) {
        document.getElementById("correo").style.borderBottom = "2px solid #dc3545";
        textoError.style.visibility = "visible";

    } else if (pass !== pass2) {
        alert('Las contraseñas deben coincidir')

    } else if (cont == 0 && nom.length != 0 && ap1.length != 0 && ap2.length != 0 && telefono.length != 0 && correo.length != 0 && pass.length != 0 && pass2.length != 0) {
        cont++;
        width = 50;

        document.getElementById("icono2").className = 'bg-primary';

        div1.style.visibility = 'hidden';
        div2.style.visibility = 'visible';

        document.getElementById("atras").style.visibility = 'visible';

        document.getElementById("nombre").style.borderBottom = "2px solid #caccce";
        document.getElementById("apellido1").style.borderBottom = "2px solid #caccce";
        document.getElementById("apellido2").style.borderBottom = "2px solid #caccce";
        document.getElementById("contrasenha1").style.borderBottom = "2px solid #caccce";
        document.getElementById("contrasenha2").style.borderBottom = "2px solid #caccce";
        document.getElementById("correo").style.borderBottom = "2px solid #caccce";
        document.getElementById("telefono").style.borderBottom = "2px solid #caccce";
        textoError.style.visibility = "hidden";

    } else if (tarj.length == 0 || fechaCad.length == 0 || cvv.length == 0) {
        document.getElementById("numTarjeta").style.borderBottom = "2px solid #dc3545";
        document.getElementById("fechaCaducidad").style.borderBottom = "2px solid #dc3545";
        document.getElementById("cvv").style.borderBottom = "2px solid #dc3545";
        textoError.style.visibility="visible";

    } else if (cont == 1 && tarj.length != 0 && fechaCad.length != 0 && cvv.length != 0) {
        cont++;
        width = 100;

        document.getElementById("icono3").className = 'bg-primary';

        div2.style.visibility = 'hidden';
        div3.style.visibility = 'visible';

        document.getElementById("siguiente").style.visibility = 'hidden';
        document.getElementById("enviar").style.visibility = 'visible';

        document.getElementById("numTarjeta").style.borderBottom = "2px solid #caccce";
        document.getElementById("fechaCaducidad").style.borderBottom = "2px solid #caccce";
        document.getElementById("cvv").style.borderBottom = "2px solid #caccce";
        textoError.style.visibility = "hidden";
    }
    barra.style.width = width + '%';
}

function reducir() {
    let barra = document.getElementById("barra");
    barra.style.width = width + '%';

    let div1 = document.getElementById("seccion1");
    let div2 = document.getElementById("seccion2");
    let div3 = document.getElementById("seccion3");

    if (cont == 2) {
        cont--;
        width = 50;

        document.getElementById("icono3").className = '';

        div3.style.visibility = 'hidden';
        div2.style.visibility = 'visible';

        document.getElementById("atras").style.visibility = 'visible';

        document.getElementById("siguiente").style.visibility = 'visible';
        document.getElementById("enviar").style.visibility = 'hidden';

    } else if (cont == 1) {
        cont--;
        width = 0;

        document.getElementById("icono2").className = '';

        div2.style.visibility = 'hidden';
        div1.style.visibility = 'visible';

        document.getElementById("atras").style.visibility = 'hidden';
    }
    barra.style.width = width + '%';
}

function progressBarScroll() {
    let winScroll = document.body.scrollTop || document.documentElement.scrollTop,
        height =
            document.documentElement.scrollHeight -
            document.documentElement.clientHeight,
        scrolled = (winScroll / height) * 100;
    document.getElementById("progressBar").style.width = scrolled + "%";
}

window.onscroll = function () {
    progressBarScroll();
};