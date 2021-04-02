package erp.negocio.banco;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import erp.arquitetura.ArquivoJson;
import erp.arquitetura.gui.Msg;
import erp.sistema.main.MainControl;
import erp.sistema.main.MainJan;

final class BancoControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((banco == null) || (banco.getId() == null)) {
				Msg.erroExcluiRegistro();
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				BancoFac.deletarRegistro(banco);
				getBancoJanCad().limparGui();
				banco = new Banco();
				Msg.sucessoExcluiRegistro();
			} catch (Exception e) {
				Msg.erroExcluiRegistro();
			}
		}
	}

	public class FechaJanela implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			getBancoJanCad().setVisible(false);
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Banco> listBanco = new LinkedList<>();

			try {
				listBanco = new LinkedList<>(BancoFac.pesquisarRegistro(new Banco()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			BancoArqCsv bancoArqCsv = new BancoArqCsv(listBanco);
			bancoArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Banco> listBanco = new LinkedList<>();

			try {

				ArquivoJson<Banco> arquivoJson = new ArquivoJson<Banco>(banco, "banco");
				arquivoJson.gravarArquivo(BancoFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			BancoArqCsv bancoArqCsv = new BancoArqCsv(listBanco);
			bancoArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getBancoJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getBancoJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			banco = new Banco();
			getBancoPainelCad().getGuiNome().requestFocus();
		}
	}

	public class Home implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			MainControl.mostrarFrame(MainJan.getFrameMain());
		}
	}

	public class Imprime implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			List<Banco> listBanco = new LinkedList<>();

			if (banco.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (listBanco.add(BancoFac.getRegistro(banco))) {
				BancoRel bancoRel = new BancoRel(listBanco);
				bancoRel.retornarRelatorio(true);
			}

		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			banco = new Banco();
			getBancoJanCad().limparGui();
			getBancoPainelCad().getGuiNome().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getBancoPainelPesq().pesquisarRegistro(banco);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getBancoJanPesq());
				getBancoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getBancoPainelPesq().pesquisarRegistro(new Banco());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getBancoJanPesq());
				getBancoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Banco> listBanco = new LinkedList<>();

			try {
				listBanco = new LinkedList<>(BancoFac.pesquisarRegistro(new Banco()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			BancoRel bancoRel = new BancoRel(listBanco);
			bancoRel.retornarRelatorio(true);

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

				if (((getBancoPainelCad().getGuiNome().getText()) == null)
						|| (getBancoPainelCad().getGuiNome().getText().length() == 0)) {
					getBancoPainelCad().getGuiNome().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}

				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					BancoFac.salvarBanco(banco);
					banco = new Banco();
					getBancoJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getBancoPainelCad().getGuiNome().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_BANCO_NOME")) {
						Msg.avisoCampoDuplicado("NOME");
						getBancoPainelCad().getGuiNome().requestFocus();
					} else if (mensagem.contains("INDEX_BANCO_CODIGO")) {
						Msg.avisoCampoDuplicado("CÓDIGO");
						getBancoPainelCad().getGuiCodigo().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private Banco banco;

	public BancoControl() {

	}

	public void atualizarGui() {
		if (banco == null) {
			return;
		}
		getBancoPainelCad().getGuiNome().setText(banco.getNome());
		getBancoPainelCad().getGuiCodigo().setText(banco.getCodigo());
	}

	public void atualizarObjeto() {
		banco.setCodigo(getBancoPainelCad().getGuiCodigo().getText());
		banco.setNome(getBancoPainelCad().getGuiNome().getText());
	}

	public Banco getBanco() {
		return banco;
	}

	public BancoJanCad getBancoJanCad() {
		return MainControl.getBancoJanCad();
	}

	public BancoJanPesq getBancoJanPesq() {
		return MainControl.getBancoJanPesq();
	}

	public BancoPainelCad getBancoPainelCad() {
		return MainControl.getBancoJanCad().getBancoPainelCad();
	}

	public BancoPainelPesq getBancoPainelPesq() {
		return MainControl.getBancoJanPesq().getBancoPainelPesq();
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}
}