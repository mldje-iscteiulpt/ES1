package pt.antiSpamFilterGUI;

/**
 * Classe responsável por inicializar a janela que demonstrará o gráfico BoxPlot resultante da execução da configuração
 * automática do filtro anti-spam. Contém os métodos que criam a janela exclusiva deste mesmo gráfico, sendo a frame idêntica
 * à frame do Menú Secundário. 
 * 
 * @author ES1-2017-IC2-82
 * @see BoxPlot 
 */

@SuppressWarnings("serial")
public class BoxPlotWindow extends MenuSecundario {

	/**
	 * Inicializar a janela da Box Plot.
	 */
	public BoxPlotWindow() {
		super("BoxPlot");
		setTitle("Boxplot");
	}
	
	/**
	 * Abrir a janela da Box Plot.
	 */
	
	public void open() {
		setSize(500, 500);
		setVisible(true);
	}
	
}
