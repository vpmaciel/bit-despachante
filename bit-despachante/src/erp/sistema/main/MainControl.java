package erp.sistema.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import erp.arquitetura.Data;
import erp.arquitetura.Sis;
import erp.arquitetura.gui.Msg;
import erp.escritorio.documento.DocumentoJanCad;
import erp.escritorio.documento.DocumentoJanPesq;
import erp.escritorio.produto.compra.lista.CompraProdutoJanCad;
import erp.escritorio.produto.compra.lista.CompraProdutoJanPesq;
import erp.escritorio.veiculo.pedido.placa.PedidoPlacaJanCad;
import erp.escritorio.veiculo.pedido.placa.PedidoPlacaJanPesq;
import erp.escritorio.veiculo.seguro.SeguroJanCad;
import erp.escritorio.veiculo.seguro.SeguroJanPesq;
import erp.financeiro.caixa.CaixaJanCad;
import erp.financeiro.caixa.CaixaJanPesq;
import erp.financeiro.cheque.ChequeJanCad;
import erp.financeiro.cheque.ChequeJanPesq;
import erp.financeiro.contaspagar.ContasPagarJanCad;
import erp.financeiro.contaspagar.ContasPagarJanPesq;
import erp.financeiro.contasreceber.ContasReceberJanCad;
import erp.financeiro.contasreceber.ContasReceberJanPesq;
import erp.financeiro.vendas.produto.VendaProdutoJanCad;
import erp.financeiro.vendas.produto.VendaProdutoJanPesq;
import erp.financeiro.vendas.servico.VendaServicoJanCad;
import erp.financeiro.vendas.servico.VendaServicoJanPesq;
import erp.negocio.banco.BancoJanCad;
import erp.negocio.banco.BancoJanPesq;
import erp.negocio.cartorio.CartorioJanCad;
import erp.negocio.cartorio.CartorioJanPesq;
import erp.negocio.centrocusto.CentroCustoJanCad;
import erp.negocio.centrocusto.CentroCustoJanPesq;
import erp.negocio.cliente.ClienteJanCad;
import erp.negocio.cliente.ClienteJanPesq;
import erp.negocio.contador.ContadorJanCad;
import erp.negocio.contador.ContadorJanPesq;
import erp.negocio.empresa.EmpresaJanCad;
import erp.negocio.empresa.EmpresaJanPesq;
import erp.negocio.fornecedor.FornecedorJanCad;
import erp.negocio.fornecedor.FornecedorJanPesq;
import erp.negocio.produto.ProdutoJanCad;
import erp.negocio.produto.ProdutoJanPesq;
import erp.negocio.produto.categoria.ProdutoCategoriaJanCad;
import erp.negocio.produto.categoria.ProdutoCategoriaJanPesq;
import erp.negocio.produto.marca.ProdutoMarcaJanCad;
import erp.negocio.produto.marca.ProdutoMarcaJanPesq;
import erp.negocio.produto.unidade.ProdutoUnidadeJanCad;
import erp.negocio.produto.unidade.ProdutoUnidadeJanPesq;
import erp.negocio.seguradora.SeguradoraJanCad;
import erp.negocio.seguradora.SeguradoraJanPesq;
import erp.negocio.servico.ServicoJanCad;
import erp.negocio.servico.ServicoJanPesq;
import erp.negocio.sindicato.SindicatoJanCad;
import erp.negocio.sindicato.SindicatoJanPesq;
import erp.negocio.veiculo.VeiculoJanCad;
import erp.negocio.veiculo.VeiculoJanPesq;
import erp.negocio.veiculo.documento.VeiculoDocumentoJanCad;
import erp.negocio.veiculo.documento.VeiculoDocumentoJanPesq;
import erp.negocio.veiculo.marca.VeiculoMarcaJanCad;
import erp.negocio.veiculo.marca.VeiculoMarcaJanPesq;
import erp.negocio.veiculo.modelo.VeiculoModeloJanCad;
import erp.negocio.veiculo.modelo.VeiculoModeloJanPesq;
import erp.sistema.dados.DadosJanCad;
import erp.sistema.login.LoginJan;
import erp.sistema.usuario.UsuarioJanCad;
import erp.sistema.usuario.UsuarioJanPesq;
import erp.utilitarios.agenda.contato.ContatoJanCad;
import erp.utilitarios.agenda.contato.ContatoJanPesq;
import erp.utilitarios.agenda.evento.EventoJanCad;
import erp.utilitarios.agenda.evento.EventoJanPesq;
import erp.utilitarios.agenda.evento.tipoevento.TipoEventoJanCad;
import erp.utilitarios.agenda.evento.tipoevento.TipoEventoJanPesq;
import erp.utilitarios.agenda.recado.RecadoJanCad;
import erp.utilitarios.agenda.recado.RecadoJanPesq;
import erp.utilitarios.calculadora.CalculadoraJanCad;
import erp.utilitarios.editor.EditorTextoJanCad;

public final class MainControl {

