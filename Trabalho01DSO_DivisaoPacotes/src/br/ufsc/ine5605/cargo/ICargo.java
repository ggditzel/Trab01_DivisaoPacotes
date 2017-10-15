package br.ufsc.ine5605.cargo;

import java.util.ArrayList;

import br.ufsc.ine5605.horario.Horario;

public interface ICargo {
		
	public int getCodigo();
	public String getNome();
	public boolean getEhGerencial();
	public boolean getPossuiAcesso();
	public ArrayList<Horario> getHorariosPermitidos();

	public void setCodigo(int codigo);
	public void setNome(String nome);
	public void setEhGerencial(boolean ehGerencial);
	public void setPossuiAcesso(boolean possuiAcesso);
}

