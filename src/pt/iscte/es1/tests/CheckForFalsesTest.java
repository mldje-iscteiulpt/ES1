package pt.iscte.es1.tests;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.TreeMap;

import org.junit.*;
import org.junit.runners.Parameterized.Parameters;
import pt.iscte.es1.reader.CheckForFalses;
import pt.iscte.es1.objects.Message;
import pt.iscte.es1.objects.Rule;

public class CheckForFalsesTest {

	Map<String,Double> rulesList = new TreeMap<String,Double>();
	//String[] tokens = {"a","b","c","d","e","f"};
	
	CheckForFalses checker;
	
	@Before
	public void setUp() {
		rulesList.put("a", 2.0);
		rulesList.put("b",1.0);
		checker = new CheckForFalses(rulesList);
	}
	
	@Test
	public void testFalses(){
		Assert.assertEquals(2.0, rulesList.get("a"), 0.000000001);
		Assert.assertEquals(1.0, rulesList.get("b"), 0.000000001);
		assertTrue(checker.isFalsePositive(5.01));
		assertFalse(checker.isFalsePositive(4.9));
		assertTrue(checker.isFalseNegative(-5.01));
		assertFalse(checker.isFalseNegative(-4.9));
	}
	
	@Before
	public void setUpFalseCounting() {
		checker = new CheckForFalses(rulesList);
	}
	
	@Test
	public void testFalseValues() {
		int numberFP = 20;
		int numberFN = 15;
		checker.setFP(numberFP);
		checker.setFN(numberFN);
		assertEquals(numberFP, checker.getFP());
		assertEquals(numberFN, checker.getFN());
	}
	
	@Before
	public void setUpCalculateFP() {
		checker = new CheckForFalses(rulesList);
	}
	
	@Test
	public void testCalculateFP() {
		String type = "ham";
		Message message = new Message("test");
		Rule a = new Rule("a", 1.0);
		Rule b = new Rule("b", 2.0);
		Rule c = new Rule("c", 1.3);
		Rule d = new Rule("d", 0.3);
		Rule e = new Rule("e", 0.0);
		Rule f = new Rule("f", 1.0);
		message.getMessages().add(a);
		message.getMessages().add(b);
		message.getMessages().add(c);
		message.getMessages().add(d);
		message.getMessages().add(e);
		message.getMessages().add(f);
		checker.calculateFalseValues(type, message);
		
		Message message2 = new Message("test");
		Rule g = new Rule("g", -1.0);
		Rule h = new Rule("h", -2.0);
		Rule i = new Rule("i", -1.3);
		Rule j = new Rule("j", -0.3);
		Rule k = new Rule("k", -0.0);
		Rule l = new Rule("l", -1.0);
		message2.getMessages().add(g);
		message2.getMessages().add(h);
		message2.getMessages().add(i);
		message2.getMessages().add(j);
		message2.getMessages().add(k);
		message2.getMessages().add(l);
		checker.calculateFalseValues(type, message2);
	}
	
	@Before
	public void setUpCalculateFN() {
		checker = new CheckForFalses(rulesList);
	}
	
	@Test
	public void testCalculateFN() {
		String type = "spam";
		Message message = new Message("test");
		Rule a = new Rule("a", 1.0);
		Rule b = new Rule("b", 2.0);
		Rule c = new Rule("c", 1.3);
		Rule d = new Rule("d", 0.3);
		Rule e = new Rule("e", 0.0);
		Rule f = new Rule("f", 1.0);
		message.getMessages().add(a);
		message.getMessages().add(b);
		message.getMessages().add(c);
		message.getMessages().add(d);
		message.getMessages().add(e);
		message.getMessages().add(f);
		checker.calculateFalseValues(type, message);
		
		Message message2 = new Message("test");
		Rule g = new Rule("g", -1.0);
		Rule h = new Rule("h", -2.0);
		Rule i = new Rule("i", -1.3);
		Rule j = new Rule("j", -0.3);
		Rule k = new Rule("k", 0.0);
		Rule l = new Rule("l", -4.0);
		message2.getMessages().add(g);
		message2.getMessages().add(h);
		message2.getMessages().add(i);
		message2.getMessages().add(j);
		message2.getMessages().add(k);
		message2.getMessages().add(l);
		checker.calculateFalseValues(type, message2);
	}
}
