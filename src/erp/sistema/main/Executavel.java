package erp.sistema.main;

import java.awt.Frame;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import erp.arquitetura.Chave;
import erp.arquitetura.Jpa;
import erp.arquitetura.gui.Msg;

public class Executavel {

	public static void main(String[] args) {
		try {
			
				 int a = 5, b = 10;
				 a = a + b - (b = a);
				 System.out.println("a: " + a + ", b: " + b);
				 
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			System.setProperty("sun.java2d.dpiaware", "true");			
			MainJan mainJan = new MainJan();
			mainJan.setState(Frame.NORMAL);
			mainJan.setVisible(true);
			mainJan.setLocationRelativeTo(null);
			mainJan.setResizable(false);
			mainJan.toFront();
			new Chave();

		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException
				| UnsupportedLookAndFeelException exception) {
			exception.printStackTrace();
			Msg.erroLookAndFeel();
		} catch (Exception exception) {
			exception.printStackTrace();
			if (Jpa.getEntityManagerFactory().isOpen()) {
				Jpa.getEntityManagerFactory().close();
			}
			Msg.erroGeral();
		} finally {

		}
	}
}