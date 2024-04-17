package es.iesjandula.reaktor.timetable_server.utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import es.iesjandula.reaktor.timetable_server.models.parse.AulaPlano;

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
	 * @deprecated Por ahora se esta usando el metodo {@link #ordenarLista(List)} el cual mas adelante sera el principal
	 */
	public Student [] ordenarStudents (List<Student> students)
	{
		Student [] sortStudents = new Student[0];
		
		for(int i=0;i<students.size();i++)
		{
			sortStudents = Arrays.copyOf(sortStudents, i+1);
			sortStudents[i] = students.get(i);
		}
		
		Arrays.sort(sortStudents);
		
		return sortStudents;
	}
	
	/**
	 * Metodo que ordena una lista generica pasandola a array y ordenandola desde ahi
	 * @param <T> generico que tomara como valor la clase profesores y estudiante
	 * @param objectList
	 * @return array ordenado
	 */
	public <T> Object [] ordenarLista(List<T> objectList)
	{
		Object [] arraySorted = new Object[0];
		
		for(int i=0;i<objectList.size();i++)
		{
			arraySorted = Arrays.copyOf(arraySorted, i+1);
			arraySorted[i] = objectList.get(i);
		}
		
		
		Arrays.sort(arraySorted);
		
		return arraySorted;
	}
	
	/**
	 * Metodo que busca las visitas al baño de un determinado alumno usando
	 * un periodo de fechas, los datos se devuelven en una lista de mapas de formato
	 * String String en el que en cada item se guarda el dia y la hora en la que se fue
	 * al baño 
	 * @param student
	 * @param fechaInicio
	 * @param fechaFin
	 * @param visitas
	 * @return lista de mapas en formato String,String que guarda en cada item el dia y la hora en la que se fue al baño
	 */
	public List<Map<String,String>> getVisitaAlumno(Student student,String fechaInicio,String fechaFin,List<Visitas> visitas)
	{
		List<Map<String,String>> visitaAlumno = new LinkedList<Map<String,String>>();
		
		List<Visitas> visitasAlumno = this.findVisitasAlumno(student, visitas);
		
		//Separador de fecha en dia mes year
		String[] splitFecha = fechaInicio.split("/");
		
		//Array de fechas en formato int
		int[] fechaInt = {Integer.parseInt(splitFecha[0].trim()),Integer.parseInt(splitFecha[1].trim()),Integer.parseInt(splitFecha[2].trim())};
		
		boolean endParser = false;
		
		//Bucle para iterar y guardar los dias y horas en los que el alumno ha ido al baño
		while(!endParser)
		{
			//Transformamos la fecha a string
			String itemDate = this.transformDate(fechaInt);
			
			//Iteramos las visitas
			for(Visitas item:visitasAlumno)
			{
				LocalDateTime date = item.getDate();
				//Nos quedamos solo con las que coincida la fecha
				if(this.compareDate(itemDate, date))
				{
					//Anotamos la fecha y la hora con las que ha ido al baño
					Map<String,String> datosVisita = new HashMap<String,String>();
					datosVisita.put("dia",itemDate);
					datosVisita.put("hora", this.parseTime(date.getHour())+":"+this.parseTime(date.getMinute()));
					visitaAlumno.add(datosVisita);
				}
			}
			
			//Comprobamos si la fecha iterada coincide con la fecha final si no la aumentamos
			if(itemDate.equals(fechaFin))
			{
				endParser = true;
			}
			else
			{
				fechaInt = this.sumarDate(fechaInt);
			}
		}
 		
		return visitaAlumno;
	}
 	
	/**
	 * Metodo que busca las visitas de varios alumnos
	 * @param fechaInicio
	 * @param fechaFin
	 * @param visitas
	 * @return
	 */
	public List<Map<String,Object>> getVisitasAlumnos(String fechaInicio,String fechaFin,List<Visitas> visitas)
	{
		List<Map<String,Object>> visitasAlumnos = new LinkedList<Map<String,Object>>();
		
		//Separador de fecha en dia mes year
		String[] splitFecha = fechaInicio.split("/");
		
		//Array de fechas en formato int
		int[] fechaInt = {Integer.parseInt(splitFecha[0].trim()),Integer.parseInt(splitFecha[1].trim()),Integer.parseInt(splitFecha[2].trim())};
		
		boolean endParser = false;
		
		//Bucle para iterar y guardar los dias y horas en los que el alumno ha ido al baño
		while(!endParser)
		{
			//Transformamos la fecha a string
			String itemDate = this.transformDate(fechaInt);
			
			//Iteramos las visitas
			for(Visitas item:visitas)
			{
				LocalDateTime date = item.getDate();
				//Nos quedamos solo con las que coincida la fecha
				if(this.compareDate(itemDate, date))
				{
					//Anotamos la fecha y la hora con las que ha ido al baño
					Map<String,Object> datosVisita = new HashMap<String,Object>();
					datosVisita.put("alumno",item.getStudent());
					datosVisita.put("dia", itemDate);
					datosVisita.put("veces", item.getStudent().getNumBathroom());
					visitasAlumnos.add(datosVisita);
				}
			}
			
			//Comprobamos si la fecha iterada coincide con la fecha final si no la aumentamos
			if(itemDate.equals(fechaFin))
			{
				endParser = true;
			}
			else
			{
				fechaInt = this.sumarDate(fechaInt);
			}
		}
		return visitasAlumnos;
				
	}
	
	/**
	 * Metodo que busca las visitas al baño de un alumno en concreto
	 * @param student
	 * @param visitas
	 * @return visitas del alumno introducido encontradas
	 */
	private List<Visitas> findVisitasAlumno(Student student,List<Visitas>visitas)
	{
		List<Visitas> visitasAlumno = new LinkedList<Visitas>();
		
		for(Visitas item:visitas)
		{
			if(item.getStudent().equals(student))
			{
				visitasAlumno.add(item);
			}
		}
		
		return visitasAlumno;
	}
	/**
	 * Metodo que transforma la fecha en entero a una fecha en string añadiendo
	 * 0 en la fecha en caso de que un valor del entero este comprendido entre
	 * 1 y 9
	 * @param dateInt
	 * @return fecha en formato string
	 */
	private String transformDate(int[]fechaInt)
	{
		String fechaString = "";
		
		if((fechaInt[0]>0 && fechaInt[0]<10) && (fechaInt[1]>0 && fechaInt[1]<10))
		{
			fechaString = "0"+fechaInt[0]+"/0"+fechaInt[1]+"/"+fechaInt[2];
		}
		else if(fechaInt[0]>0 && fechaInt[0]<10)
		{
			fechaString = "0"+fechaInt[0]+"/"+fechaInt[1]+"/"+fechaInt[2];
		}
		else if(fechaInt[1]>0 && fechaInt[1]<10)
		{
			fechaString = fechaInt[0]+"/0"+fechaInt[1]+"/"+fechaInt[2];
		}
		else
		{
			fechaString = fechaInt[0]+"/"+fechaInt[1]+"/"+fechaInt[2];
		}
		
		return fechaString;
	}
	
	/**
	 * Metodo que comprueba que dos fechas sean iguales para recoger los
	 * datos de la fecha en la que un alumno fue al baño
	 * @param fecha
	 * @param fechaReal
	 * @return true si son iguales false si no
	 */
	private boolean compareDate(String fecha,LocalDateTime fechaReal)
	{
		int [] fechaInt = {fechaReal.getDayOfMonth(),fechaReal.getMonthValue(),fechaReal.getYear()};
		
		String otherFecha = this.transformDate(fechaInt);
		
		return fecha.equals(otherFecha);
	}
	
	/**
	 * Metodo que suma la fecha en uno y comprueba los saltos de meses y años
	 * @param dateInt
	 * @return nueva fecha en array de enteros
	 */
	private int [] sumarDate(int[]dateInt)
	{
		int [] newFecha = null;
		
		switch(dateInt[1])
		{
			case 1,3,5,7,8,10,12:
			{
				dateInt[0]++;
				if(dateInt[0]>31)
				{
					dateInt[1]++;
					dateInt[0] = 1;
					if(dateInt[1]>12)
					{
						dateInt[2]++;
						dateInt[1] = 1;
					}
				}
				newFecha = dateInt;
				break;
			}
			case 4,6,9,11:
			{
				dateInt[0]++;
				if(dateInt[0]>31)
				{
					dateInt[1]++;
					dateInt[0] = 1;
				}
				newFecha = dateInt;
				break;
			}
			case 2:
			{
				boolean bisiesto = dateInt[2]%4==0;
				dateInt[0]++;
				if(bisiesto && dateInt[0]>29)
				{
					dateInt[1]++;
					dateInt[0] = 1;
				}
				else if(dateInt[0]>28 && !bisiesto)
				{
					dateInt[1]++;
					dateInt[0] = 1;
				}
				newFecha = dateInt;
				break;
			}
		}
		
		return newFecha;
	}
	
	/**
	 * Metodo que parsea los minutos y las horas de entero a string
	 * en caso de que vengan dados en numeros entre 1 y 9 se le coloca un 0 detras
	 * por ejemplo si una hora viene en 4 se parsea a 04
	 * @param hour
	 * @return hora parseada
	 */
	private String parseTime(int hour)
	{
		String newHour = ""+hour;
		
		if(hour>0 && hour<10)
		{
			newHour = "0"+hour;
		}
		
		return newHour;
	}
	
	/**
	 * Metodo que devuelve todas las aulas para la parte de planos en
	 * el frontend
	 * <br>
	 * <br>
	 * AVISO: No alterar el orden en el que se añaden las aulas
	 * ya que luego en el frontend saldran datos erroneos
	 * @return lista de aulas para los planos en el front
	 */
	public List<AulaPlano> rellenarAulasPlano()
	{
		List<AulaPlano> aulas = new LinkedList<AulaPlano>();
		aulas.add(new AulaPlano(6,7,1.6,0,14,"PLANTA BAJA",new Aula("53","2GUIA","2GUIA")));
		aulas.add(new AulaPlano(6,7,1.6,0,21,"PLANTA BAJA",new Aula("51","GUI1A","GUIA 1A")));
		aulas.add(new AulaPlano(7.5,6.5,1.6,0,28,"PLANTA BAJA",new Aula("52","GUI1B","GUIA 1B")));
		aulas.add(new AulaPlano(5,18.4,15,0,12,"PLANTA BAJA",new Aula("50","Patio","Patio Deportes")));
		aulas.add(new AulaPlano(6,7,1.6,0,21,"PLANTA BAJA",new Aula("51","GUI1A","GUIA 1A")));
		aulas.add(new AulaPlano(5,8.1,12.8,0,44.2,"PLANTA BAJA",new Aula("43","0.11","1BACH-A")));
		aulas.add(new AulaPlano(5,11.2,12.8,0,52.8,"PLANTA BAJA",new Aula("42","0.9","Aula de Dibujo")));
		aulas.add(new AulaPlano(4.8,9,13.5,12.8,0,"PLANTA BAJA",new Aula("4","0.5","1DAM/DAW")));
		aulas.add(new AulaPlano(5,6.9,18.6,15,0,"PLANTA BAJA",new Aula("35","0.7","2DAM/2DAW")));
		aulas.add(new AulaPlano(4.8,3.6,30.82,13.8,0,"PLANTA BAJA",new Aula("2","0.3","2FPB")));
		aulas.add(new AulaPlano(5,5.5,30.8,17.7,0,"PLANTA BAJA",new Aula("44","0.1","1FPB")));
		aulas.add(new AulaPlano(6,7,1.6,0,21,"PLANTA BAJA",new Aula("51","GUI1A","GUIA 1A")));
		aulas.add(new AulaPlano(6.5,4.5,36.5,0,12,"PLANTA BAJA",new Aula("46","0.2A","3DIVER")));
		aulas.add(new AulaPlano(6.5,4.5,36.5,0,16.7,"PLANTA BAJA",new Aula("49","0.2B","4DIVER")));
		aulas.add(new AulaPlano(12.5,11.3,5,4.9,0,"PRIMERA PLANTA",new Aula("17","1.11","Aula Taller Tecnología")));
		aulas.add(new AulaPlano(6,11.6,26.5,6.1,0,"PRIMERA PLANTA",new Aula("18","1.9","1ESOC")));
		aulas.add(new AulaPlano(6,11.6,26.5,18.3,0,"PRIMERA PLANTA",new Aula("15","1.7","1ESOB")));
		aulas.add(new AulaPlano(6,11.6,26.5,29.75,0,"PRIMERA PLANTA",new Aula("14","1.5","1ESOA")));
		aulas.add(new AulaPlano(6,11.6,26.5,41.3,0,"PRIMERA PLANTA",new Aula("13","1.3","3ESO_BDIVER")));
		aulas.add(new AulaPlano(6.4,11.6,18,41.2,0,"PRIMERA PLANTA",new Aula("16","1.17","3ESO_BDIVER")));
		aulas.add(new AulaPlano(6.4,11.6,18,29.7,0,"PRIMERA PLANTA",new Aula("36","1.15","3ESOA")));
		aulas.add(new AulaPlano(6.4,11.6,18,18.2,0,"PRIMERA PLANTA",new Aula("19","1.13","1MECA")));
		aulas.add(new AulaPlano(6.2,11.5,27,0,24.6,"PRIMERA PLANTA",new Aula("10","1.2","4ESOA")));
		aulas.add(new AulaPlano(6.2,11.5,27,0,12.9,"PRIMERA PLANTA",new Aula("9","1.4","DESDOBLES")));
		aulas.add(new AulaPlano(6.2,11.5,27,0,1.3,"PRIMERA PLANTA",new Aula("8","1.6","1BACHC")));
		aulas.add(new AulaPlano(6,11.3,35.5,0,1.3,"PRIMERA PLANTA",new Aula("11","1.8","LABORATORIO DE CIENCIAS")));
		aulas.add(new AulaPlano(6,11.3,35.5,0,18.7,"PRIMERA PLANTA",new Aula("12","1.12","1BACH-B")));
		aulas.add(new AulaPlano(6.8,11.3,4,4.5,0,"SEGUNDA PLANTA",new Aula("27","2.11","INFORMATICA 1")));
		aulas.add(new AulaPlano(6,8.9,11,7,0,"SEGUNDA PLANTA",new Aula("30","2.13","INFORMATICA 2")));
		aulas.add(new AulaPlano(6,11.7,25.8,5.9,0,"SEGUNDA PLANTA",new Aula("32","2.9","2ESOC")));
		aulas.add(new AulaPlano(6,11.1,25.8,18,0,"SEGUNDA PLANTA",new Aula("31","2.7","2ESOB")));
		aulas.add(new AulaPlano(6,11.2,25.8,29.45,0,"SEGUNDA PLANTA",new Aula("28","2.5","2ESOA")));
		aulas.add(new AulaPlano(6,11.1,25.8,18,0,"SEGUNDA PLANTA",new Aula("31","2.7","2ESOB")));
		aulas.add(new AulaPlano(6,11.6,25.8,41,0,"SEGUNDA PLANTA",new Aula("26","2.3","4ESOB_DIVER")));
		aulas.add(new AulaPlano(6,5.5,25.8,52.9,0,"SEGUNDA PLANTA",new Aula("25","2.1","2ESOA-AMB")));
		aulas.add(new AulaPlano(6,4,17.2,41,0,"SEGUNDA PLANTA",new Aula("40","2.21","REOLIGIÓN EVANGÉLICA Y VE")));
		aulas.add(new AulaPlano(6,8.7,17.2,41,0,"SEGUNDA PLANTA",new Aula("29","2.19","DESDOBLES")));
		aulas.add(new AulaPlano(6,11.3,17.2,29.4,0,"SEGUNDA PLANTA",new Aula("39","2.17","4ESOC")));
		aulas.add(new AulaPlano(6,11.2,17.2,18,0,"SEGUNDA PLANTA",new Aula("3","2.15","4ESOD")));
		aulas.add(new AulaPlano(6.2,11.5,26.2,0,24.8,"SEGUNDA PLANTA",new Aula("31","2.2","DESDOBLES")));
		aulas.add(new AulaPlano(6.2,11.5,26.2,0,13,"SEGUNDA PLANTA",new Aula("21","2.4","2BACH-C")));
		aulas.add(new AulaPlano(6.2,11.5,26.2,0,1.5,"SEGUNDA PLANTA",new Aula("22","2.6","2BACH-A")));
		aulas.add(new AulaPlano(6.1,11.3,34.6,0,1.5,"SEGUNDA PLANTA",new Aula("23","2.8","2BACH-B")));
		aulas.add(new AulaPlano(6.1,11.3,34.6,0,18.9,"SEGUNDA PLANTA",new Aula("24","2.12","Laboratorio FyQ - Desdbl4ESOB")));

		return aulas;
	}
	
	/**
	 * Metodo que filtra las aulas para los planos del front por su planta
	 * devolviendo una lista de las mismas filtradas
	 * @param planta
	 * @param aulas
	 * @return lista de aulas filtradas
	 * @throws HorariosError
	 */
	public List<AulaPlano> buscarPorPlanta(String planta,List<AulaPlano> aulas) throws HorariosError
	{ 
		List<AulaPlano> aulasEncontradas = new LinkedList<AulaPlano>();
		
		for(AulaPlano aula:aulas)
		{
			if(aula.getPlanta().equals(planta))
			{
				aulasEncontradas.add(aula);
			}
		}
		
		if(aulasEncontradas.isEmpty())
		{
			throw new HorariosError(404,"La planta introducida es erronea, su valor debe de se PLANTA BAJA, PRIMERA PLANTA, SEGUNDA PLANTA, en literal");
		}
		
		return aulasEncontradas;
	}
	
}




