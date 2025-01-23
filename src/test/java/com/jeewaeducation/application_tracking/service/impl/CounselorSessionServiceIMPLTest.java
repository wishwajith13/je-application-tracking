//package com.jeewaeducation.application_tracking.service.impl;
//
//import com.jeewaeducation.application_tracking.dto.counselorSession.CounselorSessionSaveDTO;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//
//@ExtendWith(MockitoExtension.class)
//class CounselorSessionServiceIMPLTest {
//
//    @Mock
//    private CounselorSessionRepo counselorSessionRepo;
//    @Mock
//    private ModelMapper modelMapper;
//    @InjectMocks
//    private CounselorSessionServiceIMPL counselorSessionServiceIMPL;
//
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//    }
//
//    @Test
//    void testSaveCounselorSession() {
//        CounselorSession counselorSession = new CounselorSession();
//        counselorSession.setSessionId(1);
//        counselorSession.setSessionDate("2025-01-12");
//        counselorSession.setSessionDescription("Software Engineers");
//
//        Mockito.when(modelMapper.map(Mockito.any(CounselorSessionSaveDTO.class), Mockito.eq(CounselorSession.class))).thenReturn(counselorSession);
//        Mockito.when(counselorSessionRepo.save(Mockito.any(CounselorSession.class))).thenReturn(counselorSession);
//
//        CounselorSessionSaveDTO counselorSessionSaveDTO = new CounselorSessionSaveDTO();
//        counselorSessionSaveDTO.setSessionDate("2025-01-12");
//        counselorSessionSaveDTO.setSessionDescription("Software Engineers");
//
//        String result = counselorSessionServiceIMPL.saveCounselorSession(counselorSessionSaveDTO);
//        Assertions.assertEquals(1 + " Saved", result);
//        System.out.println("First test passed");
//    }
//
//    @Test
//    void getAllCounselorSessions() {
//    }
//
//    @Test
//    void deleteCounselorSession() {
//    }
//
//    @Test
//    void updateCounselorSession() {
//    }
//}