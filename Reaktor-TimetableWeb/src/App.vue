<!-- Componente JavaScript -->
<script setup>
import { ref, watch } from 'vue';
import User from './js/login-users';
//Uso de DOM para cambiar el titulo de la pagina web
var titulo = document.getElementById("titulo");
titulo.textContent = "Reaktor";

//Variables para interpolar en el html
var mostrar = ref(false);
const email = ref("");
const passwd = ref("");
var inicioSesion = ref(false);
var errorSesion = ref(false);

//Observador para la contraseña para que cuando este vacia se resetee el
//boton de mostrar contraseña
watch(passwd,(nuevo,viejo)=>{
    if(nuevo=="")
    {
        mostrar = ref(false);   
    }
});

//Metodo para iniciar sesion
const iniciarSesion = () =>
{
    var usuario = new User(email,passwd);

    if(usuario.peticion())
    {
        console.log("Hola mundo");
    }
}
   

</script>
<!-- Componente HTML -->
<template>
    <div class="login">
        <h1>Iniciar Sesión</h1>
        <form action="http://localhost:8088/horarios/login" method="get" target="_blank">
            <div class="email">
                <label for="email">Email</label>
                <input type="email" name="email" id="email" v-model="email" placeholder="Email" required>
            </div>
            <div class="password">
                <label for="password">Contraseña</label>
                <div v-show="mostrar" class="password-icon">
                    <input type="text" name="password" v-model="passwd" id="password" placeholder="Contraseña" required>
                    <button v-show="passwd!=''" v-on:click="mostrar = !mostrar" type="button" id="mostrarPassword">  </button>
                </div>
                <div v-show="!mostrar" class="password-icon">
                    <input type="password"  v-model="passwd" id="password" placeholder="Contraseña" required>
                    <button v-show="passwd!=''" v-on:click="mostrar = !mostrar" type="button" id="ocultarPassword">  </button>
                </div>
            </div>
            <button v-on:click="iniciarSesion">Iniciar Sesión</button>
        </form>
    </div>
</template>
<!-- Componente CSS -->
<style scoped>

*{
    margin: 0;
    padding: 0;
}

.index{
    min-height: 100vh;
    background-color: rgb(241, 241, 224);
}

main{
    display: flex;
    align-items: center;
    justify-content: center;
    height: 80vh;
}

.login{
    padding: 35px;
    border-radius: 8px;
    box-shadow: 0 0 15px rgb(31, 155, 203);
    border: 1px solid;
    border-color: rgb(31, 155, 203);
    background-color: white;
}

.login h1{
    font-size: 40px;
    text-align: center;
    margin-bottom: 15px;
    color: rgb(31, 155, 203);
}

.login label{
    display: block;
    width: 100%;
    margin-bottom: 5px;
    color: rgb(31, 155, 203);
    font-size: 1em;
    font-weight: 600;
}

.login input {
    width: 100%;
    padding: 8px;
    box-sizing: border-box;
    margin-bottom: 16px;
    border: 1px solid;
    border-color: rgb(31, 155, 203);
}

.login button {
    width: 100%;
    padding: 8px;
    box-sizing: border-box;
    margin-bottom: 16px;
    border: 1px solid;
    border-color: rgb(31, 155, 203);
}

.login button{
    color: white;
    background-color: rgb(31, 155, 203);
    border-radius: 4px;
    border-color: transparent;
    cursor: pointer;
}

.login button:hover{
    background-color: rgb(1, 128, 179);
}

.password button{
    width: 32px;
    padding: 5px;
}

#mostrarPassword{
    background-image: url('./assets/mostrar-contrasena.svg');
    background-size: contain;
    background-repeat: no-repeat;
    background-color: transparent;
    border-color: transparent;
    cursor: pointer;
}

#ocultarPassword{
    background-image: url('./assets/ocultar-contrasena.svg');
    background-size: contain;
    background-repeat: no-repeat;
    background-color: transparent;
    border-color: transparent;
    cursor: pointer;
}

.email, .password{
    margin-bottom: 10px;
}

.password-icon{
    display: flex;
    justify-content: space-between;
}


</style>
