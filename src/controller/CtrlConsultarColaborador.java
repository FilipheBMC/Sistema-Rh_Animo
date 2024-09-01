package controller;

import viewer.JanelaConsultarColaboradores;

public class CtrlConsultarColaborador extends CtrlAbstrato{

	//
	//	ATRIBUTOS
	//
	
	//	JANELAS
	private JanelaConsultarColaboradores janelaConsultarColaboradores;
	
	//
	//	CTRLS
	//
	
	private CtrlAdicionarColaborador 		ctrlAdicionarColaborador;
	private CtrlAlterarColaborador 			ctrlAlterarColaborador;
	
	public CtrlConsultarColaborador(CtrlAbstrato ctrl) {
		super(ctrl);
	}
	
	/*
	 * Chamando os Ctrls para serem incializados e encerrados
	 * */
	
	//Chamando o Ctrl de adcionar pessoa
	
	public void iniciarAdicionarPessoa() {
		if(ctrlAdicionarColaborador == null)
			this.ctrlAdicionarColaborador = new CtrlAdicionarColaborador(this);
	}
	
	public void informarEncerramentoAdicionarColaborador() {
		this.ctrlAdicionarColaborador = null;
	}
	
	//Chamando o Ctrl de alterarcolaborador
	
	public void iniciarAlterarColaborador() {
		if(ctrlAlterarColaborador == null)
			this.ctrlAlterarColaborador = new CtrlAlterarColaborador(this);
	}
	
	public void informarEncerramentoAlterarColaborador() {
		this.ctrlAlterarColaborador = null;
	}
	
	// -----------    #-#    --------------
	
	//Chamando o Adicionar Colaborador

	@Override
	public void iniciar() {
		this.janelaConsultarColaboradores = new JanelaConsultarColaboradores(this);
	}

	@Override
	public void encerrar() {
		// TODO Auto-generated method stub
		this.janelaConsultarColaboradores.fechar();
		//Notificando ao controlador que o caso de uso se encerrou
		CtrlPrograma ctrl = (CtrlPrograma)this.getCtrlPai();
		ctrl.informarFimDeConsultarColaboradores();
		
	}
}
