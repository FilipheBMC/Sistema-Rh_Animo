package controller;

import model.Colaborador;
import model.Grupo;
import model.ModelException;
import model.Setor;
import model.dao.DaoColaborador;
import viewer.JanelaAdicionarColaborador;

public class CtrlAdicionarColaborador extends CtrlAbstrato{

	//
	//	ATRIBUTOS
	//
	
	// JANELAS
	
	private JanelaAdicionarColaborador janelaAdiconarPessoa;
	
	public CtrlAdicionarColaborador(CtrlAbstrato ctrl) {
		super(ctrl);
	}
	
	/**Adicionar colaborador*/
	public void adicionarColaborador(String nome, String cpf, String DtNascimento,
									 String DtEntrada, String DtSaida,
									 int grupo, int setor, String sexo,
									 String cargo)throws ModelException {
		
		Colaborador colaborador = new Colaborador(cpf, nome, sexo, DtNascimento, "String do setor",
												  "String do cargo", DtEntrada, DtSaida, setor, grupo);
		
		DaoColaborador daoColab = new DaoColaborador();
		daoColab.adicionar(colaborador);
		this.janelaAdiconarPessoa.notificar(colaborador.toString());
		CtrlConsultarColaborador ctrlConsulta = (CtrlConsultarColaborador)getCtrlPai();
		ctrlConsulta.atualizarTabela();
		fechar();
	}
	
	//Fechando a janela adicionar Colaborador
	public void fechar() {
		this.janelaAdiconarPessoa.fechar();
	}
	
	//Abrindo a janela adicionar Colaborador
	@Override
	public void iniciar() {
		this.janelaAdiconarPessoa = new JanelaAdicionarColaborador(this);
	}
	
	@Override
	public void encerrar() {
		this.janelaAdiconarPessoa.fechar();
		CtrlConsultarColaborador ctrl = (CtrlConsultarColaborador)getCtrlPai();
		ctrl.informarEncerramentoAdicionarColaborador();
	}
}
