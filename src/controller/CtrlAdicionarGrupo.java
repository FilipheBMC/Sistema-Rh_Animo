package controller;

import viewer.JanelaAdicionarGrupo;

public class CtrlAdicionarGrupo extends CtrlAbstrato{

	//
	//	ATRIBUTOS
	//
	
	//
	//	JANELAS
	//
	private JanelaAdicionarGrupo janelaAdicionarGrupo;
	
	public CtrlAdicionarGrupo(CtrlAbstrato ctrl) {
		super(ctrl);
	}
	
	//
	//	MÉTODOS
	//
	
	
	
	
	//	MÉTODOS DE INCIAR E ENCERRAR

	@Override
	public void iniciar() {
			this.janelaAdicionarGrupo = new JanelaAdicionarGrupo(this);
	}

	@Override
	public void encerrar() {
		// TODO Auto-generated method stub
		this.janelaAdicionarGrupo.fechar();
		CtrlConsultarGrupo ctrl = (CtrlConsultarGrupo)getCtrlPai();
		ctrl.informandoFimDoAdicionarGrupo();;
	}
	
}
