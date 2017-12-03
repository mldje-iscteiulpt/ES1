package pt.tests;

import static org.junit.Assert.*;

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
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		GUI = new AntiSpamFilterMenu();
	}

	@Test
	public void test() {
		AntiSpamFilterMenu GUI = new AntiSpamFilterMenu();
		GUI.addElements();
		//reader.readRules(filePathRules);
		//fail("Not yet implemented");
	}

}
