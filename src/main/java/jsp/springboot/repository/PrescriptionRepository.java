package jsp.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jsp.springboot.entity.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {

    // 4. Fetch Prescription by MedicalRecord ID
	@Query("SELECT p FROM Prescription p WHERE p.medicalRecord.recordId = :recordId")
	Optional<Prescription> fetchPrescriptionByMedicalRecordId(Long recordId);


    // 5. Fetch Prescription by Patient ID
    @Query("""
           SELECT p
           FROM Prescription p
           WHERE p.medicalRecord.patient.patientId = :patientId
           """)
    List<Prescription> fetchPrescriptionByPatientId(@Param("patientId") Integer patientId);
}

