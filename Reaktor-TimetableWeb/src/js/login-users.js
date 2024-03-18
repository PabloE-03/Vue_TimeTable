import axios from "axios";

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
        
        let url = "http://localhost:8088/horarios/login?"+queryString;
        let code = 0;
        axios.get(url).then(async res => {
            try{
                code = await res.data;
                console.log(code);
            }catch{
            console.log(err);
            }
        });

    }
}

export default User;
