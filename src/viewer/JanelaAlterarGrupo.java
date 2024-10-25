package viewer;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.CtrlAbstrato;
import controller.CtrlAlterarGrupo;
import controller.CtrlConsultarGrupo;
import model.Grupo;
import model.Setor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaAlterarGrupo extends ViewerAbstrato {

	//
	//		ATRIBUTOS
	//
	private JPanel 		contentPane;
	private JTextField 	TfNome;
	private JTextField 	TfCodGrupo;
	private JLabel 		LblGrupo;
	
	//
	//		MÉTODOS
	//
	public JanelaAlterarGrupo(CtrlAbstrato c, Grupo grupo, Setor[] listaGrupo) {
		super(c);
		setTitle("Alterar Grupo");
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
				CtrlAlterarGrupo ctrl = (CtrlAlterarGrupo) getCtrl();
				ctrl.encerrar();
			}
		});
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome Grupo:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 10, 89, 19);
		contentPane.add(lblNewLabel);
		
		TfNome = new JTextField(grupo.getNome());
		TfNome.setBounds(10, 39, 118, 26);
		contentPane.add(TfNome);
		TfNome.setColumns(10);
		
		JLabel lblCdigoGrupo = new JLabel("Código grupo:");
		lblCdigoGrupo.setFont(new Font("Arial", Font.BOLD, 12));
		lblCdigoGrupo.setBounds(172, 10, 89, 19);
		contentPane.add(lblCdigoGrupo);

		TfCodGrupo = new JTextField(grupo.getCodigoGrupo());
		TfCodGrupo.setColumns(10);
		TfCodGrupo.setBounds(172, 39, 118, 26);
		contentPane.add(TfCodGrupo);
		
		LblGrupo = new JLabel("Setor:");
		LblGrupo.setFont(new Font("Arial", Font.BOLD, 12));
		LblGrupo.setBounds(328, 10, 89, 19);
		contentPane.add(LblGrupo);
		
		JComboBox CbGrupo = new JComboBox<>();
		CbGrupo.setFont(new Font("Arial", Font.PLAIN, 12));
		CbGrupo.setBounds(328, 39, 152, 26);
		contentPane.add(CbGrupo);
		
		//Adicionando o item "selecione..."
		//CbGrupo.addItem("Selecione...");
		
		//Adicionando os itens da lista
		// Adicionando os itens da lista
		for (Setor grupoo : listaGrupo) {
		    CbGrupo.addItem(grupoo); // Adiciona o setor ao JComboBox
		    
		    // Se o código do setor atual for igual ao código do setor do grupo, seleciona-o
		    if (grupo.getCodigoSetor().getNomeSetor().equals(grupoo.getNomeSetor())) {
		        CbGrupo.setSelectedItem(grupoo); // Define o item selecionado no ComboBox
		    }
		}

		
		JButton BtAlterar = new JButton("Alterar");
		BtAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String nome = TfNome.getText();
			    String codigo = TfCodGrupo.getText();

			    Object selectedItem = CbGrupo.getSelectedItem(); // Obtenha o item selecionado

			    // Verifique se o item selecionado é um Setor
			    if (selectedItem instanceof Setor) {
			        Setor setor = (Setor) selectedItem; // Agora é seguro fazer o cast

			        CtrlAlterarGrupo ctrl = (CtrlAlterarGrupo)getCtrl();
			        ctrl.AlterarGrupo(nome, codigo, setor);
			    } else {
			        // Exibe uma mensagem de erro se o setor não for válido
			        notificar("Deve ser selecionada uma opção válida.");
			    }
			}

		});
		BtAlterar.setFont(new Font("Arial", Font.BOLD, 12));
		BtAlterar.setBounds(10, 298, 195, 52);
		contentPane.add(BtAlterar);
		
		JButton BtConcelar = new JButton("Cancelar");
		BtConcelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CtrlAlterarGrupo ctrl = (CtrlAlterarGrupo)getCtrl();
				ctrl.encerrar();
			}
		});
		BtConcelar.setFont(new Font("Arial", Font.BOLD, 12));
		BtConcelar.setBounds(441, 298, 195, 52);
		contentPane.add(BtConcelar);
		

		
		
		
		
		
		this.setVisible(true);
	}
}
