package erp.pedido.placa;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import erp.arquitetura.NumeroInteiro;
import erp.arquitetura.Sis;
import erp.arquitetura.gui.ConfiguracaoGui;
import erp.arquitetura.gui.EntradaMaiuscula;
import erp.arquitetura.gui.FocoEvento;
import erp.arquitetura.gui.Gui;
import erp.arquitetura.gui.ToolBar;

@SuppressWarnings("serial")
public final class PedidoPlacaPainelCad extends JPanel implements Gui {

    private ConfiguracaoGui configuracaoGui;
    private JTextField fieldPlaca;
    private JTextField fieldTipoPlaca;
    private JTextField fieldCorPlaca;
    private JTextField fieldCpfCnpjProprietario;
    private JTextField fieldRenavam;
    private JTextField fieldQuantidade;
    private ToolBar toolBar;

    public PedidoPlacaPainelCad() {
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

    public JTextField getGuiCorPlaca() {
	return fieldCorPlaca;
    }

    public JTextField getGuiCpfCnpjProprietario() {
	return fieldCpfCnpjProprietario;
    }

    public JTextField getGuiPlaca() {
	return fieldPlaca;
    }

    public JTextField getGuiQuantidade() {
	return fieldQuantidade;
    }

    public JTextField getGuiRenavam() {
	return fieldRenavam;
    }

    public JTextField getGuiTipoPlaca() {
	return fieldTipoPlaca;
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

	add(new JLabel("QUANTIDADE"));

	fieldQuantidade = new JTextField();
	fieldQuantidade.setDocument(new EntradaMaiuscula(1));
	fieldQuantidade.addKeyListener(new NumeroInteiro());
	add(fieldQuantidade);

	add(new JLabel("PLACA DO VEÍCULO"));

	fieldPlaca = new JTextField();
	fieldPlaca.setDocument(new EntradaMaiuscula(8));
	add(fieldPlaca);

	add(new JLabel("COR DA PLACA"));

	fieldCorPlaca = new JTextField();
	fieldCorPlaca.setDocument(new EntradaMaiuscula(50));
	add(fieldCorPlaca);

	add(new JLabel("TIPO DE PLACA"));

	fieldTipoPlaca = new JTextField();
	fieldTipoPlaca.setDocument(new EntradaMaiuscula(50));
	add(fieldTipoPlaca);

	add(new JLabel("CPF | CNPJ DO PROPRIETÁRIO"));

	fieldCpfCnpjProprietario = new JTextField();
	fieldCpfCnpjProprietario.setDocument(new EntradaMaiuscula(20));
	add(fieldCpfCnpjProprietario);

	add(new JLabel("RENAVAM"));

	fieldRenavam = new JTextField();
	fieldRenavam.setDocument(new EntradaMaiuscula(50));
	fieldRenavam.addKeyListener(new NumeroInteiro());
	add(fieldRenavam);

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