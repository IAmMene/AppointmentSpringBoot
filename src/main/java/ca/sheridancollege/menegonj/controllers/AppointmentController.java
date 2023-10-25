package ca.sheridancollege.menegonj.controllers;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.menegonj.beans.Appointment;
import ca.sheridancollege.menegonj.database.DatabaseAccess;

@Controller
public class AppointmentController {
	
	List<Appointment> appointmentList = new CopyOnWriteArrayList<>(); 
	
	@Autowired 
	private DatabaseAccess da; 
	
	@GetMapping("/")
	public String index (Model model) {
		model.addAttribute("appointment", new Appointment());  
		model.addAttribute("appointmentList", da.getAllAppointmentList()); 
		//da.insertAppointment(new Appointment());
		return "index"; 
	}
	
	@PostMapping("/insertAppointment")
	public String insertAppointmnet (Model model, @ModelAttribute Appointment appointment) {
		//da.insertAppointment(appointment);
		List<Appointment> existingAppointment = da.getAppointmentById(appointment.getId()); 
		
		if (existingAppointment.isEmpty()) {
			da.insertAppointment(appointment); 
		} 
		model.addAttribute("appointment", new Appointment());
		model.addAttribute("appointmentList", da.getAllAppointmentList()); 
		return "index"; 
	}
	
	@GetMapping("/editAppointmentById/{id}")
	public String editAppointmentById(Model model, @PathVariable Long id, @ModelAttribute Appointment Appointment) {
        Appointment appointment = da.getAppointmentById(id).get(0); 

        model.addAttribute("appointment", appointment);

        // Delete the existing student record
        da.deleteAppointmentById(id);
        // Refresh the student list
        model.addAttribute("appointmenttList", da.getAllAppointmentList());


        return "index";
	}
	
	@GetMapping("/deleteAppointmentById/{id}")
	public String deleteAppointmentById(Model model, @PathVariable Long id) {
		model.addAttribute("appointmentList", da.getAllAppointmentList());
		model.addAttribute("appointment", new Appointment());
		// perform delete operation here
		da.deleteAppointmentById(id);
		return "index";
	}
}


