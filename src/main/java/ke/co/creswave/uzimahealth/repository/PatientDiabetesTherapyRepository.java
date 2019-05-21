package ke.co.creswave.uzimahealth.repository;

import org.springframework.data.repository.CrudRepository;

import ke.co.creswave.uzimahealth.model.Doctor;
import ke.co.creswave.uzimahealth.model.PatientDiabetesTherapy;

public interface PatientDiabetesTherapyRepository extends CrudRepository<PatientDiabetesTherapy, Long>{

}
