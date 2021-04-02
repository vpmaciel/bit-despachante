package erp.negocio.servico;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class ServicoSel implements ListSelectionListener {

	JTable table;

	ServicoSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				Servico servicoPesquisaRegistro = new Servico();
				servicoPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], ServicoTm.ID));

				if (table.getSelectedRow() != -1) {
					Servico servico = ServicoFac.getRegistro(servicoPesquisaRegistro);
					ServicoTm servicoTm = (ServicoTm) table.getModel();
					servicoTm.getServico(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getServicoJanCad());
					MainControl.getServicoJanCad().getServicoCont().setServico(servico);
					MainControl.getServicoJanCad().getServicoCont().atualizarGui();
					MainControl.getServicoJanCad().setFocusable(true);
					MainControl.getServicoJanPesq().setVisible(false);
				}
			}
		}
	}
}
