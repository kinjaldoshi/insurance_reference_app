package com.stg.insurance.services.impl;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.stg.insurance.model.ActiveTemplateTracker;
import com.stg.insurance.model.DriversInformation;
import com.stg.insurance.models.genericdata.Category;
import com.stg.insurance.models.genericdata.Document;
import com.stg.insurance.models.genericdata.FieldValue;
import com.stg.insurance.models.genericdata.Record;
import com.stg.insurance.repository.ActiveTemplateRepository;
import com.stg.insurance.repository.DriverInformationRepo;
import com.stg.insurance.services.DataExtractionService;
import com.stg.insurance.utils.CommonUtilities;

public class DataExtractionServiceImpl implements DataExtractionService {

	@Autowired
	DriverInformationRepo driverInformationRepo;

	@Autowired
	ActiveTemplateRepository activeTemplateRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(DataExtractionServiceImpl.class);

	@Override
	public Document readCategoryFromFile(String filepath, String recordDelimiter, String fieldDelimiter,
			String fieldDemarcator) throws IOException {
		// TODO Read a category data from a file
		Category category = null;
		List<Category> catList = null;
		Document document = null;
		String content = new String(
				CommonUtilities.getStringFromInputStream(new ClassPathResource(filepath).getInputStream()));
		if (StringUtils.isEmpty(content)) {
			// TODO Throw a service exception
		} else {
			document = new Document();
			category = new Category();
			catList = new ArrayList<>();
			catList.add(category);
			document.setCategories(catList);
			category.setSubCategories(new ArrayList<>());
			String[] records = content.split(recordDelimiter);
			category.setId(records[0].substring(0, 4));
			category.setRecords(new ArrayList<>(records.length));
			for (String recordString : records) {
				Record record = new Record();
				record.setFields(new HashMap<>());
				for (String fieldString : recordString.split(fieldDelimiter)) {
					String[] fieldComponents = fieldString.split(fieldDemarcator);
					record.getFields().put(fieldComponents[0], new FieldValue(fieldComponents[2], fieldComponents[1]));
				}
				category.getRecords().add(record);
			}
		}
		LOGGER.info("File contents read");
		return document;
	}

	@Override
	public Document readContentFromDB(String code) {

		Document doc = new Document();
		List<Category> catList = null;
		Category cat = new Category();
		cat.setId(code);
		List<DriversInformation> listOfDiversInfo = driverInformationRepo.findAll();
		// System.out.println(listOfDiversInfo);
		if (!isEmpty(listOfDiversInfo)) {
			for (DriversInformation info : listOfDiversInfo) {
				List<Record> records = new ArrayList<>();
				Record rec = new Record();
				Map<String, FieldValue> field = new HashMap<>();
				field.put("6SDV03", new FieldValue(info.getDriversName(), "Driver's Name"));
				field.put("6SDV04", new FieldValue(info.getDateOfBirthDt6(), "Date of Birth - DT6"));
				field.put("6SDV05", new FieldValue(info.getDriversLicenseNumber(), "Driver's Lic Number"));
				field.put("6SDV06", new FieldValue(info.getLicenseState(), "License State"));
				field.put("6SDV18", new FieldValue(info.getDateOfBirthDt8(), "Date of Birth - DT8"));
				rec.setFields(field);
				records.add(rec);
				cat.setRecords(records);
			}
		}
		catList = new ArrayList<>();
		catList.add(cat);
		doc.setCategories(catList);
		return doc;
	}

	@Override
	public List<ActiveTemplateTracker> getActiveTemplate() {
		List<ActiveTemplateTracker> response = activeTemplateRepository.findAll();
		return response;
	}

	@Override
	public String getDefaultActiveTemplate() {
		String activeTemplateResponse = null;
		List<ActiveTemplateTracker> response = activeTemplateRepository.findAll();
		for (ActiveTemplateTracker activeTemplateTracker : response) {
			if (activeTemplateTracker.getIsActive() == true) {
				activeTemplateResponse = activeTemplateTracker.getName();
			}
		}
		return activeTemplateResponse;
	}

	@Override
	public void updateActiveTemplate(List<ActiveTemplateTracker> updateListOfActiveTemplate) {
		boolean flag = false;
		List<ActiveTemplateTracker> listFromDB = activeTemplateRepository.findAll();
		for (ActiveTemplateTracker tracker : updateListOfActiveTemplate) {
			if (!CollectionUtils.isEmpty(listFromDB)) {
				for (ActiveTemplateTracker fromBD : listFromDB) {
					if (tracker.getName().equalsIgnoreCase(fromBD.getName())) {
						tracker.setId(fromBD.getId());
						activeTemplateRepository.save(tracker);
						flag = true;
					}
				}
				if (flag != true) {
					activeTemplateRepository.save(tracker);
				}
			} else {
				activeTemplateRepository.save(tracker);
			}
		}

	}

}
