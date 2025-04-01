package erp.veiculo;

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
public final class VeiculoJan extends JFrame implements Gui {

    private ConfiguracaoGui configuracaoGui;
    private JTabbedPane tabbedPane;
    private VeiculoControl marcaControl;
    private VeiculoPainelCad marcaPainelCad;
    private VeiculoPainelPesq marcaPainelPesq;

    public VeiculoJan() {
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

    public VeiculoPainelCad getPanelVeiculo() {
	return marcaPainelCad;
    }

    public JTabbedPane getTabbedPane() {
	return tabbedPane;
    }

    public VeiculoControl getVeiculoCont() {
	return marcaControl;
    }

    public VeiculoPainelCad getVeiculoPainelCad() {
	return marcaPainelCad;
    }

    public VeiculoPainelPesq getVeiculoPainelPesq() {
	return marcaPainelPesq;
    }

    @Override
    public void iniciarControlador() {
	setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
	marcaControl = new VeiculoControl();
	addWindowListener(marcaControl.new Frame());
	marcaPainelCad.getTB().getExcluirBtn().addActionListener(marcaControl.new Exclui());
	marcaPainelCad.getTB().getNovoBtn().addActionListener(marcaControl.new Novo());
	marcaPainelCad.getTB().getPesquisarBtn().addActionListener(marcaControl.new Pesquisa());
	marcaPainelCad.getTB().getImprimirBtn().addActionListener(marcaControl.new Imprime());
	marcaPainelCad.getTB().getRelatorioBtn().addActionListener(marcaControl.new Relatorio());
	marcaPainelCad.getTB().getSalvarBtn().addActionListener(marcaControl.new Salva());
	marcaPainelCad.getTB().getHomeBtn().addActionListener(marcaControl.new Home());
	marcaPainelCad.getTB().getRegistrosBtn().addActionListener(marcaControl.new Registro());
	marcaPainelCad.getTB().getCsvBtn().addActionListener(marcaControl.new FormatoCsv());
	marcaPainelCad.getTB().getJsonBtn().addActionListener(marcaControl.new FormatoJson());

	marcaPainelPesq.iniciarControlador();
    }

    @Override
    public void iniciarFocoControlador() {
	new FocoEvento(this);
    }

    @Override
    public void iniciarGui() {
	setTitle(Sis.getNomeSistema() + " - " + "VEÍCULO");
	setIconImage(Imagem.getLogoTipoImage());
	tabbedPane = new JTabbedPane();
	marcaPainelCad = new VeiculoPainelCad();
	marcaPainelPesq = new VeiculoPainelPesq();

	final JScrollPane scrollPane = new JScrollPane(marcaPainelCad);

	KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
		new PropertyChangeListener() {

		    @Override
		    public void propertyChange(PropertyChangeEvent evt) {
			if (!(evt.getNewValue() instanceof JComponent)) {
			    return;
			}
			JComponent focused = (JComponent) evt.getNewValue();
			if (marcaPainelCad.isAncestorOf(focused)) {
			    marcaPainelCad.scrollRectToVisible(focused.getBounds());
			}
		    }
		});
	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

	tabbedPane.addTab("Cadastro", scrollPane);
	tabbedPane.addTab("Pesquisa", marcaPainelPesq);

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
	marcaPainelCad.limparGui();
    }

    @Override
    public void reiniciarGui() {
	marcaPainelCad.reiniciarGui();
    }
}
