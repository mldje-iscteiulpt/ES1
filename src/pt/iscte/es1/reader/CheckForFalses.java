package pt.iscte.es1.reader;

import java.util.Map;

import pt.iscte.es1.objects.Message;

/**
 * Classe que contabiliza o peso atribuído a cada regra presente nos ficheiros ham.log e spam.log, contabilizando de seguida
 * o número de Falsos Positivos e Falsos Negativos.
 * @author ES1-2017-IC2-82
 *
 */

public class CheckForFalses {

	/** Estrutura de dados onde são armazenadas as regras e os seus respetivos pesos.*/
	private Map<String,Double> rulesList;
	/** Número de Falsos Positivos */
	private int FP;
	/** Número de Falsos Negativos */
	private int FN;

	/**
	 * Construtor da classe responsável por verificar falsos positivos e falsos negativos
	 * @param rulesList - lista de regras
	 */
	public CheckForFalses(Map<String,Double> rulesList) {
		this.setRulesList(rulesList);
		FP = 0;
		FN = 0;
	}

	/**
	 * Calcula o número de falsos positivos e falsos negativos
	 * @param type - String que indica se é ham ou spam
	 * @param message - Mensagem a analisar
	 */
	public void calculateFalseValues(String type, Message message) {
		double weightTotal = 0.0;
		for (int i = 0; i < message.getMessages().size(); i++) {
			weightTotal+=message.getMessages().get(i).getWeight();
		}
		if(type.equals("ham")) {
			if(isFalsePositive(weightTotal)) {
				FP++;
			}
		}
		if(type.equals("spam")) {
			if(isFalseNegative(weightTotal)) {
				FN++;
			}
		}
	}

	/**
	 * Avalia se se trata de falso positivo.
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
	 * Avalia se se trata de falso negativo.
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
	 * Retorna número de falsos positivos encontrados na configuração atual.
	 * @return int - retorna número de falsos positivos
	 */
	public int getFP() {
		return FP;
	}

	/**
	 * Retorna número de falsos negativos encontrados na configuração atual.
	 * @return int - retorna número de falsos negativos
	 */
	public int getFN() {
		return FN;
	}

	/**
	 * Definir número de Falsos Positivos.
	 * @param n - número de Falsos Positivos
	 */
	public void setFP(int n) {
		this.FP = n;
	}

	/**
	 * Definir número de Falsos Negativos.
	 * @param n - número de Falsos Negativos
	 */
	public void setFN(int n) {
		this.FN = n;
	}

	/**
	 * Definir a lista de regras e pesos destas.
	 * @param rulesList - lista de regras e seus respetivos pesos
	 */
	public void setRulesList(Map<String,Double> rulesList) {
		this.rulesList = rulesList;
	}
}
