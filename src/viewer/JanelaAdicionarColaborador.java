package viewer;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.CtrlAbstrato;
import controller.CtrlAdicionarColaborador;
import model.Grupo;
import model.ModelException;
import model.Setor;
import model.dao.DaoGrupo;
import model.dao.DaoSetor;

public class JanelaAdicionarColaborador extends ViewerAbstrato {

	//
	// ATRIBUTOS
	//
	
	private JPanel contentPane;
	private JTextField TfNome;
	private JTextField TfCpf;
	private JTextField TfDtNasc;
	private JTextField TfDtEntrada;
	private JTextField TfDtSaida;


	/**
	 * Create the frame.
	 */
	public JanelaAdicionarColaborador(CtrlAbstrato c) {
		super(c);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Brandão\\Videos\\Captures\\Ânimo Consultoria (@animoconsultoria) • Fotos e vídeos do Instagram - Google Chrome 16_03_2024 18_55_16.png"));
		setTitle("Janela Adicionar Colaborador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 28, 66, 25);
		contentPane.add(lblNewLabel);
		
		TfNome = new JTextField();
		TfNome.setFont(new Font("Arial", Font.BOLD, 12));
		TfNome.setBounds(10, 51, 155, 25);
		contentPane.add(TfNome);
		TfNome.setColumns(10);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDataDeNascimento.setBounds(195, 28, 125, 25);
		contentPane.add(lblDataDeNascimento);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCpf.setBounds(10, 85, 66, 25);
		contentPane.add(lblCpf);
		
		TfCpf = new JTextField();
		TfCpf.setFont(new Font("Arial", Font.BOLD, 12));
		TfCpf.setColumns(10);
		TfCpf.setBounds(10, 106, 155, 25);
		contentPane.add(TfCpf);
		
//		TfDtNasc = new JTextField();
//		TfDtNasc.setFont(new Font("Arial", Font.BOLD, 12));
//		TfDtNasc.setColumns(10);
//		TfDtNasc.setBounds(195, 51, 155, 25);
//		contentPane.add(TfDtNasc);
		
		// Campo de Data de Nascimento com máscara
		MaskFormatter dateMask = null;
		try {
		    dateMask = new MaskFormatter("##/##/####");
		    dateMask.setPlaceholderCharacter('_'); // Define o caractere de preenchimento
		} catch (ParseException e) {
		    e.printStackTrace();
		}

		TfDtNasc = new JFormattedTextField(dateMask);
		TfDtNasc.setFont(new Font("Arial", Font.BOLD, 12));
		TfDtNasc.setBounds(195, 51, 155, 25);
		contentPane.add(TfDtNasc);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSexo.setBounds(195, 85, 125, 25);
		contentPane.add(lblSexo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial", Font.BOLD, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Selecione...", "Homem", "Mulher"}));
		comboBox.setBounds(195, 106, 155, 25);
		contentPane.add(comboBox);
		
		JLabel lblDataEntrada = new JLabel("Data de entrada:");
		lblDataEntrada.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDataEntrada.setBounds(372, 28, 125, 25);
		contentPane.add(lblDataEntrada);
		
		TfDtEntrada = new JFormattedTextField(dateMask);
		TfDtEntrada.setFont(new Font("Arial", Font.BOLD, 12));
		TfDtEntrada.setColumns(10);
		TfDtEntrada.setBounds(372, 51, 155, 25);
		contentPane.add(TfDtEntrada);
		
		JLabel lblDataDeEntrada = new JLabel("Data de saida:");
		lblDataDeEntrada.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDataDeEntrada.setBounds(372, 85, 125, 25);
		contentPane.add(lblDataDeEntrada);
		
		TfDtSaida = new JFormattedTextField(dateMask);
		TfDtSaida.setFont(new Font("Arial", Font.BOLD, 12));
		TfDtSaida.setColumns(10);
		TfDtSaida.setBounds(372, 106, 155, 25);
		contentPane.add(TfDtSaida);
		
		JLabel lblCargo = new JLabel("Setor:");
		lblCargo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCargo.setBounds(10, 141, 66, 25);
		contentPane.add(lblCargo);
		
