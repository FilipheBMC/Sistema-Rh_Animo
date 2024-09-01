package model.dao;

import java.io.Serializable;

import model.Setor;

public class DaoSetor implements Serializable {
	//
	// CONSTANTES
	//
	final public static int TAMANHO = 3;
	final public static int FATOR_CRESCIMENTO = 2;
	
	//
	// ATRIBUTOS
	//
	private static Setor[] listaSetors = new Setor[TAMANHO];
	private static int numObjetos = 0;
	
	//
	// MÉTODOS
	//
	public boolean adicionar(Setor novo) {
		// Se o parâmetro 'novo' for nulo, não faz nada.
		if(novo == null)
			return false;
		// Se o array 'listaSetors' já estiver cheio...
		if(DaoSetor.numObjetos == DaoSetor.listaSetors.length) {
			Setor[] novoArray = new Setor[DaoSetor.listaSetors.length + FATOR_CRESCIMENTO];
			for(int i = 0; i < DaoSetor.numObjetos; i++) 
				novoArray[i] = DaoSetor.listaSetors[i];
			DaoSetor.listaSetors = novoArray;			
		}
		// Coloco a referência do objeto no array
		DaoSetor.listaSetors[DaoSetor.numObjetos] = novo;
		// Incremento o numObjetos
		DaoSetor.numObjetos++;
		// Solicitando a atualização dos dados no arquivo de serialização
		Serializador.salvarObjetos();		
		// informo que a adição foi bem sucedida
		return true;		
	}
	
	public boolean alterar(Setor alterado) {
		// Solicitando a atualização dos dados no arquivo de serialização
		Serializador.salvarObjetos();		
		// informo que a adição foi bem sucedida
		return true;		
	}
	
	public boolean remover(Setor a) {
		// Procurando a posição onde 'a' está no DaoSetor
		for(int i = 0; i < DaoSetor.numObjetos; i++) {
			// Se o 'a' está na posição 'i'
			if(DaoSetor.listaSetors[i] == a) {
				// Vamos trazer todos os objetos à frente de 'a' uma posição para trás 
				for(int j = i; j < DaoSetor.numObjetos - 1; j++) 
					DaoSetor.listaSetors[j] = DaoSetor.listaSetors[j + 1];
				// Como copiei o último elemento uma posição para trás
				// coloco null na posição que ele ocupava
				DaoSetor.listaSetors[DaoSetor.numObjetos - 1] = null;
				// Decremento o número de objetos presentes no DaoSetor
				DaoSetor.numObjetos--;
				// Solicitando a atualização dos dados no arquivo de serialização
				Serializador.salvarObjetos();				
				// Retorno 'true' informando que retirei o Setor
				return true;
			}
		}
		return false;	
	}

	public Setor obter(int indice) {
		if(indice < 0 || indice >= DaoSetor.numObjetos)
			return null;
		return DaoSetor.listaSetors[indice];
	}
	
	public int obterNumObjetos() {
		return DaoSetor.numObjetos;
	}
	
	public Setor[] obterListaObjetos() {
		Setor[] copia = new Setor[DaoSetor.numObjetos];
		System.arraycopy(DaoSetor.listaSetors, 0, copia, 0, DaoSetor.numObjetos);
		return copia;
	}
	
	public void adicionarTodos(Setor[] copia) {
		for(int i = 0; i < copia.length; i++)
			this.adicionar(copia[i]);
	}
}
 