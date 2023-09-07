package com.project.Microservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private Long price;

}
