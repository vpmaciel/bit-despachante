package erp.negocio.veiculo;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import erp.arquitetura.Sis;
import erp.arquitetura.gui.ConfiguracaoGui;
import erp.arquitetura.gui.Gui;

@SuppressWarnings("serial")
public final class VeiculoJanPesq extends JFrame implements Gui {

	private VeiculoPainelPesq VeiculoPainelPesq;

	public VeiculoJanPesq() {
		iniciarLayout();
		iniciarGui();
		iniciarControlador();
	}

	@Override
	public void atualizarTable() {

	}

	@Override
	public ConfiguracaoGui getConfiguracaoGui() {
		return null;
	}

	public VeiculoPainelPesq getVeiculoPainelPesq() {
		return VeiculoPainelPesq;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		VeiculoPainelPesq.iniciarControlador();
	}

	@Override
	public void iniciarFocoControlador() {

	}

	@Override
	public void iniciarGui() {
		setIconImage(erp.arquitetura.gui.Imagem.getLogoTipoImage());
		VeiculoPainelPesq = new VeiculoPainelPesq();
		setContentPane(VeiculoPainelPesq);
	}

	@Override
	public void iniciarGuiControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		VeiculoPainelPesq.iniciarControlador();
	}

	@Override
	public void iniciarLayout() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setMinimumSize(Sis.getTamanhoJanela());
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
