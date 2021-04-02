package erp.negocio.veiculo;

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
import erp.negocio.centrocusto.CentroCusto;
import erp.negocio.centrocusto.CentroCustoComp;
import erp.negocio.centrocusto.CentroCustoFac;
import erp.negocio.veiculo.marca.VeiculoMarca;
import erp.negocio.veiculo.marca.VeiculoMarcaComp;
import erp.negocio.veiculo.marca.VeiculoMarcaFac;
import erp.negocio.veiculo.modelo.VeiculoModelo;
import erp.negocio.veiculo.modelo.VeiculoModeloComp;
import erp.negocio.veiculo.modelo.VeiculoModeloFac;
import erp.sistema.main.MainControl;

@SuppressWarnings("serial")
public final class VeiculoPainelCad extends JPanel implements Gui {
	
	private JComboBox<CentroCusto> boxCentroCusto;
	private JComboBox<String> boxFabricacao;
	private JComboBox<String> boxMesReferenciaCompra;
	private JComboBox<String> boxMesReferenciaVenda;
	private JComboBox<VeiculoMarca> boxVeiculoMarca;
	private JComboBox<VeiculoModelo> boxVeiculoModelo;
	private ConfiguracaoGui configuracaoGui;
	private JTextField fieldAnoFabricacao;
	private JTextField fieldAnoModelo;
	private JTextField fieldAnoReferenciaCompra;
	private JTextField fieldAnoReferenciaVenda;
	private JTextField fieldChassi;
	private JTextField fieldDataCompra;
	private JTextField fieldDataVenda;
	private JTextField fieldNumeroMotor;
	private JTextField fieldPlaca;
	private JTextField fieldProprietarioNome;
	private JTextField fieldRenavam;
	private JTextField fieldValorCompra;
	private JTextField fieldValorVenda;
	private JLabel labelCentroCusto;
	private JLabel labelVeiculoMarca;
	private JLabel labelVeiculoModelo;
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

	public JTextField getGuiAnoFabricacao() {
		return fieldAnoFabricacao;
	}

	public JTextField getGuiAnoModelo() {
		return fieldAnoModelo;
	}

	public JTextField getGuiAnoReferenciaCompra() {
		return fieldAnoReferenciaCompra;
	}

	public JTextField getGuiAnoReferenciaVenda() {
		return fieldAnoReferenciaVenda;
	}
	
	public JComboBox<CentroCusto> getGuiCentroCusto() {
		return boxCentroCusto;
	}

	public JTextField getGuiChassi() {
		return fieldChassi;
	}

	public JTextField getGuiDataCompra() {
		return fieldDataCompra;
	}

	public JTextField getGuiDataVenda() {
		return fieldDataVenda;
	}

	public JComboBox<String> getGuiFabricacao() {
		return boxFabricacao;
	}

	public JComboBox<String> getGuiMesReferenciaCompra() {
		return boxMesReferenciaCompra;
	}

	public JComboBox<String> getGuiMesReferenciaVenda() {
		return boxMesReferenciaVenda;
	}

	public JTextField getGuiNumeroMotor() {
		return fieldNumeroMotor;
	}

	public JTextField getGuiPlaca() {
		return fieldPlaca;
	}
	
	public JTextField getGuiProprietarioNome() {
		return fieldProprietarioNome;
	}

	public JTextField getGuiRenavam() {
		return fieldRenavam;
	}

	public JTextField getGuiValorCompra() {
		return fieldValorCompra;
	}

	public JTextField getGuiValorVenda() {
		return fieldValorVenda;
	}

	public JComboBox<VeiculoMarca> getGuiVeiculoMarca() {
		return boxVeiculoMarca;
	}

	public JComboBox<VeiculoModelo> getGuiVeiculoModelo() {
		return boxVeiculoModelo;
	}

	public JLabel getLabelCentroCusto() {
		return labelCentroCusto;
	}

	public JLabel getLabelVeiculoMarca() {
		return labelVeiculoMarca;
	}

