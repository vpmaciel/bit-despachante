package erp.sistema.evento;

import java.util.Collections;
import java.util.List;

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
import erp.sistema.main.MainControl;

@SuppressWarnings("serial")
public final class EventoPainelCad extends JPanel implements Gui {

	private JTextField fieldTipoEvento;
	private ConfiguracaoGui configuracaoGui;
	private JTextField fieldData;
	private JTextField fieldDescricao;
	private JTextField fieldHoraInicio;
	private JTextField fieldHoraTermino;
	private JLabel labelTipoEvento;
	private ToolBar toolBar;

	public EventoPainelCad() {
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

	public JTextField getDataGui() {
		return fieldData;
	}

	public JTextField getGuiDescricao() {
		return fieldDescricao;
	}

	public JTextField getGuiHoraInicio() {
		return fieldHoraInicio;
	}

	public JTextField getGuiHoraTermino() {
		return fieldHoraTermino;
	}

	public JLabel getLabelTipoEvento() {
		return labelTipoEvento;
	}

	public ToolBar getTB() {
		return toolBar;
	}

	public JTextField getTipoEventoGui() {
		return fieldTipoEvento;
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

		labelTipoEvento = new JLabel("TIPO DE EVENTO");
		labelTipoEvento.setCursor(Sis.getNovaJanelaCursor());
		add(labelTipoEvento);		
		
		fieldTipoEvento = new JTextField();
		fieldTipoEvento.setDocument(new EntradaMaiuscula(50));
		add(fieldTipoEvento);

		add(new JLabel("DESCRIÇÃO"));

		fieldDescricao = new JTextField();
		fieldDescricao.setDocument(new EntradaMaiuscula(50));
		add(fieldDescricao);

		add(new JLabel("DATA"));

		fieldData = new JTextField();
		add(fieldData);

		add(new JLabel("HORÁRIO DE INÍCIO"));

		fieldHoraInicio = new JTextField();
		add(fieldHoraInicio);

		add(new JLabel("HORÁRIO DE TÉRMINO"));

		fieldHoraTermino = new JTextField();
		add(fieldHoraTermino);
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
