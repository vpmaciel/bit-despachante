package erp.veiculo;

import javax.swing.BoxLayout;
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
public final class VeiculoPainelCad extends JPanel implements Gui {

    private ConfiguracaoGui configuracaoGui;
    private JTextField fieldPlaca;
    private JTextField fieldMarca;
    private JTextField fieldNomeProprietario;
    private JTextField fieldCpfCnpjProprietario;
    private JTextField fieldModelo;

    private ToolBar toolBar;

    public VeiculoPainelCad() {
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

    public JTextField getGuiCpfCnpjProprietario() {
	return fieldCpfCnpjProprietario;
    }

    public JTextField getGuiMarca() {
	return fieldMarca;
    }

    public JTextField getGuiModelo() {
	return fieldModelo;
    }

    public JTextField getGuiNomeProprietario() {
	return fieldNomeProprietario;
    }

    public JTextField getGuiPlaca() {
	return fieldPlaca;
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

	add(new JLabel("PLACA DO VEÍCULO"));

	fieldPlaca = new JTextField();
	fieldPlaca.setDocument(new EntradaMaiuscula(8));
	add(fieldPlaca);

	add(new JLabel("MARCA DO VEÍCULO"));

	fieldMarca = new JTextField();
	fieldMarca.setDocument(new EntradaMaiuscula(50));
	add(fieldMarca);

	add(new JLabel("MODELO DO VEÍCULO"));

	fieldModelo = new JTextField();
	fieldModelo.setDocument(new EntradaMaiuscula(50));
	add(fieldModelo);

	add(new JLabel("CPF | CNPJ DO PROPRIETÁRIO"));

	fieldCpfCnpjProprietario = new JTextField();
	fieldCpfCnpjProprietario.setDocument(new EntradaMaiuscula(20));
	add(fieldCpfCnpjProprietario);

	add(new JLabel("NOME DO PROPRIETÁRIO"));

	fieldNomeProprietario = new JTextField();
	fieldNomeProprietario.setDocument(new EntradaMaiuscula(50));
	add(fieldNomeProprietario);
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