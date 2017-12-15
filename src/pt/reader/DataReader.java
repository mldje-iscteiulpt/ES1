package pt.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pt.objects.Rules;


public class DataReader {
	
//	private static Map<String,Double> rules = new TreeMap<String,Double>();
//	private static int FP = 0;
//	private static int FN = 0;
	
	private CheckForFalses checker;
	private ArrayList<Rules> rulesList = new ArrayList<Rules>();
	public ArrayList<Rules> getRulesList(){
		return rulesList;
	}
	
	/**
	 * Obter estrutura de dados com regras e respetivos pesos.
	 */
	
//	public Map<String,Double> getRules(){
//		return rules;
//	}
	
	/**
	 * Ler ficheiro com regras (pode incluir os respetivos pesos).
	 */
	
	public void readRules(String filePathRules, DefaultTableModel model, JTable table) {
		BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePathRules));
            String line;
            while ((line = br.readLine()) != null) {
            	String parts[] = line.split(" ");
            	if(parts.length>1) {
            		model.addRow(new Object[] {parts[0], parts[1]});
            	}else {
            		model.addRow(new Object[] {parts[0], 0.0});      	
            	}
            	table.setModel(model);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
	}
	
	
	/**
	 * Ler dados ham ou spam do ficheiro..
	 */
	
	public void readInfoFile(String filePath, String fileType) {
		checker = new CheckForFalses(rulesList);
		
		BufferedReader br = null;
		String[] tokens = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
            	String delims = "[ ]+|\\t";
            	tokens = line.split(delims);
//            	for(String tok: tokens)System.out.println(tok + "");
            	checker.getFalse(fileType, tokens);
            	
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
	}
	
	public CheckForFalses getChecker() {
		return checker;
	}
	
}
