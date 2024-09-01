package model.dao;

import java.io.Serializable;

import model.Colaborador;

public class DaoColaborador implements Serializable {
	//
	// CONSTANTES
	//
	final public static int TAMANHO = 3;
	final public static int FATOR_CRESCIMENTO = 2;
	
	//
	// ATRIBUTOS
	//
	private static Colaborador[] listaColaboradors = new Colaborador[TAMANHO];
	private static int numObjetos = 0;
	
	//
	// MÉTODOS
	//
	public boolean adicionar(Colaborador novo) {
		// Se o parâmetro 'novo' for nulo, não faz nada.
		if(novo == null)
			return false;
		// Se o array 'listaColaboradors' já estiver cheio...
		if(DaoColaborador.numObjetos == DaoColaborador.listaColaboradors.length) {
			Colaborador[] novoArray = new Colaborador[DaoColaborador.listaColaboradors.length + FATOR_CRESCIMENTO];
			for(int i = 0; i < DaoColaborador.numObjetos; i++) 
				novoArray[i] = DaoColaborador.listaColaboradors[i];
			DaoColaborador.listaColaboradors = novoArray;			
		}
		// Coloco a referência do objeto no array
		DaoColaborador.listaColaboradors[DaoColaborador.numObjetos] = novo;
		// Incremento o numObjetos
		DaoColaborador.numObjetos++;
		// Solicitando a atualização dos dados no arquivo de serialização
		Serializador.salvarObjetos();		
		// informo que a adição foi bem sucedida
		return true;		
	}
	
	public boolean alterar(Colaborador alterado) {
		// Solicitando a atualização dos dados no arquivo de serialização
		Serializador.salvarObjetos();		
		// informo que a adição foi bem sucedida
		return true;		
	}
	
	public boolean remover(Colaborador a) {
		// Procurando a posição onde 'a' está no DaoColaborador
		for(int i = 0; i < DaoColaborador.numObjetos; i++) {
			// Se o 'a' está na posição 'i'
			if(DaoColaborador.listaColaboradors[i] == a) {
				// Vamos trazer todos os objetos à frente de 'a' uma posição para trás 
				for(int j = i; j < DaoColaborador.numObjetos - 1; j++) 
					DaoColaborador.listaColaboradors[j] = DaoColaborador.listaColaboradors[j + 1];
				// Como copiei o último elemento uma posição para trás
				// coloco null na posição que ele ocupava
				DaoColaborador.listaColaboradors[DaoColaborador.numObjetos - 1] = null;
				// Decremento o número de objetos presentes no DaoColaborador
				DaoColaborador.numObjetos--;
				// Solicitando a atualização dos dados no arquivo de serialização
				Serializador.salvarObjetos();				
				// Retorno 'true' informando que retirei o Colaborador
				return true;
			}
		}
		return false;	
	}

	public Colaborador obter(int indice) {
		if(indice < 0 || indice >= DaoColaborador.numObjetos)
			return null;
		return DaoColaborador.listaColaboradors[indice];
	}
	
	public int obterNumObjetos() {
		return DaoColaborador.numObjetos;
	}
	
	public Colaborador[] obterListaObjetos() {
		Colaborador[] copia = new Colaborador[DaoColaborador.numObjetos];
		System.arraycopy(DaoColaborador.listaColaboradors, 0, copia, 0, DaoColaborador.numObjetos);
		return copia;
	}
	
	public void adicionarTodos(Colaborador[] copia) {
		for(int i = 0; i < copia.length; i++)
			this.adicionar(copia[i]);
	}
}
 