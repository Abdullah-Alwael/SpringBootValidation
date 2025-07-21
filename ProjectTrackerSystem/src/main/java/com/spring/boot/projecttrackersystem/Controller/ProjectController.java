package com.spring.boot.projecttrackersystem.Controller;

import com.spring.boot.projecttrackersystem.Model.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

//    Q2 : Create project tracker system , Where you can get all the project ,
//    add , remove , update project.
    ArrayList<Project> projects = new ArrayList<>();

//• Create a new project (ID,title , description , status, companyName)
    public ResponseEntity<?> createProject(){

        return ResponseEntity.status(HttpStatus.OK).body("Project created successfully");
    }
//• Display all project .
//• Update a project
//• Delete a project
//• Change the project status as done or not done
//• Search for a project by given title
//• Display All project for one company by companyName.

}
