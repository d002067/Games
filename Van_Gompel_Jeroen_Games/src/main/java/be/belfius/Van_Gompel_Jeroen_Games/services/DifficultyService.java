package be.belfius.Van_Gompel_Jeroen_Games.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import be.belfius.Van_Gompel_Jeroen_Games.domain.Difficulty;
import be.belfius.Van_Gompel_Jeroen_Games.repository.DifficultyRepository;

public class DifficultyService {
	private DifficultyRepository difficultyRepository = new DifficultyRepository();
	public List<Difficulty> difficultyList = new ArrayList<>();

	public int getMaxDifficultyId() throws SQLException {
		if (difficultyList.isEmpty()) {
			return difficultyRepository.getMaxDifficultyId();
		} else {
			Difficulty difficulty = Collections.max(difficultyList, Comparator.comparing(s -> s.getId()));
			return difficulty.getId();
		}
	}

	public List getList() throws SQLException {
		if (difficultyList.isEmpty()) {
			difficultyList = difficultyRepository.getDifficultyList();
		}
		return difficultyList;
	}

	public Difficulty getDifficultyByIndex(int catIndex) throws SQLException {
		if (difficultyList.isEmpty()) {
			return difficultyRepository.getDifficultyByIndex(catIndex);
		} else {
			return (Difficulty) difficultyList.stream().filter(difficulty -> difficulty.getId() == catIndex).findFirst()
					.orElse(null);
		}
	}
	
	
	public List<Difficulty> getDifficultyByName(String beginLetters) throws SQLException{
		if (difficultyList.isEmpty()) {
			return difficultyRepository.getDifficultyByName(beginLetters);
		} else {
			List<Difficulty> filteredDifficulty = new ArrayList<Difficulty>();
			for (Difficulty difficulty : difficultyList) {
				if(difficulty.getDifficulty_name().toLowerCase().startsWith(beginLetters.toLowerCase())){
					filteredDifficulty.add(difficulty);
				}
			}
			return filteredDifficulty;
		}
	}
}

