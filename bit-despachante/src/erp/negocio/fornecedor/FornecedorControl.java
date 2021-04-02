package erp.negocio.fornecedor;

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

final class FornecedorControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((fornecedor == null) || (fornecedor.getId() == null)) {
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				FornecedorFac.deletarRegistro(fornecedor);
				getFornecedorJanCad().limparGui();
				fornecedor = new Fornecedor();
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
				getFornecedorJanCad().setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Fornecedor> listFornecedor = new LinkedList<>();

			try {
				listFornecedor = new LinkedList<>(FornecedorFac.pesquisarRegistro(new Fornecedor()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			FornecedorArqCsv FornecedorArqCsv = new FornecedorArqCsv(listFornecedor);
			FornecedorArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Fornecedor> listFornecedor = new LinkedList<>();

			try {

				ArquivoJson<Fornecedor> arquivoJson = new ArquivoJson<Fornecedor>(fornecedor, "Fornecedor");
				arquivoJson.gravarArquivo(FornecedorFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			FornecedorArqCsv FornecedorArqCsv = new FornecedorArqCsv(listFornecedor);
			FornecedorArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getFornecedorJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getFornecedorJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			fornecedor = new Fornecedor();
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
			List<Fornecedor> fornecedors = new LinkedList<>();

			if (fornecedor.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (fornecedors.add(FornecedorFac.getRegistro(fornecedor))) {
				FornecedorRel fornecedorRel = new FornecedorRel(fornecedors);
				fornecedorRel.retornarRelatorio(true);
			}
		}
	}

	public class MostraFrameFornecedor extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON1) {
				MainControl.mostrarFrame(getFornecedorJanCad());
			} else {
				getFornecedorJanCad().reiniciarGui();
			}
		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			fornecedor = new Fornecedor();
			getFornecedorJanCad().limparGui();
			getFornecedorPainelCad().getGuiNomeFantasia().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getFornecedorPainelPesq().pesquisarRegistro(fornecedor);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getFornecedorJanPesq());
				getFornecedorJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getFornecedorPainelPesq().pesquisarRegistro(new Fornecedor());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getFornecedorJanPesq());
				getFornecedorJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Fornecedor> fornecedors = new LinkedList<>();

			try {
				fornecedors = new LinkedList<>(FornecedorFac.pesquisarRegistro(new Fornecedor()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			FornecedorRel fornecedorRel = new FornecedorRel(fornecedors);
			fornecedorRel.retornarRelatorio(true);

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
				String nome = getFornecedorPainelCad().getGuiNomeFantasia().getText();
				if ((nome == null) || (nome.length() == 0)) {
					getFornecedorPainelCad().getGuiNomeFantasia().requestFocus();
					Msg.avisoCampoObrigatorio("NOME FANTASIA");
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					FornecedorFac.salvarRegistro(fornecedor);
					fornecedor = new Fornecedor();
					getFornecedorJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getFornecedorPainelCad().getGuiNomeFantasia().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_FORNECEDOR_CPF")) {
						Msg.avisoCampoDuplicado("CPF");
						getFornecedorPainelCad().getGuiCpf().requestFocus();
					} else if (mensagem.contains("INDEX_FORNECEDOR_CNPJ")) {
						Msg.avisoCampoDuplicado("CNPJ");
						getFornecedorPainelCad().getGuiCnpj().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private Fornecedor fornecedor;

	public void atualizarGui() {
		if (fornecedor == null) {
			return;
		}
		getFornecedorPainelCad().getGuiNomeFantasia().setText(fornecedor.getNomeFantasia());
		getFornecedorPainelCad().getGuiRazaoSocial().setText(fornecedor.getRazaoSocial());
		getFornecedorPainelCad().getGuiEmail().setText(fornecedor.getEmail());
		getFornecedorPainelCad().getGuiFax().setText(fornecedor.getFax());
		getFornecedorPainelCad().getGuiFone1().setText(fornecedor.getFone1());
		getFornecedorPainelCad().getGuiFone2().setText(fornecedor.getFone2());
		getFornecedorPainelCad().getGuiBairro().setText(fornecedor.getEnderecoBairro());
		getFornecedorPainelCad().getGuiCep().setText(fornecedor.getEnderecoCep());
		getFornecedorPainelCad().getGuiCidade().setText(fornecedor.getEnderecoCidade());
		getFornecedorPainelCad().getGuiComplemento().setText(fornecedor.getEnderecoComplemento());
		getFornecedorPainelCad().getGuiEstado().setText(fornecedor.getEnderecoEstado());
		getFornecedorPainelCad().getGuiLogradouro().setText(fornecedor.getEnderecoLogradouro());
		getFornecedorPainelCad().getGuiPais().setText(fornecedor.getEnderecoPais());
		getFornecedorPainelCad().getGuiCnpj().setText(fornecedor.getCnpj());
		getFornecedorPainelCad().getGuiCpf().setText(fornecedor.getCpf());
		getFornecedorPainelCad().getGuiTipoEmpresa().setSelectedItem(fornecedor.getTipoEmpresa());
	}

	public void atualizarObjeto() {
		fornecedor.setNomeFantasia(getFornecedorPainelCad().getGuiNomeFantasia().getText());
		fornecedor.setRazaoSocial(getFornecedorPainelCad().getGuiRazaoSocial().getText());
		fornecedor.setEmail(getFornecedorPainelCad().getGuiEmail().getText());
		fornecedor.setFax(getFornecedorPainelCad().getGuiFax().getText());
		fornecedor.setFone1(getFornecedorPainelCad().getGuiFone1().getText());
		fornecedor.setFone2(getFornecedorPainelCad().getGuiFone2().getText());
		fornecedor.setEnderecoBairro(getFornecedorPainelCad().getGuiBairro().getText());
		fornecedor.setEnderecoCep(getFornecedorPainelCad().getGuiCep().getText());
		fornecedor.setEnderecoCidade(getFornecedorPainelCad().getGuiCidade().getText());
		fornecedor.setEnderecoComplemento(getFornecedorPainelCad().getGuiComplemento().getText());
		fornecedor.setEnderecoEstado(getFornecedorPainelCad().getGuiEstado().getText());
		fornecedor.setEnderecoLogradouro(getFornecedorPainelCad().getGuiLogradouro().getText());
		fornecedor.setEnderecoPais(getFornecedorPainelCad().getGuiPais().getText());
		fornecedor.setCnpj(getFornecedorPainelCad().getGuiCnpj().getText());
		fornecedor.setCpf(getFornecedorPainelCad().getGuiCpf().getText());
		fornecedor.setTipoEmpresa((String) getFornecedorPainelCad().getGuiTipoEmpresa().getSelectedItem());

		if (getFornecedorPainelCad().getGuiCnpj().getText().length() == 0) {
			fornecedor.setCnpj(null);
		}

		if (getFornecedorPainelCad().getGuiCpf().getText().length() == 0) {
			fornecedor.setCpf(null);
		}
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public FornecedorJanCad getFornecedorJanCad() {
		return MainControl.getFornecedorJanCad();
	}

	public FornecedorJanPesq getFornecedorJanPesq() {
		return MainControl.getFornecedorJanPesq();
	}

	public FornecedorPainelCad getFornecedorPainelCad() {
		return MainControl.getFornecedorJanCad().getFornecedorPainelCad();
	}

	public FornecedorPainelPesq getFornecedorPainelPesq() {
		return MainControl.getFornecedorJanPesq().getFornecedorPainelPesq();
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
}
