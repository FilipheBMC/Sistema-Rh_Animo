package controller;

import model.Grupo;
import model.dao.DaoGrupo;
import viewer.JanelaExcluirGrupoConfirmacao;

public class CtrlExcluirGrupo extends CtrlAbstrato {

	//
	// ATRIBUTOS
	//
	private Grupo grupo;
	private JanelaExcluirGrupoConfirmacao janela;

	//
	// MÉTODOS
	//
	public CtrlExcluirGrupo(CtrlAbstrato c, Grupo grupo) {
		super(c);
		this.grupo = grupo;
		String nomeGrupo = (String) this.grupo.getNome();
		janela = new JanelaExcluirGrupoConfirmacao(this, nomeGrupo);
	}

	public void excluirGrupo() {
		DaoGrupo daoGrupo = new DaoGrupo();
		daoGrupo.remover(grupo);
		this.janela.notificar("Setor exlcuido com Sucesso!");
		encerrar();
	}

	@Override
	public void encerrar() {
		janela.fechar();
		CtrlConsultarGrupo ctrl  =(CtrlConsultarGrupo)getCtrlPai();
		ctrl.informarFimDeExcluirGrupo();
		ctrl.atualziarJanela();
	}

	@Override
	public void iniciar() {
		// Verificar no contrutor, está tudo lá
	}

}
