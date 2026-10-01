   package jsp.springboot.dao;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.springboot.entity.Department;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;
import jsp.springboot.repository.DepartmentRepository;
@Repository
public class DepartmentDao {
	@Autowired
	private DepartmentRepository departmentRepository;
	
	//1.Create Department (Save Department By single Record)
	public Department createDepartment(Department department) {
		
		///////////////////Business Logic 1  it prevents the duplicate department name insertion////////////////
		if (departmentRepository.existsByDepartmentName(department.getDepartmentName())) {
            throw new NoRecordAvailableException(
                "Department with name '" + department.getDepartmentName() + "' already exists"
            );
        }
		return departmentRepository.save(department);
	}
	
	//2.fetch all department 
	public List<Department> fetchAllDepartment(){
		List<Department> departments = departmentRepository.findAll();
		if (!departments.isEmpty()) {
			return departments;
		} else {
			throw new NoRecordAvailableException("Database is Empty!!");
		}
	}
	
	//3.Fetch department By Id 
	public Department fetchDepartmentById(Long id) {
		Optional<Department> opt = departmentRepository.findById(id);
		if (opt.isPresent())
			return opt.get();
		else
			throw new IdNotFoundException("Id is Not Present in the DataBase !!");
	}  
	
	//4.UpdateDepartment By Id
	public String updateDepartment(Department department) {
		// CASE 3
				if (department.getDepartmentId() == 0)
					throw new IdNotFoundException("Id must be passed to Update the record!!");
				// CASE 1
				Optional<Department> opt = departmentRepository.findById(department.getDepartmentId());
				if (opt.isPresent()) {
					departmentRepository.save(department);
					return "Department Record Updated!!";
				}
				// CASE 2
				else
					throw new IdNotFoundException("Id Does Not exists in dataBase!! by id " + department.getDepartmentId());
	}
	
	///5.Delete Department By Id
	public String deleteDepartment(Long id) {
		Optional<Department> opt = departmentRepository.findById(id);
		if (opt.isPresent()) {
			departmentRepository.delete(opt.get());
			return "SuccessFull!!";
		} else
			throw new IdNotFoundException("Id does not exists in the DataBase!! by id " + id);
	}
	
	//6.Fetch Department By department Name
	public Department fetchDepartmentByDepartmentName(String departmentName) {
		Optional<Department> department=departmentRepository.findByDepartmentName(departmentName);
		if(!department.isEmpty()) {
			return department.get();
		}
		else {
			throw new NoRecordAvailableException("Department are Not present by : " + department);
		}
		
	}
	
}
