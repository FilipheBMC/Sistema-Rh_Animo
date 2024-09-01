package controller;

import viewer.JanelaAlterarColaborador;

public class CtrlAlterarColaborador extends CtrlAbstrato{

	//
	// ATRIBUTOS
	//
	
	JanelaAlterarColaborador janelaAlterarColaboradores;
	
	
	public CtrlAlterarColaborador(CtrlAbstrato ctrl) {
		super(ctrl);
	}
	
	
	
	/*
	 * Métodos de incializar e encerrar
	 * */
	
	//Informando a incialização
	public void iniciar() {
		this.janelaAlterarColaboradores = new JanelaAlterarColaborador(this);
	}
	
	
	//Informando o encerramento do ctrl
	public void encerrar() {
		this.janelaAlterarColaboradores.fechar();
		CtrlConsultarColaborador ctrl = (CtrlConsultarColaborador)getCtrlPai();
		ctrl.informarEncerramentoAlterarColaborador();
	}
}
