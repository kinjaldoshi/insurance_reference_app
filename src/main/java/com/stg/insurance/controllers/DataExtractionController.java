package com.stg.insurance.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.stg.insurance.model.ActiveTemplateTracker;
import com.stg.insurance.models.genericdata.Category;
import com.stg.insurance.models.genericdata.Document;
import com.stg.insurance.services.DataExtractionService;

@RestController
@RequestMapping("/v1")
@CrossOrigin
public class DataExtractionController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private DataExtractionService dataExtractionService;

	@Value("${transform.url}")
	private String collateAL3;

	@GetMapping("/extract/combinedResponse")
	public ResponseEntity<byte[]> getCombinedResponse(@RequestParam(name = "filename", required = true) String fileName)
			throws IOException {
		Document document = new Document();
		List<Category> catList = new ArrayList<>();
		Document bp1Doc = dataExtractionService.readCategoryFromFile("bpi1.csv", "CRLF", ",", ":");
		Document biieDoc = dataExtractionService.readCategoryFromFile("BIIE.csv", "CRLF", ",", ":");
		Document dbDoc = dataExtractionService.readContentFromDB("6SDV");
		if (bp1Doc != null && !CollectionUtils.isEmpty(bp1Doc.getCategories())) {
			catList.addAll(bp1Doc.getCategories());
		}
		if (biieDoc != null && !CollectionUtils.isEmpty(biieDoc.getCategories())) {
			catList.addAll(biieDoc.getCategories());
		}
		if (dbDoc != null && !CollectionUtils.isEmpty(dbDoc.getCategories())) {
			catList.addAll(dbDoc.getCategories());
		}
		document.setCategories(catList);
		StringBuilder builder = new StringBuilder(collateAL3).append("?").append("filename=").append(fileName);
		return restTemplate.postForEntity(builder.toString(), document, byte[].class, "AL3");

	}

	@GetMapping(value = "/getActiveTemplates")
	public List<ActiveTemplateTracker> getActiveTemplate() {
		List<ActiveTemplateTracker> activeTemplateTrackerResponse = dataExtractionService.getActiveTemplate();
		return activeTemplateTrackerResponse;

	}

	@GetMapping(value = "/getDefaultActiveTemplate")
	public String getDefaultActiveTemplate() {
		String activeTemplateTrackerResponse = dataExtractionService.getDefaultActiveTemplate();
		return activeTemplateTrackerResponse;

	}

	@PostMapping(value = "/updateActiveTemplates")
	public void updateActiveTemplate(@RequestBody List<ActiveTemplateTracker> updateListOfActiveTemplate) {
		dataExtractionService.updateActiveTemplate(updateListOfActiveTemplate);

	}

}
