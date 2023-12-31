package com.clinic.demo.repository;

import com.clinic.demo.models.entitiy.TreatmentEntity;
import com.clinic.demo.models.entitiy.user.DoctorEntity;
import com.clinic.demo.models.entitiy.user.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TreatmentRepository extends JpaRepository<TreatmentEntity, Long> {
    Optional<TreatmentEntity> findByCost(Long cost);
    Optional<TreatmentEntity> findByCostLessThan(Long costsLessThan);
    List<TreatmentEntity> findAllByPatient(PatientEntity patientEntity);
    Optional<TreatmentEntity> findAllByDoctor(DoctorEntity doctorEntity);
}
