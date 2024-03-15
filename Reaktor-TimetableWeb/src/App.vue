<script setup>
import { ref } from 'vue';


var titulo = document.getElementById("titulo");
titulo.textContent = "Reaktor";
const mostrar = ref(false);
const contenido = ref("");
</script>

<template>
    <div class="login">
        <h1>Iniciar Sesión</h1>
        <form action="get">
            <div class="email">
                <label for="email">Email</label>
                <input type="email" name="email" id="email" placeholder="Email" required>
            </div>
            <div class="password">
                <label for="password">Contraseña</label>
                <div v-show="mostrar" class="password-icon">
                    <input type="text" name="password" v-model="contenido" id="password" placeholder="Contraseña" required>
                    <button v-show="contenido!=''" v-on:click="mostrar = !mostrar" type="button" id="mostrarPassword">  </button>
                </div>
                <div v-show="!mostrar" class="password-icon">
                    <input type="password" name="password" v-model="contenido" id="password" placeholder="Contraseña" required>
                    <button v-show="contenido!=''" v-on:click="mostrar = !mostrar" type="button" id="ocultarPassword">  </button>
                </div>
            </div>
            <input type="submit" value="Iniciar Sesión">
        </form>
    </div>
</template>

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

.login input[type="submit"]{
    color: white;
    background-color: rgb(31, 155, 203);
    border-radius: 5px;
    border-color: transparent;
    cursor: pointer;
}

.login input[type="submit"]:hover{
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
