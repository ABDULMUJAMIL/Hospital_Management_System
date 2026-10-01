package jsp.springboot.entity;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity 
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long departmentId;
	@Column(unique = true)
	private String departmentName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "department" ,cascade = CascadeType.ALL)
	private List<Doctor> doctors;
	
	
	public long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(long departmentId) { 
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public List<Doctor> getDoctors() {
		return doctors;
	}
	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}
	
	
	
}
