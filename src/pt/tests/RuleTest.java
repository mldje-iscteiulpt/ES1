package pt.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import pt.objects.Rule;

/**
 * Classe JUnit que faz o teste para a classe Rule
 * @author ES1-2017-IC2-82
 */
public class RuleTest {
	
	/** Atributo Rule1 que permite testar  */
	Rule rule1;
	/** Atributo Rule2 que permite testar  */
	Rule rule2;
	/** Atributo Rule2 que permite testar  */
	Rule rule3;
	/** Atributo Rule3 que permite testar  */
	Rule rule4;
	/** String que serve para denominar a Rule */
	String name1 = "a";
	/** String que serve para denominar a Rule */
	String name2 ="b";
	/** Double que serve para denominar o peso da Rule */
	Double weight1 = 1.0;
	/** Double que serve para denominar o peso da Rule */
	Double weight2 = 2.0;
	
	
	/**
	 * Instaciação das regras a serem utilizadas no teste
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		rule1=new Rule(name1,weight1);
		rule2=new Rule(name2,weight2);
		rule4=new Rule(name1,weight1);
		
	}
	
	/**
	 *Verifica o assertNull ou assertNotNull 
	 */
	@Test
	public void testNullandNotNull() {
		assertNotNull(rule1);
		assertNull(rule3);
	}
	
	/**
	 * testa a equalidade dos getname´s
	 */
	@Test
	public void testEqualities() {
		assertEquals(rule1.getName(),rule4.getName());
		assertNotEquals(rule1.getName(),rule2.getName());
			
	}
	/**
	 * faz a verificação dos getnames
	 */
	@Test
	public void testGetName() {
		rule1.getName();
		rule2.getName();
	}
	
	/**
	 * faz a verificação para ver se os pesos são ou não iguais
	 */
	@Test
	public void testGetValue() {
		rule1.getWeight();
		rule2.getWeight();
		rule4.getWeight();
		assertNotSame(rule1.getWeight(),rule2.getWeight());
		
	}
	

}
