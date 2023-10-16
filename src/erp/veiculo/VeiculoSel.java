package erp.veiculo;

import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class VeiculoSel implements ListSelectionListener {

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
				Veiculo marcaPesquisaRegistro = new Veiculo();
				marcaPesquisaRegistro.setId((Long)tm.getValueAt(selRows[0], VeiculoTm.ID));

				if (table.getSelectedRow() != -1) {
					Veiculo marca = ((List<Veiculo>) VeiculoFac.pesquisarRegistro(marcaPesquisaRegistro)).get(table.getSelectedRow());
					VeiculoTm marcaTm = (VeiculoTm) table.getModel();
					marcaTm.getMarca(table.getSelectedRow());					

					MainControl.mostrarFrame(MainControl.getPedidoPlacaJan());
					MainControl.getVeiculoJan().getVeiculoCont().setModelo(VeiculoFac.getRegistro(marca));
					MainControl.getVeiculoJan().getVeiculoCont().atualizarGui();
					MainControl.getVeiculoJan().setFocusable(true);
					MainControl.getVeiculoJan().getTabbedPane().setSelectedIndex(0);
				}
			}
		}
	}
}
