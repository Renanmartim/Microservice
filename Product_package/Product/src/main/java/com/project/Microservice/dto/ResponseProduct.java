package com.project.Microservice.dto;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseProduct {

    private String id;
    private String name;
    private String description;
    private Long price;
}
