package erp.financeiro.vendas.servico;

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
import erp.negocio.cliente.Cliente;
import erp.negocio.servico.Servico;
import erp.sistema.main.MainControl;

final class VendaServicoControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((vendaServico == null) || (vendaServico.getId() == null)) {
				Msg.erroExcluiRegistro();
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				VendaServicoFac.deletarRegistro(vendaServico);
				getVendaServicoJanCad().limparGui();
				vendaServico = new VendaServico();
				Msg.sucessoExcluiRegistro();
			} catch (Exception e) {
				Msg.erroExcluiRegistro();
			}
		}
	}

	public class FechaJanela implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			getVendaServicoJanCad().setVisible(false);
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<VendaServico> listVendaServico = new LinkedList<>();

			try {
				listVendaServico = new LinkedList<>(VendaServicoFac.pesquisarRegistro(new VendaServico()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			VendaServicoArqCsv VendaServicoArqCsv = new VendaServicoArqCsv(listVendaServico);
			VendaServicoArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<VendaServico> listVendaServico = new LinkedList<>();

			try {

				ArquivoJson<VendaServico> arquivoJson = new ArquivoJson<VendaServico>(vendaServico, "VendaServico");
				arquivoJson.gravarArquivo(VendaServicoFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			VendaServicoArqCsv VendaServicoArqCsv = new VendaServicoArqCsv(listVendaServico);
			VendaServicoArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getVendaServicoJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getVendaServicoJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			vendaServico = new VendaServico();
			getVendaServicoPainelCad().getGuiEntrada().requestFocus();
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
			List<VendaServico> vendaServicos = new LinkedList<>();

			if (vendaServico.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (vendaServicos.add(VendaServicoFac.getRegistro(vendaServico))) {
				VendaServicoRel vendaServicoRel = new VendaServicoRel(vendaServicos);
				vendaServicoRel.retornarRelatorio(true);
			}

		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			vendaServico = new VendaServico();
			getVendaServicoJanCad().limparGui();
			getVendaServicoPainelCad().getGuiEntrada().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getVendaServicoPainelPesq().pesquisarRegistro(vendaServico);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getVendaServicoJanPesq());
				getVendaServicoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getVendaServicoPainelPesq().pesquisarRegistro(new VendaServico());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getVendaServicoJanPesq());
				getVendaServicoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<VendaServico> vendaServicos = new LinkedList<>();

			try {
				vendaServicos = new LinkedList<>(VendaServicoFac.pesquisarRegistro(new VendaServico()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			VendaServicoRel vendaServicoRel = new VendaServicoRel(vendaServicos);
			vendaServicoRel.retornarRelatorio(true);

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
				if (((getVendaServicoPainelCad().getGuiEntrada().getText()) == null)
						|| (getVendaServicoPainelCad().getGuiEntrada().getText().length() == 0)) {
					getVendaServicoPainelCad().getGuiEntrada().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}

				if (!Entrada.validar(getVendaServicoPainelCad().getGuiEntrada(), "NOME", RegExp.NOME, true)) {
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					VendaServicoFac.salvarRegistro(vendaServico);
					vendaServico = new VendaServico();
					getVendaServicoJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getVendaServicoPainelCad().getGuiEntrada().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_CENTRO_CUSTO_NOME")) {
						Msg.avisoCampoDuplicado("NOME");
						getVendaServicoPainelCad().getGuiEntrada().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private VendaServico vendaServico;

	public VendaServicoControl() {

	}

	public void atualizarGui() {
		if (vendaServico == null) {
			return;
		}
		getVendaServicoPainelCad().getGuiCliente().setSelectedItem(vendaServico.getCliente());
		getVendaServicoPainelCad().getGuiData().setText(vendaServico.getData());
		getVendaServicoPainelCad().getGuiEntrada().setValue(vendaServico.getEntrada());
		getVendaServicoPainelCad().getGuiEstorno().setValue(vendaServico.getEstorno());
		getVendaServicoPainelCad().getGuiHora().setText(vendaServico.getHora());
		getVendaServicoPainelCad().getGuiServico().setSelectedItem(vendaServico.getServico());
		getVendaServicoPainelCad().getGuiQtde().setValue(vendaServico.getQtde());
		getVendaServicoPainelCad().getGuiUsuario().setText(vendaServico.getUsuario().getNome());
	}

	public void atualizarObjeto() {
		vendaServico.setCliente((Cliente) getVendaServicoPainelCad().getGuiCliente().getSelectedItem());
		vendaServico.setServico((Servico) getVendaServicoPainelCad().getGuiServico().getSelectedItem());
		getVendaServicoPainelCad().getGuiQtde().setValue(vendaServico.getQtde());
		getVendaServicoPainelCad().getGuiUsuario().setText(vendaServico.getUsuario().getNome());
		getVendaServicoPainelCad().getGuiData().setText(vendaServico.getData());
		getVendaServicoPainelCad().getGuiEntrada().setValue(vendaServico.getEntrada());
		getVendaServicoPainelCad().getGuiEstorno().setValue(vendaServico.getEstorno());
		getVendaServicoPainelCad().getGuiHora().setText(vendaServico.getUsuario().getNome());

		if (getVendaServicoPainelCad().getGuiEntrada().getText().length() == 0) {
			vendaServico.setServico(null);
		}
	}

	public VendaServico getVendaServico() {
		return vendaServico;
	}

	public VendaServicoJanCad getVendaServicoJanCad() {
		return MainControl.getVendaServicoJanCad();
	}

	public VendaServicoJanPesq getVendaServicoJanPesq() {
		return MainControl.getVendaServicoJanPesq();
	}

	public VendaServicoPainelCad getVendaServicoPainelCad() {
		return MainControl.getVendaServicoJanCad().getVendaServicoPainelCad();
	}

	public VendaServicoPainelPesq getVendaServicoPainelPesq() {
		return MainControl.getVendaServicoJanPesq().getVendaServicoPainelPesq();
	}

	public void setVendaServico(VendaServico vendaServico) {
		this.vendaServico = vendaServico;
	}
}
