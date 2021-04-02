package erp.financeiro.vendas.servico;

import java.util.Collections;
import java.util.List;

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
import erp.negocio.cliente.Cliente;
import erp.negocio.cliente.ClienteComp;
import erp.negocio.cliente.ClienteFac;
import erp.negocio.servico.Servico;
import erp.negocio.servico.ServicoComp;
import erp.negocio.servico.ServicoFac;

@SuppressWarnings("serial")
public final class VendaServicoPainelCad extends JPanel implements Gui {

	private JComboBox<Cliente> boxCliente;
	private JComboBox<Servico> boxServico;

	private ConfiguracaoGui configuracaoGui;
	private JTextField fieldData;
	private JFormattedTextField fieldEntrada;
	private JFormattedTextField fieldEstorno;
	private JTextField fieldHora;
	private JFormattedTextField fieldQtde;
	private JTextField fieldUsuario;
	private ToolBar toolBar;
	private VendaServicoControl vendaServicoControl;

	public VendaServicoPainelCad() {
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

	public JComboBox<Cliente> getGuiCliente() {
		return boxCliente;
	}

	public JTextField getGuiData() {
		return fieldData;
	}

	public JFormattedTextField getGuiEntrada() {
		return fieldEntrada;
	}

	public JFormattedTextField getGuiEstorno() {
		return fieldEstorno;
	}

	public JTextField getGuiHora() {
		return fieldHora;
	}

	public JFormattedTextField getGuiQtde() {
		return fieldQtde;
	}

	public JComboBox<Servico> getGuiServico() {
		return boxServico;
	}

	public JTextField getGuiUsuario() {
		return fieldUsuario;
	}

	public ToolBar getTB() {
		return toolBar;
	}

	public VendaServicoControl getVendaServicoCont() {
		return vendaServicoControl;
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

		add(new JLabel("USUARIO"));

		fieldUsuario = new JTextField();
		fieldUsuario.setDocument(new EntradaMaiuscula(50));
		add(fieldUsuario);

		add(new JLabel("DATA"));

		fieldData = new JFormattedTextField();
		add(fieldData);

		add(new JLabel("HORA"));

		fieldHora = new JFormattedTextField();
		add(fieldHora);

		add(new JLabel("CLIENTE"));

		boxCliente = new JComboBox<Cliente>();
		List<Cliente> clienteList = (List<Cliente>) ClienteFac.getRegistro();
		Collections.sort(clienteList, new ClienteComp().new Nome());
		for (Cliente cliente : clienteList) {
			boxCliente.addItem(cliente);
		}
		add(boxCliente);

		add(new JLabel("SERVIÇO"));

		boxServico = new JComboBox<Servico>();
		List<Servico> servicoList = (List<Servico>) ServicoFac.getRegistro();
		Collections.sort(servicoList, new ServicoComp().new Nome());
		for (Servico servico : servicoList) {
			boxServico.addItem(servico);
		}
		add(boxServico);

		add(new JLabel("QUANTIDADE"));

		fieldQtde = new JFormattedTextField();
		add(fieldQtde);

		add(new JLabel("ENTRADA"));

		fieldEntrada = new JFormattedTextField();
		add(fieldEntrada);

		add(new JLabel("ESTORNO"));

		fieldEstorno = new JFormattedTextField();
		add(fieldEstorno);
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
