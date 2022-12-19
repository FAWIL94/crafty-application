package ch.zhaw.application_crafty.model;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CraftsmanCreateDTO {
    private String companyName;
    private String street;
    private String plz;
    private String city;
}
