package be.belfius.Van_Gompel_Jeroen_Games.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import be.belfius.Van_Gompel_Jeroen_Games.domain.Difficulty;
import be.belfius.Van_Gompel_Jeroen_Games.domain.ListState;
import be.belfius.Van_Gompel_Jeroen_Games.repository.DifficultyRepository;

public class DifficultyService {
	private DifficultyRepository difficultyRepository = new DifficultyRepository();
	public List<Difficulty> difficultyList = new ArrayList<>();
	private ListState listState = ListState.EMPTY;
	
	public int getMaxDifficultyId() throws SQLException {
		if (listState == ListState.EMPTY) {
			printInfo("MaxDifficultyId From Database");
			return difficultyRepository.getMaxDifficultyId();
		} else {
			printInfo("MaxDifficultyId From Object");
			Difficulty difficulty = Collections.max(difficultyList, Comparator.comparing(s -> s.getId()));
			return difficulty.getId();
		}
	}

	public List getList() throws SQLException {
		if (listState == ListState.EMPTY) {
			printInfo("DifficultyList From Database");
			difficultyList = difficultyRepository.getDifficultyList();
			listState = ListState.FILLED;
		}else {
			printInfo("MaxDifficultyId From Object");
		}
		return difficultyList;
	}

	public Difficulty getDifficultyByIndex(int catIndex) throws SQLException {
		if (listState == ListState.EMPTY) {
			printInfo("MaxDifficultyByIndex From Database");
			return difficultyRepository.getDifficultyByIndex(catIndex);
		} else {
			printInfo("MaxDifficultyByIndex From Object");
			return (Difficulty) difficultyList.stream().filter(difficulty -> difficulty.getId() == catIndex).findFirst()
					.orElse(null);
		}
	}
	
	
	public List<Difficulty> getDifficultyByName(String beginLetters) throws SQLException{
		if (difficultyList.isEmpty()) {
			printInfo("MaxDifficultyByName From Database");
			return difficultyRepository.getDifficultyByName(beginLetters);
		} else {
			printInfo("MaxDifficultyByName From Object");
			List<Difficulty> filteredDifficulty = new ArrayList<Difficulty>();
			for (Difficulty difficulty : difficultyList) {
				if(difficulty.getDifficulty_name().toLowerCase().startsWith(beginLetters.toLowerCase())){
					filteredDifficulty.add(difficulty);
				}
			}
			return filteredDifficulty;
		}
	}
	private void printInfo(String info) {
		//System.out.println(info);
	}
}

