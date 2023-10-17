package erp.veiculo;

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

final class VeiculoControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((veiculo == null) || (veiculo.getDescricao() == null)
					|| (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION)) {
				return;
			}
			try {
				VeiculoFac.deletarRegistro(veiculo);
				getVeiculoJanCad().limparGui();
				veiculo = new Veiculo();
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
				getVeiculoJanCad().setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Veiculo> listVeiculo = new LinkedList<>();

			try {
				listVeiculo = new LinkedList<>(VeiculoFac.pesquisarRegistro(new Veiculo()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			VeiculoArqCsv veiculoArqCsv = new VeiculoArqCsv(listVeiculo);
			veiculoArqCsv.retornarArquivo(true);
			Sis.abrirDiretorio();

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Veiculo> listVeiculo = new LinkedList<>();

			try {

				ArquivoJson<Veiculo> arquivoJson = new ArquivoJson<>(veiculo, "usuario");
				arquivoJson.gravarArquivo(VeiculoFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			VeiculoArqCsv veiculoArqCsv = new VeiculoArqCsv(listVeiculo);
			veiculoArqCsv.retornarArquivo(true);
			Sis.abrirDiretorio();
		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getVeiculoJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getVeiculoJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			veiculo = new Veiculo();
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
			List<Veiculo> veiculos = new LinkedList<>();

			if (veiculo.getDescricao() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (veiculos.add(VeiculoFac.getRegistro(veiculo))) {
				VeiculoRel veiculoRel = new VeiculoRel(veiculos);
				veiculoRel.retornarRelatorio();
			}
		}
	}

	public class MostraFrameVeiculo extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON1) {
				MainControl.mostrarFrame(MainControl.getVeiculoJan());
			} else {
				MainControl.getVeiculoJan().reiniciarGui();
			}
		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			veiculo = new Veiculo();
			getVeiculoJanCad().limparGui();
			getVeiculoPainelCad().getGuiDescricao().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			veiculo = new Veiculo();
			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = MainControl.getVeiculoJan().getVeiculoPainelPesq().pesquisarRegistro(veiculo);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.getVeiculoJan().getTabbedPane().setSelectedIndex(1);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = MainControl.getVeiculoJan().getVeiculoPainelPesq().pesquisarRegistro(new Veiculo());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.getVeiculoJan().getTabbedPane().setSelectedIndex(1);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Veiculo> veiculos = new LinkedList<>();

			try {
				veiculos = new LinkedList<>(VeiculoFac.pesquisarRegistro(new Veiculo()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			VeiculoRel veiculoRel = new VeiculoRel(veiculos);
			veiculoRel.retornarRelatorio();

		}
	}

	public class Salva implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {

				int mensagem = Msg.confirmarSalvarRegistro();
				if ((mensagem != JOptionPane.YES_OPTION)
						|| !Entrada.validar(getVeiculoPainelCad().getGuiDescricao(), "NOME", RegExp.NOME, true)
						|| !Entrada.validar(getVeiculoPainelCad().getGuiDescricao(), "DESCRIÇÃO", RegExp.NUMERO_BANCO,
								false)) {
					return;
				}

				if (((getVeiculoPainelCad().getGuiDescricao().getText()) == null)
						|| (getVeiculoPainelCad().getGuiDescricao().getText().length() == 0)) {
					getVeiculoPainelCad().getGuiDescricao().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					VeiculoFac.salvarRegistro(veiculo);
					veiculo = new Veiculo();
					MainControl.getVeiculoJan().limparGui();
					Msg.sucessoSalvarRegistro();
					getVeiculoPainelCad().getGuiDescricao().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_DESCRICAO")) {
						Msg.avisoCampoDuplicado("NOME");
						getVeiculoPainelCad().getGuiDescricao().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private Veiculo veiculo;

	VeiculoControl() {
	}

	public void atualizarGui() {
		if (veiculo == null) {
			return;
		}
		getVeiculoPainelCad().getGuiDescricao().setText(veiculo.getDescricao());
	}

	public void atualizarObjeto() {
		if (veiculo == null) {
			veiculo = new Veiculo();
		}
		veiculo.setDescricao(getVeiculoPainelCad().getGuiDescricao().getText());
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public VeiculoJan getVeiculoJanCad() {
		return MainControl.getVeiculoJan();
	}

	public VeiculoPainelCad getVeiculoPainelCad() {
		return MainControl.getVeiculoJan().getVeiculoPainelCad();
	}

	public void setModelo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
}