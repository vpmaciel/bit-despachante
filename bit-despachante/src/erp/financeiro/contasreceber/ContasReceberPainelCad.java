package erp.financeiro.contasreceber;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import erp.arquitetura.Sis;
import erp.arquitetura.gui.ConfiguracaoGui;
import erp.arquitetura.gui.EntradaMaiuscula;
import erp.arquitetura.gui.FocoEvento;
import erp.arquitetura.gui.Gui;
import erp.arquitetura.gui.ToolBar;

@SuppressWarnings("serial")
public final class ContasReceberPainelCad extends JPanel implements Gui {

	private JComboBox<String> boxStatus;
	private ConfiguracaoGui configuracaoGui;
	private ContasReceberControl contasReceberControl;
	private JTextField fieldDataVencimento;
	private JFormattedTextField fieldDescricao;
	private JFormattedTextField fieldValor;
	private ToolBar toolBar;

	public ContasReceberPainelCad() {
		iniciarLayout();
		iniciarGui();
		iniciarFocoControlador();
		iniciarGuiControlador();
	}

	@Override
	public void atualizarTable() {
	}

	@Override
	public ConfiguracaoGui getConfiguracaoGui() {
		return configuracaoGui;
	}

	public ContasReceberControl getContasReceberCont() {
		return contasReceberControl;
	}

	public JTextField getGuiDataVencimento() {
		return fieldDataVencimento;
	}

	public JFormattedTextField getGuiDescricao() {
		return fieldDescricao;
	}

	public JComboBox<String> getGuiStatus() {
		return boxStatus;
	}

	public JFormattedTextField getGuiValor() {
		return fieldValor;
	}

	public ToolBar getTB() {
		return toolBar;
	}

	@Override
	public void iniciarControlador() {
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		toolBar = new ToolBar();

		add(toolBar.getTB());

		add(new JLabel("DATA DE VENCIMENTO"));

		fieldDataVencimento = new JTextField();
		fieldDataVencimento.setDocument(new EntradaMaiuscula(50));
		add(fieldDataVencimento);

		add(new JLabel("DESCRIÇÃO"));

		fieldDescricao = new JFormattedTextField();
		add(fieldDescricao);

		add(new JLabel("VALOR"));

		fieldValor = new JFormattedTextField();
		add(fieldValor);

		add(new JLabel("STATUS"));

		boxStatus = new JComboBox<String>();
		boxStatus.addItem("");
		boxStatus.addItem("PAGA");
		boxStatus.addItem("VENCIDA");
		boxStatus.addItem("NÃO VENCIDA");
		add(boxStatus);
	}

	@Override
	public void iniciarGuiControlador() {
		configuracaoGui = new ConfiguracaoGui(this);
	}

	@Override
	public void iniciarLayout() {
		setBorder(Sis.getBordaPainel());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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

	}
}
