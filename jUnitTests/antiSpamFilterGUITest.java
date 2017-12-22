package pt.iscte.es1.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.iscte.es1.antiSpamFilter.AntiSpamFilterAutomaticConfiguration;
import pt.iscte.es1.antiSpamFilter.AntiSpamFilterManager;
import pt.iscte.es1.antiSpamFilter.AntiSpamFilterProblem;
import pt.iscte.es1.antiSpamFilterGUI.AntiSpamFilterMenu;
import pt.iscte.es1.antiSpamFilterGUI.MenuSecundario;
import pt.iscte.es1.objects.Message;
import pt.iscte.es1.reader.DataReader;
import pt.iscte.es1.reader.FileChooser;

/**
 * @author Mario
 *
 */
public class antiSpamFilterGUITest {
	AntiSpamFilterMenu GUI;
	MenuSecundario menu;
	DataReader reader;
	String spamFile;
	String hamFile;
	String rulesFile;
	DataReader dataReader;
	
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
		GUI.setRulesFilePath("/AntiSpamConfigurationForProfessionalMailbox/rules.cf");
		//GUI.setRulesFilePath("c:\testRules.cf");
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testRulesFileNamePath() {
		Assert.assertEquals("/AntiSpamConfigurationForProfessionalMailbox/rules.cf", GUI.getRulesFile());
	}
	
	@SuppressWarnings("static-access")
	@Before
	public void setUpHamFilePath() throws Exception {
		GUI.setHamFilePath("/AntiSpamConfigurationForProfessionalMailbox/ham.log");
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testHamFileNamePath() {
		Assert.assertEquals("/AntiSpamConfigurationForProfessionalMailbox/ham.log", GUI.getHamFile());
	}
	
	@SuppressWarnings("static-access")
	@Before
	public void setUpSpamFilePath() throws Exception {
		GUI.setSpamFilePath("/AntiSpamConfigurationForProfessionalMailbox/spam.log");
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testSpamFileNamePath() {
		Assert.assertEquals("/AntiSpamConfigurationForProfessionalMailbox/spam.log", GUI.getSpamFile());
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
	
//	@Test
//	public void testGetButtons() {
//		for(int i=0; i != GUI.getButtonFromMenuPrincipal().size(); i++) {
//			//if(!(GUI.getButtonFromMenuPrincipal().get(i).getText().equals("Browse"))) {
//				GUI.getButtonFromMenuPrincipal().get(i).doClick();
//			//}
//			GUI.getButtonFromMenuPrincipal().get(i).doClick();
//			Assert.assertTrue(GUI.getButtonFromMenuPrincipal().get(i).isEnabled());
//			//System.out.println("Nome: " + GUI.getButtonFromMenuPrincipal().get(i).getName() + " isEnable: " + GUI.getButtonFromMenuPrincipal().get(i).isEnabled());
//		}
//		//Assert.assertFalse(condition);
//	}
	
	
	@Test
	public void testGetButtons() {
		GUI.setSpamFilePath(null);
		GUI.setHamFilePath(null);
		GUI.setRulesFilePath(null);
		GUI.getDefaultFiles();
		for(int i=0; i != GUI.getButtonFromMenuPrincipal().size(); i++) {
			GUI.getButtonFromMenuPrincipal().get(i).doClick();
			Assert.assertTrue(GUI.getButtonFromMenuPrincipal().get(i).isEnabled());
			//if(GUI.getButtonFromMenuPrincipal().get(i) instanceof FileChooser)
			//System.out.println("Nome: " + GUI.getButtonFromMenuPrincipal().get(i).getName() + " isEnable: " + GUI.getButtonFromMenuPrincipal().get(i).isEnabled());
		}
		//Assert.assertFalse(condition);
	}
	
	@Test
	public void testMenuSecundarioAuto() {
		AntiSpamFilterMenu GUI = new AntiSpamFilterMenu();
		MenuSecundario menu  = new MenuSecundario("auto");
		GUI.setSpamFilePath(null);
		GUI.setHamFilePath(null);
		GUI.setRulesFilePath(null);
		GUI.getDefaultFiles();
		for(int i=0; i != menu.getButtonFromMenuSecundario().size(); i++) {
			menu.getButtonFromMenuSecundario().get(i).doClick();
			Assert.assertTrue(menu.getButtonFromMenuSecundario().get(i).isEnabled());
			//System.out.println("Nome: " + GUI.getButtonFromMenuPrincipal().get(i).getName() + " isEnable: " + GUI.getButtonFromMenuPrincipal().get(i).isEnabled());
		}
		//Assert.assertFalse(condition);	
	}

	@Test
	public void testCreateTable() {
		MenuSecundario menuSecundario = new MenuSecundario("auto");
		menuSecundario.createTable();
	}
	
	@Test
	public void testMenuSecundarioManual() {
		AntiSpamFilterMenu GUI = new AntiSpamFilterMenu();
		GUI.setSpamFilePath(null);
		GUI.setHamFilePath(null);
		GUI.setRulesFilePath(null);
		GUI.getDefaultFiles();
		MenuSecundario menu = new MenuSecundario("manual");
		for(int i=0; i != menu.getButtonFromMenuSecundario().size(); i++) {
			menu.getButtonFromMenuSecundario().get(i).doClick();
			Assert.assertTrue(menu.getButtonFromMenuSecundario().get(i).isEnabled());
			//System.out.println("Nome: " + GUI.getButtonFromMenuPrincipal().get(i).getName() + " isEnable: " + GUI.getButtonFromMenuPrincipal().get(i).isEnabled());
		}
		//Assert.assertFalse(condition);	
	}
	
	@After
	public void closeGUI() {
		GUI.dispose();
	}
	
	
}