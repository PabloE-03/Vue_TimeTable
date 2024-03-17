const http = require("http");

class User
{
    constructor(email,password)
    {
        this.email = email;
        this.password = password;
    }
    /**
     * Metodo que realiza una peticion http a localhost:8088/horarios/login para comprobar
     * que el usuario existe, para comprobar si existe o no se comprueba el codigo de respuesta que sea
     * un 200 en caso de que no lo sea se dara como falso
     * @returns true si el codigo de devuelta es 200 false si no lo es
     */
    peticion()
    {
        var encontrado = false;
        //Declaracion de los parametros query en JSON
        const query = {
            email : this.email,
            password : this.password
        };

        //Transformacion de los parametros a string
        const queryString = new URLSearchParams(query).toString();
        
        //Declaracion de la url
        const url = {
            hostname : "localhost",
            port : 8088,
            path : "/horarios/login?"+queryString,
            method : 'GET'
        };

        //Solicitud http
        const respuesta = http.request(url,(res)=>{
            var code =  res.statusCode
            res.on('data',(chunk) => {
                console.log("Respuesta: "+chunk+"\n"+new Date().toDateString());
            })
            if(code==200)
            {
                encontrado = true;
            }
        });

        respuesta.on('error',(error) => {
            console.log(error);
        });

        respuesta.end();
        return encontrado;
    }
}

export default User;