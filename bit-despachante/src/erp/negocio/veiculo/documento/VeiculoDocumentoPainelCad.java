package erp.negocio.veiculo.documento;

import java.util.Collections;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import erp.arquitetura.Sis;
import erp.arquitetura.gui.ConfiguracaoGui;
import erp.arquitetura.gui.EntradaMaiuscula;
import erp.arquitetura.gui.FocoEvento;
import erp.arquitetura.gui.Gui;
import erp.arquitetura.gui.ToolBar;
import erp.negocio.veiculo.Veiculo;
import erp.negocio.veiculo.VeiculoComp;
import erp.negocio.veiculo.VeiculoFac;
import erp.sistema.main.MainControl;

@SuppressWarnings("serial")
public final class VeiculoDocumentoPainelCad extends JPanel implements Gui {

	private JComboBox<String> boxLocalVeiculoDocumento;
	private JComboBox<String> boxSituacaoVeiculoDocumento;
	private JComboBox<Veiculo> boxVeiculo;
	private ConfiguracaoGui configuracaoGui;
	private JTextField fieldAnoDevolucaoVeiculoDocumento;
	private JTextField fieldAnoRecebimentoVeiculoDocumento;
	private JTextField fieldCNPJRecebedorVeiculoDocumento;
	private JTextField fieldCPFRecebedorVeiculoDocumento;
	private JTextField fieldDataDevolucaoVeiculoDocumento;
	private JTextField fieldDataRecebimentoVeiculoDocumento;
	private JTextField fieldNomeProprietário;
	private JTextField fieldNomeRecebedorVeiculoDocumento;
	private JTextField fieldRGNumeroRecebedorVeiculoDocumento;
	private JTextField fieldRGOrgaoEmisssorRecebedorVeiculoDocumento;
	private JLabel labelVeiculo;
	private ToolBar toolBar;

