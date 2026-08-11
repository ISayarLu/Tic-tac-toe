package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Board;

class BoardTest {
	Board board;
	
	@BeforeEach
	void setUp() {
		board = new Board();
	}

	@Test
	void testRestart() {
		board.restart();
		assertNull(board.getWinner());
		assertTrue(board.isInProgressMode());
	}
	
	@Test
	void testmarkWhenValid () {
		Player p = board.getCurrentTurn();
		board.mark(0, 0);
		assertFalse(p == board.getCurrentTurn());
	}
	@Test
	void testmarkInNonEmptyCell () {
		Player p = board.getCurrentTurn();
		board.mark(0, 0);
		board.mark(0, 0);
		assertFalse(p == board.getCurrentTurn());
	}
	
	@Test
	void testmarkInOutOfBoundCell () {
		Player p = board.getCurrentTurn();
		board.mark(-1, 0);
		assertTrue(p == board.getCurrentTurn());
		board.mark(0, -1);
		assertTrue(p == board.getCurrentTurn());
		board.mark(4, 0);
		assertTrue(p == board.getCurrentTurn());
		board.mark(0, 3);
		assertTrue(p == board.getCurrentTurn());
	}
	
	@Test
	void testmarkWhenWinnerInColumn () {
		Player p = board.getCurrentTurn();
		board.mark(0, 0);
		board.mark(1, 1);
		board.mark(1, 0);
		board.mark(2, 2);
		board.mark(2, 0);
		assertTrue(p == board.getWinner());
		assertTrue(board.isInFinishedMode());
	}
	
	@Test
	void testmarkWhenWinnerInRow () {
		Player p = board.getCurrentTurn();
		board.mark(0, 0);
		board.mark(1, 1);
		board.mark(0, 1);
		board.mark(2, 2);
		board.mark(0, 2);
		assertTrue(p == board.getWinner());
		assertTrue(board.isInFinishedMode());
	}
	
	@Test
	void testmarkWhenWinnerInDiagonale () {
		Player p = board.getCurrentTurn();
		board.mark(0, 0);
		board.mark(1, 0);
		board.mark(1, 1);
		board.mark(2, 0);
		board.mark(2, 2);
		assertTrue(p == board.getWinner());
	}
	@Test
	void testmarkWhenNoWinner () {
		Player p = board.getCurrentTurn();
		board.mark(0, 0);
		Player p2 = board.getCurrentTurn();
		board.mark(1, 1);
		board.mark(1, 0);
		board.mark(2, 0);
		board.mark(0, 2);
		board.mark(0, 1);
		board.mark(2, 1);
		board.mark(1, 2);
		board.mark(2, 2);
		assertFalse(p == board.getWinner());
		assertFalse(p2 == board.getWinner());
		assertFalse(board.isInFinishedMode());
	}
	@Test
	void testmarkInFinishedMode () {
		Player p = board.getCurrentTurn();
		board.mark(0, 0);
		Player p2 = board.getCurrentTurn();
		board.mark(1, 1);
		board.mark(0, 1);
		board.mark(2, 2);
		board.mark(0, 2);
		assertTrue(p == board.getWinner());
		assertTrue(board.isInFinishedMode());
		board.mark(1, 2);
		assertFalse(p2 == board.getCurrentTurn());
		assertTrue(p == board.getCurrentTurn());
	}
	
}
