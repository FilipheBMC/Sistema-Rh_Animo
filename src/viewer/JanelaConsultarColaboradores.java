package viewer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import controller.CtrlConsultarColaborador;

public class JanelaConsultarColaboradores extends ViewerAbstrato {

	
	//
	//	ATRIBUTOS
	//
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaConsultarColaboradores frame = new JanelaConsultarColaboradores();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public JanelaConsultarColaboradores(CtrlAbstrato c) {
		super(c);
		setFont(new Font("Arial Narrow", Font.BOLD, 12));
		setTitle("Consulta de Colaboradores");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 660, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
	    // Adicione o WindowListener
		//Ele vai chamar o método encerrar e vai fazer com que sempre que se fechar a janela ele seja chamado
	    this.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	            // Chame o método encerrar do controlador
	            CtrlConsultarColaborador ctrl = (CtrlConsultarColaborador) getCtrl();
	            ctrl.encerrar();
	        }
	    });

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setBackground(UIManager.getColor("Button.background"));
		table.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Lista de colaboradores", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		table.setToolTipText("");
		table.setBounds(10, 143, 626, 232);
		contentPane.add(table);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 48, 19);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(88, 11, 104, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Setor:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(222, 48, 48, 19);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Selecione...", "Ti", "Recursos Humanos", "Financeiro", "Comercial", "Marketing", "Projetos"}));
		comboBox.setBounds(269, 49, 125, 21);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1_1 = new JLabel("Cargo:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(436, 49, 48, 19);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Equipe:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(436, 14, 48, 19);
		contentPane.add(lblNewLabel_1_1_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(494, 10, 125, 21);
		contentPane.add(comboBox_1);
		
		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setBounds(494, 49, 125, 21);
		contentPane.add(comboBox_1_1);
		
		JLabel lblDataDeNascimento = new JLabel("Data Nasc:");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDeNascimento.setBounds(10, 47, 95, 21);
		contentPane.add(lblDataDeNascimento);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(88, 49, 104, 21);
		contentPane.add(textField_1);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpf.setBounds(222, 10, 95, 21);
		contentPane.add(lblCpf);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(269, 12, 125, 21);
		contentPane.add(textField_2);
		
		JLabel lblDataSada = new JLabel("Data Saída:");
		lblDataSada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataSada.setBounds(10, 84, 95, 21);
		contentPane.add(lblDataSada);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(88, 84, 104, 21);
		contentPane.add(textField_3);
		
		JLabel lblDataEntrada = new JLabel("Dt Entrada:");
		lblDataEntrada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataEntrada.setBounds(222, 84, 95, 21);
		contentPane.add(lblDataEntrada);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(297, 86, 97, 21);
		contentPane.add(textField_4);
		
		JButton btnNewButton = new JButton("Consultar");
		btnNewButton.setBounds(10, 115, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(107, 115, 85, 21);
		contentPane.add(btnExcluir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CtrlConsultarColaborador ctrl  = (CtrlConsultarColaborador)getCtrl();
				ctrl.iniciarAlterarColaborador();
			}
		});
		btnAlterar.setBounds(202, 115, 85, 21);
		contentPane.add(btnAlterar);
		
		//	CHAMA O CTRLINCIARADICIONAR COLABORADOR.
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Utilizando o comando para adicionar pessoa
				CtrlConsultarColaborador ctrl = (CtrlConsultarColaborador)getCtrl();
				ctrl.iniciarAdicionarPessoa();
				
			}
		});
		btnAdicionar.setBounds(297, 115, 85, 21);
		contentPane.add(btnAdicionar);
		
		JButton BtEncerrar = new JButton("Fechar");
		BtEncerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CtrlConsultarColaborador ctrl = (CtrlConsultarColaborador)getCtrl();
				ctrl.encerrar();
			}
		});
		BtEncerrar.setBounds(392, 115, 85, 21);
		contentPane.add(BtEncerrar);
		
		this.setVisible(true);
	}
}
