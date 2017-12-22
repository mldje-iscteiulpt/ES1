package pt.iscte.es1.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pt.iscte.es1.objects.Message;
import pt.iscte.es1.objects.Rule;


/**
 * Classe responsável pela leitura das regras e itens de spam e ham.
 * @author ES1-2017-IC2-82
 *
 */

public class DataReader {

	/** Mapa que contém as regras e respetivos pesos */
	private static Map<String,Double> rules;
	/** Elemento que verifica e contabiliza os Falsos Positivos e Falsos Negativos */
	private CheckForFalses checker;
	/** Lista que contém elementos de ham */
	private static List<Message> hamList = new ArrayList<Message>();
	/** Lista que contém elementos de spam */
	private static List<Message> spamList = new ArrayList<Message>();

	@SuppressWarnings("static-access")
	public DataReader() {
		checker = new CheckForFalses(rules);
		this.rules = new TreeMap<String,Double>();
	}

	/**
	 * Obter estrutura de dados com regras e respetivos pesos.
	 * @return Map<String,Double> - retorna mapa com regras e respetivos pesos
	 */

	public Map<String,Double> getRules(){
		return rules;
	}

	/**
	 * Ler ficheiro com regras (pode incluir os respetivos pesos).
	 * @param filePathRules - path para o ficheiro com as regras
	 * @param model - DefaultTableModel que é o modelo da tabela para a qual vão ser carregadas regras e pesos
	 * @param table - JTable que é tabela que inclui o modelo e onde vão ser inseridas as regras e pesos
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
					addToRules(parts[0],Double.parseDouble(parts[1]));
				}else {
					model.addRow(new Object[] {parts[0], 0.0});
					addToRules(parts[0], 0.0);
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
	 * Ler dados ham ou spam do ficheiro e onde é invocada a avaliação de falsos positivos e falsos negativos.
	 * @param filePath - String que indica path para o ficheiro
	 * @param fileType - indica se se trata de ficheiro ham ou spam
	 */

	public void readInfoFile(String filePath, String fileType) {
		BufferedReader br = null;
		String[] tokens = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = br.readLine()) != null) {
				String delims = "[ ]+|\\t";
				tokens = line.split(delims);
				Message aux = new Message(tokens[0]);
				for(int i = 1; i != tokens.length; i++) {
					Double auxValue = rules.get(tokens[i]);
					if(!(auxValue==null)){
						aux.getMessages().add(new Rule(tokens[i], rules.get(tokens[i])));
					}
				}
				addMessageToList(aux, fileType);
				checker.calculateFalseValues(fileType, aux);

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
	 * Retorna o elemento responsável por verificar número de falsos positivos e falsos negativos
	 * @return CheckForFalses - instância da classe CheckForFalses
	 */

	public CheckForFalses getChecker() {
		return checker;
	}

	/**
	 * Adicionar à lista de regras com o respetivo peso
	 * @param nameOfRule - String com o nome da regra
	 * @param weight - Double com o valor do peso
	 */

	public void addToRules(String nameOfRule, Double weight) {
		rules.put(nameOfRule, weight);
	}

	/**
	 * Adicionar mensagem à correspondente lista de ham ou de spam
	 * @param message - identificação da mensagem
	 * @param fileType - indicador de ham ou de spam
	 */
	public void addMessageToList(Message message, String fileType) {
		if(fileType.equals("ham")){
			hamList.add(message);
		}
		else if(fileType.equals("spam")) {
			spamList.add(message);
		}
	}

	public List<Message> getHamList(){
		return hamList;
	}

	public List<Message> getSpamList(){
		return spamList;
	}
}
