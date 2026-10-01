package jsp.springboot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jsp.springboot.dao.PatientDao;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Patient;

@Service
public class PatientService {
	@Autowired
	private PatientDao dao;
	//1.Register the Patient
	public ResponseEntity<ResponseStructure<Patient>> registerPatient(Patient patient) {
		ResponseStructure<Patient> response=new ResponseStructure<Patient>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Patient Record Registered !!");
		response.setData(dao.registerPatient(patient));
		return new ResponseEntity<ResponseStructure<Patient>>(response, HttpStatus.CREATED);
	}

	// 2.Fetch All Patients Details from database
	public ResponseEntity<ResponseStructure<List<Patient>>> getAllPatients() {
		ResponseStructure<List<Patient> >response = new ResponseStructure<List<Patient>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("All Patient Data is Fetched");
		response.setData(dao.getAllPatients());
		return new ResponseEntity<ResponseStructure<List<Patient>>>(response, HttpStatus.OK);
	}

	// 3.Fetch the Patient Details By Id
	public ResponseEntity<ResponseStructure<Patient>> fetchPatientById(Integer id) {
		ResponseStructure<Patient>response =new ResponseStructure<Patient>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Patient Data is Fetched Based on id from the DB");
		response.setData(dao.fetchPatientById(id));
		return new ResponseEntity<ResponseStructure<Patient>>(response, HttpStatus.OK);

	}

	// 4.Fetch Patient By Phone Number
	public ResponseEntity<ResponseStructure<Patient>> fetchPatientByPhoneNumber(Long phoneNumber) {
		ResponseStructure<Patient>response =new ResponseStructure<Patient>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Patient Data is Fetched Based on Patient Phone Number");
		response.setData(dao.fetchPatientByPhoneNumber(phoneNumber));
		return new ResponseEntity<ResponseStructure<Patient>>(response, HttpStatus.OK);
	}

	// 5.fetch patient details by age greater than of given age
	public ResponseEntity<ResponseStructure<List<Patient>>> fetchPatientsByAgeGreaterThan(Integer patientAge) {
		ResponseStructure<List<Patient>>response = new ResponseStructure<List<Patient>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Patient Data is Fetched where age Greater Than of "+patientAge);
		response.setData(dao.fetchPatientsByAgeGreaterThan(patientAge));
		return new ResponseEntity<ResponseStructure<List<Patient>>>(response, HttpStatus.OK);
	}

	// 6.Fetch Patients By Appointment ID
	public ResponseEntity<ResponseStructure<Patient>> fetchPatientByAppointmentId(Integer appointmentId) {
		ResponseStructure<Patient>response =new ResponseStructure<Patient>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Patient Data is Fetched Based on Patient Appointment id "+appointmentId);
		response.setData(dao.fetchPatientByAppointmentId(appointmentId));
		return new ResponseEntity<ResponseStructure<Patient>>(response, HttpStatus.OK);
	}

	// 7.Fetch patients by medical records
	public ResponseEntity<ResponseStructure<Patient>> fetchPatientByMedicalRecord(Long recordId) {
		ResponseStructure<Patient>response =new ResponseStructure<Patient>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Patient Data is Fetched Based on Patient medical RecordId "+recordId);
		response.setData(dao.fetchPatientByMedicalRecord(recordId));
		return new ResponseEntity<ResponseStructure<Patient>>(response, HttpStatus.OK);
	}

	// 8.Update the Patient Informations by Id
	public ResponseEntity<ResponseStructure<String>> updatePatient(Patient patient) {
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage(" Record Updated!!");
		response.setData(dao.updatePatient(patient));
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);

	}

	// 9.Delete Patient Info
	public ResponseEntity<ResponseStructure<String>> deletePatient(Integer patientId) {
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Patient Record is Deleted Based On the Id "+patientId);
		response.setData(dao.deletePatient(patientId));
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
	}

}	
