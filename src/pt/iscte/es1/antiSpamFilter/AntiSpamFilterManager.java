package pt.iscte.es1.antiSpamFilter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import pt.iscte.es1.resultcompiler.RToEpsCompiler;
import pt.iscte.es1.resultcompiler.TexToPDFCompiler;

/** Classe que possui m�todos respons�veis pela escolha da configura��o �ptima para o tipo de filtro anti-spam
 * (profissional), por gerar os pesos �ptimos dessa mesma solu��o e por compilar os ficheiros relativos � box plot.
 * @author Mario
 *
 */

public class AntiSpamFilterManager {

	/** Path para o ficheiro com os dados de refer�ncia relativo ao n�mero de falsos positivos e falsos negativos
	 * da solu��o �ptima.
	 */
	private String filePath;
	/** Path para o ficheiro com os valores dos pesos atribu�dos a cada regra na configura��o �ptima do algoritmo */
	private String fileWeightPath;
	/** Vari�vel que define a linha do ficheiro com a configura��o �ptima do algoritmo */
	private int lineOfOptimalConfig;
	/** Array que cont�m a configura��o ideal do algoritmo relativamente aos seus pesos */
	private String[] bestConfig;

	/** Instancia��o do gestor do filtro anti-spam autom�tico */
	public AntiSpamFilterManager() {
		lineOfOptimalConfig = 1;
	}

	/**
	 * M�todo respons�vel por definir qual a configura��o �ptima do algoritmo autom�tico.
	 * @param filePath
	 */
	public void pickOptimalConfig(String filePath) {
		if (filePath.equals("default")) {
			filePath = System.getProperty("user.dir") + "/experimentBaseDirectory/referenceFronts/"
					+ "AntiSpamFilterProblem.NSGAII.rf";
		}
		BufferedReader br = null;
		String[] tokens = null;
		int lineCounter = 1;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = br.readLine()) != null) {
				String delims = "[ ]+|\\t";
				tokens = line.split(delims);
				if (bestConfig == null) {
					bestConfig = tokens;
				} else if (Double.parseDouble(bestConfig[1]) > Double.parseDouble(tokens[1])) {
					bestConfig = tokens;
					setLineOfOptimalConfig(lineCounter);
				}
				lineCounter++;
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
	 * M�todo que gera pesos �ptimos da configura��o �ptima.
	 * 
	 * @param fileWeightPath - path para o ficheiro que define os pesos das regras.
	 * @return String[] - estrutura que armazena os pesos das regras.
	 */

	public String[] generateOptimalWeights(String fileWeightPath){
		BufferedReader br = null;
		String[] tokens = null;
		String[] optimalWeights = null;
		int lineCounter = 1;
		if(fileWeightPath.equals("default")) {
			fileWeightPath = System.getProperty("user.dir") + "/experimentBaseDirectory/referenceFronts/"
					+ "AntiSpamFilterProblem.NSGAII.rs";
		}
		try {
			br = new BufferedReader(new FileReader(fileWeightPath));
			String textLine;
			while ((textLine = br.readLine()) != null) {
				String delims = "[ ]+|\\t";
				tokens = textLine.split(delims);
				if (lineCounter == lineOfOptimalConfig) {
					optimalWeights = tokens;
				}
				lineCounter++;
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
		return optimalWeights;	
	}

	/**
	 * M�todo respons�vel pela compila��o de ficheiros da BoxPlot.
	 */

	public void compileBoxPlotFiles() {
		RToEpsCompiler rCompiler = new RToEpsCompiler();
		TexToPDFCompiler pdfCompiler = new TexToPDFCompiler();
		try {
			rCompiler.compile();
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
		}

		try {
			pdfCompiler.compile();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	/** M�todo respons�vel pela devolu��o da path para o ficheiro.
	 * 
	 * @return String - path para o ficheiro.
	 */

	public String getFilePath() {
		return filePath;
	}

	/**
	 * M�todo que define o caminho para o ficheiro.
	 * @param filePath
	 */

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * M�todo que define o caminho para ficheiro com os pesos.
	 * @return fileWeightPath - retorna o caminho para o ficheiro.
	 */

	public String setFileWeightPath() {
		return fileWeightPath;
	}

	/**
	 * M�todo que define o caminho para o ficheiro dos pesos.
	 * @param fileWeightPath - caminho para ficheiro com os pesos.
	 */

	public void setFileWeightPath(String fileWeightPath) {
		this.fileWeightPath = fileWeightPath;
	}

	/**
	 * M�todo que define a linha com a configura��o �ptima gerada pelo algoritmo.
	 * @param numberOfLine - inteiro que representa o n�mero da linha com a melhor solu��o.
	 */

	private void setLineOfOptimalConfig(int numberOfLine) {
		lineOfOptimalConfig = numberOfLine;
	}

	/**
	 *  M�todo que retorna a linha com a configura��o �ptima gerada pelo algoritmo.
	 * @return lineOfOptimalConfig - linha com a configura��o �ptima do algoritmo.
	 */

	public int getLineOfOptimalConfig() {
		return lineOfOptimalConfig;
	}

	/**
	 * M�todo que retorna a melhor configura��o do algoritmo.
	 * @return String com a melhor configura��o do algoritmo (com pesos).
	 */

	public String[] getBestConfig() {
		return bestConfig;
	}

}
