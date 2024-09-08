package viewer;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CtrlAbstrato;
import controller.CtrlAdicionarGrupo;

public class JanelaAdicionarGrupo extends ViewerAbstrato {

	//
	//	ATRIBUTOS
	//
	private JPanel contentPane;

	//
	//	MÉTODOS
	//
	
	/**
	 * Create the frame.
	 */
	public JanelaAdicionarGrupo(CtrlAdicionarGrupo c) {
		super(c);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\Brandão\\Videos\\Captures\\Ânimo Consultoria (@animoconsultoria) • Fotos e vídeos do Instagram - Google Chrome 16_03_2024 18_55_16.png"));
		setTitle("Janela Adicionar Colaborador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		setVisible(true);
	}
}
