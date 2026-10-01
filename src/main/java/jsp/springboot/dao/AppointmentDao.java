package jsp.springboot.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jsp.springboot.entity.Appointment;
import jsp.springboot.entity.AppointmentStatus;
import jsp.springboot.entity.Doctor;
import jsp.springboot.entity.Patient;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;
import jsp.springboot.repository.AppointmentRepository;

@Repository
public class AppointmentDao {
	@Autowired
	private AppointmentRepository repository;

	@Autowired
	private DoctorDao doctorDao;

	@Autowired
	private PatientDao patientDao;

	public Appointment bookAppointment(Appointment appointment) {

	    if (appointment.getDoctor() == null || appointment.getDoctor().getDoctorId() == 0) {
	        throw new NoRecordAvailableException("DoctorId must be provided");
	    }

	    if (appointment.getPatient() == null || appointment.getPatient().getPatientId() == null) {
	        throw new NoRecordAvailableException("PatientId must be provided");
	    }

	    if (appointment.getAppointmentDateTime() == null) {
	        throw new NoRecordAvailableException("Appointment DateTime must be provided");
	    }

	    Doctor doctor = doctorDao.fetchDoctorById(
	            appointment.getDoctor().getDoctorId());

	    Patient patient = patientDao.fetchPatientById(
	            appointment.getPatient().getPatientId());

	    LocalDate appointmentDate =
	            appointment.getAppointmentDateTime().toLocalDate();

	    if (repository.existsDoctorAppointmentOnDate(
	            doctor.getDoctorId(), appointmentDate)) {
	        throw new NoRecordAvailableException(
	                "Doctor already has an appointment on " + appointmentDate);
	    }

	    if (repository.existsPatientAppointmentOnDate(
	            patient.getPatientId(), appointmentDate)) {
	        throw new NoRecordAvailableException(
	                "Patient already has an appointment on " + appointmentDate);
	    }

	    appointment.setDoctor(doctor);
	    appointment.setPatient(patient);

	    // IMPORTANT
	    appointment.setStatus(AppointmentStatus.BOOKED);

	    return repository.save(appointment);
	}

	// 2.Fetch All Appointment Details
	public List<Appointment> getAllAppointments() {
		List<Appointment> appointments = repository.findAll();
		if (!appointments.isEmpty()) {
			return appointments;
		} else {
			throw new NoRecordAvailableException("Database is Empty!!");
		}
	}

	// 3.Fetch Appointments by AppointmentId
	public Appointment fetchAppointmentById(Integer appointmentId) {
		Optional<Appointment> opt = repository.findById(appointmentId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new IdNotFoundException("Id Is Not present in the DataBase!!");
		}
	}

	// 4.Fetch Appointment By Date
	public List<Appointment> fetchAppointmentByDate(LocalDateTime dateTime) {
		List<Appointment> appointments = repository.findByAppointmentDateTime(dateTime);
		if (!appointments.isEmpty()) {
			return appointments;
		} else {
			throw new NoRecordAvailableException("There is No Appointments details found by " + dateTime + "  Date.");
		}
	}

	// 5.Fetch Appointment by DoctorId
	public List<Appointment> fetchAppointmentByDoctorId(Long doctorId) {
		List<Appointment> appointments = repository.fetchAppointmentByDoctorId(doctorId);
		if (!appointments.isEmpty()) {
			return appointments;
		} else {
			throw new NoRecordAvailableException("There is No Appointments details found by Doctor Id" + doctorId);
		}
	}

	// 6.Fetch Appointment by Patient Id
	public List<Appointment> fetchAppointmentByPatientId(Integer patientId) {
		List<Appointment> appointments = repository.fetchAppointmentByPatientId(patientId);
		if (!appointments.isEmpty()) {
			return appointments;
		} else {
			throw new NoRecordAvailableException("There is No Appointments details found by Patient Id" + patientId);
		}
	}

	// 7.Fetch Appointment By Appointment Status
	public List<Appointment> fetchAppointmentsByAppointmentStatus(AppointmentStatus status) {
		List<Appointment> appointments = repository.fetchAppointmentByStatus(status);
		if (!appointments.isEmpty()) {
			return appointments;
		} else {
			throw new NoRecordAvailableException(
					"There is No Appointments details found by Appointment Status " + status);
		}
	}

	// 8.Cancel Appointment
	public String cancelAppointment(Integer appointmentId) {
		Optional<Appointment> opt = repository.findById(appointmentId);
		
		if (opt.isPresent()) {
			Appointment appointment=opt.get();
			if(appointment.getStatus()!=AppointmentStatus.BOOKED) {
				throw new NoRecordAvailableException("Only Booked Appointment Can be Canceled!!");
			}
			else {
				appointment.setStatus(AppointmentStatus.CANCELED);
				repository.save(appointment);
				return "Canceled SuccessFull!!";
			}
		} else
			throw new IdNotFoundException(appointmentId + "  Id is not exists in the DataBase!!.");
	}

	// 9.Update the Appointment
	public String updateAppointment(Appointment appointment) {
		// CASE 3
		if (appointment.getAppointmentId() == null)
			throw new IdNotFoundException("Id must be passed to Update the record!!");
		// CASE 1
		Optional<Appointment> opt = repository.findById(appointment.getAppointmentId());
		if (opt.isPresent()) {
			repository.save(appointment);
			return "Appointment Record Updated!!";
		}
		// CASE 2
		else
			throw new IdNotFoundException("Id Does Not exists in dataBase!! by id " + appointment.getAppointmentId());
	}

}
