package erp.sistema.main;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
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

    private JButton buttonAjudaSobreSistema;
    private JButton buttonArquivoLogin;
    private JButton buttonArquivoUsuario;
    private JButton buttonArquivoSair;
    private JButton buttonCadastroCliente;
    private JButton buttonCadastroVeiculo;
    private JButton buttonCadastroServico;
    private JButton buttonCadastroPedidoPlaca;

    public MainJan() {
	iniciarGui();
	iniciarController();
    }

    public JButton getButtonAjudaSobreSistema() {
	return buttonAjudaSobreSistema;
    }

    public Object getButtonArquivoLogin() {
	return buttonArquivoLogin;
    }

    public Object getButtonArquivoUsuario() {
	return buttonArquivoUsuario;
    }

    public JButton getButtonArquivoSair() {
	return buttonArquivoSair;
    }

    public JButton getButtonCadastroCliente() {
	return buttonCadastroCliente;
    }

    public JButton getButtonCadastroPedidoPlaca() {
	return buttonCadastroPedidoPlaca;
    }

    public JButton getButtonCadastroServico() {
	return buttonCadastroServico;
    }

    public JButton getButtonCadastroVeiculo() {
	return buttonCadastroVeiculo;
    }

    public void iniciarController() {
	mainController = MainController.getInstance(this);
	addWindowListener(mainController.new FrameGerenteEventos());
	buttonArquivoSair.addActionListener(mainController.new MenuGerenteEventos());
	buttonArquivoUsuario.addActionListener(mainController.new MenuGerenteEventos());
	buttonArquivoLogin.addActionListener(mainController.new MenuGerenteEventos());
	buttonCadastroVeiculo.addActionListener(mainController.new MenuGerenteEventos());
	buttonCadastroPedidoPlaca.addActionListener(mainController.new MenuGerenteEventos());
	buttonCadastroServico.addActionListener(mainController.new MenuGerenteEventos());
	buttonCadastroCliente.addActionListener(mainController.new MenuGerenteEventos());
	buttonAjudaSobreSistema.addActionListener(mainController.new MenuGerenteEventos());
    }

    private void iniciarGui() {
	setTitle(Sis.getNomeSistema() + " - " + "Principal");
	setIconImage(Imagem.getLogoTipoImage());
	setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	setMinimumSize(Sis.getTamanhoJanela());
	setSize(Sis.getTamanhoJanela());
	setPreferredSize(Sis.getTamanhoJanela());
	setMaximumSize(Sis.getTamanhoJanela());

	Container container = getContentPane();

	container.setLayout(null);
	container.setBackground(Sis.getCorFundo());

	buttonArquivoLogin = new JButton("Login");
	buttonArquivoLogin.setBounds(20, 20, 140, 30);
	container.add(buttonArquivoLogin);

	buttonArquivoUsuario = new JButton("Usuario");
	buttonArquivoUsuario.setBounds(20, 60, 140, 30);
	container.add(buttonArquivoUsuario);

	buttonArquivoSair = new JButton("Sair");
	buttonArquivoSair.setBounds(20, 100, 140, 30);
	container.add(buttonArquivoSair);

	buttonAjudaSobreSistema = new JButton("Sobre o Sistema");
	buttonAjudaSobreSistema.setBounds(20, 140, 140, 30);
	container.add(buttonAjudaSobreSistema);

	buttonCadastroCliente = new JButton("Cliente");
	buttonCadastroCliente.setBounds(20, 180, 140, 30);
	container.add(buttonCadastroCliente);

	buttonCadastroPedidoPlaca = new JButton("Pedido de Placa");
	buttonCadastroPedidoPlaca.setBounds(20, 220, 140, 30);
	container.add(buttonCadastroPedidoPlaca);

	buttonCadastroServico = new JButton("Serviço");
	buttonCadastroServico.setBounds(20, 260, 140, 30);
	container.add(buttonCadastroServico);

	buttonCadastroVeiculo = new JButton("Veículo");
	buttonCadastroVeiculo.setBounds(20, 300, 140, 30);
	container.add(buttonCadastroVeiculo);

    }
}