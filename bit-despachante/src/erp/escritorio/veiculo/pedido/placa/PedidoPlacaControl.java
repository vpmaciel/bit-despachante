package erp.escritorio.veiculo.pedido.placa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import erp.arquitetura.ArquivoJson;
import erp.arquitetura.gui.Msg;
import erp.arquitetura.validacao.Entrada;
import erp.arquitetura.validacao.RegExp;
import erp.sistema.main.MainControl;

final class PedidoPlacaControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((pedidoPlaca == null) || (pedidoPlaca.getId() == null)) {
				Msg.erroExcluiRegistro();
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
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
			getPedidoPlacaJanCad().setVisible(false);
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<PedidoPlaca> listPedidoPlaca = new LinkedList<>();

			try {
				listPedidoPlaca = new LinkedList<>(PedidoPlacaFac.pesquisarRegistro(new PedidoPlaca()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			PedidoPlacaArqCsv PedidoPlacaArqCsv = new PedidoPlacaArqCsv(listPedidoPlaca);
			PedidoPlacaArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<PedidoPlaca> listPedidoPlaca = new LinkedList<>();

			try {

				ArquivoJson<PedidoPlaca> arquivoJson = new ArquivoJson<PedidoPlaca>(pedidoPlaca, "PedidoPlaca");
				arquivoJson.gravarArquivo(PedidoPlacaFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			PedidoPlacaArqCsv PedidoPlacaArqCsv = new PedidoPlacaArqCsv(listPedidoPlaca);
			PedidoPlacaArqCsv.retornarArquivo(true);

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
			getPedidoPlacaPainelCad().getGuiNome().requestFocus();
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
			List<PedidoPlaca> pedidoPlacas = new LinkedList<>();

			if (pedidoPlaca.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (pedidoPlacas.add(PedidoPlacaFac.getRegistro(pedidoPlaca))) {
				PedidoPlacaRel pedidoPlacaRel = new PedidoPlacaRel(pedidoPlacas);
				pedidoPlacaRel.retornarRelatorio(true);
			}

		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			pedidoPlaca = new PedidoPlaca();
			getPedidoPlacaJanCad().limparGui();
			getPedidoPlacaPainelCad().getGuiNome().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getPedidoPlacaPainelPesq().pesquisarRegistro(pedidoPlaca);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getPedidoPlacaJanPesq());
				getPedidoPlacaJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getPedidoPlacaPainelPesq().pesquisarRegistro(new PedidoPlaca());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getPedidoPlacaJanPesq());
				getPedidoPlacaJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<PedidoPlaca> pedidoPlacas = new LinkedList<>();

			try {
				pedidoPlacas = new LinkedList<>(PedidoPlacaFac.pesquisarRegistro(new PedidoPlaca()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			PedidoPlacaRel pedidoPlacaRel = new PedidoPlacaRel(pedidoPlacas);
			pedidoPlacaRel.retornarRelatorio(true);

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
				if (((getPedidoPlacaPainelCad().getGuiNome().getText()) == null)
						|| (getPedidoPlacaPainelCad().getGuiNome().getText().length() == 0)) {
					getPedidoPlacaPainelCad().getGuiNome().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}

				if (!Entrada.validar(getPedidoPlacaPainelCad().getGuiNome(), "NOME", RegExp.NOME, true)) {
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					PedidoPlacaFac.salvarRegistro(pedidoPlaca);
					pedidoPlaca = new PedidoPlaca();
					getPedidoPlacaJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getPedidoPlacaPainelCad().getGuiNome().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_CENTRO_CUSTO_NOME")) {
						Msg.avisoCampoDuplicado("NOME");
						getPedidoPlacaPainelCad().getGuiNome().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private PedidoPlaca pedidoPlaca;

	public PedidoPlacaControl() {

	}

	public void atualizarGui() {
		if (pedidoPlaca == null) {
			return;
		}
		getPedidoPlacaPainelCad().getGuiCidade().setText(pedidoPlaca.getCidade());
		getPedidoPlacaPainelCad().getGuiCor().setText(pedidoPlaca.getCor());
		getPedidoPlacaPainelCad().getGuiEstado().setText(pedidoPlaca.getEstado());
		getPedidoPlacaPainelCad().getGuiFazerPlaca().setSelectedItem(pedidoPlaca.getFazerPlaca());
		getPedidoPlacaPainelCad().getGuiFazerTarjeta().setSelectedItem(pedidoPlaca.getFazerTarjeta());
		getPedidoPlacaPainelCad().getGuiNome().setText(pedidoPlaca.getNome());
		getPedidoPlacaPainelCad().getGuiPlaca().setText(pedidoPlaca.getPlaca());
		getPedidoPlacaPainelCad().getGuiRenavam().setText(pedidoPlaca.getRenavam());
	}

	public void atualizarObjeto() {
		pedidoPlaca.setCidade(getPedidoPlacaPainelCad().getGuiCidade().getText());
		pedidoPlaca.setCor(getPedidoPlacaPainelCad().getGuiCor().getText());
		pedidoPlaca.setEstado(getPedidoPlacaPainelCad().getGuiEstado().getText());
		pedidoPlaca.setFazerPlaca((String) getPedidoPlacaPainelCad().getGuiFazerPlaca().getSelectedItem());
		pedidoPlaca.setFazerTarjeta((String) getPedidoPlacaPainelCad().getGuiFazerTarjeta().getSelectedItem());
		pedidoPlaca.setNome(getPedidoPlacaPainelCad().getGuiNome().getText());
		pedidoPlaca.setPlaca(getPedidoPlacaPainelCad().getGuiPlaca().getText());
		pedidoPlaca.setRenavam(getPedidoPlacaPainelCad().getGuiRenavam().getText());

		if (getPedidoPlacaPainelCad().getGuiNome().getText().length() == 0) {
			pedidoPlaca.setNome(null);
		}
	}

	public PedidoPlaca getPedidoPlaca() {
		return pedidoPlaca;
	}

	public PedidoPlacaJanCad getPedidoPlacaJanCad() {
		return MainControl.getPedidoPlacaJanCad();
	}

	public PedidoPlacaJanPesq getPedidoPlacaJanPesq() {
		return MainControl.getPedidoPlacaJanPesq();
	}

	public PedidoPlacaPainelCad getPedidoPlacaPainelCad() {
		return MainControl.getPedidoPlacaJanCad().getPedidoPlacaPainelCad();
	}

	public PedidoPlacaPainelPesq getPedidoPlacaPainelPesq() {
		return MainControl.getPedidoPlacaJanPesq().getPedidoPlacaPainelPesq();
	}

	public void setPedidoPlaca(PedidoPlaca pedidoPlaca) {
		this.pedidoPlaca = pedidoPlaca;
	}
}
