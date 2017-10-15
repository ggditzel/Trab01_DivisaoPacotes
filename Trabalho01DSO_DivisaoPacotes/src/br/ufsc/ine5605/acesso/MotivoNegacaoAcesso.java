package br.ufsc.ine5605.acesso;

public enum MotivoNegacaoAcesso {
	ACESSO_BLOQUEADO("Acesso bloqueado"),
	HORARIO_NAO_PERMITIDO("Horario nao permitido"),
	MATRICULA_INEXISTENTE("Matricula nao existe"),
	NAO_POSSUI_ACESSO("Nao possui acesso");
	
	private String nome;
	
	private MotivoNegacaoAcesso(String nome) {
		this.nome = nome;
	}
	
	public String getNome(){
		return this.nome;
	}
	
}
