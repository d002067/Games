package be.belfius.Van_Gompel_Jeroen_Games.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import be.belfius.Van_Gompel_Jeroen_Games.domain.Category;
import be.belfius.Van_Gompel_Jeroen_Games.domain.ListState;
import be.belfius.Van_Gompel_Jeroen_Games.repository.CategoryRepository;

public class CategoryService {
	private CategoryRepository categoryRepository = new CategoryRepository();
	public List<Category> categoryList = new ArrayList<>();
	private ListState listState = ListState.EMPTY;
	
	public int getMaxCategoryId() throws SQLException {
		if (listState == ListState.EMPTY) {
			printInfo("MaxCategoryId From Database");
			return categoryRepository.getMaxCategoryId();
		} else {
			printInfo("MaxCategoryId From Object");
			Category category = Collections.max(categoryList, Comparator.comparing(s -> s.getId()));
			return category.getId();
		}
	}

	public List getCategoryList() throws SQLException {
		if (listState == ListState.EMPTY) {
			printInfo("CategoryList From Database");
			categoryList = categoryRepository.getCategoryList();
			listState = ListState.FILLED;
		}else {
			printInfo("CategoryList From Object");
		}		
		return categoryList;
	}

	public Category getCategoryByIndex(int catIndex) throws SQLException {
		if (listState == ListState.EMPTY) {
			printInfo("CategoryByIndex From Database");
			return categoryRepository.getCategoryByIndex(catIndex);
		} else {
			printInfo("CategoryByIndex From Object");
			return (Category) categoryList.stream().filter(category -> category.getId() == catIndex).findFirst()
					.orElse(null);
		}
	}
	
	
	public List<Category> getCategoryByName(String beginLetters) throws SQLException{
		if (listState == ListState.EMPTY) {
			printInfo("CategoryByName From Database");
			return categoryRepository.getCategoryList(beginLetters);
		} else {
			printInfo("CategoryByName From Object");
			return  (List<Category>) categoryList.stream().filter(category -> category.getCategory_name().toLowerCase().startsWith(beginLetters.toLowerCase())).collect(Collectors.toList());
			/*List<Category> filteredCategory = new ArrayList<Category>();
			for (Category category : categoryList) {
				if(category.getCategory_name().toLowerCase().startsWith(beginLetters.toLowerCase())){
					filteredCategory.add(category);
				}
			}
			return filteredCategory;*/
		}
	}
	private void printInfo(String info) {
		//System.out.println(info);
	}
}