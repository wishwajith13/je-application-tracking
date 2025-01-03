package com.jeewaeducation.application_tracking.dto.counselorSession;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CounselorSessionDTO {
    private int sessionId;
    private Date sessionDate;
    private String sessionDescription;
}
