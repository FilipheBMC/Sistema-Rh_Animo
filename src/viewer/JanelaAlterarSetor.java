package viewer;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.CtrlAbstrato;
import controller.CtrlAlterarSetor;
import model.ModelException;
import model.Setor;

public class JanelaAlterarSetor extends ViewerAbstrato {

	//
	// ATRIBUTOS
	//
	private JPanel contentPane;
	private JTextField TfNome;
	private JTextField TfCodSetor;

	//
	// MÉTODOS
	//
	public JanelaAlterarSetor(CtrlAbstrato c, Setor setor) {
		super(c);
		setTitle("Alterar setor");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Brandão\\3D Objects\\Projetos\\Rh Animo JAva\\rhAnimo\\animo.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(00, 100, 660, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Ele vai chamar o método encerrar e vai fazer com que sempre que se fechar a
		// janela ele seja chamado
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// Chame o método encerrar do controlador
				CtrlAlterarSetor ctrl = (CtrlAlterarSetor) getCtrl();
				ctrl.encerrar();
			}
		});

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome setor;");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 10, 89, 19);
		contentPane.add(lblNewLabel);

		TfNome = new JTextField(setor.getNomeSetor());
		TfNome.setBounds(10, 39, 118, 26);
		contentPane.add(TfNome);
		TfNome.setColumns(10);

		JLabel lblCdigoSetor = new JLabel("Código Setor:");
		lblCdigoSetor.setFont(new Font("Arial", Font.BOLD, 12));
		lblCdigoSetor.setBounds(172, 10, 89, 19);
		contentPane.add(lblCdigoSetor);

		TfCodSetor = new JTextField(codSetor(setor));
		TfCodSetor.setColumns(10);
		TfCodSetor.setBounds(172, 39, 118, 26);
		contentPane.add(TfCodSetor);

		JButton BtAlterar = new JButton("Alterar");
		BtAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aux = TfCodSetor.getText();
				int codSet = 0;
				String nome = TfNome.getText();

				try {
					codSet = Integer.parseInt(aux);
				} catch (Exception me) {
					notificar("O codigo do setor deve ser numeral.");
				}

				CtrlAlterarSetor ctrl = (CtrlAlterarSetor) getCtrl();
				ctrl.alteracaoSetor(nome, codSet);

			}
		});
		BtAlterar.setFont(new Font("Arial", Font.BOLD, 12));
		BtAlterar.setBounds(10, 291, 293, 56);
		contentPane.add(BtAlterar);

		JButton BtCancelar = new JButton("Cancelar");
		BtCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CtrlAlterarSetor ctrl = (CtrlAlterarSetor) getCtrl();
				ctrl.encerrar();
			}
		});
		BtCancelar.setFont(new Font("Arial", Font.BOLD, 12));
		BtCancelar.setBounds(311, 291, 293, 56);
		contentPane.add(BtCancelar);

		this.setVisible(true);
	}

	private String codSetor(Setor setor) {
		int cod = setor.getCodSetor();
		String codString = Integer.toString(cod); // Converte o int em String
		return codString;
	}
}
