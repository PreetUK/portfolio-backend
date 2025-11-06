package com.portfolio.backend.service;

import com.portfolio.backend.entity.About;
import com.portfolio.backend.repository.AboutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AboutService {

    @Autowired
    private AboutRepository aboutRepository;

    public List<About> getAllAbout() {
        return aboutRepository.findAll();
    }

    public Optional<About> getAboutById(Long id) {
        return aboutRepository.findById(id);
    }

    public About getFirstAbout() {
        List<About> aboutList = aboutRepository.findAll();
        return aboutList.isEmpty() ? null : aboutList.get(0);
    }

    public About createOrUpdateAbout(About about) {
        return aboutRepository.save(about);
    }

    public void deleteAbout(Long id) {
        aboutRepository.deleteById(id);
    }
}