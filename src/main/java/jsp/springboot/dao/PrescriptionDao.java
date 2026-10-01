package jsp.springboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.springboot.entity.MedicalRecord;
import jsp.springboot.entity.Prescription;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;
import jsp.springboot.repository.PrescriptionRepository;

@Repository
public class PrescriptionDao {
	@Autowired
	private PrescriptionRepository repository;

	@Autowired
	private MedicalRecordDao medicalRecordDao;

	public Prescription createPrescription(Prescription prescription) {

	    if (prescription.getMedicalRecord() == null 
	        || prescription.getMedicalRecord().getRecordId() == null) {

	        throw new NoRecordAvailableException(
	            "MedicalRecordId must be provided inside medicalRecord object");
	    }

	    // Fetch managed MedicalRecord
	    MedicalRecord record = medicalRecordDao
	            .fetchMedicalRecordById(
	                prescription.getMedicalRecord().getRecordId());

	    // Only one prescription per medical record
	    if (record.getPrescription() != null) {
	        throw new NoRecordAvailableException(
	            "Prescription already exists for this MedicalRecord");
	    }

	    // Attach both sides
	    record.setPrescription(prescription);
	    prescription.setMedicalRecord(record);

	    // 🔥 Save and capture managed entity
	    MedicalRecord updatedRecord = medicalRecordDao.updateMedicalRecord(record);

	    // 🔥 Return managed prescription (ID will not be null)
	    return updatedRecord.getPrescription();
	}

	// 2.Fetch all Prescription record from the database
	public List<Prescription> getAllPrescriptions() {
		List<Prescription> prescriptions = repository.findAll();
		if (!prescriptions.isEmpty()) {
			return prescriptions;
		} else {
			throw new NoRecordAvailableException("Database is Empty!!");
		}
	}

	// 3.Fetch Prescription By Id
	public Prescription fetchPrescriptionById(Integer presciptionId) {
		Optional<Prescription> opt = repository.findById(presciptionId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new IdNotFoundException("Id Is Not present in the DataBase!!");
		}
	}

	// 4.Fetch Prescription By Medical record Id
	public Prescription fetchPrescriptionByMedicalRecordId(Long recordId) {
		Optional<Prescription> opt = repository.fetchPrescriptionByMedicalRecordId(recordId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new NoRecordAvailableException(
					"There is No Prescription details found by " + recordId + "  Medical RecordId.");
		}
	}

	// 5.Fetch Prescription By Patient Id
	public List<Prescription> fetchPrescriptionsByPatientId(Integer patientId) {
		List<Prescription> prescriptions = repository.fetchPrescriptionByPatientId(patientId);
		if (!prescriptions.isEmpty()) {
			return prescriptions;
		} else {
			throw new NoRecordAvailableException(
					"There is No Prscription details found by " + patientId + "  PatientId.");
		}
	}

}
