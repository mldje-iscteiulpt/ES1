package pt.reader;

import java.awt.Container;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

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
