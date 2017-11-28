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
	
	public FileChooser(String fileName, Container parent, JTextField textField) {
		this.fileName = fileName;
		this.parent = parent;
		this.textField = textField;
	}
	
	public void choose() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(fileName + " File OPEN");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("LOG & CF files", "log", "cf");
		fileChooser.setFileFilter(filter);
		int returnValue = fileChooser.showOpenDialog(parent);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
//			textField.setText(fileChooser.getSelectedFile().getPath());
			textField.setText(fileChooser.getSelectedFile().getPath().replace('\\', File.separatorChar));
		}
	}
	
	private String updateDirectory(String path) {
		
		String[] tokens = path.split("|\\");
		for (String word : tokens) {
			System.out.println(word);
		}
		
		return "";
	}
	
}
