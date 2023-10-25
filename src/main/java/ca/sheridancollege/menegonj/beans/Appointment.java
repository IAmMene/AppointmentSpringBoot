package ca.sheridancollege.menegonj.beans;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Appointment {
	private Long id; 
	private String firstName; 
	private String email; 
	private LocalDate appointmentDate; 
	private LocalTime appointmentTime; 
}
