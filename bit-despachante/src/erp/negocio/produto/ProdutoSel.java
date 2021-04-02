package erp.negocio.produto;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class ProdutoSel implements ListSelectionListener {

	JTable table;

	ProdutoSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				Produto produtoPesquisaRegistro = new Produto();
				produtoPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], ProdutoTm.ID));

				if (table.getSelectedRow() != -1) {
					Produto produto = ProdutoFac.getRegistro(produtoPesquisaRegistro);
					ProdutoTm ProdutoTm = (ProdutoTm) table.getModel();
					ProdutoTm.getProduto(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getProdutoJanCad());
					MainControl.getProdutoJanCad().getProdutoCont().setProduto(produto);
					MainControl.getProdutoJanCad().getProdutoCont().atualizarGui();
					MainControl.getProdutoJanCad().setFocusable(true);
					MainControl.getProdutoJanPesq().setVisible(false);
				}
			}
		}
	}
}
