package be.belfius.Van_Gompel_Jeroen_Games.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import be.belfius.Van_Gompel_Jeroen_Games.domain.Category;
import be.belfius.Van_Gompel_Jeroen_Games.domain.Game;
import be.belfius.Van_Gompel_Jeroen_Games.repository.GameRepository;


public class GameService {
	private GameRepository gameRepository = new GameRepository();
	public List<Game> gameList = new ArrayList<>();

	public GameService() throws ClassNotFoundException {

	}

	public int getMaxGameId() throws SQLException {
		if (gameList.isEmpty()) {
			return gameRepository.getMaxGameId();
		} else {
			Game game = Collections.max(gameList, Comparator.comparing(s -> s.getId()));
			return game.getId();
		}
	}

	public List getGameList(CategoryService categoryService, DifficultyService difficultyService) throws SQLException {
		if (gameList.isEmpty()) {
			System.out.println("GameList From Database");
			gameList = gameRepository.getGameList();
			for (Game game : gameList) {
				game.setDifficulty(difficultyService.getDifficultyByIndex(game.getDifficulty_id()));
				game.setCategory(categoryService.getCategoryByIndex(game.getCategory_id()));
			}
		}else {
			System.out.println("GameList From Object");
		}
		return gameList.stream()
				.sorted(Comparator.comparing(Game::getGame_name))
				.collect(Collectors.toList());
	}

	public Game getGameByIndex(int gameIndex, CategoryService categoryService, DifficultyService difficultyService) throws SQLException {
		if (gameList.isEmpty()) {
			System.out.println("GameByIndex From Database");
			Game game = gameRepository.getGameByIndex(gameIndex);
			game.setDifficulty(difficultyService.getDifficultyByIndex(game.getDifficulty_id()));
			game.setCategory(categoryService.getCategoryByIndex(game.getCategory_id()));
			return game;
		} else {
			System.out.println("GameByIndex From Object");
			return (Game) gameList.stream().filter(game -> game.getId() == gameIndex).findFirst().orElse(null);
		}
	}

	public List<Game> getGameByName(String beginLetters, CategoryService categoryService, DifficultyService difficultyService) throws SQLException {
		if (gameList.isEmpty()) {
			System.out.println("GameByName From Database");
			List<Game> myGameList =  gameRepository.getGameByName(beginLetters);
			for (Game game : myGameList) {
				game.setDifficulty(difficultyService.getDifficultyByIndex(game.getDifficulty_id()));
				game.setCategory(categoryService.getCategoryByIndex(game.getCategory_id()));
			}
			return myGameList;
		} else {
			System.out.println("GameByName From Object");
			return (List<Game>) gameList.stream().filter(game -> game.getGame_name().toLowerCase().startsWith(beginLetters.toLowerCase())).collect(Collectors.toList());
			/*List<Game> filteredGame = new ArrayList<Game>();
			for (Game game : gameList) {
				if (game.getGame_name().toLowerCase().startsWith(beginLetters.toLowerCase())) {
					filteredGame.add(game);
				}
			}
			return filteredGame;*/
		}
	}
}
