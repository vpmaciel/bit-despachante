package erp.sistema.empresa;

import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class EmpresaSel implements ListSelectionListener {

	JTable table;

	EmpresaSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				Empresa usuarioPesquisaRegistro = new Empresa();
				usuarioPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], EmpresaTm.ID));

				if (table.getSelectedRow() != -1) {
					Empresa empresa = ((List<Empresa>) EmpresaFac.pesquisarRegistro(usuarioPesquisaRegistro)).get(0);
					EmpresaTm empresaTm = (EmpresaTm) table.getModel();
					empresaTm.getEmpresa(table.getSelectedRow());

					MainControl.mostrarFrame(MainControl.getEmpresaJan());
					MainControl.getEmpresaJan().getEmpresaCont().setEmpresa(empresa);
					MainControl.getEmpresaJan().getEmpresaCont().atualizarGui();
					MainControl.getEmpresaJan().setFocusable(true);
					MainControl.getEmpresaJan().getTabbedPane().setSelectedIndex(0);
				}
			}
		}
	}
}
