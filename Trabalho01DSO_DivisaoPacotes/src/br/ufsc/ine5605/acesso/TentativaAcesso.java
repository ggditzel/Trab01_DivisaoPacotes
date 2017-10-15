package br.ufsc.ine5605.acesso;

import br.ufsc.ine5605.horario.Hora;

public abstract class TentativaAcesso {
	protected String data;
	protected Hora hora;
	protected int matricula;
	
	
	public TentativaAcesso(String data, Hora hora, int matricula) {
		this.data = data;
		this.hora = hora;
		this.matricula = matricula;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Hora getHora() {
		return hora;
	}
	public void setHora(Hora hora) {
		this.hora = hora;
	}
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	
}
