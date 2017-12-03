package pt.antiSpamFilterGUI;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

public class MenuSecundario extends JFrame {

	private String typeOfMenu;
	private GroupLayout groupLayoutPanel; JPanel panel; JLabel labelFP; JLabel labelFN; JButton btnGerarConfig; JButton btnAvaliarConfig;
	JButton btnVisualizar; JButton btnMenu; JScrollPane scrollPane; JTextField textFieldFN; JTextField textFieldFP;
	JTable table; JButton btnReiniciarPesos; JButton btnGuardarPesos;

	public static DefaultTableModel model;

	/**
	 * Inicializar a janela do Menú de Configuração Manual ou Automática.
	 */

	public MenuSecundario(String typeOfMenu) {

		elements();

		this.typeOfMenu = typeOfMenu;
		this.setBounds(100, 100, 500, 500);
		if("BoxPlot".equals(typeOfMenu)){
			this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		}
		else {
			if("manual".equals(typeOfMenu)) {
				setTitle("Filtragem Manual Anti-Spam");
				btnGerarConfig.setOpaque(false);
				btnGerarConfig.setContentAreaFilled(false);
				btnGerarConfig.setBorderPainted(false);
				btnGerarConfig.setText("");
				btnGerarConfig.setEnabled(false);
			}
			else if("auto".equals(typeOfMenu)){
				setTitle("Filtragem Automática Anti-Spam");
			}
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		this.getContentPane().setLayout(new CardLayout(0, 0));



	}

	/**
	 * Adicionar elementos à janela e eventos associados aos botões.
	 */

	public void elements() {

		if (this instanceof BoxPlotWindow) {

		} else {
			panel = new JPanel();
			scrollPane = new JScrollPane();
			labelFP = new JLabel("Falsos Positivos");
			labelFN = new JLabel("Falsos Negativos");
			textFieldFP = new JTextField();
			textFieldFP.setColumns(10);
			textFieldFN = new JTextField();
			textFieldFN.setColumns(10);
			btnAvaliarConfig = new JButton("Avaliar configuração");
			btnAvaliarConfig.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					if (event.getActionCommand().equals("Avaliar configuração")) {
						AntiSpamFilterMenu.datareader.readInfoFile(AntiSpamFilterMenu.hamFile);
						AntiSpamFilterMenu.datareader.readInfoFile(AntiSpamFilterMenu.spamFile);
					}
				}
			});

			btnGerarConfig = new JButton("Gerar configuração");
			btnGerarConfig.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {


				}
			});
			btnVisualizar = new JButton("Visualizar Gráfico");
			btnVisualizar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					BoxPlotWindow boxPlot = new BoxPlotWindow();
				}
			});
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
			btnReiniciarPesos = new JButton("Reiniciar Pesos");
			btnReiniciarPesos.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					if (event.getActionCommand().equals("Reiniciar Pesos")) {
						resetValues();
					}
				}
			});
			btnGuardarPesos = new JButton("Guardar Pesos");
			btnGuardarPesos.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					if (event.getActionCommand().equals("Guardar Pesos")) {
						writeRulesWeights(AntiSpamFilterMenu.rulesFile);
					}
				}
			});

			groupLayoutPanel = new GroupLayout(panel);
			setElementPositions();

			table = new JTable();
			createTableContent(table);
			AntiSpamFilterMenu.datareader.readRules(AntiSpamFilterMenu.rulesFile, model, table); // ler regras

			scrollPane.setViewportView(table);
			panel.setLayout(groupLayoutPanel);
			if ("auto".equals(typeOfMenu)) {
				btnGerarConfig.setVisible(true);
			} else if ("manual".equals(typeOfMenu)) {
				btnGerarConfig.setVisible(false);	
			}
			this.add(panel);
		}
	}

	/**
	 * Criar conteúdo da tabela.
	 */

	private void createTableContent(JTable table) {
		model = new DefaultTableModel(0, 2);

		String[] columnNames = { "Regra", "Peso" };
		model.setColumnIdentifiers(columnNames);
	}

	/**
	 * Definir posição dos elementos na frame.
	 */

	private void setElementPositions() {
		setHorizontalGroup();
		setVerticalGroup();
	}

	/**
	 * Definir layout vertical.
	 */

	private void setVerticalGroup() {
		groupLayoutPanel.setVerticalGroup(
				groupLayoutPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayoutPanel.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayoutPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnReiniciarPesos)
								.addComponent(btnGuardarPesos))
						.addGap(3)
						.addGroup(groupLayoutPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayoutPanel.createSequentialGroup()
										.addGroup(groupLayoutPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnAvaliarConfig)
												.addComponent(btnVisualizar))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayoutPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnGerarConfig)
												.addComponent(btnMenu)))
								.addGroup(groupLayoutPanel.createSequentialGroup()
										.addGroup(groupLayoutPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(labelFP)
												.addComponent(textFieldFP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayoutPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(textFieldFN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(labelFN))))
						.addContainerGap(18, Short.MAX_VALUE))
				);
	}

	/**
	 * Definir layout horizontal.
	 */

	private void setHorizontalGroup() {
		groupLayoutPanel.setHorizontalGroup(
				groupLayoutPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayoutPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayoutPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayoutPanel.createSequentialGroup()
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 466, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(Alignment.TRAILING, groupLayoutPanel.createSequentialGroup()
										.addGroup(groupLayoutPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(labelFP)
												.addComponent(labelFN))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayoutPanel.createParallelGroup(Alignment.LEADING, false)
												.addComponent(textFieldFN, 0, 0, Short.MAX_VALUE)
												.addComponent(textFieldFP, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
										.addGroup(groupLayoutPanel.createParallelGroup(Alignment.LEADING, false)
												.addComponent(btnGerarConfig, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnAvaliarConfig, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnReiniciarPesos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayoutPanel.createParallelGroup(Alignment.LEADING, false)
												.addComponent(btnVisualizar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnMenu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnGuardarPesos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(20))))
				);
	}

	/**
	 * Reinicializar os pesos das regras com o valor 0.0
	 */

	public void resetValues() {
		for(int i = 0; i != model.getRowCount(); i++) {
			model.setValueAt(0.0, i, 1);
		}
		table.setModel(model);
		model.fireTableDataChanged();
	}

	public void writeRulesWeights(String filePathRules) {
		try {
			double a ;
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePathRules));
			for(int i = 0; i != model.getRowCount(); i++) {		
				if(model.getValueAt(i, 1) instanceof String) {
					 a = Double.parseDouble((String)model.getValueAt(i, 1));
				} else {
					 a = (double) model.getValueAt(i, 1);
				}
				
				String rule = (String) model.getValueAt(i, 0);
				AntiSpamFilterMenu.datareader.getRules().put(rule,a);
				writer.write(rule + " " +  AntiSpamFilterMenu.datareader.getRules().get(rule));
				writer.newLine();
			}
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Redefinição do método equals.
	 */

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		return true;
	}
}
