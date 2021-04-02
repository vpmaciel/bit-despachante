package erp.negocio.cliente;

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
import erp.arquitetura.gui.Msg;
import erp.negocio.empresa.Empresa;
import erp.sistema.main.MainControl;

final class ClienteControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((cliente == null) || (cliente.getId() == null)) {
				Msg.erroExcluiRegistro();
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				ClienteFac.deletarRegistro(cliente);
				getClienteJanCad().limparGui();
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
			getClienteJanCad().setVisible(false);
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Cliente> listCliente = new LinkedList<>();

			try {
				listCliente = new LinkedList<>(ClienteFac.pesquisarRegistro(new Cliente()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			ClienteArqCsv ClienteArqCsv = new ClienteArqCsv(listCliente);
			ClienteArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Cliente> listCliente = new LinkedList<>();

			try {

				ArquivoJson<Cliente> arquivoJson = new ArquivoJson<Cliente>(cliente, "Cliente");
				arquivoJson.gravarArquivo(ClienteFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			ClienteArqCsv ClienteArqCsv = new ClienteArqCsv(listCliente);
			ClienteArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getClienteJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getClienteJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			cliente = new Cliente();
			getClientePainelCad().getGuiNome().requestFocus();
		}
	}

	public class Home implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			MainControl.mostrarFrame(MainControl.getMainJanCad());
		}
	}

	public class Imprime implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			List<Cliente> clientes = new LinkedList<>();

			if (cliente.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (clientes.add(ClienteFac.getRegistro(cliente))) {
				ClienteRel clienteRel = new ClienteRel(clientes);
				clienteRel.retornarRelatorio(true);
			}
		}
	}

