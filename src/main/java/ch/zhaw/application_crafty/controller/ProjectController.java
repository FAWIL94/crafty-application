package ch.zhaw.application_crafty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.application_crafty.model.Project;
import ch.zhaw.application_crafty.model.ProjectCreateDTO;
import ch.zhaw.application_crafty.model.ProjectStateAggregationDTO;
import ch.zhaw.application_crafty.repository.ProjectRepository;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    ProjectRepository projectRepository;

    @PostMapping("")
    public ResponseEntity<Project> createProject(@RequestBody ProjectCreateDTO pDTO) {
        Project pDAO = new Project(pDTO.getName(), pDTO.getDescription(), pDTO.getCosts(),pDTO.getProjectType());
        Project j = projectRepository.save(pDAO);
        return new ResponseEntity<>(j, HttpStatus.CREATED);
    }  
    @GetMapping("")
    public ResponseEntity<Page<Project>> getAllProjects(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize, 
            @RequestParam(required = false) Double min, 
            @RequestParam(required = false) Double max) {
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 2;
        }
        Page<Project> allProjects;
        
        if (min != null && max != null) {
            allProjects = projectRepository.findByCostsBetween(min, max, PageRequest.of(page - 1, pageSize));
        } else if (min != null) {
            allProjects = projectRepository.findByCostsGreaterThan(min, PageRequest.of(page - 1, pageSize));
        } else {
            allProjects = projectRepository.findAll(PageRequest.of(page - 1, pageSize));
        }
        
        return new ResponseEntity<>(allProjects, HttpStatus.OK);
    }

@GetMapping("costsgreaterthan")
public ResponseEntity<List<Project>> getProjectMinCosts(@RequestParam Double min){
    return new ResponseEntity<>(projectRepository.findByCostsGreaterThan(min), HttpStatus.OK);
}
@GetMapping("/costsinrange")
    public ResponseEntity<List<Project>> getProjectMinMaxCosts(@RequestParam Double min, @RequestParam Double max) {
        return new ResponseEntity<>(projectRepository.findByCostsBetween(min, max), HttpStatus.OK);
    }

    @GetMapping("/bystate")
    public ResponseEntity<List<ProjectStateAggregationDTO>> getProjectStateAggregation() {
        return new ResponseEntity<>(projectRepository.getProjectStateAggregation(), HttpStatus.OK);
    }
}