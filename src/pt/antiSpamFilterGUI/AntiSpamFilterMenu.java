package pt.antiSpamFilterGUI;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import pt.reader.DataReader;
import pt.reader.FileChooser;

@SuppressWarnings("serial")
public class AntiSpamFilterMenu extends JFrame {

	/** Campo de texto que indica path para o ficheiro rules.cf ou semelhante que contenha as regras */
	private JTextField textFieldRules; 
	/** Campo de texto que indica path para o ficheiro ham.log ou semelhante que contenha o ham */
	private JTextField textFieldHam; 
	/** Campo de texto que indica path para o ficheiro spam.log ou semelhante que contenha o spam */
	private JTextField textFieldSpam;
	/** String que contém path para o ficheiro rules.cf ou semelhante que contenha as regras */
	private static String rulesFile; 
	/** String que contém path para o ficheiro ham.log ou semelhante que contenha o ham */
	private static String hamFile; 
	/** String que contém path para o ficheiro spam.log ou semelhante que contenha o spam */
	private static String spamFile;
	/** Variável estática de acesso a classe DataReader que permite a leitura de ficheiros de regras, de ham e de spam 
	 * e armazenamento dos mesmos */
	static DataReader dataReader;

	/**
	 * Classe responsável por iniciar a interface gráfico da aplicação. Esta inicia-se pelo menú de seleção dos ficheiros rules.cf,
	 * ham.log e spam.log. É possível selecionar a configuração manual ou automática do filtro anti-spam ou sair da aplicação.
	 * A classe contém os métodos que inicializam a frame e que adicionam os diversos componentes a essa frame (nomeadamente os botões para
	 * poder localizar os ficheiros, os campos de texto onde se apresenta o path desses mesmos ficheiros, os botões para iniciar a
	 * configuração manual ou automática e o botão de saída do programa), bem como os métodos para obter a path para os três ficheiros mencionados
	 * anteriormente.
	 * 
	 * @author ES1-2017-IC2-82
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AntiSpamFilterMenu window = new AntiSpamFilterMenu();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Criar o menú principal da aplicação.
	 */
	public AntiSpamFilterMenu() {
		initialize();
		setVisible(true);
	}

	/**
	 * Inicializar o conteúdo da frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new CardLayout(0, 0));
		setTitle("Filtragem Anti-Spam");

		dataReader = new DataReader();
		
		if(!(this instanceof MenuSecundario)) {
			addElements();
		}

	}

	/**
	 * Adicionar elementos à frame.
	 */

	public void addElements() {
		JPanel panelMenu = new JPanel();
		this.getContentPane().add(panelMenu, "panel Menu");
		panelMenu.setLayout(null);

		JLabel lblSelecioneALocalizao = new JLabel("Selecione a localiza��o dos ficheiros:");
		lblSelecioneALocalizao.setBounds(10, 11, 220, 14);
		panelMenu.add(lblSelecioneALocalizao);

		JLabel lblRulescf = new JLabel("rules.cf");
		lblRulescf.setBounds(10, 75, 46, 14);
		panelMenu.add(lblRulescf);

		JLabel lblHamlog = new JLabel("ham.log");
		lblHamlog.setBounds(10, 127, 46, 14);
		panelMenu.add(lblHamlog);

		JLabel lblSpamlog = new JLabel("spam.log");
		lblSpamlog.setBounds(10, 180, 54, 14);
		panelMenu.add(lblSpamlog);

		textFieldRules = new JTextField();
		textFieldRules.setEnabled(false);
		textFieldRules.setBounds(70, 72, 296, 20);
		panelMenu.add(textFieldRules);
		textFieldRules.setColumns(10);

		textFieldHam = new JTextField();
		textFieldHam.setEnabled(false);
		textFieldHam.setBounds(70, 124, 296, 20);
		panelMenu.add(textFieldHam);
		textFieldHam.setColumns(10);

		textFieldSpam = new JTextField();
		textFieldSpam.setEnabled(false);
		textFieldSpam.setBounds(70, 177, 296, 20);
		panelMenu.add(textFieldSpam);
		textFieldSpam.setColumns(10);

		JButton btnConfigManual = new JButton("Configura��o Manual");
		btnConfigManual.setBounds(10, 291, 455, 23);
		btnConfigManual.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getActionCommand().equals("Configura��o Manual")) {
					MenuSecundario menumanual = new MenuSecundario("manual");
					menumanual.setVisible(true);
					dispose();
				}
			}
		});
		panelMenu.add(btnConfigManual);

		JButton btnConfigAuto = new JButton("Configura��o Autom�tica");
		btnConfigAuto.setBounds(10, 325, 455, 23);
		btnConfigAuto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getActionCommand().equals("Configura��o Autom�tica")) {
					MenuSecundario menuauto = new MenuSecundario("auto");
					menuauto.setVisible(true);
					dispose();
				}
			}
		});
		panelMenu.add(btnConfigAuto);

		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(10, 359, 455, 23);
		btnSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getActionCommand().equals("Sair")) {
					System.exit(0);
				}
			}
		});
		panelMenu.add(btnSair);

		JButton btnBrowseRules = new JButton("Browse");
		btnBrowseRules.setBounds(376, 71, 89, 23);
		btnBrowseRules.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				FileChooser chooser = new FileChooser("Rules", getParent(), textFieldRules);
				//rulesFile = chooser.choose();
				setRulesFilePath(chooser.choose());
			}
		});
		panelMenu.add(btnBrowseRules);

		JButton btnBrowseHam = new JButton("Browse");
		btnBrowseHam.setBounds(376, 123, 89, 23);
		panelMenu.add(btnBrowseHam);
		btnBrowseHam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FileChooser chooser = new FileChooser("Ham", getParent(), textFieldHam);
				setHamFilePath(chooser.choose());
			}
		});
		;

		JButton btnBrowseSpam = new JButton("Browse");
		btnBrowseSpam.setBounds(376, 176, 89, 23);
		panelMenu.add(btnBrowseSpam);
		btnBrowseSpam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FileChooser chooser = new FileChooser("Spam", getParent(), textFieldSpam);
				spamFile = chooser.choose();
			}
		});
	}
	
	/**
	 * Método que devolve instância do DataReader que é responsável pela leitura dos ficheiros rules.cf,
	 * ham.log e spam.log, e pelo armazenamento dos dados lidos em estruturas de dados.
	 * @return DataReader
	 */
	public static DataReader getDatareader() {
		return dataReader;
	}
	
	/**
	 * Devolve path para ficheiro rules.cf
	 * @return String
	 */
	public static String getRulesFile() {
		return rulesFile;
	}
	
	
	/**
	 * Método que define qual a path para o ficheiro com as regras
	 * @param rulesFile - path para o ficheiro das regras
	 */
	@SuppressWarnings("static-access")
	public void setRulesFilePath(String rulesFile) {
		this.rulesFile = rulesFile;
	}
	
	/**
	 * Devolve path para ficheiro ham.log
	 * @return String
	 */
	
	public static String getHamFile() {
		return hamFile;
	}
	
	/**
	 * Define path para ficheiro ham.log
	 * @return String
	 */
	
	public static void setHamFilePath(String hamFilePath) {
		hamFile = hamFilePath;
	}
	
	/**
	 * Devolve path para ficheiro spam.log
	 * @return String
	 */
	
	public static String getSpamFile() {
		return spamFile;
	}
	
	public static void setSpamFilePath(String spamFilePath) {
		spamFile = spamFilePath;
	}
}