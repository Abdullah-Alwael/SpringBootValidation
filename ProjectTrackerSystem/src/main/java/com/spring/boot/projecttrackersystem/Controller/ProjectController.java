package com.spring.boot.projecttrackersystem.Controller;

import com.spring.boot.projecttrackersystem.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    ArrayList<Project> projects = new ArrayList<>();

    @PostMapping("/new")
    public ResponseEntity<?> createProject(@Valid @RequestBody Project project, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }

        projects.add(project);
        return ResponseEntity.status(HttpStatus.OK).body("Project created successfully");
    }

    @GetMapping("list")
    public ResponseEntity<?> displayProjects() {
        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }

    @PutMapping("/update/{iD}")
    public ResponseEntity<?> updateProject(@PathVariable String iD, @Valid @RequestBody Project project, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }

        boolean updated = false;
        for (Project p : projects) {
            if (p.getID().equals(iD)) {
                projects.set(projects.indexOf(p), project);
                updated = true;
                break;
            }
        }
        if (updated) {
            return ResponseEntity.status(HttpStatus.OK).body("Project updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, Project with ID " + iD + " does not exist!");
        }
    }

    @DeleteMapping("/delete/{iD}")
    public ResponseEntity<?> deleteProject(@PathVariable String iD) {
        boolean deleted = false;

        for (Project p : projects) {
            if (p.getID().equals(iD)) {
                projects.remove(p);
                deleted = true;
                break;
            }
        }
        if (deleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Project was deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, Project with ID " + iD + " does not exist!");
        }
    }

//• Change the project status
//• Search for a project by given title
//• Display All project for one company by companyName.

}
