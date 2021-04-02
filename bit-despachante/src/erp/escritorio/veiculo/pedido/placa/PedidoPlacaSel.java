package erp.escritorio.veiculo.pedido.placa;

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
				PedidoPlaca pedidoPlacaPesquisaRegistro = new PedidoPlaca();
				pedidoPlacaPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], PedidoPlacaTm.ID));

				if (table.getSelectedRow() != -1) {
					PedidoPlaca pedidoPlaca = PedidoPlacaFac.getRegistro(pedidoPlacaPesquisaRegistro);
					PedidoPlacaTm pedidoPlacaTm = (PedidoPlacaTm) table.getModel();
					pedidoPlacaTm.getPedidoPlaca(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getPedidoPlacaJanCad());
					MainControl.getPedidoPlacaJanCad().getPedidoPlacaCont().setPedidoPlaca(pedidoPlaca);
					MainControl.getPedidoPlacaJanCad().getPedidoPlacaCont().atualizarGui();
					MainControl.getPedidoPlacaJanCad().setFocusable(true);
					MainControl.getPedidoPlacaJanPesq().setVisible(false);
				}
			}
		}
	}
}
