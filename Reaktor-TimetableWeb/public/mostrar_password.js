document.addEventListener("DOMContentLoaded", function () {
    var passwordInput = document.getElementById("password");
    var mostrarPassWord = document.getElementById("mostrarPassword");

    mostrarPassWord.addEventListener("click", function () {
        if (passwordInput.type === "password") {
            passwordInput.type = "text";
            mostrarPassWord.style.backgroundImage = "url('./assets/ocultar-contrasena.svg')";
            console.log("Ocultando");
        } else {
            passwordInput.type = "password";
            mostrarPassWord.style.backgroundImage = "url('./assets/ocultar-contrasena.svg')";
            console.log("Mostrando");
        }
    });
});