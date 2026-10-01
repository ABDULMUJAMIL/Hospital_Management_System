package jsp.springboot.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.springboot.dao.AppointmentDao;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Appointment;
import jsp.springboot.entity.AppointmentStatus;

@Service
public class AppointmentService {
	@Autowired
	private AppointmentDao dao;
	
	//1.Book Appointment
		public ResponseEntity<ResponseStructure<Appointment>> bookAppointment(Appointment appointment) {
			ResponseStructure<Appointment> response=new ResponseStructure<Appointment>();
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setMessage("Appointment Booked!!");
			response.setData(dao.bookAppointment(appointment));
			return new ResponseEntity<ResponseStructure<Appointment>>(response, HttpStatus.CREATED);
		}
		
		//2.Fetch All Appointment Details
		public ResponseEntity<ResponseStructure<List<Appointment>>> getAllAppointments(){
			ResponseStructure<List<Appointment> >response = new ResponseStructure<List<Appointment>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("All Appointment Data is Fetched");
			response.setData(dao.getAllAppointments());
			return new ResponseEntity<ResponseStructure<List<Appointment>>>(response, HttpStatus.OK);

		}
		
		//3.Fetch Appointments by AppointmentId
		public ResponseEntity<ResponseStructure<Appointment>> fetchAppointmentById(Integer appointmentId) {
			ResponseStructure<Appointment> response=new ResponseStructure<Appointment>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Appointment details is fetched based on the AppointmentId "+appointmentId);
			response.setData(dao.fetchAppointmentById(appointmentId));
			return new ResponseEntity<ResponseStructure<Appointment>>(response, HttpStatus.OK);
		}
		
		//4.Fetch Appointment By Date 
		public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAppointmentByDate(LocalDateTime dateTime){
			ResponseStructure<List<Appointment> >response = new ResponseStructure<List<Appointment>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Appointment Data is Fetched based onthe appointment Date " +dateTime);
			response.setData(dao.fetchAppointmentByDate(dateTime));
			return new ResponseEntity<ResponseStructure<List<Appointment>>>(response, HttpStatus.OK);
		}
		
		//5.Fetch Appointment by DoctorId
		public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAppointmentByDoctorId(Long doctorId){
			ResponseStructure<List<Appointment> >response = new ResponseStructure<List<Appointment>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Appointment Data is Fetched based on the Doctor ID " +doctorId);
			response.setData(dao.fetchAppointmentByDoctorId(doctorId));
			return new ResponseEntity<ResponseStructure<List<Appointment>>>(response, HttpStatus.OK);
		}
		
		//6.Fetch Appointment by Patient Id
		public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAppointmentByPatientId(Integer patientId){
			ResponseStructure<List<Appointment> >response = new ResponseStructure<List<Appointment>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Appointment Data is Fetched based on the Patient ID " +patientId);
			response.setData(dao.fetchAppointmentByPatientId(patientId));
			return new ResponseEntity<ResponseStructure<List<Appointment>>>(response, HttpStatus.OK);
		}
		
		//7.Fetch Appointment By Appointment Status
		public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAppointmentsByAppointmentStatus(AppointmentStatus status){
			ResponseStructure<List<Appointment> >response = new ResponseStructure<List<Appointment>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Appointment Data is Fetched based on the Appointment Status  " +status);
			response.setData(dao.fetchAppointmentsByAppointmentStatus(status));
			return new ResponseEntity<ResponseStructure<List<Appointment>>>(response, HttpStatus.OK);
		}
		
		//8.Cancel Appointment 
		public ResponseEntity<ResponseStructure<String>> cancelAppointment(Integer appointmentId) {
			ResponseStructure<String> response=new ResponseStructure<String>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Appointment details is Canceled based on the AppointmentId "+appointmentId);
			response.setData(dao.cancelAppointment(appointmentId));
			return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
		}
		
		//9.Update the Appointment
		public ResponseEntity<ResponseStructure<String>> updateAppointment(Appointment appointment) {
			ResponseStructure<String> response=new ResponseStructure<String>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Appointment details is Updated !!");
			response.setData(dao.updateAppointment(appointment));
			return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
		}
		

}
