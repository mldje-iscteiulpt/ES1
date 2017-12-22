package pt.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import pt.objects.Message;

/**
 * Classe JUnit que faz o teste para a classe Message
 * @author ES1-2017-IC2-82
 *
 */
public class MessageTest {
	
	/** Atributo Message1 que permite testar  */
	Message message1;
	/** Atributo Message2 que permite testar  */
	Message message2;
	/** Atributo Message3 que permite testar  */
	Message message3;
	/** Atributo Message4 que permite testar  */
	Message message4;
	/** Atributo String name que permite denominar a message  */
	String name = "a";
	/** Atributo String name1 que permite denominar a message  */
	String name1 = "b";

	/**
	 *Instaciação das mensagens a serem utilizadas no teste
	 * 
	 */
	@Before
	public void setUp() throws Exception {
		message1=new Message(name);
		message2=new Message(name);
		message4=new Message(name1);
	}
	
	/**
	 * Verifica o assertNull ou assertNotNull 
	 */
	@Test
	public void testNullandNotNull() {
		assertNotNull(message1);
		assertNull(message3);
	}
	
	/**
	 * testa a equalidade dos getname´s
	 */
	@Test
	public void testEqualities() {
		assertEquals(message1.getName(),message2.getName());
		assertNotEquals(message1.getName(),message4.getName());
	}
	/**
	 * verifica os getname´s
	 */
	@Test
	public void testGetName() {
		message2.getName();
		message4.getName();
	}
	
	/**
	 * verifica se os getValue são ou não iguais
	 */
	@Test
	public void testGetValue() {
		message1.getValue();
		message2.getValue();
		assertNotSame(message1.getValue(),message2.getValue());
		
	}
	
	/**
	 * faz a verificação para o metodo AddValue
	 */
	@Test
	public void testAddValue() {
		message1.addToValue(1.0);
		assertNotNull(message1);
	}
	
	/**
	 * verifica se os getMessages são ou nao iguais
	 */
	@Test
	public void testGetMesages() {
		message1.getMessages();
		message2.getMessages();
		assertEquals(message1.getMessages(),message2.getMessages());
	}
}
