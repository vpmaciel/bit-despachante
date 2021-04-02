package erp.financeiro.vendas.servico;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class VendaServicoSel implements ListSelectionListener {

	JTable table;

	VendaServicoSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				VendaServico vendaServicoPesquisaRegistro = new VendaServico();
				vendaServicoPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], VendaServicoTm.ID));

				if (table.getSelectedRow() != -1) {
					VendaServico vendaServico = VendaServicoFac.getRegistro(vendaServicoPesquisaRegistro);
					VendaServicoTm vendaServicoTm = (VendaServicoTm) table.getModel();
					vendaServicoTm.getVendaServico(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getVendaServicoJanCad());
					MainControl.getVendaServicoJanCad().getVendaServicoCont().setVendaServico(vendaServico);
					MainControl.getVendaServicoJanCad().getVendaServicoCont().atualizarGui();
					MainControl.getVendaServicoJanCad().setFocusable(true);
					MainControl.getVendaServicoJanPesq().setVisible(false);
				}
			}
		}
	}
}
