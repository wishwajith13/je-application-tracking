package com.jeewaeducation.application_tracking.service.impl;

import com.jeewaeducation.application_tracking.dto.counselorSession.CounselorSessionDTO;
import com.jeewaeducation.application_tracking.dto.counselorSession.CounselorSessionSaveDTO;
import com.jeewaeducation.application_tracking.entity.CounselorSession;
import com.jeewaeducation.application_tracking.exception.DuplicateKeyException;
import com.jeewaeducation.application_tracking.exception.NotFoundException;
import com.jeewaeducation.application_tracking.repo.CounselorSessionRepo;
import com.jeewaeducation.application_tracking.service.CounselorSessionService;
import com.jeewaeducation.application_tracking.utility.mappers.CounselorSessionMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounselorSessionServiceIMPL implements CounselorSessionService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CounselorSessionRepo counselorSessionRepo;
    @Autowired
    private CounselorSessionMapper counselorSessionMapper;

    @Override
    public String saveCounselorSession(CounselorSessionSaveDTO counselorSessionSaveDTO) {
        CounselorSession counselorSession = modelMapper.map(counselorSessionSaveDTO, CounselorSession.class);
        counselorSessionRepo.findById(counselorSession.getSessionId()).ifPresent(a -> {
            throw new DuplicateKeyException("Session already exists with ID: " + counselorSession.getSessionId());
        });
        counselorSessionRepo.save(counselorSession);
        return counselorSession.getSessionId() + " Saved";
    }

    @Override
    public List<CounselorSessionDTO> getAllCounselorSessions() {
        List<CounselorSession> counselorSessions = counselorSessionRepo.findAll();
        if (counselorSessions.isEmpty()) {
            throw new NotFoundException("No sessions found");
        } else {
            return counselorSessionMapper.entityListToDtoList(counselorSessions);
        }
    }

    @Override
    public String deleteCounselorSession(int id) {
        counselorSessionRepo.findById(id).orElseThrow(() -> new NotFoundException("No session found with ID: " + id));
        counselorSessionRepo.deleteById(id);
        return "Session deleted with ID: " + id;
    }

    @Override
    public String updateCounselorSession(CounselorSessionDTO counselorSessionDTO) {
        counselorSessionRepo.findById(counselorSessionDTO.getSessionId()).orElseThrow(() -> new NotFoundException("No session found with ID: " + counselorSessionDTO.getSessionId()));
        CounselorSession counselorSession = modelMapper.map(counselorSessionDTO, CounselorSession.class);
        counselorSessionRepo.save(counselorSession);
        return counselorSession.getSessionId() + " Updated";
    }
}
