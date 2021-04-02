package erp.negocio.veiculo.marca;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import erp.arquitetura.Sis;
import erp.arquitetura.gui.ConfiguracaoGui;
import erp.arquitetura.gui.Gui;

@SuppressWarnings("serial")
public final class VeiculoMarcaJanPesq extends JFrame implements Gui {

	private VeiculoMarcaPainelPesq VeiculoMarcaPainelPesq;

	public VeiculoMarcaJanPesq() {
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

	public VeiculoMarcaPainelPesq getVeiculoMarcaPainelPesq() {
		return VeiculoMarcaPainelPesq;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		VeiculoMarcaPainelPesq.iniciarControlador();
	}

	@Override
	public void iniciarFocoControlador() {

	}

	@Override
	public void iniciarGui() {
		setIconImage(erp.arquitetura.gui.Imagem.getLogoTipoImage());
		VeiculoMarcaPainelPesq = new VeiculoMarcaPainelPesq();
		setContentPane(VeiculoMarcaPainelPesq);
	}

	@Override
	public void iniciarGuiControlador() {

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
