package erp.financeiro.caixa;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import erp.arquitetura.Sis;
import erp.arquitetura.gui.ConfiguracaoGui;
import erp.arquitetura.gui.FocoEvento;
import erp.arquitetura.gui.Gui;
import erp.arquitetura.gui.ToolBar;

@SuppressWarnings("serial")
public final class CaixaPainelCad extends JPanel implements Gui {

	private CaixaControl caixaControl;
	private ConfiguracaoGui configuracaoGui;
	private JTextField fieldData;
	private JTextField fieldEntradaProduto;
	private JTextField fieldEstornoTotal;
	private JTextField fieldSaidaTotal;
	private JTextField fieldSaldoAnterior;
	private JTextField fieldSaldoFinal;
	private ToolBar toolBar;

	public CaixaPainelCad() {
		iniciarLayout();
		iniciarGui();
		iniciarFocoControlador();
		iniciarGuiControlador();
	}

	@Override
	public void atualizarTable() {
	}

	public CaixaControl getCaixaCont() {
		return caixaControl;
	}

	@Override
	public ConfiguracaoGui getConfiguracaoGui() {
		return configuracaoGui;
	}

	public JTextField getGuiData() {
		return fieldData;
	}

	public JTextField getGuiEntrada() {
		return fieldEntradaProduto;
	}

	public JTextField getGuiEstorno() {
		return fieldEstornoTotal;
	}

	public JTextField getGuiSaida() {
		return fieldSaidaTotal;
	}

	public JTextField getGuiSaldo() {
		return fieldSaldoFinal;
	}

	public JTextField getGuiSaldoAnterior() {
		return fieldSaldoAnterior;
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

		add(new JLabel("DATA"));

		fieldData = new JTextField();
		add(fieldData);

		add(new JLabel("SALDO ANTERIOR"));

		fieldSaldoAnterior = new JTextField();
		add(fieldSaldoAnterior);

		add(new JLabel("ENTRADA"));

		fieldEntradaProduto = new JTextField();
		add(fieldEntradaProduto);

		add(new JLabel("ESTORNO"));

		fieldEstornoTotal = new JTextField();
		add(fieldEstornoTotal);

		add(new JLabel("SAÍDA"));

		fieldSaidaTotal = new JTextField();
		add(fieldSaidaTotal);

		add(new JLabel("SALDO"));

		fieldSaldoFinal = new JTextField();
		add(fieldSaldoFinal);
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
