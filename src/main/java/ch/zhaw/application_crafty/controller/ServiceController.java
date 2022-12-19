package ch.zhaw.application_crafty.controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.application_crafty.model.Project;
import ch.zhaw.application_crafty.model.ProjectAssignDTO;
import ch.zhaw.application_crafty.model.ProjectCompleteDTO;
import ch.zhaw.application_crafty.service.ProjectService;

@RestController
@RequestMapping("/api/service")
public class ServiceController {
    @Autowired
    ProjectService projectService;

    @PostMapping("/projectassignment")
    public ResponseEntity<Project> assignProject(@RequestBody ProjectAssignDTO assignDTO) {
        Optional<Project> project = projectService.assignProject(assignDTO.getProjectId(), assignDTO.getCustomerId());
        if (project.isPresent()) {
            return new ResponseEntity<>(project.get(), HttpStatus.OK); 
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/projectcomplete")
    public ResponseEntity<Project> completeProject(@RequestBody ProjectCompleteDTO completeDTO) {
        Optional<Project> project = projectService.completeProject(completeDTO.getProjectId(), completeDTO.getComment());
        if (project.isPresent()) {
            return new ResponseEntity<>(project.get(), HttpStatus.OK); 
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
