package erp.sistema.main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import erp.arquitetura.Internet;
import erp.arquitetura.Sis;

@SuppressWarnings("serial")
public class SobrePainel extends JPanel {

	private final JLabel labelAutor = new JLabel();
	private final JLabel labelCopyright = new JLabel();
	private final JLabel labelEmpresa = new JLabel();
	private final JLabel labelTitulo = new JLabel();

	public SobrePainel() {
		super();
		inicializarGui();
	}

	private void inicializarGui() {
		this.setSize(300, 200);
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEtchedBorder());

		labelTitulo.setText("BIT - DESPACHANTE DE VEÍCULOS 1.0");

		labelAutor.setText("<html>Vicente Paulo Maciel - <a href=\"vpmaciel@gmail.com\">vpmaciel@gmail.com</a><html>");
		labelAutor.setCursor(Sis.getNovaJanelaCursor());
		labelAutor.addMouseListener(new Internet().new MailSistema("vpmaciel@gmail.com"));
		labelCopyright.setText("Copyright 2023. Todos os direitos reservados.");
		labelEmpresa.setText(
				"<html><a href=\"https://www.linkedin.com/in/vpmaciel/\">https://www.linkedin.com/in/vpmaciel/</a><html>");
		labelEmpresa.setCursor(Sis.getNovaJanelaCursor());
		labelEmpresa.addMouseListener(new Internet().new LinkSistema("https://www.linkedin.com/in/vpmaciel/"));
		
		this.add(labelTitulo, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 15, 0, 15), 0, 0));
		this.add(labelAutor, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(0, 15, 0, 15), 0, 0));
		this.add(labelCopyright, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(0, 15, 0, 15), 0, 0));
		this.add(labelEmpresa, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(0, 15, 5, 15), 0, 0));
	}
}
