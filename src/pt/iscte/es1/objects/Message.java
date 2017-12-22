package pt.iscte.es1.objects;

import java.util.ArrayList;
import java.util.List;

/** Classe que define as mensagens contidas nos ficheiros ham e spam. Define pelo identificador da mensagem que é definido como um "nome",
 * e associa o respetivo valor dos pesos combinados dessa mensagem. Retorna esse valor que é depois utilizado para calcular o número de 
 * falsos positivos e falsos negativos.
 * @author ES1-2017-IC2-82
 *
 */

public class Message {

	/** String que é identificadora da Regra */
	private String name;
	/** Valor que resulta da soma de todos os pesos das regras contidas nessa mensagem */
	private double value;
	/** Estrutura que contém todas as regras dessa mensagem */
	private List<Rule> messages;


	/**
	 * Construtor que inicia uma mensagem com o respetivo identificador e um valor inicial de 0.0 respeitante aos pesos das regras. 
	 * Contém estrutura que abarca as regras verificadas nessa mensagem.
	 * @param name - String que define identificador da mensagem.
	 */
	public Message(String name) {
		this.name = name;
		value = 0.0;
		messages = new ArrayList<Rule>();
	}

	/**
	 * Método que devolve nome da regra.
	 * @return name - String com nome da regra
	 */
	public String getName() {
		return name;
	}

	/**
	 * Método que devolve o valor resultante da soma dos pesos de todas as regras contidas na mensagem.
	 * @return value - double que indica somatório dos pesos
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Método que devolve a lista com as regras contidas na mensagem.
	 * @return List<Rule> - lista com as regras contidas na mensagem
	 */
	public List<Rule> getMessages() {
		return messages;
	}

	/**
	 * Método responsável pela soma dos pesos das regras contidas na mensagem.
	 * @param weight - peso a ser somado ao total de pesos das regras contidas na mensagem
	 */
	public void addToValue(double weight) {
		this.value+=weight;
	}
}