	public JLabel getLabelVeiculoModelo() {
		return labelVeiculoModelo;
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

		this.add(toolBar.getTB());

		add(new JLabel("PLACA"));

		fieldPlaca = new JTextField();
		fieldPlaca.setDocument(new EntradaMaiuscula(8));
		add(fieldPlaca);

		labelVeiculoMarca = new JLabel("MARCA DO VEÍCULO");
		labelVeiculoMarca.setCursor(Sis.getNovaJanelaCursor());
		add(labelVeiculoMarca);

		boxVeiculoMarca = new JComboBox<VeiculoMarca>();

		List<VeiculoMarca> veiculoMarcas = (List<VeiculoMarca>) VeiculoMarcaFac.getRegistro();
		Collections.sort(veiculoMarcas, new VeiculoMarcaComp().new Marca());

		for (VeiculoMarca veiculoMarca : veiculoMarcas) {
			boxVeiculoMarca.addItem(veiculoMarca);
		}

		add(boxVeiculoMarca);

		labelVeiculoModelo = new JLabel("MODELO DO VEÍCULO");
		labelVeiculoModelo.setCursor(Sis.getNovaJanelaCursor());
		add(labelVeiculoModelo);

		boxVeiculoModelo = new JComboBox<VeiculoModelo>();

		List<VeiculoModelo> veiculoModelos = (List<VeiculoModelo>) VeiculoModeloFac.getRegistro();

		Collections.sort(veiculoModelos, new VeiculoModeloComp().new Modelo());

		for (VeiculoModelo v : veiculoModelos) {
			boxVeiculoModelo.addItem(v);
		}

		add(boxVeiculoModelo);

		add(new JLabel("CHASSI"));

		fieldChassi = new JTextField();
		fieldChassi.setDocument(new EntradaMaiuscula(20));
		add(fieldChassi);

		add(new JLabel("NÚMERO DO MOTOR"));

		fieldNumeroMotor = new JTextField();
		fieldNumeroMotor.setDocument(new EntradaMaiuscula(15));
		add(fieldNumeroMotor);

		add(new JLabel("RENAVAM"));

		fieldRenavam = new JTextField();
		fieldRenavam.setDocument(new EntradaMaiuscula(15));
		add(fieldRenavam);

		add(new JLabel("VALOR DE COMPRA"));

		fieldValorCompra = new JTextField();
		fieldValorCompra.setDocument(new EntradaMaiuscula(10));
		add(fieldValorCompra);

		add(new JLabel("VALOR DE VENDA"));

		fieldValorVenda = new JTextField();
		fieldValorVenda.setDocument(new EntradaMaiuscula(10));
		add(fieldValorVenda);

		add(new JLabel("DATA DA COMPRA"));

		fieldDataCompra = new JTextField();
		add(fieldDataCompra);

		add(new JLabel("DATA DA VENDA"));

		fieldDataVenda = new JTextField();
		add(fieldDataVenda);

		add(new JLabel("MÊS DE REFERÊNCIA DA COMPRA"));

		boxMesReferenciaCompra = new JComboBox<String>();
		boxMesReferenciaCompra.addItem("");
		boxMesReferenciaCompra.addItem("JANEIRO");
		boxMesReferenciaCompra.addItem("FEVEREIRO");
		boxMesReferenciaCompra.addItem("MARÇO");
		boxMesReferenciaCompra.addItem("ABRIL");
		boxMesReferenciaCompra.addItem("MAIO");
		boxMesReferenciaCompra.addItem("JUNHO");
		boxMesReferenciaCompra.addItem("JULHO");
		boxMesReferenciaCompra.addItem("AGOSTO");
		boxMesReferenciaCompra.addItem("SETEMBRO");
		boxMesReferenciaCompra.addItem("OUTUBRO");
		boxMesReferenciaCompra.addItem("NOVEMBRO");
		boxMesReferenciaCompra.addItem("DEZEMBRO");
		add(boxMesReferenciaCompra);

		add(new JLabel("ANO DE REFERÊNCIA DA COMPRA"));

		fieldAnoReferenciaCompra = new JTextField();
		add(fieldAnoReferenciaCompra);

		add(new JLabel("MÊS DE REFERÊNCIA DA VENDA"));

		boxMesReferenciaVenda = new JComboBox<String>();
		boxMesReferenciaVenda.addItem("");
		boxMesReferenciaVenda.addItem("JANEIRO");
		boxMesReferenciaVenda.addItem("FEVEREIRO");
		boxMesReferenciaVenda.addItem("MARÇO");
		boxMesReferenciaVenda.addItem("ABRIL");
		boxMesReferenciaVenda.addItem("MAIO");
		boxMesReferenciaVenda.addItem("JUNHO");
		boxMesReferenciaVenda.addItem("JULHO");
		boxMesReferenciaVenda.addItem("AGOSTO");
		boxMesReferenciaVenda.addItem("SETEMBRO");
		boxMesReferenciaVenda.addItem("OUTUBRO");
		boxMesReferenciaVenda.addItem("NOVEMBRO");
		boxMesReferenciaVenda.addItem("DEZEMBRO");

		add(boxMesReferenciaVenda);

		add(new JLabel("ANO DE REFERÊNCIA DA VENDA"));

		fieldAnoReferenciaVenda = new JTextField();
		fieldAnoReferenciaVenda.setDocument(new EntradaMaiuscula(10));
		add(fieldAnoReferenciaVenda);

		add(new JLabel("NOME DO PROPRIETÁRIO"));

		fieldProprietarioNome = new JTextField();
		fieldProprietarioNome.setDocument(new EntradaMaiuscula(50));
		add(fieldProprietarioNome);

		add(new JLabel("ANO DE FABRICAÇÃO ( VEÍCULO )"));

		fieldAnoFabricacao = new JTextField();
		add(fieldAnoFabricacao);

		add(new JLabel("ANO MODELO ( VEÍCULO )"));

		fieldAnoModelo = new JTextField();
		add(fieldAnoModelo);

		add(new JLabel("FABRICAÇÃO"));

		boxFabricacao = new JComboBox<String>();
		boxFabricacao.addItem("");
		boxFabricacao.addItem("NACIONAL");
		boxFabricacao.addItem("IMPORTADO");
		add(boxFabricacao);

		labelCentroCusto = new JLabel("CENTRO DE CUSTO");
		add(labelCentroCusto);

		boxCentroCusto = new JComboBox<CentroCusto>();
		List<CentroCusto> centroCustos = (List<CentroCusto>) CentroCustoFac.getRegistro();
		Collections.sort(centroCustos, new CentroCustoComp().new Nome());
		for (CentroCusto c : centroCustos) {
			boxCentroCusto.addItem(c);
		}

		add(boxCentroCusto);
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
		CentroCusto centroCusto = null;
		List<CentroCusto> centroCustos = (List<CentroCusto>) CentroCustoFac.getRegistro();
		Collections.sort(centroCustos, new CentroCustoComp().new Nome());
		this.boxCentroCusto.removeAllItems();

		for (CentroCusto b : centroCustos) {
			this.boxCentroCusto.addItem(b);
		}

		VeiculoMarca veiculoMarca = null;
		List<VeiculoMarca> veiculoMarcas = (List<VeiculoMarca>) VeiculoMarcaFac.getRegistro();
		Collections.sort(veiculoMarcas, new VeiculoMarcaComp().new Marca());
		this.boxVeiculoMarca.removeAllItems();
		this.boxVeiculoMarca.addItem(veiculoMarca);

		for (VeiculoMarca v : veiculoMarcas) {
			this.boxVeiculoMarca.addItem(v);
		}

		VeiculoModelo veiculoModelo = null;
		List<VeiculoModelo> veiculoModelos = (List<VeiculoModelo>) VeiculoModeloFac.getRegistro();
		Collections.sort(veiculoModelos, new VeiculoModeloComp().new Modelo());
		this.boxVeiculoModelo.removeAllItems();
		this.boxVeiculoModelo.addItem(veiculoModelo);

		for (VeiculoModelo v : veiculoModelos) {
			this.boxVeiculoModelo.addItem(v);
		}

		if (MainControl.getVeiculoJanCad().getVeiculoCont().getVeiculo() != null) {
			centroCusto = MainControl.getVeiculoJanCad().getVeiculoCont().getVeiculo().getCentroCusto();
			boxCentroCusto.setSelectedItem(centroCusto);

			veiculoMarca = MainControl.getVeiculoJanCad().getVeiculoCont().getVeiculo().getMarca();
			boxVeiculoMarca.setSelectedItem(veiculoMarca);

			veiculoModelo = MainControl.getVeiculoJanCad().getVeiculoCont().getVeiculo().getModelo();
			boxVeiculoModelo.setSelectedItem(veiculoModelo);
		}
	}
}
