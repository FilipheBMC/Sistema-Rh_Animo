package controller;

import viewer.JanelaConsultarGrupo;

public class CtrlConsultarGrupo extends CtrlAbstrato{

	public CtrlConsultarGrupo(CtrlAbstrato c) {
		super(c);
	}
	
	//
	//	ATRIBUTOS
	//
	
	//	CTRLS
	private CtrlAdicionarGrupo ctrlAdicionarGrupo;
	
	//	JANELAS
	private JanelaConsultarGrupo janelaConsultarGrupo;
	
	//
	//	MÉTODOS
	//
	
	//----------- CTRL ADICIONAR GRUPOS ------------------------
	
	//Incializando o ctrl
	public void iniciarInicializarAdicionarGrupo() {
		this.ctrlAdicionarGrupo = new CtrlAdicionarGrupo(this);
	}
	
	//Informando fim do adicionar grupor
	public void informandoFimDoAdicionarGrupo() {
		ctrlAdicionarGrupo = null;
	}
	
	
	// ---------------------------------------------------------------

	
	//MÉTODOS DA CLASSE ABSTRATA
	
	@Override
	public void iniciar() {
		// TODO Auto-generated method stub
		this.janelaConsultarGrupo = new JanelaConsultarGrupo(this);
	}

	@Override
	public void encerrar() {
		// TODO Auto-generated method stub
		this.janelaConsultarGrupo.fechar();
		CtrlPrograma ctrl = (CtrlPrograma)getCtrlPai();
		ctrl.informarFimDeConsultarGrupo();
	}
	
}
