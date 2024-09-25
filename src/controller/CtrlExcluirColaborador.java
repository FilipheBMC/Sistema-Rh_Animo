package controller;

import model.Colaborador;
import model.dao.DaoColaborador;
import viewer.JanelaExcluirColaboradorCofirmacao;

public class CtrlExcluirColaborador extends CtrlAbstrato{

	//
	//		ATRIBUTOS
	//
	private JanelaExcluirColaboradorCofirmacao janela;
	private Colaborador						   colaborador;	
	
	
	//
	//		MÉTODOS
	//
	
	public CtrlExcluirColaborador(CtrlAbstrato c, Colaborador colaborador) {
		super(c);
		this.colaborador = colaborador;
		String nomeColab = colaborador.getNome();
		
		this.janela = new JanelaExcluirColaboradorCofirmacao(this, nomeColab);
	}
	
	public void excluirColaborador() {
		DaoColaborador daoColaborador = new DaoColaborador();
		daoColaborador.remover(colaborador);
		this.janela.notificar("Colaborador excluido com sucesso!");
		this.encerrar();
	}
	
	@Override
	public void iniciar() {
		//Ver do construtor, está lá
	}
	
	@Override
	public void encerrar() {
		this.janela.fechar();
		CtrlConsultarColaborador ctrl = (CtrlConsultarColaborador)getCtrlPai();
		ctrl.informarFinalizacaoExcluirColaborador();
	}
}
