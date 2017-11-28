package pt.reader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class DataReader {
	
	private static Map<String,Double> rules = new TreeMap<String,Double>();
	private static ArrayList<String> ham = new ArrayList<String>();
	private static ArrayList<String> spam = new ArrayList<String>();
	
	/**
	 * Obter estrutura de dados com regras e respetivos pesos.
	 */
	
	public Map<String,Double> getRules(){
		return rules;
	}
	
	/**
	 * Obter estrutura de dados com ham.
	 */
	
	public ArrayList<String> getHam(){
		return ham;
	}
	
	/**
	 * Obter estrutura de dados com spam.
	 */
	
	public ArrayList<String> getSpam(){
		return spam;
	}
	
	/**
	 * Ler ficheiro com regras (pode incluir os respetivos pesos).
	 */
	
	public void readRules(String filePathRules) {
		BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePathRules));
            String line;
            while ((line = br.readLine()) != null) {
            	String parts[] = line.split(" ");
            	if(parts.length>1) {
            		rules.put(parts[0], Double.parseDouble(parts[1]));  //add(line,0.0);
            	}else {
            		rules.put(parts[0], 0.0);            	
            	}
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
	
//	/**
//	 * Escrever no ficheiro de regras os respetivos pesos.
//	 */
//	
//	public void writeRulesWeights(String filePathRules) {
//		Iterator iterator = rules.entrySet().iterator();
//		try {
//			BufferedWriter writer = new BufferedWriter(new FileWriter(filePathRules));
//			while (iterator.hasNext()) {
//				Map.Entry pair = (Map.Entry)iterator.next();
//		        writer.write(pair.getKey() + " " + pair.getValue());
//		        System.out.println(pair.getKey() + " " + pair.getValue());
//		        writer.newLine();
//			}
//		    //Close writer
//		    writer.close();
//		} catch(Exception e) {
//		    e.printStackTrace();
//		}
//	}
	
	/**
	 * Ler dados ham ou spam do ficheiro..
	 */
	
	public void readInfoFile(String filePath, ArrayList<String> list) {
		BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
            	String delims = "[ ]+|\\t";
            	String[] tokens = line.split(delims);
            	list.add(tokens[0]);
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
}
