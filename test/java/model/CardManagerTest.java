package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CardManagerTest {
	private CardManager manager = new CardManager();
	
	// tests to check proper endings for every factor is triggered correctly
	@Test
	void testGetFactorEnding_HealthMax() {
		// Prosperity - 100; should get ProsperityMax
		Stats stats = new Stats(100, 50, 50);
		Card result = manager.getFactorEnding(stats);
		
		assertNotNull(result);
		assertEquals(CardData.getEndingDeck().get(0).getTitle(), result.getTitle(),"Should return Prosperity Max Card");
	}
	
	@Test
	void testGetFactorEnding_HealthMin() {
		// Prosperity - 0; should get ProsperityMin
		Stats stats = new Stats(0, 50, 50);
		Card result = manager.getFactorEnding(stats);
				
		assertNotNull(result);
		assertEquals(CardData.getEndingDeck().get(1).getTitle(), result.getTitle(), "Should return Prosperity Min Card");
	}
	
	@Test
	void testGetFactorEnding_RepMax() {
		// Rep = 100; should return Rep Max ending
		Stats stats = new Stats(50, 100, 50);
		Card result  = manager.getFactorEnding(stats);
		
		assertNotNull(result);
		assertEquals(CardData.getEndingDeck().get(2).getTitle(), result.getTitle(), "Should return Rep Max.");
	}
	
	@Test
	void testGetFactorEnding_RepMin() {
		// Rep = 0; should return Rep Min ending
		Stats stats = new Stats(50, 0, 50);
		Card result  = manager.getFactorEnding(stats);
				
		assertNotNull(result);
		assertEquals(CardData.getEndingDeck().get(3).getTitle(), result.getTitle(), "Should return Rep Min.");
	}
	
	@Test
	void testGetFactorEnding_FinMax() {
		// Finance = 100; should return Fin Max Ending
		Stats stats = new Stats(50, 50, 100);
		Card result = manager.getFactorEnding(stats);
		
		assertNotNull(result);
		assertEquals(CardData.getEndingDeck().get(4).getTitle(), result.getTitle(),"Should return Fin Max.");
	}
	
	@Test
	void testGetFactorEnding_FinMin() {
		// Finance = 0; should return Fin Min Ending
		Stats stats = new Stats(50, 50, 0);
		Card result = manager.getFactorEnding(stats);
		
		assertNotNull(result);
		assertEquals(CardData.getEndingDeck().get(5).getTitle(), result.getTitle(), "Should return Fin Min.");
	}
	
	// test to ensure no ending is factor ending is triggered when no MIN or MAX
	@Test
	void testGetFactorEnding_NoEndingTriggered() {
	    Stats stats = new Stats(50, 50, 50);
	    Card result = manager.getFactorEnding(stats);
	    
	    assertNull(result, "Should return null if no stats have hit 0 or 100");
	}
	
	// tests to check true endings
	@Test
	void testGetGoodEnding_MoreThanHalfAverage() {
		// factor average more than half
		Stats stats = new Stats(80, 80, 80);
		Card result = manager.getEnding(stats);
		
		assertNotNull(result);
		assertEquals(CardData.getEndingDeck().get(6).getTitle(), result.getTitle(), "Should return Good Ending.");
	}
	
	@Test
	void testGetBadEnding_LessThanHalfAverage() {
		// factor average less than half
		Stats stats = new Stats(20, 20, 20);
		Card result = manager.getEnding(stats);
		
		assertNotNull(result);
		assertEquals(CardData.getEndingDeck().get(7).getTitle(), result.getTitle(), "Should return Bad Ending");
		
	}
	
	@Test
	void testGetEnding_ExactlyHalfAverage() {
		// factor average exactly half to check edge case
		Stats stats = new Stats(50, 50, 50);
		Card result = manager.getEnding(stats);
		
		assertNotNull(result);
		assertEquals(CardData.getEndingDeck().get(7).getTitle(), result.getTitle(), "Should return Bad Ending");
		
	}
}
