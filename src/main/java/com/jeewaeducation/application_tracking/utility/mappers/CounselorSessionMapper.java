package com.jeewaeducation.application_tracking.utility.mappers;

import com.jeewaeducation.application_tracking.dto.counselorSession.CounselorSessionDTO;
import com.jeewaeducation.application_tracking.entity.CounselorSession;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CounselorSessionMapper {
    List<CounselorSessionDTO> entityListToDtoList(List<CounselorSession> items);
}
