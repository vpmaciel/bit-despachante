package erp.veiculo.marca;

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

final class MarcaControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((marca == null) || (marca.getDescricao() == null) || (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION)) {
				return;
			}
			try {
				MarcaFac.deletarRegistro(marca);
				getContaJanCad().limparGui();
				marca = new Marca();
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
				getContaJanCad().setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Marca> listConta = new LinkedList<>();

			try {
				listConta = new LinkedList<>(MarcaFac.pesquisarRegistro(new Marca()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			MarcaArqCsv marcaArqCsv = new MarcaArqCsv(listConta);
			marcaArqCsv.retornarArquivo(true);
			Sis.abrirDiretorio();

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Marca> listConta = new LinkedList<>();

			try {

				ArquivoJson<Marca> arquivoJson = new ArquivoJson<>(marca, "usuario");
				arquivoJson.gravarArquivo(MarcaFac.getRegistro());				
			} catch (Exception e) {
				e.printStackTrace();
			}

			MarcaArqCsv marcaArqCsv = new MarcaArqCsv(listConta);
			marcaArqCsv.retornarArquivo(true);
			Sis.abrirDiretorio();
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
			marca = new Marca();
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
			List<Marca> marcas = new LinkedList<>();

			if (marca.getDescricao() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (marcas.add(MarcaFac.getRegistro(marca))) {
				MarcaRel marcaRel = new MarcaRel(marcas);
				marcaRel.retornarRelatorio();
			}
		}
	}

	public class MostraFrameConta extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON1) {
				MainControl.mostrarFrame(MainControl.getMarcaJan());
			} else {
				MainControl.getMarcaJan().reiniciarGui();
			}
		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			marca = new Marca();
			getContaJanCad().limparGui();
			getContaPainelCad().getGuiDescricao().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			marca = new Marca();
			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			JOptionPane.showMessageDialog(null, marca.getDescricao());
			totalPesquisaRegistro = MainControl.getMarcaJan().getContaPainelPesq().pesquisarRegistro(marca);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.getMarcaJan().getTabbedPane().setSelectedIndex(1);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = MainControl.getMarcaJan().getContaPainelPesq()
					.pesquisarRegistro(new Marca());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.getMarcaJan().getTabbedPane().setSelectedIndex(1);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Marca> marcas = new LinkedList<>();

			try {
				marcas = new LinkedList<>(MarcaFac.pesquisarRegistro(new Marca()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			MarcaRel marcaRel = new MarcaRel(marcas);
			marcaRel.retornarRelatorio();

		}
	}

	public class Salva implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			try {

				int mensagem = Msg.confirmarSalvarRegistro();
				if ((mensagem != JOptionPane.YES_OPTION) || !Entrada.validar(getContaPainelCad().getGuiDescricao(), "NOME", RegExp.NOME, true) || !Entrada.validar(getContaPainelCad().getGuiDescricao(), "DESCRIÇÃO", RegExp.NUMERO_BANCO, false)) {
					return;
				}

				if (((getContaPainelCad().getGuiDescricao().getText()) == null)
						|| (getContaPainelCad().getGuiDescricao().getText().length() == 0)) {
					getContaPainelCad().getGuiDescricao().requestFocus();
					Msg.avisoCampoObrigatorio("NOME");
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					MarcaFac.salvarRegistro(marca);
					marca = new Marca();
					MainControl.getMarcaJan().limparGui();
					Msg.sucessoSalvarRegistro();
					getContaPainelCad().getGuiDescricao().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_DESCRICAO")) {
						Msg.avisoCampoDuplicado("NOME");
						getContaPainelCad().getGuiDescricao().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private Marca marca;

	MarcaControl() {
	}

	public void atualizarGui() {
		if (marca == null) {
			return;
		}
		getContaPainelCad().getGuiDescricao().setText(marca.getDescricao());		
	}

	public void atualizarObjeto() {
		if (marca == null) {
			marca = new Marca();
		}
		marca.setDescricao(getContaPainelCad().getGuiDescricao().getText());		
	}

	public Marca getConta() {
		return marca;
	}

	public MarcaJanCad getContaJanCad() {
		return MainControl.getMarcaJan();
	}

	public MarcaPainelCad getContaPainelCad() {
		return MainControl.getMarcaJan().getContaPainelCad();
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
}