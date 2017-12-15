package pt.objects;

public class Rules {

	private String name;
	private double weight;
	
	/**
	 * Construtor de regras
	 * @param name		Recebe o nome da regra
	 * @param weight	Recebe o peso da regra
	 */
	public Rules(String name, double weight) {
		this.name = name;
		this.weight = weight;
	}
	
	public String getName() {
		return name;
	}
	
	public double getWeight() {
		return weight;
	}
	
}
