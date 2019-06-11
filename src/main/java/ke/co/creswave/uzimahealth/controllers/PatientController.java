package ke.co.creswave.uzimahealth.controllers;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ke.co.creswave.uzimahealth.model.ApiResponse;
import ke.co.creswave.uzimahealth.model.Patient;
import ke.co.creswave.uzimahealth.model.PatientDiabetesMonitoringLog;
import ke.co.creswave.uzimahealth.model.PatientDiabetesSettings;
import ke.co.creswave.uzimahealth.model.PatientDiabetesTherapy;
import ke.co.creswave.uzimahealth.model.PatientVitals;
import ke.co.creswave.uzimahealth.repository.PatientDiabetesMonitoringLogRepository;
import ke.co.creswave.uzimahealth.repository.PatientDiabetesSettingsRepository;
import ke.co.creswave.uzimahealth.repository.PatientDiabetesTherapyRepository;
import ke.co.creswave.uzimahealth.repository.PatientRepository;
import ke.co.creswave.uzimahealth.repository.PatientVitalsRepository;

@RestController
@RequestMapping(value = "/patient")
public class PatientController {
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private PatientVitalsRepository patientVitalsRepository;
	@Autowired
	private PatientDiabetesSettingsRepository patientDiabetesSettingsRepository;
	@Autowired
	private PatientDiabetesTherapyRepository patientDiabetesTheraphyRepository;
	@Autowired
	private PatientDiabetesMonitoringLogRepository patientDiabetesMonitoringLogRepository;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public HttpEntity<ApiResponse> registerPatient(
			@Valid @RequestBody Patient uploadRequest, HttpServletRequest request) {
	
		ApiResponse apiResponse = new ApiResponse("Patient registered successfully.", 200);
		patientRepository.save(uploadRequest);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public HttpEntity<ApiResponse> updatePatient(
			@Valid @RequestBody Patient uploadRequest, HttpServletRequest request) {
	
		ApiResponse apiResponse = new ApiResponse("Patient updated successfully.", 200);
		patientRepository.save(uploadRequest);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update/vitals", method = RequestMethod.POST)
	public HttpEntity<ApiResponse> updatePatientVitals(
			@Valid @RequestBody PatientVitals uploadRequest, HttpServletRequest request) {
	
		ApiResponse apiResponse = new ApiResponse("Patient vitals updated successfully.", 200);
		patientVitalsRepository.save(uploadRequest);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/update/diabetessettings", method = RequestMethod.POST)
	public HttpEntity<ApiResponse> updatePatientDiabetesSettings(
			@Valid @RequestBody PatientDiabetesSettings uploadRequest, HttpServletRequest request) {
	
		ApiResponse apiResponse = new ApiResponse("Patient diabetes settings updated successfully.", 200);
		patientDiabetesSettingsRepository.save(uploadRequest);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update/diabetestherapy", method = RequestMethod.POST)
	public HttpEntity<ApiResponse> updatePatientDiabetesTherapy(
			@Valid @RequestBody PatientDiabetesTherapy uploadRequest, HttpServletRequest request) {
	
		ApiResponse apiResponse = new ApiResponse("Patient diabetes therapy updated successfully.", 200);
		patientDiabetesTheraphyRepository.save(uploadRequest);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update/diabetesmonitoringlog", method = RequestMethod.POST)
	public HttpEntity<ApiResponse> updatePatientDiabetesMonitoringLog(
			@Valid @RequestBody PatientDiabetesMonitoringLog uploadRequest, HttpServletRequest request) {
	
		ApiResponse apiResponse = new ApiResponse("Patient diabetes moniotoring log updated successfully.", 200);
		patientDiabetesMonitoringLogRepository.save(uploadRequest);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
}
