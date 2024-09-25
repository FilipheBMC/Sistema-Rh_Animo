package controller;

import model.Grupo;
import model.ModelException;
import model.Setor;
import model.dao.DaoGrupo;
import model.dao.DaoSetor;
import viewer.JanelaConsultarGrupo;

public class CtrlConsultarGrupo extends CtrlAbstrato {

	public CtrlConsultarGrupo(CtrlAbstrato c) {
		super(c);
	}

	//
	// ATRIBUTOS
	//

	// CTRLS
	private CtrlAdicionarGrupo 		ctrlAdicionarGrupo;
	private CtrlExcluirGrupo		ctrlExcluirGrupo;
	private CtrlAlterarGrupo		ctrlAlterarGrupo;

	// JANELAS
	private JanelaConsultarGrupo 	janelaConsultarGrupo;

	//
	// MÉTODOS
	//
	
	// ----------- CTRL EXCLUIR GRUPOS ------------------------

	public void iniciarExcluirGrupo(Grupo grupo) {
		if(ctrlExcluirGrupo == null)
			this.ctrlExcluirGrupo = new CtrlExcluirGrupo(this, grupo);
	}
	
	public void informarFimDeExcluirGrupo() {
		this.ctrlExcluirGrupo = null;
	}
	
	// ----------- CTRL ADICIONAR GRUPOS ------------------------

	// Incializando o ctrl
	public void iniciarInicializarAdicionarGrupo() {
	        // Inicializa o controlador para adicionar um grupo
	        this.ctrlAdicionarGrupo = new CtrlAdicionarGrupo(this);
	}
	
	public void iniciarAdicionarGrupo(String nome, String codgrupo, Setor setor) {
		try {
			Grupo grupo = new Grupo(nome, setor, codgrupo);
			DaoGrupo daoGrupo = new DaoGrupo();
			daoGrupo.adicionar(grupo);
			this.janelaConsultarGrupo.notificar(grupo.toString());
			this.atualziarJanela();
		}
		catch(ModelException me) {
			this.janelaConsultarGrupo.notificar(me.getMessage());
		}

	}
	
	//-----------------		CTRL ALTERAR GRUPO		-----------------------
	
	public void iniciarAlterarGrupo(Grupo grupo) {
		
		DaoSetor daoSetor = new DaoSetor();
		Setor[] listaSetor = daoSetor.obterListaObjetos();
		
		
		
		if(ctrlAlterarGrupo == null)
			ctrlAlterarGrupo = new CtrlAlterarGrupo(this, grupo, listaSetor);
	}
	
	public void informarFimAlterarGrupo() {
		ctrlAlterarGrupo = null;
		this.atualziarJanela();
	}
	
	//---------------------------------------------------------------------

	// Informando fim do adicionar grupor
	public void informandoFimDoAdicionarGrupo() {
		ctrlAdicionarGrupo = null;
	}
	
	//	MÉTODO DE ATUAZLIAÇÃO DA JANELA
	
	public void atualziarJanela() {
		DaoGrupo daoGrupo = new DaoGrupo();
		Grupo[] listaGrupo = daoGrupo.obterListaObjetos();
		this.janelaConsultarGrupo.atualizarDados(listaGrupo);
	}

	// ---------------------------------------------------------------

	// MÉTODOS DA CLASSE ABSTRATA

	@Override
	public void iniciar() {

		// TODO Auto-generated method stub
		this.janelaConsultarGrupo = new JanelaConsultarGrupo(this);
		DaoGrupo daoGrupo = new DaoGrupo();
		Grupo[] listaGrupo = daoGrupo.obterListaObjetos();
		this.janelaConsultarGrupo.atualizarDados(listaGrupo);
	}

	@Override
	public void encerrar() {
		// TODO Auto-generated method stub
		this.janelaConsultarGrupo.fechar();
		CtrlPrograma ctrl = (CtrlPrograma) getCtrlPai();
		ctrl.informarFimDeConsultarGrupo();
	}

}
