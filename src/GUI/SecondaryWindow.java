package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class SecondaryWindow extends JFrame{

	private String mode;
	private TableModel model;
	
	public SecondaryWindow(String title, String mode) {
		setTitle(title + " || " + mode);
		this.mode = mode;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		model = new AbstractTableModel() {
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getRowCount() {
				// TODO Auto-generated method stub
				return 50;
			}
			
			@Override
			public int getColumnCount() {
				// TODO Auto-generated method stub
				return 2;
			}
		};
	}
	
	public void open() {
		addContent();

		setSize(1000, 800);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int) (dim.getWidth() / 2 - this.getSize().getWidth() / 2),
				(int) (dim.getHeight() / 2 - this.getSize().getHeight() / 2));

		setVisible(true);
	}
	
	private void addContent() {
		/*
		 * Painel Superior
		 */
		JPanel superiorPanel = new JPanel();
		superiorPanel.setLayout(new GridLayout(0, 1));
		add(superiorPanel, BorderLayout.CENTER);
		
		JTable table = new JTable();
		table.setModel(model);
		table.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("Rules");
		table.getTableHeader().getColumnModel().getColumn(1).setHeaderValue("Pesos");
		superiorPanel.add(table);

		JScrollPane scroll = new JScrollPane(table);
		superiorPanel.add(scroll);
		
		/*
		 * Painel Inferior
		 */
		JPanel inferiorPanel = new JPanel();
		inferiorPanel.setLayout(new GridLayout(0, 2));
		add(inferiorPanel, BorderLayout.SOUTH);
		
		JPanel leftPanel = new JPanel();
		inferiorPanel.add(leftPanel);
		
		JPanel rightPanel = new JPanel();
		inferiorPanel.add(rightPanel);
		
		JLabel falsosPositivos = new JLabel ("falsosPositivos");
		leftPanel.add(falsosPositivos);
		
		JTextField valorFp= new JTextField(3);
		valorFp.setEditable(false);
		leftPanel.add(valorFp);
		
		JLabel falsosNegativos= new JLabel ("falsoNegativos");
		leftPanel.add(falsosNegativos);
		
		JTextField valorFn= new JTextField(3);
		valorFp.setEditable(false);
		leftPanel.add(valorFn);
		
		JButton visual = new JButton("Visualizar Gráfico");
		rightPanel.add(visual);
		visual.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BoxPlotWindow boxPlot = new BoxPlotWindow();
				boxPlot.open();
				
			}
		});
		
		JButton returnMenu = new JButton("Inicio >");
		rightPanel.add(returnMenu);
		returnMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PrimaryWindow window = new PrimaryWindow();
				window.open();
				dispose();
				
			}
		});
		
		JButton avaliarConfig = new JButton("Avaliar Configuração");
		rightPanel.add(avaliarConfig);
		//falta listener
		
		
	}
	
}
