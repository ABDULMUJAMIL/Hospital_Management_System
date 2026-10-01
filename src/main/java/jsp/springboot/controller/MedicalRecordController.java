package jsp.springboot.controller;

import java.time.LocalDate;
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
import jsp.springboot.entity.MedicalRecord;
import jsp.springboot.service.MedicalRecordService;

@RestController
@RequestMapping("/record")
public class MedicalRecordController {
	@Autowired
	private MedicalRecordService service;

	// 1.Create a medical Record
	@PostMapping
	public ResponseEntity<ResponseStructure<MedicalRecord>> createRecord(@RequestBody MedicalRecord record) {
		return service.createRecord(record);
	}

	// 2.fetch All records
	@GetMapping
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getAllMedicalRecords() {
		return service.getAllMedicalRecords();
	}

	// 3.Fetch Record By Id
	@GetMapping("/{recordId}")
	public ResponseEntity<ResponseStructure<MedicalRecord>> fetchMedicalRecordById(@PathVariable Long recordId) {
		return service.fetchMedicalRecordById(recordId);
	}

	// 4.Fetch Record By PatientId
	@GetMapping("/patientId/{patientId}")
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchRecordByPatientId(
			@PathVariable Integer patientId) {
		return service.fetchRecordByPatientId(patientId);
	}

	// 5.Fetch Medical Record By DoctorId
	@GetMapping("/doctorId/{doctorId}")
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchRecordByDoctorId(@PathVariable Long doctorId) {
		return service.fetchRecordByDoctorId(doctorId);
	}

	// 6.Fetch Record By Appointment
	@GetMapping("/appointmentId/{appointmentId}")
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchRecordByDoctorId(@PathVariable Integer appointmentId) {
		return service.fetchRecordByAppointmentId(appointmentId);
	}


	// 7.Fetch Medical Record By VisitDate
	@GetMapping("/date/{date}")
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchRecordByVisitDate(@PathVariable LocalDate date) {
		return service.fetchRecordByVisitDate(date);
	}

	// 8.Delete Patient Info
	@DeleteMapping("/{recordId}")
	public ResponseEntity<ResponseStructure<String>> deletePatient(@PathVariable Long recordId) {
		return service.deleteRecord(recordId);
	}
	
	//9.Update Medical Record
	@PutMapping
	public ResponseEntity<ResponseStructure<MedicalRecord>> updateMedicalRecord(@RequestBody MedicalRecord record) {
		return service.updateMedicalRecord(record);
	}
}
