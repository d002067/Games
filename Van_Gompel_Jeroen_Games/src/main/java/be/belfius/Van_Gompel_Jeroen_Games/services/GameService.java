package be.belfius.Van_Gompel_Jeroen_Games.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import be.belfius.Van_Gompel_Jeroen_Games.domain.Category;
import be.belfius.Van_Gompel_Jeroen_Games.domain.Game;
import be.belfius.Van_Gompel_Jeroen_Games.domain.ListState;
import be.belfius.Van_Gompel_Jeroen_Games.repository.GameRepository;


public class GameService {
	private GameRepository gameRepository = new GameRepository();
	public List<Game> gameList = new ArrayList<>();
	private ListState listState = ListState.EMPTY;
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

	public List getGameList(CategoryService categoryService, DifficultyService difficultyService, BorrowService borrowService) throws SQLException {
		if (listState == ListState.EMPTY) {
			printInfo("GameList From Database");
			gameList = gameRepository.getGameList();
			for (Game game : gameList) {
				game.setDifficulty(difficultyService.getDifficultyByIndex(game.getDifficulty_id()));
				game.setCategory(categoryService.getCategoryByIndex(game.getCategory_id()));
				game.borrowList = borrowService.getBorrowListForGame(game.getId());				
			}
			listState = ListState.FILLED;
		}else {
			printInfo("GameList From Object");
		}
		return gameList.stream()
				.sorted(Comparator.comparing(Game::getGame_name))
				.collect(Collectors.toList());
	}

	public Game getGameByIndex(int gameIndex, CategoryService categoryService, DifficultyService difficultyService) throws SQLException {
		if (listState == ListState.EMPTY) {
			printInfo("GameByIndex From Database");
			Game game = gameRepository.getGameByIndex(gameIndex);
			game.setDifficulty(difficultyService.getDifficultyByIndex(game.getDifficulty_id()));
			game.setCategory(categoryService.getCategoryByIndex(game.getCategory_id()));
			return game;
		} else {
			printInfo("GameByIndex From Object");
			return (Game) gameList.stream().filter(game -> game.getId() == gameIndex).findFirst().orElse(null);
		}
	}

	public List<Game> getGameByName(String beginLetters, CategoryService categoryService, DifficultyService difficultyService, BorrowService borrowService) throws SQLException {
		if (listState == ListState.EMPTY) {
			printInfo("GameByName From Database");
			List<Game> myGameList =  gameRepository.getGameList(beginLetters);
			for (Game game : myGameList) {
				game.setDifficulty(difficultyService.getDifficultyByIndex(game.getDifficulty_id()));
				game.setCategory(categoryService.getCategoryByIndex(game.getCategory_id()));
				game.borrowList = borrowService.getBorrowListForGame(game.getId());	
			}
			return myGameList;
		} else {
			printInfo("GameByName From Object");
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
	public List<Game> getSelectedGame(String letters, CategoryService categoryService, DifficultyService difficultyService, BorrowService borrowService) throws SQLException {
		if (listState == ListState.EMPTY) {
			printInfo("GameByName From Database");
			List<Game> myGameList =  gameRepository.getGameByPart(letters);
			for (Game game : myGameList) {
				game.setDifficulty(difficultyService.getDifficultyByIndex(game.getDifficulty_id()));
				game.setCategory(categoryService.getCategoryByIndex(game.getCategory_id()));
				game.borrowList = borrowService.getBorrowListForGame(game.getId());	
			}
			return myGameList;
		} else {
			printInfo("GameByName From Object");
			return (List<Game>) gameList.stream().filter(game -> game.getGame_name().toLowerCase().contains(letters.toLowerCase())).collect(Collectors.toList());
			/*List<Game> filteredGame = new ArrayList<Game>();
			for (Game game : gameList) {
				if (game.getGame_name().toLowerCase().startsWith(beginLetters.toLowerCase())) {
					filteredGame.add(game);
				}
			}
			return filteredGame;*/
		}
	}
	private void printInfo(String info) {
		//System.out.println(info);		
	}
}
