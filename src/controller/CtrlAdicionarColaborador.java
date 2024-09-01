package controller;

import viewer.JanelaAdicionarColaborador;

public class CtrlAdicionarColaborador extends CtrlAbstrato{

	//
	//	ATRIBUTOS
	//
	
	// JANELAS
	
	private JanelaAdicionarColaborador janelaAdiconarPessoa;
	
	public CtrlAdicionarColaborador(CtrlAbstrato ctrl) {
		super(ctrl);
	}
	
	//Fechando a janela adicionar Colaborador
	public void fechar() {
		this.janelaAdiconarPessoa.fechar();
	}
	
	//Abrindo a janela adicionar Colaborador
	@Override
	public void iniciar() {
		this.janelaAdiconarPessoa = new JanelaAdicionarColaborador(this);
	}
	
	@Override
	public void encerrar() {
		this.janelaAdiconarPessoa.fechar();
		CtrlConsultarColaborador ctrl = (CtrlConsultarColaborador)getCtrlPai();
		ctrl.informarEncerramentoAdicionarColaborador();
	}
}
