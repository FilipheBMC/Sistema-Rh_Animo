package controller;

import model.Setor;
import model.dao.DaoSetor;
import viewer.JanelaSetor;

public class CtrlSetor extends CtrlAbstrato{

	
	//
	//	ATRIBUTOS	
	//
	
	private JanelaSetor janelaSetor;
	private Setor setor;
	private DaoSetor daoSetor;
	private CtrlExcluirSetor ctrlExcluirSetor;
	
	//
	//	METODOS
	//
	
	public CtrlSetor(CtrlAbstrato c) {
		super(c);
	}
	
	//MÉTODO DE VALIDAÇÃO DO SETOR E ARMAZENAMENTO DE DADOS
	
	public void adicionarSetor(String nome, int cod){
		try {
		this.setor = new Setor(nome, cod);
		daoSetor = new DaoSetor();
		this.daoSetor.adicionar(setor);
		this.atualizar();
		this.janelaSetor.notificar(setor.toString());

		}
		catch(Exception me) {
			this.janelaSetor.notificar(me.getMessage());
		}
	}
	
	//MÉTODO EXCLUIR SETOR
	public void excluirSetor(Setor setor) {
		if(ctrlExcluirSetor == null)
			ctrlExcluirSetor = new CtrlExcluirSetor(this, setor);
	}
	
	public void informarFinalizarExcluirSetor() {
		ctrlExcluirSetor = null;
		this.atualizar();
	}
	
	private void atualizar() {
		daoSetor = new DaoSetor();
		Setor[] listaSetor = daoSetor.obterListaObjetos();
		this.janelaSetor.atualizarDados(listaSetor);
	}
	

	@Override
	public void iniciar() {
//		// TODO Auto-generated method stub
		DaoSetor daoSet = new DaoSetor();
		Setor[] listaSetor = daoSet.obterListaObjetos();		
		this.janelaSetor = new JanelaSetor(this, listaSetor);
		this.janelaSetor.atualizarDados(listaSetor);
		
	}

	@Override
	public void encerrar() {
		// TODO Auto-generated method stub
		this.janelaSetor.fechar();
		CtrlPrograma ctrl = (CtrlPrograma)getCtrlPai();
		ctrl.informarFimSetor();
	}
	
	
}
