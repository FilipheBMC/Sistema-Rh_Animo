package controller;

import model.Colaborador;
import model.dao.DaoColaborador;
import viewer.JanelaConsultarColaboradores;

public class CtrlConsultarColaborador extends CtrlAbstrato{

	//
	//	ATRIBUTOS
	//
	
	//	JANELAS
	private JanelaConsultarColaboradores 	janelaConsultarColaboradores;
	
	//
	//	CTRLS
	//
	
	private CtrlAdicionarColaborador 		ctrlAdicionarColaborador;
	private CtrlAlterarColaborador 			ctrlAlterarColaborador;
	private CtrlExcluirColaborador			ctrlExcluirColaborador;
	
	public CtrlConsultarColaborador(CtrlAbstrato ctrl) {
		super(ctrl);
	}
	
	/**Função para atualizar tabela*/
	public void atualizarTabela() {
		System.out.println("outro erro");
		DaoColaborador daoColaboradores = new DaoColaborador();
		Colaborador[] listaColab = daoColaboradores.obterListaObjetos();
		this.janelaConsultarColaboradores.atualizarDados(listaColab);
	}

	
	/*
	 * Chamando os Ctrls para serem incializados e encerrados
	 * */
	
	//Chamando o Ctrl de adcionar pessoa
	
	public void iniciarAdicionarPessoa() {
		if(ctrlAdicionarColaborador == null) {
			this.ctrlAdicionarColaborador = new CtrlAdicionarColaborador(this);
		}

	}
	
	public void informarEncerramentoAdicionarColaborador() {
		this.atualizarTabela();
		this.ctrlAdicionarColaborador = null;
	}
	
	//Chamando o Ctrl de alterarcolaborador
	
	public void iniciarAlterarColaborador(Colaborador colaboradorSelecionado) {
		if(ctrlAlterarColaborador == null)
			this.ctrlAlterarColaborador = new CtrlAlterarColaborador(this, colaboradorSelecionado);
	}
	
	public void informarEncerramentoAlterarColaborador() {
		this.atualizarTabela();
		this.ctrlAlterarColaborador = null;
	}
	
	// -----------    #-#    --------------
	//Chamando o inciar e finalizar exclusão de colaborador
	
	public void iniciarExcluirColaborador(Colaborador colaborador) {
		if(ctrlExcluirColaborador == null)
			this.ctrlExcluirColaborador = new CtrlExcluirColaborador(this, colaborador);
	}
	
	public void informarFinalizacaoExcluirColaborador() {
		this.atualizarTabela();
		ctrlExcluirColaborador = null;
	}
	
	// -----------    #-#    --------------
	
	
	//Chamando o Adicionar Colaborador
	

	@Override
	public void iniciar() {
		DaoColaborador daoColab = new DaoColaborador();
		Colaborador[] ArrayColaborador = daoColab.obterListaObjetos();
		this.janelaConsultarColaboradores = new JanelaConsultarColaboradores(this, ArrayColaborador);
		this.janelaConsultarColaboradores.atualizarDados(ArrayColaborador);
	}

	@Override
	public void encerrar() {
		// TODO Auto-generated method stub
		this.janelaConsultarColaboradores.fechar();
		//Notificando ao controlador que o caso de uso se encerrou
		CtrlPrograma ctrl = (CtrlPrograma)this.getCtrlPai();
		ctrl.informarFimDeConsultarColaboradores();
		
	}
}
