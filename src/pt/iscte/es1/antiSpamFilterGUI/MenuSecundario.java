package pt.iscte.es1.antiSpamFilterGUI;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import org.uma.jmetal.util.JMetalException;

import pt.iscte.es1.antiSpamFilter.AntiSpamFilterAutomaticConfiguration;
import pt.iscte.es1.antiSpamFilter.AntiSpamFilterManager;
import pt.iscte.es1.antiSpamFilter.AntiSpamFilterProblem;
import pt.iscte.es1.reader.DataReader;
import pt.iscte.es1.tabledata.TableDataManipulator;

/**
 * Classe responsável por gerar a janela de menú secundário onde é possível realizar a configuração manual ou
 * automática do filtro anti-spam. Esta janela difere consoante o tipo de filtro, apresentando diferenças relativamente
 * às opções (difere no número de botões apresentados). 
 * Nas duas opções apresenta uma tabela com as regras e respetivos pesos; é possível editar os pesos apenas na configuração manual. No caso
 * específico da configuração manual, irá ler o ficheiro de regras e, caso este ficheiro indique os pesos de cada regra, adiciona esses pesos;
 * se não indicar, os mesmos são iniciados com o valor 0.0.
 * Apresenta também um botão para avaliar a configuração que indica o número de falsos positivos e falsos negativos decorrentes dos pesos
 * gerados em configuração manual ou automática. 
 * No caso específico da configuração automática, apresenta a opção de gerar os pesos das regras de forma aleatória e apresenta igualmente
 * a possibilidade de visualizar o gráfico BoxPlot.
 * 
 * @author ES1-2017-IC2-82
 *
 */

@SuppressWarnings("serial")
public class MenuSecundario extends AntiSpamFilterMenu {

	/** String que define o tipo de menú, se o menú relativo a filtro anti-spam manual ou automático  */
	private String typeOfMenu;
	/** GroupLayout onde serão dispostos os elementos gráficos da interface gráfica */
	private GroupLayout groupLayoutPanel;
	/** Panel para inserção dos elementos gráficos como botões, labels, campos de texto e tabela */
	private JPanel panel;
	/** Etiqueta com referência a "Falsos Positivos" */
	private JLabel labelFP;
	/** Etiqueta com referência a "Falsos Negativos" */
	private JLabel labelFN;
	/** Botão que gera configuração automática do filtro anti-spam */
	private JButton btnGerarConfig;
	/** Botão que inicia a avaliação do número de Falsos Positivos e Falsos Negativos consoante os pesos atribuídos às regras */
	private JButton btnAvaliarConfig;
	/** Botão que abre janela para visualização do gráfico BoxPlot */
	private JButton btnVisualizar;
	/** Botão para regressar ao menú inicial */
	private JButton btnMenu;
	/** Elemento que permite realizar o scroll da tabela das regras e pesos */
	private JScrollPane scrollPane;
	/** Campo onde surgem o número de Falsos Negativos */
	private JTextField textFieldFN;
	/** Campo onde surgem o número de Falsos Positivos */
	private JTextField textFieldFP;
	/** Tabela que apresenta as regras e respetivos pesos */
	private JTable table;
	/** Botão que inicia o reset dos valores de todas regras para 0.0 */
	private JButton btnReiniciarPesos;
	/** Botão que permite guardar as regras e os pesos contidos na tabela em ficheiro de texto */
	private JButton btnGuardarPesos;
	/** Variável de acesso aos métodos que manipulam os dados da tabela para escrita no ficheiro ou reset dos mesmos (peso=0.0) */
	private TableDataManipulator tableData;
	/** Modelo inserido na tabela que apresenta colunas de regras e respetivos pesos */
	public static DefaultTableModel model;
	
	List<JButton> menuSecundarioButtons;

	/**
	 * Inicializar a janela do menú de configuração manual ou automático do filtro anti-spam.
	 * @param typeOfMenu - Tipo de menú a ser gerado (manual ou automático)
	 */

