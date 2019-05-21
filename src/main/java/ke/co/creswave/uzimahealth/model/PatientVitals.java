package ke.co.creswave.uzimahealth.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patient_vitals")
public class PatientVitals {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "patient_id")
	private long patientId;

	@Column(name = "height")
	private double height;

	@Column(name = "weight")
	private double weight;

	@Column(name = "calculated_bmi")
	private double calculatedBMI;

	@Column(name = "temperature")
	private double temperature;

	@Column(name = "pulse")
	private String pulse;

	@Column(name = "respiratory_rate")
	private String respiratoryRate;

	@Column(name = "blood_pressure")
	private String bloodPressure;

	@Column(name = "blood_oxygen_saturation")
	private double bloodOxygenSaturation;

	@Column(name = "date_taken")
	private Date dateTaken;

	@Column(name = "taken_by")
	private long takenBy;

	@Column(name = "visit_id")
	private int visitId;

	@Column(name = "institution_id")
	private int institutionId;
	
	@Column(name = "blood_group")
	private String bloodGroup;
	

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public int getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(int institutionId) {
		this.institutionId = institutionId;
	}

	public int getVisitId() {
		return visitId;
	}

	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getCalculatedBMI() {
		return calculatedBMI;
	}

	public void setCalculatedBMI(double calculatedBMI) {
		this.calculatedBMI = calculatedBMI;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public String getPulse() {
		return pulse;
	}

	public void setPulse(String pulse) {
		this.pulse = pulse;
	}

	public String getRespiratoryRate() {
		return respiratoryRate;
	}

	public void setRespiratoryRate(String respiratoryRate) {
		this.respiratoryRate = respiratoryRate;
	}

	public String getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public double getBloodOxygenSaturation() {
		return bloodOxygenSaturation;
	}

	public void setBloodOxygenSaturation(double bloodOxygenSaturation) {
		this.bloodOxygenSaturation = bloodOxygenSaturation;
	}

	public Date getDateTaken() {
		return dateTaken;
	}

	public void setDateTaken(Date dateTaken) {
		this.dateTaken = dateTaken;
	}

	public long getTakenBy() {
		return takenBy;
	}

	public void setTakenBy(long takenBy) {
		this.takenBy = takenBy;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

}
