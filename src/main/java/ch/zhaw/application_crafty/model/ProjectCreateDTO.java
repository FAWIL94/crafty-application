package ch.zhaw.application_crafty.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@NoArgsConstructor
@Getter

public class ProjectCreateDTO {
    private String name;
    private String description;
    private Double costs;
    private ProjectType projectType;
}
