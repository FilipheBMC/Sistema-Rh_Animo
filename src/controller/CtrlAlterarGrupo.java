package controller;

import model.Colaborador;
import model.Grupo;
import model.ModelException;
import model.Setor;
import model.dao.DaoColaborador;
import model.dao.DaoGrupo;
import viewer.JanelaAlterarGrupo;

public class CtrlAlterarGrupo extends CtrlAbstrato{

	//
	//		ATRIBUTOS
	//
	private JanelaAlterarGrupo	janelaAlterarGrupo;
	private Grupo[]				listaGrupo;
	private DaoGrupo			daoGrupo;
	private Grupo				grupoAlterar;
	private Grupo				grupoAntigo;
	
	//
	//		MÉTODOS
	//
	
	public CtrlAlterarGrupo(CtrlAbstrato c, Grupo grupo, Setor[] listaSetor) {
		super(c);
		this.grupoAlterar = grupo;
		this.grupoAntigo = grupoAlterar;
		
		this.janelaAlterarGrupo = new JanelaAlterarGrupo(this, grupoAlterar, listaSetor);
	}
	
	public void AlterarGrupo(String nome, String codigo, Setor setor) {
		if(grupoAntigo.getCodigoSetor() != grupoAlterar.getCodigoSetor()) {
			try {
				this.grupoAlterar.setCodigoSetor(setor);

			}
			catch(ModelException e) {
				e.printStackTrace();
			}
		}
		
		if(!grupoAlterar.getNome().equals(grupoAntigo.getNome())) {
			try {
				this.grupoAlterar.setNome(nome);
			}
			catch(ModelException e) {
				e.printStackTrace();
			}
		}
		
		if(grupoAlterar.getCodigoGrupo().equals(grupoAntigo.getCodigoGrupo())) {
			try {
				this.grupoAlterar.setCodigoGrupo(codigo);
			}
			catch(ModelException e) {
				e.printStackTrace();
			}
		}
		
		DaoColaborador daoColaborador = new DaoColaborador();
		Colaborador[] listaColaborador = daoColaborador.obterListaObjetos();
		
		for(int i = 0, j = listaColaborador.length; i < j; i++) {
			if(listaColaborador[i].getCodigoGrupo() == grupoAntigo) {
				try {
					listaColaborador[i].setGrupo(grupoAlterar, null);
					daoColaborador.alterar(listaColaborador[i]);
				} catch (ModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		this.janelaAlterarGrupo.notificar("O grupo foi alterado com sucesso!");
		this.encerrar();
	}
	
	@Override
	public void iniciar() {
		// TODO Auto-generated method stub
		//É somente ir no construtor que lá estará inicializando a janela
	}
	
	@Override
	public void encerrar() {
		// TODO Auto-generated method stub
		this.janelaAlterarGrupo.fechar();
		CtrlConsultarGrupo ctrl = (CtrlConsultarGrupo)getCtrlPai();
		ctrl.informarFimAlterarGrupo();
		ctrl.atualziarJanela();
	}
	
	
}