package es.iesjandula.reaktor.timetable_server.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.iesjandula.reaktor.timetable_server.exceptions.HorariosError;
import es.iesjandula.reaktor.timetable_server.models.User;

public class TimeTableUtils 
{
	/**Logger de la clase */
	private static Logger log = LogManager.getLogger();
	/**
	 * Metodo que obtiene un usuario pasandole el email y su password si encuentra 
	 * el usuario lo devuelve en caso contrario devuelve un error
	 * @param email email del usuario 
	 * @param password password del usuario
	 * @return usuario entontrado
	 * @throws HorariosError error de cliente por no encontrar el usuario
	 */
	public void getUser(String email,String password) throws HorariosError
	{
		File file = new File("src/main/resources/users.json");
		User user = null;
		try
		{
			//Lectura de usuarios
			ObjectMapper mapper = new ObjectMapper();
			
			List<User> users = mapper.readValue(file, new TypeReference<List<User>>(){});
			
			//Busqueda del usuario
			int index = 0;
			boolean out = false;
			
			while(index<users.size() && !out)
			{
				user = users.get(index);
				
				if(user.getEmail().equals(email) && user.getPassword().equals(password))
				{
					out = true;
				}
				
				index++;
			}
			
			//Comprobacion de que se ha encontrado el usuario especificado
			user = out ? user : null;
			
			if(user==null)
			{
				log.error("Usuario con email "+email+" y passwd "+password+" no existe");
				throw new HorariosError(404,"Usuario no encontrado");
			}
			
		}
		catch(IOException exception)
		{
			log.error("Error al leer los usuario del json "+file.getName(),exception);
			throw new HorariosError(500,"Error al obtener los usuarios del fichero json base",exception);
		}
		//El return se coloca para que en un futuro se devuelva el usuario
		//return user;
	}
}
