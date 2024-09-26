package controller;

import java.util.ArrayList;
import java.util.List;

import model.Setor;
import model.dao.DaoSetor;
import viewer.JanelaSetor;

public class CtrlSetor extends CtrlAbstrato {

	//
	// ATRIBUTOS
	//

	private JanelaSetor janelaSetor;
	private Setor setor;
	private DaoSetor daoSetor;
	private CtrlExcluirSetor ctrlExcluirSetor;
	private CtrlAlterarSetor ctrlAlterarSetor;

	//
	// METODOS
	//

	public CtrlSetor(CtrlAbstrato c) {
		super(c);
	}

	// MÉTODO DE VALIDAÇÃO DO SETOR E ARMAZENAMENTO DE DADOS

	public void adicionarSetor(String nome, int cod) {
		try {
			this.setor = new Setor(nome, cod);
			daoSetor = new DaoSetor();
			this.daoSetor.adicionar(setor);
			this.atualizar();
			this.janelaSetor.notificar(setor.toString());

		} catch (Exception me) {
			this.janelaSetor.notificar(me.getMessage());
		}
	}

	// MÉTODO EXCLUIR SETOR
	public void excluirSetor(Setor setor) {
		if (ctrlExcluirSetor == null)
			ctrlExcluirSetor = new CtrlExcluirSetor(this, setor);
	}

	public void informarFinalizarExcluirSetor() {
		ctrlExcluirSetor = null;
		this.atualizar();
	}

	public void atualizar() {
		daoSetor = new DaoSetor();
		Setor[] listaSetor = daoSetor.obterListaObjetos();
		this.janelaSetor.atualizarDados(listaSetor);
	}

	//	ALTERAÇÂO DE SETOR
	
	//Iniciando
	public void iniciarAlterarSetor(Setor setorEscolhido) {
		if(ctrlAlterarSetor == null)
			this.ctrlAlterarSetor = new CtrlAlterarSetor(this, setorEscolhido);
	}
	
	//Recebendo a finalização
	public void informarFinalizacaoAlterarSetor() {
		ctrlAlterarSetor = null;
		this.atualizar();
	}
	
	/**
	 * Consultando setores existentes.
	 * */
	public void Consulta(String nome, int codigo) {
	    daoSetor = new DaoSetor();
	    
	    // Se nenhum campo estiver preenchido, notifica o usuário
	    if (nome.isEmpty() && codigo == 0) {
	        this.atualizar();  // Aqui ele deveria atualizar para o estado original
	        this.janelaSetor.notificar("Para consultar o setor, preencha algum campo.");
	        return;
	    }

	    Setor[] listaSetores = daoSetor.obterListaObjetos();
	    List<Setor> setoresEncontrados = new ArrayList<>();  // Usar lista para evitar posições nulas

	    // Se o código for zero, busca por nome
	    if (codigo == 0) {
	        for (Setor setor : listaSetores) {
	            if (nome.equalsIgnoreCase(setor.getNomeSetor())) {  // Verifica o nome
	                setoresEncontrados.add(setor);
	            }
	        }
	    }
	    // Se o nome estiver vazio, busca por código
	    else if (nome.isEmpty()) {
	        for (Setor setor : listaSetores) {
	            if (codigo == setor.getCodSetor()) {  // Verifica o código
	                setoresEncontrados.add(setor);
	            }
	        }
	    }

	    // Atualiza a tabela com os resultados da busca
	    if (!setoresEncontrados.isEmpty()) {
	        Setor[] arraySetoresEncontrados = setoresEncontrados.toArray(new Setor[0]);
	        this.janelaSetor.atualizarDados(arraySetoresEncontrados);  // Atualiza JTable com os setores encontrados
	    } else {
	        this.janelaSetor.atualizarDados(listaSetores);  // Aqui, restaura a tabela original
	        this.janelaSetor.notificar("Nenhum setor foi encontrado.");
	    }
	}


	@Override
	public void iniciar() {
		DaoSetor daoSet = new DaoSetor();
		Setor[] listaSetor = daoSet.obterListaObjetos();
		this.janelaSetor = new JanelaSetor(this, listaSetor);
		this.janelaSetor.atualizarDados(listaSetor);

	}

	@Override
	public void encerrar() {
		// TODO Auto-generated method stub
		this.janelaSetor.fechar();
		CtrlPrograma ctrl = (CtrlPrograma) getCtrlPai();
		ctrl.informarFimSetor();
	}

}
