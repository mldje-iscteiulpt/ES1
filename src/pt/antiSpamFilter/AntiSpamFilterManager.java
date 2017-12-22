package pt.antiSpamFilter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import pt.resultcompiler.RToEpsCompiler;
import pt.resultcompiler.TexToPDFCompiler;

public class AntiSpamFilterManager {

	private String filePath;
	private String fileWeightPath;
	private int lineOfOptimalConfig;
	private String[] bestConfig;

	public AntiSpamFilterManager() {
		lineOfOptimalConfig = 1;
	}

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
				System.out.println("Tokens: " + Arrays.toString(tokens)); // tokens.toString());
				// primeira linha lida
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
				System.out.println(
						"Best Config: " + Arrays.toString(bestConfig) + " in line " + getLineOfOptimalConfig());
				if (br != null) {
					br.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String setFileWeightPath() {
		return fileWeightPath;
	}

	public void setFileWeightPath(String fileWeightPath) {
		this.fileWeightPath = fileWeightPath;
	}

	private void setLineOfOptimalConfig(int numberOfLine) {
		lineOfOptimalConfig = numberOfLine;
	}

	public int getLineOfOptimalConfig() {
		return lineOfOptimalConfig;
	}
	
	public String[] getBestConfig() {
		return bestConfig;
	}

}
