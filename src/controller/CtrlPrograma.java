package controller;

import model.dao.Serializador;
import viewer.JanelaAdicionarColaborador;
import viewer.JanelaConsultarColaboradores;
import viewer.JanelaPrincipal;
import viewer.JanelaSetor;

public class CtrlPrograma extends CtrlAbstrato{

	//
	// ATRIBUTOS
	//
	
	// JANELAS
	private JanelaPrincipal        			janela;
	private JanelaConsultarColaboradores 	janelaColaboradores;
	private JanelaAdicionarColaborador		janelaAdicionarColaborador;
	
	// CTRLS
	public CtrlConsultarColaborador			CtrlConsultarColaborador;
	public CtrlAdicionarGrupo				ctrlAdicionarGrupo;
	public CtrlConsultarGrupo 				ctrlConsultarGrupo;
	public CtrlSetor						ctrlSetor;
	
	
	//
	// MÉTODOS
	//
	public CtrlPrograma() {
		super(null);
	}
	
	/**
	 * Método que indica o que o controlador irá executar no
	 * início de suas ações.
	 */
	public void iniciar() {
		// Recupera os objetos previamente salvos
		// (não esqueça de ver se este método está interagindo com 
		// todos os DAO's do sistema)
		Serializador.recuperarObjetos();		
		// Instanciando um objeto da classe JanelaPrincipal
		this.janela = new JanelaPrincipal(this);				
	}
	
	//----------- CTRL CONSULTAR COLABORADORES ------------------------
	
	/*
	 * Iniciando janela para consultar colaboradores
	 * 
	 * */
	
	public void iniciarConsultarColaboradores() {
		if(CtrlConsultarColaborador == null)
			this.CtrlConsultarColaborador = new CtrlConsultarColaborador(this);
	}
	
	//Finalizando a janela
	
	public void informarFimDeConsultarColaboradores() {
		this.CtrlConsultarColaborador = null;
	}
	
	// ---------------------------------------------------------------
	
	
	//----------- CTRL CONSULTAR GRUPOS ------------------------
	
	public void inicilizarConsultarGrupo() {
		if(this.ctrlConsultarGrupo == null)
			this.ctrlConsultarGrupo = new CtrlConsultarGrupo(this);
	}
	
	public void informarFimDeConsultarGrupo() {
		this.ctrlConsultarGrupo = null;
	}
	
	// ---------------------------------------------------------------
	
	//----------- CTRL SETOR ------------------------
	
	public void inicializarSetor() {
		if(this.ctrlSetor == null)
			this.ctrlSetor = new CtrlSetor(this);
	}
	
	public void informarFimSetor() {
		this.ctrlSetor = null;
	}
	
	// ---------------------------------------------------------------
	
	/**
	 * Método para encerrar programa
	 * **/
	@Override
	public void encerrar() {
		// TODO Auto-generated method stub
		System.exit(0);
	}
	
	/**
	 * Método main
	 */
	public static void main(String[] args) {		
		new CtrlPrograma();
	}


}
