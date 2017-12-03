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
import pt.reader.DataReader.FileChooser;

@SuppressWarnings("serial")
public class AntiSpamFilterMenu extends JFrame {

	private JTextField textFieldRules; JTextField textFieldHam; JTextField textFieldSpam; static String rulesFile; 
	static String hamFile; static String spamFile;
	
	public static DataReader datareader;

	/**
	 * Iniciar a aplica��o.
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
	 * Criar a aplica��o.
	 */
	public AntiSpamFilterMenu() {
		initialize();
		setVisible(true);
	}

	/**
	 * Inicializar o conte�do da frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new CardLayout(0, 0));
		setTitle("Filtragem Anti-Spam");

		datareader = new DataReader();

		addElements();

	}

	/**
	 * Adicionar elementos � frame.
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
				FileChooser chooser = datareader.new FileChooser("Rules", getParent(), textFieldRules);
				rulesFile = chooser.choose();
			}
		});
		panelMenu.add(btnBrowseRules);

		JButton btnBrowseHam = new JButton("Browse");
		btnBrowseHam.setBounds(376, 123, 89, 23);
		panelMenu.add(btnBrowseHam);
		btnBrowseHam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DataReader.FileChooser chooser = datareader.new FileChooser("Ham", getParent(), textFieldHam);
				hamFile = chooser.choose();
			}
		});
		;

		JButton btnBrowseSpam = new JButton("Browse");
		btnBrowseSpam.setBounds(376, 176, 89, 23);
		panelMenu.add(btnBrowseSpam);
		btnBrowseSpam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DataReader.FileChooser chooser = datareader.new FileChooser("Spam", getParent(), textFieldSpam);
				spamFile = chooser.choose();
			}
		});
	}
}