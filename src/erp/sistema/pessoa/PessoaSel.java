package erp.sistema.pessoa;

import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class PessoaSel implements ListSelectionListener {

	JTable table;

	PessoaSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				Pessoa usuarioPesquisaRegistro = new Pessoa();
				usuarioPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], PessoaTm.ID));

				if (table.getSelectedRow() != -1) {
					Pessoa pessoa = ((List<Pessoa>) PessoaFac.pesquisarRegistro(usuarioPesquisaRegistro)).get(0);
					PessoaTm pessoaTm = (PessoaTm) table.getModel();
					pessoaTm.getPessoa(table.getSelectedRow());

					MainControl.mostrarFrame(MainControl.getPessoaJan());
					MainControl.getPessoaJan().getPessoaCont().setPessoa(pessoa);
					MainControl.getPessoaJan().getPessoaCont().atualizarGui();
					MainControl.getPessoaJan().setFocusable(true);
					MainControl.getPessoaJan().getTabbedPane().setSelectedIndex(0);
				}
			}
		}
	}
}
