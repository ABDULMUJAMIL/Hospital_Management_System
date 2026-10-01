package jsp.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.springboot.dao.PrescriptionDao;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Prescription;

@Service
public class PrescriptionService {
	@Autowired
	private PrescriptionDao dao;

	// 1.create prescription(Add prescription record into the database)
	public ResponseEntity<ResponseStructure<Prescription>> createPrescription(Prescription prescription) {
		ResponseStructure<Prescription> response = new ResponseStructure<Prescription>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Prescription Record Created !!");
		response.setData(dao.createPrescription(prescription));
		return new ResponseEntity<ResponseStructure<Prescription>>(response, HttpStatus.CREATED);
	}

	// 2.Fetch all Prescription record from the database
	public ResponseEntity<ResponseStructure<List<Prescription>>> getAllPrescriptions() {
		ResponseStructure<List<Prescription>> response = new ResponseStructure<List<Prescription>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("All Prescription Data is Fetched");
		response.setData(dao.getAllPrescriptions());
		return new ResponseEntity<ResponseStructure<List<Prescription>>>(response, HttpStatus.OK);
	}

	// 3.Fetch Prescription By Id
	public ResponseEntity<ResponseStructure<Prescription>> fetchPrescriptionById(Integer presciptionId) {
		ResponseStructure<Prescription> response = new ResponseStructure<Prescription>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Presciption Data is Fetched Based on id from the DB");
		response.setData(dao.fetchPrescriptionById(presciptionId));
		return new ResponseEntity<ResponseStructure<Prescription>>(response, HttpStatus.OK);
	}

	// 4.Fetch Prescription By Medical record Id
	public ResponseEntity<ResponseStructure<Prescription>> fetchPrescriptionByMedicalRecordId(Long recordId) {
		ResponseStructure<Prescription> response = new ResponseStructure<Prescription>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Presciption Data is Fetched Based on the Medical RecordId " + recordId + " from the DB");
		response.setData(dao.fetchPrescriptionByMedicalRecordId(recordId));
		return new ResponseEntity<ResponseStructure<Prescription>>(response, HttpStatus.OK);
	}

	// 5.Fetch Prescription By Patient Id
	public ResponseEntity<ResponseStructure<List<Prescription>>> fetchPrescriptionsByPatientId(Integer patientId) {
		ResponseStructure<List<Prescription>> response = new ResponseStructure<List<Prescription>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Prescription Data is Fetched Based On the PatientId " + patientId);
		response.setData(dao.fetchPrescriptionsByPatientId(patientId));
		return new ResponseEntity<ResponseStructure<List<Prescription>>>(response, HttpStatus.OK);
	}

}
