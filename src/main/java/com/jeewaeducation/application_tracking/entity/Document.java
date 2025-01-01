package com.jeewaeducation.application_tracking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Document {
    @Id
    @Column(name="document_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int documentId;
    @Column(name="document_type",nullable = false)
    private String documentType;
    @Column(name="document_name",nullable = false)
    private String documentName;
    @Column(name="document_path",nullable = false)
    private String documentPath;
    @Column(name="created_date",nullable = false)
    private Date createdDate;
    //counsillor id and student id should be added
}
