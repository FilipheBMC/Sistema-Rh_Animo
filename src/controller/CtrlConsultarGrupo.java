package controller;

import model.Grupo;
import model.ModelException;
import model.Setor;
import model.dao.DaoGrupo;
import viewer.JanelaConsultarGrupo;

public class CtrlConsultarGrupo extends CtrlAbstrato {

	public CtrlConsultarGrupo(CtrlAbstrato c) {
		super(c);
	}

	//
	// ATRIBUTOS
	//

	// CTRLS
	private CtrlAdicionarGrupo ctrlAdicionarGrupo;

	// JANELAS
	private JanelaConsultarGrupo janelaConsultarGrupo;

	//
	// MÉTODOS
	//

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
			Grupo[] listaGrupo = daoGrupo.obterListaObjetos();
			this.janelaConsultarGrupo.atualizarDados(listaGrupo);
		}
		catch(ModelException me) {
			this.janelaConsultarGrupo.notificar(me.getMessage());
		}

	}

	// Informando fim do adicionar grupor
	public void informandoFimDoAdicionarGrupo() {
		ctrlAdicionarGrupo = null;
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
