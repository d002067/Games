package be.belfius.Van_Gompel_Jeroen_Games.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import be.belfius.Van_Gompel_Jeroen_Games.domain.Category;
import be.belfius.Van_Gompel_Jeroen_Games.repository.CategoryRepository;

public class CategoryService {
	private CategoryRepository categoryRepository = new CategoryRepository();
	public List<Category> categoryList = new ArrayList<>();

	public int getMaxCategoryId() throws SQLException {
		if (categoryList.isEmpty()) {
			return categoryRepository.getMaxCategoryId();
		} else {
			Category category = Collections.max(categoryList, Comparator.comparing(s -> s.getId()));
			return category.getId();
		}
	}

	public List getCategoryList() throws SQLException {
		if (categoryList.isEmpty()) {
			categoryList = categoryRepository.getCategoryList();
		}
		return categoryList;
	}

	public Category getCategoryByIndex(int catIndex) throws SQLException {
		if (categoryList.isEmpty()) {
			return categoryRepository.getCategoryByIndex(catIndex);
		} else {
			return (Category) categoryList.stream().filter(category -> category.getId() == catIndex).findFirst()
					.orElse(null);
		}
	}
	
	
	public List<Category> getCategoryByName(String beginLetters) throws SQLException{
		if (categoryList.isEmpty()) {
			return categoryRepository.getCategoryByName(beginLetters);
		} else {
			//return  (List<Category>) categoryList.stream().filter(category -> category.getCategory_name().startsWith(beginLetters));
			List<Category> filteredCategory = new ArrayList<Category>();
			for (Category category : categoryList) {
				if(category.getCategory_name().toLowerCase().startsWith(beginLetters.toLowerCase())){
					filteredCategory.add(category);
				}
			}
			return filteredCategory;
		}
	}
}