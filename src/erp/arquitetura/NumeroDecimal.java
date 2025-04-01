package erp.arquitetura;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class NumeroDecimal implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
	JTextField textField = (JTextField) e.getSource();

	if (!((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == ',')) {
	    textField.setText("");
	}
	if (textField.getText().length() > 10) {
	    e.consume(); // Ignora a tecla digitada

	}
    }

    @Override
    public void keyReleased(KeyEvent e) {
	JTextField textField = (JTextField) e.getSource();

	if (!((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == ',')) {
	    textField.setText("");
	}
	if (textField.getText().length() > 10) {
	    e.consume(); // Ignora a tecla digitada
	}
    }

    @Override
    public void keyTyped(KeyEvent e) {
	JTextField textField = (JTextField) e.getSource();

	if (!((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == ',')) {
	    textField.setText("");
	}
	if (textField.getText().length() > 10) {
	    e.consume(); // Ignora a tecla digitada
	}
    }
}
