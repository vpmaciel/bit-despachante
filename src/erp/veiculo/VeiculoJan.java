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
    private VeiculoControl veiculoControl;
    private VeiculoPainelCad veiculoPainelCad;
    private VeiculoPainelPesq veiculoPainelPesq;

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
	return veiculoPainelCad;
    }

    public JTabbedPane getTabbedPane() {
	return tabbedPane;
    }

    public VeiculoControl getVeiculoCont() {
	return veiculoControl;
    }

    public VeiculoPainelCad getVeiculoPainelCad() {
	return veiculoPainelCad;
    }

    public VeiculoPainelPesq getVeiculoPainelPesq() {
	return veiculoPainelPesq;
    }

    @Override
    public void iniciarControlador() {
	setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
	veiculoControl = new VeiculoControl();
	addWindowListener(veiculoControl.new Frame());
	veiculoPainelCad.getTB().getExcluirBtn().addActionListener(veiculoControl.new Exclui());
	veiculoPainelCad.getTB().getNovoBtn().addActionListener(veiculoControl.new Novo());
	veiculoPainelCad.getTB().getPesquisarBtn().addActionListener(veiculoControl.new Pesquisa());
	veiculoPainelCad.getTB().getImprimirBtn().addActionListener(veiculoControl.new Imprime());
	veiculoPainelCad.getTB().getRelatorioBtn().addActionListener(veiculoControl.new Relatorio());
	veiculoPainelCad.getTB().getSalvarBtn().addActionListener(veiculoControl.new Salva());
	veiculoPainelCad.getTB().getHomeBtn().addActionListener(veiculoControl.new Home());
	veiculoPainelCad.getTB().getCsvBtn().addActionListener(veiculoControl.new FormatoCsv());

	veiculoPainelPesq.iniciarControlador();
    }

    @Override
    public void iniciarFocoControlador() {
	new FocoEvento(this);
    }

    @Override
    public void iniciarGui() {
	setTitle(Sis.getNomeSistema() + " - " + "Veículo");
	setIconImage(Imagem.getLogoTipoImage());
	tabbedPane = new JTabbedPane();
	veiculoPainelCad = new VeiculoPainelCad();
	veiculoPainelPesq = new VeiculoPainelPesq();

	final JScrollPane scrollPane = new JScrollPane(veiculoPainelCad);

	KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
		new PropertyChangeListener() {

		    @Override
		    public void propertyChange(PropertyChangeEvent evt) {
			if (!(evt.getNewValue() instanceof JComponent)) {
			    return;
			}
			JComponent focused = (JComponent) evt.getNewValue();
			if (veiculoPainelCad.isAncestorOf(focused)) {
			    veiculoPainelCad.scrollRectToVisible(focused.getBounds());
			}
		    }
		});
	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

	tabbedPane.addTab("Cadastro", scrollPane);
	tabbedPane.addTab("Pesquisa", veiculoPainelPesq);

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
	veiculoPainelCad.limparGui();
    }

    @Override
    public void reiniciarGui() {
	veiculoPainelCad.reiniciarGui();
    }
}
