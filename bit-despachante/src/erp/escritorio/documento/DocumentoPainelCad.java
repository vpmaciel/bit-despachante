package erp.escritorio.documento;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import erp.arquitetura.Sis;
import erp.arquitetura.gui.ConfiguracaoGui;
import erp.arquitetura.gui.EntradaMaiuscula;
import erp.arquitetura.gui.FocoEvento;
import erp.arquitetura.gui.Gui;
import erp.arquitetura.gui.ToolBar;

@SuppressWarnings("serial")
public final class DocumentoPainelCad extends JPanel implements Gui {

	private JComboBox<String> boxMesDevolucaoDocumento;
	private JComboBox<String> boxMesRecebimentoDocumento;
	private JComboBox<String> boxSituacaoDocumento;
	private JComboBox<String> boxTipoDocumento;
	private ConfiguracaoGui configuracaoGui;
	private JTextField fieldAnoDevolucaoDocumento;
	private JTextField fieldAnoRecebimentoDocumento;
	private JTextField fieldCNPJRecebedorDocumento;
	private JTextField fieldCPFRecebedorDocumento;
	private JTextField fieldDescricao;
	private JTextField fieldDiaDevolucaoDocumento;
	private JTextField fieldDiaRecebimentoDocumento;
	private JTextField fieldLocalDocumento;
	private JTextField fieldNomeProprietário;
	private JTextField fieldNomeRecebedorDocumento;
	private JTextField fieldRGNumeroRecebedorDocumento;
	private JTextField fieldRGOrgaoEmisssorRecebedorDocumento;
	private ToolBar toolBar;

	public DocumentoPainelCad() {
		iniciarLayout();
		iniciarGui();
		iniciarFocoControlador();
		iniciarGuiControlador();
	}

	@Override
	public void atualizarTable() {

	}

	@Override
	public ConfiguracaoGui getConfiguracaoGui() {
		return configuracaoGui;
	}

	public JTextField getGuiAnoDevolucaoDocumento() {
		return fieldAnoDevolucaoDocumento;
	}

	public JTextField getGuiAnoRecebimentoDocumento() {
		return fieldAnoRecebimentoDocumento;
	}

	public JTextField getGuiCNPJRecebedorDocumento() {
		return fieldCNPJRecebedorDocumento;
	}

	public JTextField getGuiCPFRecebedorDocumento() {
		return fieldCPFRecebedorDocumento;
	}

	public JTextField getGuiDiaDevolucaoDocumento() {
		return fieldDiaDevolucaoDocumento;
	}

	public JTextField getGuiDiaRecebimentoDocumento() {
		return fieldDiaRecebimentoDocumento;
	}

	public JTextField getGuiLocalDocumento() {
		return fieldLocalDocumento;
	}

	public JComboBox<String> getGuiMesDevolucaoDocumento() {
		return boxMesDevolucaoDocumento;
	}

	public JComboBox<String> getGuiMesRecebimentoDocumento() {
		return boxMesRecebimentoDocumento;
	}

	public JTextField getGuiNomeProprietário() {
		return fieldNomeProprietário;
	}

	public JTextField getGuiNomeRecebedorDocumento() {
		return fieldNomeRecebedorDocumento;
	}

	public JTextField getGuiRGNumeroRecebedorDocumento() {
		return fieldRGNumeroRecebedorDocumento;
	}

	public JTextField getGuiRGOrgaoEmisssorRecebedorDocumento() {
		return fieldRGOrgaoEmisssorRecebedorDocumento;
	}

	public JComboBox<String> getGuiSituacaoDocumento() {
		return boxSituacaoDocumento;
	}

	public JComboBox<String> getGuiTipoDocumento() {
		return boxTipoDocumento;
	}

	public JTextField getGuiVeiculo() {
		return fieldDescricao;
	}

	public ToolBar getTB() {
		return toolBar;
	}

