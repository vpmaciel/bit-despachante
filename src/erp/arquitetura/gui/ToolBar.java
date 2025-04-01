package erp.arquitetura.gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBar {

    JButton buttonCsv = new JButton(Imagem.getCsv());
    JButton buttonExclui = new JButton(Imagem.getExclui());
    JButton buttonHome = new JButton(Imagem.getHome());
    JButton buttonImprime = new JButton(Imagem.getImprime());
    JButton buttonJson = new JButton(Imagem.getJson());
    JButton buttonNovo = new JButton(Imagem.getNovo());
    JButton buttonPesquisa = new JButton(Imagem.getPesquisar());
    JButton buttonRegistros = new JButton(Imagem.getRegistros());
    JButton buttonRelatorio = new JButton(Imagem.getRelatorio());
    JButton buttonSalvar = new JButton(Imagem.getSalva());
    JButton buttonFecharCaixa = new JButton(Imagem.getFecharCaixa());

    JToolBar toolBar = new JToolBar();

    public ToolBar() {
	Dimension tamanhoToolBar = new Dimension(620, 40);
	toolBar.setPreferredSize(tamanhoToolBar);
	toolBar.setMinimumSize(tamanhoToolBar);
	toolBar.setSize(tamanhoToolBar);
	toolBar.setMaximumSize(tamanhoToolBar);
	toolBar.setFloatable(false);
	toolBar.setOpaque(false);
	buttonHome.setToolTipText("Home");
	toolBar.add(buttonHome);

	buttonNovo.setToolTipText("Novo");
	toolBar.add(buttonNovo);

	buttonExclui.setToolTipText("Excluir");
	toolBar.add(buttonExclui);

	buttonSalvar.setToolTipText("Salvar");
	toolBar.add(buttonSalvar);

	buttonPesquisa.setToolTipText("Pesquisar");
	toolBar.add(buttonPesquisa);

	buttonRegistros.setToolTipText("Registros");
	toolBar.add(buttonRegistros);

	buttonImprime.setToolTipText("Imprimir");
	toolBar.add(buttonImprime);

	buttonCsv.setToolTipText("Arquivo csv");
	toolBar.add(buttonCsv);

	buttonJson.setToolTipText("Arquivo json");
	toolBar.add(buttonJson);

	buttonRelatorio.setToolTipText("Relatório");
	toolBar.add(buttonRelatorio);
    }

    public JButton getCsvBtn() {
	return buttonCsv;
    }

    public JButton getExcluirBtn() {
	return buttonExclui;
    }

    public JButton getHomeBtn() {
	return buttonHome;
    }

    public JButton getImprimirBtn() {
	return buttonImprime;
    }

    public JButton getJsonBtn() {
	return buttonJson;
    }

    public JButton getNovoBtn() {
	return buttonNovo;
    }

    public JButton getPesquisarBtn() {
	return buttonPesquisa;
    }

    public JButton getRegistrosBtn() {
	return buttonRegistros;
    }

    public JButton getRelatorioBtn() {
	return buttonRelatorio;
    }

    public JButton getSalvarBtn() {
	return buttonSalvar;
    }

    public JToolBar getTB() {
	return toolBar;
    }

    public JButton getFecharCaixaBtn() {
	//
	return buttonFecharCaixa;
    }
}