package com.java14.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class CommentResponseDto {

    private String id;
    private String comment;
    private String managerId;
    private String companyId;
    private Integer rate;
}
