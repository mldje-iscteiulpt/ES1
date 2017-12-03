package pt.reader;

import java.awt.Container;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


public class DataReader {
	
	private static Map<String,Double> rules = new TreeMap<String,Double>();
	private static int FP = 0;
	private static int FN = 0;
	
	/**
	 * Obter estrutura de dados com regras e respetivos pesos.
	 */
	
	public Map<String,Double> getRules(){
		return rules;
	}
	
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
	
	public void readInfoFile(String filePath) {
		BufferedReader br = null;
		String[] tokens = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
            	String delims = "[ ]+|\\t";
            	tokens = line.split(delims);
            	
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


	
	public class FileChooser {

		private String fileName;
		private Container parent;
		private JTextField textField;
		private String filePath;
		
		public FileChooser(String fileName, Container parent, JTextField textField) {
			this.fileName = fileName;
			this.parent = parent;
			this.textField = textField;
		}
		
		/**
		 * Seleção de ficheiro rules.cf, ham.log ou spam.log
		 * @return String
		 */
		public String choose() {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle(fileName + " File Open");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("LOG & CF files", "log", "cf");
			fileChooser.setFileFilter(filter);
			int returnValue = fileChooser.showOpenDialog(parent);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				textField.setText(file.getAbsolutePath());
				filePath = file.getAbsolutePath().replace("\\", File.separator);
			}
			return filePath;
		}	
	}
}
