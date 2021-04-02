package erp.utilitarios.agenda.recado;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import erp.arquitetura.Sis;
import erp.arquitetura.gui.ConfiguracaoGui;
import erp.arquitetura.gui.EntradaMaiuscula;
import erp.arquitetura.gui.FocoEvento;
import erp.arquitetura.gui.Gui;
import erp.arquitetura.gui.ToolBar;

@SuppressWarnings("serial")
public final class RecadoPainelCad extends JPanel implements Gui {

	private ConfiguracaoGui configuracaoGui;
	private JTextField fieldData;
	private JTextField fieldDestinatario;
	private JTextArea fieldRecado;
	private JTextField fieldRemetente;
	private JLabel labelData;
	private JLabel labelDestinatario;
	private JLabel labelRecado;
	private JLabel labelRemetente;
	private ToolBar toolBar;

	public RecadoPainelCad() {
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

	public JTextField getGuiData() {
		return fieldData;
	}

	public JTextField getGuiDestinatario() {
		return fieldDestinatario;
	}

	public JTextArea getGuiRecado() {
		return fieldRecado;
	}

	public JTextField getGuiRemetente() {
		return fieldRemetente;
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

		labelData = new JLabel("DATA");
		add(labelData);

		fieldData = new JTextField();
		add(fieldData);

		labelRemetente = new JLabel("REMETENTE");
		add(labelRemetente);

		fieldRemetente = new JTextField();
		fieldRemetente.setDocument(new EntradaMaiuscula(50));
		add(fieldRemetente);

		labelDestinatario = new JLabel("DESTINATÁRIO");
		add(labelDestinatario);

		fieldDestinatario = new JTextField();
		fieldDestinatario.setDocument(new EntradaMaiuscula(14));
		add(fieldDestinatario);

		labelRecado = new JLabel("RECADO");
		add(labelRecado);

		fieldRecado = new JTextArea();
		fieldRecado.setLineWrap(true);
		fieldRecado.setDocument(new EntradaMaiuscula(500));
		add(fieldRecado);
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

	}

	@Override
	public void reiniciarGui() {

	}
}
