package com.gmail.listeners;

import com.gmail.entity.BaseEntity;

import javax.persistence.PrePersist;
import java.time.LocalDate;

public class UserEntityListener {
    @PrePersist
    public void prePersist(BaseEntity entity) {
        entity.setDateCreate(LocalDate.now());
    }
}
