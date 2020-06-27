package com.gorohovart.alphabank;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalyticController {

	@Autowired
	private AnalyticsStorage storage;

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping("/analytic")
	public HashMap<String, UserStats> analytic() {
		return storage.getUserStatsMap();
	}

}
