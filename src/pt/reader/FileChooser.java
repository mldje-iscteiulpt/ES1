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
	
	/**
	 * Construtor da procura do ficheiro a utilizar
	 * @param fileName Nome do ficheiro (Rules,Ham,Spam)
	 * @param parent Componente que inclui outros componentes AWT
	 * @param textField	Espaço a ser editado com o resultado obtido
	 */
	public FileChooser(String fileName, Container parent, JTextField textField) {
		this.fileName = fileName;
		this.parent = parent;
		this.textField = textField;
	}
	
	/**
	 * Seleção de ficheiro rules.cf, ham.log ou spam.log
	 * @return String	Devolve o path do ficheiro em questão
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
