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
		return MainControl.getMainJanCad();
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
	private JMenu menuCadastroProduto;
	private JMenu menuCadastroVeiculo;
	private JMenu menuEscritorio;
	private JMenu menuFinanceiro;
	private JMenu menuFinanceiroVenda;	
	private JMenuItem menuItemAjudaSobreSistema;	
	private JMenuItem menuItemArquivoLogin;
	private JMenuItem menuItemArquivoLogoff;
	private JMenuItem menuItemArquivoReiniciar;
	private JMenuItem menuItemArquivoSair;
	private JMenuItem menuItemCadastroBanco;
	private JMenuItem menuItemCadastroCartorio;
	private JMenuItem menuItemCadastroCentroCusto;
	private JMenuItem menuItemCadastroCliente;
	private JMenuItem menuItemCadastroContador;
	private JMenuItem menuItemCadastroEmpresa;
	private JMenuItem menuItemCadastroFornecedor;
	private JMenuItem menuItemCadastroProdutoCategoria;
	private JMenuItem menuItemCadastroProdutoMarca;
	private JMenuItem menuItemCadastroProdutoProduto;
	private JMenuItem menuItemCadastroProdutoUnidade;
	private JMenuItem menuItemCadastroSeguradora;
	private JMenuItem menuItemCadastroServico;
	private JMenuItem menuItemCadastroSindicato;
	private JMenuItem menuItemCadastroVeiculoDocumento;
	private JMenuItem menuItemCadastroVeiculoMarca;
	private JMenuItem menuItemCadastroVeiculoModelo;
	private JMenuItem menuItemCadastroVeiculoVeiculo;
	private JMenuItem menuItemEscritorioCompras;
	private JMenuItem menuItemEscritorioDocumento;
	private JMenuItem menuItemEscritorioPedidoPlaca;
	private JMenuItem menuItemEscritorioSeguros;
	private JMenuItem menuItemFinanceiroCaixa;
	private JMenuItem menuItemFinanceiroCheque;
	private JMenuItem menuItemFinanceiroContasPagar;
	private JMenuItem menuItemFinanceiroContasReceber;
	private JMenuItem menuItemFinanceiroVendaProduto;
	private JMenuItem menuItemFinanceiroVendaServico;
	private JMenuItem menuItemSistemaBancoDados;
	private JMenuItem menuItemSistemaUsuario;
	private JMenuItem menuItemSistemaDados;
	private JMenuItem menuItemUtilitarioAgendaContato;
	private JMenuItem menuItemUtilitarioAgendaEvento;
	private JMenuItem menuItemUtilitarioAgendaRecado;
	private JMenuItem menuItemUtilitarioAgendaTipoEvento;
	private JMenuItem menuItemUtilitarioCalculadora;
	private JMenuItem menuItemUtilitarioEditorTexto;
	private JMenu menuSistema;
	private JMenu menuUtilitario;
	private JMenu menuUtilitarioAgenda;

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

	public JMenu getMenuFinanceiro() {
		return menuFinanceiro;
	}

	public JMenuItem getMenuFinanceiroVenda() {
		return menuFinanceiroVenda;
	}
	
	public JMenuItem getMenuItemAjudaSobreSistema() {
		return menuItemAjudaSobreSistema;
	}

	public JMenuItem getMenuItemArquivoLogin() {
		return menuItemArquivoLogin;
	}

	public JMenuItem getMenuItemArquivoLogoff() {
		return menuItemArquivoLogoff;
	}

	public JMenuItem getMenuItemArquivoReiniciar() {
		return menuItemArquivoReiniciar;
	}

	public JMenuItem getMenuItemArquivoSair() {
		return menuItemArquivoSair;
	}

	public JMenuItem getMenuItemCadastroBanco() {
		return menuItemCadastroBanco;
	}

	public JMenuItem getMenuItemCadastroCartorio() {
		return menuItemCadastroCartorio;
	}

	public JMenuItem getMenuItemCadastroCentroCusto() {
		return menuItemCadastroCentroCusto;
	}

	public JMenuItem getMenuItemCadastroCliente() {
		return menuItemCadastroCliente;
	}

	public JMenuItem getMenuItemCadastroContador() {
		return menuItemCadastroContador;
	}

	public JMenuItem getMenuItemCadastroEmpresa() {
		return menuItemCadastroEmpresa;
	}

	public JMenuItem getMenuItemCadastroFinanceiroContasPagar() {
		return menuItemFinanceiroContasPagar;
	}

	public JMenuItem getMenuItemCadastroFinanceiroContasReceber() {
		return menuItemFinanceiroContasReceber;
	}

	public JMenuItem getMenuItemCadastroFornecedor() {
		return menuItemCadastroFornecedor;
	}

	public JMenuItem getMenuItemCadastroProdutoCategoria() {
		return menuItemCadastroProdutoCategoria;
	}

	public JMenuItem getMenuItemCadastroProdutoMarca() {
		return menuItemCadastroProdutoMarca;
	}

	public JMenuItem getMenuItemCadastroProdutoProduto() {
		return menuItemCadastroProdutoProduto;
	}

	public JMenuItem getMenuItemCadastroProdutoUnidade() {
		return menuItemCadastroProdutoUnidade;
	}

	public JMenuItem getMenuItemCadastroSeguradora() {
		return menuItemCadastroSeguradora;
	}

	public JMenuItem getMenuItemCadastroServico() {
		return menuItemCadastroServico;
	}

	public JMenuItem getMenuItemCadastroSindicato() {
		return menuItemCadastroSindicato;
	}

	public JMenu getMenuItemCadastroVeiculo() {
		return menuCadastroVeiculo;
	}

	public JMenuItem getMenuItemCadastroVeiculoDocumento() {
		return menuItemCadastroVeiculoDocumento;
	}

	public JMenuItem getMenuItemCadastroVeiculoMarca() {
		return menuItemCadastroVeiculoMarca;
	}

	public JMenuItem getMenuItemCadastroVeiculoModelo() {
		return menuItemCadastroVeiculoModelo;
	}

	public JMenuItem getMenuItemCadastroVeiculoVeiculo() {
		return menuItemCadastroVeiculoVeiculo;
	}

	public JMenuItem getMenuItemEscritorioCompras() {
		return menuItemEscritorioCompras;
	}

	public JMenuItem getMenuItemEscritorioDocumento() {
		return menuItemEscritorioDocumento;
	}

	public JMenuItem getMenuItemEscritorioPedidoPlaca() {
		return menuItemEscritorioPedidoPlaca;
	}

	public JMenuItem getMenuItemEscritorioSeguros() {
		return menuItemEscritorioSeguros;
	}

	public JMenuItem getMenuItemFinanceiroCaixa() {
		return menuItemFinanceiroCaixa;
	}

	public JMenuItem getMenuItemFinanceiroCheque() {
		return menuItemFinanceiroCheque;
	}

	public JMenuItem getMenuItemFinanceiroContasPagar() {
		return menuItemFinanceiroContasPagar;
	}

	public JMenuItem getMenuItemFinanceiroContasReceber() {
		return menuItemFinanceiroContasReceber;
	}

	public JMenuItem getMenuItemFinanceiroVendaProduto() {
		return menuItemFinanceiroVendaProduto;
	}

	public JMenuItem getMenuItemFinanceiroVendaServico() {
		return menuItemFinanceiroVendaServico;
	}

	public JMenuItem getMenuItemSistemaBancoDados() {
		return menuItemSistemaBancoDados;
	}
	
	public JMenuItem getMenuItemSistemaDados() {
		return menuItemSistemaDados;
	}	

	public JMenuItem getMenuItemSistemaUsuario() {
		return menuItemSistemaUsuario;
	}

	public JMenuItem getMenuItemUtilitarioAgendaContato() {
		return menuItemUtilitarioAgendaContato;
	}

	public JMenuItem getMenuItemUtilitarioAgendaEvento() {
		return menuItemUtilitarioAgendaEvento;
	}

	public JMenuItem getMenuItemUtilitarioAgendaRecado() {
		return menuItemUtilitarioAgendaRecado;
	}

	public JMenuItem getMenuItemUtilitarioAgendaTipoEvento() {
		return menuItemUtilitarioAgendaTipoEvento;
	}

	public JMenuItem getMenuItemUtilitarioCalculadora() {
		return menuItemUtilitarioCalculadora;
	}

	public JMenuItem getMenuItemUtilitarioEditorTexto() {
		return menuItemUtilitarioEditorTexto;
	}

	public JMenu getMenuSistema() {
		return menuSistema;
	}

	public JMenu getMenuUtilitario() {
		return menuUtilitario;
	}

	public JMenu getMenuUtilitarioAgencia() {
		return menuUtilitarioAgenda;
	}

	public JMenu getMenuUtilitarioAgenda() {
		return menuUtilitarioAgenda;
	}

	public void iniciarCont() {
		mainControl = MainControl.getInstance(this);
		Timer timer = new Timer(1280, mainControl.new Relogio());
		timer.setInitialDelay(0);
		timer.setRepeats(true);
		timer.start();
		addWindowListener(mainControl.new FrameGerenteEventos());
		menuItemArquivoLogin.addActionListener(mainControl.new MenuArquivoGerenteEventos());
		menuItemArquivoLogoff.addActionListener(mainControl.new MenuArquivoGerenteEventos());
		menuItemArquivoReiniciar.addActionListener(mainControl.new MenuArquivoGerenteEventos());
		menuItemArquivoSair.addActionListener(mainControl.new MenuArquivoGerenteEventos());
		menuItemCadastroBanco.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemCadastroCentroCusto.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemCadastroCartorio.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemCadastroContador.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemCadastroCliente.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemCadastroEmpresa.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemCadastroSindicato.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemCadastroServico.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemCadastroSeguradora.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemCadastroProdutoCategoria.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemCadastroProdutoMarca.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemCadastroProdutoProduto.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemCadastroProdutoUnidade.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemCadastroFornecedor.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemCadastroVeiculoVeiculo.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemCadastroVeiculoModelo.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemCadastroVeiculoMarca.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemCadastroVeiculoDocumento.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemSistemaUsuario.addActionListener(mainControl.new MenuSistemaGerenteEventos());
		menuItemSistemaBancoDados.addActionListener(mainControl.new MenuSistemaGerenteEventos());
		menuItemSistemaDados.addActionListener(mainControl.new MenuSistemaGerenteEventos());		
		menuItemUtilitarioAgendaEvento.addActionListener(mainControl.new MenuUtilitarioGerenteEventos());
		menuItemUtilitarioAgendaTipoEvento.addActionListener(mainControl.new MenuUtilitarioGerenteEventos());
		menuItemUtilitarioAgendaRecado.addActionListener(mainControl.new MenuUtilitarioGerenteEventos());
		menuItemUtilitarioAgendaContato.addActionListener(mainControl.new MenuUtilitarioGerenteEventos());
		menuItemUtilitarioCalculadora.addActionListener(mainControl.new MenuUtilitarioGerenteEventos());
		menuItemUtilitarioEditorTexto.addActionListener(mainControl.new MenuUtilitarioGerenteEventos());
		menuItemEscritorioDocumento.addActionListener(mainControl.new MenuEscritorioGerenteEventos());
		menuItemEscritorioPedidoPlaca.addActionListener(mainControl.new MenuEscritorioGerenteEventos());
		menuItemEscritorioCompras.addActionListener(mainControl.new MenuEscritorioGerenteEventos());
		menuItemEscritorioSeguros.addActionListener(mainControl.new MenuEscritorioGerenteEventos());
		menuItemFinanceiroCaixa.addActionListener(mainControl.new MenuFinanceiroGerenteEventos());
		menuItemFinanceiroCheque.addActionListener(mainControl.new MenuFinanceiroGerenteEventos());
		menuItemFinanceiroContasPagar.addActionListener(mainControl.new MenuFinanceiroGerenteEventos());
		menuItemFinanceiroContasReceber.addActionListener(mainControl.new MenuFinanceiroGerenteEventos());
		menuItemFinanceiroVendaProduto.addActionListener(mainControl.new MenuFinanceiroGerenteEventos());
		menuItemFinanceiroVendaServico.addActionListener(mainControl.new MenuFinanceiroGerenteEventos());
		menuItemAjudaSobreSistema.addActionListener(mainControl.new MenuAjudaGerenteEventos());
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

		menuItemArquivoLogin = new JMenuItem("Login");
		menuArquivo.add(menuItemArquivoLogin);

		menuItemArquivoLogoff = new JMenuItem("Logoff");
		menuArquivo.add(menuItemArquivoLogoff);

		menuItemArquivoReiniciar = new JMenuItem("Reiniciar");
		menuArquivo.add(menuItemArquivoReiniciar);

		menuItemArquivoSair = new JMenuItem("Sair");
		menuArquivo.addSeparator();
		menuArquivo.add(menuItemArquivoSair);

		menuBar.add(menuArquivo);

		menuCadastro = new JMenu("Cadastro");
		menuCadastro.setMnemonic('C');

		menuItemCadastroBanco = new JMenuItem("Bancos");
		menuCadastro.add(menuItemCadastroBanco);

		menuItemCadastroCentroCusto = new JMenuItem("Centro de Custo");
		menuCadastro.add(menuItemCadastroCentroCusto);

		menuItemCadastroCartorio = new JMenuItem("Cartórios");
		menuCadastro.add(menuItemCadastroCartorio);

		menuItemCadastroContador = new JMenuItem("Dados");
		menuCadastro.add(menuItemCadastroContador);

		menuItemCadastroCliente = new JMenuItem("Clientes");
		menuCadastro.add(menuItemCadastroCliente);

		menuItemCadastroEmpresa = new JMenuItem("Empresas");
		menuCadastro.add(menuItemCadastroEmpresa);

		menuItemCadastroFornecedor = new JMenuItem("Fornecedores");
		menuCadastro.add(menuItemCadastroFornecedor);

		menuCadastroProduto = new JMenu("Produtos");

		menuItemCadastroProdutoProduto = new JMenuItem("Produtos");
		menuCadastroProduto.add(menuItemCadastroProdutoProduto);

		menuCadastroProduto.addSeparator();

		menuItemCadastroProdutoCategoria = new JMenuItem("Categorias");
		menuCadastroProduto.add(menuItemCadastroProdutoCategoria);

		menuItemCadastroProdutoMarca = new JMenuItem("Marcas");
		menuCadastroProduto.add(menuItemCadastroProdutoMarca);

		menuItemCadastroProdutoUnidade = new JMenuItem("Unidade");
		menuCadastroProduto.add(menuItemCadastroProdutoUnidade);

		menuCadastro.add(menuCadastroProduto);

		menuItemCadastroServico = new JMenuItem("Serviços");
		menuCadastro.add(menuItemCadastroServico);

		menuItemCadastroSeguradora = new JMenuItem("Seguradora");
		menuCadastro.add(menuItemCadastroSeguradora);

		menuItemCadastroSindicato = new JMenuItem("Sindicatos");
		menuCadastro.add(menuItemCadastroSindicato);

		menuCadastroVeiculo = new JMenu("Veículos");
		menuCadastro.add(menuCadastroVeiculo);

		menuItemCadastroVeiculoVeiculo = new JMenuItem("Veículos");
		menuCadastroVeiculo.add(menuItemCadastroVeiculoVeiculo);
		menuCadastroVeiculo.addSeparator();

		menuItemCadastroVeiculoMarca = new JMenuItem("Marcas");
		menuCadastroVeiculo.add(menuItemCadastroVeiculoMarca);

		menuItemCadastroVeiculoModelo = new JMenuItem("Modelos");
		menuCadastroVeiculo.add(menuItemCadastroVeiculoModelo);
		menuCadastroVeiculo.addSeparator();

		menuItemCadastroVeiculoDocumento = new JMenuItem("Documentos");
		menuCadastroVeiculo.add(menuItemCadastroVeiculoDocumento);

		menuBar.add(menuCadastro);

		menuEscritorio = new JMenu("Escritório");
		menuEscritorio.setMnemonic('E');

		menuItemEscritorioDocumento = new JMenuItem("Documentos");
		menuEscritorio.add(menuItemEscritorioDocumento);

		menuItemEscritorioPedidoPlaca = new JMenuItem("Pedido de Placa");
		menuEscritorio.add(menuItemEscritorioPedidoPlaca);

		menuItemEscritorioCompras = new JMenuItem("Lista de Compra");
		menuEscritorio.add(menuItemEscritorioCompras);

		menuItemEscritorioSeguros = new JMenuItem("Seguros");
		menuEscritorio.add(menuItemEscritorioSeguros);

		menuBar.add(menuEscritorio);

		menuFinanceiro = new JMenu("Financeiro");
		menuFinanceiro.setMnemonic('F');

		menuItemFinanceiroCaixa = new JMenuItem("Caixa");
		menuFinanceiro.add(menuItemFinanceiroCaixa);
		menuFinanceiro.addSeparator();

		menuItemFinanceiroCheque = new JMenuItem("Cheques");
		menuFinanceiro.add(menuItemFinanceiroCheque);

		menuItemFinanceiroContasPagar = new JMenuItem("Contas a Pagar");
		menuFinanceiro.add(menuItemFinanceiroContasPagar);

		menuItemFinanceiroContasReceber = new JMenuItem("Contas a Receber");
		menuFinanceiro.add(menuItemFinanceiroContasReceber);
		menuFinanceiro.addSeparator();

		menuFinanceiroVenda = new JMenu("Vendas");
		menuFinanceiro.add(menuFinanceiroVenda);

		menuItemFinanceiroVendaProduto = new JMenuItem("Produtos");
		menuFinanceiroVenda.add(menuItemFinanceiroVendaProduto);

		menuItemFinanceiroVendaServico = new JMenuItem("Serviços");
		menuFinanceiroVenda.add(menuItemFinanceiroVendaServico);

		menuBar.add(menuFinanceiro);

		menuUtilitario = new JMenu("Utilitários");
		menuUtilitario.setMnemonic('U');

		menuUtilitarioAgenda = new JMenu("Agenda");
		menuUtilitario.add(menuUtilitarioAgenda);

		menuItemUtilitarioAgendaContato = new JMenuItem("Contato");
		menuUtilitarioAgenda.add(menuItemUtilitarioAgendaContato);

		menuItemUtilitarioAgendaEvento = new JMenuItem("Evento");
		menuUtilitarioAgenda.add(menuItemUtilitarioAgendaEvento);

		menuItemUtilitarioAgendaTipoEvento = new JMenuItem("Tipo de Evento");
		menuUtilitarioAgenda.add(menuItemUtilitarioAgendaTipoEvento);

		menuItemUtilitarioAgendaRecado = new JMenuItem("Recado");
		menuUtilitarioAgenda.add(menuItemUtilitarioAgendaRecado);

		menuItemUtilitarioCalculadora = new JMenuItem("Calculadora");
		menuUtilitario.add(menuItemUtilitarioCalculadora);

		menuItemUtilitarioEditorTexto = new JMenuItem("Editor de Texto");
		menuUtilitario.add(menuItemUtilitarioEditorTexto);

		menuBar.add(menuUtilitario);

		menuSistema = new JMenu("Sistema");
		menuSistema.setMnemonic('S');

		menuItemSistemaBancoDados = new JMenuItem("Banco de Dados");
		menuSistema.add(menuItemSistemaBancoDados);

		menuItemSistemaUsuario = new JMenuItem("Usuários");
		menuSistema.add(menuItemSistemaUsuario);
		menuBar.add(menuSistema);
		
		menuItemSistemaDados = new JMenuItem("Dados");
		menuSistema.add(menuItemSistemaDados);
		menuBar.add(menuSistema);		

		menuAjuda = new JMenu("Ajuda");
		menuAjuda.setMnemonic('u');
	
		menuItemAjudaSobreSistema = new JMenuItem("Sobre o Sistema");
		menuAjuda.add(menuItemAjudaSobreSistema);

		menuBar.add(menuAjuda);

		setJMenuBar(menuBar);
	}
}