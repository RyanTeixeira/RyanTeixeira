package telas;
//bibliotecas usadas
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import regras.ControleBotaoSel;
import regras.StatusBotao;

public class TelaPrincipal extends JFrame {

    private static int QUANTIDADE_JOGADA = 2;
    private int jogadas = 0;

    private JPanel painel;

    private List<ControleBotaoSel> listaControle;
    private List<ControleBotaoSel> listaBotaoSell;

    private ActionListener acaoBotoes;

    public TelaPrincipal() {
        super("Jogo da Memoria");

        listaControle = new ArrayList<ControleBotaoSel>();
        listaBotaoSell = new ArrayList<>();

        
        //verificar a quantidade de jogadas
        acaoBotoes = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JButton botao = (JButton) e.getSource();

                for (ControleBotaoSel cont : listaControle) {
                    if (cont.getRefBotao().get(botao) != null) {
                        jogadas++;
                        cont.execAcaoBotoes((JButton) e.getSource(), StatusBotao.SELECIONADO);

                        if (!listaBotaoSell.contains(cont)) {
                            listaBotaoSell.add(cont);
                        }

                        listaBotaoSell.add(cont);

                        if (jogadas == QUANTIDADE_JOGADA) {
                            if (listaBotaoSell.size() > 1) {
                                for (ControleBotaoSel cbs : listaBotaoSell) {
                                    cbs.limparSelecao();
                                }
                            }
                            jogadas = 0;
                            listaBotaoSell.clear();
                        }
                        break;
                    }
                }

            }
        };

        //formatação layout
        painel = new JPanel();
        this.add(painel);
        painel.setLayout(null);

        //Posição e tamanho Tela
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 500, 500);//seta tamanho e posição da janela
        this.setVisible(true); //exibe a janela
        criarJogoMemoria(7);//QUANTIDADE DE PARES
    }

    private void criarJogoMemoria(int quantidadePares) {
        //qt botões
        ControleBotaoSel controle = null;

        List<Rectangle> posicionamento = new ArrayList<>();
        int x = 10;
        int y = 10;

        Random rand = new Random();

        int j = 0;
        int i = 0;

        for (i = 0; i < (quantidadePares * 2); i++) {
            //randomizar o posicionamento botões
            Rectangle rec = new Rectangle(x, y, 75, 75);
            posicionamento.add(rec);
            if (i % 5 == 0 && i > 0) {
                y += 80;
                x += 10;
            } else {
                y += 160;
                x += 10;
            }

        }

        for (i = 0; i < (quantidadePares * 2); i++) {

            if (i % 2 == 0) {
                j++;
                controle = new ControleBotaoSel();
                controle.setNmBotao(""+i);
                this.listaControle.add(controle);
            }

            JButton botao = new JButton("?");

            //cria o botão
            this.painel.add(botao);
            botao.addActionListener(this.acaoBotoes);

            //Adiciona os botões de forma aleatória
            int posicao = rand.nextInt(((posicionamento.size() - 1) > 0) ? posicionamento.size() - 1 : 1);
            botao.setBounds(posicionamento.get(posicao));
            posicionamento.remove(posicao);

            controle.addBotao(botao);
        }
        
    }
}
