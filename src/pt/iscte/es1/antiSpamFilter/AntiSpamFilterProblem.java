package pt.iscte.es1.antiSpamFilter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import pt.iscte.es1.objects.Message;
import pt.iscte.es1.objects.Rule;
import pt.iscte.es1.reader.CheckForFalses;

public class AntiSpamFilterProblem extends AbstractDoubleProblem {
	
	private final List<Message> hamList;
	private final List<Message> spamList;
	private final Map<String,Double> rules;
	private static int numberOfRules;

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
	  
	  @Override
	  public void evaluate(DoubleSolution solution){
		  int FN = 0;
		  int FP = 0;
		  String auxName;
		  Double auxValue;
		  Double sum = 0.0;
		  
		//percorre lista do spam e vai adicionar os pesos no solution
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
		  
		  //percorre lista ham e vai adicionar os pesos no solution
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
