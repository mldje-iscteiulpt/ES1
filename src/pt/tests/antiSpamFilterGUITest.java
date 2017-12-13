package pt.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.antiSpamFilterGUI.AntiSpamFilterMenu;
import pt.reader.DataReader;

/**
 * @author Mario
 *
 */
public class antiSpamFilterGUITest {
	AntiSpamFilterMenu GUI;
	DataReader reader;
	String spamFile;
	String hamFile;
	String rulesFile;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		GUI = new AntiSpamFilterMenu();
	}

	@Test
	public void test() {
		GUI.addElements();
		//reader.readRules(filePathRules);
		//fail("Not yet implemented");
	}
	
	@Before
	public void setUpRulesFilePath() throws Exception {
		GUI.setRulesFilePath("c:\testRules.cf");
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testRulesFileNamePath() {
		Assert.assertEquals("c:\testRules.cf", GUI.getRulesFile());
	}
	
	@SuppressWarnings("static-access")
	@Before
	public void setUpHamFilePath() throws Exception {
		GUI.setHamFilePath("c:\testHam.log");
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testHamFileNamePath() {
		Assert.assertEquals("c:\testHam.log", GUI.getHamFile());
	}
	
	@SuppressWarnings("static-access")
	@Before
	public void setUpSpamFilePath() throws Exception {
		GUI.setSpamFilePath("c:\testSpam.log");
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testSpamFileNamePath() {
		Assert.assertEquals("c:\testSpam.log", GUI.getSpamFile());
	}
	
	@SuppressWarnings("static-access")
	@Before
	public void setUpGetDataReader() {
		reader = GUI.getDatareader();
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testGetDataReader() {
		Assert.assertNotEquals(GUI.getDatareader(), reader);
	}
	
	
}
