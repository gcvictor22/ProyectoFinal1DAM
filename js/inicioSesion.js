function verificar() {
    let email = document.getElementById("email");
    let contrasenha = document.getElementById("password");
    let formulario = document.getElementById("formulario");

    if (email.length == 0 || contrasenha.length == 0) {
        alert('Faltan datos por rellenar');
    }else {
        formulario.setAttribute('action', '../index.html');
    }
}