package erp.negocio.empresa;

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

final class EmpresaControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((empresa == null) || (empresa.getId() == null)) {
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
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

			EmpresaArqCsv EmpresaArqCsv = new EmpresaArqCsv(listEmpresa);
			EmpresaArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Empresa> listEmpresa = new LinkedList<>();

			try {

				ArquivoJson<Empresa> arquivoJson = new ArquivoJson<Empresa>(empresa, "Empresa");
				arquivoJson.gravarArquivo(EmpresaFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			EmpresaArqCsv EmpresaArqCsv = new EmpresaArqCsv(listEmpresa);
			EmpresaArqCsv.retornarArquivo(true);

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
				MainControl.mostrarFrame(MainControl.getMainJanCad());
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
				empresaRel.retornarRelatorio(true);
			}
		}
	}

	public class MostraFrameEmpresa extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON1) {
				MainControl.mostrarFrame(getEmpresaJanCad());
			} else {
				getEmpresaJanCad().reiniciarGui();
			}
		}
	}

	public class NovoFrame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			empresa = new Empresa();
			getEmpresaJanCad().limparGui();
			getEmpresaPainelCad().getGuiNomeFantasia().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getEmpresaPainelPesq().pesquisarRegistro(empresa);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getEmpresaJanPesq());
				getEmpresaJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getEmpresaPainelPesq().pesquisarRegistro(new Empresa());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getEmpresaJanPesq());
				getEmpresaJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
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
			empresaRel.retornarRelatorio(true);

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

				String nomeFantasia = getEmpresaPainelCad().getGuiNomeFantasia().getText();

				if ((nomeFantasia == null) || (nomeFantasia.length() == 0)) {
					getEmpresaPainelCad().getGuiNomeFantasia().requestFocus();
					Msg.avisoCampoObrigatorio("NomeFantasia");
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					EmpresaFac.salvarRegistro(empresa);
					empresa = new Empresa();
					getEmpresaJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getEmpresaPainelCad().getGuiNomeFantasia().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_EMPRESA_CNPJ")) {
						Msg.avisoCampoDuplicado("CNPJ");
						getEmpresaPainelCad().getGuiCnpj().requestFocus();
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

	public void atualizarGui() {
		if (empresa == null) {
			return;
		}
		getEmpresaPainelCad().getGuiNomeFantasia().setText(empresa.getNomeFantasia());
		getEmpresaPainelCad().getGuiRazaoSocial().setText(empresa.getRazaoSocial());
		getEmpresaPainelCad().getGuiEmail().setText(empresa.getEmail());
		getEmpresaPainelCad().getGuiFax().setText(empresa.getFax());
		getEmpresaPainelCad().getGuiFone1().setText(empresa.getFone1());
		getEmpresaPainelCad().getGuiFone2().setText(empresa.getFone2());
		getEmpresaPainelCad().getGuiBairro().setText(empresa.getEnderecoBairro());
		getEmpresaPainelCad().getGuiCep().setText(empresa.getEnderecoCep());
		getEmpresaPainelCad().getGuiCidade().setText(empresa.getEnderecoCidade());
		getEmpresaPainelCad().getGuiComplemento().setText(empresa.getEnderecoComplemento());
		getEmpresaPainelCad().getGuiEstado().setText(empresa.getEnderecoEstado());
		getEmpresaPainelCad().getGuiLogradouro().setText(empresa.getEnderecoLogradouro());
		getEmpresaPainelCad().getGuiPais().setText(empresa.getEnderecoPais());
		getEmpresaPainelCad().getGuiCnpj().setText(empresa.getCnpj());
		getEmpresaPainelCad().getGuiEmpresa().setSelectedItem(empresa.getTipoEmpresa());
	}

	public void atualizarObjeto() {
		empresa.setNomeFantasia(getEmpresaPainelCad().getGuiNomeFantasia().getText());
		empresa.setRazaoSocial(getEmpresaPainelCad().getGuiRazaoSocial().getText());
		empresa.setEmail(getEmpresaPainelCad().getGuiEmail().getText());
		empresa.setFax(getEmpresaPainelCad().getGuiFax().getText());
		empresa.setFone1(getEmpresaPainelCad().getGuiFone1().getText());
		empresa.setFone2(getEmpresaPainelCad().getGuiFone2().getText());
		empresa.setEnderecoBairro(getEmpresaPainelCad().getGuiBairro().getText());
		empresa.setEnderecoCep(getEmpresaPainelCad().getGuiCep().getText());
		empresa.setEnderecoCidade(getEmpresaPainelCad().getGuiCidade().getText());
		empresa.setEnderecoComplemento(getEmpresaPainelCad().getGuiComplemento().getText());
		empresa.setEnderecoEstado(getEmpresaPainelCad().getGuiEstado().getText());
		empresa.setEnderecoLogradouro(getEmpresaPainelCad().getGuiLogradouro().getText());
		empresa.setEnderecoPais(getEmpresaPainelCad().getGuiPais().getText());
		empresa.setCnpj(getEmpresaPainelCad().getGuiCnpj().getText());
		empresa.setTipoEmpresa((String) getEmpresaPainelCad().getGuiEmpresa().getSelectedItem());

		if (getEmpresaPainelCad().getGuiCnpj().getText().length() == 0) {
			empresa.setCnpj(null);
		}
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public EmpresaJanCad getEmpresaJanCad() {
		return MainControl.getEmpresaJanCad();
	}

	public EmpresaJanPesq getEmpresaJanPesq() {
		return MainControl.getEmpresaJanPesq();
	}

	public EmpresaPainelCad getEmpresaPainelCad() {
		return MainControl.getEmpresaJanCad().getEmpresaPainelCad();
	}

	public EmpresaPainelPesq getEmpresaPainelPesq() {
		return MainControl.getEmpresaJanPesq().getEmpresaPainelPesq();
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}
