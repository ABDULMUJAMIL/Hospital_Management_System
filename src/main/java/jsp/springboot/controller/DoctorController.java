package jsp.springboot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.AppointmentStatus;
import jsp.springboot.entity.Doctor;
import jsp.springboot.service.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
	@Autowired
	private DoctorService service;
	
	//1.Add Doctor record
		@PostMapping
		public ResponseEntity<ResponseStructure<Doctor>> addDoctor(@RequestBody Doctor doctor){
			return service.addDoctor(doctor);
		}
		
		//2.Get all Doctors record
		@GetMapping
		public ResponseEntity<ResponseStructure<List<Doctor>>> getAllDoctors(){
			return service.getAllDoctors();
		}
		
		//3. Fetch Doctors By ID
		@GetMapping("/{id}")
		public ResponseEntity<ResponseStructure<Doctor>> fetchDoctorById(@PathVariable Long id){
			return service.fetchDoctorById(id);
		}
		
		//4.Fetch Doctor By Specialization
		@GetMapping("/specialization/{specialization}")
		public ResponseEntity<ResponseStructure<List<Doctor>>> fetchDoctorsBySpecialization(@PathVariable String specialization){
			return service.fetchDoctorsBySpecialization(specialization);
		}
		
		//5.Fetch Doctors By Department Name
		@GetMapping("/departmentName/{departmentName}")
		public ResponseEntity<ResponseStructure<List<Doctor>>> fetchDoctorsByDepartmentName(@PathVariable String departmentName){
			return service.fetchDoctorsByDepartmentName(departmentName);
		}
		
		//6.Fetch Doctor By Patient Id
		@GetMapping("patientId/{id}")
		public ResponseEntity<ResponseStructure<List<Doctor>>> fetchDoctorByPatientId(@PathVariable Long id){
			return service.fetchDoctorByPatientId(id);
		}
		
		//7.Fetch Doctors by AppoientMent Status
		@GetMapping("/status/{status}")
		public ResponseEntity<ResponseStructure<List<Doctor>>> fetchDoctorsByAppointmentStatus(@PathVariable AppointmentStatus status){
			return service.fetchDoctorsByAppointmentStatus(status);
		}
		
		//8.Fetch Doctors By Available days
		@GetMapping("/day/{day}")
		public ResponseEntity<ResponseStructure<List<Doctor>>> fetchDoctorsByAvailableDays(@PathVariable String day){
			return service.fetchDoctorsByAvailableDays(day);
		}
		
		//9.Update Doctor Information
		@PutMapping
		public ResponseEntity<ResponseStructure<String>> updateDoctor(@PathVariable Doctor doctor){
			return service.updateDoctor(doctor);
		}
		
		//10.Delete Doctor Record
		@DeleteMapping("/{id}")
		public ResponseEntity<ResponseStructure<String>> deleteDoctor(@PathVariable Long id) {
			return service.deleteDoctor(id);
		}
}
