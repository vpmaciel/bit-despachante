package erp.sistema.usuario;

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
public final class UsuarioJanCad extends JFrame implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private UsuarioControl usuarioControl;
	private UsuarioPainelCad usuarioPainelCad;

	public UsuarioJanCad() {
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

	@Override
	public ConfiguracaoGui getConfiguracaoGui() {
		return configuracaoGui;
	}

	public UsuarioPainelCad getPanelUsuario() {
		return usuarioPainelCad;
	}

	public UsuarioControl getUsuarioCont() {
		return usuarioControl;
	}

	public UsuarioPainelCad getUsuarioPainelCad() {
		return usuarioPainelCad;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		usuarioControl = new UsuarioControl();
		addWindowListener(usuarioControl.new Frame());
		usuarioPainelCad.getTB().getExcluirBtn().addActionListener(usuarioControl.new Exclui());
		usuarioPainelCad.getTB().getNovoBtn().addActionListener(usuarioControl.new Novo());
		usuarioPainelCad.getTB().getPesquisarBtn().addActionListener(usuarioControl.new Pesquisa());
		usuarioPainelCad.getTB().getImprimirBtn().addActionListener(usuarioControl.new Imprime());
		usuarioPainelCad.getTB().getRelatorioBtn().addActionListener(usuarioControl.new Relatorio());
		usuarioPainelCad.getTB().getSalvarBtn().addActionListener(usuarioControl.new Salva());
		usuarioPainelCad.getTB().getHomeBtn().addActionListener(usuarioControl.new Home());
		usuarioPainelCad.getTB().getRegistrosBtn().addActionListener(usuarioControl.new Registro());
		usuarioPainelCad.getTB().getGraficoBtn().setEnabled(false);
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		usuarioPainelCad = new UsuarioPainelCad();

		final JScrollPane scrollPane = new JScrollPane(usuarioPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (usuarioPainelCad.isAncestorOf(focused)) {
							usuarioPainelCad.scrollRectToVisible(focused.getBounds());
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
		usuarioPainelCad.limparGui();
	}

	@Override
	public void reiniciarGui() {
		usuarioPainelCad.reiniciarGui();
	}
}
