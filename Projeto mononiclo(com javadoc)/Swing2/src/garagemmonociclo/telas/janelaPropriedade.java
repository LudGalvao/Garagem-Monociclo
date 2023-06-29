package garagemmonociclo.telas;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import garagemmonociclo.classes.Propriedade;

/**
 * Classe que representa a janela de propriedade.
 * @author Gustavo
 * @author Lud
 * @author Adryano
 */
public class janelaPropriedade {

    /**
     * Cria e retorna a janela de propriedade.
     *
     * @return a janela de propriedade
     */
    public static JFrame CriarJanelaPropriedade() {
        JFrame janelaPropriedade = new JFrame("Propriedades"); // Janela Normal
        janelaPropriedade.setResizable(false); // A janela não poderá ter o tamanho ajustado
        janelaPropriedade.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        janelaPropriedade.setSize(500, 360); // Define tamanho da janela

        // Define o layout da janela
        Container caixa = janelaPropriedade.getContentPane();
        caixa.setLayout(null);

        // Define os labels dos campos
        JLabel labelidPropriedade = new JLabel("Id Propriedade: ");
        JLabel labelidPessoa = new JLabel("Id Pessoa: ");
        JLabel labelidProduto = new JLabel("Id Produto: ");
        JLabel labeldataAquisicao = new JLabel("Data Aquisiçao: ");
        JLabel labeldescricao = new JLabel("Descricao: ");

        // Posiciona os labels na janela
        labelidPropriedade.setBounds(50, 40, 120, 20); // coluna, linha, largura, tamanho
        labelidPessoa.setBounds(50, 70, 120, 20); // coluna, linha, largura, tamanho
        labelidProduto.setBounds(50, 100, 120, 20);
        labeldataAquisicao.setBounds(50, 130, 120, 20);
        labeldescricao.setBounds(50, 160, 120, 20);

        // Define os input box
        JTextField jTextidPropriedade = new JTextField();
        JTextField jTextidPessoa = new JTextField();
        JTextField jTextidProduto = new JTextField();
        JTextField jTextdataAquisicao = new JTextField();
        JTextField jTextdescricao = new JTextField();

        // Define se os campos estão habilitados ou não no início
        jTextidPropriedade.setEnabled(true);
        jTextidPessoa.setEnabled(true);
        jTextidProduto.setEnabled(true);
        jTextdataAquisicao.setEnabled(true);
        jTextdescricao.setEnabled(true);

        // Posiciona os input box
        jTextidPropriedade.setBounds(140, 40, 160, 20);
        jTextidPessoa.setBounds(140, 70, 160, 20);
        jTextidProduto.setBounds(140, 100, 160, 20);
        jTextdataAquisicao.setBounds(140, 130, 160, 20);
        jTextdescricao.setBounds(140, 160, 160, 20);

        // Adiciona os rótulos e os input box na janela
        janelaPropriedade.add(labelidPropriedade);
        janelaPropriedade.add(labelidPessoa);
        janelaPropriedade.add(labelidProduto);
        janelaPropriedade.add(labeldataAquisicao);
        janelaPropriedade.add(labeldescricao);
        janelaPropriedade.add(jTextidPropriedade);
        janelaPropriedade.add(jTextidPessoa);
        janelaPropriedade.add(jTextidProduto);
        janelaPropriedade.add(jTextdataAquisicao);
        janelaPropriedade.add(jTextdescricao);
        Propriedade propriedade = new Propriedade();

        JButton botaoConsultar = new JButton("Consultar");
        botaoConsultar.setBounds(310, 40, 100, 20);
        janelaPropriedade.add(botaoConsultar);

        JButton botaoAtualizar = new JButton("Atualizar");
        botaoAtualizar.setBounds(310, 70, 100, 20);
        janelaPropriedade.add(botaoAtualizar);
        botaoAtualizar.setEnabled(true);

        JButton botaoExcluir = new JButton("Excluir");
        botaoExcluir.setBounds(310, 130, 100, 20);
        janelaPropriedade.add(botaoExcluir);
        botaoExcluir.setEnabled(true);

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBounds(310, 100, 100, 20);
        janelaPropriedade.add(botaoCadastrar);
        botaoCadastrar.setEnabled(true);

        JButton botaoLimpar = new JButton("Limpar");
        botaoLimpar.setBounds(310, 190, 100, 20);
        janelaPropriedade.add(botaoLimpar);

        /**
         * Ação executada ao clicar no botão "Cadastrar".
         */
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (JOptionPane.showConfirmDialog(janelaPropriedade, "Tem Certeza?") == 0) {
                        JOptionPane.showMessageDialog(janelaPropriedade, "Cadastro realizado");

                        int idPropriedade = Integer.parseInt(jTextidPropriedade.getText());
                        int idPessoa = Integer.parseInt(jTextidPessoa.getText());
                        int idProduto = Integer.parseInt(jTextidProduto.getText());
                        String dataAquisicao = jTextdataAquisicao.getText();
                        String descricao = jTextdescricao.getText();

                        if (!propriedade.consultarPropriedade(idPropriedade)) {
                            propriedade.cadastrarPropriedade(idPropriedade, idPessoa, idProduto, dataAquisicao,
                                    descricao);
                            JOptionPane.showMessageDialog(janelaPropriedade, "Cadastro nao realizado");
                        } else {
                            JOptionPane.showMessageDialog(janelaPropriedade, "Propriedade já cadastrada");
                            botaoCadastrar.setEnabled(false);
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    JOptionPane.showMessageDialog(janelaPropriedade, "Erro ao cadastrar propriedade");
                }
            }
        });

        /**
         * Ação executada ao clicar no botão "Atualizar".
         */
        botaoAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (JOptionPane.showConfirmDialog(janelaPropriedade, "Tem Certeza?") == 0) {
                        JOptionPane.showMessageDialog(janelaPropriedade, "Atualizacao realizada");

                        int idPropriedade = Integer.parseInt(jTextidPropriedade.getText());
                        int idPessoa = Integer.parseInt(jTextidPessoa.getText());
                        int idProduto = Integer.parseInt(jTextidProduto.getText());
                        String dataAquisicao = jTextdataAquisicao.getText();
                        String descricao = jTextdescricao.getText();

                        if (!propriedade.atualizarPropriedade(idPropriedade, idPessoa, idProduto, dataAquisicao,
                                descricao)) {
                            JOptionPane.showMessageDialog(janelaPropriedade, "Não foi possível atualizar a propriedade");
                        } else {
                            JOptionPane.showMessageDialog(janelaPropriedade, "Atualização realizada");
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(janelaPropriedade, "Verifique os campos numéricos");
                } catch (Exception ex) {
                    // TODO: Trate qualquer outra exceção que possa ocorrer durante a atualização
                }
            }
        });

        /**
         * Ação executada ao clicar no botão "Excluir".
         */
        botaoExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (JOptionPane.showConfirmDialog(janelaPropriedade, "Tem Certeza?") == 0) {
                        JOptionPane.showMessageDialog(janelaPropriedade, "Cadastro excluido");

                        // Aqui você pode adicionar a lógica para excluir a pessoa com base no ID
                        int idPropriedade = Integer.parseInt(jTextidPessoa.getText());
                        propriedade.removerPropriedade(idPropriedade);
                        // Adicione qualquer lógica adicional que você precise após excluir a pessoa
                    }
                } catch (Exception e2) {
                    // TODO: Trate qualquer exceção que possa ocorrer ao excluir a pessoa
                }
            }
        });

        /**
         * Ação executada ao clicar no botão "Consultar".
         */
        botaoConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!propriedade.consultarPropriedade(Integer.parseInt(jTextidPropriedade.getText()))) {
                        JOptionPane.showMessageDialog(janelaPropriedade,
                                "Propriedade não encontrada, tente novamente.");
                        botaoCadastrar.setEnabled(true);
                    } else {
                        jTextidPessoa.setText(String.valueOf(propriedade.getIdPessoa()));
                        jTextidProduto.setText(String.valueOf(propriedade.getIdProduto()));
                        jTextdataAquisicao.setText(propriedade.getDataAquisicao());
                        jTextdescricao.setText(propriedade.getDescricao());

                        jTextidPessoa.setEnabled(true);
                        jTextidProduto.setEnabled(true);
                        jTextdataAquisicao.setEnabled(true);
                        jTextdescricao.setEnabled(true);

                        botaoCadastrar.setEnabled(true);
                        botaoAtualizar.setEnabled(true);

                        JOptionPane.showMessageDialog(janelaPropriedade, "Propriedade encontrada");
                        botaoAtualizar.setEnabled(true);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });

        /**
         * Ação executada ao clicar no botão "Limpar".
         */
        botaoLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jTextidPropriedade.setText(""); // Limpar campo
                jTextidPessoa.setText(""); // Limpar campo
                jTextidProduto.setText(""); // Limpar campo
                jTextdataAquisicao.setText("");
                jTextdescricao.setText("");

                jTextidPessoa.requestFocus(); // Colocar o foco em um campo
            }
        });

        return janelaPropriedade;
    }
}
