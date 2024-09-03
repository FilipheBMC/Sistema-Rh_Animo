package viewer;

import java.awt.Color;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controller.CtrlAbstrato;
import controller.CtrlSetor;
import model.Setor;

public class JanelaSetor extends ViewerAbstrato {

	//
	// ATRIBUTOS
	//
	private JPanel 			contentPane;
	private CtrlSetor 		ctrl = (CtrlSetor) getCtrl();
	private JTextField 		TfNome;
	private JTextField 		TfCod;
	private JTable 			table;
	private JButton 		BtAdicionar;
	private JButton 		btnAlterar;
	private JButton 		btnConsultar;
	private JButton 		btnExcluir;
	private JButton 		btnSair;
	private Setor[]			listaSetor;

	/**
	 * Create the frame.
	 */
	public JanelaSetor(CtrlAbstrato c, Setor[] listSetor) {
		super(c);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\Brandão\\Videos\\Captures\\Ânimo Consultoria (@animoconsultoria) • Fotos e vídeos do Instagram - Google Chrome 16_03_2024 18_55_16.png"));
		setFont(new Font("Arial Narrow", Font.BOLD, 12));
		setTitle("Consulta de Colaboradores");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 660, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Adicione o WindowListener
		// Ele vai chamar o método encerrar e vai fazer com que sempre que se fechar a
		// janela ele seja chamado
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// Chame o método encerrar do controlador
				ctrl.encerrar();
			}
		});

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome do Setor:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 19, 110, 13);
		contentPane.add(lblNewLabel);

		TfNome = new JTextField();
		TfNome.setBounds(119, 15, 120, 24);
		contentPane.add(TfNome);
		TfNome.setColumns(10);

		JLabel lblCdigoDoSetor = new JLabel("Código do Setor:");
		lblCdigoDoSetor.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCdigoDoSetor.setBounds(252, 20, 110, 13);
		contentPane.add(lblCdigoDoSetor);

		TfCod = new JTextField();
		TfCod.setColumns(10);
		TfCod.setBounds(372, 15, 83, 24);
		contentPane.add(TfCod);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setBackground(UIManager.getColor("Button.background"));
		table.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Lista de colaboradores",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		table.setToolTipText("");
		table.setBounds(10, 143, 626, 232);
		contentPane.add(table);

		BtAdicionar = new JButton("Adicionar");
		BtAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = TfNome.getText();
				String aux = TfCod.getText();
				int codigo;
				
				try {
					codigo = Integer.parseInt(aux);
				}
				catch (NumberFormatException me) {
					notificar("O código do setor deve ser um numeral.");
					return;
				}

				// Adicioanr mensagem de confirmação

				CtrlAbstrato ct = (CtrlAbstrato) getCtrl();
				if (ct instanceof CtrlSetor) {
					CtrlSetor ctrlSetor = (CtrlSetor) getCtrl();
					ctrlSetor.adicionarSetor(nome, codigo);

				}
			}

			private void JOptionPane(String string, String string2) {
				// TODO Auto-generated method stub

			}
		});
		BtAdicionar.setFont(new Font("Arial", Font.BOLD, 12));
		BtAdicionar.setBounds(10, 56, 90, 21);
		contentPane.add(BtAdicionar);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Arial", Font.BOLD, 12));
		btnAlterar.setBounds(110, 56, 90, 21);
		contentPane.add(btnAlterar);

		btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(new Font("Arial", Font.BOLD, 12));
		btnConsultar.setBounds(210, 56, 90, 21);
		contentPane.add(btnConsultar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Arial", Font.BOLD, 12));
		btnExcluir.setBounds(310, 56, 90, 21);
		contentPane.add(btnExcluir);

		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.encerrar();
			}
		});
		btnSair.setFont(new Font("Arial", Font.BOLD, 12));
		btnSair.setBounds(10, 97, 90, 21);
		contentPane.add(btnSair);

		setVisible(true);
	
	}
	
	/**
	 * Atualiza os dados apresentados no JTable da janela
	 */
	public void atualizarDados(Setor[] lstSetor) {
		this.listaSetor = lstSetor;
		HelperTableModel h = new HelperTableModel(listaSetor);
		if(this.table == null)
			this.table = new JTable(h.getTableModel());
		else 
			this.table.setModel(h.getTableModel());
	}

}
