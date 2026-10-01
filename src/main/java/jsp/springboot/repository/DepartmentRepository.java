package jsp.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.springboot.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	//Fetch Department By department Name
	public Optional<Department> findByDepartmentName(String departmentName);
	
	 boolean existsByDepartmentName(String departmentName);
}