	@Override
	public void iniciarControlador() {

	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {

		toolBar = new ToolBar();

		add(toolBar.getTB());

		add(new JLabel("TIPO DE DOCUMENTO"));

		boxTipoDocumento = new JComboBox<String>();
		boxTipoDocumento.addItem("");
		boxTipoDocumento.addItem("ALVARÁ");
		boxTipoDocumento.addItem("APOSTILA");
		boxTipoDocumento.addItem("ATA");
		boxTipoDocumento.addItem("ATESTADO");
		boxTipoDocumento.addItem("AUTO");
		boxTipoDocumento.addItem("AVISO");
		boxTipoDocumento.addItem("BOLETIM");
		boxTipoDocumento.addItem("BOLETO BANCÁRIO");
		boxTipoDocumento.addItem("CARTEIRA DE IDENTIDADE – RG");
		boxTipoDocumento.addItem("CADASTRO DE PESSOA FÍSICA – CPF");
		boxTipoDocumento.addItem("CARTEIRA DE TRABALHO E PREVIDÊNCIA SOCIAL – CTPS");
		boxTipoDocumento.addItem("CARTEIRA NACIONAL DE HABILITAÇÃO – CNH");
		boxTipoDocumento.addItem("CERTIDÃO DE CASAMENTO");
		boxTipoDocumento.addItem("CERTIDÃO DE NASCIMENTO");
		boxTipoDocumento.addItem("CERTIDÃO DE ÓBITO");
		boxTipoDocumento.addItem("CERTIDÃO DE PRONTUÁRIO – IIRGD");
		boxTipoDocumento.addItem("CERTIFICADO DE ALISTAMENTO MILITAR");
		boxTipoDocumento.addItem("CARTA");
		boxTipoDocumento.addItem("CARTÃO DE CRÉDITO");
		boxTipoDocumento.addItem("CERTIDÃO");
		boxTipoDocumento.addItem("CIRCULAR");
		boxTipoDocumento.addItem("CONTRATO");
		boxTipoDocumento.addItem("CONVÊNIO");
		boxTipoDocumento.addItem("CONVITE");
		boxTipoDocumento.addItem("CONVOCAÇÃO");
		boxTipoDocumento.addItem("COMUNICADO");
		boxTipoDocumento.addItem("COBRANÇA");
		boxTipoDocumento.addItem("CHEQUE");
		boxTipoDocumento.addItem("CRV/CRLV");
		boxTipoDocumento.addItem("DECLARAÇÃO");
		boxTipoDocumento.addItem("DECRETO");
		boxTipoDocumento.addItem("DELIBERAÇÃO");
		boxTipoDocumento.addItem("DESPACHO");
		boxTipoDocumento.addItem("EDITAL");
		boxTipoDocumento.addItem("ESTATUTO");
		boxTipoDocumento.addItem("EXPOSIÇÃO DE MOTIVOS");
		boxTipoDocumento.addItem("ESTRANGEIROS E IMIGRANTES");
		boxTipoDocumento.addItem("FAX");
		boxTipoDocumento.addItem("GUIA");
		boxTipoDocumento.addItem("INSTRUÇÃO NORMATIVA");
		boxTipoDocumento.addItem("MEMORANDO");
		boxTipoDocumento.addItem("MENSAGEM");
		boxTipoDocumento.addItem("NOTA");
		boxTipoDocumento.addItem("NOTA TÉCNICA");
		boxTipoDocumento.addItem("NOTA INFORMATIVA");
		boxTipoDocumento.addItem("NORMA OPERACIONAL");
		boxTipoDocumento.addItem("NOTA FISCAL");
		boxTipoDocumento.addItem("OUTRO");
		boxTipoDocumento.addItem("OFÍCIO");
		boxTipoDocumento.addItem("ORDEM DE SERVIÇO");
		boxTipoDocumento.addItem("PASSAPORTE");
		boxTipoDocumento.addItem("PARECER");
		boxTipoDocumento.addItem("PORTARIA");
		boxTipoDocumento.addItem("RELATÓRIO");
		boxTipoDocumento.addItem("REQUERIMENTO");
		boxTipoDocumento.addItem("RESOLUÇÃO");
		boxTipoDocumento.addItem("TÍTULO DE ELEITOR");
		add(boxTipoDocumento);

		add(new JLabel("DESCRIÇÃO DO DOCUMENTO"));

		fieldDescricao = new JTextField();
		fieldDescricao.setDocument(new EntradaMaiuscula(50));
		add(fieldDescricao);

		add(new JLabel("NOME DA PESSOA QUE RECEBEU O DOCUMENTO"));

		fieldNomeRecebedorDocumento = new JTextField();
		fieldNomeRecebedorDocumento.setDocument(new EntradaMaiuscula(50));
		add(fieldNomeRecebedorDocumento);

		add(new JLabel("IDENTIDADE DA PESSOA QUE RECEBEU O DOCUMENTO"));

		fieldRGNumeroRecebedorDocumento = new JTextField();
		fieldRGNumeroRecebedorDocumento.setDocument(new EntradaMaiuscula(15));
		add(fieldRGNumeroRecebedorDocumento);

		add(new JLabel("IDENTIDADE ÓRGÃO EMISSOR DA PESSOA QUE RECEBEU O DOCUMENTO"));

		fieldRGOrgaoEmisssorRecebedorDocumento = new JTextField();
		fieldRGOrgaoEmisssorRecebedorDocumento.setDocument(new EntradaMaiuscula(20));
		add(fieldRGOrgaoEmisssorRecebedorDocumento);

		add(new JLabel("CPF DA PESSOA QUE RECEBEU O DOCUMENTO"));

		fieldCPFRecebedorDocumento = new JTextField();
		add(fieldCPFRecebedorDocumento);

		add(new JLabel("CNPJ DA PESSOA QUE RECEBEU O DOCUMENTO"));

		fieldCNPJRecebedorDocumento = new JTextField();
		add(fieldCNPJRecebedorDocumento);

		add(new JLabel("SITUAÇÃO DO DOCUMENTO"));

		boxSituacaoDocumento = new JComboBox<String>();
		boxSituacaoDocumento.addItem("");
		boxSituacaoDocumento.addItem("AGUARDANDO");
		boxSituacaoDocumento.addItem("PROCESSANDO");
		boxSituacaoDocumento.addItem("FINALIZADO");
		add(boxSituacaoDocumento);

		add(new JLabel("DOCUMENTO ESTA ATUALMENTE COM"));

		fieldLocalDocumento = new JTextField();
		fieldLocalDocumento.setDocument(new EntradaMaiuscula(50));
		add(fieldLocalDocumento);

		add(new JLabel("ANO DE RECEBIMENTO DO DOCUMENTO"));

		fieldAnoRecebimentoDocumento = new JTextField();
		add(fieldAnoRecebimentoDocumento);

		add(new JLabel("MÊS DE RECEBIMENTO DO DOCUMENTO"));

		boxMesRecebimentoDocumento = new JComboBox<String>();
		boxMesRecebimentoDocumento.addItem("");
		boxMesRecebimentoDocumento.addItem("JANEIRO");
		boxMesRecebimentoDocumento.addItem("FEVEREIRO");
		boxMesRecebimentoDocumento.addItem("MARÇO");
		boxMesRecebimentoDocumento.addItem("ABRIL");
		boxMesRecebimentoDocumento.addItem("MAIO");
		boxMesRecebimentoDocumento.addItem("JUNHO");
		boxMesRecebimentoDocumento.addItem("JULHO");
		boxMesRecebimentoDocumento.addItem("AGOSTO");
		boxMesRecebimentoDocumento.addItem("SETEMBRO");
		boxMesRecebimentoDocumento.addItem("OUTUBRO");
		boxMesRecebimentoDocumento.addItem("NOVEMBRO");
		boxMesRecebimentoDocumento.addItem("DEZEMBRO");
		add(boxMesRecebimentoDocumento);

		add(new JLabel("DATA DE RECEBIMENTO DO DOCUMENTO"));

		fieldDiaRecebimentoDocumento = new JTextField();
		fieldDiaRecebimentoDocumento.setDocument(new EntradaMaiuscula(2));
		add(fieldDiaRecebimentoDocumento);

		add(new JLabel("ANO DE DEVOLUÇÃO DO DOCUMENTO"));

		fieldAnoDevolucaoDocumento = new JTextField();
		add(fieldAnoDevolucaoDocumento);

		add(new JLabel("MÊS DE DEVOLUÇÃO DO DOCUMENTO"));

		boxMesDevolucaoDocumento = new JComboBox<String>();
		boxMesDevolucaoDocumento.addItem("");
		boxMesDevolucaoDocumento.addItem("JANEIRO");
		boxMesDevolucaoDocumento.addItem("FEVEREIRO");
		boxMesDevolucaoDocumento.addItem("MARÇO");
		boxMesDevolucaoDocumento.addItem("ABRIL");
		boxMesDevolucaoDocumento.addItem("MAIO");
		boxMesDevolucaoDocumento.addItem("JUNHO");
		boxMesDevolucaoDocumento.addItem("JULHO");
		boxMesDevolucaoDocumento.addItem("AGOSTO");
		boxMesDevolucaoDocumento.addItem("SETEMBRO");
		boxMesDevolucaoDocumento.addItem("OUTUBRO");
		boxMesDevolucaoDocumento.addItem("NOVEMBRO");
		boxMesDevolucaoDocumento.addItem("DEZEMBRO");
		add(boxMesDevolucaoDocumento);

		add(new JLabel("DATA DE DEVOLUÇÃO DO DOCUMENTO"));

		fieldDiaDevolucaoDocumento = new JTextField();
		fieldDiaDevolucaoDocumento.setDocument(new EntradaMaiuscula(2));
		add(fieldDiaDevolucaoDocumento);
	}

	@Override
	public void iniciarGuiControlador() {
		configuracaoGui = new ConfiguracaoGui(this);
	}

	@Override
	public void iniciarLayout() {
		setBorder(Sis.getBordaPainel());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	@Override
	public void iniciarTabela() {

	}

	@Override
	public void limparGui() {
		configuracaoGui.limparGui();
	}

	@Override
	public void reiniciarGui() {

	}
}