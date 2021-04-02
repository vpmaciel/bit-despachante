package erp.negocio.produto.marca;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class ProdutoMarcaSel implements ListSelectionListener {

	JTable table;

	ProdutoMarcaSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				ProdutoMarca produtoMarcaPesquisaRegistro = new ProdutoMarca();
				produtoMarcaPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], ProdutoMarcaTm.ID));

				if (table.getSelectedRow() != -1) {
					ProdutoMarca produtoMarca = ProdutoMarcaFac.getRegistro(produtoMarcaPesquisaRegistro);
					ProdutoMarcaTm produtoMarcaTm = (ProdutoMarcaTm) table.getModel();
					produtoMarcaTm.getProdutoMarca(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getProdutoMarcaJanCad());
					MainControl.getProdutoMarcaJanCad().getProdutoMarcaCont().setProdutoMarca(produtoMarca);
					MainControl.getProdutoMarcaJanCad().getProdutoMarcaCont().atualizarGui();
					MainControl.getProdutoMarcaJanCad().setFocusable(true);
					MainControl.getProdutoMarcaJanPesq().setVisible(false);
				}
			}
		}
	}
}
