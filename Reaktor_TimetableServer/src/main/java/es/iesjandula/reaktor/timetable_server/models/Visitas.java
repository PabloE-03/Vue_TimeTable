package es.iesjandula.reaktor.timetable_server.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Visitas 
{
	/**Alumno que ha ido al baño */
	private Student student;
	
	/**Variable que indica la ida */
	private boolean ida;
	
	/**Variable que indica la vuelta */
	private boolean vuelta;
	
	/**Variable que indica la fecha en la que ha ido */
	private LocalDateTime date;
}
