package com.jeewaeducation.application_tracking;

import com.amazonaws.services.s3.AmazonS3;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest

class ApplicationTrackingApplicationTests {

	@Test
	void contextLoads() {
	}

	@Configuration
	static class TestConfig {
		@Bean
		public AmazonS3 amazonS3() {
			return Mockito.mock(AmazonS3.class); // Provide a mocked bean
		}
	}
}
