package ch.zhaw.application_crafty.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.NonNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Document("customer")
public class Customer {
    @Id
    private String id;
    @NonNull
    private String email;
    @NonNull
    private String name;
}
