package viewer;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CtrlPrograma;

public class JanelaPrincipal extends ViewerAbstrato {

	//
	// ATRIBUTOS
	//
	private JPanel contentPane;
	private CtrlPrograma ctrl = (CtrlPrograma)getCtrl();

	/**
	 * Create the frame.
	 */
	public JanelaPrincipal(CtrlPrograma ctrl) {
		// Guardo a referência para o controlador do programa
		super(ctrl);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Brandão\\Videos\\Captures\\Ânimo Consultoria (@animoconsultoria) • Fotos e vídeos do Instagram - Google Chrome 16_03_2024 18_55_16.png"));
		setTitle("Sistema de Rh Animo Consultoria");


	/**
	 * Create the frame.
	 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton BtColaboradores = new JButton("Colaboradores");
		BtColaboradores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CtrlPrograma ctrl = (CtrlPrograma)getCtrl();
				ctrl.iniciarConsultarColaboradores();
			}
		});
		BtColaboradores.setBounds(10, 10, 125, 47);
		contentPane.add(BtColaboradores);
		
		JButton BtGrupo = new JButton("Grupos");
		BtGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CtrlPrograma ctrl = (CtrlPrograma)getCtrl();
				ctrl.inicilizarConsultarGrupo();
			}
		});
		BtGrupo.setBounds(169, 10, 111, 47);
		contentPane.add(BtGrupo);
		
		JButton BtSetor = new JButton("Setor");
		BtSetor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.inicializarSetor();
			}
		});
		BtSetor.setBounds(310, 10, 111, 47);
		contentPane.add(BtSetor);
		
		JButton btnImportarDados = new JButton("Importar Dados");
		btnImportarDados.setBounds(448, 10, 141, 47);
		contentPane.add(btnImportarDados);
		
		JButton btnExportarDados = new JButton("Exportar Dados");
		btnExportarDados.setBounds(10, 77, 141, 47);
		contentPane.add(btnExportarDados);
		
		this.setVisible(true);
	}
}
