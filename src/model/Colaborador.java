package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Colaborador implements Serializable{

	//
	// ATRIBUTOS
	//
	
	private String cpf;
	private String nome;
	private String sexo;
	private String dataNascimento;
	private String setor;
	private String cargo;
	private String dataEntrada;
	private String dataSaida;
	private int codigoSetor;
	private int codigoGrupo;

	//
	// METÓDOS
	//
	
	public Colaborador(String cpf, String nome, String sexo, String dataNascimento, String setor, String cargo,
			String dataEntrada, String dataSaida, int codigoSetor, int  codigoGrupo) throws ModelException {
		super();
		this.setCpf(cpf);
		this.setNome(nome);
		this.setSexo(sexo);
		this.setDataNascimento(dataNascimento);
		this.setSetor(setor);
		this.setCargo(cargo);
		this.setDataEntrada(dataEntrada);
		this.setDataSaida(dataSaida);
		this.setCodigoSetor(codigoSetor);
		this.setCodigoGrupo(codigoGrupo);
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) throws ModelException {
		validarCpf(cpf);
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws ModelException {
		validarNome(nome);
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) throws ModelException {
		validarSexo(sexo);
		this.sexo = sexo;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) throws ModelException {
		validarDataNascimento(dataNascimento);
		this.dataNascimento = dataNascimento;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) throws ModelException {
		validarSetor(setor);
		this.setor = setor;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) throws ModelException {
		validarCargo(cargo);
		this.cargo = cargo;
	}

	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) throws ModelException {
		validarDataEntrada(dataEntrada);
		this.dataEntrada = dataEntrada;
	}

	public String getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(String dataSaida) throws ModelException {
		validarDataSaida(dataSaida);
		this.dataSaida = dataSaida;
	}
	
	//MÉTODOS GETS E SETS PARA O SETOR
	
	public int getCodigoSetor() {
		return this.codigoSetor;
	}
	
	public void setCodigoSetor(int cod)throws ModelException {
		validaCodigoSetor(cod);
		this.codigoSetor = cod;
	}
	
	//MÉTODOS GETS E SETS PARA O GRUPO
	
	public int getCodigoGrupo(){
		return this.codigoGrupo;
	}
	
	public void setCodigoGrupo(int cod)throws ModelException {
		validaCodigoGrupo(cod);
		this.codigoGrupo = cod;
	}
	
	//
	//MÉTODOS DE VALIDAÇÃO
	//

	public static void validaCodigoSetor(int cod)throws ModelException {
		
		if(cod == 0 || cod < 0)
			throw new ModelException("O código não pode ser menor que 0.");
		if(cod > 100) {
			throw new ModelException("O código não pode ser maior que 100");
		}
	}
	
	public static void validaCodigoGrupo(int cod)throws ModelException{
		if(cod == 0 || cod < 0)
			throw new ModelException("O código não pode ser menor igual a zero.");
	}
	
	public static void validarCpf(String cpf) throws ModelException {
		if (cpf == null)
			throw new ModelException("O cpf não pode ser nulo!");

		int tamanho = cpf.length();
		if (tamanho != 14)
			throw new ModelException("O cpf deve ter 14 caracteres!");

		for (int pos = 0; pos < tamanho; pos++) {
			char c = cpf.charAt(pos);
			switch (pos) {
			case 3:
			case 7:
				if (c != '.')
					throw new ModelException("Na posição " + pos + " do cpf deve ter '.'");
				break;
			case 11:
				if (c != '-')
					throw new ModelException("Na posição 11 do cpf deve ter '-'");
				break;
			default:
				if (!Character.isDigit(c))
					throw new ModelException("Na posição " + pos + " do cpf deve constar um dígito: " + c);
				break;
			}
		}
	}

	public static void validarNome(String nome) throws ModelException {
		if (nome == null)
			throw new ModelException("O nome não pode ser nulo!");

		int tamanho = nome.length();
		if (tamanho < 5 || tamanho > 40)
			throw new ModelException("O número de caracteres do nome é inválido: " + tamanho);

		for (int i = 0; i < tamanho; i++) {
			char c = nome.charAt(i);
			if (!Character.isAlphabetic(c) && !Character.isSpaceChar(c))
				throw new ModelException("O caracter na posição " + i + " é inválido: " + c);
		}
	}

	public static void validarSexo(String sexo) throws ModelException {
		if (sexo != "M" && sexo != "F") {
			throw new ModelException("Sexo Inváliado! Use 'M' ou 'F'");
		}
	}

	public static void validarDataNascimento(String dataNascimento) throws ModelException {
		if (dataNascimento == null) {
			throw new ModelException("A data de nascimento não pode ser nula!");
		}

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate.parse(dataNascimento, formatter);
		} catch (DateTimeParseException e) {
			throw new ModelException("Data de nascimento inválida. Use o formato dd/MM/yyyy.");
		}
	}

	public static void validarDataEntrada(String dataEntrada) throws ModelException {
		if (dataEntrada == null) {
			throw new ModelException("A data de entrada não pode ser nula!");
		}

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate.parse(dataEntrada, formatter);
		} catch (DateTimeParseException e) {
			throw new ModelException("Data de entrada inválida. Use o formato dd/MM/yyyy.");
		}
	}

	public static void validarDataSaida(String dataSaida) throws ModelException {
		if (dataSaida == null) {
			throw new ModelException("A data de saída não pode ser nula!");
		}

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate.parse(dataSaida, formatter);
		} catch (DateTimeParseException e) {
			throw new ModelException("Data de saída inválida. Use o formato dd/MM/yyyy.");
		}
	}

	public static void validarSetor(String setor) throws ModelException {
		if (setor == null)
			throw new ModelException("O setor não pode ser nulo!");

		int tamanho = setor.length();
		if (tamanho < 1 || tamanho > 40)
			throw new ModelException("O número de caracteres do setor é inválido: " + tamanho);
	}

	public static void validarCargo(String cargo) throws ModelException {
		if (cargo == null)
			throw new ModelException("O cargo não pode ser nulo!");

		int tamanho = cargo.length();
		if (tamanho < 1 || tamanho > 40)
			throw new ModelException("O número de caracteres do cargo é inválido: " + tamanho);
	}

	@Override
	public String toString() {
		return this.cpf + "|" + this.nome + "|" + this.sexo + "|" + this.dataNascimento + "|" + this.setor + "|"
				+ this.cargo + "|" + this.dataEntrada + "|" + this.dataSaida + "|";
	}

}
