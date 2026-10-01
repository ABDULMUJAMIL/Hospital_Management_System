package jsp.springboot.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.springboot.entity.Appointment;
import jsp.springboot.entity.AppointmentStatus;
import jsp.springboot.entity.Doctor;
import jsp.springboot.entity.MedicalRecord;
import jsp.springboot.entity.Patient;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;
import jsp.springboot.repository.AppointmentRepository;
import jsp.springboot.repository.MedicalRecordRepository;

@Repository
public class MedicalRecordDao {
	@Autowired
	private MedicalRecordRepository repository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private DoctorDao doctorDao;

	@Autowired
	private PatientDao patientDao;

	public MedicalRecord createRecord(MedicalRecord record) {

	    if (record.getDoctor() == null || record.getDoctor().getDoctorId() == 0) {
	        throw new NoRecordAvailableException("DoctorId must be provided");
	    }

	    if (record.getPatient() == null || record.getPatient().getPatientId() == null) {
	        throw new NoRecordAvailableException("PatientId must be provided");
	    }

	    if (record.getVisitDate() == null) {
	        throw new NoRecordAvailableException("Visit date must be provided");
	    }

	    Doctor doctor = doctorDao.fetchDoctorById(record.getDoctor().getDoctorId());
	    Patient patient = patientDao.fetchPatientById(record.getPatient().getPatientId());

	    Optional<Appointment> optionalAppointment =
	            appointmentRepository.findAppointmentForMedicalRecord(
	                    doctor.getDoctorId(),
	                    patient.getPatientId(),
	                    record.getVisitDate());

	    if (optionalAppointment.isEmpty()) {
	        throw new NoRecordAvailableException("No appointment found for this doctor and patient on given date");
	    }

	    Appointment appointment = optionalAppointment.get();

	    if (appointment.getStatus() != AppointmentStatus.COMPLETED) {
	        throw new NoRecordAvailableException("Medical record can only be created when appointment is COMPLETED");
	    }

	    record.setDoctor(doctor);
	    record.setPatient(patient);

	    return repository.save(record);
	}


	// 2.fetch All records
	public List<MedicalRecord> getAllMedicalRecords() {
		List<MedicalRecord> records = repository.findAll();
		if (!records.isEmpty()) {
			return records;
		} else {
			throw new NoRecordAvailableException("Database is Empty!!");
		}
	}

	// 3.Fetch Record By Id
	public MedicalRecord fetchMedicalRecordById(Long recordId) {
		Optional<MedicalRecord> opt = repository.findById(recordId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new IdNotFoundException("Id Is Not present in the DataBase!!");
		}
	}

	// 4.Fetch Record By PatientId
	public List<MedicalRecord> fetchRecordByPatientId(Integer patientId) {
		List<MedicalRecord> records = repository.fetchMedicalRecordByPatientId(patientId);
		if (!records.isEmpty()) {
			return records;
		} else {
			throw new NoRecordAvailableException("There is No Patient details found by " + patientId + "  PatientId.");
		}
	}

	// 5.Fetch Medical Record By DoctorId
	public List<MedicalRecord> fetchRecordByDoctorId(Long doctorId) {
		List<MedicalRecord> records = repository.fetchMedicalRecordByDoctorId(doctorId);
		if (!records.isEmpty()) {
			return records;
		} else {
			throw new NoRecordAvailableException("There is No Patient details found by " + doctorId + "  DoctorId.");
		}
	}

	// 6.Fetch Record By Appointment
	public List<MedicalRecord> fetchMedicalRecordByAppointmentId(Integer appointmentId) {
		Optional<Appointment> opt = appointmentRepository.findById(appointmentId);
		if (opt.isEmpty()) {
				throw new IdNotFoundException("Id not found!!");
			}
		Appointment appointment = opt.get();
		if (appointment.getStatus() != AppointmentStatus.COMPLETED) {
			throw new NoRecordAvailableException("No meddical record Found this Appointment");
		}
		List<MedicalRecord> optional = repository
				.fetchMedicalRecordByPatientId(appointment.getPatient().getPatientId());
		if(optional.isEmpty()) {
			throw new NoRecordAvailableException("There is nO Medical Record Found!!");
		}
		return optional;
	}

	// 7.Fetch Medical Record By VisitDate
	public List<MedicalRecord> fetchRecordByVisitDate(LocalDate date) {
		List<MedicalRecord> records = repository.fetchRecordByVisitDate(date);
		if (!records.isEmpty()) {
			return records;
		} else {
			throw new NoRecordAvailableException("There is No Patient details found by Date " + date);
		}
	}

	// 8.Delete Medical Record
	public String deleteMedicalRecord(Long recordId) {
		Optional<MedicalRecord> opt = repository.findById(recordId);
		if (opt.isPresent()) {
			repository.delete(opt.get());
			return "SuccessFull!!";
		} else
			throw new IdNotFoundException(recordId + "  Id is not exists in the DataBase!!.");
	}

	public MedicalRecord updateMedicalRecord(MedicalRecord record) {

	    if (record.getRecordId() == null)
	        throw new IdNotFoundException("Id must be passed to Update the record!!");

	    Optional<MedicalRecord> opt = repository.findById(record.getRecordId());

	    if (opt.isPresent()) {
	        return repository.save(record);  // 🔥 return managed entity
	    } else {
	        throw new IdNotFoundException(
	            "Id Does Not exist in database by id " + record.getRecordId());
	    }
	}

}
