package viewer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
import controller.CtrlAlterarGrupo;
import controller.CtrlConsultarGrupo;
import model.Colaborador;
import model.Grupo;
import model.Setor;
import model.dao.DaoSetor;

public class JanelaConsultarGrupo extends ViewerAbstrato {

	//
	// ATRIBUTOS
	//

	private JPanel contentPane;
	private JTable table;
	private JTextField TfNomeGrupo;
	private JTextField TfCodigoGrupo;
	JComboBox<Object> CbCodSetor;
	private CtrlConsultarGrupo ctrl = (CtrlConsultarGrupo) getCtrl();
	private Grupo[] listGrupo;

	//
	// MÉTODOS
	//

	/**
	 * Create the frame.
	 */
	public JanelaConsultarGrupo(CtrlConsultarGrupo c) {
		super(c);
		setTitle("Consulta de Grupos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\Brandão\\Videos\\Captures\\Ânimo Consultoria (@animoconsultoria) • Fotos e vídeos do Instagram - Google Chrome 16_03_2024 18_55_16.png"));
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
				CtrlConsultarGrupo ctrl = (CtrlConsultarGrupo) getCtrl();
				ctrl.encerrar();
			}
		});

		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setBackground(UIManager.getColor("Button.background"));
		table.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Lista de Grupos", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		table.setToolTipText("");
		table.setBounds(10, 143, 626, 232);
		contentPane.add(table);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 6, 50, 19);
		contentPane.add(lblNewLabel);

		TfNomeGrupo = new JTextField();
		TfNomeGrupo.setFont(new Font("Arial", Font.PLAIN, 12));
		TfNomeGrupo.setBounds(54, 6, 106, 21);
		contentPane.add(TfNomeGrupo);
		TfNomeGrupo.setColumns(10);

		DaoSetor dao = new DaoSetor();
		Setor[] set = dao.obterListaObjetos();

//		JComboBox CbCodSetor = new JComboBox();
//		CbCodSetor.setModel(new DefaultComboBoxModel("Selecione...", set));
//		CbCodSetor.setFont(new Font("Arial", Font.PLAIN, 12));
//		CbCodSetor.setBounds(10, 65, 150, 24);
//		contentPane.add(CbCodSetor);

//		JComboBox<Setor> CbCodSetor = new JComboBox<>();
//		CbCodSetor.setFont(new Font("Arial", Font.PLAIN, 12));
//		CbCodSetor.setBounds(10, 65, 150, 24);
//		contentPane.add(CbCodSetor);
//
//		// Adiciona o item "Selecione..." como primeiro item
//		CbCodSetor.addItem(null); // Item nulo representa a opção "Selecione..."
//
//		// Adiciona os setores do array 'set'
//		for (Setor setor : set) {
//		    CbCodSetor.addItem(setor);
//		}

		CbCodSetor = new JComboBox<>();
		CbCodSetor.setFont(new Font("Arial", Font.PLAIN, 12));
		CbCodSetor.setBounds(10, 65, 150, 24);
		contentPane.add(CbCodSetor);

		// Adiciona o item "Selecione..." como primeiro item
		CbCodSetor.addItem("Selecione...");

		// Adiciona os setores do array 'set'
		for (Setor setor : set) {
			CbCodSetor.addItem(setor);
		}

		JLabel lblCdigoDoSetor = new JLabel("Código do Setor:");
		lblCdigoDoSetor.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCdigoDoSetor.setBounds(10, 36, 113, 19);
		contentPane.add(lblCdigoDoSetor);

		JLabel lblCdigoDoGrupo = new JLabel("Código do Grupo:");
		lblCdigoDoGrupo.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCdigoDoGrupo.setBounds(180, 6, 127, 19);
		contentPane.add(lblCdigoDoGrupo);

		JButton BtAdicionarGrupo = new JButton("Adicionar");
		BtAdicionarGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = TfNomeGrupo.getText();
				String auxCodGrupo = TfCodigoGrupo.getText();
