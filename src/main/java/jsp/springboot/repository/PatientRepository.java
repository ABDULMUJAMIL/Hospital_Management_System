package jsp.springboot.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jsp.springboot.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
	
	//4.Fetch the patient Details by Phone Number
	Optional<Patient> findByPhoneNumber(Long phoneNumber);
	
	//5.Fetch the Patient Details By Patient Age GreaterThan value
	List<Patient> findByPatientAgeGreaterThan(Integer patientAge);
	
	//6.Fetch Patient by Appointment ID
	@Query("""
		    SELECT a.patient
		    FROM Appointment a
		    WHERE a.appointmentId = :appointmentId
		""")
		Optional<Patient> fetchPatientByAppointmentId(@Param("appointmentId") Integer appointmentId);

	
	//7.Fetch Patient By Medical Records Id
	@Query("""
		    SELECT m.patient
		    FROM MedicalRecord m
		    WHERE m.recordId = :recordId
		""")
		Optional<Patient> fetchPatientByMedicalRecordId(@Param("recordId") Long recordId);
}
