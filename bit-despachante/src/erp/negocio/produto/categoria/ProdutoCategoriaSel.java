package erp.negocio.produto.categoria;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import erp.sistema.main.MainControl;

final class ProdutoCategoriaSel implements ListSelectionListener {

	JTable table;

	ProdutoCategoriaSel(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int[] selRows = table.getSelectedRows();
			TableModel tm = table.getModel();
			if (selRows.length > 0) {
				ProdutoCategoria servicoPesquisaRegistro = new ProdutoCategoria();
				servicoPesquisaRegistro.setId((Long) tm.getValueAt(selRows[0], ProdutoCategoriaTm.ID));

				if (table.getSelectedRow() != -1) {
					ProdutoCategoria servico = ProdutoCategoriaFac.getRegistro(servicoPesquisaRegistro);
					ProdutoCategoriaTm servicoTm = (ProdutoCategoriaTm) table.getModel();
					servicoTm.getProdutoCategoria(table.getSelectedRow());
					MainControl.mostrarFrame(MainControl.getProdutoCategoriaJanCad());
					MainControl.getProdutoCategoriaJanCad().getProdutoCategoriaCont().setProdutoCategoria(servico);
					MainControl.getProdutoCategoriaJanCad().getProdutoCategoriaCont().atualizarGui();
					MainControl.getProdutoCategoriaJanCad().setFocusable(true);
					MainControl.getProdutoCategoriaJanPesq().setVisible(false);
				}
			}
		}
	}
}
