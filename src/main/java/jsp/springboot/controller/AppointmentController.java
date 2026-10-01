package jsp.springboot.controller;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Appointment;
import jsp.springboot.entity.AppointmentStatus;
import jsp.springboot.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService service;

	// 1.Book Appointment
	@PostMapping
	public ResponseEntity<ResponseStructure<Appointment>> bookAppointment(@RequestBody Appointment appointment) {
		return service.bookAppointment(appointment);
	}

	// 2.Fetch All Appointment Details
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Appointment>>> getAllAppointments() {
		return service.getAllAppointments();
	}

	// 3.Fetch Appointments by AppointmentId
	@GetMapping("/{appointmentId}")
	public ResponseEntity<ResponseStructure<Appointment>> fetchAppointmentById(@PathVariable Integer appointmentId) {
		return service.fetchAppointmentById(appointmentId);
	}

	// 4.Fetch Appointment By Date
	@GetMapping("date/{dateTime}")
	public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAppointmentByDate(@PathVariable LocalDateTime dateTime) {
		return service.fetchAppointmentByDate(dateTime);
	}

	// 5.Fetch Appointment by DoctorId
	@GetMapping("/doctorId/{doctorId}")
	public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAppointmentByDoctorId(
			@PathVariable Long doctorId) {
		return service.fetchAppointmentByDoctorId(doctorId);
	}

	// 6.Fetch Appointment by Patient Id
	@GetMapping("/patientId/{patientId}")
	public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAppointmentByPatientId(
			@PathVariable Integer patientId) {
		return service.fetchAppointmentByPatientId(patientId);
	}

	// 7.Fetch Appointment By Appointment Status
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAppointmentsByAppointmentStatus(
			@PathVariable AppointmentStatus status) {
		return service.fetchAppointmentsByAppointmentStatus(status);
	}

	// 8.Cancel Appointment
	@PutMapping("/{appointmentId}")
	public ResponseEntity<ResponseStructure<String>> cancelAppointment(@PathVariable Integer appointmentId) {
		return service.cancelAppointment(appointmentId);
	}

	// 9.Update the Appointment
	@PutMapping
	public ResponseEntity<ResponseStructure<String>> updateAppointment(@RequestBody Appointment appointment) {
		return service.updateAppointment(appointment);
	}

}
