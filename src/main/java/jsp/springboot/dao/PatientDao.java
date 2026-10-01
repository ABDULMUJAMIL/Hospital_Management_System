package jsp.springboot.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jsp.springboot.entity.Patient;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;
import jsp.springboot.repository.PatientRepository;

@Repository
public class PatientDao {
	@Autowired
	private PatientRepository repository;

	// 1.Register the Patient
	public Patient registerPatient(Patient patient) {
		return repository.save(patient);
	}

	// 2.Fetch All Patients Details from database
	public List<Patient> getAllPatients() {
		List<Patient> patients = repository.findAll();
		if (!patients.isEmpty()) {
			return patients;
		} else {
			throw new NoRecordAvailableException("Database is Empty!!");
		}
	}

	// 3.Fetch the Patient Details By Id
	public Patient fetchPatientById(Integer id) {
		Optional<Patient> opt = repository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new IdNotFoundException("Id Is Not present in the DataBase!!");
		}
	}

	// 4.Fetch Patient By Phone Number
	public Patient fetchPatientByPhoneNumber(Long phoneNumber) {
		Optional<Patient> opt = repository.findByPhoneNumber(phoneNumber);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new NoRecordAvailableException(
					"There is No Patient details found by " + phoneNumber + "  phone Number.");
		}
	}

	// 5.fetch patient details by age greater than of given age
	public List<Patient> fetchPatientsByAgeGreaterThan(Integer patientAge) {
		List<Patient> patients = repository.findByPatientAgeGreaterThan(patientAge);
		if (!patients.isEmpty()) {
			return patients;
		} else {
			throw new NoRecordAvailableException(
					"There is No Patient details found by age Greater Than of  " + patientAge);
		}
	}

	// 6.Fetch Patients By Appointment ID
	public Patient fetchPatientByAppointmentId(Integer appointmentId) {
		Optional<Patient> opt = repository.fetchPatientByAppointmentId(appointmentId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new NoRecordAvailableException(
					"There is No Patient details found by Appointment ID   " + appointmentId);
		}
	}

	// 7.Fetch patients by medical records
	public Patient fetchPatientByMedicalRecord(Long recordId) {
		Optional<Patient> opt = repository.fetchPatientByMedicalRecordId(recordId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new NoRecordAvailableException("There is No Patient details found by Medical RecordId  " + recordId);
		}
	}

	// 8.Update the Patient Informations by Id
	public String updatePatient(Patient patient) {
		// CASE 3
		if (patient.getPatientId() == null)
			throw new IdNotFoundException("Id must be passed to Update the record!!");
		// CASE 1
		Optional<Patient> opt = repository.findById(patient.getPatientId());
		if (opt.isPresent()) {
			repository.save(patient);
			return "Patient Record Updated!!";
		}
		// CASE 2
		else
			throw new IdNotFoundException("Id Does Not exists in dataBase!! by id " + patient.getPatientId());
	}

	// 9.Delete Patient Info
	public String deletePatient(Integer patientId) {
		Optional<Patient> opt = repository.findById(patientId);
		if (opt.isPresent()) {
			repository.delete(opt.get());
			return "SuccessFull!!";
		} else
			throw new IdNotFoundException(patientId + "  Id is not exists in the DataBase!!.");
	}

}
