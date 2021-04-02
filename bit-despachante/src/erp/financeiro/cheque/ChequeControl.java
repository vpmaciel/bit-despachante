package erp.financeiro.cheque;

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

final class ChequeControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((cheque == null) || (cheque.getId() == null)) {
				Msg.erroExcluiRegistro();
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				ChequeFac.deletarRegistro(cheque);
				getChequeJanCad().limparGui();
				cheque = new Cheque();
				Msg.sucessoExcluiRegistro();
			} catch (Exception e) {
				Msg.erroExcluiRegistro();
			}
		}
	}

	public class FechaJanela implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			getChequeJanCad().setVisible(false);
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Cheque> listCheque = new LinkedList<>();

			try {
				listCheque = new LinkedList<>(ChequeFac.pesquisarRegistro(new Cheque()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			ChequeArqCsv ChequeArqCsv = new ChequeArqCsv(listCheque);
			ChequeArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Cheque> listCheque = new LinkedList<>();

			try {

				ArquivoJson<Cheque> arquivoJson = new ArquivoJson<Cheque>(cheque, "Cheque");
				arquivoJson.gravarArquivo(ChequeFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			ChequeArqCsv ChequeArqCsv = new ChequeArqCsv(listCheque);
			ChequeArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getChequeJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getChequeJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			cheque = new Cheque();
			getChequePainelCad().getGuiNomeFantasia().requestFocus();
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
			List<Cheque> cheques = new LinkedList<>();

			if (cheque.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (cheques.add(ChequeFac.getRegistro(cheque))) {
				ChequeRel chequeRel = new ChequeRel(cheques);
				chequeRel.retornarRelatorio(true);
			}

		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			cheque = new Cheque();
			getChequeJanCad().limparGui();
			getChequePainelCad().getGuiNomeFantasia().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getChequePainelPesq().pesquisarRegistro(cheque);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getChequeJanPesq());
				getChequeJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getChequePainelPesq().pesquisarRegistro(new Cheque());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getChequeJanPesq());
				getChequeJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Cheque> cheques = new LinkedList<>();

			try {
				cheques = new LinkedList<>(ChequeFac.pesquisarRegistro(new Cheque()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			ChequeRel chequeRel = new ChequeRel(cheques);
			chequeRel.retornarRelatorio(true);

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
				if (((getChequePainelCad().getGuiNomeFantasia().getText()) == null)
						|| (getChequePainelCad().getGuiNomeFantasia().getText().length() == 0)) {
					getChequePainelCad().getGuiNomeFantasia().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}

				if (!Entrada.validar(getChequePainelCad().getGuiNomeFantasia(), "NOME", RegExp.NOME, true)) {
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					ChequeFac.salvarRegistro(cheque);
					cheque = new Cheque();
					getChequeJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getChequePainelCad().getGuiNomeFantasia().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_CENTRO_CUSTO_NOME")) {
						Msg.avisoCampoDuplicado("NOME");
						getChequePainelCad().getGuiNomeFantasia().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private Cheque cheque;

	public ChequeControl() {

	}

	public void atualizarGui() {
		if (cheque == null) {
			return;
		}
		getChequePainelCad().getGuiNomeFantasia().setText(cheque.getNome());
	}

	public void atualizarObjeto() {
		cheque.setNome(getChequePainelCad().getGuiNomeFantasia().getText());

		if (getChequePainelCad().getGuiNomeFantasia().getText().length() == 0) {
			cheque.setNome(null);
		}
	}

	public Cheque getCheque() {
		return cheque;
	}

	public ChequeJanCad getChequeJanCad() {
		return MainControl.getChequeJanCad();
	}

	public ChequeJanPesq getChequeJanPesq() {
		return MainControl.getChequeJanPesq();
	}

	public ChequePainelCad getChequePainelCad() {
		return MainControl.getChequeJanCad().getChequePainelCad();
	}

	public ChequePainelPesq getChequePainelPesq() {
		return MainControl.getChequeJanPesq().getChequePainelPesq();
	}

	public void setCheque(Cheque cheque) {
		this.cheque = cheque;
	}
}
