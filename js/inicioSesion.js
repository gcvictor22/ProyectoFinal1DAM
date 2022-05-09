function verificar() {
    let email = document.getElementById("email");
    let contrasenha = document.getElementById("password");

    if (email.length == 0) {
        alert('Faltan datos por rellenar');
    }else if(contrasenha.length == 0){
        alert('Faltan datos por rellenar');
    }
}