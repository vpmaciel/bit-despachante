package erp.sistema.main;

import java.awt.Frame;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import erp.arquitetura.gui.Msg;

public class Executavel {

    public static void main(String[] args) {
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    MainJan mainJan = new MainJan();
	    mainJan.setState(Frame.NORMAL);
	    mainJan.setVisible(true);
	    mainJan.setLocationRelativeTo(null);
	    mainJan.setResizable(false);
	    mainJan.toFront();
	} catch (ClassNotFoundException | IllegalAccessException | InstantiationException
		| UnsupportedLookAndFeelException exception) {
	    exception.printStackTrace();
	    Msg.erroLookAndFeel();
	} catch (Exception exception) {
	    exception.printStackTrace();	    
	    Msg.erroGeral();
	} 
    }
}