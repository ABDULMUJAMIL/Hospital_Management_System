package jsp.springboot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Prescription;
import jsp.springboot.service.PrescriptionService;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {
	@Autowired
	private PrescriptionService service;

	// 1.create prescription(Add prescription record into the database)
	@PostMapping
	public ResponseEntity<ResponseStructure<Prescription>> createPrescription(@RequestBody Prescription prescription) {
		return service.createPrescription(prescription);
	}

	// 2.Fetch all Prescription record from the database
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Prescription>>> getAllPrescriptions() {
		return service.getAllPrescriptions();
	}

	// 3.Fetch Prescription By Id
	@GetMapping("/{presciptionId}")
	public ResponseEntity<ResponseStructure<Prescription>> fetchPrescriptionById(@PathVariable Integer presciptionId) {
		return service.fetchPrescriptionById(presciptionId);
	}

	// 4.Fetch Prescription By Medical record Id
	@GetMapping("/recordId/{recordId}")
	public ResponseEntity<ResponseStructure<Prescription>> fetchPrescriptionByMedicalRecordId(
			@PathVariable Long recordId) {
		return service.fetchPrescriptionByMedicalRecordId(recordId);
	}

	// 5.Fetch Prescription By Patient Id
	@GetMapping("/patientId/{patientId}")
	public ResponseEntity<ResponseStructure<List<Prescription>>> fetchPrescriptionsByPatientId(
			@PathVariable Integer patientId) {
		return service.fetchPrescriptionsByPatientId(patientId);
	}

}
