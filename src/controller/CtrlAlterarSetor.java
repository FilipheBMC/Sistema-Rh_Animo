package controller;

import model.Grupo;
import model.ModelException;
import model.Setor;
import model.dao.DaoGrupo;
import model.dao.DaoSetor;
import viewer.JanelaAlterarSetor;

public class CtrlAlterarSetor extends CtrlAbstrato{

	//
	//		ATRIBUTOS	
	//
	JanelaAlterarSetor 		 janelaAlterar;
	Setor					 setorAlterar;
	Setor					 setorAntigo;
	DaoSetor				 daoSetor;
	Setor[]					 listaSetor;
	
	//
	//		MÉTODOS
	//
	public CtrlAlterarSetor(CtrlAbstrato c, Setor setorAlterar) {
		super(c);
		this.setorAlterar = setorAlterar;
		janelaAlterar = new JanelaAlterarSetor(this, setorAlterar);
		
		setorAntigo = setorAlterar;
		
		
	}
	
	//	RECEBENDO AS ALTERARAÇÕES E FAZENDO VALIDAÇÃO PARA CASO SEJA IGUAL.
	public void alteracaoSetor(String nome, int codigo) {
		String nom = setorAlterar.getNomeSetor();
		if(nome != nom) {
			try {
				System.out.println("Algo aqui");
				setorAlterar.setCodigoSetor(codigo);
			} catch (ModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int cod = setorAlterar.getCodSetor();
		if(codigo == cod) {
			try {
				System.out.println("Algo lá");
				setorAlterar.setNomeSetor(nome);
			} catch (ModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		this.janelaAlterar.notificar("O setor foi alterado com sucesso!");
		DaoSetor daoSetor = new DaoSetor();
		daoSetor.alterar(setorAlterar);
		this.encerrar();
	}
	
	//Alterando os outros setores em que os grupos o possuem
	private void altualizandoOsOutros() {
		DaoGrupo daoGrupo = new DaoGrupo();
		Grupo[] grupoLista = daoGrupo.obterListaObjetos();
		
		for(int i= 0, j = grupoLista.length; i < j; i++) {
			if(setorAntigo == grupoLista[i].getCodigoSetor()) {
				try {
					grupoLista[i].setCodigoSetor(setorAlterar);
				} catch (ModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	//Alterar os colaboradores que estão no setor alterado
	
	@Override
	public void iniciar() {
		// TODO Auto-generated method stub
		//Vá no contrutor que vai estar lá
	}
	
	@Override
	public void encerrar() {
		// TODO Auto-generated method stub
		this.janelaAlterar.fechar();
		CtrlSetor ctrl = (CtrlSetor)getCtrlPai();
		ctrl.informarFinalizacaoAlterarSetor();
	}
}
