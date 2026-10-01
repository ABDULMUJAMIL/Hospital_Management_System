package jsp.springboot.entity;
import jakarta.persistence.*;


@Entity
public class Prescription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer presciptionId;
	private String medicine;
	private String instruction;
	
	@OneToOne(mappedBy = "prescription")
	private MedicalRecord medicalRecord;


	public Integer getPresciptionId() {
		return presciptionId;
	}

	public void setPresciptionId(Integer presciptionId) {
		this.presciptionId = presciptionId;
	}

	public String getMedicine() {
		return medicine;
	}

	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}	
}
