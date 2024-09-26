package viewer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.CtrlAbstrato;
import controller.CtrlConsultarColaborador;
import model.Colaborador;
import model.Grupo;
import model.Setor;

public class JanelaConsultarColaboradores extends ViewerAbstrato {

	//
	// ATRIBUTOS
	//

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField TfNome;
	private JTextField TfDtNascimeto;
	private JComboBox cbSexo;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private Colaborador[] listColaborador;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { JanelaConsultarColaboradores frame =
	 * new JanelaConsultarColaboradores(); frame.setVisible(true); } catch
	 * (Exception e) { e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public JanelaConsultarColaboradores(CtrlConsultarColaborador c, Colaborador[] arrayColaborador) {
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
				CtrlConsultarColaborador ctrl = (CtrlConsultarColaborador) getCtrl();
				ctrl.encerrar();
			}
		});

		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.atualizarDados(arrayColaborador);
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setBackground(UIManager.getColor("Button.background"));
		table.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Lista de colaboradores",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		table.setToolTipText("");
		table.setBounds(10, 143, 626, 232);
		contentPane.add(table);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 48, 19);
		contentPane.add(lblNewLabel);

		TfNome = new JTextField();
		TfNome.setBounds(88, 11, 104, 21);
		contentPane.add(TfNome);
		TfNome.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Setor:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(222, 48, 48, 19);
		contentPane.add(lblNewLabel_1);

		//Resovler aqui, colocar a lista para fazer com que se possa escolher conforme os setores existentes.
		JComboBox cbSetor = new JComboBox();
		cbSetor.setModel(new DefaultComboBoxModel(new String[] { "Selecione...", "Ti", "Recursos Humanos",
				"Financeiro", "Comercial", "Marketing", "Projetos" }));
		cbSetor.setBounds(269, 49, 125, 21);
		contentPane.add(cbSetor);

		JLabel lblNewLabel_1_1 = new JLabel("Cargo:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(436, 49, 48, 19);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Grupo:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(436, 14, 48, 19);
		contentPane.add(lblNewLabel_1_1_1);

		JComboBox cbGrupo = new JComboBox();
		cbGrupo.setBounds(494, 10, 125, 21);
		contentPane.add(cbGrupo);

		JComboBox cbCargo = new JComboBox();
		cbCargo.setBounds(494, 49, 125, 21);
		contentPane.add(cbCargo);

		JLabel lblDataDeNascimento = new JLabel("Data Nasc:");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDeNascimento.setBounds(10, 47, 95, 21);
		contentPane.add(lblDataDeNascimento);

		TfDtNascimeto = new JTextField();
		TfDtNascimeto.setColumns(10);
		TfDtNascimeto.setBounds(88, 49, 104, 21);
		contentPane.add(TfDtNascimeto);

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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = TfNome.getText();
				String dtNascimento = TfDtNascimeto.getText();
				String sexo = (String) cbSexo.getSelectedObjects().toString();
				Setor setor = (Setor)cbSetor.getSelectedItem();
				Grupo grupo = (Grupo)cbGrupo.getSelectedItem();
				
				CtrlConsultarColaborador ctrl = (CtrlConsultarColaborador)getCtrl();
				ctrl.conuslta(nome, dtNascimento, setor, grupo, sexo);
				
			}
		});
		btnNewButton.setBounds(10, 115, 85, 21);
		contentPane.add(btnNewButton);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Colaborador colab = receberColaborador();
				if(colab == null)
					notificar("Selecione um colaborador para poder exclui-lo.");
				
				CtrlAbstrato ctrlAbs = (CtrlAbstrato) getCtrl();
				if (ctrlAbs instanceof CtrlConsultarColaborador) {
					CtrlConsultarColaborador ctrl = (CtrlConsultarColaborador) getCtrl();
					ctrl.iniciarExcluirColaborador(colab);
				}
				else {
					notificar("Tem de escolher um colaborador válido para se excluir.");
				}

			}
		});
		btnExcluir.setBounds(107, 115, 85, 21);
		contentPane.add(btnExcluir);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Colaborador colaborador = receberColaborador();
				
				if(colaborador == null) {
					notificar("Tem de se selionar um colaborador para poder altera-lo.");
					return;
				}
				
				if(colaborador instanceof Colaborador) {
					CtrlConsultarColaborador ctrl = (CtrlConsultarColaborador) getCtrl();
					ctrl.iniciarAlterarColaborador(colaborador);
				}

			}
		});
		btnAlterar.setBounds(202, 115, 85, 21);
		contentPane.add(btnAlterar);

		// CHAMA O CTRLINCIARADICIONAR COLABORADOR.
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Utilizando o comando para adicionar pessoa
				CtrlConsultarColaborador ctrl = (CtrlConsultarColaborador) getCtrl();
				ctrl.iniciarAdicionarPessoa();

			}
		});
		btnAdicionar.setBounds(297, 115, 87, 21);
		contentPane.add(btnAdicionar);

		JButton BtEncerrar = new JButton("Fechar");
		BtEncerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CtrlConsultarColaborador ctrl = (CtrlConsultarColaborador) getCtrl();
				ctrl.encerrar();
			}
		});
		BtEncerrar.setBounds(399, 115, 85, 21);
		contentPane.add(BtEncerrar);
		
		JLabel sdasmndajsd = new JLabel("Sexo:");
		sdasmndajsd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sdasmndajsd.setBounds(436, 90, 48, 19);
		contentPane.add(sdasmndajsd);
		
		cbSexo = new JComboBox();
		cbSexo.setModel(new DefaultComboBoxModel(new String[] {"Selecione...", "Masculino", "Feminino"}));
		cbSexo.setBounds(494, 86, 125, 21);
		contentPane.add(cbSexo);

		this.setVisible(true);
	}

	/**
	 * Atualiza os dados apresentados no JTable da janela
	 */
//	public void atualizarDados(Colaborador[] lstColaborador) {
//		this.listColaborador = lstColaborador;
//		HelperTableModel h = new HelperTableModel(listColaborador);
//		if (this.table == null)
//			this.table = new JTable(h.getTableModel());
//		else
//			this.table.setModel(h.getTableModel());
//	}
	public void atualizarDados(Colaborador[] lstColaborador) {
	    this.listColaborador = lstColaborador;
	    HelperTableModel h = new HelperTableModel(listColaborador);
	    TableModel model = h.getTableModel();

	    if (model == null) {
	        // Em caso de erro, configurar um modelo vazio
	        model = new DefaultTableModel(new Object[][]{}, new String[]{"Coluna1", "Coluna2", "Coluna3"});
	    }

	    if (this.table == null) {
	        this.table = new JTable(model);
	    } else {
	        this.table.setModel(model);
	    }
	}
	
	/**Pegando o objeto selecionado pelo usuário*/
	public Colaborador receberColaborador() {
		int numLinhaSelecionada = this.table.getSelectedRow();
		if(numLinhaSelecionada != -1)
			return this.listColaborador[numLinhaSelecionada];
		return null;
	}
}
