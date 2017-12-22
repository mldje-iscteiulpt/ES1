package pt.iscte.es1.objects;

/**
 * Classe que define uma regra pelo seu nome e pelo seu peso.
 * @author ES1-2017-IC2-82
 *
 */

public class Rule {

	/** Nome da regra */
	private String name;
	/** Peso da regra */
	private double weight;

	/**
	 * Construtor de uma regra.
	 * @param name - String com o nome da regra.
	 * @param weight - Double com o peso correspondente à regra.
	 */

	public Rule(String name, double weight) {
		this.name = name;
		this.weight = weight;
	}

	/**
	 * Devolve o nome da regra.
	 * @return name - String correspondente ao nome
	 */

	public String getName() {
		return name;
	}

	/**
	 * Devolve o peso da regra.
	 * @return weight - double correspondente ao peso da regra
	 */

	public double getWeight() {
		return weight;
	}

}
