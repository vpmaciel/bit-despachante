package erp.cliente;

import java.awt.FlowLayout;
import java.awt.KeyboardFocusManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import erp.arquitetura.Sis;
import erp.arquitetura.gui.ConfiguracaoGui;
import erp.arquitetura.gui.FocoEvento;
import erp.arquitetura.gui.Gui;
import erp.arquitetura.gui.Imagem;

@SuppressWarnings("serial")
public final class ClienteJan extends JFrame implements Gui {

    private ConfiguracaoGui configuracaoGui;
    private JTabbedPane tabbedPane;
    private ClienteControl clienteControl;
    private ClientePainelCad clientePainelCad;
    private ClientePainelPesq clientePainelPesq;
    private ClientePainelGraf clientePainelGraf;

    public ClienteJan() {
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

    public ClienteControl getClienteCont() {
	return clienteControl;
    }

    @Override
    public ConfiguracaoGui getConfiguracaoGui() {
	return configuracaoGui;
    }

    public ClientePainelCad getClientePainelCad() {
	return clientePainelCad;
    }

    public ClientePainelPesq getClientePainelPesq() {
	return clientePainelPesq;
    }

    public ClientePainelCad getPanelConta() {
	return clientePainelCad;
    }

    public JTabbedPane getTabbedPane() {
	return tabbedPane;
    }

    @Override
    public void iniciarControlador() {
	setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
	clienteControl = new ClienteControl();
	addWindowListener(clienteControl.new Frame());
	clientePainelCad.getTB().getExcluirBtn().addActionListener(clienteControl.new Exclui());
	clientePainelCad.getTB().getNovoBtn().addActionListener(clienteControl.new Novo());
	clientePainelCad.getTB().getPesquisarBtn().addActionListener(clienteControl.new Pesquisa());
	clientePainelCad.getTB().getImprimirBtn().addActionListener(clienteControl.new Imprime());
	clientePainelCad.getTB().getRelatorioBtn().addActionListener(clienteControl.new Relatorio());
	clientePainelCad.getTB().getSalvarBtn().addActionListener(clienteControl.new Salva());
	clientePainelCad.getTB().getHomeBtn().addActionListener(clienteControl.new Home());
	clientePainelCad.getTB().getRegistrosBtn().addActionListener(clienteControl.new Registro());
	clientePainelCad.getTB().getCsvBtn().addActionListener(clienteControl.new FormatoCsv());
	clientePainelCad.getTB().getJsonBtn().addActionListener(clienteControl.new FormatoJson());

	clientePainelPesq.iniciarControlador();
    }

    @Override
    public void iniciarFocoControlador() {
	new FocoEvento(this);
    }

    @Override
    public void iniciarGui() {
	setTitle(Sis.getNomeSistema() + " - " + "Cliente");
	setIconImage(Imagem.getLogoTipoImage());
	tabbedPane = new JTabbedPane();
	clientePainelCad = new ClientePainelCad();
	clientePainelPesq = new ClientePainelPesq();
	clientePainelGraf = new ClientePainelGraf();

	final JScrollPane scrollPane = new JScrollPane(clientePainelCad);

	KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
		new PropertyChangeListener() {

		    @Override
		    public void propertyChange(PropertyChangeEvent evt) {
			if (!(evt.getNewValue() instanceof JComponent)) {
			    return;
			}
			JComponent focused = (JComponent) evt.getNewValue();
			if (clientePainelCad.isAncestorOf(focused)) {
			    clientePainelCad.scrollRectToVisible(focused.getBounds());
			}
		    }
		});
	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

	tabbedPane.addTab("Cadastro", scrollPane);
	tabbedPane.addTab("Pesquisa", clientePainelPesq);
	tabbedPane.addTab("Painel", clientePainelGraf);

	add(tabbedPane);
	setContentPane(tabbedPane);
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
	clientePainelCad.limparGui();
    }

    @Override
    public void reiniciarGui() {
	clientePainelCad.reiniciarGui();
    }
}
