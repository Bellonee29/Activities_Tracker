package com.toDoApp.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
public class Base implements Serializable {
    @Temporal(value = TemporalType.DATE)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;
    @Temporal(value = TemporalType.DATE)
    @Column(name = "updated_at")
    private Date updatedAt;
    @PrePersist
    protected void prePersist(){
        this.createdAt=new Date();
    }
    @PreUpdate
    protected void preUpdate(){
        this.updatedAt=new Date();
    }
}
