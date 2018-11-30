package com.stg.insurance.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stg.insurance.model.ActiveTemplateTracker;
import com.stg.insurance.models.genericdata.Document;

@Service
public interface DataExtractionService {

	Document readCategoryFromFile(String filepath, String recordDelimiter, String fieldDelimiter,
			String fieldDemarcator) throws IOException;

	Document readContentFromDB(String code);

	List<ActiveTemplateTracker> getActiveTemplate();

	void updateActiveTemplate(List<ActiveTemplateTracker> updateListOfActiveTemplate);


	String getDefaultActiveTemplate();

}
