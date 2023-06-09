package erp.veiculo.marca;

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
public final class MarcaPainelCad extends JPanel implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private JTextField fieldTitular;	
	private ToolBar toolBar;
	private MarcaControl marcaControl;

	public MarcaPainelCad() {
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

	public JTextField getGuiDescricao() {
		return fieldTitular;
	}

	public ToolBar getTB() {
		return toolBar;
	}

	public MarcaControl getUsuarioCont() {
		return marcaControl;
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

		add(new JLabel("DESCRIÇÃO"));

		fieldTitular = new JTextField();
		fieldTitular.setDocument(new EntradaMaiuscula(10));
		add(fieldTitular);	
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