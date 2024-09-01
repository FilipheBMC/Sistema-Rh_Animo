package viewer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.CtrlAbstrato;

abstract public class ViewerAbstrato extends JFrame {
	//
	// ATRIBUTOS
	//
	/**
	 * Guarda a referência para o controlador de caso de uso que é o dono da janela
	 */
	final private CtrlAbstrato ctrl;

	//
	// MÉTODOS
	//
	public ViewerAbstrato(CtrlAbstrato c) {
		super();
		this.ctrl = c;
	}
	
	/**
	 * Retorna a referência para o controlador de caso de uso que é o dono da janela
	 */
	public CtrlAbstrato getCtrl() {
		return this.ctrl;
	}
	
	/**
	 * Exibe uma janela modal com uma mensagem para o usuário
	 * @param texto
	 */
	public void notificar(String texto) {
		JOptionPane.showMessageDialog(null, texto);
	}
	
	/**
	 * Solicito que a janela seja fechada
	 */
	public void fechar() {
		this.setVisible(false);
	}
}
