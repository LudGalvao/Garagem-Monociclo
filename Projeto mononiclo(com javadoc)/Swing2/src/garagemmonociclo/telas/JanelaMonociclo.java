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

import garagemmonociclo.classes.Monociclo;

/**
 * Classe responsável por criar a janela de interação com Monociclos.
 * @author Gustavo
 * @author Lud
 * @author Adryano
 */
public class JanelaMonociclo {
	/**
     * Método para criar a janela de Monociclos.
     *
     * @return JFrame contendo a janela de Monociclos.
     */
	public static JFrame CriarJanelaMonociclo() {
		JFrame janelaMonociclo = new JFrame("Monociclos");
		janelaMonociclo.setResizable(false);
		janelaMonociclo.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaMonociclo.setSize(500, 360);

		Container caixa = janelaMonociclo.getContentPane();
		caixa.setLayout(null);

		JLabel labelidProduto = new JLabel("Id Produto: ");
		JLabel labelmarca = new JLabel("Marca: ");
		JLabel labelmodelo = new JLabel("Modelo: ");
		JLabel labelcor = new JLabel("Cor: ");
		JLabel labelanoFabricacao = new JLabel("Ano Fabrica: ");

		// Posiciona os labels na janela
		labelidProduto.setBounds(60, 40, 100, 20); // coluna, linha, largura, tamanho
		labelmarca.setBounds(60, 70, 100, 20); // coluna, linha, largura, tamanho
		labelmodelo.setBounds(60, 100, 100, 20);
		labelcor.setBounds(60, 130, 100, 20);
		labelanoFabricacao.setBounds(60, 160, 100, 20);

		// Define os input box
		JTextField jTextidProduto = new JTextField();
		JTextField jTextmarca = new JTextField();
		JTextField jTextmodelo = new JTextField();
		JTextField jTextcor = new JTextField();
		JTextField jTextanoFabricacao = new JTextField();

		// Define se os campos estão habilitados ou não no início
		jTextidProduto.setEnabled(true);
		jTextmarca.setEnabled(true);
		jTextmodelo.setEnabled(true);
		jTextcor.setEnabled(true);
		jTextanoFabricacao.setEnabled(true);

		// Posiciona os input box
		jTextidProduto.setBounds(130, 40, 160, 20);
		jTextmarca.setBounds(130, 70, 160, 20);
		jTextmodelo.setBounds(130, 100, 160, 20);
		jTextcor.setBounds(130, 130, 160, 20);
		jTextanoFabricacao.setBounds(130, 160, 160, 20);

		// Adiciona os rótulos e os input box na janela
		janelaMonociclo.add(labelidProduto);
		janelaMonociclo.add(labelmarca);
		janelaMonociclo.add(labelmodelo);
		janelaMonociclo.add(labelcor);
		janelaMonociclo.add(labelanoFabricacao);
		janelaMonociclo.add(jTextidProduto);
		janelaMonociclo.add(jTextmarca);
		janelaMonociclo.add(jTextmodelo);
		janelaMonociclo.add(jTextcor);
		janelaMonociclo.add(jTextanoFabricacao);

		// Define botões e a localização deles na janela
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(300, 40, 100, 20);
		janelaMonociclo.add(botaoConsultar);

		JButton botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.setBounds(300, 70, 100, 20);
		janelaMonociclo.add(botaoAtualizar);
		botaoAtualizar.setEnabled(true);

		JButton botaoCadastrar = new JButton("Cadastrar");
		botaoCadastrar.setBounds(300, 100, 100, 20);
		janelaMonociclo.add(botaoCadastrar);
		botaoCadastrar.setEnabled(true);

		JButton botaoExcluir = new JButton("Excluir");
		botaoExcluir.setBounds(300, 130, 100, 20);
		janelaMonociclo.add(botaoExcluir);
		botaoExcluir.setEnabled(true);

		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(300, 190, 100, 20);
		janelaMonociclo.add(botaoLimpar);

		Monociclo monociclo = new Monociclo();

		botaoConsultar.addActionListener(new ActionListener() {
			/**
             * Método para tratar o evento do botão "Consultar".
             *
             * @param e evento de ação.
             */
			public void actionPerformed(ActionEvent e) {
				try {
					if (!monociclo.consultarMonociclo(Integer.parseInt(jTextidProduto.getText()))) {
						JOptionPane.showMessageDialog(janelaMonociclo, "Monociclo nao encontrado, tente novamente.");

						botaoCadastrar.setEnabled(true);
					} else {

						jTextidProduto.setText(Integer.toString(monociclo.getIdProduto()));
						jTextmarca.setText(monociclo.getMarca());
						jTextmodelo.setText(monociclo.getModelo());
						jTextcor.setText(monociclo.getCor());
						jTextanoFabricacao.setText(Integer.toString(monociclo.getAnoFabricacao()));

						jTextmarca.setEnabled(true);
						jTextmodelo.setEnabled(true);
						jTextcor.setEnabled(true);
						jTextanoFabricacao.setEnabled(true);
						botaoAtualizar.setEnabled(true);
						botaoCadastrar.setEnabled(true);
						JOptionPane.showMessageDialog(janelaMonociclo, "Monociclo encontrado!");
					}

				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});

		botaoAtualizar.addActionListener(new ActionListener() {
			/**
             * Método para tratar o evento do botão "Atualizar".
             *
             * @param e evento de ação.
             */
			public void actionPerformed(ActionEvent e) {
				try {
					if (JOptionPane.showConfirmDialog(janelaMonociclo, "Tem Certeza?") == 0)

						JOptionPane.showMessageDialog(janelaMonociclo, "Monociclo Atualizado");
					{
						if (!monociclo.atualizarMonociclo(Integer.parseInt(jTextidProduto.getText()),
								jTextmarca.getText(), jTextmodelo.getText(), jTextcor.getText(),
								Integer.parseInt(jTextanoFabricacao.getText()))) {
							JOptionPane.showMessageDialog(janelaMonociclo, "Não foi possivel atualizar o Monociclo");
						} else {
							JOptionPane.showMessageDialog(janelaMonociclo, "Atualização realizada");
						}

					}

				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});

		botaoCadastrar.addActionListener(new ActionListener() {
			/**
             * Método para tratar o evento do botão "Cadastrar".
             *
             * @param e evento de ação.
             */
			public void actionPerformed(ActionEvent e) {
				try {
					if (JOptionPane.showConfirmDialog(janelaMonociclo, "Tem Certeza?") == 0) 
						JOptionPane.showMessageDialog(janelaMonociclo, "Monociclo Cadastrado");{
						if (!monociclo.consultarMonociclo(Integer.parseInt(jTextidProduto.getText()))) {
							monociclo.cadastrarMonociclo(Integer.parseInt(jTextidProduto.getText()),
									jTextmarca.getText(), jTextmodelo.getText(), jTextcor.getText(),
									Integer.parseInt(jTextanoFabricacao.getText()));
						} else {
							JOptionPane.showMessageDialog(janelaMonociclo, "Monociclo já cadastrado");
							botaoCadastrar.setEnabled(true);
						}
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});

		botaoExcluir.addActionListener(new ActionListener() {
			/**
             * Método para tratar o evento do botão "Excluir".
             *
             * @param e evento de ação.
             */
			public void actionPerformed(ActionEvent e) {
				try {
					if (JOptionPane.showConfirmDialog(janelaMonociclo, "Tem Certeza?") == 0) {
						int idProduto = Integer.parseInt(jTextidProduto.getText());
						if (monociclo.consultarMonociclo(idProduto)) {
							monociclo.removerMonociclo(idProduto);
							JOptionPane.showMessageDialog(janelaMonociclo, "Monociclo removido com sucesso");
							// Limpar os campos de entrada após a exclusão
							jTextidProduto.setText("");
							jTextmarca.setText("");
							jTextmodelo.setText("");
							jTextcor.setText("");
							jTextanoFabricacao.setText("");
						} else {
							JOptionPane.showMessageDialog(janelaMonociclo, "Monociclo não encontrado");
						}
					}
				} catch (Exception e2) {
					// Tratar exceção, se necessário
				}
			}
		});

		botaoLimpar.addActionListener(new ActionListener() {
			/**
             * Método para tratar o evento do botão "Limpar".
             *
             * @param e evento de ação.
             */
			public void actionPerformed(ActionEvent e) {
				jTextidProduto.setText(""); // Limpar campo
				jTextmarca.setText(""); // Limpar campo
				jTextmodelo.setText(""); // Limpar campo
				jTextcor.setText("");
				jTextanoFabricacao.setText(""); // Limpar campo

				jTextidProduto.requestFocus(); // Colocar o foco em um campo
			}
		});

		return janelaMonociclo;
	}

}
