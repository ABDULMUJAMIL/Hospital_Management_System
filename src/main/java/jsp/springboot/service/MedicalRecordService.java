package jsp.springboot.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jsp.springboot.dao.MedicalRecordDao;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.MedicalRecord;

@Service
public class MedicalRecordService {
	@Autowired
	private MedicalRecordDao dao;

	// 1.Create a medical Record
	public ResponseEntity<ResponseStructure<MedicalRecord>> createRecord(MedicalRecord record) {
		ResponseStructure<MedicalRecord> response = new ResponseStructure<MedicalRecord>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Medical Record Created!!");
		response.setData(dao.createRecord(record));
		return new ResponseEntity<ResponseStructure<MedicalRecord>>(response, HttpStatus.CREATED);
	}

	// 2.fetch All records
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getAllMedicalRecords() {
		ResponseStructure<List<MedicalRecord>> response = new ResponseStructure<List<MedicalRecord>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("All MedicalRecord Data is Fetched");
		response.setData(dao.getAllMedicalRecords());
		return new ResponseEntity<ResponseStructure<List<MedicalRecord>>>(response, HttpStatus.OK);
	}

	// 3.Fetch Record By Id
	public ResponseEntity<ResponseStructure<MedicalRecord>> fetchMedicalRecordById(Long recordId) {
		ResponseStructure<MedicalRecord> response = new ResponseStructure<MedicalRecord>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Medical Record Fetched Based On the RecordId " + recordId);
		response.setData(dao.fetchMedicalRecordById(recordId));
		return new ResponseEntity<ResponseStructure<MedicalRecord>>(response, HttpStatus.OK);
	}

	// 4.Fetch Record By PatientId
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchRecordByPatientId(Integer patientId) {
		ResponseStructure<List<MedicalRecord>> response = new ResponseStructure<List<MedicalRecord>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("MedicalRecord Data is Fetched based on the PatientId " + patientId);
		response.setData(dao.fetchRecordByPatientId(patientId));
		return new ResponseEntity<ResponseStructure<List<MedicalRecord>>>(response, HttpStatus.OK);
	}

	// 5.Fetch Medical Record By DoctorId
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchRecordByDoctorId(Long doctorId) {
		ResponseStructure<List<MedicalRecord>> response = new ResponseStructure<List<MedicalRecord>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("MedicalRecord Data is Fetched based on the DoctorID " + doctorId);
		response.setData(dao.fetchRecordByDoctorId(doctorId));
		return new ResponseEntity<ResponseStructure<List<MedicalRecord>>>(response, HttpStatus.OK);
	}

	// 6.Fetch Record By Appointment
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchRecordByAppointmentId(Integer appointmentId) {
		ResponseStructure<List<MedicalRecord>> response = new ResponseStructure<List<MedicalRecord>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("MedicalRecord Data is Fetched based on the appointemntId " + appointmentId);
		response.setData(dao.fetchMedicalRecordByAppointmentId(appointmentId));
		return new ResponseEntity<ResponseStructure<List<MedicalRecord>>>(response, HttpStatus.OK);
	}
	
	// 7.Fetch Medical Record By VisitDate
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchRecordByVisitDate(LocalDate date) {
		ResponseStructure<List<MedicalRecord>> response = new ResponseStructure<List<MedicalRecord>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("MedicalRecord Data is Fetched based on the Visit Date " + date);
		response.setData(dao.fetchRecordByVisitDate(date));
		return new ResponseEntity<ResponseStructure<List<MedicalRecord>>>(response, HttpStatus.OK);
	}
	
	//8.Delete Medical Record
	public ResponseEntity<ResponseStructure<String>> deleteRecord(Long recordId) {
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Medical Record is Deleted Based On the Id "+recordId);
		response.setData(dao.deleteMedicalRecord(recordId));
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
	}
	
	//9.Update Medical Record
	public ResponseEntity<ResponseStructure<MedicalRecord>> updateMedicalRecord(MedicalRecord record) {
		ResponseStructure<MedicalRecord> response = new ResponseStructure<MedicalRecord>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Medical Record is Updated!!");
		response.setData(dao.updateMedicalRecord(record));
		return new ResponseEntity<ResponseStructure<MedicalRecord>>(HttpStatus.OK);
	}
}
