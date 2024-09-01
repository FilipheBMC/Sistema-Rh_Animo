package model;

import java.io.Serializable;

public class Setor implements Serializable{
	
	private String nomeSetor;
	private String codigoSetor;
	
	public Setor(String setor, String codigoSetor)throws ModelException {
		this.setNomeSetor(setor);
	}
	
	//Métodos get e set do código do setor
	public String getCodSetor() {
		return codigoSetor;
	}

	//Métodos get e set do nome do setor
	public String getNomeSetor() {
		return nomeSetor;
	}

	public void setNomeSetor(String nomeSetor)throws ModelException {
		validaNomeSetor(nomeSetor);
		this.nomeSetor = nomeSetor;
	}
	
	private static void validaNomeSetor(String nomeSetor)throws ModelException {
		if(nomeSetor == null)
			throw new ModelException("O nome do setor não pode ser nulo.");
		
		int numQtd = nomeSetor.length();
		
		if(numQtd < 4)
			throw new ModelException("O nome do setor não pode ter menos de 4 caracteres.");
	}
}
