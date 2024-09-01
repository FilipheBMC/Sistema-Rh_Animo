package model;

import java.io.Serializable;

import model.dao.DaoGrupo ;
import model.dao.DaoSetor;

public class Grupo implements Serializable{
	
	private String nome;
	private String codSetor;
	private String codGrupo;
	
	public Grupo(String nome, String codSetor, String codGrupo) throws ModelException{
		this.setNome(nome);
		this.setCodigoGrupo(codGrupo);
		this.setCodigoSetor(codSetor);
	}
	
	//
	//	MÉTODOS GETS E SETS DO CÓDIGO SETOR
	//
	
	public String getCodigoSetor() {
		return this.codSetor;
	}
	
	public void setCodigoSetor(String codSetor) throws ModelException {
		validaCodigoSetor(codSetor);
		this.codSetor = codSetor;
	}
	
	// ---------------------------------------------------------------------------
	
	//
	//	MÉTODOS GETS E SETS DO CÓDIGO GRUPO 
	//
	
	public String getCodigoGrupo() {
		return this.codGrupo;
	}
	
	public void setCodigoGrupo(String codigo)throws ModelException {
		validaCodigoGrupo(codigo);
		this.codSetor = codigo;
	}
	
	// ----------------------------------------------------------------------

	//
	//	MÉTODOS GETS E SETS DO ATRIBUTO NOME
	//
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome)throws ModelException {
		validaNome(nome);
		this.nome = nome;
	}
	
	// -------------------------------------------------------------------------
	
	//
	//		METODOS DE VALIDAÇÃO
	//
	
	private static void validaCodigoSetor(String cod)throws ModelException {
		
		
		DaoSetor setor = new DaoSetor();
		
		Setor[] set = setor.obterListaObjetos();
		
		for(int i = 0, qtdGrup = set.length; i < qtdGrup; i++) {
			if(set[i].getCodSetor().equals(cod))
				throw new ModelException("O código não pode ser igual ao do setor " + set[i].getNomeSetor());
		}
	}
	
	private static void validaCodigoGrupo(String cod)throws ModelException {
		
		for(int i = 0, qtd = cod.length(); i < qtd; i++) {
			char numStr = cod.charAt(i);
			
			if(!Character.isDigit(numStr))
				throw new ModelException("O código deve possuir somente caracteres.");
			
			int num = Integer.parseInt(cod);
			
			if(num < 0)
				throw new ModelException("O código não pode ser menor que 0");
			else if(num > 1000)
				throw new ModelException("O código não pode ser maior que 1000.");
			
		}
		
		DaoGrupo grup = new DaoGrupo();
		
		Grupo[] grupo = grup.obterListaObjetos();
		
		for(int i = 0, qtdGrup = grupo.length; i < qtdGrup; i++) {
			if(grupo[i].getCodigoGrupo().equals(cod))
				throw new ModelException("O código não pode ser igual ao do grupo " + grupo[i].getNome());
		}
	}
	
	private static void validaNome(String nom)throws ModelException {
		if(nom == null) {
			throw new ModelException("O nome do grupo não pode ser vazio.");
		}
		
		if(nom.length() < 4) {
			throw new ModelException("O nome do grupo deve conter pelo menos 4 caracteres.");
		}
	}
}
