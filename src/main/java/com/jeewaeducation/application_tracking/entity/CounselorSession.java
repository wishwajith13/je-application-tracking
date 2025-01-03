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
@Table(name = "counselor_session")
public class CounselorSession {
    @Id
    @Column(name = "session_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sessionId;
    @Column(name = "session_date", nullable = false)
    private Date sessionDate;
    @Column(name = "session_description")
    private String sessionDescription;
}
