package jsp.springboot.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jsp.springboot.entity.AppointmentStatus;
import jsp.springboot.entity.Department;
import jsp.springboot.entity.Doctor;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;
import jsp.springboot.repository.DoctorRepository;

@Repository
public class DoctorDao {
	@Autowired
	private DoctorRepository repository;

	@Autowired
	private DepartmentDao departmentDao;

	// 1.Add doctor
	public Doctor addDoctor(Doctor doctor) {

		if (doctor.getDepartment() == null || doctor.getDepartment().getDepartmentId() == 0) {

			throw new NoRecordAvailableException("Department Id must be provided");
		}

		if (doctor.getAvailableDays() == null) {
			doctor.setAvailableDays(new ArrayList<>());
		}

		Department department = departmentDao.fetchDepartmentById(doctor.getDepartment().getDepartmentId());

		doctor.setDepartment(department);

		return repository.save(doctor);
	}

	// 2.fetch All Doctors
	public List<Doctor> getAllDoctors() {
		List<Doctor> doctors = repository.findAll();
		if (!doctors.isEmpty()) {
			return doctors;
		} else {
			throw new NoRecordAvailableException("Database is Empty!!");
		}
	}

	// 3.fetch Doctor By Id
	public Doctor fetchDoctorById(Long id) {
		Optional<Doctor> opt = repository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new IdNotFoundException("Id is Not Present in the DataBase !!");
		}
	}

	// 4.fetch Doctor By Specialization
	public List<Doctor> fetchDoctorBySpecialization(String specialization) {
		List<Doctor> doctors = repository.findBySpecialization(specialization);
		if (!doctors.isEmpty()) {
			return doctors;
		} else {
			throw new NoRecordAvailableException(
					"There is No Doctors Are there by " + specialization + " specialization.");
		}
	}

	// 5.fetch Doctors By Department Name
	public List<Doctor> fetchDoctorsByDepartmentName(String departmentName) {
		List<Doctor> doctors = repository.fetchDoctorByDepartmentName(departmentName);
		if (!doctors.isEmpty()) {
			return doctors;
		} else {
			throw new NoRecordAvailableException(
					"There is No Doctors Are there by " + departmentName + " department Name.");
		}
	}

	// 6.fetch Doctor By Patient id
	public List<Doctor> fetchDoctorsByPatientId(Long patientId) {
		List<Doctor> doctors = repository.fetchDoctorByPatientId(patientId);
		if (!doctors.isEmpty()) {
			return doctors;
		} else {
			throw new NoRecordAvailableException("There are no doctors for patient id " + patientId);
		}
	}

	// 7.fetch Doctors by Appointment Status
	public List<Doctor> fetchDoctorsByAppointmentStatus(AppointmentStatus status) {
		List<Doctor> doctors = repository.fetchDoctorByAppointmentStatus(status);
		if (!doctors.isEmpty()) {
			return doctors;
		} else {
			throw new NoRecordAvailableException("There is No Doctors Are there by " + status + " AppointmentStatus.");
		}
	}

	// 8.fetch Doctors by Available Days
	public List<Doctor> fetchDoctorsByAvailableDays(String day) {
		List<Doctor> doctors = repository.fetchDoctorsByAvailableDays(day);
		if (!doctors.isEmpty()) {
			return doctors;
		} else {
			throw new NoRecordAvailableException("There is No Doctors Are Available  " + day + " .");
		}
	}

	// 9.update Doctor Info
	public String updateDoctor(Doctor doctor) {
		// CASE 3
		if (doctor.getDoctorId() == 0)
			throw new IdNotFoundException("Id must be passed to Update the record!!");
		// CASE 1
		Optional<Doctor> opt = repository.findById(doctor.getDoctorId());
		if (opt.isPresent()) {
			repository.save(doctor);
			return "Doctor Record Updated!!";
		}
		// CASE 2
		else
			throw new IdNotFoundException("Id Does Not exists in dataBase!! by id " + doctor.getDoctorId());
	}

	// 10.delete Doctor by Id
	public String deleteDoctor(Long id) {
		Optional<Doctor> opt = repository.findById(id);
		if (opt.isPresent()) {
			repository.delete(opt.get());
			return "SuccessFull!!";
		} else
			throw new IdNotFoundException(id + "  Id is not exists in the DataBase!!.");
	}

}