package com.linktic.prueba.tecnica.product_catalog_service.model.audit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class AuditableEntity {

    @Column(name = "CREATED_DATE", updatable = false)
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "MODIFIED_DATE")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Column(name = "USER_CREATION", updatable = false)
    @CreatedBy
    private String userCreation;

    @Column(name = "USER_MODIFICATION")
    @LastModifiedBy
    private String userModification;
    
}	