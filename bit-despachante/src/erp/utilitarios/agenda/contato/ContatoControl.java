package erp.utilitarios.agenda.contato;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import erp.arquitetura.ArquivoJson;
import erp.arquitetura.gui.Msg;
import erp.negocio.empresa.Empresa;
import erp.negocio.empresa.EmpresaComp;
import erp.negocio.empresa.EmpresaFac;
import erp.sistema.main.MainControl;

final class ContatoControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((contato == null) || (contato.getId() == null)) {
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				ContatoFac.deletarRegistro(contato);
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
				getContatoJanCad().setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Contato> listContato = new LinkedList<>();

			try {
				listContato = new LinkedList<>(ContatoFac.pesquisarRegistro(new Contato()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			ContatoArqCsv contatoArqCsv = new ContatoArqCsv(listContato);
			contatoArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Contato> listContato = new LinkedList<>();

			try {

				ArquivoJson<Contato> arquivoJson = new ArquivoJson<Contato>(contato, "contato");
				arquivoJson.gravarArquivo(ContatoFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			ContatoArqCsv contatoArqCsv = new ContatoArqCsv(listContato);
			contatoArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getContatoJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getContatoJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			contato = new Contato();
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
			List<Contato> contatos = new LinkedList<>();

			if (contato.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (contatos.add(ContatoFac.getRegistro(contato))) {
				ContatoRel contatoRel = new ContatoRel(contatos);
				contatoRel.retornarRelatorio(true);
			}

		}
	}

	public class MostraJanCad extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent event) {
			if (event.getSource() == getContatoPainelCad().getLabelEmpresa()) {
				MainControl.mostrarFrame(MainControl.getEmpresaJanCad());
			} else {
				MainControl.getEmpresaJanCad().reiniciarGui();
			}
		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			List<Empresa> empresaList = (List<Empresa>) EmpresaFac.getRegistro();
			Collections.sort(empresaList, new EmpresaComp().new NomeFantasia());

			getContatoPainelCad().getGuiEmpresa().removeAllItems();
			getContatoPainelCad().getGuiEmpresa().addItem(new Empresa());

			for (Empresa e : empresaList) {
				getContatoPainelCad().getGuiEmpresa().addItem(e);
			}

			contato = new Contato();
			getContatoJanCad().limparGui();
			getContatoPainelCad().getGuiNome().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getContatoPainelPesq().pesquisarRegistro(contato);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getContatoJanPesq());
				getContatoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getContatoPainelPesq().pesquisarRegistro(new Contato());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getContatoJanPesq());
				getContatoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Contato> contatos = new LinkedList<>();

			try {
				contatos = new LinkedList<>(ContatoFac.pesquisarRegistro(new Contato()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			ContatoRel contatoRel = new ContatoRel(contatos);
			contatoRel.retornarRelatorio(true);

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
				String nome = getContatoPainelCad().getGuiNome().getText();
				if ((nome == null) || (nome.length() == 0)) {
					getContatoPainelCad().getGuiNome().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					ContatoFac.salvarRegistro(contato);
					contato = new Contato();
					MainControl.getAgendaContatoJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getContatoPainelCad().getGuiNome().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_CONTATO_CPF")) {
						Msg.avisoCampoDuplicado("CPF");
						getContatoPainelCad().getGuiCpf().requestFocus();
					} else if (mensagem.contains("INDEX_CONTATO_CNPJ")) {
						Msg.avisoCampoDuplicado("CNPJ");
						getContatoPainelCad().getGuiCnpj().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private Contato contato;

	public void atualizarGui() {
		if (contato == null) {
			return;
		}
		getContatoPainelCad().getGuiNome().setText(contato.getNome());
		getContatoPainelCad().getGuiSexo().setSelectedItem(contato.getSexo());
		getContatoPainelCad().getGuiEmail().setText(contato.getEmail());
		getContatoPainelCad().getGuiFax().setText(contato.getFax());
		getContatoPainelCad().getGuiFone1().setText(contato.getFone1());
		getContatoPainelCad().getGuiFone2().setText(contato.getFone2());
		getContatoPainelCad().getGuiEmpresa().setSelectedItem(contato.getEmpresa());
		getContatoPainelCad().getGuiBairro().setText(contato.getEnderecoBairro());
		getContatoPainelCad().getGuiCep().setText(contato.getEnderecoCep());
		getContatoPainelCad().getGuiCidade().setText(contato.getEnderecoCidade());
		getContatoPainelCad().getGuiComplemento().setText(contato.getEnderecoComplemento());
		getContatoPainelCad().getGuiEstado().setText(contato.getEnderecoEstado());
		getContatoPainelCad().getGuiLogradouro().setText(contato.getEnderecoLogradouro());
		getContatoPainelCad().getGuiPais().setText(contato.getEnderecoPais());
		getContatoPainelCad().getGuiCnpj().setText(contato.getCnpj());
		getContatoPainelCad().getGuiCpf().setText(contato.getCpf());
	}

	public void atualizarObjeto() {
		contato.setNome(getContatoPainelCad().getGuiNome().getText());
		contato.setSexo((String) getContatoPainelCad().getGuiSexo().getSelectedItem());
		contato.setEmail(getContatoPainelCad().getGuiEmail().getText());
		contato.setFax(getContatoPainelCad().getGuiFax().getText());
		contato.setFone1(getContatoPainelCad().getGuiFone1().getText());
		contato.setFone2(getContatoPainelCad().getGuiFone2().getText());
		contato.setEmpresa((Empresa) getContatoPainelCad().getGuiEmpresa().getSelectedItem());
		contato.setEnderecoBairro(getContatoPainelCad().getGuiBairro().getText());
		contato.setEnderecoCep(getContatoPainelCad().getGuiCep().getText());
		contato.setEnderecoCidade(getContatoPainelCad().getGuiCidade().getText());
		contato.setEnderecoComplemento(getContatoPainelCad().getGuiComplemento().getText());
		contato.setEnderecoEstado(getContatoPainelCad().getGuiEstado().getText());
		contato.setEnderecoLogradouro(getContatoPainelCad().getGuiLogradouro().getText());
		contato.setEnderecoPais(getContatoPainelCad().getGuiPais().getText());
		contato.setCnpj(getContatoPainelCad().getGuiCnpj().getText());
		contato.setCpf(getContatoPainelCad().getGuiCpf().getText());
	}

	public Contato getContato() {
		return contato;
	}

	public ContatoJanCad getContatoJanCad() {
		return MainControl.getAgendaContatoJanCad();
	}

	public ContatoJanPesq getContatoJanPesq() {
		return MainControl.getAgendaContatoJanPesq();
	}

	public ContatoPainelCad getContatoPainelCad() {
		return MainControl.getAgendaContatoJanCad().getContatoPainelCad();
	}

	public ContatoPainelPesq getContatoPainelPesq() {
		return MainControl.getAgendaContatoJanPesq().getContatoPainelPesq();
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}
}
