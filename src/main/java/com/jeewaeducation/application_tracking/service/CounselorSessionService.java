package com.jeewaeducation.application_tracking.service;

import com.jeewaeducation.application_tracking.dto.counselorSession.CounselorSessionDTO;
import com.jeewaeducation.application_tracking.dto.counselorSession.CounselorSessionSaveDTO;
import com.jeewaeducation.application_tracking.entity.CounselorSession;

import java.util.List;

public interface CounselorSessionService {
    String saveCounselorSession(CounselorSessionSaveDTO counselorSessionSaveDTO);

    List<CounselorSessionDTO> getAllCounselorSessions();

    String deleteCounselorSession(int id);

    String updateCounselorSession(CounselorSessionDTO counselorSessionDTO);
}
