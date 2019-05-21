package ke.co.creswave.uzimahealth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medical_facilities")
public class MedicalFacility {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String facilityType;

	private String facilityName;

	private double facilityLatitude;

	private double facilityLongitude;
	
	private String facilityLocation;
	
	public String getFacilityLocation() {
		return facilityLocation;
	}

	public void setFacilityLocation(String facilityLocation) {
		this.facilityLocation = facilityLocation;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public double getFacilityLatitude() {
		return facilityLatitude;
	}

	public void setFacilityLatitude(double facilityLatitude) {
		this.facilityLatitude = facilityLatitude;
	}

	public double getFacilityLongitude() {
		return facilityLongitude;
	}

	public void setFacilityLongitude(double facilityLongitude) {
		this.facilityLongitude = facilityLongitude;
	}

}