	public VeiculoDocumentoPainelCad() {
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

	public JTextField getGuiAnoDevolucaoVeiculoDocumento() {
		return fieldAnoDevolucaoVeiculoDocumento;
	}

	public JTextField getGuiAnoRecebimentoVeiculoDocumento() {
		return fieldAnoRecebimentoVeiculoDocumento;
	}

	public JTextField getGuiCNPJRecebedorVeiculoDocumento() {
		return fieldCNPJRecebedorVeiculoDocumento;
	}

	public JTextField getGuiCPFRecebedorVeiculoDocumento() {
		return fieldCPFRecebedorVeiculoDocumento;
	}

	public JTextField getGuiDiaDevolucaoVeiculoDocumento() {
		return fieldDataDevolucaoVeiculoDocumento;
	}

	public JTextField getGuiDiaRecebimentoVeiculoDocumento() {
		return fieldDataRecebimentoVeiculoDocumento;
	}

	public JComboBox<String> getGuiLocalVeiculoDocumento() {
		return boxLocalVeiculoDocumento;
	}

	public JTextField getGuiNomeProprietário() {
		return fieldNomeProprietário;
	}

	public JTextField getGuiNomeRecebedorVeiculoDocumento() {
		return fieldNomeRecebedorVeiculoDocumento;
	}

	public JTextField getGuiRGNumeroRecebedorVeiculoDocumento() {
		return fieldRGNumeroRecebedorVeiculoDocumento;
	}

	public JTextField getGuiRGOrgaoEmisssorRecebedorVeiculoDocumento() {
		return fieldRGOrgaoEmisssorRecebedorVeiculoDocumento;
	}

	public JComboBox<String> getGuiSituacaoVeiculoDocumento() {
		return boxSituacaoVeiculoDocumento;
	}

	public JComboBox<Veiculo> getGuiVeiculo() {
		return boxVeiculo;
	}

	public JLabel getLabelVeiculo() {
		return labelVeiculo;
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

		labelVeiculo = new JLabel("PLACA");
		labelVeiculo.setCursor(Sis.getNovaJanelaCursor());
		add(labelVeiculo);

		boxVeiculo = new JComboBox<Veiculo>();

		List<Veiculo> listVeiculo = (List<Veiculo>) VeiculoFac.getRegistro();
		Collections.sort(listVeiculo, new VeiculoComp().new Placa());

		for (Veiculo veiculo : listVeiculo) {
			boxVeiculo.addItem(veiculo);
		}

		add(boxVeiculo);

		add(new JLabel("NOME DA PESSOA QUE RECEBEU O DOCUMENTO"));

		fieldNomeRecebedorVeiculoDocumento = new JTextField();
		fieldNomeRecebedorVeiculoDocumento.setDocument(new EntradaMaiuscula(50));
		add(fieldNomeRecebedorVeiculoDocumento);

		add(new JLabel("IDENTIDADE DA PESSOA QUE RECEBEU O DOCUMENTO"));

		fieldRGNumeroRecebedorVeiculoDocumento = new JTextField();
		fieldRGNumeroRecebedorVeiculoDocumento.setDocument(new EntradaMaiuscula(15));
		add(fieldRGNumeroRecebedorVeiculoDocumento);

		add(new JLabel("IDENTIDADE ÓRGÃO EMISSOR DA PESSOA QUE RECEBEU O DOCUMENTO"));

		fieldRGOrgaoEmisssorRecebedorVeiculoDocumento = new JTextField();
		fieldRGOrgaoEmisssorRecebedorVeiculoDocumento.setDocument(new EntradaMaiuscula(20));
		add(fieldRGOrgaoEmisssorRecebedorVeiculoDocumento);

		add(new JLabel("CPF DA PESSOA QUE RECEBEU O DOCUMENTO"));

		fieldCPFRecebedorVeiculoDocumento = new JTextField();
		add(fieldCPFRecebedorVeiculoDocumento);

		add(new JLabel("CNPJ DA PESSOA QUE RECEBEU O DOCUMENTO"));

		fieldCNPJRecebedorVeiculoDocumento = new JTextField();
		add(fieldCNPJRecebedorVeiculoDocumento);

		add(new JLabel("SITUAÇÃO DO DOCUMENTO"));

		boxSituacaoVeiculoDocumento = new JComboBox<String>();
		boxSituacaoVeiculoDocumento.addItem("");
		boxSituacaoVeiculoDocumento.addItem("AGUARDANDO");
		boxSituacaoVeiculoDocumento.addItem("PROCESSANDO");
		boxSituacaoVeiculoDocumento.addItem("FINALIZADO");
		add(boxSituacaoVeiculoDocumento);

		add(new JLabel("DOCUMENTO ESTA ATUALMENTE COM"));

		boxLocalVeiculoDocumento = new JComboBox<String>();
		boxLocalVeiculoDocumento.addItem("");
		boxLocalVeiculoDocumento.addItem("CONSESSSIONÁRIA");
		boxLocalVeiculoDocumento.addItem("DESPACHANTE");
		boxLocalVeiculoDocumento.addItem("CLIENTE");
		boxLocalVeiculoDocumento.addItem("DELEGACIA - DETRAN");
		boxLocalVeiculoDocumento.addItem("FÁBRICA");
		boxLocalVeiculoDocumento.addItem("LOJA");
		boxLocalVeiculoDocumento.addItem("BANCO");
		add(boxLocalVeiculoDocumento);

		add(new JLabel("ANO DE RECEBIMENTO DO DOCUMENTO"));

		fieldAnoRecebimentoVeiculoDocumento = new JTextField();
		add(fieldAnoRecebimentoVeiculoDocumento);

		add(new JLabel("DATA DE RECEBIMENTO DO DOCUMENTO"));

		fieldDataRecebimentoVeiculoDocumento = new JTextField();
		fieldDataRecebimentoVeiculoDocumento.setDocument(new EntradaMaiuscula(2));
		add(fieldDataRecebimentoVeiculoDocumento);

		add(new JLabel("ANO DE DEVOLUÇÃO DO DOCUMENTO"));

		fieldAnoDevolucaoVeiculoDocumento = new JTextField();
		add(fieldAnoDevolucaoVeiculoDocumento);

		add(new JLabel("DATA DE DEVOLUÇÃO DO DOCUMENTO"));

		fieldDataDevolucaoVeiculoDocumento = new JTextField();
		fieldDataDevolucaoVeiculoDocumento.setDocument(new EntradaMaiuscula(2));
		add(fieldDataDevolucaoVeiculoDocumento);
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
		Veiculo veiculo = null;
		List<Veiculo> listVeiculo = (List<Veiculo>) VeiculoFac.getRegistro();
		Collections.sort(listVeiculo, new VeiculoComp().new Placa());
		this.boxVeiculo.removeAllItems();

		for (Veiculo v : listVeiculo) {
			this.boxVeiculo.addItem(v);
		}

		if (MainControl.getVeiculoDocumentoJanCad().getVeiculoDocumentoCont().getVeiculoDocumento() != null) {
			veiculo = MainControl.getVeiculoDocumentoJanCad().getVeiculoDocumentoCont().getVeiculoDocumento()
					.getVeiculo();
			boxVeiculo.setSelectedItem(veiculo);
		}

	}
}