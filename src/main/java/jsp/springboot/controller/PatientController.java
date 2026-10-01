package jsp.springboot.controller;

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
import jsp.springboot.entity.Patient;
import jsp.springboot.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	private PatientService service;

	// 1.Register the Patient
	@PostMapping
	public ResponseEntity<ResponseStructure<Patient>> registerPatient(@RequestBody Patient patient) {
		return service.registerPatient(patient);
	}

	// 2.Fetch All Patients Details from database
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Patient>>> getAllPatients() {
		return service.getAllPatients();
	}

	// 3.Fetch the Patient Details By Id
	@GetMapping("/patientId/{id}")
	public ResponseEntity<ResponseStructure<Patient>> fetchPatientById(@PathVariable Integer id) {
		return service.fetchPatientById(id);
	}

	// 4.Fetch Patient By Phone Number
	@GetMapping("/phoneNumber/{phoneNumber}")
	public ResponseEntity<ResponseStructure<Patient>> fetchPatientByPhoneNumber(@PathVariable Long phoneNumber) {
		return service.fetchPatientByPhoneNumber(phoneNumber);
	}

	// 5.fetch patient details by age greater than of given age
	@GetMapping("/age/{patientAge}")
	public ResponseEntity<ResponseStructure<List<Patient>>> fetchPatientsByAgeGreaterThan(
			@PathVariable Integer patientAge) {
		return service.fetchPatientsByAgeGreaterThan(patientAge);
	}

	// 6.Fetch Patients By Appointment ID
	@GetMapping("/appointmentId/{appointmentId}")
	public ResponseEntity<ResponseStructure<Patient>> fetchPatientByAppointmentId(@PathVariable Integer appointmentId) {
		return service.fetchPatientByAppointmentId(appointmentId);
	}

	// 7.Fetch patients by medical records
	@GetMapping("/recordId/{recordId}")
	public ResponseEntity<ResponseStructure<Patient>> fetchPatientByMedicalRecord(@PathVariable Long recordId) {
		return service.fetchPatientByMedicalRecord(recordId);
	}

	// 8.Update the Patient Informations by Id
	@PutMapping
	public ResponseEntity<ResponseStructure<String>> updatePatient(@RequestBody Patient patient) {
		return service.updatePatient(patient);
	}

	// 9.Delete Patient Info
	@DeleteMapping("/{patientId}")
	public ResponseEntity<ResponseStructure<String>> deletePatient(@PathVariable Integer patientId) {
		return service.deletePatient(patientId);
	}

}
