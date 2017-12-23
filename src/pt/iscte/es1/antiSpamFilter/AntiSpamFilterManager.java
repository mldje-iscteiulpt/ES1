package pt.iscte.es1.antiSpamFilter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import pt.iscte.es1.resultcompiler.RToEpsCompiler;
import pt.iscte.es1.resultcompiler.TexToPDFCompiler;

/** Classe que possui métodos responsáveis pela escolha da configuração óptima para o tipo de filtro anti-spam
 * (profissional), por gerar os pesos óptimos dessa mesma solução e por compilar os ficheiros relativos à BoxPlot.
 * @author ES1-2017-IC2-82
 *
 */

public class AntiSpamFilterManager {

	/** Path para o ficheiro com os dados de referência relativo ao número de falsos positivos e falsos negativos
	 * da solução óptima.
	 */
	private String filePath;
	/** Path para o ficheiro com os valores dos pesos atribuídos a cada regra na configuração óptima do algoritmo */
	private String fileWeightPath;
	/** Variável que define a linha do ficheiro com a configuração óptima do algoritmo */
	private int lineOfOptimalConfig;
	/** Array que contém a configuração ideal do algoritmo relativamente aos seus pesos */
	private String[] bestConfig;

	/** Instanciação do gestor do filtro anti-spam automático */
	public AntiSpamFilterManager() {
		lineOfOptimalConfig = 1;
	}

	/**
	 * Método responsável por definir qual a configuração óptima do algoritmo automático.
	 * @param filePath - caminho de ficheiro onde é definida a configuração óptima do algoritmo.
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
	 * Método que gera pesos óptimos da configuração óptima.
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
	 * Método responsável pela compilação de ficheiros da BoxPlot.
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

	/** Método responsável pela devolução da path para o ficheiro.
	 * 
	 * @return String - path para o ficheiro.
	 */

	public String getFilePath() {
		return filePath;
	}

	/**
	 * Método que define o caminho para o ficheiro.
	 * @param filePath - caminho para o ficheiro.
	 */

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Método que define o caminho para ficheiro com os pesos.
	 * @return fileWeightPath - retorna o caminho para o ficheiro.
	 */

	public String setFileWeightPath() {
		return fileWeightPath;
	}

	/**
	 * Método que define o caminho para o ficheiro dos pesos.
	 * @param fileWeightPath - caminho para ficheiro com os pesos.
	 */

	public void setFileWeightPath(String fileWeightPath) {
		this.fileWeightPath = fileWeightPath;
	}

	/**
	 * Método que define a linha com a configuração óptima gerada pelo algoritmo.
	 * @param numberOfLine - inteiro que representa o número da linha com a melhor solução.
	 */

	private void setLineOfOptimalConfig(int numberOfLine) {
		lineOfOptimalConfig = numberOfLine;
	}

	/**
	 *  Método que retorna a linha com a configuração óptima gerada pelo algoritmo.
	 * @return lineOfOptimalConfig - linha com a configuração óptima do algoritmo.
	 */

	public int getLineOfOptimalConfig() {
		return lineOfOptimalConfig;
	}

	/**
	 * Método que retorna a melhor configuração do algoritmo.
	 * @return String com a melhor configuração do algoritmo (com pesos).
	 */

	public String[] getBestConfig() {
		return bestConfig;
	}

}
