package jsp.springboot.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jsp.springboot.dao.DoctorDao;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.AppointmentStatus;
import jsp.springboot.entity.Doctor;

@Service
public class DoctorService {
	@Autowired
	private DoctorDao dao;
	
	//1.Add Doctor record
	public ResponseEntity<ResponseStructure<Doctor>> addDoctor(Doctor doctor){
		ResponseStructure<Doctor> response=new ResponseStructure<Doctor>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Doctor Record Added !!");
		response.setData(dao.addDoctor(doctor));

		return new ResponseEntity<ResponseStructure<Doctor>>(response, HttpStatus.CREATED);
	}
	
	//2.Get all Doctors record
	public ResponseEntity<ResponseStructure<List<Doctor>>> getAllDoctors(){
		ResponseStructure<List<Doctor> >response = new ResponseStructure<List<Doctor>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("All Doctors Data is Fetched");
		response.setData(dao.getAllDoctors());
		return new ResponseEntity<ResponseStructure<List<Doctor>>>(response, HttpStatus.OK);
	}
	
	//3. Fetch Doctors By ID
	public ResponseEntity<ResponseStructure<Doctor>> fetchDoctorById(Long id){
		ResponseStructure<Doctor>response =new ResponseStructure<Doctor>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Doctor Data is Fetched Based on id from the DB");
		response.setData(dao.fetchDoctorById(id));
		return new ResponseEntity<ResponseStructure<Doctor>>(response, HttpStatus.OK);
	}
	
	//4.Fetch Doctor By Specialization
	public ResponseEntity<ResponseStructure<List<Doctor>>> fetchDoctorsBySpecialization(String specialization){
		ResponseStructure<List<Doctor>> response=new ResponseStructure<List<Doctor>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Doctors Data is Fetched based on the Specialization " + specialization);
		response.setData(dao.fetchDoctorBySpecialization(specialization));
		return new ResponseEntity<ResponseStructure<List<Doctor>>>(response, HttpStatus.OK);
	}
	
	//5.Fetch Doctors By Department Name
	public ResponseEntity<ResponseStructure<List<Doctor>>> fetchDoctorsByDepartmentName(String departmentName){
		ResponseStructure<List<Doctor>> response=new ResponseStructure<List<Doctor>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Doctors Data is Fetched based on the Department Name " + departmentName);
		response.setData(dao.fetchDoctorsByDepartmentName(departmentName));
		return new ResponseEntity<ResponseStructure<List<Doctor>>>(response, HttpStatus.OK);
	}
	
	//6.Fetch Doctor By Patient Id
	public ResponseEntity<ResponseStructure<List<Doctor>>> fetchDoctorByPatientId(Long id){
		ResponseStructure<List<Doctor>>response =new ResponseStructure<List<Doctor>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Doctor Data is Fetched Based on the Patient id "+id);
		response.setData(dao.fetchDoctorsByPatientId(id));
		return new ResponseEntity<ResponseStructure<List<Doctor>>>(response, HttpStatus.OK);
	}
	
	//7.Fetch Doctors by AppoientMent Status
	public ResponseEntity<ResponseStructure<List<Doctor>>> fetchDoctorsByAppointmentStatus(AppointmentStatus status){
		ResponseStructure<List<Doctor>> response=new ResponseStructure<List<Doctor>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Doctors Data is Fetched based on the Appointment  Status " + status);
		response.setData(dao.fetchDoctorsByAppointmentStatus(status));
		return new ResponseEntity<ResponseStructure<List<Doctor>>>(response, HttpStatus.OK);
	}
	
	//8.Fetch Doctors By Available days
	public ResponseEntity<ResponseStructure<List<Doctor>>> fetchDoctorsByAvailableDays(String day){
		ResponseStructure<List<Doctor>> response=new ResponseStructure<List<Doctor>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Doctors Data is Fetched based on the Doctors Available Day  " + day);
		response.setData(dao.fetchDoctorsByAvailableDays(day));
		return new ResponseEntity<ResponseStructure<List<Doctor>>>(response, HttpStatus.OK);
	}
	
	//9.Update Doctor Information
	public ResponseEntity<ResponseStructure<String>> updateDoctor(Doctor doctor){
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Doctor Record Updated!!");
		response.setData(dao.updateDoctor(doctor));
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
	}
	
	//10.Delete Doctor Record
	public ResponseEntity<ResponseStructure<String>> deleteDoctor(Long id) {
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Doctor Record Deleted!! by id " + id);
		response.setData(dao.deleteDoctor(id));
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
	}
}