	public MenuSecundario(String typeOfMenu) {

		this.typeOfMenu = typeOfMenu;
		menuSecundarioButtons = new ArrayList<JButton>();
		
		elements();

		this.setBounds(100, 100, 500, 500);
//		if ("BoxPlot".equals(typeOfMenu)) {
//			this.setDefaultCloseOperation(HIDE_ON_CLOSE);
//		} else {
			if ("manual".equals(typeOfMenu)) {
				setTitle("Filtragem Manual Anti-Spam");
//				btnGerarConfig.setOpaque(false);
//				btnGerarConfig.setContentAreaFilled(false);
//				btnGerarConfig.setBorderPainted(false);
//				btnGerarConfig.setText("");
//				btnGerarConfig.setEnabled(false);
			} else if ("auto".equals(typeOfMenu)) {
				setTitle("Filtragem Automática Anti-Spam");
			}
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		}
		this.getContentPane().setLayout(new CardLayout(0, 0));

	}

	/**
	 * Adicionar elementos à janela e eventos associados aos botões.
	 */

	public void elements() {
//
//		if (this instanceof BoxPlotWindow) {
//
//		} else {
			panel = new JPanel();
			scrollPane = new JScrollPane();
			labelFP = new JLabel("Falsos Positivos");
			labelFN = new JLabel("Falsos Negativos");
			textFieldFP = new JTextField();
			textFieldFP.setColumns(10);
			textFieldFN = new JTextField();
			textFieldFN.setColumns(10);
			createTable();
			tableData = new TableDataManipulator(table, model);
			btnAvaliarConfig = new JButton("Avaliar configura��o");
			btnAvaliarConfig.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					if (event.getActionCommand().equals("Avaliar configura��o")) {
						//DataReader dataReader = AntiSpamFilterMenu.getDatareader();
						dataReader.readInfoFile(AntiSpamFilterMenu.getHamFile(), "ham");
						setFP(dataReader.getChecker().getFP());
						dataReader.readInfoFile(AntiSpamFilterMenu.getSpamFile(), "spam");
						setFN(dataReader.getChecker().getFN());
					}
				}
			});
			menuSecundarioButtons.add(btnAvaliarConfig);

			btnGerarConfig = new JButton("Gerar configura��o");
			btnGerarConfig.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {					
					AntiSpamFilterProblem problem = new AntiSpamFilterProblem(dataReader.getHamList(), dataReader.getSpamList(), dataReader.getRules());
						try {
							useAutomaticConfig(new AntiSpamFilterManager(), new AntiSpamFilterAutomaticConfiguration(problem));
						} catch (IOException |JMetalException i) {
						}
					
				}
			});
			menuSecundarioButtons.add(btnGerarConfig);
			btnVisualizar = new JButton("Visualizar Gr�fico");
			btnVisualizar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
				}
			});
			menuSecundarioButtons.add(btnVisualizar);
			btnMenu = new JButton("Retornar ao Menú");
			btnMenu.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					if (event.getActionCommand().equals("Retornar ao Menú")) {
						AntiSpamFilterMenu menu = new AntiSpamFilterMenu();
						dispose();
					}
				}
			});
			menuSecundarioButtons.add(btnMenu);
			btnReiniciarPesos = new JButton("Reiniciar Pesos");
			btnReiniciarPesos.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					if (event.getActionCommand().equals("Reiniciar Pesos")) {
						tableData.resetValues();
					}
				}
			});
			menuSecundarioButtons.add(btnReiniciarPesos);
			btnGuardarPesos = new JButton("Guardar Pesos");
			btnGuardarPesos.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					if (event.getActionCommand().equals("Guardar Pesos")) {
						tableData.writeRulesWeights(AntiSpamFilterMenu.getRulesFile(), dataReader.getRules());
					}
				}
			});
			menuSecundarioButtons.add(btnGuardarPesos);
			
			groupLayoutPanel = new GroupLayout(panel);
			setElementPositions();

			AntiSpamFilterMenu.getDatareader().readRules(AntiSpamFilterMenu.getRulesFile(), model, table); // ler regras

			scrollPane.setViewportView(table);
			panel.setLayout(groupLayoutPanel);
			
			if(typeOfMenu.equals("auto")) {
				DataReader dataReader = AntiSpamFilterMenu.getDatareader();

				dataReader.readInfoFile(AntiSpamFilterMenu.getHamFile(), "ham");
				setFP(dataReader.getChecker().getFP());
				dataReader.readInfoFile(AntiSpamFilterMenu.getSpamFile(), "spam");
				setFN(dataReader.getChecker().getFN());
			}
			
			if ("auto".equals(typeOfMenu)) {
				btnGerarConfig.setVisible(true);

			} else  {
				btnGerarConfig.setVisible(false);
			}
			this.add(panel);
