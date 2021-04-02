package erp.negocio.veiculo;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

public final class VeiculoSel implements ListSelectionListener {

	JTable table;

	VeiculoSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				Veiculo veiculoPesquisaRegistro = new Veiculo();
				veiculoPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], VeiculoTm.ID));

				if (table.getSelectedRow() != -1) {
					Veiculo veiculo = VeiculoFac.getRegistro(veiculoPesquisaRegistro);
					VeiculoTm veiculoTm = (VeiculoTm) table.getModel();
					veiculoTm.getVeiculo(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getVeiculoJanCad());
					MainControl.getVeiculoJanCad().getVeiculoCont().setVeiculo(veiculo);
					MainControl.getVeiculoJanCad().getVeiculoCont().atualizarGui();
					MainControl.getVeiculoJanCad().setFocusable(true);
					MainControl.getVeiculoJanPesq().setVisible(false);
				}
			}
		}
	}
}
