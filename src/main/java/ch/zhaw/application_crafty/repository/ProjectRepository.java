package ch.zhaw.application_crafty.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import ch.zhaw.application_crafty.model.Project;
import ch.zhaw.application_crafty.model.ProjectStateAggregationDTO;

public interface ProjectRepository extends MongoRepository<Project, String> {
    List<Project> findByCostsGreaterThan(Double costs);
    List<Project> findByCostsBetween(Double min, Double max);
    Page<Project> findByCostsGreaterThan(Double costs, Pageable pageable);
    Page<Project>findByCostsBetween(Double min, Double max, Pageable pageable);
    @Aggregation("{$group: {_id: '$projectState',projectIds: {$push: '$_id'},count: {$count: {}}}}")
    List<ProjectStateAggregationDTO> getProjectStateAggregation();
    
}

