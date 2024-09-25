package controller;

import model.Colaborador;
import model.Grupo;
import model.ModelException;
import model.Setor;
import model.dao.DaoColaborador;
import model.dao.DaoGrupo;
import model.dao.DaoSetor;
import viewer.JanelaAlterarColaborador;

public class CtrlAlterarColaborador extends CtrlAbstrato{

	//
	// ATRIBUTOS
	//
	
	private JanelaAlterarColaborador janelaAlterarColaboradores;
	private DaoColaborador			 daoColaborador;
	private Colaborador 			 colaboradorAlterar;
	private Colaborador				 colaboradorAntigo;
	
	
	public CtrlAlterarColaborador(CtrlAbstrato ctrl, Colaborador colaborador) {
		super(ctrl);
		
		//Fazendo um array de setores
		DaoSetor daoSetor = new DaoSetor();
		Setor[] listaSetores = daoSetor.obterListaObjetos();
		
		//Fazendo um array de Grupos
		DaoGrupo daoGrupo = new DaoGrupo();
		Grupo[]  listaGrupos = daoGrupo.obterListaObjetos();
		
		this.colaboradorAntigo = colaborador;
		this.colaboradorAlterar = colaborador;
		
		this.janelaAlterarColaboradores = new JanelaAlterarColaborador(this, colaborador, listaSetores, listaGrupos);
	}
	
	//Método de alteração do dado do Colaborador
	public void alterarDadosDoColaborador(String nome, String cpf, String sexo, String cargo,
										 String dataEntrada, String dataSaida, String dataNascimento,
										 Setor setr, Grupo grupo) {
		
		//Verificando  se houve alteração no nome e alterando caso se tenha alguma ateração
		if(!colaboradorAntigo.getNome().equals(nome)) {
			try {
				colaboradorAlterar.setNome(nome);
			}
			catch(ModelException e) {
				e.printStackTrace();
			}
		}
		
		//Verificando se houve alteração no cpf e alterando caso tenha alguma alteração
		if(!colaboradorAntigo.getCpf().equals(cpf)) {
			try {
				colaboradorAlterar.setCpf(cpf);
			}
			catch(ModelException e) {
				e.printStackTrace();
			}
		}
		
		//Verificando se houve alguma alteração no sexo e alterando caso tenha sido alterado
		if(!colaboradorAntigo.getSexo().equals(sexo)) {
			try {
				colaboradorAlterar.setSexo(sexo);
			}
			catch(ModelException e) {
				e.printStackTrace();
			}
		}
		
		//Verifiando se houve alguma alteração na data de nascimento e alterando caso tenha ocorrido alguma alteração
		if(!colaboradorAntigo.getDataNascimento().equals(dataNascimento)) {
			try {
				colaboradorAlterar.setDataNascimento(dataNascimento);
			}
			catch(ModelException e) {
				e.printStackTrace();
			}
		}
		
		//Verificando se houve alguma alteração na data de entrada e caso tenha ocorrido ira alterar
		if(!colaboradorAntigo.getDataEntrada().equals(dataEntrada)) {
			try {
				colaboradorAlterar.setDataEntrada(dataEntrada);
			}
			catch(ModelException e) {
				e.printStackTrace();
			}
		}
		
		//Verificando se houve alguma alteração no data de saida e caso tenha ocorrido ira guardar a alteração
		if(!colaboradorAntigo.getDataSaida().equals(dataSaida)) {
			try{
				colaboradorAlterar.setDataSaida(dataSaida);
			}
			catch(ModelException e) {
				e.printStackTrace();
			}
		}
		
		//Verificando se ocorreu alguma alteração no grupo em que o colaborador é pertencente
		if(!colaboradorAntigo.getCodigoGrupo().getNome().equals(grupo.getNome())) {
			try {
				colaboradorAlterar.setGrupo(grupo, null);
			}
			catch(ModelException e) {
				e.printStackTrace();
			}
		}
		
		//Verificando se houve alguma alteração no cargo e caso tenha ocorrido irá alterar
		if(!colaboradorAntigo.getCargo().equals(cargo)) {
			try {
				colaboradorAlterar.setCargo(cargo);
			}
			catch(ModelException e) {
				e.printStackTrace();
			}
		}
		
		//Verificando se houve alguma alteração no setor e caso tenha ocorrido ira altera-lo
		
		if(!colaboradorAntigo.getCodigoGrupo().getCodigoSetor().getNomeSetor().equals(grupo.getCodigoSetor().getNomeSetor())) {
			try {
				colaboradorAlterar.getCodigoGrupo().setCodigoSetor(setr);
			}
			catch(ModelException e) {
				e.printStackTrace();
			}
		}
		
		
		//Agora estamos finalizando o a alteração
		this.janelaAlterarColaboradores.notificar("Alteração do colaborador executada com sucesso!");
		DaoColaborador daoAtualizar = new DaoColaborador();
		daoAtualizar.alterar(colaboradorAlterar);
		this.encerrar();
		
	}
	
	/*
	 * Métodos de incializar e encerrar
	 * */
	
	//Informando a incialização
	public void iniciar() {
		//A janela vai estar sendo inicializada no construtor
	}
	
	
	//Informando o encerramento do ctrl
	public void encerrar() {
		janelaAlterarColaboradores.fechar();
		CtrlConsultarColaborador ctrl = (CtrlConsultarColaborador)getCtrlPai();
		ctrl.informarEncerramentoAlterarColaborador();
	}
}
