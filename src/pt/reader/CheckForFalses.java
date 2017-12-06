package pt.reader;

import java.util.ArrayList;

import pt.objects.Rules;

public class CheckForFalses {

	private ArrayList<Rules> rulesList;
	
	private int FP = 0;
	private int FN = 0;
	
	
	public CheckForFalses(ArrayList<Rules> rulesList) {
		this.rulesList = rulesList;
		FP = 0;
		FN = 0;
	}
	
	public void getFalse(String type, String[] tokens) {
		
		double weightTotal = 0;
		
		for (int i = 0; i < tokens.length; i++) {
			for (int j = 0; j < rulesList.size(); j++) {
				if (tokens[i].equals(rulesList.get(j).getName())) {
					weightTotal+=rulesList.get(j).getWeight();
				}
			}
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
	
	/*
	 * Para o HAM
	 */
	public boolean isFalsePositive(double weightTotal){
		if(weightTotal > 5.0) {
			return true;
		}
		return false;
	}
	
	/*
	 * Para o SPAM
	 */
	public boolean isFalseNegative(double weightTotal){
		if(weightTotal < -5.0) {
			return true;
		}
		return false;
	}
	
	
	public int getFP() {
		return FP;
	}
	
	public int getFN() {
		return FN;
	}
	
	/*
	 * Para testar a classe
	 */
	public static void main(String[] args) {
		ArrayList<Rules> rulesList = new ArrayList<Rules>();
		rulesList.add(new Rules("a", 1.0));
		rulesList.add(new Rules("b", 2.0));
		rulesList.add(new Rules("c", 1.3));
		rulesList.add(new Rules("d", 0.3));
		rulesList.add(new Rules("e", 0.0));
		rulesList.add(new Rules("f", 1.0));
		
		String[] tokens = {"a","b","c","d","e","f"};
		
		CheckForFalses checker = new CheckForFalses(rulesList);
		checker.getFalse("ham", tokens);
		checker.getFalse("spam", tokens);
	}
}
