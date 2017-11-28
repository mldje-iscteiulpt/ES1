package pt.antiSpamFilterGUI;

@SuppressWarnings("serial")
public class BoxPlotWindow extends MenuSecundario {

	/**
	 * Inicializar a janela da Box Plot.
	 */
	public BoxPlotWindow() {
		super("BoxPlot");
		setTitle("Boxplot");
		open();
	}
	
	/**
	 * Abrir a janela da Box Plot.
	 */
	
	public void open() {
		setSize(500, 500);
		setVisible(true);
	}
	
}
