package model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class StatsTest {
	// test to check no changes in factors
	@Test
	void testModifyByZero_SameStats() {
	    Stats stats = new Stats(50, 50, 50);
	    
	    stats.modifyHealth(0);
	    stats.modifyRep(0);
	    stats.modifyMoney(0);
	    
	    assertEquals(50, stats.getHealth(), "Prosperity - no change");
	    assertEquals(50, stats.getRep(), "Reputation - no change");
	    assertEquals(50, stats.getMoney(), "Finance - no change");
	}
	
	// tests to check increments and decrements in factors
	@Test
	void testModifyHealth_IncreaseValue() {
		Stats stats = new Stats(50, 50 ,50);
				
		stats.modifyHealth(10);
		
		assertEquals(60, stats.getHealth(), "Prosperity - increase 10");
	}
	
	
	@Test 
	void testModifyHealth_DecreaseValue() {
		Stats stats = new Stats(50, 50 ,50);
		
		stats.modifyHealth(-10);
		
		assertEquals(40, stats.getHealth(), "Prosperity -  decrease 10");
	}
	
	@Test
	void testModifyFinance_IncreaseValue() { 
		Stats stats = new Stats(50, 50 ,50);
		
		stats.modifyMoney(20);
		
		assertEquals(70, stats.getMoney(), "Finance - increase 20");
	}
	
	@Test
	void testModifyFinance_DecreaseValue() { 
		Stats stats = new Stats(50, 50 ,50);
		
		stats.modifyMoney(-20);
		
		assertEquals(30, stats.getMoney(), "Finance - decrease 20");
	}
	
	@Test
	void testModifyReputation_IncreaseValue() {
		Stats stats = new Stats(50, 50, 50);
		
		stats.modifyRep(30);
		
		assertEquals(80, stats.getRep(), "Reputation - increase 30");
	}
	
	@Test
	void testModifyReputation_DecreaseValue() {
		Stats stats = new Stats(50, 50, 50);
		
		stats.modifyRep(-30);
		
		assertEquals(20, stats.getRep(), "Reputation - decrease 30");
	}
	
	// tests edge cases; makes sure it never becomes out of bounds
	@Test
	void testHealthNegativeOverflow() {
		Stats stats = new Stats(10, 50, 50); 
		    
		stats.modifyHealth(-50); 
		    
		assertEquals(0, stats.getHealth(), "Health should never drop below 0.");
	}

	@Test
	void testMoneyPositiveOverflow() {
		Stats stats = new Stats(50, 50, 90); 
		stats.modifyMoney(30); 
		    
		assertEquals(100, stats.getMoney(), "Money should never exceed 100.");
	}
}