		JComboBox CbSetor = new JComboBox();
		DaoSetor daoSetor = new DaoSetor();
		Setor[] listaSetores = daoSetor.obterListaObjetos();
		String[] listaNomeSetores = pegarStringSetor(listaSetores);
		CbSetor.setModel(new DefaultComboBoxModel(listaNomeSetores));
		CbSetor.setFont(new Font("Arial", Font.BOLD, 12));
		CbSetor.setBounds(10, 168, 155, 25);
		contentPane.add(CbSetor);
		
		JLabel lblGrupo = new JLabel("Grupo:");
		lblGrupo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblGrupo.setBounds(195, 141, 66, 25);
		contentPane.add(lblGrupo);
		
		JComboBox CbGrupo = new JComboBox();
		DaoGrupo daoGrupo = new DaoGrupo();
		Grupo[] listaGrupos = daoGrupo.obterListaObjetos();
		String[] listaNomeGrupos = pegarStringGrupo(listaGrupos);
		CbGrupo.setModel(new DefaultComboBoxModel(listaNomeGrupos));
		CbGrupo.setFont(new Font("Arial", Font.BOLD, 12));
		CbGrupo.setBounds(195, 168, 155, 25);
		contentPane.add(CbGrupo);
		
		JLabel lblCargo_1 = new JLabel("Cargo:");
		lblCargo_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCargo_1.setBounds(372, 141, 66, 25);
		contentPane.add(lblCargo_1);
		
		JComboBox CbGrupo_1 = new JComboBox();
		CbGrupo_1.setFont(new Font("Arial", Font.BOLD, 12));
		CbGrupo_1.setBounds(372, 168, 155, 25);
		contentPane.add(CbGrupo_1);
		
		JButton BtEnviar = new JButton("Enviar");
		BtEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Pegando as Text Fields
				String nome = TfNome.getText();
				String Nascimento = TfDtNasc.getText();
				String DtEntrada = TfDtEntrada.getText();
				String DtSaida = TfDtSaida.getText();
				String cpf = TfCpf.getText();
				
				String auxgrupo = (String) CbGrupo.getSelectedItem().toString();
				String auxsetor = (String) CbSetor.getSelectedItem().toString();
				
				int grupo = 0;
				int setor = 0;
				
				//Validação código grupo
				try {
					grupo = Integer.parseInt(auxgrupo);
				}
				catch(Exception ex) {
					notificar("O código do grupo deve ser um numeral.");
				}
				
				try {
					setor = Integer.parseInt(auxsetor);
				}
				catch(Exception ex) {
					notificar("O código do setor deve ser um numeral.");
				}
				
				//Ctrl para adicionar colaborador
				try {
					CtrlAdicionarColaborador ctrl = (CtrlAdicionarColaborador)getCtrl();
					ctrl.adicionarColaborador(nome, cpf, Nascimento, DtEntrada, DtSaida, grupo, setor, DtSaida, cpf);	
				}
				catch(ModelException me) {
					notificar(me.toString());
				}

				
			}
		});
		BtEnviar.setFont(new Font("Arial", Font.BOLD, 12));
		BtEnviar.setBounds(10, 310, 95, 33);
		contentPane.add(BtEnviar);
		
		JButton BtCancelar = new JButton("Cancelar");
		BtCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CtrlAdicionarColaborador ctrl = (CtrlAdicionarColaborador)getCtrl();
				ctrl.fechar();
			}
		});
		BtCancelar.setFont(new Font("Arial", Font.BOLD, 12));
		BtCancelar.setBounds(119, 310, 95, 33);
		contentPane.add(BtCancelar);
		
		this.setVisible(true);
	}
	
	/**Formar array de strings de setor*/
	private String[] pegarStringSetor(Setor[] pTransformar) {
		int tamanho = pTransformar.length;
		String[] listaStrings = new String[tamanho];
		for(int i = 0; i < tamanho; i++) {
			listaStrings[i] = pTransformar[i].getNomeSetor();
		}
		return listaStrings;
	}
	
	/**Formar array de strings de grupo*/
	private String[] pegarStringGrupo(Grupo[] pTransformar) {
		int tamanho = pTransformar.length;
		String[] listaStrings = new String[tamanho];
		for(int i = 0; i < tamanho; i++) {
			listaStrings[i] = pTransformar[i].getNome();
		}
		return listaStrings;
	}

}
