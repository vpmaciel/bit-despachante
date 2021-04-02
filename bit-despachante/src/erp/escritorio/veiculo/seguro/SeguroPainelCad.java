package erp.escritorio.veiculo.seguro;

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
import erp.negocio.seguradora.Seguradora;
import erp.negocio.seguradora.SeguradoraComp;
import erp.negocio.seguradora.SeguradoraFac;

@SuppressWarnings("serial")
public final class SeguroPainelCad extends JPanel implements Gui {

	private JComboBox<Seguradora> boxSeguradora;
	private ConfiguracaoGui configuracaoGui;
	private JTextField fieldCorretor;
	private JTextField fieldDataFimVigencia;
	private JTextField fieldDataInicioVigencia;
	private JTextField fieldNome;
	private SeguroControl seguroControl;

	private ToolBar toolBar;

	public SeguroPainelCad() {
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

	public JTextField getGuiCorretor() {
		return fieldCorretor;
	}

	public JTextField getGuiDataFimVigencia() {
		return fieldDataFimVigencia;
	}

	public JTextField getGuiDataInicioVigencia() {
		return fieldDataInicioVigencia;
	}

	public JTextField getGuiNome() {
		return fieldNome;
	}

	public JComboBox<Seguradora> getGuiSeguradora() {
		return boxSeguradora;
	}

	public SeguroControl getSeguroCont() {
		return seguroControl;
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

		add(new JLabel("NOME DO SEGURADO"));

		fieldNome = new JTextField();
		fieldNome.setDocument(new EntradaMaiuscula(50));
		add(fieldNome);

		add(new JLabel("DATA DE INÍCIO DA VIGÊNCIA"));

		fieldDataInicioVigencia = new JTextField();
		fieldDataInicioVigencia.setDocument(new EntradaMaiuscula(50));
		add(fieldDataInicioVigencia);

		add(new JLabel("DATA DE FIM DA VIGÊNCIA"));

		fieldDataFimVigencia = new JTextField();
		fieldDataFimVigencia.setDocument(new EntradaMaiuscula(50));
		add(fieldDataFimVigencia);

		add(new JLabel("CORRETOR"));

		fieldCorretor = new JTextField();
		fieldCorretor.setDocument(new EntradaMaiuscula(50));
		add(fieldCorretor);

		add(new JLabel("SEGURADORA"));

		boxSeguradora = new JComboBox<Seguradora>();
		List<Seguradora> produtoMarcaList = (List<Seguradora>) SeguradoraFac.getRegistro();
		Collections.sort(produtoMarcaList, new SeguradoraComp().new Nome());

		for (Seguradora produtoMarca : produtoMarcaList) {
			boxSeguradora.addItem(produtoMarca);
		}
		add(boxSeguradora);

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

	public void setGuiSeguradora(JComboBox<Seguradora> boxSeguradora) {
		this.boxSeguradora = boxSeguradora;
	}
}
