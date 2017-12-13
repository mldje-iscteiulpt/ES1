package pt.reader;

import java.util.Map;
import pt.objects.Message;

/**
 * Classe que contabiliza o peso atribuído a cada regra presente nos ficheiros ham.log e spam.log, contabilizando de seguida
 * o número de Falsos Positivos e Falsos Negativos.
 * @author ES1-2017-IC2-82
 *
 */

public class CheckForFalses {

	private Map<String,Double> rulesList;

	/** Número de Falsos Positivos */
	private int FP = 0;
	/** Número de Falsos Negativos */
	private int FN = 0;
	
	/**
	 * Construtor da classe responsável por verificar falsos positivos e falsos negativos
	 * @param rulesList
	 */
	
	//ALTERAR - tem de receber mapa
	public CheckForFalses(Map<String,Double> rulesList) {
		this.rulesList = rulesList;
		FP = 0;
		FN = 0;
	}
	
	/**
	 * Calcula o número de falsos positivos e falsos negativos
	 * @param type - String que indica se é ham ou spam
	 * @param tokens - String que é linha a analisar
	 */
	
public void getFalse(String type, Message message) {
		
		double weightTotal = 0.0;
		//ALTERAR - vai diretamente ao Mapa e obtém os pesos - ESTE é equivalente ao CALCULATE
		for (int i = 0; i < message.getMessages().size(); i++) {
			weightTotal+=message.getMessages().get(i).getWeight();
		}
		
		if(type.equals("ham")) {
			if(isFalsePositive(weightTotal)) {
				FP++;
			}
			System.out.println("HAM:");
		}
		else if(type.equals("spam")) {
			if(isFalseNegative(weightTotal)) {
				FN++;
			}
			System.out.println("\nSPAM:");
		}
		
		System.out.println("False Positives: " + FP);
		System.out.println("False Negatives: " + FN);
		
	}
	
	/**
	 * Avalia se se trata de falso positivo
	 * @param weightTotal - double com o peso total do item do ham
	 * @return  boolean - indica se é um falso positivo ou não
	 */
	public boolean isFalsePositive(double weightTotal){
		if(weightTotal > 5.0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Avalia se se trata de falso negativo
	 * @param weightTotal - double com o peso total do item do spam
	 * @return  boolean - indica se é um falso negativo ou não
	 */
	public boolean isFalseNegative(double weightTotal){
		if(weightTotal < -5.0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Retorna número de falsos positivos encontrados na configuração atual
	 * @return int - retorna número de falsos positivos
	 */
	public int getFP() {
		return FP;
	}
	
	/**
	 * Retorna número de falsos negativos encontrados na configuração atual
	 * @return int - retorna número de falsos negativos
	 */
	public int getFN() {
		return FN;
	}
	
//	/*
//	 * Para testar a classe - APAGAR ISTO, ERRADO
//	 */
//	public static void main(String[] args) {
//		ArrayList<Rule> rulesList = new ArrayList<Rule>();
//		rulesList.add(new Rule("a", 1.0));
//		rulesList.add(new Rule("b", 2.0));
//		rulesList.add(new Rule("c", 1.3));
//		rulesList.add(new Rule("d", 0.3));
//		rulesList.add(new Rule("e", 0.0));
//		rulesList.add(new Rule("f", 1.0));
//		
//		String[] tokens = {"a","b","c","d","e","f"};
//		
//		//CheckForFalses checker = new CheckForFalses(rulesList);
//		//checker.getFalse("ham", tokens);
//		//checker.getFalse("spam", tokens);
//	}
}
