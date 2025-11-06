package com.portfolio.backend.controller;

import com.portfolio.backend.entity.About;
import com.portfolio.backend.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/about")
@CrossOrigin(origins = "http://localhost:4200")
public class AboutController {

    @Autowired
    private AboutService aboutService;

    @GetMapping
    public ResponseEntity<List<About>> getAllAbout() {
        return ResponseEntity.ok(aboutService.getAllAbout());
    }

    @GetMapping("/first")
    public ResponseEntity<About> getFirstAbout() {
        About about = aboutService.getFirstAbout();
        if (about != null) {
            return ResponseEntity.ok(about);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<About> getAboutById(@PathVariable Long id) {
        return aboutService.getAboutById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<About> createAbout(@RequestBody About about) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(aboutService.createOrUpdateAbout(about));
    }

    @PutMapping("/{id}")
    public ResponseEntity<About> updateAbout(@PathVariable Long id, @RequestBody About about) {
        about.setId(id);
        return ResponseEntity.ok(aboutService.createOrUpdateAbout(about));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbout(@PathVariable Long id) {
        aboutService.deleteAbout(id);
        return ResponseEntity.noContent().build();
    }
}