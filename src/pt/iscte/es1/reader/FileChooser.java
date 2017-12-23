package pt.iscte.es1.reader;

import java.awt.Container;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Classe responsável pela funcionalidade de seleção de ficheiros através do botão browse. Os ficheiros a selecionar são
 * ham.log, spam.log e rules.cf
 * @author ES1-2017-IC2-82
 *
 */

public class FileChooser {

	/** String com o nome do ficheiro */
	private String fileName;
	/** Container onde será inserida a instância do JFileChooser */
	private Container parent;
	/** Campo de texto com path para o ficheiro */
	private JTextField textField;
	/** String que contém a path para o ficheiro */
	private String filePath;

	/**
	 * Construtor da classe FileChooser
	 * @param fileName - nome do ficheiro
	 * @param parent - nome do objeto da classe Container
	 * @param textField - campo de texto onde ficará escrito o path dos ficheiros
	 */
	public FileChooser(String fileName, Container parent, JTextField textField) {
		this.fileName = fileName;
		this.parent = parent;
		this.textField = textField;
	}

	/**
	 * Seleção de ficheiro rules.cf, ham.log ou spam.log
	 * @return filePath - String com a path para o ficheiro
	 */
	public String choose() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(fileName + " File Open");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("LOG & CF files", "log", "cf");
		fileChooser.setFileFilter(filter);
		int returnValue = fileChooser.showOpenDialog(parent);
		if (auxChoose(returnValue)) {
			File file = fileChooser.getSelectedFile();
			textField.setText(file.getAbsolutePath());
			filePath = file.getAbsolutePath().replace("\\", File.separator);
		}
		return filePath;
	}	


	/**
	 * Método auxiliar referente à escolha de ficheiro em objeto JFileChooser.
	 * @param returnValue - valor retornado.
	 * @return boolean - valor de verdadeiro ou falso.
	 */

	public boolean auxChoose(int returnValue) {
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			return true;
		}
		return false;
	}

}
