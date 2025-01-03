package com.jeewaeducation.application_tracking.controller;

import com.jeewaeducation.application_tracking.dto.counselorSession.CounselorSessionDTO;
import com.jeewaeducation.application_tracking.dto.counselorSession.CounselorSessionSaveDTO;
import com.jeewaeducation.application_tracking.entity.CounselorSession;
import com.jeewaeducation.application_tracking.service.CounselorSessionService;
import com.jeewaeducation.application_tracking.utility.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/counselor-session")
@CrossOrigin
public class CounselorSessionController {

    @Autowired
    private CounselorSessionService counselorSessionService;

    @PostMapping(
            path = {"/save"}
    )
    public ResponseEntity<StandardResponse> saveCounselorSession(@RequestBody CounselorSessionSaveDTO counselorSessionSaveDTO) {
        String message = counselorSessionService.saveCounselorSession(counselorSessionSaveDTO);
        return new ResponseEntity<StandardResponse>(new StandardResponse(201, "Success", message), HttpStatus.CREATED);
    }

    @GetMapping(
            path = {"/getAll"}
    )
    public ResponseEntity<StandardResponse> getAllCounselorSessions() {
        List<CounselorSessionDTO> message = counselorSessionService.getAllCounselorSessions();
        return new ResponseEntity<StandardResponse>(new StandardResponse(201, "Success", message), HttpStatus.CREATED);
    }

    @DeleteMapping(
            path = {"/delete/{id}"}
    )
    public ResponseEntity<StandardResponse> deleteCounselorSession(@PathVariable int id) {
        String message = counselorSessionService.deleteCounselorSession(id);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "Success", message), HttpStatus.OK);
    }

    @PutMapping(
            path = {"/update"}
    )
    public ResponseEntity<StandardResponse> updateCounselorSession(@RequestBody CounselorSessionDTO counselorSessionDTO) {
        String message = counselorSessionService.updateCounselorSession(counselorSessionDTO);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "Success", message), HttpStatus.OK);
    }
}
