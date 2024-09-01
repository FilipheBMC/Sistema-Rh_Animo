package controller;

abstract public class CtrlAbstrato {
	//
	// ATRIBUTOS
	//
	/**
	 * Referência para o controlador (pai) que solicitou a sua execução
	 */
	final private CtrlAbstrato ctrlPai;
	
	
	/**
	 * O construtor vai determinar que toda vez que um objeto controlador
	 * for instanciado, ele iniciará a execução do caso de uso
	 */
	public CtrlAbstrato(CtrlAbstrato c) {
		this.ctrlPai = c;
		this.iniciar();		
	}
	
	/**
	 * Retorna a referência para o controlador pai (ou seja, quem solicitou 
	 * a execução DESTE caso de uso
	 */
	public CtrlAbstrato getCtrlPai() {
		return this.ctrlPai;		
	}
	
	/**
	 * Método a ser adicionado nos controladores indicando o que 
	 * deve ser feito quando ele é iniciado.
	 */
	public abstract void iniciar();
	/**
	 * Método a ser adicionado nos controladores indicando o que 
	 * deve ser feito quando ele é iniciado.
	 */
	public abstract void encerrar();
}
