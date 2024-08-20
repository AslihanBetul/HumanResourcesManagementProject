package com.java14.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document

public class Comment {
    @MongoId
    private String id;

    private String comment;
    private String managerId;
    private String companyId;
    private Integer rate;

}
