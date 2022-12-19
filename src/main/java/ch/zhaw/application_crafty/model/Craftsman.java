package ch.zhaw.application_crafty.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@RequiredArgsConstructor
@Document("craftsman")
public class Craftsman {
    @Id
    private String id;
    @NonNull
    private String companyName;
    @NonNull
    private String street;
    @NonNull
    private String plz;
    @NonNull
    private String city;
}
