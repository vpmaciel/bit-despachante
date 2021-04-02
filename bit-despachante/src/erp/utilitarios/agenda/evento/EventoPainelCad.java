package erp.utilitarios.agenda.evento;

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
import erp.utilitarios.agenda.evento.tipoevento.TipoEvento;
import erp.utilitarios.agenda.evento.tipoevento.TipoEventoComp;
import erp.utilitarios.agenda.evento.tipoevento.TipoEventoFac;

@SuppressWarnings("serial")
public final class EventoPainelCad extends JPanel implements Gui {

	private JComboBox<TipoEvento> boxTipoEvento;
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

	public JComboBox<TipoEvento> getTipoEventoGui() {
		return boxTipoEvento;
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

		boxTipoEvento = new JComboBox<TipoEvento>();
		boxTipoEvento.setMaximumRowCount(5);

		List<TipoEvento> tipoEventosList = (List<TipoEvento>) TipoEventoFac.getRegistro();
		Collections.sort(tipoEventosList, new TipoEventoComp().new Nome());

		for (TipoEvento t : tipoEventosList) {
			boxTipoEvento.addItem(t);
		}
		add(boxTipoEvento);

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

		TipoEvento tipoEvento = null;
		List<TipoEvento> tipoEventoList = (List<TipoEvento>) TipoEventoFac.getRegistro();
		Collections.sort(tipoEventoList, new TipoEventoComp().new Nome());
		boxTipoEvento.removeAllItems();

		for (TipoEvento t : tipoEventoList) {
			this.boxTipoEvento.addItem(t);
		}

		if (MainControl.getAgendaEventoJanCad().getEventoCont().getEvento() != null) {

			tipoEvento = MainControl.getAgendaEventoJanCad().getEventoCont().getEvento().getTipoEvento();
			boxTipoEvento.setSelectedItem(tipoEvento);
		}

	}
}
