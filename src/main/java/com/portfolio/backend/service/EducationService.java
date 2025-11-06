package com.portfolio.backend.service;

import com.portfolio.backend.entity.Education;
import com.portfolio.backend.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationService {

    @Autowired
    private EducationRepository educationRepository;

    public List<Education> getAllEducation() {
        return educationRepository.findAllByOrderByStartDateDesc();
    }

    public Optional<Education> getEducationById(Long id) {
        return educationRepository.findById(id);
    }

    public Education createEducation(Education education) {
        return educationRepository.save(education);
    }

    public Education updateEducation(Long id, Education educationDetails) {
        Education education = educationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Education not found"));

        education.setInstitution(educationDetails.getInstitution());
        education.setDegree(educationDetails.getDegree());
        education.setFieldOfStudy(educationDetails.getFieldOfStudy());
        education.setGrade(educationDetails.getGrade());
        education.setStartDate(educationDetails.getStartDate());
        education.setEndDate(educationDetails.getEndDate());
        education.setDescription(educationDetails.getDescription());

        return educationRepository.save(education);
    }

    public void deleteEducation(Long id) {
        educationRepository.deleteById(id);
    }
}