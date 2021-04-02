package erp.escritorio.produto.compra.lista;

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
public final class CompraProdutoJanCad extends JFrame implements Gui {

	private CompraProdutoControl compraProdutoControl;
	private CompraProdutoPainelCad compraProdutoPainelCad;
	private ConfiguracaoGui configuracaoGui;

	public CompraProdutoJanCad() {
		iniciarLayout();
		iniciarGui();
		iniciarFocoControlador();
		iniciarGuiControlador();
		iniciarControlador();
	}

	@Override
	public void atualizarTable() {
	}

	public void desabilitarGui() {
	}

	public CompraProdutoControl getCompraProdutoCont() {
		return compraProdutoControl;
	}

	public CompraProdutoPainelCad getCompraProdutoPainelCad() {
		return compraProdutoPainelCad;
	}

	@Override
	public ConfiguracaoGui getConfiguracaoGui() {
		return configuracaoGui;
	}

	public CompraProdutoPainelCad getPanelCompraProdutoCadastro() {
		return compraProdutoPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		compraProdutoControl = new CompraProdutoControl();
		addWindowListener(compraProdutoControl.new Frame());
		compraProdutoPainelCad.getTB().getExcluirBtn().addActionListener(compraProdutoControl.new Exclui());
		compraProdutoPainelCad.getTB().getNovoBtn().addActionListener(compraProdutoControl.new Novo());
		compraProdutoPainelCad.getTB().getPesquisarBtn().addActionListener(compraProdutoControl.new Pesquisa());
		compraProdutoPainelCad.getTB().getImprimirBtn().addActionListener(compraProdutoControl.new Imprime());
		compraProdutoPainelCad.getTB().getRelatorioBtn().addActionListener(compraProdutoControl.new Relatorio());
		compraProdutoPainelCad.getTB().getSalvarBtn().addActionListener(compraProdutoControl.new Salva());
		compraProdutoPainelCad.getTB().getHomeBtn().addActionListener(compraProdutoControl.new Home());
		compraProdutoPainelCad.getTB().getRegistrosBtn().addActionListener(compraProdutoControl.new Registro());
		compraProdutoPainelCad.getTB().getCsvBtn().addActionListener(compraProdutoControl.new FormatoCsv());
		compraProdutoPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		compraProdutoPainelCad = new CompraProdutoPainelCad();

		final JScrollPane scrollPane = new JScrollPane(compraProdutoPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (compraProdutoPainelCad.isAncestorOf(focused)) {
							compraProdutoPainelCad.scrollRectToVisible(focused.getBounds());
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
		compraProdutoPainelCad.limparGui();
	}

	@Override
	public void reiniciarGui() {
		compraProdutoPainelCad.reiniciarGui();
	}
}
