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
 * Classe JUnit responsável por testar a interface gráfica do filtro anti-spam.
 * @author ES1-2017-IC2-82
 *
 */
public class antiSpamFilterGUITest {
	/** Objeto da classe AntiSpamFilterMenu. */
	AntiSpamFilterMenu GUI;
	/** Objeto da classe Menú Secundário. */
	MenuSecundario menu;
	/** Instância da classe responsável pela leitura de dados de regras, ham e spam. */
	DataReader reader;
	/** Caminho de ficheiro que contém spam. */
	String spamFile;
	/** Caminho de ficheiro que contém ham. */
	String hamFile;
	/** Caminho de ficheiro que contém as regras. */
	String rulesFile;
	
	/**
	 * Instanciação de objeto da classe AntiSpamFilterMenu.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		GUI = new AntiSpamFilterMenu();
	}

	/**
	 * Adicionar elementos da interface gráfica.
	 */
	@Test
	public void test() {
		GUI.addElements();
	}
	
	/**
	 * Definir caminho de ficheiro de regras.
	 * @throws Exception
	 */
	
	@Before
	public void setUpRulesFilePath() throws Exception {
		GUI.setRulesFilePath("/AntiSpamConfigurationForProfessionalMailbox/rules.cf");

	}
	
	/**
	 * Testar o caminho para o ficheiro de regras.
	 */
	
	@SuppressWarnings("static-access")
	@Test
	public void testRulesFileNamePath() {
		Assert.assertEquals("/AntiSpamConfigurationForProfessionalMailbox/rules.cf", GUI.getRulesFile());
	}
	
	/**
	 * Definir caminho de ficheiro ham.
	 * @throws Exception
	 */
	
	@SuppressWarnings("static-access")
	@Before
	public void setUpHamFilePath() throws Exception {
		GUI.setHamFilePath("/AntiSpamConfigurationForProfessionalMailbox/ham.log");
	}
	
	/**
	 * Testar caminho para ficheiro que contém ham.
	 */
	
	@SuppressWarnings("static-access")
	@Test
	public void testHamFileNamePath() {
		Assert.assertEquals("/AntiSpamConfigurationForProfessionalMailbox/ham.log", GUI.getHamFile());
	}
	
	/**
	 * Definir caminho de ficheiro que contém spam.
	 * @throws Exception
	 */
	
	@SuppressWarnings("static-access")
	@Before
	public void setUpSpamFilePath() throws Exception {
		GUI.setSpamFilePath("/AntiSpamConfigurationForProfessionalMailbox/spam.log");
	}
	
	/**
	 * Testar caminho para ficheiro spam.
	 */
	
	@SuppressWarnings("static-access")
	@Test
	public void testSpamFileNamePath() {
		Assert.assertEquals("/AntiSpamConfigurationForProfessionalMailbox/spam.log", GUI.getSpamFile());
	}
	
	/**
	 * Definir instância da classe DataReader.
	 */
	@SuppressWarnings("static-access")
	@Before
	public void setUpGetDataReader() {
		reader = GUI.getDatareader();
	}
	
	/** Testar instância da classe DataReader.
	
	@SuppressWarnings("static-access")
	@Test
	public void testGetDataReader() {
		Assert.assertNotEquals(GUI.getDatareader(), reader);
	}
	
	/**
	 * Testagem de botões da interface gráfica.
	 */
	
	@Test
	public void testGetButtons() {
		GUI.setSpamFilePath(null);
		GUI.setHamFilePath(null);
		GUI.setRulesFilePath(null);
		GUI.getDefaultFiles();
		for(int i=0; i != GUI.getButtonFromMenuPrincipal().size(); i++) {
			GUI.getButtonFromMenuPrincipal().get(i).doClick();
			Assert.assertTrue(GUI.getButtonFromMenuPrincipal().get(i).isEnabled());	
		}
	}
	
	/**
	 * Testar o menú secundário referente à configuração automática.
	 */
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
		}
	}

	/**
	 * Testar a criação de tabela em meno secundário.
	 */
	@Test
	public void testCreateTable() {
		MenuSecundario menuSecundario = new MenuSecundario("auto");
		menuSecundario.createTable();
	}
	
	/**
	 * Testar botoões pertencentes a menú secundário manual.
	 */
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
		}
	}
	
	/**
	 * Terminar a interface gráfica após o término dos testes.
	 */
	
	@After
	public void closeGUI() {
		GUI.dispose();
	}
	
	
}