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

    private static MainController mainController;

    public static MainJan getFrameMain() {
	return MainController.getMainJan();
    }

    public static MainController getMainControl() {
	return mainController;
    }

    static void mostrarFrame(JFrame frame) {
	MainController.mostrarFrame(frame);
    }

    private JMenu menuAjuda;
    private JMenu menuArquivo;
    private JMenuBar menuBar = new JMenuBar();
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
	iniciarController();
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

    public void iniciarController() {
	mainController = MainController.getInstance(this);
	addWindowListener(mainController.new FrameGerenteEventos());
	menuItemArquivoSair.addActionListener(mainController.new MenuGerenteEventos());
	menuItemArquivoUsuario.addActionListener(mainController.new MenuGerenteEventos());
	menuItemArquivoLogin.addActionListener(mainController.new MenuGerenteEventos());
	menuItemCadastroVeiculo.addActionListener(mainController.new MenuGerenteEventos());
	menuItemCadastroPedidoPlaca.addActionListener(mainController.new MenuGerenteEventos());
	menuItemCadastroServico.addActionListener(mainController.new MenuGerenteEventos());
	menuItemCadastroCliente.addActionListener(mainController.new MenuGerenteEventos());
	menuItemAjudaSobreSistema.addActionListener(mainController.new MenuGerenteEventos());
    }

    private void iniciarGui() {
	setTitle(Sis.getNomeSistema() + " - " + "Principal");
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