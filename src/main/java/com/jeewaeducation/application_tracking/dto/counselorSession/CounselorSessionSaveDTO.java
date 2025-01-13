package com.jeewaeducation.application_tracking.dto.counselorSession;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CounselorSessionSaveDTO {
    private String sessionDate;
    private String sessionDescription;
}
