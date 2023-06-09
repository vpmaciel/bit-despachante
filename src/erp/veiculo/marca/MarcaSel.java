package erp.veiculo.marca;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class MarcaSel implements ListSelectionListener {

	JTable table;

	MarcaSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				Marca marcaPesquisaRegistro = new Marca();
				marcaPesquisaRegistro.setId((Long)tm.getValueAt(selRows[0], MarcaTm.ID));

				if (table.getSelectedRow() != -1) {
					Marca marca = ((List<Marca>) MarcaFac.pesquisarRegistro(marcaPesquisaRegistro)).get(table.getSelectedRow());
					MarcaTm marcaTm = (MarcaTm) table.getModel();
					marcaTm.getMarca(table.getSelectedRow());					

					MainControl.mostrarFrame(MainControl.getMarcaJan());
					MainControl.getMarcaJan().getContaCont().setMarca(MarcaFac.getRegistro(marca));
					MainControl.getMarcaJan().getContaCont().atualizarGui();
					MainControl.getMarcaJan().setFocusable(true);
					MainControl.getMarcaJan().getTabbedPane().setSelectedIndex(0);
				}
			}
		}
	}
}
