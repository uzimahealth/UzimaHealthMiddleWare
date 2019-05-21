package ke.co.creswave.uzimahealth.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patient_diabetes_settings")
public class PatientDiabetesSettings {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private Date dateAdded;
	
	private String hardwareMeter;
	
	private String insulinPump;
	
	private long patientId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getHardwareMeter() {
		return hardwareMeter;
	}

	public void setHardwareMeter(String hardwareMeter) {
		this.hardwareMeter = hardwareMeter;
	}

	public String getInsulinPump() {
		return insulinPump;
	}

	public void setInsulinPump(String insulinPump) {
		this.insulinPump = insulinPump;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	
}
