package es.iesjandula.reaktor.timetable_server.models;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author David Martinez
 *
 */
@Data
@NoArgsConstructor
public class Classroom
{
	/** Attribute number*/
	private String number;
	
	/** Attribute floor*/
	private String floor;
	
	/** Attribute name classroom */
	private String name;

	public Classroom(String number, String floor, String name) 
	{
		this.number = number;
		this.floor = floor;
		this.name = name;
	}

	public Classroom(String number, String floor) 
	{
		super();
		this.number = number;
		this.floor = floor;
	}
	
	
	
	
}
