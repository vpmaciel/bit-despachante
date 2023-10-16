package erp.pedido.placa;

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

final class PedidoPlacaControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((cliente == null) || (cliente.getDescricao() == null) || (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION)) {
				return;
			}
			try {
				PedidoPlacaFac.deletarRegistro(cliente);
				getContaJanCad().limparGui();
				cliente = new PedidoPlaca();
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

			List<PedidoPlaca> listConta = new LinkedList<>();

			try {
				listConta = new LinkedList<>(PedidoPlacaFac.pesquisarRegistro(new PedidoPlaca()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			PedidoPlacaArqCsv clienteArqCsv = new PedidoPlacaArqCsv(listConta);
			clienteArqCsv.retornarArquivo(true);
			Sis.abrirDiretorio();

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<PedidoPlaca> listConta = new LinkedList<>();

			try {

				ArquivoJson<PedidoPlaca> arquivoJson = new ArquivoJson<>(cliente, "usuario");
				arquivoJson.gravarArquivo(PedidoPlacaFac.getRegistro());				
			} catch (Exception e) {
				e.printStackTrace();
			}

			PedidoPlacaArqCsv clienteArqCsv = new PedidoPlacaArqCsv(listConta);
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
			cliente = new PedidoPlaca();
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
			List<PedidoPlaca> clientes = new LinkedList<>();

			if (cliente.getDescricao() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (clientes.add(PedidoPlacaFac.getRegistro(cliente))) {
				PedidoPlacaRel clienteRel = new PedidoPlacaRel(clientes);
				clienteRel.retornarRelatorio();
			}
		}
	}

	public class MostraFrameConta extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON1) {
				MainControl.mostrarFrame(MainControl.getPedidoPlacaJan());
			} else {
				MainControl.getPedidoPlacaJan().reiniciarGui();
			}
		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			cliente = new PedidoPlaca();
			getContaJanCad().limparGui();
			getContaPainelCad().getGuiDescricao().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			cliente = new PedidoPlaca();
			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = MainControl.getPedidoPlacaJan().getContaPainelPesq().pesquisarRegistro(cliente);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.getPedidoPlacaJan().getTabbedPane().setSelectedIndex(1);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = MainControl.getPedidoPlacaJan().getContaPainelPesq()
					.pesquisarRegistro(new PedidoPlaca());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.getPedidoPlacaJan().getTabbedPane().setSelectedIndex(1);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<PedidoPlaca> clientes = new LinkedList<>();

			try {
				clientes = new LinkedList<>(PedidoPlacaFac.pesquisarRegistro(new PedidoPlaca()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			PedidoPlacaRel clienteRel = new PedidoPlacaRel(clientes);
			clienteRel.retornarRelatorio();

		}
	}

	public class Salva implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {

				int mensagem = Msg.confirmarSalvarRegistro();
				if ((mensagem != JOptionPane.YES_OPTION) || !Entrada.validar(getContaPainelCad().getGuiDescricao(), "NOME", RegExp.NOME, true) || !Entrada.validar(getContaPainelCad().getGuiDescricao(), "DESCRIÇÃO", RegExp.NUMERO_BANCO, false)) {
					return;
				}

				if (((getContaPainelCad().getGuiDescricao().getText()) == null)
						|| (getContaPainelCad().getGuiDescricao().getText().length() == 0)) {
					getContaPainelCad().getGuiDescricao().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					PedidoPlacaFac.salvarRegistro(cliente);
					cliente = new PedidoPlaca();
					MainControl.getPedidoPlacaJan().limparGui();
					Msg.sucessoSalvarRegistro();
					getContaPainelCad().getGuiDescricao().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_DESCRICAO")) {
						Msg.avisoCampoDuplicado("NOME");
						getContaPainelCad().getGuiDescricao().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private PedidoPlaca cliente;

	PedidoPlacaControl() {
	}

	public void atualizarGui() {
		if (cliente == null) {
			return;
		}
		getContaPainelCad().getGuiDescricao().setText(cliente.getDescricao());		
	}

	public void atualizarObjeto() {
		if (cliente == null) {
			cliente = new PedidoPlaca();
		}
		cliente.setDescricao(getContaPainelCad().getGuiDescricao().getText());		
	}

	public PedidoPlaca getConta() {
		return cliente;
	}

	public PedidoPlacaJan getContaJanCad() {
		return MainControl.getPedidoPlacaJan();
	}

	public PedidoPlacaPainelCad getContaPainelCad() {
		return MainControl.getPedidoPlacaJan().getContaPainelCad();
	}

	public void setModelo(PedidoPlaca cliente) {
		this.cliente = cliente;
	}
}