package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class PrimaryWindow extends JFrame {

	private List<JTextField> textFieldsList = new ArrayList<JTextField>();

	public PrimaryWindow() {
		setTitle("Filtragem Anti-Spam");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
	}

	public void open() {
		addContent();

		setSize(800, 200);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int) (dim.getWidth() / 2 - this.getSize().getWidth() / 2),
				(int) (dim.getHeight() / 2 - this.getSize().getHeight() / 2));

		setVisible(true);
	}

	private void addContent() {
		/*
		 * Painel Central
		 */
		JPanel centralPanel = new JPanel();
		centralPanel.setLayout(new GridLayout(3, 3));
		add(centralPanel, BorderLayout.CENTER);

		for (int i = 0; i < 3; i++) {
			JTextField textField = new JTextField();
			textField.setEnabled(false);
			centralPanel.add(textField);
			textFieldsList.add(textField);
		}

		/*
		 * Paineis East e West
		 */
		JPanel westPanel = new JPanel();
		westPanel.setLayout(new GridLayout(3, 0));
		add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new GridLayout(3, 0));
		add(eastPanel, BorderLayout.EAST);

		for (int i = 0; i < 3; i++) {
			JLabel label = new JLabel(setName(i));
			westPanel.add(label);

			JButton button = new JButton("Procurar");
			eastPanel.add(button);
			button.addActionListener(new buttonListener(i));
		}

		/*
		 * Painel inferior
		 */
		JPanel inferiorPanel = new JPanel();
		inferiorPanel.setLayout(new GridLayout(0, 2));
		add(inferiorPanel, BorderLayout.SOUTH);

		// Automático
		JButton automaticButton = new JButton("Automático");
		inferiorPanel.add(automaticButton);
		automaticButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SecondaryWindow window = new SecondaryWindow(getTitle() , automaticButton.getText());
				window.open();
				dispose();

			}
		});

		// Manual
		JButton manualButton = new JButton("Manual");
		inferiorPanel.add(manualButton);
		manualButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SecondaryWindow window = new SecondaryWindow(getTitle() , manualButton.getText());
				window.open();
				dispose();

			}
		});

	}

	// Implementado como ActionListener para os botões de procura
	private class buttonListener implements ActionListener {
		private int index;

		public buttonListener(int index) {
			this.index = index;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			textFieldsList.get(index).setText(setName(index));
			System.out.println(setName(index) + " browse button was pressed");
		}
	}

	private String setName(int i) {
		String name = "";
		switch (i) {
		case 0:
			name = "rules";
			break;
		case 1:
			name = "ham";
			break;
		case 2:
			name = "spam";
			break;
		}

		return name;
	}

}
