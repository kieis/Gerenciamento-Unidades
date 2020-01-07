package br.com.poo.sysfi.view;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public final class LimitadoTextField extends JTextField {
	public final static int SOMENTELETRAS = 1;
	public final static int SOMENTENUMEROS = 0;
	
	private int maximoCaracteres=-1;
	private int tipo;
	
	public LimitadoTextField(int tipo, int maximo) {
		super();
		setTipo(tipo);
		setMaximoCaracteres(maximo);
		addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent evt) {
				jTextFieldKeyTyped(evt);
			}
		});
	}
	
	private void jTextFieldKeyTyped(KeyEvent evt) {
		String caracteres="0987654321";
		
		if(getTipo() == SOMENTELETRAS) {
			if(caracteres.contains(evt.getKeyChar() + "")) {
				evt.consume();
			}
		}else if(getTipo() == SOMENTENUMEROS) {
			if(!caracteres.contains(evt.getKeyChar()+"")) {
				evt.consume();
			}
		}
		
		if((getText().length()>=getMaximoCaracteres()) && (getMaximoCaracteres() !=-1)) {
			evt.consume();
			setText(getText().substring(0,getMaximoCaracteres()));
		}
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getMaximoCaracteres() {
		return maximoCaracteres;
	}

	public void setMaximoCaracteres(int maximoCaracteres) {
		this.maximoCaracteres = maximoCaracteres;
	}
}