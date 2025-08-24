package com.Hospital.Management.System.controller;

import com.Hospital.Management.System.entity.Patient;
import com.Hospital.Management.System.repository.PatientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @PostMapping("/create")
    public Patient createPatient(@RequestBody Patient patient)
    {
        return patientRepository.save(patient);
    }

    @GetMapping
    public List<Patient> getAllPatinets()
    {
        return patientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable long id) throws AttributeNotFoundException {
        Patient patinetDetails = patientRepository.findById(id).orElseThrow(() -> new AttributeNotFoundException("The Patient id "+id+" Not Found"));
        return ResponseEntity.ok(patinetDetails);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatientDetails(@PathVariable long id,@RequestBody Patient patientDetails) throws AttributeNotFoundException {
        Patient patient = patientRepository.findById(id).orElseThrow(()->new AttributeNotFoundException("Patient Id "+id+" Not Found"));

        patient.setName(patientDetails.getName());
        patient.setAge(patientDetails.getAge());
        patient.setBloodGroup(patientDetails.getBloodGroup());
        patient.setFees(patientDetails.getFees());
        patient.setPrescription(patientDetails.getPrescription());
        patient.setUrgency(patientDetails.getUrgency());

        Patient updatedPatientDetails = patientRepository.save(patient);
        return ResponseEntity.ok(updatedPatientDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Boolean>> deletePatientById(@PathVariable long id) throws AttributeNotFoundException {
        Patient patient = patientRepository.findById(id).orElseThrow(()->new AttributeNotFoundException("Patient Id "+id+" Not Found"));
        patientRepository.delete(patient);
        Map<String,Boolean> response =  new HashMap<String,Boolean>();
        response.put("Deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);

    }

}
