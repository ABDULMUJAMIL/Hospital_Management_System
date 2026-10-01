package jsp.springboot.repository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jsp.springboot.entity.MedicalRecord;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long>{
		
	//4.Fetch Record by Patient
	@Query("SELECT m FROM MedicalRecord m WHERE m.patient.patientId = ?1")
	List<MedicalRecord> fetchMedicalRecordByPatientId(Integer patientId);
	
	//5.Fetch Record By Doctor
	@Query("SELECT m FROM MedicalRecord m WHERE m.doctor.doctorId = ?1")
	List<MedicalRecord> fetchMedicalRecordByDoctorId(Long doctorId);
	
	//6.Fetch Record by Appointment
	
	//7.fetch record by visit date
	@Query("SELECT mr FROM MedicalRecord mr WHERE mr.visitDate =?1")
	List<MedicalRecord> fetchRecordByVisitDate(LocalDate visitDate);

}
