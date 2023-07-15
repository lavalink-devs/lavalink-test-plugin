package dev.arbjerg.test.plugin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class TestRestInterceptor {

	private static final Logger log = LoggerFactory.getLogger(TestRestInterceptor.class);

	public TestRestInterceptor() {
		log.info("Loading Test rest interceptor...");
	}

	@GetMapping("/v4/sessions/{sessionId}/test")
	public String test(@PathVariable String sessionId) {
		log.info("Test rest interceptor called");
		return "Hello " + sessionId;
	}
}
