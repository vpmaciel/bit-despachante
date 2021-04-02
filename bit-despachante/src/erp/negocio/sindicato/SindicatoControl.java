package erp.negocio.sindicato;

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

final class SindicatoControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((sindicato == null) || (sindicato.getId() == null)) {
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				SindicatoFac.deletarRegistro(sindicato);
				getSindicatoJanCad().limparGui();
				sindicato = new Sindicato();
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
				getSindicatoJanCad().setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Sindicato> listSindicato = new LinkedList<>();

			try {
				listSindicato = new LinkedList<>(SindicatoFac.pesquisarRegistro(new Sindicato()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			SindicatoArqCsv SindicatoArqCsv = new SindicatoArqCsv(listSindicato);
			SindicatoArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Sindicato> listSindicato = new LinkedList<>();

			try {

				ArquivoJson<Sindicato> arquivoJson = new ArquivoJson<Sindicato>(sindicato, "Sindicato");
				arquivoJson.gravarArquivo(SindicatoFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			SindicatoArqCsv SindicatoArqCsv = new SindicatoArqCsv(listSindicato);
			SindicatoArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getSindicatoJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getSindicatoJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			sindicato = new Sindicato();
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
			List<Sindicato> sindicatos = new LinkedList<>();

			if (sindicato.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (sindicatos.add(SindicatoFac.getRegistro(sindicato))) {
				SindicatoRel sindicatoRel = new SindicatoRel(sindicatos);
				sindicatoRel.retornarRelatorio(true);
			}
		}
	}

	public class MostraFrameSindicato extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON1) {
				MainControl.mostrarFrame(MainControl.getSindicatoJanCad());
			} else {
				MainControl.getSindicatoJanCad().reiniciarGui();
			}
		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			sindicato = new Sindicato();
			getSindicatoJanCad().limparGui();
			getSindicatoPainelCad().getGuiNomeFantasia().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getSindicatoPainelPesq().pesquisarRegistro(sindicato);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getSindicatoJanPesq());
				getSindicatoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getSindicatoPainelPesq().pesquisarRegistro(new Sindicato());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getSindicatoJanPesq());
				getSindicatoJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Sindicato> sindicatos = new LinkedList<>();

			try {
				sindicatos = new LinkedList<>(SindicatoFac.pesquisarRegistro(new Sindicato()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			SindicatoRel sindicatoRel = new SindicatoRel(sindicatos);
			sindicatoRel.retornarRelatorio(true);

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
				String nome = getSindicatoPainelCad().getGuiNomeFantasia().getText();
				if ((nome == null) || (nome.length() == 0)) {
					getSindicatoPainelCad().getGuiNomeFantasia().requestFocus();
					Msg.avisoCampoObrigatorio("NomeFantasia");
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					SindicatoFac.salvarRegistro(sindicato);
					sindicato = new Sindicato();
					getSindicatoJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getSindicatoPainelCad().getGuiNomeFantasia().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_SINDICATO_CNPJ")) {
						Msg.avisoCampoDuplicado("CNPJ");
						getSindicatoPainelCad().getGuiCnpj().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private Sindicato sindicato;

	public void atualizarGui() {
		if (sindicato == null) {
			return;
		}
		getSindicatoPainelCad().getGuiNomeFantasia().setText(sindicato.getNomeFantasia());
		getSindicatoPainelCad().getGuiEmail().setText(sindicato.getEmail());
		getSindicatoPainelCad().getGuiFax().setText(sindicato.getFax());
		getSindicatoPainelCad().getGuiFone1().setText(sindicato.getFone1());
		getSindicatoPainelCad().getGuiFone2().setText(sindicato.getFone2());
		getSindicatoPainelCad().getGuiBairro().setText(sindicato.getEnderecoBairro());
		getSindicatoPainelCad().getGuiCep().setText(sindicato.getEnderecoCep());
		getSindicatoPainelCad().getGuiCidade().setText(sindicato.getEnderecoCidade());
		getSindicatoPainelCad().getGuiComplemento().setText(sindicato.getEnderecoComplemento());
		getSindicatoPainelCad().getGuiEstado().setText(sindicato.getEnderecoEstado());
		getSindicatoPainelCad().getGuiLogradouro().setText(sindicato.getEnderecoLogradouro());
		getSindicatoPainelCad().getGuiPais().setText(sindicato.getEnderecoPais());
		getSindicatoPainelCad().getGuiCnpj().setText(sindicato.getCnpj());
		getSindicatoPainelCad().getGuiTipoSindicato().setSelectedItem(sindicato.getTipoSindicato());
	}

	public void atualizarObjeto() {
		sindicato.setNomeFantasia(getSindicatoPainelCad().getGuiNomeFantasia().getText());
		sindicato.setRazaoSocial(getSindicatoPainelCad().getGuiRazaoSocial().getText());
		sindicato.setEmail(getSindicatoPainelCad().getGuiEmail().getText());
		sindicato.setFax(getSindicatoPainelCad().getGuiFax().getText());
		sindicato.setFone1(getSindicatoPainelCad().getGuiFone1().getText());
		sindicato.setFone2(getSindicatoPainelCad().getGuiFone2().getText());
		sindicato.setEnderecoBairro(getSindicatoPainelCad().getGuiBairro().getText());
		sindicato.setEnderecoCep(getSindicatoPainelCad().getGuiCep().getText());
		sindicato.setEnderecoCidade(getSindicatoPainelCad().getGuiCidade().getText());
		sindicato.setEnderecoComplemento(getSindicatoPainelCad().getGuiComplemento().getText());
		sindicato.setEnderecoEstado(getSindicatoPainelCad().getGuiEstado().getText());
		sindicato.setEnderecoLogradouro(getSindicatoPainelCad().getGuiLogradouro().getText());
		sindicato.setEnderecoPais(getSindicatoPainelCad().getGuiPais().getText());
		sindicato.setCnpj(getSindicatoPainelCad().getGuiCnpj().getText());
		sindicato.setTipoSindicato((String) getSindicatoPainelCad().getGuiTipoSindicato().getSelectedItem());

		if (getSindicatoPainelCad().getGuiCnpj().getText().length() == 0) {
			sindicato.setCnpj(null);
		}
	}

	public Sindicato getSindicato() {
		return sindicato;
	}

	public SindicatoJanCad getSindicatoJanCad() {
		return MainControl.getSindicatoJanCad();
	}

	public SindicatoJanPesq getSindicatoJanPesq() {
		return MainControl.getSindicatoJanPesq();
	}

	public SindicatoPainelCad getSindicatoPainelCad() {
		return MainControl.getSindicatoJanCad().getSindicatoPainelCad();
	}

	public SindicatoPainelPesq getSindicatoPainelPesq() {
		return MainControl.getSindicatoJanPesq().getSindicatoPainelPesq();
	}

	public void setSindicato(Sindicato sindicato) {
		this.sindicato = sindicato;
	}
}
