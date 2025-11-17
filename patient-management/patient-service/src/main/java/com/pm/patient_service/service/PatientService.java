package com.pm.patient_service.service;


import java.util.List;

import com.pm.patient_service.dto.PatientRequestDTO;
import org.springframework.stereotype.Service;

import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRepository;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients = patientRepository.findAll();

        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patient){
        Patient patient1 = patientRepository.save(PatientMapper.toPatient(patient));
        return PatientMapper.toDTO(patient1);
    }

}
