package es.iesjandula.reaktor.timetable_server.utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.iesjandula.reaktor.timetable_server.exceptions.HorariosError;
import es.iesjandula.reaktor.timetable_server.models.ActitudePoints;
import es.iesjandula.reaktor.timetable_server.models.Classroom;
import es.iesjandula.reaktor.timetable_server.models.Student;
import es.iesjandula.reaktor.timetable_server.models.User;
import es.iesjandula.reaktor.timetable_server.models.Visitas;
import es.iesjandula.reaktor.timetable_server.models.parse.Aula;

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
	
	public List<Aula> transformarAula(List<Aula> original)
	{
		List<Aula> transformada = new LinkedList<Aula>();
		
		for(Aula aula:original)
		{
			if(!this.desecharAula(aula.getNombre(),aula.getAbreviatura()))
			{
				transformada.add(aula);
			}
		}
		return transformada;
	}
	
	private boolean desecharAula(String nombre,String numero)
	{
		switch(nombre)
		{
		case "LABORATORIO DE CIENCIAS":
		{
			return true;
		}
		case "Aula Taller Tecnología":
		{
			return true;
		}
		case "DESDOBLES":
		{
			return true;
		}
		case "Laboratorio FyQ - Desdbl4ESOB":
		{
			return true;
		}
		case "INFORMATICA 1":
		{
			return true;
		}
		case "INFORMATICA 2":
		{
			return true;
		}
		case "Sin asignar o sin aula":
		{
			return true;
		}
		case "Aula de Dibujo":
		{
			return true;
		}
		case "Patio Deportes":
		{
			return true;
		}
		default:
		{
			if(numero.equals("2.21") || numero.equals("1.11"))
			{
				return true;
			}
			
			return false;
		}
		}
	}
	/**
	 * Metodo que busca una clase determinada usando su identificador
	 * @param numero
	 * @param aulas
	 * @return clase encontrada
	 */
	public Classroom searchClassroom(String numero,List<Aula> aulas)
	{	
		int index = 0;
		boolean out = false;
		Classroom classroom = null;
		
		while(index<aulas.size() && !out)
		{
			Aula aula = aulas.get(index);
			
			if(aula.getNumIntAu().equals(numero))
			{
				classroom = new Classroom(aula.getNumIntAu(),aula.getAbreviatura(),aula.getNombre());
				out = true;
			}	
			index++;
		}
		
		return classroom;
		
	}
	/**
	 * Metodo que recibe el contenido de un fichero en bytes y lo parsea para obtener
	 * el nombre, apellidos y curso del alumnado
	 * @param content contenido en bytes
	 * @return lista de alumnos parseados
	 * @throws HorariosError
	 */
	public List<Student> parseStudent(byte [] content) throws HorariosError
	{
		List<Student> students = new LinkedList<Student>();
		
		String stringContent = new String(content);
		
		String [] split = stringContent.split("\n");
		
		split[0] = split[0].trim();
		
		if(!split[0].equals("\"Alumno/a\",\"Unidad\"") && !split[0].equals("\"Alumno/a\",\"Curso\""))
		{
			log.error("Los datos iniciales no son Alumno/a y Unidad o curso");
			throw new HorariosError(406,"Los datos del csv no coinciden con lo que requiere el servidor");
		}
		else
		{
			for(int i = 1;i<split.length;i++)
			{
				String [] splitDatos = split[i].split(",");
				//Operaciones con el nombre del alumno
				splitDatos[0] = splitDatos[0].substring(1);
				splitDatos[0] = splitDatos[0].trim();
				//Operaciones con el apellido del alumno
				splitDatos[1] = splitDatos[1].trim();
				splitDatos[1] = splitDatos[1].substring(0, splitDatos[1].length()-1);
				//Operaciones con el curso del alumno
				splitDatos[2] = splitDatos[2].trim();
				splitDatos[2] = splitDatos[2].substring(1, splitDatos[2].length()-1);
				
				students.add(new Student(splitDatos[1],splitDatos[0],splitDatos[2],0));
			}
		}
		return students;
	}
	/**
	 * Metodo que busca alumnos por el curso y los ordena por apellido
	 * @param course
	 * @param students
	 * @return lista de alumnos ordenada
	 * @throws HorariosError
	 */
	public Student [] sortStudentCourse(String course,List<Student> students) throws HorariosError
	{
		//Array para ordenar los alumnos
 		Student [] sortStudent = new Student[0];
		
 		//Busqueda de alumnos por curso
		for(Student student : students)
		{
			if(student.getCourse().equals(course))
			{
				sortStudent = Arrays.copyOf(sortStudent, sortStudent.length+1);
				sortStudent[sortStudent.length-1] = student;
			}
		}
		
		//Si no existen devolvemos un error
		if(sortStudent.length==0)
		{
			log.error("El curso "+course+" no se encuentra en ningun alumno");
			throw new HorariosError(404,"El curso intreoducido no coincide con ningun alumno");
		}
		
		//Si no hay error ordenamos los alumnos por apellido
		Arrays.sort(sortStudent);
		
		return sortStudent;
	}
	
	/**
	 * Metodo que busca a un estudiante por su nombre,apellidos y curso
	 * @param name
	 * @param lastName
	 * @param course
	 * @param students
	 * @return estudiante encontrado
	 */
	public Student findStudent (String name,String lastName,String course,List<Student> students)
	{
		Student student = null;
		int index = 0;
		boolean out = false;
		
		while(index<students.size() && !out)
		{
			Student item = students.get(index);
			
			if(item.getName().equals(name) && item.getLastName().equals(lastName) && item.getCourse().equals(course))
			{
				student = item;
				out = true;
			}
			
			index++;
		}
		return student;
	}
	/**
	 * Metodo que registra y comprueba la ida al baño de un estudiante
	 * @param student
	 * @param visitas
	 * @return visita como ida registrada
	 * @throws HorariosError
	 */
	public List<Visitas> comprobarVisita(Student student,List<Visitas> visitas) throws HorariosError
	{
		if(visitas.isEmpty())
		{
			visitas.add(new Visitas(student,true,false,null));
		}
		else
		{
			int index = 0;
			boolean out = false;
			
			while(index<visitas.size() && !out)
			{
				Visitas item = visitas.get(index);
				
				if(student.equals(item.getStudent()) && item.isIda() && !item.isVuelta())
				{
					out = true;
				}
				index++;
			}
			
			if(out)
			{
				throw new HorariosError(404,"El estudiante no ha regresado del baño");
			}
			else
			{
				visitas.add(new Visitas(student,true,false,null));
			}
		}
		
		return visitas;
	}
	
	/**
	 * Metodo que registra y comprueba la vuelta del baño de un estudiante
	 * @param student
	 * @param visitas
	 * @return lista de visitas actualizada con la vuelta
	 * @throws HorariosError
	 */
	public List<Visitas> comprobarVuelta(Student student,List<Visitas> visitas) throws HorariosError
	{
		if(visitas.isEmpty())
		{
			throw new HorariosError(404,"No hay visitas registradas");
		}
		else
		{
			int index = 0;
			boolean out = false;
			
			while(index<visitas.size() && !out)
			{
				Visitas item = visitas.get(index);
				
				if(student.equals(item.getStudent()) && item.isIda() && !item.isVuelta())
				{
					visitas.remove(index);
					item.setVuelta(true);
					int numBathroom = student.getNumBathroom();
					numBathroom++;
					student.setNumBathroom(numBathroom);
					LocalDateTime date = LocalDateTime.now();
					visitas.add(new Visitas(student,true,true,date));
					out = true;
				}
				
				index++;
			}
			
			if(!out)
			{
				throw new HorariosError(404,"El alumno no ha ido al baño en ningun momento");
			}
			
			return visitas;
		}
	}
	
	/**
	 * Metodo que busca un estudainte y suma en uno las veces que ha ido al baño
	 * @param student
	 * @param students
	 * @return
	 */
	public List<Student> sumarBathroom(Student student,List<Student> students)
	{
		int index = 0;
		boolean out = false;
		
		while(index<students.size() && !out)
		{
			Student item = students.get(index);
			
			if(item.equals(student))
			{
				students.remove(index);
				int numBathroom = student.getNumBathroom();
				numBathroom++;
				student.setNumBathroom(numBathroom);
				students.add(student);
				out = true;
			}
			index++;
		}
		
		return students;
	}
	
	/**
	 * Metodo que ordena todos los estudiantes por su apellido
	 * @param students
	 * @return lista de estudiantes ordenados
	 */
	public Student [] ordenarStudents (List<Student> students)
	{
		Student [] sortStudents = new Student[0];
		
		for(int i=0;i<students.size();i++)
		{
			sortStudents = Arrays.copyOf(sortStudents, i++);
			sortStudents[i] = students.get(i);
		}
		
		Arrays.sort(sortStudents);
		
		return sortStudents;
	}
 	
}
