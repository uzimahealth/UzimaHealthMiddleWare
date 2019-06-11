package ke.co.creswave.uzimahealth.repository;

import org.springframework.data.repository.CrudRepository;

import ke.co.creswave.uzimahealth.model.PatientDiabetesMonitoringLog;
import ke.co.creswave.uzimahealth.model.PatientDiabetesSettings;

public interface PatientDiabetesMonitoringLogRepository extends CrudRepository<PatientDiabetesMonitoringLog, Long>{

}
