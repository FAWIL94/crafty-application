package ch.zhaw.application_crafty.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjectStateAggregationDTO {
    private String id;
    private List<String> projectIds;
    private String count;
}
