package pt.antiSpamFilterGUI;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Map;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import pt.reader.DataReader;

public class MenuSecundario extends AntiSpamFilterMenu {
	
	private String typeOfMenu;
	private DataReader reader;
	private GroupLayout groupLayoutPanel; JPanel panel; JLabel labelFP; JLabel labelFN; JButton btnGerarConfig; JButton btnAvaliarConfig;
	JButton btnVisualizar; JButton btnMenu; JScrollPane scrollPane; JTextField textFieldFN; JTextField textFieldFP; DefaultTableModel model;
	JTable table; JButton btnReiniciarPesos; JButton btnGuardarPesos;

	/**
	 * Inicializar a janela do Men� de Configura��o Manual ou Autom�tica.
	 */
	
	public MenuSecundario(String typeOfMenu) {

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
				setTitle("Filtragem Autom�tica Anti-Spam");
			}
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		this.getContentPane().setLayout(new CardLayout(0, 0));
	
		addElements();
	}
	
	/**
	 * Adicionar elementos � janela e eventos associados aos bot�es.
	 */

	public void addElements() {
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
			btnAvaliarConfig = new JButton("Avaliar configura��o");
			btnGerarConfig = new JButton("Gerar configura��o");
			btnVisualizar = new JButton("Visualizar Gr�fico");
			btnVisualizar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					BoxPlotWindow boxPlot = new BoxPlotWindow();
				}
			});
			btnMenu = new JButton("Retornar ao Men�");
			btnMenu.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					if (event.getActionCommand().equals("Retornar ao Men�")) {
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
						writeRulesWeights("AntiSpamConfigurationForProfessionalMailbox/rules.cf");
					}
				}
			});
			groupLayoutPanel = new GroupLayout(panel);
			setElementPositions();

			table = new JTable();
			createTableContent(table);
			
			//TESTE
			table.getModel().addTableModelListener(new TableModelListener() {
			      public void tableChanged(TableModelEvent e) {
			    	 System.out.println("lol" + model.getValueAt(1, 1));
			         System.out.println(e);
			         for(int i = 0; i != model.getRowCount(); i++) {
			 			Double rule = (Double) model.getValueAt(i, 1);
			 			System.out.println(rule);
			 			//reader.getRules().put(rule, rule);
			 		}
			      }
			    });
			
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
	 * Criar conte�do da tabela.
	 */
	
	@SuppressWarnings({ "rawtypes" })
	private void createTableContent(JTable table) {
		reader = new DataReader();
		model = new DefaultTableModel(0, 2) {

			//PROBLEMA: volta a 0.0
//			@Override
//			public void setValueAt(Object value, int row, int col) {
//				//model.setValueAt(value, row, col);
//		        fireTableCellUpdated(row, col);
//				model.fireTableDataChanged();
//		    }


			@Override
			public void setValueAt(Object value, int row, int col) {
//				model.setValueAt(value, row, col);
		        fireTableCellUpdated(row, col);
				model.fireTableDataChanged();
		    }
			
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
		Iterator iterator = reader.getRules().entrySet().iterator();
	    while (iterator.hasNext()) {
	        Map.Entry pair = (Map.Entry)iterator.next();
	        model.addRow(new Object[] { pair.getKey(), pair.getValue() });
	    }
		table.setModel(model);
	}
	
	/**
	 * Definir posi��o dos elementos na frame.
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
			String rule = (String) model.getValueAt(i, 0);
			reader.getRules().put(rule, 0.0);
		}
		table.setModel(model);
		model.fireTableDataChanged();
	}
	
	public void writeRulesWeights(String filePathRules) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePathRules));
			for (int row = 0; row < table.getRowCount(); row++) {
			    	writer.write((String) model.getDataVector().elementAt(row).toString().replaceAll("\\[", "").replaceAll("\\]","").replaceAll("[[-+^:,]]",""));
			        writer.newLine();
			}
			System.out.println(model.getDataVector().elementAt(0).toString());
		    //Close writer
		    writer.close();
		} catch(Exception e) {
		    e.printStackTrace();
		}
	}
	
	/**
	 * Redefini��o do m�todo equals.
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
