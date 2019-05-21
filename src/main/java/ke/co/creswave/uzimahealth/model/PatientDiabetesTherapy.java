package ke.co.creswave.uzimahealth.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patient_diabetes_therapy")
public class PatientDiabetesTherapy {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private Date dateAdded;
	
	private String yearOfDiagnosis;
	
	private Date dateOfDiagnosis;
	
	private String insulinTherapy;
	
	private String bloodGlucoseUnit;
	
	private String carbsMeasuredIn;
	
	private String insulin;
	
	private String pills;
	
	private String hypo;
	
	private String targetRange;
	
	private String hyper;
	
	private String bodyWeightUnit;
	
	private String bodyWeightTarget;
	
	private String a1cUnit;
	
	private long patientId;

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

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

	public String getYearOfDiagnosis() {
		return yearOfDiagnosis;
	}

	public void setYearOfDiagnosis(String yearOfDiagnosis) {
		this.yearOfDiagnosis = yearOfDiagnosis;
	}

	public Date getDateOfDiagnosis() {
		return dateOfDiagnosis;
	}

	public void setDateOfDiagnosis(Date dateOfDiagnosis) {
		this.dateOfDiagnosis = dateOfDiagnosis;
	}

	public String getInsulinTherapy() {
		return insulinTherapy;
	}

	public void setInsulinTherapy(String insulinTherapy) {
		this.insulinTherapy = insulinTherapy;
	}

	public String getBloodGlucoseUnit() {
		return bloodGlucoseUnit;
	}

	public void setBloodGlucoseUnit(String bloodGlucoseUnit) {
		this.bloodGlucoseUnit = bloodGlucoseUnit;
	}

	public String getCarbsMeasuredIn() {
		return carbsMeasuredIn;
	}

	public void setCarbsMeasuredIn(String carbsMeasuredIn) {
		this.carbsMeasuredIn = carbsMeasuredIn;
	}

	public String getInsulin() {
		return insulin;
	}

	public void setInsulin(String insulin) {
		this.insulin = insulin;
	}

	public String getPills() {
		return pills;
	}

	public void setPills(String pills) {
		this.pills = pills;
	}

	public String getHypo() {
		return hypo;
	}

	public void setHypo(String hypo) {
		this.hypo = hypo;
	}

	public String getTargetRange() {
		return targetRange;
	}

	public void setTargetRange(String targetRange) {
		this.targetRange = targetRange;
	}

	public String getHyper() {
		return hyper;
	}

	public void setHyper(String hyper) {
		this.hyper = hyper;
	}

	public String getBodyWeightUnit() {
		return bodyWeightUnit;
	}

	public void setBodyWeightUnit(String bodyWeightUnit) {
		this.bodyWeightUnit = bodyWeightUnit;
	}

	public String getBodyWeightTarget() {
		return bodyWeightTarget;
	}

	public void setBodyWeightTarget(String bodyWeightTarget) {
		this.bodyWeightTarget = bodyWeightTarget;
	}

	public String getA1cUnit() {
		return a1cUnit;
	}

	public void setA1cUnit(String a1cUnit) {
		this.a1cUnit = a1cUnit;
	}
		
}