//		}
	}

	public void useAutomaticConfig(AntiSpamFilterManager manager,AntiSpamFilterAutomaticConfiguration autoConf) throws IOException {
	
			autoConf.generateAutomaticConfig();
			manager.pickOptimalConfig("default");
			tableData.writeOptimalDataToTable(manager.generateOptimalWeights("default"));
			String[] aux = manager.getBestConfig();
			Double auxFN = Double.parseDouble(aux[0]);
			Double auxFP = Double.parseDouble(aux[1]);
			setFN(auxFN.intValue());
			setFP(auxFP.intValue());
			//manager.compileBoxPlotFiles();
		
	}

	/**
	 * Criar conteúdo da tabela e definir quais as colunas editáveis. Na configuração manual só a coluna 1 (pesos) é editável;
	 * na configuração automática as duas colunas não serão editáveis
	 * @param table - JTable com regras e respetivos pesos
	 */

	public void createTable() {
		table = new JTable();
		model = new DefaultTableModel(0, 2) {
			
			@Override
			public boolean isCellEditable(int row, int column) {
				if("manual".equals(typeOfMenu)) {
					return column==1;
				}
				return false;
			}
		};

		String[] columnNames = { "Regra", "Peso" };
		model.setColumnIdentifiers(columnNames);
	}

	/**
	 * Definir posições dos elementos gráficos na frame.
	 */

	private void setElementPositions() {
		setHorizontalGroup();
		setVerticalGroup();
	}

	/**
	 * Definir layout vertical.
	 */

	private void setVerticalGroup() {
		groupLayoutPanel.setVerticalGroup(groupLayoutPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayoutPanel.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayoutPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnReiniciarPesos).addComponent(btnGuardarPesos))
						.addGap(3)
						.addGroup(groupLayoutPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayoutPanel.createSequentialGroup()
										.addGroup(groupLayoutPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnAvaliarConfig).addComponent(btnVisualizar))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayoutPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnGerarConfig).addComponent(btnMenu)))
								.addGroup(groupLayoutPanel.createSequentialGroup()
										.addGroup(groupLayoutPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(labelFP).addComponent(textFieldFP,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayoutPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(textFieldFN, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(labelFN))))
						.addContainerGap(18, Short.MAX_VALUE)));
	}

	/**
	 * Definir layout horizontal.
	 */

	private void setHorizontalGroup() {
		groupLayoutPanel.setHorizontalGroup(groupLayoutPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayoutPanel.createSequentialGroup().addContainerGap()
						.addGroup(groupLayoutPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayoutPanel.createSequentialGroup()
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 466,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(Alignment.TRAILING,
										groupLayoutPanel.createSequentialGroup()
												.addGroup(groupLayoutPanel.createParallelGroup(Alignment.LEADING)
														.addComponent(labelFP).addComponent(labelFN))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayoutPanel.createParallelGroup(Alignment.LEADING, false)
														.addComponent(textFieldFN, 0, 0, Short.MAX_VALUE)
														.addComponent(textFieldFP, GroupLayout.PREFERRED_SIZE, 44,
																GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
												.addGroup(groupLayoutPanel.createParallelGroup(Alignment.LEADING, false)
														.addComponent(btnGerarConfig, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(btnAvaliarConfig, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(btnReiniciarPesos, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayoutPanel.createParallelGroup(Alignment.LEADING, false)
														.addComponent(btnVisualizar, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(btnMenu, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(btnGuardarPesos, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addGap(20)))));
	}

	
	/**
	 * Definir número de casos que são falsos positivos
	 * @param number - número de falsos positivos
	 */

	public void setFP(int number) {
		textFieldFP.setText(number + "");
	}
	
	/**
	 * Definir número de casos que são falsos negativos
	 * @param number
	 */

	public void setFN(int number) {
		textFieldFN.setText(number + "");
	}
	
	public List<JButton> getButtonFromMenuSecundario(){
		return menuSecundarioButtons;
	}

	/**
	 * Redefinição do método equals.
	 * @param Object - objeto que se pretende comparar com outro objeto
	 * @return Boolean - informa se objetos comparados são idênticos ou não
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
}
