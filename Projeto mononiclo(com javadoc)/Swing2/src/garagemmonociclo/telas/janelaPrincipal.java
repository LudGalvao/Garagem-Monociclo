package garagemmonociclo.telas;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;



/**
 * Classe que representa a janela principal do programa.
 * @author Gustavo
 * @author Lud
 * @author Adryano
 */
public class janelaPrincipal {
	
	/**
     * Método responsável por apresentar o menu da janela principal.
     */
		public static void apresentarMenu() {
			//configura janela principal
			JFrame janelaPrincipal = new JFrame("Cadastro de propriedade no usuario");
			janelaPrincipal.setTitle("jenela Principal");
			janelaPrincipal.setResizable(false);
			janelaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			janelaPrincipal.setSize(800, 600);
			
			// Cria uma barra de menu para a janela principal
				JMenuBar menuBar = new JMenuBar();
			
			// Adiciona a barra de menu ao frame
				janelaPrincipal.setJMenuBar(menuBar);
			
			// Define e adiciona menu na barra de menu
			
				JMenu menuMonociclo   = new JMenu("Monociclo");
				JMenu menuPessoa     = new JMenu("Pessoa");
				JMenu menuPropriedade = new JMenu("Propriedade");
			
				menuBar.add(menuMonociclo);
				menuBar.add(menuPessoa);
				menuBar.add(menuPropriedade);
				
			JMenuItem mMonociclo         = new JMenuItem("Monociclo ");
			JMenuItem mPessoa     = new JMenuItem("Pessoa ");
			JMenuItem mPropriedade = new JMenuItem("Propriedade ");
			
			menuMonociclo.add(mMonociclo);
			menuPessoa.add(mPessoa);
			menuPropriedade.add(mPropriedade);

			JFrame janelaAutomovel    = JanelaMonociclo.CriarJanelaMonociclo();
			JFrame janelaClientes     = JanelaPessoa.criarJanelaPessoa();
			JFrame janelaFuncionarios = janelaPropriedade.CriarJanelaPropriedade();
					
			mMonociclo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					janelaAutomovel.setVisible(true);
				}
			});
			mPessoa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					janelaClientes.setVisible(true);
				}
			});
			mPropriedade.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					janelaFuncionarios.setVisible(true);
				}
			});
			
			
			janelaPrincipal.setVisible(true);
			
		}
	

	/**
	 * Método principal que inicia a aplicação.
	 *
	 * @param args Argumentos da linha de comando.
	*/
	public static void main(String[] args) {
		apresentarMenu();
		}
}