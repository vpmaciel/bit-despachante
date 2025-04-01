package erp.veiculo;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import erp.arquitetura.ArquivoJson;
import erp.arquitetura.Sis;
import erp.arquitetura.gui.Msg;
import erp.sistema.main.MainController;

final class VeiculoControl {

    public class Exclui implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    if ((veiculo == null) || (veiculo.getId() == null)
		    || (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION)) {
		return;
	    }
	    try {
		VeiculoFac.deletarRegistro(veiculo);
		getVeiculoJanCad().limparGui();
		veiculo = new Veiculo();
		Msg.sucessoExcluiRegistro();
	    } catch (Exception e) {
		Msg.erroExcluiRegistro();
	    }
	}
    }

    public class FechaJanela implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    try {
		getVeiculoJanCad().setVisible(false);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    public class FormatoCsv implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

	    List<Veiculo> listVeiculo = new LinkedList<>();

	    try {
		listVeiculo = new LinkedList<>(VeiculoFac.pesquisarRegistro(new Veiculo()));
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	    VeiculoArqCsv veiculoArqCsv = new VeiculoArqCsv(listVeiculo);
	    veiculoArqCsv.retornarArquivo(true);
	    Sis.abrirDiretorio();

	}
    }

    public class FormatoJson implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

	    try {
		ArquivoJson<Veiculo> arquivoJson = new ArquivoJson<>(veiculo, "veiculo");
		arquivoJson.gravarArquivo(VeiculoFac.getRegistro());
		arquivoJson.retornarArquivo(true);
		Sis.abrirDiretorio();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    public class Frame extends WindowAdapter {

	@Override
	public void windowActivated(WindowEvent e) {
	    getVeiculoJanCad().reiniciarGui();
	}

	@Override
	public void windowClosing(WindowEvent e) {
	    getVeiculoJanCad().setVisible(false);
	}

	@Override
	public void windowOpened(WindowEvent e) {
	    veiculo = new Veiculo();
	}
    }

    public class Home implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    try {
		MainController.mostrarFrame(MainController.getMainJan());
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    public class Imprime implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    List<Veiculo> veiculos = new LinkedList<>();

	    if (veiculo.getMarca() == null) {
		Msg.avisoImprimiRegistroNaoCadastrado();
		return;
	    }
	    if (veiculos.add(VeiculoFac.getRegistro(veiculo))) {
		VeiculoRel veiculoRel = new VeiculoRel(veiculos);
		veiculoRel.retornarRelatorio();
	    }
	}
    }

    public class MostraFrameVeiculo extends MouseAdapter {

	@Override
	public void mouseClicked(MouseEvent event) {
	    if (event.getButton() == MouseEvent.BUTTON1) {
		MainController.mostrarFrame(MainController.getVeiculoJan());
	    } else {
		MainController.getVeiculoJan().reiniciarGui();
	    }
	}
    }

    public class Novo implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    veiculo = new Veiculo();
	    getVeiculoJanCad().limparGui();
	    getVeiculoPainelCad().getGuiPlaca().requestFocus();
	}
    }

    public class Pesquisa implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    veiculo = new Veiculo();
	    atualizarObjeto(false);
	    long totalPesquisaRegistro = 0;
	    totalPesquisaRegistro = MainController.getVeiculoJan().getVeiculoPainelPesq().pesquisarRegistro(veiculo);
	    Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

	    if (totalPesquisaRegistro > 0) {
		MainController.getVeiculoJan().getTabbedPane().setSelectedIndex(1);
	    }
	}
    }

    public class Registro implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    long totalPesquisaRegistro = 0;
	    totalPesquisaRegistro = MainController.getVeiculoJan().getVeiculoPainelPesq().pesquisarRegistro(new Veiculo());
	    Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

	    if (totalPesquisaRegistro > 0) {
		MainController.getVeiculoJan().getTabbedPane().setSelectedIndex(1);
	    }
	}
    }

    public class Relatorio implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

	    List<Veiculo> veiculoList = new LinkedList<>();

	    try {
		veiculoList = new LinkedList<>(VeiculoFac.pesquisarRegistro(new Veiculo()));
		if (veiculoList.size() == 0) {
		    JOptionPane.showMessageDialog(null, "Sem registros para gerar relatório !", "Aviso",
			    JOptionPane.WARNING_MESSAGE);
		    return;
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	    VeiculoRel veiculoRel = new VeiculoRel(veiculoList);
	    veiculoRel.retornarRelatorio();

	}
    }

    public class Salva implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    try {

		int mensagem = Msg.confirmarSalvarRegistro();

		if (((getVeiculoPainelCad().getGuiPlaca().getText()) == null)
			|| (getVeiculoPainelCad().getGuiPlaca().getText().length() == 0)) {
		    getVeiculoPainelCad().getGuiPlaca().requestFocus();
		    Msg.avisoCampoObrigatorio("PLACA DO VEÍCULO");
		    return;
		}
		if (mensagem == JOptionPane.YES_OPTION) {
		   
		    long totalPesquisaRegistro = 0;
		    Veiculo veiculoPesquisa = new Veiculo();
		    veiculoPesquisa.setPlaca(getVeiculoPainelCad().getGuiPlaca().getText());
		    totalPesquisaRegistro = MainController.getVeiculoJan().getVeiculoPainelPesq().pesquisarRegistro(veiculoPesquisa);
		    
		    if (totalPesquisaRegistro > 0 && veiculo.getId() == null) {
			Msg.avisoCampoDuplicado("CPF | CNPJ");
			return;
		    }
		   
		    boolean salva;
		    
		    salva = atualizarObjeto(true);
		    
		    if (!salva) {
			return;
		    }
		    VeiculoFac.salvarRegistro(veiculo);
		    veiculo = new Veiculo();
		    MainController.getVeiculoJan().limparGui();
		    Msg.sucessoSalvarRegistro();
		    getVeiculoPainelCad().getGuiPlaca().requestFocus();
		}
	    } catch (Exception e) {
		Throwable throwable = e.getCause().getCause();
		String mensagem = throwable.toString();
		if (mensagem.contains("ConstraintViolationException")) {
		    if (mensagem.contains("INDEX_VEICULO_PLACA")) {
			Msg.avisoCampoDuplicado("PLACA DO VEÍCULO");
			getVeiculoPainelCad().getGuiPlaca().requestFocus();
		    }
		}
		e.printStackTrace();
		Msg.erroSalvarRegistro();
	    }
	}
    }

    private Veiculo veiculo;

    VeiculoControl() {
    }

    public void atualizarGui() {
	if (veiculo == null) {
	    return;
	}
	getVeiculoPainelCad().getGuiCpfCnpjProprietario().setText(veiculo.getCpfCnpjProprietario());
	getVeiculoPainelCad().getGuiMarca().setText(veiculo.getMarca());
	getVeiculoPainelCad().getGuiModelo().setText(veiculo.getModelo());
	getVeiculoPainelCad().getGuiNomeProprietario().setText(veiculo.getNomeProprietario());
	getVeiculoPainelCad().getGuiPlaca().setText(veiculo.getPlaca());

    }

    public boolean atualizarObjeto(boolean pesquisa) {
	if (veiculo == null) {
	    veiculo = new Veiculo();
	}
	veiculo.setCpfCnpjProprietario(getVeiculoPainelCad().getGuiCpfCnpjProprietario().getText());
	veiculo.setMarca(getVeiculoPainelCad().getGuiMarca().getText());
	veiculo.setModelo(getVeiculoPainelCad().getGuiModelo().getText());
	veiculo.setNomeProprietario(getVeiculoPainelCad().getGuiNomeProprietario().getText());
	veiculo.setPlaca(getVeiculoPainelCad().getGuiPlaca().getText());
	
	return true;
    }

    public Veiculo getVeiculo() {
	return veiculo;
    }

    public VeiculoJan getVeiculoJanCad() {
	return MainController.getVeiculoJan();
    }

    public VeiculoPainelCad getVeiculoPainelCad() {
	return MainController.getVeiculoJan().getVeiculoPainelCad();
    }

    public void setVeiculo(Veiculo veiculo) {
	this.veiculo = veiculo;
    }
}