package pt.iscte.es1.tests;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.TreeMap;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.Before;
import org.junit.Test;

import pt.iscte.es1.antiSpamFilterGUI.MenuSecundario;
import pt.iscte.es1.reader.DataReader;
import pt.iscte.es1.tabledata.TableDataManipulator;

/** 
 * JUnit responsável por testar a classe que manipula os dados na JTable do menú secundário (manual ou automático).
 * @author ES1-2017-IC2-82
 */

public class TableDataManipulatorTest {
	
	/** Instância da classe TableDataManipulator */
	private TableDataManipulator tableData;
	/** Instância de classe MenuSecundario
	private MenuSecundario menu;
	/** Instância de classe de leitura de dados (DataReader) */
	private DataReader dataReader;
	/** Tabela do menú secundário */
	JTable table;
	/** DefaultTableModel utilizada em menú secundário */
	DefaultTableModel model;

	/** Instanciação de diversos elementos necessários à escrita de dados na tabela. */
	
	@Before
	public void setUpWriteOptimalDataToTable() throws Exception {
		table = new JTable();
		model = new DefaultTableModel(0, 2);
		String[] columnNames = { "Regra", "Peso" };
		model.setColumnIdentifiers(columnNames);
		model.addRow(new Object[] {"a", 0.0});
		model.addRow(new Object[] {"b", 0.0});
		tableData=new TableDataManipulator(table,model);
	}

	/** Teste de escrita de dados em tabela. */
	
	@Test
	public void testWriteOptimalDataToTable() {
		String[] data = {"0.1","0.2"};
		tableData.writeOptimalDataToTable(data);
	}
	
	/** Preparação de modelo necessário a testagem de reset de valores em tabela */
	
	@Before
	public void setUpResetValues() throws Exception {
		JTable table = new JTable();
		DefaultTableModel model = new DefaultTableModel(0, 2);
		String[] columnNames = { "Regra", "Peso" };
		model.setColumnIdentifiers(columnNames);
		model.addRow(new Object[] {"a", 0.0});
		model.addRow(new Object[] {"b", 0.0});
		tableData=new TableDataManipulator(table,model);
	}

	/** Teste referente a reset de valores em tabela, colocando os mesmos a 0.0 */
	@Test
	public void testResetValues() {
		tableData.resetValues();
	}
	
	/** Instanciação e modificação de elementos para testagem de escrita de regras. */
	
	@Before
	public void setUpWriteRulesWeights() throws Exception {
		DefaultTableModel model = new DefaultTableModel(0, 2);
		String[] columnNames = { "Regra", "Peso" };
		model.setColumnIdentifiers(columnNames);
		model.addRow(new Object[] {"a", 0.0});
		model.addRow(new Object[] {"b", 2.0});
		model.addRow(new Object[] {"a", "4.0"});
		model.addRow(new Object[] {"b", "5.0"});
	}
	
	/** Testagem de escrita de regras em tabelas. */
	@Test
	public void testWriteRulesWeights() {
		Map<String,Double> rules = new TreeMap<String,Double>();
		String filePathRules = "../ES1-2017-IC2-82/JUnitTestInput/TestFile";
		tableData.writeRulesWeights(filePathRules, rules);
	}
}
