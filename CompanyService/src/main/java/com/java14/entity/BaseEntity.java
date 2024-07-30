package com.java14.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor

public class BaseEntity implements Serializable{

    private Long createAt;
    private Long updateAt;

}
