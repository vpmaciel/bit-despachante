package erp.cliente;

import java.awt.BorderLayout;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.XChartPanel;

@SuppressWarnings("serial")
public class ClientePainelGraf extends JPanel {

    private CategoryChart chart;
    private XChartPanel<CategoryChart> chartPanel;

    private final List<String> meses = Arrays.asList(
            "Jan", "Fev", "Mar", "Abr", "Mai", "Jun",
            "Jul", "Ago", "Set", "Out", "Nov", "Dez"
    );

    public ClientePainelGraf() {
	
	int anoAtual = Year.now().getValue();

        setLayout(new BorderLayout());

        // Criar gráfico
        chart = new CategoryChartBuilder()
                .width(800)
                .height(500)
                .title("Total de Clientes por Mês em " + anoAtual)
                .xAxisTitle("Mês")
                .yAxisTitle("Quantidade de Clientes")
                .build();

        chart.addSeries("Clientes", meses, carregarDados());

        chartPanel = new XChartPanel<>(chart);
        add(chartPanel, BorderLayout.CENTER);

        // Atualização automática a cada 60 segundos
        Timer timer = new Timer(60000, e -> atualizarGrafico());
        //timer.start();
    }

    private List<Integer> carregarDados() {

        Map<Integer, Long> mapa = null;
	try {
	    mapa = ClienteFac.pesquisarTotalClientesPorMes();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

        List<Integer> totais = new ArrayList<>();

        for (int mes = 1; mes <= 12; mes++) {
            totais.add(mapa.getOrDefault(mes, 0L).intValue());
        }

        return totais;
    }

    void atualizarGrafico() {

        List<Integer> novosDados = carregarDados();

        chart.updateCategorySeries("Clientes", meses, novosDados, null);

        chartPanel.revalidate();
        chartPanel.repaint();
    }
}
