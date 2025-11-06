package com.portfolio.backend.service;

import com.portfolio.backend.entity.Experience;
import com.portfolio.backend.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    public List<Experience> getAllExperiences() {
        return experienceRepository.findAllByOrderByStartDateDesc();
    }

    public Optional<Experience> getExperienceById(Long id) {
        return experienceRepository.findById(id);
    }

    public List<Experience> getCurrentExperiences() {
        return experienceRepository.findByCurrentlyWorkingTrue();
    }

    public Experience createExperience(Experience experience) {
        return experienceRepository.save(experience);
    }

    public Experience updateExperience(Long id, Experience experienceDetails) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found"));

        experience.setCompany(experienceDetails.getCompany());
        experience.setPosition(experienceDetails.getPosition());
        experience.setDescription(experienceDetails.getDescription());
        experience.setLocation(experienceDetails.getLocation());
        experience.setStartDate(experienceDetails.getStartDate());
        experience.setEndDate(experienceDetails.getEndDate());
        experience.setCurrentlyWorking(experienceDetails.isCurrentlyWorking());
        experience.setResponsibilities(experienceDetails.getResponsibilities());

        return experienceRepository.save(experience);
    }

    public void deleteExperience(Long id) {
        experienceRepository.deleteById(id);
    }
}