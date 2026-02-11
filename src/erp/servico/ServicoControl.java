package erp.servico;

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
import erp.arquitetura.Numero;
import erp.arquitetura.Sis;
import erp.arquitetura.gui.Msg;
import erp.sistema.main.MainController;

final class ServicoControl {

    public class Exclui implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    if ((servico == null) || (servico.getDescricao() == null)
		    || (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION)) {
		return;
	    }
	    try {
		ServicoFac.deletarRegistro(servico);
		getServicoJanCad().limparGui();
		servico = new Servico();
		Msg.sucessoExcluiRegistro();
	    } catch (Exception e) {
		Msg.erroExcluiRegistro();
	    }
	}
    }

    public class FechaCaixa implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    if (Msg.confirmarFecharCaixa() != JOptionPane.YES_OPTION) {
		return;
	    }

	    Float totalCaixa = 0.0f;

	    try {

		List<Servico> listConta = new LinkedList<>();
		listConta = new LinkedList<>(ServicoFac.pesquisarRegistro(new Servico()));

		if (listConta.size() == 0) {
		    JOptionPane.showMessageDialog(null, "Caixa sem lançamentos !", "Aviso",
			    JOptionPane.WARNING_MESSAGE);
		    return;
		}

		ServicoArqCsv servicoArqCsv = new ServicoArqCsv(listConta);

		for (Servico servico_item : listConta) {
		    totalCaixa += servico_item.getValor();
		    ServicoFac.deletarRegistro(servico_item);
		}

		getServicoJanCad().limparGui();
		Msg.sucessoFecharCaixa();
		JOptionPane.showMessageDialog(null, "Caixa Fechado: R$ " + Numero.FloatToString(totalCaixa));
		servicoArqCsv.retornarArquivo(true);
		Sis.abrirDiretorio();
	    } catch (Exception e) {
		Msg.erroExcluiRegistro();
	    }
	}
    }

    public class FechaJanela implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    try {
		getServicoJanCad().setVisible(false);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    public class FormatoCsv implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

	    List<Servico> listConta = new LinkedList<>();

	    try {
		listConta = new LinkedList<>(ServicoFac.pesquisarRegistro(new Servico()));
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	    ServicoArqCsv servicoArqCsv = new ServicoArqCsv(listConta);
	    servicoArqCsv.retornarArquivo(true);
	    Sis.abrirDiretorio();

	}
    }

    public class FormatoJson implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    try {

		ArquivoJson<Servico> arquivoJson = new ArquivoJson<>(servico, "servico");
		arquivoJson.gravarArquivo(ServicoFac.getRegistro());
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
	    getServicoJanCad().reiniciarGui();
	}

	@Override
	public void windowClosing(WindowEvent e) {
	    getServicoJanCad().setVisible(false);
	}

	@Override
	public void windowOpened(WindowEvent e) {
	    servico = new Servico();
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
	    List<Servico> servicos = new LinkedList<>();

	    if (servico.getDescricao() == null) {
		Msg.avisoImprimiRegistroNaoCadastrado();
		return;
	    }
	    if (servicos.add(ServicoFac.getRegistro(servico))) {
		ServicoRel servicoRel = new ServicoRel(servicos);
		servicoRel.retornarRelatorio();
	    }
	}
    }

    public class MostraFrameConta extends MouseAdapter {

	@Override
	public void mouseClicked(MouseEvent event) {
	    if (event.getButton() == MouseEvent.BUTTON1) {
		MainController.mostrarFrame(MainController.getServicoJan());
	    } else {
		MainController.getServicoJan().reiniciarGui();
	    }
	}
    }

    public class Novo implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    servico = new Servico();
	    getServicoJanCad().limparGui();
	    getServicoPainelCad().getGuiPlaca().requestFocus();
	}
    }

    public class Pesquisa implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    servico = new Servico();
	    atualizarObjeto(false);
	    long totalPesquisaRegistro = 0;
	    totalPesquisaRegistro = MainController.getServicoJan().getServicoPainelPesq().pesquisarRegistro(servico);
	    Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

	    if (totalPesquisaRegistro > 0) {
		MainController.getServicoJan().getTabbedPane().setSelectedIndex(1);
	    }
	}
    }

    public class Registro implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    long totalPesquisaRegistro = 0;
	    totalPesquisaRegistro = MainController.getServicoJan().getServicoPainelPesq()
		    .pesquisarRegistro(new Servico());
	    Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

	    if (totalPesquisaRegistro > 0) {
		MainController.getServicoJan().getTabbedPane().setSelectedIndex(1);
	    }
	}
    }

    public class Relatorio implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

	    List<Servico> servicoList = new LinkedList<>();

	    try {
		servicoList = new LinkedList<>(ServicoFac.pesquisarRegistro(new Servico()));
		if (servicoList.size() == 0) {
		    JOptionPane.showMessageDialog(null, "Sem registros para gerar relatório !", "Aviso",
			    JOptionPane.WARNING_MESSAGE);
		    return;
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	    ServicoRel servicoRel = new ServicoRel(servicoList);
	    servicoRel.retornarRelatorio();

	}
    }

    public class Salva implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    try {

		int mensagem = Msg.confirmarSalvarRegistro();

		if (((getServicoPainelCad().getGuiPlaca().getText()) == null)
			|| (getServicoPainelCad().getGuiPlaca().getText().length() == 0)) {
		    getServicoPainelCad().getGuiPlaca().requestFocus();
		    Msg.avisoCampoObrigatorio("Placa do Veículo");
		    return;
		}

		if (((getServicoPainelCad().getGuiDescricao().getText()) == null)
			|| (getServicoPainelCad().getGuiDescricao().getText().length() == 0)) {
		    getServicoPainelCad().getGuiDescricao().requestFocus();
		    Msg.avisoCampoObrigatorio("Descrição do Serviço");
		    return;
		}

		if (((getServicoPainelCad().getGuiValor().getText()) == null)
			|| (getServicoPainelCad().getGuiValor().getText().length() == 0)) {
		    getServicoPainelCad().getGuiValor().requestFocus();
		    Msg.avisoCampoObrigatorio("Valor do Serviço");
		    return;
		}

		if (mensagem == JOptionPane.YES_OPTION) {
		    boolean salva;

		    salva = atualizarObjeto(true);

		    if (!salva) {
			return;
		    }

		    ServicoFac.salvarRegistro(servico);
		    servico = new Servico();
		    MainController.getServicoJan().limparGui();
		    Msg.sucessoSalvarRegistro();
		    getServicoPainelCad().getGuiPlaca().requestFocus();
		}
	    } catch (Exception e) {
		getServicoPainelCad().getGuiPlaca().requestFocus();
		e.printStackTrace();
		Msg.erroSalvarRegistro();
	    }
	}
    }

    private Servico servico;

    ServicoControl() {
    }

    public void atualizarGui() {
	if (servico == null) {
	    return;
	}
	getServicoPainelCad().getGuiCpfCnpjCliente().setText(servico.getCpfCnpjCliente());
	getServicoPainelCad().getGuiData().setText(servico.getDataFormatada());
	getServicoPainelCad().getGuiDescricao().setText(servico.getDescricao());
	getServicoPainelCad().getGuiNomeCliente().setText(servico.getNomeCliente());
	getServicoPainelCad().getGuiPlaca().setText(servico.getPlaca());
	getServicoPainelCad().getGuiTelefoneCliente().setText(servico.getTelefoneCliente());
	getServicoPainelCad().getGuiValor().setText(Numero.FloatToString(servico.getValor()));
    }

    public boolean atualizarObjeto(boolean pesquisa) {
	if (servico == null) {
	    servico = new Servico();
	}
	servico.setCpfCnpjCliente(getServicoPainelCad().getGuiCpfCnpjCliente().getText());
	servico.setDescricao(getServicoPainelCad().getGuiDescricao().getText());
	servico.setNomeCliente(getServicoPainelCad().getGuiNomeCliente().getText());
	servico.setPlaca(getServicoPainelCad().getGuiPlaca().getText());
	servico.setTelefoneCliente(getServicoPainelCad().getGuiTelefoneCliente().getText());
	servico.setValor(Numero.stringToFloat(getServicoPainelCad().getGuiValor().getText()));

	return true;
    }

    public Servico getConta() {
	return servico;
    }

    public ServicoJan getServicoJanCad() {
	return MainController.getServicoJan();
    }

    public ServicoPainelCad getServicoPainelCad() {
	return MainController.getServicoJan().getServicoPainelCad();
    }

    public void setServico(Servico servico) {
	this.servico = servico;
    }
}