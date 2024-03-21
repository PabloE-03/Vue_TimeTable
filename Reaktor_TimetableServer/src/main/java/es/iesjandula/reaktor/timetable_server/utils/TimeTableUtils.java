package es.iesjandula.reaktor.timetable_server.utils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.iesjandula.reaktor.timetable_server.exceptions.HorariosError;
import es.iesjandula.reaktor.timetable_server.models.ActitudePoints;
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
	
	public List<ActitudePoints> loadPoints()
	{
		List<ActitudePoints> points = new LinkedList<ActitudePoints>();
		
		points.add(new ActitudePoints(1, "Buen comportamiento en aula o en actividad extraescolar"));
		points.add(new ActitudePoints(1, "Buen trabajo en clase"));
		points.add(new ActitudePoints(1, "Realizacion de las tareas propuestas en el aula de reflexión/aula convivencia/trabajos comunitarios"));
		points.add(new ActitudePoints(1, "Buen comportamiento en el aula de reflexión/aula convivencia/trabajos comunitarios"));
		points.add(new ActitudePoints(2, "Alumno que no ha perdido puntos en 2 semanas"));
		points.add(new ActitudePoints(2, "Comportamiento excelente en aula o en actividad extraescolar"));
		points.add(new ActitudePoints(2, "Realizar trabajos extraordinarios o voluntarios"));
		points.add(new ActitudePoints(2, "Buen comportamiento en el aula de reflexión/aula convivencia/trabajos comunitarios"));
		points.add(new ActitudePoints(2, "Ayudar al profesor/a en clase"));
		points.add(new ActitudePoints(2, "Asistencia de las familias a reuniones grupales de tutoria"));
		points.add(new ActitudePoints(5, "Participar activamente en las actividades del centro"));
		points.add(new ActitudePoints(5, "Aparece en el cuadro de honor de la clase"));
		points.add(new ActitudePoints(5, "Participar activamente en el cuidado del centro"));
		points.add(new ActitudePoints(5, "Obtencion de un diploma de Convivencia+"));
		points.add(new ActitudePoints(-1, "Interrumpir puntualmente el desarrollo normal de la clase"));
		points.add(new ActitudePoints(-1, "No realizar las tareas en el aula de reflexión/aula convivencia/trabajos comunitarios"));
		points.add(new ActitudePoints(-1, "Mal comportamiento en el aula de reflexión/aula convivencia/trabajos comunitarios"));
		points.add(new ActitudePoints(-3, "Interrumpir de forma reiterada el desarrollo normal de la clase"));
		points.add(new ActitudePoints(-3, "Tres o mas retrasos injustificados en la misma materia a la entrada de clase"));
		points.add(new ActitudePoints(-3, "Molestar a un compañero/a"));
		points.add(new ActitudePoints(-3, "Consumir comida o bebida sin el permiso del profesor/a"));
		points.add(new ActitudePoints(-5, "Comportamiento inadecuado en dependencias comunes"));
		points.add(new ActitudePoints(-5, "Enfrentamiento verbal menor con un compañero"));
		points.add(new ActitudePoints(-5, "Salir de la clase sin la tarjeta del docente"));
		points.add(new ActitudePoints(-5, "Uso indebido del movil (1a y 2a vez)"));
		points.add(new ActitudePoints(-5, "Faltas de asistencia sin justificar igual o superior a 24 horas al mes (aviso al tutor)"));
		points.add(new ActitudePoints(-10, "Imposibilidad de desarrollar de forma normal la clase (envio al aula de reflexion)"));
		points.add(new ActitudePoints(-10, "Desobediencia o enfrentamiento verbal leve con el docente"));
		points.add(new ActitudePoints(-10, "No colaborar con el centro en el esclarecimiento de hechos de conducta contraria a las normas de convivencia"));
		points.add(new ActitudePoints(-10, "Incumplimiento de una sancion impuesta anteriormente"));
		points.add(new ActitudePoints(-10, "Copiar o hacer trampa durante una actividad evaluable"));
		points.add(new ActitudePoints(-10, "Enfrentamiento verbal con otro compañero o enfrentamiento con leve contacto fisico"));
		points.add(new ActitudePoints(-10, "Uso incorrecto de los medios TIC"));
		points.add(new ActitudePoints(-10, "Causar daños menores en material, instalaciones o mobiliario del centro"));
		points.add(new ActitudePoints(-10, "Perder o deteriorar la tarjeta del docente"));
		points.add(new ActitudePoints(-15, "Desobediencia o enfrentamiento grave con el docente"));
		points.add(new ActitudePoints(-15, "Impedimento del desarrollo normal de la clase de forma colectiva"));
		points.add(new ActitudePoints(-15, "Introduccion de objetos prohibidos en el centro"));
		points.add(new ActitudePoints(-15, "Injurias, ofensas, amenazas o coacciones entre iguales"));
		points.add(new ActitudePoints(-15, "No entregar a las familias los comunicados de infraccion"));
		points.add(new ActitudePoints(-15, "Quedarse en otras clases en periodo lectivo o durante el recreo"));
		points.add(new ActitudePoints(-15, "Uso indebido del movil 3a vez y siguientes (aviso a convivencia)"));
		points.add(new ActitudePoints(-25, "Uso de los objetos prohibidos en el centro"));
		points.add(new ActitudePoints(-25, "Causar daños intencionados en material, instalaciones o mobiliarios del centro"));
		points.add(new ActitudePoints(-25, "Suplantacion de identidad de un docente o familiar"));
		points.add(new ActitudePoints(-25, "Falsificacion o sustraccion de documentos o pertenencias academicas"));
		points.add(new ActitudePoints(-25, "Injurias, ofensas, amenazas o coacciones a un docente"));
		points.add(new ActitudePoints(-25, "Injurias, ofensas, amenazas o coacciones a un miembro de la comunidad educativa con fines agravantes"));
		points.add(new ActitudePoints(-25, "Abandonar el centro sin permiso previo"));
		points.add(new ActitudePoints(-25, "Sustraccion de pertenencias de cualquier miembro de la comunidad educativa"));
		points.add(new ActitudePoints(-25, "Realizacion y/o difusion de grabaciones de voz, fotos o videos en el centro de cualquier miembro de la comunidad educativa"));
		points.add(new ActitudePoints(-25, "Consumo de sustancias prohibidas en el centro"));
		points.add(new ActitudePoints(-25, "Agresion fisica entre iguales"));
		points.add(new ActitudePoints(-75, "Agresion fisica a docentes o cualquier miembro de la comunidad educativa con agravante"));
		
		return points;
	}
}
