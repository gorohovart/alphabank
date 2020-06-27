package com.gorohovart.task2;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalyticController {

	@Autowired
	private AnalyticsStorage storage;

	@RequestMapping("/admin/health")
	public Status index() {
		return Status.UP;
	}

	@RequestMapping("/analytic")
	public Collection<UserStats> analytic() {
		System.out.println("size is" + storage.getUserStatsMap().size());
		return storage.getUserStatsMap().values();
	}

	@RequestMapping("/analytic/{id}")
	public ResponseEntity<?> analyticById(@PathVariable("id") String id) {

		UserStats stats = storage.getUserStatsMap().get(id);
		if (stats == null) {
			Map<String, String> errors = new HashMap<>();
			errors.put("status", "user not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
		}

		return ResponseEntity.ok().body(stats);
	}


	@RequestMapping("/analytic/{id}/stats")
	public ResponseEntity<?> statsById(@PathVariable("id") String id) {
		UserStats stats = storage.getUserStatsMap().get(id);
		if (stats == null) {
			Map<String, String> errors = new HashMap<>();
			errors.put("status", "user not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
		}

		return ResponseEntity.ok().body(stats.getStats());
	}

	@RequestMapping("/analytic/{id}/templates")
	public ResponseEntity<?> templatesById(@PathVariable("id") String id) {
		UserStats stats = storage.getUserStatsMap().get(id);
		if (stats == null) {
			Map<String, String> errors = new HashMap<>();
			errors.put("status", "user not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
		}

		return ResponseEntity.ok().body(stats.getTemplates());
	}


}
