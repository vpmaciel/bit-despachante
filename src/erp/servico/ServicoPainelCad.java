package erp.servico;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import erp.arquitetura.NumeroDecimal;
import erp.arquitetura.Sis;
import erp.arquitetura.gui.ConfiguracaoGui;
import erp.arquitetura.gui.EntradaMaiuscula;
import erp.arquitetura.gui.FocoEvento;
import erp.arquitetura.gui.Gui;
import erp.arquitetura.gui.ToolBar;

@SuppressWarnings("serial")
public final class ServicoPainelCad extends JPanel implements Gui {

    private ConfiguracaoGui configuracaoGui;
    private JTextField fieldData;
    private JTextField fieldPlaca;
    private JTextField fieldValor;
    private JTextField fieldDescricao;
    private JTextField fieldCpfCnpjCliente;
    private JTextField fieldNomeCliente;
    private JTextField fieldTelefoneCliente;
    private ToolBar toolBar;

    public ServicoPainelCad() {
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

    public JTextField getGuiCpfCnpjCliente() {
	return fieldCpfCnpjCliente;
    }

    public JTextField getGuiData() {
	return fieldData;
    }

    public JTextField getGuiDescricao() {
	return fieldDescricao;
    }

    public JTextField getGuiNomeCliente() {
	return fieldNomeCliente;
    }

    public JTextField getGuiPlaca() {
	return fieldPlaca;
    }

    public JTextField getGuiTelefoneCliente() {
	return fieldTelefoneCliente;
    }

    public JTextField getGuiValor() {
	return fieldValor;
    }

    public ToolBar getTB() {
	return toolBar;
    }

    public ToolBar getToolBar() {
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
	fieldData.setEnabled(false);
	add(fieldData);

	add(new JLabel("PLACA DO VEÍCULO"));

	fieldPlaca = new JTextField();
	fieldPlaca.setDocument(new EntradaMaiuscula(8));
	add(fieldPlaca);

	add(new JLabel("DESCRIÇÃO DO VEÍCULO"));

	fieldDescricao = new JTextField();
	fieldDescricao.setDocument(new EntradaMaiuscula(50));
	add(fieldDescricao);

	add(new JLabel("VALOR DO SERVÍÇO"));

	fieldValor = new JTextField();
	fieldValor.addKeyListener(new NumeroDecimal());
	fieldValor.setDocument(new EntradaMaiuscula(15));
	add(fieldValor);

	add(new JLabel("CPF | CNPJ DO CLIENTE"));

	fieldCpfCnpjCliente = new JTextField();
	fieldCpfCnpjCliente.setDocument(new EntradaMaiuscula(20));
	add(fieldCpfCnpjCliente);

	add(new JLabel("NOME DO CLIENTE"));

	fieldNomeCliente = new JTextField();
	fieldNomeCliente.setDocument(new EntradaMaiuscula(50));
	add(fieldNomeCliente);

	add(new JLabel("TELEFONE DO CLIENTE"));

	fieldTelefoneCliente = new JTextField();
	fieldTelefoneCliente.setDocument(new EntradaMaiuscula(15));
	add(fieldTelefoneCliente);

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