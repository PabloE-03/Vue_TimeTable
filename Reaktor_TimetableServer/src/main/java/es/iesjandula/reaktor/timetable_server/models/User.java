package es.iesjandula.reaktor.timetable_server.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User 
{
	/**Email del usuario */
	private String email;
	
	/**Contraseña del usuario */
	private String password;
}
