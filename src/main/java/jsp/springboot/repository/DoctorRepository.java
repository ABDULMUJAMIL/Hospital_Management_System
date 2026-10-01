package jsp.springboot.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jsp.springboot.entity.AppointmentStatus;
import jsp.springboot.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
	
	//4. Fetch Doctor by Specialization
	public List<Doctor> findBySpecialization(String specialization);
	
	//5.Fetch Doctor by DepartmentName
	@Query("select d from Doctor d where d.department.departmentName=?1")
	public List<Doctor> fetchDoctorByDepartmentName(String departmentName);
	
	//6.Fetch Doctor By Patient Id
	@Query("""
		    SELECT DISTINCT a.doctor
		    FROM Appointment a
		    WHERE a.patient.patientId = :patientId
		""")
		List<Doctor> fetchDoctorByPatientId(@Param("patientId") Long patientId);

	
	//7.Fetch Doctor by AppointmentStatus
	@Query("""
		    SELECT DISTINCT d
		    FROM Doctor d
		    JOIN d.appointments a
		    WHERE a.status = :status
		""")
		List<Doctor> fetchDoctorByAppointmentStatus(
		        @Param("status") AppointmentStatus status);

	
	//8.Fetch Doctors by Available Days
	@Query("SELECT d FROM Doctor d JOIN d.availableDays day WHERE day = :day")
	List<Doctor> fetchDoctorsByAvailableDays(@Param("day") String day);

}