//				Setor codigoSetor = (Setor) CbCodSetor.getSelectedItem();
				int codigoGrupo = 0;
				
				Object selectedItem = CbCodSetor.getSelectedItem();
				Setor codigoSetor = null;

				if (selectedItem instanceof Setor) {
				    codigoSetor = (Setor) selectedItem;
				} else {
				    notificar("Por favor, selecione um setor válido.");
				    return; // Saia do método caso um setor não tenha sido selecionado
				}
				
				if (nome.isEmpty() && auxCodGrupo.isEmpty()) {
					CtrlConsultarGrupo ctrl = (CtrlConsultarGrupo) getCtrl();
					ctrl.iniciarInicializarAdicionarGrupo();
				} else {
					try {
						codigoGrupo = Integer.parseInt(auxCodGrupo);
					} catch (Exception me) {
						notificar("O código do grupo deve ser um Digito inteiro.");
						return;
					}
				}

				CtrlConsultarGrupo ctrl = (CtrlConsultarGrupo) getCtrl();
				ctrl.iniciarAdicionarGrupo(nome, auxCodGrupo, codigoSetor);
				limparTf();
			}
		});
		BtAdicionarGrupo.setFont(new Font("Arial", Font.BOLD, 12));
		BtAdicionarGrupo.setBounds(180, 65, 90, 24);
		contentPane.add(BtAdicionarGrupo);

		TfCodigoGrupo = new JTextField();
		TfCodigoGrupo.setFont(new Font("Arial", Font.PLAIN, 12));
		TfCodigoGrupo.setColumns(10);
		TfCodigoGrupo.setBounds(297, 6, 70, 21);
		contentPane.add(TfCodigoGrupo);

		JButton BtAlterarGrupo = new JButton("Alterar");
		BtAlterarGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Grupo grupo = receberGrupo();
				
				if(grupo == null) {
					notificar("O escolha um grupo para poder altera-lo.");
					return;
				}
				
				CtrlConsultarGrupo ctrl = (CtrlConsultarGrupo)getCtrl();
				ctrl.iniciarAlterarGrupo(grupo);
			}
		});
		BtAlterarGrupo.setFont(new Font("Arial", Font.BOLD, 12));
		BtAlterarGrupo.setBounds(280, 65, 90, 24);
		contentPane.add(BtAlterarGrupo);

		JButton BtConsultarGrupo = new JButton("Consultar");
		BtConsultarGrupo.setFont(new Font("Arial", Font.BOLD, 12));
		BtConsultarGrupo.setBounds(380, 65, 90, 24);
		contentPane.add(BtConsultarGrupo);

		JButton BtRemoverGrupo = new JButton("Remover");
		BtRemoverGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Grupo grupo = receberGrupo();
				
				if(grupo == null) {
		            notificar("Nenhum grupo foi selecionado para exclusão.");
		            return;  // Sai da função se nenhum setor for selecionado
				}
				
				//Verificando e pegando a instancia da classe CtrlConsultarGrupo
				CtrlAbstrato ctrl = (CtrlAbstrato)getCtrl();
				if(ctrl instanceof CtrlConsultarGrupo) {
					CtrlConsultarGrupo ct = (CtrlConsultarGrupo)getCtrl();
					ct.iniciarExcluirGrupo(grupo);
				}
				else {
					notificar("Tem de se escolher um grupo para poder ser excluido.");
				}
			}
		});
		BtRemoverGrupo.setFont(new Font("Arial", Font.BOLD, 12));
		BtRemoverGrupo.setBounds(480, 65, 90, 24);
		contentPane.add(BtRemoverGrupo);

		JButton BtSair = new JButton("Sair");
		BtSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.encerrar();
			}
		});
		BtSair.setFont(new Font("Arial", Font.BOLD, 12));
		BtSair.setBounds(10, 99, 90, 24);
		contentPane.add(BtSair);

		this.setVisible(true);

	}
	
	
	/**
	 * Atualiza os dados apresentados no JTable da janela
	 */
//	public void atualizarDados(Grupo[] lstGrupo) {
//		this.listGrupo = lstGrupo;
//		HelperTableModel h = new HelperTableModel(listGrupo);
//		if(this.table == null)
//			this.table = new JTable(h.getTableModel());
//		else 
//			this.table.setModel(h.getTableModel());
//	}
	public void atualizarDados(Grupo[] lstGrupo) {
	    this.listGrupo = lstGrupo;
	    HelperTableModel h = new HelperTableModel(listGrupo);
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
	public Grupo receberGrupo() {
		int numLinhaSelecionada = this.table.getSelectedRow();
		if(numLinhaSelecionada != -1)
			return this.listGrupo[numLinhaSelecionada];
		return null;
	}
	
	private void limparTf() {
		this.TfCodigoGrupo.setText("");
		this.TfNomeGrupo.setText("");
		this.CbCodSetor.setSelectedIndex(0);
	}
}
