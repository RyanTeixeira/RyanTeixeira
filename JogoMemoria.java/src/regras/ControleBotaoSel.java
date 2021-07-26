package regras;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;

public class ControleBotaoSel {

    private String nmBotao;
    private Map<JButton, StatusBotao> RefBotao;

    public ControleBotaoSel() {
        this.RefBotao = new HashMap<>();
    }

    public void execAcaoBotoes(JButton botao, StatusBotao estado) {
        alterarSel(botao, estado);

        if (this.isverificarSelecionados()) {
            alterarEstadosDeTodosBotoes(StatusBotao.ENCONTRADO_PARES);
        } else {
            alterarVisualizacaoDoBotao(botao);
        }
    }

    private void alterarEstadosDeTodosBotoes(StatusBotao estado) {
        for (JButton botao : this.RefBotao.keySet()) {
            alterarSel(botao, estado);
            alterarVisualizacaoDoBotao(botao);
        }
    }

    public String getNmBotao() {
        return nmBotao;
    }

    public void setNmBotao(String nmBotao) {
        this.nmBotao = nmBotao;
    }

    public Map<JButton, StatusBotao> getRefBotao() {
        return RefBotao;
    }

    public void setRefBotao(Map<JButton, StatusBotao> RefBotao) {
        this.RefBotao = RefBotao;
    }

    public void addBotao(JButton botao) {
        this.RefBotao.put(botao, StatusBotao.NORMAL);
    }

    public void alterarSel(JButton botao, StatusBotao selecionado) {
        this.RefBotao.put(botao, selecionado);
    }

    private void alterarVisualizacaoDoBotao(JButton botao) {
        StatusBotao selecionado = this.RefBotao.get(botao);

        switch (selecionado) {
            case NORMAL:
                botao.setBackground(null);
                botao.setText("?");
                break;
            case SELECIONADO:
                botao.setBackground(Color.GREEN);
                botao.setText(this.nmBotao);
                break;
            case ENCONTRADO_PARES:
                botao.setBackground(Color.MAGENTA);
                botao.setText(this.nmBotao);
                botao.setEnabled(false);
                break;
        }
    }

    public void limparSelecao() {
        alterarEstadosDeTodosBotoes(StatusBotao.NORMAL);
    }

    public Boolean isverificarSelecionados() {
        for (StatusBotao b : this.RefBotao.values()) {
            if (b != StatusBotao.SELECIONADO) {
                return false;
            }
        }
        return true;
    }
}
