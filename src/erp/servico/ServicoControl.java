package erp.servico;

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
import erp.arquitetura.Numero;
import erp.arquitetura.Sis;
import erp.arquitetura.gui.Msg;
import erp.sistema.main.MainControl;

final class ServicoControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((servico == null) || (servico.getDescricao() == null)
					|| (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION)) {
				return;
			}
			try {
				ServicoFac.deletarRegistro(servico);
				getContaJanCad().limparGui();
				servico = new Servico();
				Msg.sucessoExcluiRegistro();
			} catch (Exception e) {
				Msg.erroExcluiRegistro();
			}
		}
	}

	public class FechaCaixa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if (Msg.confirmarFecharCaixa() != JOptionPane.YES_OPTION) {
				return;
			}

			Float totalCaixa = 0.0f;

			try {

				List<Servico> listConta = new LinkedList<>();
				listConta = new LinkedList<>(ServicoFac.pesquisarRegistro(new Servico()));

				if (listConta.size() == 0) {
					JOptionPane.showMessageDialog(null, "Caixa sem lançamentos !", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}

				ServicoArqCsv servicoArqCsv = new ServicoArqCsv(listConta);

				for (Servico servico_item : listConta) {
					totalCaixa += servico_item.getValor();
					ServicoFac.deletarRegistro(servico_item);
				}

				getContaJanCad().limparGui();
				Msg.sucessoFecharCaixa();
				JOptionPane.showMessageDialog(null, "Caixa Fechado: R$ " + Numero.FloatToString(totalCaixa));
				servicoArqCsv.retornarArquivo(true);
				Sis.abrirDiretorio();
			} catch (Exception e) {
				Msg.erroExcluiRegistro();
			}
		}
	}

	public class FechaJanela implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {
				getContaJanCad().setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Servico> listConta = new LinkedList<>();

			try {
				listConta = new LinkedList<>(ServicoFac.pesquisarRegistro(new Servico()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			ServicoArqCsv servicoArqCsv = new ServicoArqCsv(listConta);
			servicoArqCsv.retornarArquivo(true);
			Sis.abrirDiretorio();

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {

				ArquivoJson<Servico> arquivoJson = new ArquivoJson<>(servico, "servico");
				arquivoJson.gravarArquivo(ServicoFac.getRegistro());
				arquivoJson.retornarArquivo(true);
				Sis.abrirDiretorio();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getContaJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getContaJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			servico = new Servico();
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
			List<Servico> servicos = new LinkedList<>();

			if (servico.getDescricao() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (servicos.add(ServicoFac.getRegistro(servico))) {
				ServicoRel servicoRel = new ServicoRel(servicos);
				servicoRel.retornarRelatorio();
			}
		}
	}

	public class MostraFrameConta extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON1) {
				MainControl.mostrarFrame(MainControl.getServicoJan());
			} else {
				MainControl.getServicoJan().reiniciarGui();
			}
		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			servico = new Servico();
			getContaJanCad().limparGui();
			getContaPainelCad().getGuiPlaca().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			servico = new Servico();
			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = MainControl.getServicoJan().getContaPainelPesq().pesquisarRegistro(servico);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.getServicoJan().getTabbedPane().setSelectedIndex(1);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = MainControl.getServicoJan().getContaPainelPesq().pesquisarRegistro(new Servico());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.getServicoJan().getTabbedPane().setSelectedIndex(1);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Servico> servicoList = new LinkedList<>();

			try {
				servicoList = new LinkedList<>(ServicoFac.pesquisarRegistro(new Servico()));
				if (servicoList.size() == 0) {
					JOptionPane.showMessageDialog(null, "Sem registros para gerar relatório !", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}

			ServicoRel servicoRel = new ServicoRel(servicoList);
			servicoRel.retornarRelatorio();

		}
	}

	public class Salva implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {

				int mensagem = Msg.confirmarSalvarRegistro();

				if (((getContaPainelCad().getGuiPlaca().getText()) == null)
						|| (getContaPainelCad().getGuiPlaca().getText().length() == 0)) {
					getContaPainelCad().getGuiPlaca().requestFocus();
					Msg.avisoCampoObrigatorio("PLACA DO VEÍCULO");
					return;
				}

				if (((getContaPainelCad().getGuiDescricao().getText()) == null)
						|| (getContaPainelCad().getGuiDescricao().getText().length() == 0)) {
					getContaPainelCad().getGuiDescricao().requestFocus();
					Msg.avisoCampoObrigatorio("DESCRIÇÃO DO SERVIÇO");
					return;
				}

				if (((getContaPainelCad().getGuiValor().getText()) == null)
						|| (getContaPainelCad().getGuiValor().getText().length() == 0)) {
					getContaPainelCad().getGuiValor().requestFocus();
					Msg.avisoCampoObrigatorio("VALOR DO SERVIÇO");
					return;
				}

				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					ServicoFac.salvarRegistro(servico);
					servico = new Servico();
					MainControl.getServicoJan().limparGui();
					Msg.sucessoSalvarRegistro();
					getContaPainelCad().getGuiPlaca().requestFocus();
				}
			} catch (Exception e) {
				getContaPainelCad().getGuiPlaca().requestFocus();
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private Servico servico;

	ServicoControl() {
	}

	public void atualizarGui() {
		if (servico == null) {
			return;
		}
		getContaPainelCad().getGuiCpfCnpjCliente().setText(servico.getCpfCnpjCliente());
		getContaPainelCad().getGuiData().setText(servico.getData().toString());
		getContaPainelCad().getGuiDescricao().setText(servico.getDescricao());
		getContaPainelCad().getGuiNomeCliente().setText(servico.getNomeCliente());
		getContaPainelCad().getGuiPlaca().setText(servico.getPlaca());
		getContaPainelCad().getGuiTelefoneCliente().setText(servico.getTelefoneCliente());
		getContaPainelCad().getGuiValor().setText(Numero.FloatToString(servico.getValor()));
	}

	public void atualizarObjeto() {
		if (servico == null) {
			servico = new Servico();
		}
		servico.setCpfCnpjCliente(getContaPainelCad().getGuiCpfCnpjCliente().getText());
		servico.setDescricao(getContaPainelCad().getGuiDescricao().getText());
		servico.setNomeCliente(getContaPainelCad().getGuiNomeCliente().getText());
		servico.setPlaca(getContaPainelCad().getGuiPlaca().getText());
		servico.setTelefoneCliente(getContaPainelCad().getGuiTelefoneCliente().getText());
		servico.setValor(Numero.stringToFloat(getContaPainelCad().getGuiValor().getText()));
	}

	public Servico getConta() {
		return servico;
	}

	public ServicoJan getContaJanCad() {
		return MainControl.getServicoJan();
	}

	public ServicoPainelCad getContaPainelCad() {
		return MainControl.getServicoJan().getContaPainelCad();
	}

	public void setModelo(Servico servico) {
		this.servico = servico;
	}
}