package jsp.springboot.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Department;
import jsp.springboot.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private DepartmentService service;
	
	//1.Create Department
		@PostMapping
		public ResponseEntity<ResponseStructure<Department>> createDepartment(@RequestBody Department department ) {
			return service.createDepartment(department);
		}
		
		//2.Fetch All department 
		@GetMapping
		public ResponseEntity<ResponseStructure<List<Department>>> fetchAllDepartment(){
			return service.fetchAllDepartment();
		}
		
		//3.fetch department by Id
		@GetMapping("/{id}")
		public ResponseEntity<ResponseStructure<Department>> fetchDepartmentById(@PathVariable Long id){
			return service.fetchDepartmentById(id);
		}
		
		//4.Update Department
		@PutMapping
		public ResponseEntity<ResponseStructure<String>> updateDepartment(@RequestBody Department department){
			return service.updateDepartment(department);
		}
		
		//5.Delete Department
		@DeleteMapping("/{id}")
		public ResponseEntity<ResponseStructure<String>> deleteDepartment( @PathVariable Long id) {
			return service.deleteDepartment(id);
		}
		
		//6.Fetch Department By Department Name
		@GetMapping("/departmentName/{departmentName}")
		public ResponseEntity<ResponseStructure<Department>> fetchDepartmentByDepartmentName(@PathVariable String departmentName){
			return service.fetchDepartmentByDepartmentName(departmentName);
		}
}
