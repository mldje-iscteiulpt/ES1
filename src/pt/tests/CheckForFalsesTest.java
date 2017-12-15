package pt.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;
import org.junit.runners.Parameterized.Parameters;

import pt.objects.Rules;
import pt.reader.CheckForFalses;

public class CheckForFalsesTest {

	ArrayList<Rules> rulesList;
	String[] tokens = {"a","b","c","d","e","f"};
	
	CheckForFalses checker;
	
	@Parameters
	public void checkRulesList(){
		rulesList = new ArrayList<Rules>();
		rulesList.add(new Rules("a", 1.0));
		rulesList.add(new Rules("b", 2.0));
		rulesList.add(new Rules("c", 1.3));
		rulesList.add(new Rules("d", 0.3));
		rulesList.add(new Rules("e", 0.0));
		rulesList.add(new Rules("f", 1.0));
		
	}
	
	@Before
	public void setUp() {
		checker = new CheckForFalses(new ArrayList<Rules>());

	}
	
	@Test
	public void testFalses(){
		assertTrue(checker.isFalsePositive(5.01));
		assertFalse(checker.isFalsePositive(4.9));
		assertTrue(checker.isFalseNegative(-5.01));
		assertFalse(checker.isFalseNegative(-4.9));
	}
	
}
