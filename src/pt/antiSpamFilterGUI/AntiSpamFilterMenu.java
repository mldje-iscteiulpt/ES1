package pt.antiSpamFilterGUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import pt.reader.DataReader;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class AntiSpamFilterMenu extends JFrame {

	private JTextField textFieldRules;
	private JTextField textFieldHam;
	private JTextField textFieldSpam;
	private DataReader datareader;
	
	/**
	 * Iniciar a aplicação.
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
	 * Criar a aplicação.
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
		
		datareader = new DataReader();
		
		addElements();
		
	}
	
	/**
	 * Adicionar elementos à frame.
	 */
	
	public void addElements() {
		JPanel panelMenu = new JPanel();
		this.getContentPane().add(panelMenu, "panel Menu");
		panelMenu.setLayout(null);
		
		JLabel lblSelecioneALocalizao = new JLabel("Selecione a localização dos ficheiros:");
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
		textFieldRules.setBounds(70, 72, 296, 20);
		panelMenu.add(textFieldRules);
		textFieldRules.setColumns(10);
		
		textFieldHam = new JTextField();
		textFieldHam.setBounds(70, 124, 296, 20);
		panelMenu.add(textFieldHam);
		textFieldHam.setColumns(10);
		
		textFieldSpam = new JTextField();
		textFieldSpam.setBounds(70, 177, 296, 20);
		panelMenu.add(textFieldSpam);
		textFieldSpam.setColumns(10);
		
		JButton btnConfigManual = new JButton("Configuração Manual");
		btnConfigManual.setBounds(10, 291, 455, 23);
		btnConfigManual.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
			if(event.getActionCommand().equals("Configuração Manual")) {
				datareader.readRules("AntiSpamConfigurationForProfessionalMailbox/rules.cf"); //ler regras
				datareader.readInfoFile("AntiSpamConfigurationForProfessionalMailbox/ham.log", datareader.getHam()); //ler ham
				datareader.readInfoFile("AntiSpamConfigurationForProfessionalMailbox/ham.log", datareader.getSpam()); //ler spam
				MenuSecundario menumanual = new MenuSecundario("manual");
				dispose();
			}
			}
		});
		panelMenu.add(btnConfigManual);
		
		JButton btnConfigAuto = new JButton("Configuração Automática");
		btnConfigAuto.setBounds(10, 325, 455, 23);
		btnConfigAuto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
			if(event.getActionCommand().equals("Configuração Automática")) {
				datareader.readRules("AntiSpamConfigurationForProfessionalMailbox/rules.cf"); //ler regras
				datareader.readInfoFile("AntiSpamConfigurationForProfessionalMailbox/ham.log", datareader.getHam()); //ler ham
				datareader.readInfoFile("AntiSpamConfigurationForProfessionalMailbox/ham.log", datareader.getSpam()); //ler spam
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
			if(event.getActionCommand().equals("Sair")) {
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
			if(event.getActionCommand().equals("Browse")) {
				//datareader.writeRulesWeights(); //escrever em ficheiro de regras
			}
			}
		});
		panelMenu.add(btnBrowseRules);
		
		JButton btnBrowseHam = new JButton("Browse");
		btnBrowseHam.setBounds(376, 123, 89, 23);
		panelMenu.add(btnBrowseHam);
		
		JButton btnBrowseSpam = new JButton("Browse");
		btnBrowseSpam.setBounds(376, 176, 89, 23);
		panelMenu.add(btnBrowseSpam);
		
	}
}