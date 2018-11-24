package com.stg.insurance.models.genericdata;

import java.util.List;

/**
 * @author Aditya Verma
 * 
 *         A Document which will have a main category on top of the heirarchy
 *
 */
public class Document {

	private List<Category> categories;

	/**
	 * @return the categories
	 */
	public List<Category> getCategories() {
		return categories;
	}

	/**
	 * @param categories
	 *            the categories to set
	 */
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}
