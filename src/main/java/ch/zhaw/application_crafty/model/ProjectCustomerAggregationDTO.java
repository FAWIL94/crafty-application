package ch.zhaw.application_crafty.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProjectCustomerAggregationDTO {
    private String id;
    private List<String> projectIds;
    private Double totalCost;
}
