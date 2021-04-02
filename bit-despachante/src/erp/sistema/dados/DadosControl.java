package erp.sistema.dados;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import erp.arquitetura.gui.Msg;
import erp.sistema.main.MainControl;

final class DadosControl {

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
				DadosFac.deletarRegistro(contador);
				getDadosJanCad().limparGui();
				contador = new Dados();
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
				MainControl.getDadosJan().setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getDadosJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getDadosJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			contador = new Dados();
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
			List<Dados> contadors = new LinkedList<>();

			if (contador.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (contadors.add(DadosFac.getRegistro(contador))) {
				DadosRel contadorRel = new DadosRel(contadors);
				contadorRel.retornarRelatorio(true);
			}
		}
	}

	public class MostraFrameDados extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON1) {
				MainControl.mostrarFrame(MainControl.getDadosJan());
			} else {
				MainControl.getDadosJan().reiniciarGui();
			}
		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			contador = new Dados();
			getDadosJanCad().limparGui();
			getDadosPainelCad().getGuiNome().requestFocus();
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Dados> contadors = new LinkedList<>();

			try {
				contadors = new LinkedList<>(DadosFac.pesquisarRegistro(new Dados()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			DadosRel contadorRel = new DadosRel(contadors);
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
				String nome = getDadosPainelCad().getGuiNome().getText();
				if ((nome == null) || (nome.length() == 0)) {
					getDadosPainelCad().getGuiNome().requestFocus();
					Msg.avisoCampoObrigatorio("Dados");
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					DadosFac.salvarRegistro(contador);
					contador = new Dados();
					getDadosJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getDadosPainelCad().getGuiNome().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_CONTADOR_CRC")) {
						Msg.avisoCampoDuplicado("CRC");
						getDadosPainelCad().getGuiCrc().requestFocus();
					} else if (mensagem.contains("INDEX_CONTADOR_CPF")) {
						Msg.avisoCampoDuplicado("CPF");
						getDadosPainelCad().getGuiCpf().requestFocus();
					} else if (mensagem.contains("INDEX_CONTADOR_CNPJ")) {
						Msg.avisoCampoDuplicado("CNPJ");
						getDadosPainelCad().getGuiCnpj().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private Dados contador;

	public void atualizarGui() {
		if (contador == null) {
			return;
		}
		getDadosPainelCad().getGuiCnpj().setText(contador.getCnpj());
		getDadosPainelCad().getGuiNome().setText(contador.getNome());
		getDadosPainelCad().getGuiEmail().setText(contador.getEmail());
		getDadosPainelCad().getGuiFone1().setText(contador.getFone1());
	}

	public void atualizarObjeto() {
		contador.setCnpj(getDadosPainelCad().getGuiCnpj().getText());
		contador.setNome(getDadosPainelCad().getGuiNome().getText());
		contador.setEmail(getDadosPainelCad().getGuiEmail().getText());
		contador.setFone1(getDadosPainelCad().getGuiFone1().getText());

		if (getDadosPainelCad().getGuiCnpj().getText().length() == 0) {
			contador.setCnpj(null);
		}
	}

	public Dados getDados() {
		return contador;
	}

	public DadosJanCad getDadosJanCad() {
		return MainControl.getDadosJan();
	}

	public DadosPainelCad getDadosPainelCad() {
		return MainControl.getDadosJan().getDadosPainelCad();
	}

	public void setDados(Dados contador) {
		this.contador = contador;
	}
}
