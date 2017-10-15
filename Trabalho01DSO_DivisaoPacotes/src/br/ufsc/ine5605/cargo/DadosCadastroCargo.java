package br.ufsc.ine5605.cargo;

import java.util.ArrayList;

import br.ufsc.ine5605.horario.Horario;

public class DadosCadastroCargo {
	public int codigo;
	public String nome;
	public boolean ehGerencial;
	public boolean possuiAcesso;
	public ArrayList<Horario> horariosPermitidos;
	
	public DadosCadastroCargo(int codigo, String nome, boolean ehGerencial, boolean possuiAcesso, ArrayList<Horario> horariosPermitidos) {
		this.codigo = codigo;
		this.nome = nome;
		this.ehGerencial = ehGerencial;
		this.possuiAcesso = possuiAcesso;
		this.horariosPermitidos = horariosPermitidos;
	}
}
