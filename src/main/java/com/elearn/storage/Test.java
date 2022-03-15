package com.elearn.storage;

import java.util.List;

import com.elearn.storage.model.Category;

public class Test {

	public static void main(String[] args) {
		testAddCategory("Dummy");
		getAllCategories();
	}

	private static void testAddCategory(String categoryName) {
		ApplicationDataStorage.addCategory(categoryName);
	}

	private static void getAllCategories() {
		List<Category> categoryList = ApplicationDataStorage.getAllCategories();
		for (Category category : categoryList) {
			System.out.println(category.getCategoryId() + " == " + category.getCategoryName());
		}
	}

}
