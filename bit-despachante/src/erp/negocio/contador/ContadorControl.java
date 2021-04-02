package erp.negocio.contador;

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
import erp.sistema.main.MainControl;

final class ContadorControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((contador == null) || (contador.getId() == null)) {
				return;
			}
			if (Msg.confirmarExcluiRegistro() != 0) {
				return;
			}
			try {
				ContadorFac.deletarRegistro(contador);
				getContadorJanCad().limparGui();
				contador = new Contador();
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
				MainControl.getContadorJanCad().setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Contador> listContador = new LinkedList<>();

			try {
				listContador = new LinkedList<>(ContadorFac.pesquisarRegistro(new Contador()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			ContadorArqCsv ContadorArqCsv = new ContadorArqCsv(listContador);
			ContadorArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Contador> listContador = new LinkedList<>();

			try {

				ArquivoJson<Contador> arquivoJson = new ArquivoJson<Contador>(contador, "Contador");
				arquivoJson.gravarArquivo(ContadorFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			ContadorArqCsv ContadorArqCsv = new ContadorArqCsv(listContador);
			ContadorArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getContadorJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getContadorJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			contador = new Contador();
		}
	}

	public class Home implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {
				MainControl.mostrarFrame(MainControl.getMainJanCad());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class Imprime implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			List<Contador> contadors = new LinkedList<>();

			if (contador.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (contadors.add(ContadorFac.getRegistro(contador))) {
				ContadorRel contadorRel = new ContadorRel(contadors);
				contadorRel.retornarRelatorio(true);
			}
		}
	}

	public class MostraFrameContador extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON1) {
				MainControl.mostrarFrame(MainControl.getContadorJanCad());
			} else {
				MainControl.getContadorJanCad().reiniciarGui();
			}
		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			contador = new Contador();
			getContadorJanCad().limparGui();
			getContadorPainelCad().getGuiNome().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getContadorPainelPesq().pesquisarRegistro(contador);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getContadorJanPesq());
				getContadorJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getContadorPainelPesq().pesquisarRegistro(new Contador());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getContadorJanPesq());
				getContadorJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Contador> contadors = new LinkedList<>();

			try {
				contadors = new LinkedList<>(ContadorFac.pesquisarRegistro(new Contador()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			ContadorRel contadorRel = new ContadorRel(contadors);
			contadorRel.retornarRelatorio(true);

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
				String nome = getContadorPainelCad().getGuiNome().getText();
				if ((nome == null) || (nome.length() == 0)) {
					getContadorPainelCad().getGuiNome().requestFocus();
					Msg.avisoCampoObrigatorio("Contador");
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					ContadorFac.salvarRegistro(contador);
					contador = new Contador();
					getContadorJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getContadorPainelCad().getGuiNome().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_CONTADOR_CRC")) {
						Msg.avisoCampoDuplicado("CRC");
						getContadorPainelCad().getGuiCrc().requestFocus();
					} else if (mensagem.contains("INDEX_CONTADOR_CPF")) {
						Msg.avisoCampoDuplicado("CPF");
						getContadorPainelCad().getGuiCpf().requestFocus();
					} else if (mensagem.contains("INDEX_CONTADOR_CNPJ")) {
						Msg.avisoCampoDuplicado("CNPJ");
						getContadorPainelCad().getGuiCnpj().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private Contador contador;

	public void atualizarGui() {
		if (contador == null) {
			return;
		}
		getContadorPainelCad().getGuiCnpj().setText(contador.getCnpj());
		getContadorPainelCad().getGuiNome().setText(contador.getNome());
		getContadorPainelCad().getGuiCpf().setText(contador.getCpf());
		getContadorPainelCad().getGuiCrc().setText(contador.getCrc());
		getContadorPainelCad().getGuiEmail().setText(contador.getEmail());
		getContadorPainelCad().getGuiFax().setText(contador.getFax());
		getContadorPainelCad().getGuiFone1().setText(contador.getFone1());
		getContadorPainelCad().getGuiFone2().setText(contador.getFone2());
		getContadorPainelCad().getGuiSite().setText(contador.getSite());
	}

	public void atualizarObjeto() {
		contador.setCnpj(getContadorPainelCad().getGuiCnpj().getText());
		contador.setCpf(getContadorPainelCad().getGuiCpf().getText());
		contador.setCrc(getContadorPainelCad().getGuiCrc().getText());
		contador.setNome(getContadorPainelCad().getGuiNome().getText());
		contador.setEmail(getContadorPainelCad().getGuiEmail().getText());
		contador.setFax(getContadorPainelCad().getGuiFax().getText());
		contador.setFone1(getContadorPainelCad().getGuiFone1().getText());
		contador.setFone2(getContadorPainelCad().getGuiFone2().getText());
		contador.setSite(getContadorPainelCad().getGuiSite().getText());

		if (getContadorPainelCad().getGuiCnpj().getText().length() == 0) {
			contador.setCnpj(null);
		}

		if (getContadorPainelCad().getGuiCpf().getText().length() == 0) {
			contador.setCpf(null);
		}

		if (getContadorPainelCad().getGuiCrc().getText().length() == 0) {
			contador.setCrc(null);
		}
	}

	public Contador getContador() {
		return contador;
	}

	public ContadorJanCad getContadorJanCad() {
		return MainControl.getContadorJanCad();
	}

	public ContadorJanPesq getContadorJanPesq() {
		return MainControl.getContadorJanPesq();
	}

	public ContadorPainelCad getContadorPainelCad() {
		return MainControl.getContadorJanCad().getContadorPainelCad();
	}

	public ContadorPainelPesq getContadorPainelPesq() {
		return MainControl.getContadorJanPesq().getContadorPainelPesq();
	}

	public void setContador(Contador contador) {
		this.contador = contador;
	}
}
