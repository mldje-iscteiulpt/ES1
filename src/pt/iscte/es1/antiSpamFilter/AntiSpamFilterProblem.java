package pt.iscte.es1.antiSpamFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import pt.iscte.es1.objects.Message;

/** Classe que, através da lista de regras, de ham e de spam gera um Objecto Solution que será utilizado na produção 
 * de uma solução optimizada para o problema apresentado.
 * @author Mario
 *
 */
@SuppressWarnings("serial")
public class AntiSpamFilterProblem extends AbstractDoubleProblem {

	/** Lista contendo ham. */
	private final List<Message> hamList;
	/** Lista contendo spam */
	private final List<Message> spamList;
	/** Estrutura de dados que contém as regras. */
	private final Map<String,Double> rules;
	/** Variável que contém o número de regras */
	private static int numberOfRules;


	/** Instanciação de objeto da classe AntiSpamFilterProblem
	 * 
	 * @param hamList - Lista de ham.
	 * @param spamList - Lista de spam.
	 * @param rules - Lista de regras.
	 */
	public AntiSpamFilterProblem(List<Message> hamList, List<Message> spamList, Map<String,Double> rules) {
		this.rules = rules;
		this.hamList = hamList;
		this.spamList = spamList;

		numberOfRules = rules.size();

		setNumberOfVariables(numberOfRules);
		setNumberOfObjectives(2);
		setName("AntiSpamFilterProblem");

		List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
		List<Double> upperLimit = new ArrayList<>(getNumberOfVariables()) ;

		for (int i = 0; i < getNumberOfVariables(); i++) {
			lowerLimit.add(-5.0);
			upperLimit.add(5.0);
		}

		setLowerLimit(lowerLimit);
		setUpperLimit(upperLimit);
	}

	/**
	 * Método responsável por avaliar a solução gerada e que contabiliza o número de falsos positivos e
	 * falsos negativos.
	 */
	@Override
	public void evaluate(DoubleSolution solution){
		int FN = 0;
		int FP = 0;
		String auxName;
		Double auxValue;
		Double sum = 0.0;

		for(int spamIndex = 0; spamIndex != spamList.size(); spamIndex++) {
			for(int messageIndex = 0; messageIndex != spamList.get(spamIndex).getMessages().size(); messageIndex++) {
				auxName = spamList.get(spamIndex).getMessages().get(messageIndex).getName();
				if(rules.get(auxName) != null) {
					sum+=solution.getVariableValue(spamIndex);
				}
			}
			if(sum < -5) {
				FN++;
			}
		}

		for(int hamIndex = 0; hamIndex!= hamList.size(); hamIndex++) {
			for(int messageIndex = 0; messageIndex != hamList.get(hamIndex).getMessages().size(); messageIndex++) {
				auxName = hamList.get(hamIndex).getMessages().get(messageIndex).getName();
				auxValue = rules.get(auxName);
				if(auxValue != null) {
					sum+=solution.getVariableValue(messageIndex);
				}
			}
			if(sum > 5) {
				FP++;
			}
		}

		solution.setObjective(0, FN);
		solution.setObjective(1, FP);

	}

}
