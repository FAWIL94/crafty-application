package ch.zhaw.application_crafty.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.zhaw.application_crafty.model.Project;
import ch.zhaw.application_crafty.model.ProjectState;
import ch.zhaw.application_crafty.repository.CustomerRepository;
import ch.zhaw.application_crafty.repository.ProjectRepository;


@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    CustomerRepository customerRepository;
    
    public Optional<Project> assignProject(String projectId, String customerId) {
        if (customerRepository.findById(customerId).isPresent()) {
            Optional<Project> projectToAssign = projectRepository.findById(projectId);
            if (projectToAssign.isPresent()) {
                Project project = projectToAssign.get();
                if (project.getProjectState() == ProjectState.NEW) {
                    project.setProjectState(ProjectState.ASSIGNED);
                    project.setCustomerId(customerId);
                    projectRepository.save(project);
                    return Optional.of(project);
                }
            }
        }
        return Optional.empty();
    }

    public Optional<Project> completeProject(String projectId, String comment) {
        Optional<Project> projectToComplete = projectRepository.findById(projectId);
        if (projectToComplete.isPresent()) {
            Project project = projectToComplete.get();
            if (project.getProjectState() == ProjectState.ASSIGNED) {
                project.setProjectState(ProjectState.DONE);
                project.setComment(comment);
                projectRepository.save(project);
                return Optional.of(project);
            }
        }
        return Optional.empty();
    }
}