package model.dao;

import java.io.Serializable;

import model.Grupo;

public class DaoGrupo implements Serializable {
	//
	// CONSTANTES
	//
	final public static int TAMANHO = 3;
	final public static int FATOR_CRESCIMENTO = 2;
	
	//
	// ATRIBUTOS
	//
	private static Grupo[] listaGrupos = new Grupo[TAMANHO];
	private static int numObjetos = 0;
	
	//
	// MÉTODOS
	//
	public boolean adicionar(Grupo novo) {
		// Se o parâmetro 'novo' for nulo, não faz nada.
		if(novo == null)
			return false;
		// Se o array 'listaGrupos' já estiver cheio...
		if(DaoGrupo.numObjetos == DaoGrupo.listaGrupos.length) {
			Grupo[] novoArray = new Grupo[DaoGrupo.listaGrupos.length + FATOR_CRESCIMENTO];
			for(int i = 0; i < DaoGrupo.numObjetos; i++) 
				novoArray[i] = DaoGrupo.listaGrupos[i];
			DaoGrupo.listaGrupos = novoArray;			
		}
		// Coloco a referência do objeto no array
		DaoGrupo.listaGrupos[DaoGrupo.numObjetos] = novo;
		// Incremento o numObjetos
		DaoGrupo.numObjetos++;
		// Solicitando a atualização dos dados no arquivo de serialização
		Serializador.salvarObjetos();		
		// informo que a adição foi bem sucedida
		return true;		
	}
	
	public boolean alterar(Grupo alterado) {
		// Solicitando a atualização dos dados no arquivo de serialização
		Serializador.salvarObjetos();		
		// informo que a adição foi bem sucedida
		return true;		
	}
	
	public boolean remover(Grupo a) {
		// Procurando a posição onde 'a' está no DaoGrupo
		for(int i = 0; i < DaoGrupo.numObjetos; i++) {
			// Se o 'a' está na posição 'i'
			if(DaoGrupo.listaGrupos[i] == a) {
				// Vamos trazer todos os objetos à frente de 'a' uma posição para trás 
				for(int j = i; j < DaoGrupo.numObjetos - 1; j++) 
					DaoGrupo.listaGrupos[j] = DaoGrupo.listaGrupos[j + 1];
				// Como copiei o último elemento uma posição para trás
				// coloco null na posição que ele ocupava
				DaoGrupo.listaGrupos[DaoGrupo.numObjetos - 1] = null;
				// Decremento o número de objetos presentes no DaoGrupo
				DaoGrupo.numObjetos--;
				// Solicitando a atualização dos dados no arquivo de serialização
				Serializador.salvarObjetos();				
				// Retorno 'true' informando que retirei o Grupo
				return true;
			}
		}
		return false;	
	}

	public Grupo obter(int indice) {
		if(indice < 0 || indice >= DaoGrupo.numObjetos)
			return null;
		return DaoGrupo.listaGrupos[indice];
	}
	
	public int obterNumObjetos() {
		return DaoGrupo.numObjetos;
	}
	
	public Grupo[] obterListaObjetos() {
		Grupo[] copia = new Grupo[DaoGrupo.numObjetos];
		System.arraycopy(DaoGrupo.listaGrupos, 0, copia, 0, DaoGrupo.numObjetos);
		return copia;
	}
	
	public void adicionarTodos(Grupo[] copia) {
		for(int i = 0; i < copia.length; i++)
			this.adicionar(copia[i]);
	}
}
 