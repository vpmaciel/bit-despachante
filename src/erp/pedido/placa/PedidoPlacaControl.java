package erp.pedido.placa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import erp.arquitetura.ArquivoJson;
import erp.arquitetura.Sis;
import erp.arquitetura.gui.Msg;
import erp.sistema.main.MainControl;

final class PedidoPlacaControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((pedidoPlaca == null) || (pedidoPlaca.getRenavam() == null)
					|| (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION)) {
				return;
			}
			try {
				PedidoPlacaFac.deletarRegistro(pedidoPlaca);
				getPedidoPlacaJanCad().limparGui();
				pedidoPlaca = new PedidoPlaca();
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
				getPedidoPlacaJanCad().setVisible(false);
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
			try {

				ArquivoJson<PedidoPlaca> arquivoJson = new ArquivoJson<>(pedidoPlaca, "pedido-de-placa");
				arquivoJson.gravarArquivo(PedidoPlacaFac.getRegistro());
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
			getPedidoPlacaJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getPedidoPlacaJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			pedidoPlaca = new PedidoPlaca();
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

			if (pedidoPlaca.getRenavam() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (clientes.add(PedidoPlacaFac.getRegistro(pedidoPlaca))) {
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
			pedidoPlaca = new PedidoPlaca();
			getPedidoPlacaJanCad().limparGui();
			getPedidoPlacaPainelCad().getGuiQuantidade().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			pedidoPlaca = new PedidoPlaca();
			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = MainControl.getPedidoPlacaJan().getContaPainelPesq().pesquisarRegistro(pedidoPlaca);
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

			List<PedidoPlaca> pedidoPlacaList = new LinkedList<>();

			try {
				pedidoPlacaList = new LinkedList<>(PedidoPlacaFac.pesquisarRegistro(new PedidoPlaca()));
				if (pedidoPlacaList.size() == 0) {
					JOptionPane.showMessageDialog(null, "Sem registros para gerar relatório !", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}

			PedidoPlacaRel clienteRel = new PedidoPlacaRel(pedidoPlacaList);
			clienteRel.retornarRelatorio();

		}
	}

	public class Salva implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {

				int mensagem = Msg.confirmarSalvarRegistro();

				if (((getPedidoPlacaPainelCad().getGuiQuantidade().getText()) == null)
						|| (getPedidoPlacaPainelCad().getGuiQuantidade().getText().length() == 0)) {
					getPedidoPlacaPainelCad().getGuiQuantidade().requestFocus();
					Msg.avisoCampoObrigatorio("QUANTIDADE");
					return;
				}

				if (((getPedidoPlacaPainelCad().getGuiPlaca().getText()) == null)
						|| (getPedidoPlacaPainelCad().getGuiPlaca().getText().length() == 0)) {
					getPedidoPlacaPainelCad().getGuiPlaca().requestFocus();
					Msg.avisoCampoObrigatorio("PLACA DO VEÍCULO");
					return;
				}

				if (((getPedidoPlacaPainelCad().getGuiCorPlaca().getText()) == null)
						|| (getPedidoPlacaPainelCad().getGuiCorPlaca().getText().length() == 0)) {
					getPedidoPlacaPainelCad().getGuiCorPlaca().requestFocus();
					Msg.avisoCampoObrigatorio("COR DA PALCA");
					return;
				}

				if (((getPedidoPlacaPainelCad().getGuiTipoPlaca().getText()) == null)
						|| (getPedidoPlacaPainelCad().getGuiTipoPlaca().getText().length() == 0)) {
					getPedidoPlacaPainelCad().getGuiTipoPlaca().requestFocus();
					Msg.avisoCampoObrigatorio("TIPO DE PLACA");
					return;
				}

				if (((getPedidoPlacaPainelCad().getGuiCpfCnpjProprietario().getText()) == null)
						|| (getPedidoPlacaPainelCad().getGuiCpfCnpjProprietario().getText().length() == 0)) {
					getPedidoPlacaPainelCad().getGuiCpfCnpjProprietario().requestFocus();
					Msg.avisoCampoObrigatorio("CPF | CNPJ DO PROPRIETÁRIO");
					return;
				}

				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					PedidoPlacaFac.salvarRegistro(pedidoPlaca);
					pedidoPlaca = new PedidoPlaca();
					MainControl.getPedidoPlacaJan().limparGui();
					Msg.sucessoSalvarRegistro();
					getPedidoPlacaPainelCad().getGuiQuantidade().requestFocus();
				}

			} catch (Exception e) {
				getPedidoPlacaPainelCad().getGuiQuantidade().requestFocus();
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private PedidoPlaca pedidoPlaca;

	PedidoPlacaControl() {
	}

	public void atualizarGui() {
		if (pedidoPlaca == null) {
			return;
		}
		getPedidoPlacaPainelCad().getGuiCorPlaca().setText(pedidoPlaca.getCorPlaca());
		getPedidoPlacaPainelCad().getGuiCpfCnpjProprietario().setText(pedidoPlaca.getCpfCnpjProprietario());
		getPedidoPlacaPainelCad().getGuiPlaca().setText(pedidoPlaca.getPlaca());
		getPedidoPlacaPainelCad().getGuiQuantidade().setText(String.valueOf(pedidoPlaca.getQuantidade()));
		getPedidoPlacaPainelCad().getGuiRenavam().setText(pedidoPlaca.getRenavam());
		getPedidoPlacaPainelCad().getGuiTipoPlaca().setText(pedidoPlaca.getTipoPlaca());
	}

	public void atualizarObjeto() {
		if (pedidoPlaca == null) {
			pedidoPlaca = new PedidoPlaca();
		}
		pedidoPlaca.setData(new Date());
		pedidoPlaca.setCorPlaca(getPedidoPlacaPainelCad().getGuiCorPlaca().getText());
		pedidoPlaca.setCpfCnpjProprietario(getPedidoPlacaPainelCad().getGuiCpfCnpjProprietario().getText());
		pedidoPlaca.setPlaca(getPedidoPlacaPainelCad().getGuiPlaca().getText());
		try {
			pedidoPlaca.setQuantidade(Integer.parseInt(getPedidoPlacaPainelCad().getGuiQuantidade().getText()));
		} catch (Exception e) {
			pedidoPlaca.setQuantidade(null);
		}

		pedidoPlaca.setRenavam(getPedidoPlacaPainelCad().getGuiRenavam().getText());
		pedidoPlaca.setTipoPlaca(getPedidoPlacaPainelCad().getGuiTipoPlaca().getText());
	}

	public PedidoPlaca getPedidoPlaca() {
		return pedidoPlaca;
	}

	public PedidoPlacaJan getPedidoPlacaJanCad() {
		return MainControl.getPedidoPlacaJan();
	}

	public PedidoPlacaPainelCad getPedidoPlacaPainelCad() {
		return MainControl.getPedidoPlacaJan().getContaPainelCad();
	}

	public void setModelo(PedidoPlaca cliente) {
		this.pedidoPlaca = cliente;
	}
}