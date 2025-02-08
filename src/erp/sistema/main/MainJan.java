package erp.sistema.main;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import erp.arquitetura.Sis;
import erp.arquitetura.gui.Imagem;

@SuppressWarnings("serial")
public class MainJan extends JFrame {

	private static MainControl mainControl;

	public static MainJan getFrameMain() {
		return MainControl.getMainJan();
	}

	public static MainControl getMainControl() {
		return mainControl;
	}

	static void mostrarFrame(JFrame frame) {
		MainControl.mostrarFrame(frame);
	}

	private JMenu menuAjuda;
	private JMenu menuArquivo;
	private final JMenuBar menuBar = new JMenuBar();
	private JMenu menuCadastro;
	private JMenuItem menuItemAjudaSobreSistema;
	private JMenuItem menuItemArquivoLogin;
	private JMenuItem menuItemArquivoUsuario;
	private JMenuItem menuItemArquivoSair;
	private JMenuItem menuItemCadastroCliente;
	private JMenuItem menuItemCadastroVeiculo;
	private JMenuItem menuItemCadastroServico;
	private JMenuItem menuItemCadastroPedidoPlaca;
	

	public MainJan() {
		iniciarGui();
		iniciarCont();
	}

	public JMenu getMenuAjuda() {
		return menuAjuda;
	}

	public JMenu getMenuArquivo() {
		return menuArquivo;
	}

	public JMenu getMenuCadastro() {
		return menuCadastro;
	}

	public JMenuItem getMenuItemAjudaSobreSistema() {
		return menuItemAjudaSobreSistema;
	}
	
	public Object getMenuItemArquivoLogin() {
		return menuItemArquivoLogin;
	}
	
	public Object getMenuItemArquivoUsuario() {
		return menuItemArquivoUsuario;
	}

	public JMenuItem getMenuItemArquivoSair() {
		return menuItemArquivoSair;
	}

	public JMenuItem getMenuItemCadastroCliente() {
		return menuItemCadastroCliente;
	}

	public JMenuItem getMenuItemCadastroPedidoPlaca() {
		return menuItemCadastroPedidoPlaca;
	}

	public JMenuItem getMenuItemCadastroServico() {
		return menuItemCadastroServico;
	}

	public JMenuItem getMenuItemCadastroVeiculo() {
		return menuItemCadastroVeiculo;
	}

	public void iniciarCont() {
		mainControl = MainControl.getInstance(this);
		addWindowListener(mainControl.new FrameGerenteEventos());
		menuItemArquivoSair.addActionListener(mainControl.new MenuGerenteEventos());		
		menuItemArquivoUsuario.addActionListener(mainControl.new MenuGerenteEventos());
		menuItemArquivoLogin.addActionListener(mainControl.new MenuGerenteEventos());
		menuItemCadastroVeiculo.addActionListener(mainControl.new MenuGerenteEventos());
		menuItemCadastroPedidoPlaca.addActionListener(mainControl.new MenuGerenteEventos());
		menuItemCadastroServico.addActionListener(mainControl.new MenuGerenteEventos());
		menuItemCadastroCliente.addActionListener(mainControl.new MenuGerenteEventos());
		menuItemAjudaSobreSistema.addActionListener(mainControl.new MenuGerenteEventos());
	}

	private void iniciarGui() {
		setTitle(Sis.getNomeSistema() + " - " + "PRINCIPAL");
		setIconImage(Imagem.getLogoTipoImage());
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setMinimumSize(Sis.getTamanhoJanela());
		setSize(Sis.getTamanhoJanela());
		setPreferredSize(Sis.getTamanhoJanela());
		setMaximumSize(Sis.getTamanhoJanela());

		menuArquivo = new JMenu("Arquivo");
		menuArquivo.setMnemonic('A');

		menuItemArquivoLogin = new JMenuItem("Login");
		
		menuArquivo.add(menuItemArquivoLogin);
		
		menuItemArquivoUsuario = new JMenuItem("Usuário");
		
		menuArquivo.add(menuItemArquivoUsuario);
		
		menuItemArquivoSair = new JMenuItem("Sair");

		menuArquivo.add(menuItemArquivoSair);

		menuBar.add(menuArquivo);

		menuCadastro = new JMenu("Cadastro");
		menuCadastro.setMnemonic('C');

		menuItemCadastroPedidoPlaca = new JMenuItem("Pedido de Placa");
		menuCadastro.add(menuItemCadastroPedidoPlaca);

		menuItemCadastroServico = new JMenuItem("Serviço");
		menuCadastro.add(menuItemCadastroServico);

		menuItemCadastroVeiculo = new JMenuItem("Veículo");
		menuCadastro.add(menuItemCadastroVeiculo);

		menuItemCadastroCliente = new JMenuItem("Cliente");
		menuCadastro.add(menuItemCadastroCliente);

		menuBar.add(menuCadastro);

		menuAjuda = new JMenu("Ajuda");
		menuAjuda.setMnemonic('u');

		menuItemAjudaSobreSistema = new JMenuItem("Sobre o Sistema");
		menuAjuda.add(menuItemAjudaSobreSistema);

		menuBar.add(menuAjuda);

		setJMenuBar(menuBar);
	}
}