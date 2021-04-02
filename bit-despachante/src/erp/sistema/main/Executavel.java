package erp.sistema.main;

import java.awt.Frame;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import erp.arquitetura.Chave;
import erp.arquitetura.Jpa;
import erp.arquitetura.gui.Msg;
import erp.sistema.usuario.UsuarioUtil;

public class Executavel {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SplashScreen splash = new SplashScreen(5000);
			splash.toFront();
			MainJan mainJan = new MainJan();
			UsuarioUtil.criarUsuario();
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