	public class MostraFrame extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent event) {
			if (event.getSource() == getClientePainelCad().getLabelEmpresa()) {
				MainControl.mostrarFrame(MainControl.getEmpresaJanCad());
			}
		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			cliente = new Cliente();
			getClienteJanCad().limparGui();
			getClientePainelCad().getGuiNome().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getClientePainelPesq().pesquisarRegistro(cliente);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getClienteJanPesq());
				getClienteJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getClientePainelPesq().pesquisarRegistro(new Cliente());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getClienteJanPesq());
				getClienteJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
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
			clienteRel.retornarRelatorio(true);

		}
	}

	public class Salva implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {
				int mensagem = Msg.confirmarSalvarRegistro();

				if (mensagem != JOptionPane.YES_OPTION) {
					return;
				}

				if (((getClientePainelCad().getGuiNome().getText()) == null)
						|| (getClientePainelCad().getGuiNome().getText().length() == 0)) {
					getClientePainelCad().getGuiNome().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					ClienteFac.salvarRegistro(cliente);
					cliente = new Cliente();
					getClienteJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getClientePainelCad().getGuiNome().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_CLIENTE_CPF")) {
						Msg.avisoCampoDuplicado("CPF");
						getClientePainelCad().getGuiCpf().requestFocus();
					} else if (mensagem.contains("INDEX_CLIENTE_CNPJ")) {
						Msg.avisoCampoDuplicado("CNPJ");
						getClientePainelCad().getGuiCnpj().requestFocus();
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

	public void atualizarGui() {
		if (cliente == null) {
			return;
		}
		getClientePainelCad().getGuiNome().setText(cliente.getNome());
		getClientePainelCad().getGuiSexo().setSelectedItem(cliente.getSexo());
		getClientePainelCad().getGuiEstadoCivil().setSelectedItem(cliente.getEstadoCivil());
		getClientePainelCad().getGuiEmail().setText(cliente.getEmail());
		getClientePainelCad().getGuiFone1().setText(cliente.getFone1());
		getClientePainelCad().getGuiFone2().setText(cliente.getFone2());
		getClientePainelCad().getGuiCargo().setText(cliente.getCargo());
		getClientePainelCad().getGuiSalario().setText(cliente.getSalario());
		getClientePainelCad().getGuiBairro().setText(cliente.getEnderecoBairro());
		getClientePainelCad().getGuiCep().setText(cliente.getEnderecoCep());
		getClientePainelCad().getGuiCidade().setText(cliente.getEnderecoCidade());
		getClientePainelCad().getGuiComplemento().setText(cliente.getEnderecoComplemento());
		getClientePainelCad().getGuiEstado().setText(cliente.getEnderecoEstado());
		getClientePainelCad().getGuiLogradouro().setText(cliente.getEnderecoLogradouro());
		getClientePainelCad().getGuiPais().setText(cliente.getEnderecoPais());
		getClientePainelCad().getGuiCnpj().setText(cliente.getCnpj());
		getClientePainelCad().getGuiCpf().setText(cliente.getCpf());
		getClientePainelCad().getGuiRGNumero().setText(cliente.getRgNumero());
		getClientePainelCad().getGuiRGOrgaoEmisssor().setText(cliente.getRgOrgaoEmissor());
		getClientePainelCad().getGuiDataNascimento().setText(cliente.getDataNascimento());
		getClientePainelCad().getGuiEmpresa().setSelectedItem(cliente.getEmpresa());
		getClientePainelCad().getGuiStatus().setSelectedItem(cliente.getStatus());
		getClientePainelCad().getGuiDeficiencia().setSelectedItem(cliente.getDeficiencia());
		getClientePainelCad().getGuiEscolaridade().setSelectedItem(cliente.getEscolaridade());
	}

	public void atualizarObjeto() {
		cliente.setNome(getClientePainelCad().getGuiNome().getText());
		cliente.setSexo((String) getClientePainelCad().getGuiSexo().getSelectedItem());
		cliente.setEstadoCivil((String) getClientePainelCad().getGuiEstadoCivil().getSelectedItem());
		cliente.setEmail(getClientePainelCad().getGuiEmail().getText());
		cliente.setFone1(getClientePainelCad().getGuiFone1().getText());
		cliente.setFone2(getClientePainelCad().getGuiFone2().getText());
		cliente.setCargo(getClientePainelCad().getGuiCargo().getText());
		cliente.setEmpresa((Empresa) getClientePainelCad().getGuiEmpresa().getSelectedItem());
		cliente.setSalario(getClientePainelCad().getGuiSalario().getText());
		cliente.setEnderecoBairro(getClientePainelCad().getGuiBairro().getText());
		cliente.setEnderecoCep(getClientePainelCad().getGuiCep().getText());
		cliente.setEnderecoCidade(getClientePainelCad().getGuiCidade().getText());
		cliente.setEnderecoComplemento(getClientePainelCad().getGuiComplemento().getText());
		cliente.setEnderecoEstado(getClientePainelCad().getGuiEstado().getText());
		cliente.setEnderecoLogradouro(getClientePainelCad().getGuiLogradouro().getText());
		cliente.setEnderecoPais(getClientePainelCad().getGuiPais().getText());
		cliente.setCnpj(getClientePainelCad().getGuiCnpj().getText());
		cliente.setCpf(getClientePainelCad().getGuiCpf().getText());
		cliente.setRgNumero(getClientePainelCad().getGuiRGNumero().getText());
		cliente.setRgOrgaoEmissor(getClientePainelCad().getGuiRGOrgaoEmisssor().getText());
		cliente.setDataNascimento(getClientePainelCad().getGuiDataNascimento().getText());
		cliente.setStatus((String) getClientePainelCad().getGuiStatus().getSelectedItem());
		cliente.setDeficiencia((String) getClientePainelCad().getGuiDeficiencia().getSelectedItem());
		cliente.setEscolaridade((String) getClientePainelCad().getGuiEscolaridade().getSelectedItem());
	}

	public Cliente getCliente() {
		return cliente;
	}

	public ClienteJanCad getClienteJanCad() {
		return MainControl.getClienteJanCad();
	}

	public ClienteJanPesq getClienteJanPesq() {
		return MainControl.getClienteJanPesq();
	}

	public ClientePainelCad getClientePainelCad() {
		return MainControl.getClienteJanCad().getClientePainelCad();
	}

	public ClientePainelPesq getClientePainelPesq() {
		return MainControl.getClienteJanPesq().getClientePainelPesq();
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
