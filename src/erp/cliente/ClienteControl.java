package erp.cliente;

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
import erp.arquitetura.validacao.Telefone;
import erp.sistema.main.MainController;

final class ClienteControl {

    public class Exclui implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    if ((cliente == null) || (cliente.getCpfCnpj() == null)
		    || (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION)) {
		return;
	    }
	    try {
		ClienteFac.deletarRegistro(cliente);
		getCienteJanCad().limparGui();
		cliente = new Cliente();
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
		getCienteJanCad().setVisible(false);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    public class FormatoCsv implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

	    List<Cliente> listConta = new LinkedList<>();

	    try {
		listConta = new LinkedList<>(ClienteFac.pesquisarRegistro(new Cliente()));
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	    ClienteArqCsv clienteArqCsv = new ClienteArqCsv(listConta);
	    clienteArqCsv.retornarArquivo(true);
	    Sis.abrirDiretorio();

	}
    }

    public class FormatoJson implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    try {

		ArquivoJson<Cliente> arquivoJson = new ArquivoJson<>(cliente, "cliente");
		arquivoJson.gravarArquivo(ClienteFac.getRegistro());
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
	    getCienteJanCad().reiniciarGui();
	}

	@Override
	public void windowClosing(WindowEvent e) {
	    getCienteJanCad().setVisible(false);
	}

	@Override
	public void windowOpened(WindowEvent e) {
	    cliente = new Cliente();
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
	    List<Cliente> clientes = new LinkedList<>();

	    if (cliente.getCpfCnpj() == null) {
		Msg.avisoImprimiRegistroNaoCadastrado();
		return;
	    }
	    if (clientes.add(ClienteFac.getRegistro(cliente))) {
		ClienteRel clienteRel = new ClienteRel(clientes);
		clienteRel.retornarRelatorio();
	    }
	}
    }

    public class MostraFrameConta extends MouseAdapter {

	@Override
	public void mouseClicked(MouseEvent event) {
	    if (event.getButton() == MouseEvent.BUTTON1) {
		MainController.mostrarFrame(MainController.getClienteJan());
	    } else {
		MainController.getClienteJan().reiniciarGui();
	    }
	}
    }

    public class Novo implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    cliente = new Cliente();
	    getCienteJanCad().limparGui();
	    getClientePainelCad().getGuiNome().requestFocus();
	}
    }

    public class Pesquisa implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    cliente = new Cliente();
	    atualizarObjeto(false);

	    long totalPesquisaRegistro = 0;

	    totalPesquisaRegistro = MainController.getClienteJan().getClientePainelPesq().pesquisarRegistro(cliente);

	    if (totalPesquisaRegistro > 0) {
		MainController.getClienteJan().getTabbedPane().setSelectedIndex(1);
	    }
	}
    }

    public class Registro implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

	    long totalPesquisaRegistro = 0;

	    totalPesquisaRegistro = MainController.getClienteJan().getClientePainelPesq()
		    .pesquisarRegistro(new Cliente());
	    Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

	    if (totalPesquisaRegistro > 0) {
		MainController.getClienteJan().getTabbedPane().setSelectedIndex(1);
	    }
	}
    }

    public class Relatorio implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

	    List<Cliente> clienteList = new LinkedList<>();

	    try {
		clienteList = new LinkedList<>(ClienteFac.pesquisarRegistro(new Cliente()));
		if (clienteList.size() == 0) {
		    JOptionPane.showMessageDialog(null, "Sem registros para gerar relatório !", "Aviso",
			    JOptionPane.WARNING_MESSAGE);
		    return;
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	    ClienteRel clienteRel = new ClienteRel(clienteList);
	    clienteRel.retornarRelatorio();

	}
    }

    public class Salva implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    try {

		int mensagem = Msg.confirmarSalvarRegistro();

		if (((getClientePainelCad().getGuiNome().getText()) == null)
			|| (getClientePainelCad().getGuiNome().getText().length() == 0)) {
		    getClientePainelCad().getGuiNome().requestFocus();
		    Msg.avisoCampoObrigatorio("Nome");
		    return;
		}

		if (((getClientePainelCad().getGuiCpfCnpj().getText()) == null)
			|| (getClientePainelCad().getGuiCpfCnpj().getText().length() == 0)) {
		    getClientePainelCad().getGuiCpfCnpj().requestFocus();
		    Msg.avisoCampoObrigatorio("CPF | CNPJ");
		    return;
		}

		if (mensagem == JOptionPane.YES_OPTION) {

		    long totalPesquisaRegistro = 0;
		    Cliente clientePesquisa = new Cliente();
		    clientePesquisa.setCpfCnpj(getClientePainelCad().getGuiCpfCnpj().getText());
		    totalPesquisaRegistro = MainController.getClienteJan().getClientePainelPesq()
			    .pesquisarRegistro(clientePesquisa);

		    if (totalPesquisaRegistro > 0 && cliente.getId() == null) {
			Msg.avisoCampoDuplicado("CPF | CNPJ");
			return;
		    }

		    boolean salva;

		    salva = atualizarObjeto(true);

		    if (!salva) {
			return;
		    }

		    ClienteFac.salvarRegistro(cliente);
		    cliente = new Cliente();
		    MainController.getClienteJan().limparGui();
		    Msg.sucessoSalvarRegistro();
		    getClientePainelCad().getGuiNome().requestFocus();
		}

	    } catch (Exception e) {
		Throwable throwable = e.getCause().getCause();
		String mensagem = throwable.toString();
		if (mensagem.contains("ConstraintViolationException")) {
		    if (mensagem.contains("INDEX_CLIENTE_CPF_CNPJ")) {
			Msg.avisoCampoDuplicado("CPF | CNPJ");
			getClientePainelCad().getGuiNome().requestFocus();
		    }
		}
		e.printStackTrace();
		Msg.erroSalvarRegistro();
	    }
	}
    }

    private Cliente cliente;

    ClienteControl() {
    }

    public void atualizarGui() {
	if (cliente == null) {
	    return;
	}
	getClientePainelCad().getGuiEmail().setText(cliente.getEmail());
	getClientePainelCad().getGuiCpfCnpj().setText(cliente.getCpfCnpj());
	getClientePainelCad().getGuiNome().setText(cliente.getNome());
	getClientePainelCad().getGuiTelefone().setText(cliente.getTelefone());
    }

    public boolean atualizarObjeto(boolean pesquisa) {

	if (cliente == null) {
	    cliente = new Cliente();
	}
	cliente.setCpfCnpj(getClientePainelCad().getGuiCpfCnpj().getText());
	cliente.setEmail(getClientePainelCad().getGuiEmail().getText());
	cliente.setNome(getClientePainelCad().getGuiNome().getText());

	if (Telefone.validarFormato(getClientePainelCad().getGuiTelefone().getText())) {
	    cliente.setTelefone(getClientePainelCad().getGuiTelefone().getText());
	} else {
	    if (pesquisa) {
		Msg.avisoCampoInvalido("TELEFONE", "00-000000000");
		return false;
	    }
	}

	return true;
    }

    public ClienteJan getCienteJanCad() {
	return MainController.getClienteJan();
    }

    public ClientePainelCad getClientePainelCad() {
	return MainController.getClienteJan().getClientePainelCad();
    }

    public Cliente getConta() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }
}