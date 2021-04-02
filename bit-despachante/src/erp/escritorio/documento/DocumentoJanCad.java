package erp.escritorio.documento;

import java.awt.FlowLayout;
import java.awt.KeyboardFocusManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import erp.arquitetura.Sis;
import erp.arquitetura.gui.ConfiguracaoGui;
import erp.arquitetura.gui.FocoEvento;
import erp.arquitetura.gui.Gui;
import erp.arquitetura.gui.Imagem;

@SuppressWarnings("serial")
public final class DocumentoJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private DocumentoControl documentoControl;
	private DocumentoPainelCad documentoPainelCad;

	public DocumentoJanCad() {
		iniciarLayout();
		iniciarGui();
		iniciarFocoControlador();
		iniciarGuiControlador();
		iniciarControlador();
	}

	@Override
	public void atualizarTable() {

	}

	@Override
	public ConfiguracaoGui getConfiguracaoGui() {
		return configuracaoGui;
	}

	public DocumentoControl getDocumentoCont() {
		return documentoControl;
	}

	public DocumentoPainelCad getDocumentoPainelCad() {
		return documentoPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		documentoControl = new DocumentoControl();
		addWindowListener(documentoControl.new Frame());
		documentoPainelCad.getTB().getExcluirBtn().addActionListener(documentoControl.new Exclui());
		documentoPainelCad.getTB().getNovoBtn().addActionListener(documentoControl.new Novo());
		documentoPainelCad.getTB().getPesquisarBtn().addActionListener(documentoControl.new Pesquisa());
		documentoPainelCad.getTB().getImprimirBtn().addActionListener(documentoControl.new Imprime());
		documentoPainelCad.getTB().getRelatorioBtn().addActionListener(documentoControl.new Relatorio());
		documentoPainelCad.getTB().getSalvarBtn().addActionListener(documentoControl.new Salva());
		documentoPainelCad.getTB().getHomeBtn().addActionListener(documentoControl.new Home());
		documentoPainelCad.getTB().getRegistrosBtn().addActionListener(documentoControl.new Registro());
		documentoPainelCad.getTB().getCsvBtn().addActionListener(documentoControl.new FormatoCsv());
		documentoPainelCad.getTB().getGraficoBtn().setEnabled(false);

	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());

		documentoPainelCad = new DocumentoPainelCad();

		final JScrollPane scrollPane = new JScrollPane(documentoPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (documentoPainelCad.isAncestorOf(focused)) {
							documentoPainelCad.scrollRectToVisible(focused.getBounds());
						}
					}
				});
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		add(scrollPane);
		setContentPane(scrollPane);
		pack();
	}

	@Override
	public void iniciarGuiControlador() {
		configuracaoGui = new ConfiguracaoGui(this);
	}

	@Override
	public void iniciarLayout() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setPreferredSize(Sis.getTamanhoJanela());
		setMinimumSize(Sis.getTamanhoJanela());
		setSize(Sis.getTamanhoJanela());
		setMaximumSize(Sis.getTamanhoJanela());
	}

	@Override
	public void iniciarTabela() {
	}

	@Override
	public void limparGui() {
		configuracaoGui.limparGui();
	}

	@Override
	public void reiniciarGui() {
		documentoPainelCad.reiniciarGui();
	}
}
