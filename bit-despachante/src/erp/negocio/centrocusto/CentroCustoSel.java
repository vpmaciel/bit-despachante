package erp.negocio.centrocusto;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class CentroCustoSel implements ListSelectionListener {

	JTable table;

	CentroCustoSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				CentroCusto centroCustoPesquisaRegistro = new CentroCusto();
				centroCustoPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], CentroCustoTm.ID));

				if (table.getSelectedRow() != -1) {
					CentroCusto centroCusto = CentroCustoFac.getRegistro(centroCustoPesquisaRegistro);
					CentroCustoTm centroCustoTm = (CentroCustoTm) table.getModel();
					centroCustoTm.getCentroCusto(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getCentroCustoJanCad());
					MainControl.getCentroCustoJanCad().getCentroCustoCont().setCentroCusto(centroCusto);
					MainControl.getCentroCustoJanCad().getCentroCustoCont().atualizarGui();
					MainControl.getCentroCustoJanCad().setFocusable(true);
					MainControl.getCentroCustoJanPesq().setVisible(false);
				}
			}
		}
	}
}
