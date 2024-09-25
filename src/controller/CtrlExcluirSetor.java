package controller;

import model.Setor;
import model.dao.DaoSetor;
import viewer.JanelaExcluirSetorConfirmacao;

public class CtrlExcluirSetor extends CtrlAbstrato{
	
	//
	//	ATRIBUTOS
	//
	private Setor 							setor;
	private JanelaExcluirSetorConfirmacao 	janelaExcluirSetor;
	
	//
	//	MÉTODOS
	//
	
	public CtrlExcluirSetor(CtrlAbstrato c, Setor setor) {
		super (c);
		String setNome = setor.getNomeSetor();
		this.setor = setor;
		this.janelaExcluirSetor = new JanelaExcluirSetorConfirmacao(this, setNome);
	}
	
	public void excluirSetor() {
		DaoSetor daoSetor = new DaoSetor();
		daoSetor.remover(setor);
		this.janelaExcluirSetor.notificar("Setor exlcuido com Sucesso!");
		encerrar();
	}
	

	
	public void iniciar() {
		//Ver no contrutor que vai estar lá a inicialização
	}
	
	public void encerrar() {
		this.janelaExcluirSetor.fechar();
		CtrlSetor ctrlSetor = (CtrlSetor)getCtrlPai();
		ctrlSetor.informarFinalizarExcluirSetor();
	}

}
