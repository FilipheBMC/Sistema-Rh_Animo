package controller;

import java.util.ArrayList;
import java.util.List;

import model.Colaborador;
import model.Grupo;
import model.Setor;
import model.dao.DaoColaborador;
import model.dao.DaoGrupo;
import model.dao.DaoSetor;
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
	
	//
	//		CONSULTA DE COLABORADOR
	//
	
	public void conuslta(String nome, String dtNascimento, Setor setor, Grupo grupo, String sexo) {
		DaoColaborador daoColaborador = new DaoColaborador();
		Colaborador[] listaColab = daoColaborador.obterListaObjetos();
		List<Colaborador> colaboradorEncontrados = new ArrayList<Colaborador>();
		int tamColab = listaColab.length;
		
		if(!nome.isEmpty()) {
			for(int i =0; i < tamColab; i++) {
				if(nome.equals(listaColab[i].getNome()))
					colaboradorEncontrados.add(listaColab[i]);
			}
		}
		else if(!dtNascimento.isEmpty()) {
			for(int  i = 0; i < tamColab; i++) {
				if(dtNascimento.equals(listaColab[i].getNome()))
					colaboradorEncontrados.add(listaColab[i]);
			}
		}
		
		else if(setor != null) {
			for(int i = 0; i < tamColab; i++) {
				if(setor.getCodSetor() == listaColab[i].getSetor().getCodSetor())
					colaboradorEncontrados.add(listaColab[i]);
			}
		}
		
		else if(grupo != null) {
			for(int i = 0; i < tamColab; i++) {
				if(grupo.getCodigoGrupo().equals(listaColab[i].getCodigoGrupo().getCodigoGrupo()))
					colaboradorEncontrados.add(listaColab[i]);
			}
		}
		
		else if(sexo != null) {
			for(int i = 0; i < tamColab; i++) {
				if(sexo.equals(listaColab[i].getSexo()))
					colaboradorEncontrados.add(listaColab[i]);
			}
		}
		
		if (!colaboradorEncontrados.isEmpty()) {
		    Colaborador[] arrayColaboradoresEncontrados = colaboradorEncontrados.toArray(new Colaborador[0]);
		    this.janelaConsultarColaboradores.atualizarDados(arrayColaboradoresEncontrados); // Atualiza JTable com os colaboradores encontrados
		} else {
		    this.atualizarTabela(); // Atualizar a janela com todos os colaboradores
		    this.janelaConsultarColaboradores.notificar("Nenhum colaborador foi encontrado.");
		}

	}
	
	// -----------    #-#    --------------
	
	
	//Chamando o Adicionar Colaborador
	

	@Override
	public void iniciar() {
		DaoColaborador daoColab = new DaoColaborador();
		Colaborador[] ArrayColaborador = daoColab.obterListaObjetos();
		DaoSetor daoSetor = new DaoSetor();
		Setor[] listaSetor = daoSetor.obterListaObjetos();
		DaoGrupo daoGrupo = new DaoGrupo();
		Grupo[] listaGrupo = daoGrupo.obterListaObjetos();
		this.janelaConsultarColaboradores = new JanelaConsultarColaboradores(this, ArrayColaborador, listaSetor, listaGrupo);
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
