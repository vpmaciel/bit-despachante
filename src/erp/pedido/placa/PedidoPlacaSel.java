package erp.pedido.placa;

import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class PedidoPlacaSel implements ListSelectionListener {

	JTable table;

	PedidoPlacaSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				PedidoPlaca marcaPesquisaRegistro = new PedidoPlaca();
				marcaPesquisaRegistro.setId((Long)tm.getValueAt(selRows[0], PedidoPlacaTm.ID));

				if (table.getSelectedRow() != -1) {
					PedidoPlaca marca = ((List<PedidoPlaca>) PedidoPlacaFac.pesquisarRegistro(marcaPesquisaRegistro)).get(table.getSelectedRow());
					PedidoPlacaTm marcaTm = (PedidoPlacaTm) table.getModel();
					marcaTm.getMarca(table.getSelectedRow());					

					MainControl.mostrarFrame(MainControl.getPedidoPlacaJan());
					MainControl.getPedidoPlacaJan().getContaCont().setModelo(PedidoPlacaFac.getRegistro(marca));
					MainControl.getPedidoPlacaJan().getContaCont().atualizarGui();
					MainControl.getPedidoPlacaJan().setFocusable(true);
					MainControl.getPedidoPlacaJan().getTabbedPane().setSelectedIndex(0);
				}
			}
		}
	}
}