	public class FrameGerenteEventos extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {

		}

		@Override
		public void windowClosing(WindowEvent e) {
			if (Msg.confirmarSairDoSistema() == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
		}

		@Override
		public void windowOpened(WindowEvent e) {
		}
	}

	public class MenuAjudaGerenteEventos implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			if (Sis.getUsuario() == null) {
				Msg.avisoUsuarioNaoLogado();
				return;
			}
			if (actionEvent.getSource() == mainJan.getMenuItemAjudaSobreSistema()) {
				Msg.ajuda();
			}
		}
	}

	public class MenuArquivoGerenteEventos implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if (actionEvent.getSource() == mainJan.getMenuItemArquivoSair()) {
				if (Msg.confirmarSairDoSistema() == 0) {
					System.exit(0);
				}
			}
			if (actionEvent.getSource() == mainJan.getMenuItemArquivoLogin()) {
				mostrarFrame(loginJan);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemArquivoLogoff()) {
				Sis.setUsuario(null);
				Msg.avisoUsuarioNaoLogado();
			}
			if (actionEvent.getSource() == mainJan.getMenuItemArquivoReiniciar()) {
				@SuppressWarnings("rawtypes")
				Class cls = Executavel.class;
				ProtectionDomain pDomain = cls.getProtectionDomain();
				CodeSource cSource = pDomain.getCodeSource();
				URL loc = cSource.getLocation();

				String comando = "java -jar " + loc.toString().substring(5);

				try {
					Runtime.getRuntime().exec(comando);
				} catch (IOException MensagemdeErro) {
					System.out.println(MensagemdeErro);
				}
				System.exit(0);
			}
		}
	}

	public class MenuCadastroGerenteEventos implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if (Sis.getUsuario() == null) {
				Msg.avisoUsuarioNaoLogado();
				return;
			}
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroCentroCusto()) {
				mostrarFrame(centroCustoJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroBanco()) {
				mostrarFrame(bancoJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroCartorio()) {
				mostrarFrame(cartorioJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroContador()) {
				mostrarFrame(contadorJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroCliente()) {
				mostrarFrame(clienteJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroEmpresa()) {
				mostrarFrame(empresaJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroSindicato()) {
				mostrarFrame(sindicatoJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroFornecedor()) {
				mostrarFrame(fornecedorJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroVeiculoVeiculo()) {
				mostrarFrame(veiculoJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroVeiculoModelo()) {
				mostrarFrame(veiculoModeloJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroVeiculoMarca()) {
				mostrarFrame(veiculoMarcaJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroVeiculoDocumento()) {
				mostrarFrame(veiculoDocumentoJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroProdutoCategoria()) {
				mostrarFrame(produtoCategoriaJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroProdutoMarca()) {
				mostrarFrame(produtoMarcaJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroProdutoProduto()) {
				mostrarFrame(produtoJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroProdutoUnidade()) {
				mostrarFrame(produtoUnidadeJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroServico()) {
				mostrarFrame(servicoJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroProdutoUnidade()) {
				mostrarFrame(produtoUnidadeJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemCadastroSeguradora()) {
				mostrarFrame(seguradoraJanCad);
			}
		}
	}

	public class MenuEscritorioGerenteEventos implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if (Sis.getUsuario() == null) {
				Msg.avisoUsuarioNaoLogado();
				return;
			}
			if (actionEvent.getSource() == mainJan.getMenuItemEscritorioCompras()) {
				mostrarFrame(compraProdutoJanCad);
			}

			if (actionEvent.getSource() == mainJan.getMenuItemEscritorioDocumento()) {
				mostrarFrame(documentoJanCad);
			}

			if (actionEvent.getSource() == mainJan.getMenuItemEscritorioPedidoPlaca()) {
				mostrarFrame(pedidoPlacaJanCad);
			}

			if (actionEvent.getSource() == mainJan.getMenuItemEscritorioSeguros()) {
				mostrarFrame(seguroJanCad);
			}
		}
	}

	public class MenuFinanceiroGerenteEventos implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if (Sis.getUsuario() == null) {
				Msg.avisoUsuarioNaoLogado();
				return;
			}

			if (actionEvent.getSource() == mainJan.getMenuItemFinanceiroCaixa()) {
				mostrarFrame(caixaJanCad);
			}

			if (actionEvent.getSource() == mainJan.getMenuItemFinanceiroContasPagar()) {
				mostrarFrame(contasPagarJanCad);
			}

			if (actionEvent.getSource() == mainJan.getMenuItemFinanceiroContasReceber()) {
				mostrarFrame(contasReceberJanCad);
			}

			if (actionEvent.getSource() == mainJan.getMenuItemFinanceiroVendaProduto()) {
				mostrarFrame(vendaProdutoJanCad);
			}

			if (actionEvent.getSource() == mainJan.getMenuItemFinanceiroVendaServico()) {
				mostrarFrame(vendaServicoJanCad);
			}

			if (actionEvent.getSource() == mainJan.getMenuItemFinanceiroCheque()) {
				mostrarFrame(chequeJanCad);
			}

		}
	}

	public class MenuSistemaGerenteEventos implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if (Sis.getUsuario() == null) {
				Msg.avisoUsuarioNaoLogado();
				return;
			}
			if (actionEvent.getSource() == mainJan.getMenuItemSistemaUsuario()) {
				mostrarFrame(usuarioJanCad);
			}
			
			if (actionEvent.getSource() == mainJan.getMenuItemSistemaDados()) {
				mostrarFrame(dadosJanCad);				
			}		
		}
	}

	public class MenuUtilitarioGerenteEventos implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if (Sis.getUsuario() == null) {
				Msg.avisoUsuarioNaoLogado();
				return;
			}
			if (actionEvent.getSource() == mainJan.getMenuItemUtilitarioAgendaEvento()) {
				mostrarFrame(eventoJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemUtilitarioAgendaTipoEvento()) {
				mostrarFrame(tipoEventoJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemUtilitarioAgendaRecado()) {
				mostrarFrame(recadoJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemUtilitarioAgendaContato()) {
				mostrarFrame(contatoJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemUtilitarioCalculadora()) {
				mostrarFrame(calculadoraJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemUtilitarioEditorTexto()) {
				mostrarFrame(editorTextoJanCad);
			}
		}
	}

	public class Relogio implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			MainControl.getAgendaContatoJanCad().setTitle(getTitulo("CONTATO"));
			MainControl.getAgendaContatoJanPesq().setTitle(getTitulo("CONTATO"));
			MainControl.getAgendaEventoJanCad().setTitle(getTitulo("EVENTO"));
			MainControl.getAgendaEventoJanPesq().setTitle(getTitulo("EVENTO"));
			MainControl.getAgendaTipoEventoJanCad().setTitle(getTitulo("TIPO DE EVENTO"));
			MainControl.getAgendaTipoEventoJanPesq().setTitle(getTitulo("TIPO DE EVENTO"));
			MainControl.getAgendaRecadoJanCad().setTitle(getTitulo("RECADO"));
			MainControl.getAgendaRecadoJanPesq().setTitle(getTitulo("RECADO"));
			MainControl.getBancoJanCad().setTitle(getTitulo("BANCO"));
			MainControl.getBancoJanPesq().setTitle(getTitulo("BANCO"));
			MainControl.getCalculadoraJanCad().setTitle(getTitulo("CALCULADORA"));
			MainControl.getCartorioJanCad().setTitle(getTitulo("CARTÓRIO"));
			MainControl.getCartorioJanPesq().setTitle(getTitulo("CARTÓRIO"));
			MainControl.getCentroCustoJanCad().setTitle(getTitulo("CENTRO DE CUSTO"));
			MainControl.getCentroCustoJanPesq().setTitle(getTitulo("CENTRO DE CUSTO"));
			MainControl.getClienteJanCad().setTitle(getTitulo("CLIENTE"));
			MainControl.getClienteJanPesq().setTitle(getTitulo("CLIENTE"));
			MainControl.getContadorJanCad().setTitle(getTitulo("CONTADOR"));
			MainControl.getContadorJanPesq().setTitle(getTitulo("CONTADOR"));
			MainControl.getEditorTextoJanCad().setTitle(getTitulo("EDITOR DE TEXTO"));
			MainControl.getEmpresaJanCad().setTitle(getTitulo("EMPRESA"));
			MainControl.getEmpresaJanPesq().setTitle(getTitulo("EMPRESA"));
			MainControl.getFornecedorJanCad().setTitle(getTitulo("FORNECEDOR"));
			MainControl.getFornecedorJanPesq().setTitle(getTitulo("FORNECEDOR"));
			MainControl.getLoginJan().setTitle(getTitulo("LOGIN"));
			MainControl.getMainJanCad().setTitle(getTitulo("PRINCIPAL"));
			MainControl.getSindicatoJanCad().setTitle(getTitulo("SINDICATO"));
			MainControl.getSindicatoJanPesq().setTitle(getTitulo("SINDICATO"));
			MainControl.getUsuarioJanCad().setTitle(getTitulo("USUÁRIO"));
			MainControl.getUsuarioJanPesq().setTitle(getTitulo("USUÁRIO"));
			MainControl.getVeiculoJanCad().setTitle(getTitulo("VEÍCULO"));
			MainControl.getVeiculoJanPesq().setTitle(getTitulo("VEÍCULO"));
			MainControl.getVeiculoMarcaJanCad().setTitle(getTitulo("MARCA DE VEÍCULO"));
			MainControl.getVeiculoMarcaJanPesq().setTitle(getTitulo("MARCA DE VEÍCULO"));
			MainControl.getVeiculoModeloJanCad().setTitle(getTitulo("MODELO DE VEÍCULO"));
			MainControl.getVeiculoModeloJanPesq().setTitle(getTitulo("MODELO DE VEÍCULO"));
			MainControl.getVeiculoDocumentoJanCad().setTitle(getTitulo("DOCUMENTO DE VEÍCULO"));
			MainControl.getVeiculoDocumentoJanPesq().setTitle(getTitulo("DOCUMENTO DE VEÍCULO"));
			MainControl.getServicoJanCad().setTitle(getTitulo("SERVIÇO"));
			MainControl.getServicoJanPesq().setTitle(getTitulo("SERVIÇO"));
			MainControl.getProdutoJanCad().setTitle(getTitulo("PRODUTO"));
			MainControl.getProdutoJanPesq().setTitle(getTitulo("PRODUTO"));
			MainControl.getProdutoCategoriaJanCad().setTitle(getTitulo("PRODUTO - CATEGORIA"));
			MainControl.getProdutoCategoriaJanPesq().setTitle(getTitulo("PRODUTO - CATEGORIA"));
			MainControl.getProdutoUnidadeJanCad().setTitle(getTitulo("PRODUTO - UNIDADE"));
			MainControl.getProdutoUnidadeJanPesq().setTitle(getTitulo("PRODUTO - UNIDADE"));
			MainControl.getProdutoMarcaJanCad().setTitle(getTitulo("PRODUTO - MARCA"));
			MainControl.getProdutoMarcaJanPesq().setTitle(getTitulo("PRODUTO - MARCA"));
			MainControl.getSeguradoraJanCad().setTitle(getTitulo("SEGURADORA"));
			MainControl.getSeguradoraJanPesq().setTitle(getTitulo("SEGURADORA"));
			MainControl.getServicoJanCad().setTitle(getTitulo("SERVIÇO"));
			MainControl.getServicoJanPesq().setTitle(getTitulo("SERVIÇO"));
			MainControl.getDocumentoJanCad().setTitle(getTitulo("DOCUMENTO"));
			MainControl.getDocumentoJanPesq().setTitle(getTitulo("DOCUMENTO"));
			MainControl.getCompraProdutoJanCad().setTitle(getTitulo("COMPRAS DE PRODUTOS"));
			MainControl.getCompraProdutoJanPesq().setTitle(getTitulo("COMPRAS DE PRODUTOS"));
			MainControl.getPedidoPlacaJanCad().setTitle(getTitulo("PEDIDO DE PLACA"));
			MainControl.getPedidoPlacaJanPesq().setTitle(getTitulo("PEDIDO DE PLACA"));
			MainControl.getSeguroJanCad().setTitle(getTitulo("SEGURO"));
			MainControl.getSeguroJanPesq().setTitle(getTitulo("SEGURO"));
			MainControl.getVendaServicoJanCad().setTitle(getTitulo("VENDAS - SEVIÇOS"));
			MainControl.getVendaServicoJanPesq().setTitle(getTitulo("VENDAS - SERVIÇOS"));
			MainControl.getVendaProdutoJanCad().setTitle(getTitulo("VENDAS - PRODUTOS"));
			MainControl.getVendaProdutoJanPesq().setTitle(getTitulo("VENDAS - PRODUTOS"));
			MainControl.getContasReceberJanCad().setTitle(getTitulo("CONTAS A RECEBER"));
			MainControl.getContasReceberJanPesq().setTitle(getTitulo("CONTAS A RECEBER"));
			MainControl.getContasPagarJanCad().setTitle(getTitulo("CONTAS A PAGAR"));
			MainControl.getContasPagarJanPesq().setTitle(getTitulo("CONTAS A PAGAR"));
			MainControl.getCaixaJanCad().setTitle(getTitulo("CAIXA"));
			MainControl.getCaixaJanPesq().setTitle(getTitulo("CAIXA"));
			MainControl.getChequeJanCad().setTitle(getTitulo("CHEQUES"));
			MainControl.getChequeJanPesq().setTitle(getTitulo("CHEQUES"));
			MainControl.getSeguradoraJanCad().setTitle(getTitulo("SEGURADORA"));
			MainControl.getSeguradoraJanPesq().setTitle(getTitulo("SEGURADORA"));
			MainControl.getDadosJan().setTitle(getTitulo("DADOS DO SISTEMA"));
		}
	}

	private static BancoJanCad bancoJanCad;

	private static BancoJanPesq bancoJanPesq;
	private static CaixaJanCad caixaJanCad;
	private static CaixaJanPesq caixaJanPesq;
	private static CalculadoraJanCad calculadoraJanCad;
	private static CartorioJanCad cartorioJanCad;
	private static CartorioJanPesq cartorioJanPesq;
	private static CentroCustoJanCad centroCustoJanCad;
	private static CentroCustoJanPesq centroCustoJanPesq;
	private static ChequeJanCad chequeJanCad;
	private static ChequeJanPesq chequeJanPesq;
	private static ClienteJanCad clienteJanCad;
	private static ClienteJanPesq clienteJanPesq;
	private static CompraProdutoJanCad compraProdutoJanCad;
	private static CompraProdutoJanPesq compraProdutoJanPesq;
	private static ContadorJanCad contadorJanCad;
	private static ContadorJanPesq contadorJanPesq;
	private static ContasPagarJanCad contasPagarJanCad;
	private static ContasPagarJanPesq contasPagarJanPesq;
	private static ContasReceberJanCad contasReceberJanCad;
	private static ContasReceberJanPesq contasReceberJanPesq;
	private static ContatoJanCad contatoJanCad;
	private static ContatoJanPesq contatoJanPesq;
	private static DadosJanCad dadosJanCad;
	private static DocumentoJanCad documentoJanCad;
	private static DocumentoJanPesq documentoJanPesq;
	private static EditorTextoJanCad editorTextoJanCad;
	private static EmpresaJanCad empresaJanCad;
	private static EmpresaJanPesq empresaJanPesq;
	private static EventoJanCad eventoJanCad;
	private static EventoJanPesq eventoJanPesq;
	private static FornecedorJanCad fornecedorJanCad;
	private static FornecedorJanPesq fornecedorJanPesq;
	private static LoginJan loginJan;
	private static MainControl mainControl;
	private static MainJan mainJan;
	private static PedidoPlacaJanCad pedidoPlacaJanCad;
	private static PedidoPlacaJanPesq pedidoPlacaJanPesq;
	private static ProdutoCategoriaJanCad produtoCategoriaJanCad;
	private static ProdutoCategoriaJanPesq produtoCategoriaJanPesq;
	private static ProdutoJanCad produtoJanCad;
	private static ProdutoJanPesq produtoJanPesq;
	private static ProdutoMarcaJanCad produtoMarcaJanCad;
	private static ProdutoMarcaJanPesq produtoMarcaJanPesq;
	private static ProdutoUnidadeJanCad produtoUnidadeJanCad;
	private static ProdutoUnidadeJanPesq produtoUnidadeJanPesq;
	private static RecadoJanCad recadoJanCad;
	private static RecadoJanPesq recadoJanPesq;
	private static SeguradoraJanCad seguradoraJanCad;
	private static SeguradoraJanPesq seguradoraJanPesq;
	private static SeguroJanCad seguroJanCad;
	private static SeguroJanPesq seguroJanPesq;
	private static ServicoJanCad servicoJanCad;
	private static ServicoJanPesq servicoJanPesq;
	private static SindicatoJanCad sindicatoJanCad;
	private static SindicatoJanPesq sindicatoJanPesq;
	private static TipoEventoJanCad tipoEventoJanCad;
	private static TipoEventoJanPesq tipoEventoJanPesq;
	private static int totalPrincipalCont;
	private static UsuarioJanCad usuarioJanCad;
	private static UsuarioJanPesq usuarioJanPesq;
	private static VeiculoDocumentoJanCad veiculoDocumentoJanCad;
	private static VeiculoDocumentoJanPesq veiculoDocumentoJanPesq;
	private static VeiculoJanCad veiculoJanCad;
	private static VeiculoJanPesq veiculoJanPesq;
	private static VeiculoMarcaJanCad veiculoMarcaJanCad;
	private static VeiculoMarcaJanPesq veiculoMarcaJanPesq;
	private static VeiculoModeloJanCad veiculoModeloJanCad;
	private static VeiculoModeloJanPesq veiculoModeloJanPesq;
	private static VendaProdutoJanCad vendaProdutoJanCad;
	private static VendaProdutoJanPesq vendaProdutoJanPesq;
	private static VendaServicoJanCad vendaServicoJanCad;
	private static VendaServicoJanPesq vendaServicoJanPesq;
	static {
		totalPrincipalCont = 0;
	}

	public static ContatoJanCad getAgendaContatoJanCad() {
		return contatoJanCad;
	}

	public static ContatoJanPesq getAgendaContatoJanPesq() {
		return contatoJanPesq;
	}

	public static EventoJanCad getAgendaEventoJanCad() {
		return eventoJanCad;
	}

	public static EventoJanPesq getAgendaEventoJanPesq() {
		return eventoJanPesq;
	}

	public static RecadoJanCad getAgendaRecadoJanCad() {
		return recadoJanCad;
	}

	public static RecadoJanPesq getAgendaRecadoJanPesq() {
		return recadoJanPesq;
	}

	public static TipoEventoJanCad getAgendaTipoEventoJanCad() {
		return tipoEventoJanCad;
	}

	public static TipoEventoJanPesq getAgendaTipoEventoJanPesq() {
		return tipoEventoJanPesq;
	}

	public static BancoJanCad getBancoJanCad() {
		return bancoJanCad;
	}

	public static BancoJanPesq getBancoJanPesq() {
		return bancoJanPesq;
	}

	public static CaixaJanCad getCaixaJanCad() {
		return caixaJanCad;
	}

	public static CaixaJanPesq getCaixaJanPesq() {
		return caixaJanPesq;
	}

	public static CalculadoraJanCad getCalculadoraJanCad() {
		return calculadoraJanCad;
	}

	public static CartorioJanCad getCartorioJanCad() {
		return cartorioJanCad;
	}

	public static CartorioJanPesq getCartorioJanPesq() {
		return cartorioJanPesq;
	}

	public static CentroCustoJanCad getCentroCustoJanCad() {
		return centroCustoJanCad;
	}

	public static CentroCustoJanPesq getCentroCustoJanPesq() {
		return centroCustoJanPesq;
	}

	public static ChequeJanCad getChequeJanCad() {
		return chequeJanCad;
	}

	public static ChequeJanPesq getChequeJanPesq() {
		return chequeJanPesq;
	}

	public static ClienteJanCad getClienteJanCad() {
		return clienteJanCad;
	}

	public static ClienteJanPesq getClienteJanPesq() {
		return clienteJanPesq;
	}

	public static CompraProdutoJanCad getCompraProdutoJanCad() {
		return compraProdutoJanCad;
	}

	public static CompraProdutoJanPesq getCompraProdutoJanPesq() {
		return compraProdutoJanPesq;
	}

	public static ContadorJanCad getContadorJanCad() {
		return contadorJanCad;
	}

	public static ContadorJanPesq getContadorJanPesq() {
		return contadorJanPesq;
	}

	public static ContasPagarJanCad getContasPagarJanCad() {
		return contasPagarJanCad;
	}

	public static ContasPagarJanPesq getContasPagarJanPesq() {
		return contasPagarJanPesq;
	}

	public static ContasReceberJanCad getContasReceberJanCad() {
		return contasReceberJanCad;
	}

	public static ContasReceberJanPesq getContasReceberJanPesq() {
		return contasReceberJanPesq;
	}

	public static DadosJanCad getDadosJan() {
		return dadosJanCad;
	}

	public static DocumentoJanCad getDocumentoJanCad() {
		return documentoJanCad;
	}

	public static DocumentoJanPesq getDocumentoJanPesq() {
		return documentoJanPesq;
	}

	public static EditorTextoJanCad getEditorTextoJanCad() {
		return editorTextoJanCad;
	}

	public static EmpresaJanCad getEmpresaJanCad() {
		return empresaJanCad;
	}

	public static EmpresaJanPesq getEmpresaJanPesq() {
		return empresaJanPesq;
	}

	public static FornecedorJanCad getFornecedorJanCad() {
		return fornecedorJanCad;
	}

	public static FornecedorJanPesq getFornecedorJanPesq() {
		return fornecedorJanPesq;
	}

	public static synchronized MainControl getInstance(MainJan mainJan) {
		if (totalPrincipalCont > 1) {
			JOptionPane.showMessageDialog(null, "Foi instanciado mais de uma Objeto:" + Executavel.class);
			System.exit(0);
		}
		if (mainControl == null) {
			++totalPrincipalCont;
			return new MainControl(mainJan);
		}
		return mainControl;
	}

	public static LoginJan getLoginJan() {
		return loginJan;
	}

	public static MainJan getMainJanCad() {
		return mainJan;
	}

	public static PedidoPlacaJanCad getPedidoPlacaJanCad() {
		return pedidoPlacaJanCad;
	}

	public static PedidoPlacaJanPesq getPedidoPlacaJanPesq() {
		return pedidoPlacaJanPesq;
	}

	public static ProdutoCategoriaJanCad getProdutoCategoriaJanCad() {
		return produtoCategoriaJanCad;
	}

	public static ProdutoCategoriaJanPesq getProdutoCategoriaJanPesq() {
		return produtoCategoriaJanPesq;
	}

	public static ProdutoJanCad getProdutoJanCad() {
		return produtoJanCad;
	}

	public static ProdutoJanPesq getProdutoJanPesq() {
		return produtoJanPesq;
	}

	public static ProdutoMarcaJanCad getProdutoMarcaJanCad() {
		return produtoMarcaJanCad;
	}

	public static ProdutoMarcaJanPesq getProdutoMarcaJanPesq() {
		return produtoMarcaJanPesq;
	}

	public static ProdutoUnidadeJanCad getProdutoUnidadeJanCad() {
		return produtoUnidadeJanCad;
	}

	public static ProdutoUnidadeJanPesq getProdutoUnidadeJanPesq() {
		return produtoUnidadeJanPesq;
	}

	public static SeguradoraJanCad getSeguradoraJanCad() {
		return seguradoraJanCad;
	}

	public static SeguradoraJanPesq getSeguradoraJanPesq() {
		return seguradoraJanPesq;
	}

	public static SeguroJanCad getSeguroJanCad() {
		return seguroJanCad;
	}

	public static SeguroJanPesq getSeguroJanPesq() {
		return seguroJanPesq;
	}

	public static ServicoJanCad getServicoJanCad() {
		return servicoJanCad;
	}

	public static ServicoJanPesq getServicoJanPesq() {
		return servicoJanPesq;
	}

	public static SindicatoJanCad getSindicatoJanCad() {
		return sindicatoJanCad;
	}

	public static SindicatoJanPesq getSindicatoJanPesq() {
		return sindicatoJanPesq;
	}

	public static UsuarioJanCad getUsuarioJanCad() {
		return usuarioJanCad;
	}

	public static UsuarioJanPesq getUsuarioJanPesq() {
		return usuarioJanPesq;
	}

	public static VeiculoDocumentoJanCad getVeiculoDocumentoJanCad() {
		return veiculoDocumentoJanCad;
	}

	public static VeiculoDocumentoJanPesq getVeiculoDocumentoJanPesq() {
		return veiculoDocumentoJanPesq;
	}

	public static VeiculoJanCad getVeiculoJanCad() {
		return veiculoJanCad;
	}

	public static VeiculoJanPesq getVeiculoJanPesq() {
		return veiculoJanPesq;
	}

	public static VeiculoMarcaJanCad getVeiculoMarcaJanCad() {
		return veiculoMarcaJanCad;
	}

	public static VeiculoMarcaJanPesq getVeiculoMarcaJanPesq() {
		return veiculoMarcaJanPesq;
	}

	public static VeiculoModeloJanCad getVeiculoModeloJanCad() {
		return veiculoModeloJanCad;
	}

	public static VeiculoModeloJanPesq getVeiculoModeloJanPesq() {
		return veiculoModeloJanPesq;
	}

	public static VendaProdutoJanCad getVendaProdutoJanCad() {
		return vendaProdutoJanCad;
	}

	public static VendaProdutoJanPesq getVendaProdutoJanPesq() {
		return vendaProdutoJanPesq;
	}

	public static VendaServicoJanCad getVendaServicoJanCad() {
		return vendaServicoJanCad;
	}

	public static VendaServicoJanPesq getVendaServicoJanPesq() {
		return vendaServicoJanPesq;
	}

	public static void mostrarFrame(JFrame frame) {
		frame.setVisible(true);
		frame.toFront();
		frame.setLocationRelativeTo(null);
		mainJan.setLocationRelativeTo(null);
		frame.setResizable(false);
	}

	private MainControl(MainJan mainJan) {
		MainControl.mainJan = mainJan;
		criarFrames();
	}

	private void criarFrame(JFrame frame) {
		frame.pack();
		frame.setVisible(false);
	}

	private void criarFrames() {
		loginJan = new LoginJan();
		criarFrame(loginJan);

		dadosJanCad = new DadosJanCad();
		criarFrame(dadosJanCad);

		bancoJanCad = new BancoJanCad();
		criarFrame(bancoJanCad);

		bancoJanPesq = new BancoJanPesq();
		criarFrame(bancoJanPesq);

		centroCustoJanCad = new CentroCustoJanCad();
		criarFrame(centroCustoJanCad);

		centroCustoJanPesq = new CentroCustoJanPesq();
		criarFrame(centroCustoJanPesq);

		clienteJanCad = new ClienteJanCad();
		criarFrame(clienteJanCad);

		clienteJanPesq = new ClienteJanPesq();
		criarFrame(clienteJanPesq);

		empresaJanCad = new EmpresaJanCad();
		criarFrame(empresaJanCad);

		empresaJanPesq = new EmpresaJanPesq();
		criarFrame(empresaJanPesq);

		usuarioJanCad = new UsuarioJanCad();
		criarFrame(usuarioJanCad);

		usuarioJanPesq = new UsuarioJanPesq();
		criarFrame(usuarioJanPesq);

		cartorioJanCad = new CartorioJanCad();
		criarFrame(cartorioJanCad);

		cartorioJanPesq = new CartorioJanPesq();
		criarFrame(cartorioJanPesq);

		contadorJanCad = new ContadorJanCad();
		criarFrame(contadorJanCad);

		contadorJanPesq = new ContadorJanPesq();
		criarFrame(contadorJanPesq);

		sindicatoJanCad = new SindicatoJanCad();
		criarFrame(sindicatoJanCad);

		sindicatoJanPesq = new SindicatoJanPesq();
		criarFrame(sindicatoJanPesq);

		fornecedorJanCad = new FornecedorJanCad();
		criarFrame(fornecedorJanCad);

		fornecedorJanPesq = new FornecedorJanPesq();
		criarFrame(fornecedorJanPesq);

		veiculoJanCad = new VeiculoJanCad();
		criarFrame(veiculoJanCad);

		veiculoJanPesq = new VeiculoJanPesq();
		criarFrame(veiculoJanPesq);

		contatoJanCad = new ContatoJanCad();
		criarFrame(contatoJanCad);

		contatoJanPesq = new ContatoJanPesq();
		criarFrame(contatoJanPesq);

		eventoJanCad = new EventoJanCad();
		criarFrame(eventoJanCad);

		eventoJanPesq = new EventoJanPesq();
		criarFrame(eventoJanPesq);

		tipoEventoJanCad = new TipoEventoJanCad();
		criarFrame(tipoEventoJanCad);

		tipoEventoJanPesq = new TipoEventoJanPesq();
		criarFrame(tipoEventoJanPesq);

		recadoJanCad = new RecadoJanCad();
		criarFrame(recadoJanCad);

		recadoJanPesq = new RecadoJanPesq();
		criarFrame(recadoJanPesq);

		veiculoMarcaJanCad = new VeiculoMarcaJanCad();
		criarFrame(veiculoMarcaJanCad);

		veiculoMarcaJanPesq = new VeiculoMarcaJanPesq();
		criarFrame(veiculoMarcaJanPesq);

		veiculoModeloJanCad = new VeiculoModeloJanCad();
		criarFrame(veiculoModeloJanCad);

		veiculoModeloJanPesq = new VeiculoModeloJanPesq();
		criarFrame(veiculoModeloJanPesq);

		veiculoDocumentoJanCad = new VeiculoDocumentoJanCad();
		criarFrame(veiculoDocumentoJanCad);

		veiculoDocumentoJanPesq = new VeiculoDocumentoJanPesq();
		criarFrame(veiculoDocumentoJanPesq);

		calculadoraJanCad = new CalculadoraJanCad();
		criarFrame(calculadoraJanCad);

		editorTextoJanCad = new EditorTextoJanCad();
		criarFrame(editorTextoJanCad);

		servicoJanCad = new ServicoJanCad();
		criarFrame(servicoJanCad);

		servicoJanPesq = new ServicoJanPesq();
		criarFrame(servicoJanPesq);

		produtoUnidadeJanCad = new ProdutoUnidadeJanCad();
		criarFrame(produtoUnidadeJanCad);

		produtoUnidadeJanPesq = new ProdutoUnidadeJanPesq();
		criarFrame(produtoUnidadeJanPesq);

		produtoMarcaJanCad = new ProdutoMarcaJanCad();
		criarFrame(produtoMarcaJanCad);

		produtoMarcaJanPesq = new ProdutoMarcaJanPesq();
		criarFrame(produtoMarcaJanPesq);

		produtoCategoriaJanCad = new ProdutoCategoriaJanCad();
		criarFrame(produtoCategoriaJanCad);

		produtoCategoriaJanPesq = new ProdutoCategoriaJanPesq();
		criarFrame(produtoCategoriaJanPesq);

		produtoJanCad = new ProdutoJanCad();
		criarFrame(produtoJanCad);

		produtoJanPesq = new ProdutoJanPesq();
		criarFrame(produtoJanPesq);

		seguradoraJanCad = new SeguradoraJanCad();
		criarFrame(seguradoraJanCad);

		seguradoraJanPesq = new SeguradoraJanPesq();
		criarFrame(seguradoraJanPesq);

		documentoJanCad = new DocumentoJanCad();
		criarFrame(documentoJanCad);

		documentoJanPesq = new DocumentoJanPesq();
		criarFrame(documentoJanPesq);

		compraProdutoJanCad = new CompraProdutoJanCad();
		criarFrame(compraProdutoJanCad);

		compraProdutoJanPesq = new CompraProdutoJanPesq();
		criarFrame(compraProdutoJanPesq);

		pedidoPlacaJanCad = new PedidoPlacaJanCad();
		criarFrame(pedidoPlacaJanCad);

		pedidoPlacaJanPesq = new PedidoPlacaJanPesq();
		criarFrame(pedidoPlacaJanPesq);

		seguroJanCad = new SeguroJanCad();
		criarFrame(seguroJanCad);

		seguroJanPesq = new SeguroJanPesq();
		criarFrame(seguroJanPesq);

		vendaServicoJanCad = new VendaServicoJanCad();
		criarFrame(vendaServicoJanCad);

		vendaServicoJanPesq = new VendaServicoJanPesq();
		criarFrame(vendaServicoJanPesq);

		vendaProdutoJanCad = new VendaProdutoJanCad();
		criarFrame(vendaProdutoJanCad);

		vendaProdutoJanPesq = new VendaProdutoJanPesq();
		criarFrame(vendaProdutoJanPesq);

		contasReceberJanCad = new ContasReceberJanCad();
		criarFrame(contasReceberJanCad);

		contasReceberJanPesq = new ContasReceberJanPesq();
		criarFrame(contasReceberJanPesq);

		contasPagarJanCad = new ContasPagarJanCad();
		criarFrame(contasPagarJanCad);

		contasPagarJanPesq = new ContasPagarJanPesq();
		criarFrame(contasPagarJanPesq);

		chequeJanCad = new ChequeJanCad();
		criarFrame(chequeJanCad);

		chequeJanPesq = new ChequeJanPesq();
		criarFrame(chequeJanPesq);

		caixaJanCad = new CaixaJanCad();
		criarFrame(caixaJanCad);

		caixaJanPesq = new CaixaJanPesq();
		criarFrame(caixaJanPesq);
	}

	private String getTitulo(String titulo) {
		return Sis.getNomeSistema() + " - " + titulo + " " + Data.getDataHoraSimples() + Sis.getUsuarioFormatado();
	}
}