package ca.sheridancollege.menegonj.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.menegonj.beans.Appointment;

@Repository
public class DatabaseAccess {

	@Autowired 
	protected NamedParameterJdbcTemplate jdbc;
	
	public void insertAppointment(Appointment appointment) {
	    MapSqlParameterSource namedParameters = new MapSqlParameterSource();

	    namedParameters.addValue("firstName", appointment.getFirstName());
	    namedParameters.addValue("email", appointment.getEmail());
	    namedParameters.addValue("appointmentDate", appointment.getAppointmentDate());
	    namedParameters.addValue("appointmentTime", appointment.getAppointmentTime());

	    String query = "INSERT INTO appointment(firstName, email, appointmentDate, appointmentTime) VALUES (:firstName, :email, :appointmentDate, :appointmentTime)";

	    int rowsAffected = jdbc.update(query, namedParameters);

	    if (rowsAffected > 0) {
	        System.out.println("Appointment inserted into the database");
	    }
	}
	
	public List<Appointment> getAllAppointmentList() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource(); 
		String query = "SELECT * FROM appointment";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Appointment>(Appointment.class));
	}
	
	public void deleteAppointmentById(Long id) { 
		MapSqlParameterSource namedParameters = new MapSqlParameterSource(); 
		String query = "DELETE FROM appointment WHERE id = :id"; 
		namedParameters.addValue("id", id);
		if (jdbc.update(query, namedParameters) > 0) {
			System.out.println("Deleted appointment" + id + "from the database."); 
		}
	}
	
	public List<Appointment>getAppointmentById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM appointment WHERE id = :id";
		namedParameters.addValue("id", id);
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Appointment>(Appointment.class));
	}
}



/*You need a method to
insertAppointment an Appointment to a database, as well as getAllAppointments, deleteAppointmentById,
and getAppointmentById. */ 