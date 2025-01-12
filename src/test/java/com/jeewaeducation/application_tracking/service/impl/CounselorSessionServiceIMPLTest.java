package com.jeewaeducation.application_tracking.service.impl;

import com.jeewaeducation.application_tracking.repo.CounselorSessionRepo;
import com.jeewaeducation.application_tracking.service.CounselorSessionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;


class CounselorSessionServiceIMPLTest {

    @Mock
    private CounselorSessionRepo counselorSessionRepo;
    @Mock
    private CounselorSessionService counselorSessionService;
    @Mock
    private ModelMapper modelMapper;
    AutoCloseable autoCloseable;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testSaveCounselorSession() {
    }

    @Test
    void getAllCounselorSessions() {
    }

    @Test
    void deleteCounselorSession() {
    }

    @Test
    void updateCounselorSession() {
    }
}