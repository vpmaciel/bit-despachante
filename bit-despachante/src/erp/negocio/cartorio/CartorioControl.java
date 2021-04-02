package erp.negocio.cartorio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import erp.arquitetura.ArquivoJson;
import erp.arquitetura.gui.Msg;
import erp.sistema.main.MainControl;

final class CartorioControl {

	public class Exclui implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ((cartorio == null) || (cartorio.getId() == null)) {
				Msg.erroExcluiRegistro();
				return;
			}
			if (Msg.confirmarExcluiRegistro() != JOptionPane.YES_OPTION) {
				return;
			}
			try {
				CartorioFac.deletarRegistro(cartorio);
				getCartorioJanCad().limparGui();
				cartorio = new Cartorio();
				Msg.sucessoExcluiRegistro();
			} catch (Exception e) {
				Msg.erroExcluiRegistro();
			}
		}
	}

	public class FechaJanela implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			getCartorioJanCad().setVisible(false);
		}
	}

	public class FormatoCsv implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Cartorio> listCartorio = new LinkedList<>();

			try {
				listCartorio = new LinkedList<>(CartorioFac.pesquisarRegistro(new Cartorio()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			CartorioArqCsv CartorioArqCsv = new CartorioArqCsv(listCartorio);
			CartorioArqCsv.retornarArquivo(true);

		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Cartorio> listCartorio = new LinkedList<>();

			try {

				ArquivoJson<Cartorio> arquivoJson = new ArquivoJson<Cartorio>(cartorio, "Cartorio");
				arquivoJson.gravarArquivo(CartorioFac.getRegistro());
			} catch (Exception e) {
				e.printStackTrace();
			}

			CartorioArqCsv CartorioArqCsv = new CartorioArqCsv(listCartorio);
			CartorioArqCsv.retornarArquivo(true);

		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getCartorioJanCad().reiniciarGui();
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getCartorioJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			cartorio = new Cartorio();
			getCartorioPainelCad().getNomeGuiFantasia().requestFocus();
		}
	}

	public class Home implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			MainControl.mostrarFrame(MainControl.getMainJanCad());
		}
	}

	public class Imprime implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			List<Cartorio> cartorios = new LinkedList<>();

			if (cartorio.getId() == null) {
				Msg.avisoImprimiRegistroNaoCadastrado();
				return;
			}
			if (cartorios.add(CartorioFac.getRegistro(cartorio))) {
				CartorioRel cartorioRel = new CartorioRel(cartorios);
				cartorioRel.retornarRelatorio(true);
			}

		}
	}

	public class Novo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			cartorio = new Cartorio();
			getCartorioJanCad().limparGui();
			getCartorioPainelCad().getNomeGuiFantasia().requestFocus();
		}
	}

	public class Pesquisa implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			atualizarObjeto();
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getCartorioPainelPesq().pesquisarRegistro(cartorio);
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getCartorioJanPesq());
				getCartorioJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Registro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			long totalPesquisaRegistro = 0;
			totalPesquisaRegistro = getCartorioPainelPesq().pesquisarRegistro(new Cartorio());
			Msg.avisoRegistroEncontrado(totalPesquisaRegistro);

			if (totalPesquisaRegistro > 0) {
				MainControl.mostrarFrame(getCartorioJanPesq());
				getCartorioJanPesq().setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Cartorio> cartorios = new LinkedList<>();

			try {
				cartorios = new LinkedList<>(CartorioFac.pesquisarRegistro(new Cartorio()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			CartorioRel cartorioRel = new CartorioRel(cartorios);
			cartorioRel.retornarRelatorio(true);

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

				if (((getCartorioPainelCad().getNomeGuiFantasia().getText()) == null)
						|| (getCartorioPainelCad().getNomeGuiFantasia().getText().length() == 0)) {
					getCartorioPainelCad().getNomeGuiFantasia().requestFocus();
					Msg.avisoCampoObrigatorio("NOME FANTASIA");
					return;
				}
				if (mensagem == JOptionPane.YES_OPTION) {
					atualizarObjeto();
					CartorioFac.salvarRegistro(cartorio);
					cartorio = new Cartorio();
					getCartorioJanCad().limparGui();
					Msg.sucessoSalvarRegistro();
					getCartorioPainelCad().getNomeGuiFantasia().requestFocus();
				}
			} catch (Exception e) {
				Throwable throwable = e.getCause().getCause();
				String mensagem = throwable.toString();
				if (mensagem.contains("ConstraintViolationException")) {
					if (mensagem.contains("INDEX_CARTORIO_CNPJ")) {
						Msg.avisoCampoDuplicado("CNPJ");
						getCartorioPainelCad().getGuiCnpj().requestFocus();
					} else {
						Msg.avisoCampoDuplicado();
					}
				}
				e.printStackTrace();
				Msg.erroSalvarRegistro();
			}
		}
	}

	private Cartorio cartorio;

	public CartorioControl() {

	}

	public void atualizarGui() {
		if (cartorio == null) {
			return;
		}
		getCartorioPainelCad().getGuiCnpj().setText(cartorio.getCnpj());
		getCartorioPainelCad().getGuiComarca().setText(cartorio.getComarca());
		getCartorioPainelCad().getGuiDistrito().setText(cartorio.getDistrito());
		getCartorioPainelCad().getGuiMunicipio().setText(cartorio.getMunicipio());
		getCartorioPainelCad().getNomeGuiFantasia().setText(cartorio.getNomeFantasia());
		getCartorioPainelCad().getGuiRazaoSocial().setText(cartorio.getRazaoSocial());
		getCartorioPainelCad().getGuiSubstituto().setText(cartorio.getSubstituto());
		getCartorioPainelCad().getGuiTitular().setText(cartorio.getTitular());
		getCartorioPainelCad().getGuiEmail().setText(cartorio.getEmail());
		getCartorioPainelCad().getGuiFax().setText(cartorio.getFax());
		getCartorioPainelCad().getGuiFone1().setText(cartorio.getFone1());
		getCartorioPainelCad().getGuiFone2().setText(cartorio.getFone2());
		getCartorioPainelCad().getGuiBairro().setText(cartorio.getEnderecoBairro());
		getCartorioPainelCad().getGuiCep().setText(cartorio.getEnderecoCep());
		getCartorioPainelCad().getGuiCidade().setText(cartorio.getEnderecoCidade());
		getCartorioPainelCad().getGuiComplemento().setText(cartorio.getEnderecoComplemento());
		getCartorioPainelCad().getGuiEstado().setText(cartorio.getEnderecoEstado());
		getCartorioPainelCad().getGuiLogradouro().setText(cartorio.getEnderecoLogradouro());
		getCartorioPainelCad().getGuiPais().setText(cartorio.getEnderecoPais());
	}

	public void atualizarObjeto() {
		cartorio.setCnpj(getCartorioPainelCad().getGuiCnpj().getText());
		cartorio.setComarca(getCartorioPainelCad().getGuiComarca().getText());
		cartorio.setDistrito(getCartorioPainelCad().getGuiDistrito().getText());
		cartorio.setMunicipio(getCartorioPainelCad().getGuiMunicipio().getText());
		cartorio.setNomeFantasia(getCartorioPainelCad().getNomeGuiFantasia().getText());
		cartorio.setRazaoSocial(getCartorioPainelCad().getGuiRazaoSocial().getText());
		cartorio.setSubstituto(getCartorioPainelCad().getGuiSubstituto().getText());
		cartorio.setTitular(getCartorioPainelCad().getGuiTitular().getText());
		cartorio.setEmail(getCartorioPainelCad().getGuiEmail().getText());
		cartorio.setFax(getCartorioPainelCad().getGuiFax().getText());
		cartorio.setFone1(getCartorioPainelCad().getGuiFone1().getText());
		cartorio.setFone2(getCartorioPainelCad().getGuiFone2().getText());
		cartorio.setEnderecoBairro(getCartorioPainelCad().getGuiBairro().getText());
		cartorio.setEnderecoCep(getCartorioPainelCad().getGuiCep().getText());
		cartorio.setEnderecoCidade(getCartorioPainelCad().getGuiCidade().getText());
		cartorio.setEnderecoComplemento(getCartorioPainelCad().getGuiComplemento().getText());
		cartorio.setEnderecoEstado(getCartorioPainelCad().getGuiEstado().getText());
		cartorio.setEnderecoLogradouro(getCartorioPainelCad().getGuiLogradouro().getText());
		cartorio.setEnderecoPais(getCartorioPainelCad().getGuiPais().getText());

		if (getCartorioPainelCad().getGuiRazaoSocial().getText().length() == 0) {
			cartorio.setRazaoSocial(null);
		}
	}

	public Cartorio getCartorio() {
		return cartorio;
	}

	public CartorioJanCad getCartorioJanCad() {
		return MainControl.getCartorioJanCad();
	}

	public CartorioJanPesq getCartorioJanPesq() {
		return MainControl.getCartorioJanPesq();
	}

	public CartorioPainelCad getCartorioPainelCad() {
		return MainControl.getCartorioJanCad().getCartorioPainelCad();
	}

	public CartorioPainelPesq getCartorioPainelPesq() {
		return MainControl.getCartorioJanPesq().getCartorioPainelPesq();
	}

	public void setCartorio(Cartorio cartorio) {
		this.cartorio = cartorio;
	}
}