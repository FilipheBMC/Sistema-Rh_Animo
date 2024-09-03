package model;

import java.io.Serializable;

public class Setor implements Serializable{
	
	private String nomeSetor;
	private int codigoSetor;
	
	public Setor(String setor, int codigoSetor)throws ModelException {
		this.setNomeSetor(setor);
		this.setCodigoSetor(codigoSetor);
	}
	
	//Métodos get e set do código do setor
	public int getCodSetor() {
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
	
	public String toString() {
		return "O nome do setor é: " + getNomeSetor() + "\n" +
				"O código é: " + getCodSetor();
	}
	
	public void setCodigoSetor(int codSetor)throws ModelException {
		validaCodSetor(codSetor);
		this.codigoSetor = codSetor;
	}
	
	//
	//	FAZER O MÉTODO DE VALIDAÇÃO DO CÓDIGO DE SETOR
	//
	
	private static void validaCodSetor(int codigoSetor)throws ModelException {
		if(codigoSetor == 0)
			throw new ModelException("O Códogio do setor não pode ser 0.");
		if(codigoSetor < 0)
			throw new ModelException("O códgio do setor não pode ser menor que 0.");
	}
	
	private static void validaNomeSetor(String nomeSetor)throws ModelException {
		if(nomeSetor == null)
			throw new ModelException("O nome do setor não pode ser nulo.");
		
		int numQtd = nomeSetor.length();
		
		if(numQtd < 4)
			throw new ModelException("O nome do setor não pode ter menos de 4 caracteres.");
	}
	

}
