/**
 * 
 */
package pt.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.objects.Message;
import pt.reader.CheckForFalses;
import pt.reader.DataReader;


public class DataReaderTest {

	/** Atributo DataReader1 que permite testar  */
	DataReader data1;
	/** Atributo DataReader2 que permite testar  */
	DataReader data2;
	/** Atributo DataReader3 que permite testar  */
	DataReader data3;
	/** Atributo CheckForFalses que permite testar os metodos que o usam  */
	CheckForFalses checker;
	/** Atributo Map<String,Double> que permite testar o metodo readRules  */
	Map<String,Double> rules;
	/** Atributo List<Message> hamList que permite testar o metodo readRules  */
	List<Message> hamList;
	/** Atributo List<Message> spamList que permite testar o metodo readRules  */
	List<Message> spamList;
	/** Atributo que permite definir o tipo de file */
	String fileType;
	/** Atributo que permite definir o tipo de file(1) */
	String fileType1;
	/** Atributo que permite definir o caminho ate ao file */
	String filePath;
	/** Atributo Message que permite testar */
	Message message;
	/** Atributo que permite definir o caminho para o file que contém as rules */
	String filePathRules;
	/** DefaultTableModel*/
	DefaultTableModel model;
	/**JTable*/
	JTable table;


	/**
	 * Instaciação de DataReader()
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		data1 = new DataReader();
		data2 = new DataReader();
	}

	/**
	 * Verifica o assertNull ou assertNotNull
	 */
	@Test
	public void testNullandNotNull() {
		assertNotNull(data1);
		assertNull(data3);
	}


	/**
	 * Instaciação de datareader
	 */
	@Before
	public void setUpGetChecker() {
		data1 = new DataReader();
	}

	/**
	 * verificação do checkforfalses	
	 */
	@Test
	public void testGetChecker() {
		rules = new TreeMap<String,Double>();
		CheckForFalses checker = new CheckForFalses(rules);
		assertNotEquals(data1.getChecker(), checker);
	}

	/**
	 * Instaciação de rules
	 */
	@Before
	public void setUpAddToRules() {
		rules = new TreeMap<String,Double>();
	}

	/**
	 * verificação do adicionar À lista rules
	 */
	@Test
	public void testAddToRules() {
		data1.addToRules("test", 0.0);
		assertEquals(data1.getRules().size(),1);
	}

	/**
	 * instaciação da hamList
	 */
	@Before
	public void setUpGetHamList() {
		hamList = data1.getHamList();
	}

	/**
	 * verificação do adicionar à lista ham
	 */
	@Test
	public void testGetHamList() {
		hamList.add(new Message("testMessage"));
		assertEquals(hamList.get(0).getName(),"testMessage");
	}
	
	
	@After
	public void afterGetHamList() {
		hamList.clear();
	}

	/**
	 * instaciação do spamList  
	 */
	@Before
	public void setUpGetSpamList() {
		spamList = data1.getSpamList();
	}

	/**
	 * verificação do adicionar à lista spam
	 */
	@Test
	public void testGetSpamList() {
		spamList.add(new Message("testMessage"));
		assertEquals(spamList.get(0).getName(),"testMessage");
	}

	/**
	 * garante que a lista spam fica vazia
	 */
	@After
	public void afterGetSpamList() {
		spamList.clear();
	}

	/**
	 * instaciação da mensagem e do tipo de file
	 */
	@Before
	public void setUpAddMessageToList() {
		message = new Message("messageTest");
		fileType1 = "spam";
	
	}

	/**
	 * verificação do adicionar de mensagem à lista
	 */
	
	@Test
	public void testAddMessageToList() {
		data1.addMessageToList(message, fileType1);
		assertEquals(data1.getSpamList().get(0).getName(), message.getName());
	}

	@After
	public void afterAddMessageToList() {
		data1.getSpamList().clear();
	}

	/**
	 * instaciação da mensagem e do tipo de file neste caso ham
	 */
	@Before
	public void setUpAddMessageToList2() {
		message = new Message("messageTest");
		fileType = "ham";
	}

	/**
	 * verificação do adicionar À lista
	 */
	@Test
	public void testAddMessageToList2() {
		data1.addMessageToList(message, fileType);
		assertEquals(data1.getHamList().get(0).getName(), message.getName());
	}

	@After
	public void afterAddMessageToList2() {
		data1.getHamList().clear();

	}

	/**
	 * instaciação do filepathRules, model e table
	 */
	@Before
	public void setUpReadRules() {
		filePathRules = "./JUnitTestInput/TestFile";
		model = new DefaultTableModel(2,0);
		table = new JTable();
	}

	/**
	 * verifica o readRules
	 */
	@Test
	public void testReadRules() {
		data1.readRules(filePathRules, model, table);
	}
	
	
	@Before
	public void setUpReadInfoFile() {
		fileType = "ham";
		filePath = "./JUnitTestInput/TestFile";
	}

	@Test
	public void testReadInfoFileHam() {
		data1.readInfoFile(filePath, fileType);
		assertEquals(fileType, "ham");
	}
	
	@Before
	public void setUpReadInfoFile2() {
		fileType1 = "spam";
		filePath = "./JUnitTestInput/TestFile";
	}

	@Test
	public void testReadInfoFileSpam() {
		data1.readInfoFile(filePath, fileType1);
		assertEquals(fileType1, "spam");
	}
}







