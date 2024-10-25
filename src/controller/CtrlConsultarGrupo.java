package controller;

import java.util.ArrayList;
import java.util.List;

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

	//		MÉTODO DE CONSULTA
	
	public void consulta(String nome, String codGrupo, Setor setor) {
	    DaoGrupo daoGrupo = new DaoGrupo();
	    Grupo[] listaGrupos = daoGrupo.obterListaObjetos();
	    List<Grupo> gruposEncontrados = new ArrayList<>();
	    int tamGrupo = listaGrupos.length;

	    if (nome.isEmpty() && codGrupo.isEmpty() && setor == null) {
	    	atualziarJanela();
	        janelaConsultarGrupo.notificar("Para consultar os grupos, insira ao menos um critério.");
	        return;
	    }
	    
	    for (int i = 0; i < tamGrupo; i++) {
	    	System.out.println("Teste");
	        Grupo grupo = listaGrupos[i];
	        
	        // Comparar por nome se o nome não for vazio
	        if (!nome.isEmpty() && grupo.getNome().equals(nome)) {
	            gruposEncontrados.add(grupo);
	        }
	        
	        // Comparar por código do grupo se fornecido
	        if (!codGrupo.isEmpty() && grupo.getCodigoGrupo().equals(codGrupo)) {
	            gruposEncontrados.add(grupo);
	        }
	        
	        // Comparar por setor se fornecido
	        if (setor != null && grupo.getCodigoSetor().getCodSetor() == setor.getCodSetor()) {
	            gruposEncontrados.add(grupo);
	        }
	    }

	    if (!gruposEncontrados.isEmpty()) {
	        Grupo[] arrayGruposEncontrados = gruposEncontrados.toArray(new Grupo[0]);
	        this.janelaConsultarGrupo.atualizarDados(arrayGruposEncontrados);  // Atualiza JTable com os grupos encontrados
	    } else {
	        this.atualziarJanela();  // Atualizar a janela com todos os grupos
	        this.janelaConsultarGrupo.notificar("Nenhum grupo foi encontrado.");
	    }
	}

	
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
