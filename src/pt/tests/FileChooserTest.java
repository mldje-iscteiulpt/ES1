package pt.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Container;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;

import pt.reader.FileChooser;

public class FileChooserTest {

	/** Atributo FileChooser1 que permite testar  */
	FileChooser f1;
	/** Atributo FileChooser2 que permite testar  */
	FileChooser f2;
	/** Atributo FileChooser3 que permite testar  */
	FileChooser f3;
	/** Atributo FileChooser4 que permite testar  */
	FileChooser f4;
	/** Atributo String f1 que permite nomear o FileChooser  */
	String filename1="f1";
	/** Atributo String f2 que permite nomear o FileChooser  */
	String filename2="f2";
	/** Atributo String f3 que permite nomear o FileChooser  */
	String filename3="f3";
	/** Atributo Container parent1 */
	Container parent1;
	/** Atributo Container parent2  */
	Container parent2;
	/** Atributo textField1  */
	JTextField textField1;
	/** Atributo textField2  */
	JTextField textField2;

	
	/**
	 *Instaciação de fileChooser a serem utilizadas no teste
	 * 
	 */
	@Before
	public void setUp() throws Exception {
		f1=new FileChooser(filename1,parent1,textField1);
		f2=new FileChooser(filename2,parent2,textField2);
		f3=new FileChooser(filename1,parent1,textField1);
	}

	/**
	 * Verifica o assertNull ou assertNotNull
	 */
	@Test
	public void testNullandNotNull() {
		assertNotNull(f1);
		assertNotNull(f2);
		assertNotNull(f3);
		assertNull(f4);
	}

	/**
	 * Verifica o metodo choose da classe filechooser
	 */
	@Test
	public void testChoose() {
		f1.choose();
	}
	
	/**
	 * verifica a veracidade do auxiliar booleano do metodo choose
	 */
	@Test
	public void testAuxChooser() {
		assertTrue(f1.auxChoose(JFileChooser.APPROVE_OPTION));
		assertFalse(f1.auxChoose(JFileChooser.CANCEL_OPTION));
	}
}