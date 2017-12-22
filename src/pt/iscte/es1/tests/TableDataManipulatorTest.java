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

public class TableDataManipulatorTest {
	
	private TableDataManipulator tableData;
	private MenuSecundario menu;
	private DataReader dataReader;
	JTable table;
	DefaultTableModel model;

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

	@Test
	public void testWriteOptimalDataToTable() {
		String[] data = {"0.1","0.2"};
		tableData.writeOptimalDataToTable(data);
	}
	
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

	@Test
	public void testResetValues() {
		tableData.resetValues();
	}
	
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
	
	@Test
	public void testWriteRulesWeights() {
		Map<String,Double> rules = new TreeMap<String,Double>();
		String filePathRules = "../ES1-2017-IC2-82/JUnitTestInput/TestFile";
		tableData.writeRulesWeights(filePathRules, rules);
	}
}
