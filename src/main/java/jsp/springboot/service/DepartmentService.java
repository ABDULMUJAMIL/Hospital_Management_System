package jsp.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.springboot.dao.DepartmentDao;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Department;
@Service
public class DepartmentService {
	@Autowired
	private DepartmentDao dao;
	
	//1.Create Department
	public ResponseEntity<ResponseStructure<Department>> createDepartment(Department department ) {

		ResponseStructure<Department> respose = new ResponseStructure<Department>();
		respose.setStatusCode(HttpStatus.CREATED.value());
		respose.setMessage("Department Record Created !!");
		respose.setData(dao.createDepartment(department));

		return new ResponseEntity<ResponseStructure<Department>>(respose, HttpStatus.CREATED);
	}
	
	//2.Fetch All department 
	public ResponseEntity<ResponseStructure<List<Department>>> fetchAllDepartment(){
		
		ResponseStructure<List<Department>> response=new ResponseStructure<List<Department>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("All Department Data is Fetched");
		response.setData(dao.fetchAllDepartment());
		
		return new ResponseEntity<ResponseStructure<List<Department>>>(response, HttpStatus.OK);
	}
	
	//3.fetch department by Id
	public ResponseEntity<ResponseStructure<Department>> fetchDepartmentById(Long id){
		
		ResponseStructure<Department> response = new ResponseStructure<Department>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Department Data is Fetched Based on id ,from the DB");
		response.setData(dao.fetchDepartmentById(id));
		return new ResponseEntity<ResponseStructure<Department>>(response, HttpStatus.OK);
	}
	
	//4.Update Department
	public ResponseEntity<ResponseStructure<String>> updateDepartment(Department department){
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Department Record Updated!!");
		response.setData(dao.updateDepartment(department));
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
	}
	
	//5.Delete Department
	public ResponseEntity<ResponseStructure<String>> deleteDepartment(Long id) {
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Department Record Deleted!! by id " + id);
		response.setData(dao.deleteDepartment(id));
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
	}
	
	//6.Fetch Department By Department Name
	public ResponseEntity<ResponseStructure<Department>> fetchDepartmentByDepartmentName(String departmentName){
		ResponseStructure<Department> response=new ResponseStructure<Department>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Fecthed Department Data based on : " + departmentName + "  department name");
		response.setData(dao.fetchDepartmentByDepartmentName(departmentName));
		return new ResponseEntity<ResponseStructure<Department>>(response, HttpStatus.OK);
	}
}
