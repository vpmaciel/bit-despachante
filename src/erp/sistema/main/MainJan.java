package erp.sistema.main;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Timer;
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
	private JMenuItem menuItemArquivoSair;	
	private JMenuItem menuItemCadastroPessoa;
	private JMenuItem menuItemCadastroEvento;
	private JMenuItem menuItemCadastroEmpresa;
	private JMenuItem menuItemCadastroAtividade;
	private JMenuItem menuItemCadastroConta;
	
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

	public JMenuItem getMenuItemArquivoSair() {
		return menuItemArquivoSair;
	}	

	public JMenuItem getMenuItemCadastroPessoa() {
		return menuItemCadastroPessoa;
	}

	public JMenuItem getMenuItemCadastroEvento() {
		return menuItemCadastroEvento;
	}

	public JMenuItem getMenuItemCadastroEmpresa() {
		return menuItemCadastroEmpresa;
	}
	
	public JMenuItem getMenuItemCadastroConta() {
		return menuItemCadastroConta;
	}

	public void iniciarCont() {
		mainControl = MainControl.getInstance(this);		
		addWindowListener(mainControl.new FrameGerenteEventos());
		menuItemArquivoSair.addActionListener(mainControl.new MenuGerenteEventos());
		menuItemCadastroEvento.addActionListener(mainControl.new MenuGerenteEventos());
		menuItemCadastroConta.addActionListener(mainControl.new MenuGerenteEventos());
		menuItemCadastroEmpresa.addActionListener(mainControl.new MenuGerenteEventos());
		menuItemCadastroPessoa.addActionListener(mainControl.new MenuGerenteEventos());
		menuItemAjudaSobreSistema.addActionListener(mainControl.new MenuGerenteEventos());
	}

	private void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setMinimumSize(Sis.getTamanhoJanela());
		setSize(Sis.getTamanhoJanela());
		setPreferredSize(Sis.getTamanhoJanela());
		setMaximumSize(Sis.getTamanhoJanela());

		menuArquivo = new JMenu("Arquivo");
		menuArquivo.setMnemonic('A');

		menuItemArquivoSair = new JMenuItem("Sair");
		
		menuArquivo.add(menuItemArquivoSair);

		menuBar.add(menuArquivo);

		menuCadastro = new JMenu("Cadastro");
		menuCadastro.setMnemonic('C');
		
		menuItemCadastroAtividade = new JMenuItem("Atividade");
		menuCadastro.add(menuItemCadastroAtividade);		
				
		menuItemCadastroConta = new JMenuItem("Conta");
		menuCadastro.add(menuItemCadastroConta);
		
		menuItemCadastroEmpresa = new JMenuItem("Empresa");
		menuCadastro.add(menuItemCadastroEmpresa);
		
		menuItemCadastroEvento = new JMenuItem("Evento");
		menuCadastro.add(menuItemCadastroEvento);

		menuItemCadastroPessoa = new JMenuItem("Pessoa");
		menuCadastro.add(menuItemCadastroPessoa);					
		
		menuBar.add(menuCadastro);

		menuAjuda = new JMenu("Ajuda");
		menuAjuda.setMnemonic('u');

		menuItemAjudaSobreSistema = new JMenuItem("Sobre o Sistema");
		menuAjuda.add(menuItemAjudaSobreSistema);

		menuBar.add(menuAjuda);

		setJMenuBar(menuBar);
	}
}