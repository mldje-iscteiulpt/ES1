package pt.main;

import java.awt.EventQueue;

import pt.antiSpamFilterGUI.AntiSpamFilterMenu;

public class Main {

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
}
