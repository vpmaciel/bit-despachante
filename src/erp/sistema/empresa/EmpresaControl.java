package erp.sistema.empresa;

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

final class EmpresaControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((empresa == null) || (empresa.getId() == null) || (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION)) {
				return;
			}
			try {
				EmpresaFac.deletarRegistro(empresa);
				getEmpresaJanCad().limparGui();
				empresa = new Empresa();
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
				getEmpresaJanCad().setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Empresa> listEmpresa = new LinkedList<>();

			try {
				listEmpresa = new LinkedList<>(EmpresaFac.pesquisarRegistro(new Empresa()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			EmpresaArqCsv empresaArqCsv = new EmpresaArqCsv(listEmpresa);
			empresaArqCsv.retornarArquivo(true);
			Sis.abrirDiretorio();

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Empresa> listEmpresa = new LinkedList<>();

			try {

				ArquivoJson<Empresa> arquivoJson = new ArquivoJson<>(empresa, "usuario");
				arquivoJson.gravarArquivo(EmpresaFac.getRegistro());				
			} catch (Exception e) {
				e.printStackTrace();
			}

			EmpresaArqCsv empresaArqCsv = new EmpresaArqCsv(listEmpresa);
			empresaArqCsv.retornarArquivo(true);
			Sis.abrirDiretorio();
		}
	}



	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getEmpresaJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getEmpresaJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			empresa = new Empresa();
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
			List<Empresa> empresas = new LinkedList<>();

			if (empresa.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (empresas.add(EmpresaFac.getRegistro(empresa))) {
				EmpresaRel empresaRel = new EmpresaRel(empresas);
				empresaRel.retornarRelatorio();
			}
		}
	}

	public class MostraFrameEmpresa extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON1) {
				MainControl.mostrarFrame(MainControl.getEmpresaJan());
			} else {
				MainControl.getEmpresaJan().reiniciarGui();
			}
		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			empresa = new Empresa();
			getEmpresaJanCad().limparGui();
			getEmpresaPainelCad().getGuiNome().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = MainControl.getEmpresaJan().getEmpresaPainelPesq().pesquisarRegistro(empresa);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.getEmpresaJan().getTabbedPane().setSelectedIndex(1);
				// getEmpresaJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = MainControl.getEmpresaJan().getEmpresaPainelPesq()
					.pesquisarRegistro(new Empresa());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.getEmpresaJan().getTabbedPane().setSelectedIndex(1);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Empresa> empresas = new LinkedList<>();

			try {
				empresas = new LinkedList<>(EmpresaFac.pesquisarRegistro(new Empresa()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			EmpresaRel empresaRel = new EmpresaRel(empresas);
			empresaRel.retornarRelatorio();

		}
	}

	public class Salva implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {

				int mensagem = Msg.confirmarSalvarRegistro();
				if ((mensagem != JOptionPane.YES_OPTION) || !Entrada.validar(getEmpresaPainelCad().getGuiNome(), "NOME", RegExp.NOME, true) || !Entrada.validar(getEmpresaPainelCad().getGuiSenha(), "CÓDIGO", RegExp.NUMERO_BANCO, false)) {
					return;
				}

				if (((getEmpresaPainelCad().getGuiNome().getText()) == null)
						|| (getEmpresaPainelCad().getGuiNome().getText().length() == 0)) {
					getEmpresaPainelCad().getGuiNome().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					EmpresaFac.salvarRegistro(empresa);
					empresa = new Empresa();
					MainControl.getEmpresaJan().limparGui();
					Msg.sucessoSalvarRegistro();
					getEmpresaPainelCad().getGuiNome().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_USUARIO_NOME")) {
						Msg.avisoCampoDuplicado("NOME");
						getEmpresaPainelCad().getGuiNome().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private Empresa empresa;

	EmpresaControl() {
	}

	public void atualizarGui() {
		if (empresa == null) {
			return;
		}
		getEmpresaPainelCad().getGuiNome().setText(empresa.getNome());
		getEmpresaPainelCad().getGuiSenha().setText(empresa.getSenha());
	}

	public void atualizarObjeto() {

		empresa.setSenha(getEmpresaPainelCad().getGuiSenha().getText());
		empresa.setNome(getEmpresaPainelCad().getGuiNome().getText());
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public EmpresaJanCad getEmpresaJanCad() {
		return MainControl.getEmpresaJan();
	}

	public EmpresaPainelCad getEmpresaPainelCad() {
		return MainControl.getEmpresaJan().getEmpresaPainelCad();
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}