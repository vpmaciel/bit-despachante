package erp.negocio.veiculo.modelo;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class VeiculoModeloSel implements ListSelectionListener {

	JTable table;

	VeiculoModeloSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				VeiculoModelo veiculoModeloPesquisaRegistro = new VeiculoModelo();
				veiculoModeloPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], VeiculoModeloTm.ID));

				if (table.getSelectedRow() != -1) {
					VeiculoModelo veiculoModelo = VeiculoModeloFac.getRegistro(veiculoModeloPesquisaRegistro);
					VeiculoModeloTm veiculoModeloTm = (VeiculoModeloTm) table.getModel();
					veiculoModeloTm.getVeiculoModelo(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getVeiculoModeloJanCad());
					MainControl.getVeiculoModeloJanCad().getVeiculoModeloCont().setVeiculoModelo(veiculoModelo);
					MainControl.getVeiculoModeloJanCad().getVeiculoModeloCont().atualizarGui();
					MainControl.getVeiculoModeloJanCad().setFocusable(true);
					MainControl.getVeiculoModeloJanPesq().setVisible(false);
				}
			}
		}
	}
}
