package jsp.springboot.repository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jsp.springboot.entity.Appointment;
import jsp.springboot.entity.AppointmentStatus;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	// 4.Fetch the Appointment by date
	List<Appointment> findByAppointmentDateTime(LocalDateTime dateTime);

	// 5.Fetch Appointment By Doctor id
	@Query("SELECT a FROM Appointment a WHERE a.doctor.doctorId = ?1")
	List<Appointment> fetchAppointmentByDoctorId(Long doctorId);

	//6.Fetch Appointment by patient Id
	@Query("SELECT a FROM Appointment a WHERE a.patient.patientId = ?1")
	List<Appointment> fetchAppointmentByPatientId(Integer patientId);
	
	//7.Fetch Appointment by Appointment Status
	@Query("SELECT a FROM Appointment a WHERE a.status = ?1")
	List<Appointment> fetchAppointmentByStatus(AppointmentStatus status);
	
	
	//3. logic for doctor can not have two appointment on same day
	@Query("""
		    select count(a) > 0
		    from Appointment a
		    where a.doctor.doctorId = :doctorId
		    and date(a.appointmentDateTime) = :date
		""")
		boolean existsDoctorAppointmentOnDate(
		    @Param("doctorId") Long doctorId,
		    @Param("date") LocalDate date
		);

	//4. logic by patient can not have two appointment on the same day
	@Query("""
		    select count(a) > 0
		    from Appointment a
		    where a.patient.patientId = :patientId
		    and date(a.appointmentDateTime) = :date
		""")
		boolean existsPatientAppointmentOnDate(
		        @Param("patientId") Integer patientId,
		        @Param("date") LocalDate date
		);

	//5.
	@Query("SELECT a FROM Appointment a WHERE a.doctor.doctorId = :doctorId "
		     + "AND a.patient.patientId = :patientId "
		     + "AND FUNCTION('DATE', a.appointmentDateTime) = :visitDate")
		Optional<Appointment> findAppointmentForMedicalRecord(Long doctorId,
		                                                      Integer patientId,
		                                                      LocalDate visitDate);


}
