package com.java14.entity;

import com.java14.util.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BaseEntity {
    private Long createDate;
    private Long updateDate;
    private EStatus status;
}
