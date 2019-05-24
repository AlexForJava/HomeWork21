package com.gmail.entity;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Data
@MappedSuperclass
public class BaseEntity {
    private LocalDate dateCreate;
}
