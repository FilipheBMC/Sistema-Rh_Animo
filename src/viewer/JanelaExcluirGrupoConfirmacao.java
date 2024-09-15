package viewer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.CtrlAbstrato;
import controller.CtrlExcluirGrupo;

public class JanelaExcluirGrupoConfirmacao extends ViewerAbstrato {

	//
	// ATRIBUTOS
	//
	private JPanel contentPane;
	private JLabel LbNome;

	/**
	 * Create the frame.
	 */
	public JanelaExcluirGrupoConfirmacao(CtrlAbstrato c, String grupo) {
		super(c);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 236);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton BtOk = new JButton("Sim");
		BtOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CtrlExcluirGrupo ctrl = (CtrlExcluirGrupo) getCtrl();
				ctrl.excluirGrupo();

			}
		});
		BtOk.setFont(new Font("Arial", Font.BOLD, 12));
		BtOk.setBounds(49, 143, 116, 35);
		contentPane.add(BtOk);

		JButton BtCancelar = new JButton("Não");
		BtCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CtrlExcluirGrupo ctrl = (CtrlExcluirGrupo) getCtrl();
				ctrl.encerrar();
			}
		});
		BtCancelar.setFont(new Font("Arial", Font.BOLD, 12));
		BtCancelar.setBounds(219, 143, 116, 35);
		contentPane.add(BtCancelar);

		JLabel LblMenssagem = new JLabel("Tem certeza que deseja excluir o Grupo:");
		LblMenssagem.setIcon(UIManager.getIcon("OptionPane.warningIcon"));
		LblMenssagem.setFont(new Font("Arial", Font.BOLD, 12));
		LblMenssagem.setBounds(49, 25, 286, 73);
		contentPane.add(LblMenssagem);

		LbNome = new JLabel(grupo);
		LbNome.setFont(new Font("Arial", Font.BOLD, 12));
		LbNome.setBounds(88, 77, 100, 14);
		contentPane.add(LbNome);

		this.setVisible(true);

	}

}
