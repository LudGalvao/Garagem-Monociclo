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

import garagemmonociclo.classes.Pessoa;

/**
 * Classe responsável por criar a janela de cadastro, consulta, atualização e exclusão de pessoas.
 * @author Gustavo
 * @author Lud
 * @author Adryano
 */
public class JanelaPessoa {
    
    /**
     * Cria a janela de pessoa.
     *
     * @return a janela de pessoa criada
     */
    public static JFrame criarJanelaPessoa() {

        // Define a janelaClientes
        JFrame janelaPessoa = new JFrame("Pessoa"); // Janela Normal
        janelaPessoa.setResizable(false); // A janela não poderá ter o tamanho ajustado
        janelaPessoa.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        janelaPessoa.setSize(500, 360); // Define tamanho da janela

        // Define o layout da janela
        Container caixa = janelaPessoa.getContentPane();
        caixa.setLayout(null);

        // Define os labels dos campos
        JLabel labelidPessoa = new JLabel("Id Pessoa: ");
        JLabel labelnome = new JLabel("Nome: ");
        JLabel labelsobrenome = new JLabel("Sobrenome: ");
        JLabel labeldataNascimento = new JLabel("Data Nasci: ");

        // Posiciona os labels na janela
        labelidPessoa.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
        labelnome.setBounds(50, 70, 100, 20); // coluna, linha, largura, tamanho
        labelsobrenome.setBounds(50, 100, 100, 20);
        labeldataNascimento.setBounds(50, 130, 100, 20);

        // Define os input box
        JTextField jTextidPessoa = new JTextField();
        JTextField jTextnome = new JTextField();
        JTextField jTextsobrenome = new JTextField();
        JTextField jTextdataNascimento = new JTextField();

        // Define se os campos estão habilitados ou não no início
        jTextidPessoa.setEnabled(true);
        jTextnome.setEnabled(true);
        jTextsobrenome.setEnabled(true);
        jTextdataNascimento.setEnabled(true);

        // Posiciona os input box
        jTextidPessoa.setBounds(120, 40, 160, 20);
        jTextnome.setBounds(120, 70, 160, 20);
        jTextsobrenome.setBounds(120, 100, 160, 20);
        jTextdataNascimento.setBounds(120, 130, 160, 20);

        // Adiciona os rótulos e os input box na janela
        janelaPessoa.add(labelidPessoa);
        janelaPessoa.add(labelnome);
        janelaPessoa.add(labelsobrenome);
        janelaPessoa.add(labeldataNascimento);
        janelaPessoa.add(jTextidPessoa);
        janelaPessoa.add(jTextnome);
        janelaPessoa.add(jTextsobrenome);
        janelaPessoa.add(jTextdataNascimento);

        // Define botões e a localização deles na janela
        JButton botaoConsultar = new JButton("Consultar");
        botaoConsultar.setBounds(300, 40, 100, 20);
        janelaPessoa.add(botaoConsultar);

        JButton botaoAtualizar = new JButton("Atualizar");
        botaoAtualizar.setBounds(300, 70, 100, 20);
        janelaPessoa.add(botaoAtualizar);
        botaoAtualizar.setEnabled(true);

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBounds(300, 100, 100, 20);
        janelaPessoa.add(botaoCadastrar);
        botaoCadastrar.setEnabled(true);

        JButton botaoExcluir = new JButton("Excluir");
        botaoExcluir.setBounds(300, 130, 100, 20);
        janelaPessoa.add(botaoExcluir);
        botaoExcluir.setEnabled(true);

        JButton botaoLimpar = new JButton("Limpar");
        botaoLimpar.setBounds(300, 190, 100, 20);
        janelaPessoa.add(botaoLimpar);

        Pessoa pessoa = new Pessoa();

        /**
         * Ação executada ao clicar no botão "Consultar".
         */
        botaoConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!pessoa.consultarPessoa(Integer.parseInt(jTextidPessoa.getText()))) {
                        JOptionPane.showMessageDialog(janelaPessoa, "Pessoa não encontrada, tente novamente.");
                        botaoCadastrar.setEnabled(true);
                    } else {
                        jTextnome.setText(pessoa.getNome());
                        jTextsobrenome.setText(pessoa.getSobrenome());
                        jTextdataNascimento.setText(pessoa.getDataNascimento());

                        jTextnome.setEnabled(true);
                        jTextsobrenome.setEnabled(true);
                        jTextdataNascimento.setEnabled(true);
                        botaoCadastrar.setEnabled(true);
                        botaoAtualizar.setEnabled(true);
                        JOptionPane.showMessageDialog(janelaPessoa, "Pessoa encontrada!");
                        botaoAtualizar.setEnabled(true);
                    }
                } catch (Exception e2) {
                    // TODO: Trate qualquer exceção que possa ocorrer ao consultar a pessoa
                }
            }
        });

        /**
         * Ação executada ao clicar no botão "Atualizar".
         */
        botaoAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (JOptionPane.showConfirmDialog(janelaPessoa, "Tem Certeza?") == 0) {
                        if (!pessoa.atualizarPessoa(Integer.parseInt(jTextidPessoa.getText()), jTextnome.getText(),
                                jTextsobrenome.getText(), jTextdataNascimento.getText())) {
                            JOptionPane.showMessageDialog(janelaPessoa, "Não foi possível atualizar a pessoa");
                        } else {
                            JOptionPane.showMessageDialog(janelaPessoa, "Atualização realizada");
                        }
                    }
                } catch (Exception e2) {
                    // TODO: Trate qualquer exceção que possa ocorrer ao atualizar a pessoa
                }
            }
        });

        /**
         * Ação executada ao clicar no botão "Cadastrar".
         */
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (JOptionPane.showConfirmDialog(janelaPessoa, "Tem Certeza?") == 0) {
                        if (!pessoa.consultarPessoa(Integer.parseInt(jTextidPessoa.getText()))) {
                            pessoa.cadastrarPessoa(Integer.parseInt(jTextidPessoa.getText()), jTextnome.getText(),
                                    jTextsobrenome.getText(), jTextdataNascimento.getText());
                            JOptionPane.showMessageDialog(janelaPessoa, "Cadastro realizado com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(janelaPessoa, "Pessoa já cadastrada");
                            botaoCadastrar.setEnabled(true);
                        }
                    }
                } catch (Exception e2) {
                    // TODO: Trate qualquer exceção que possa ocorrer ao cadastrar a pessoa
                }
            }
        });

        /**
         * Ação executada ao clicar no botão "Excluir".
         */
        botaoExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (JOptionPane.showConfirmDialog(janelaPessoa, "Tem Certeza?") == 0) {
                        int idPessoa = Integer.parseInt(jTextidPessoa.getText());
                        pessoa.removerPessoa(idPessoa);
                        JOptionPane.showMessageDialog(janelaPessoa, "Cadastro excluído com sucesso!");
                    }
                } catch (Exception e2) {
                    // TODO: Trate qualquer exceção que possa ocorrer ao excluir a pessoa
                }
            }
        });

        /**
         * Ação executada ao clicar no botão "Limpar".
         */
        botaoLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jTextidPessoa.setText(""); // Limpar campo
                jTextnome.setText(""); // Limpar campo
                jTextsobrenome.setText(""); // Limpar campo
                jTextdataNascimento.setText("");

                jTextidPessoa.requestFocus(); // Colocar o foco em um campo
            }
        });

        return janelaPessoa;
    }
}
