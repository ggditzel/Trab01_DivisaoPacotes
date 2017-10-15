package br.ufsc.ine5605.cargo;

public class DadosAlteraStatus {
	public int codigo;
	public boolean statusGerencial;
	public boolean statusAcesso;
	
	public DadosAlteraStatus(int codigo, boolean statusGerencial, boolean statusAcesso) {
		this.codigo = codigo;
		this.statusGerencial = statusGerencial;
		this.statusAcesso = statusAcesso;
	}
}
