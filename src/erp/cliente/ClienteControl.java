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
import erp.arquitetura.validacao.Entrada;
import erp.arquitetura.validacao.RegExp;
import erp.sistema.main.MainControl;

final class ClienteControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((cliente == null) || (cliente.getCpfCnpj() == null) || (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION)) {
				return;
			}
			try {
				ClienteFac.deletarRegistro(cliente);
				getContaJanCad().limparGui();
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
				getContaJanCad().setVisible(false);
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

			List<Cliente> listConta = new LinkedList<>();

			try {

				ArquivoJson<Cliente> arquivoJson = new ArquivoJson<>(cliente, "usuario");
				arquivoJson.gravarArquivo(ClienteFac.getRegistro());				
			} catch (Exception e) {
				e.printStackTrace();
			}

			ClienteArqCsv clienteArqCsv = new ClienteArqCsv(listConta);
			clienteArqCsv.retornarArquivo(true);
			Sis.abrirDiretorio();
		}
	}



	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getContaJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getContaJanCad().setVisible(false);
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
				MainControl.mostrarFrame(MainControl.getMainJan());
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
				MainControl.mostrarFrame(MainControl.getClienteJan());
			} else {
				MainControl.getClienteJan().reiniciarGui();
			}
		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			cliente = new Cliente();
			getContaJanCad().limparGui();
			getContaPainelCad().getGuiEmail().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			cliente = new Cliente();
			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = MainControl.getClienteJan().getContaPainelPesq().pesquisarRegistro(cliente);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.getClienteJan().getTabbedPane().setSelectedIndex(1);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = MainControl.getClienteJan().getContaPainelPesq()
					.pesquisarRegistro(new Cliente());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.getClienteJan().getTabbedPane().setSelectedIndex(1);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Cliente> clientes = new LinkedList<>();

			try {
				clientes = new LinkedList<>(ClienteFac.pesquisarRegistro(new Cliente()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			ClienteRel clienteRel = new ClienteRel(clientes);
			clienteRel.retornarRelatorio();

		}
	}

	public class Salva implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {

				int mensagem = Msg.confirmarSalvarRegistro();
				if ((mensagem != JOptionPane.YES_OPTION) || !Entrada.validar(getContaPainelCad().getGuiNome(), "NOME", RegExp.NOME, true)) {
					return;
				}

				if (((getContaPainelCad().getGuiEmail().getText()) == null)
						|| (getContaPainelCad().getGuiEmail().getText().length() == 0)) {
					getContaPainelCad().getGuiEmail().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					ClienteFac.salvarRegistro(cliente);
					cliente = new Cliente();
					MainControl.getClienteJan().limparGui();
					Msg.sucessoSalvarRegistro();
					getContaPainelCad().getGuiEmail().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_CPF_CNPJ")) {
						Msg.avisoCampoDuplicado("CPF | CNPJ");
						getContaPainelCad().getGuiEmail().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
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
		getContaPainelCad().getGuiEmail().setText(cliente.getEmail());		
		getContaPainelCad().getGuiCpfCnpj().setText(cliente.getCpfCnpj());
		getContaPainelCad().getGuiNome().setText(cliente.getNome());
		getContaPainelCad().getGuiTelefone().setText(cliente.getTelefone());
	}

	public void atualizarObjeto() {
		if (cliente == null) {
			cliente = new Cliente();
		}
		cliente.setCpfCnpj(getContaPainelCad().getGuiCpfCnpj().getText());	
		cliente.setEmail(getContaPainelCad().getGuiEmail().getText());
		cliente.setNome(getContaPainelCad().getGuiNome().getText());
		cliente.setTelefone(getContaPainelCad().getGuiTelefone().getText());
		
	}

	public Cliente getConta() {
		return cliente;
	}

	public ClienteJan getContaJanCad() {
		return MainControl.getClienteJan();
	}

	public ClientePainelCad getContaPainelCad() {
		return MainControl.getClienteJan().getContaPainelCad();
	}

	public void setModelo(Cliente cliente) {
		this.cliente = cliente;
	}
}