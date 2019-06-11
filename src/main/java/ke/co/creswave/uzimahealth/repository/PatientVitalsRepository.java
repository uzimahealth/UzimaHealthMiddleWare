package ke.co.creswave.uzimahealth.repository;

import org.springframework.data.repository.CrudRepository;

import ke.co.creswave.uzimahealth.model.Patient;
import ke.co.creswave.uzimahealth.model.PatientVitals;

public interface PatientVitalsRepository extends CrudRepository<PatientVitals, Long>{

}
