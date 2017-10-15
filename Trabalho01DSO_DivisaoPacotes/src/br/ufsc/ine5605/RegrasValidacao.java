package br.ufsc.ine5605;

public enum RegrasValidacao {
	
	/*
	 * As regras de validacao podem ser diferentes, e permitem informar como funcionam
	 */
	
	VALIDA_NOME_CARGO("[a-zA-Z]+([ ][a-zA-Z]*)*",
						"Digite apenas letras e apenas um espaco entre as palavras; sem acentuacao, numeros ou caracteres especiais"),
	VALIDA_NOME_FUNCIONARIO("[a-zA-Z]+([ ][a-zA-Z]*)*",
			"Digite apenas letras e apenas um espaco entre as palavras; sem acentuacao, numeros ou caracteres especiais");
	
	private String regraValidacao;
	private String explicaoRegra;
	
	RegrasValidacao(String regraValidacao, String explicaoRegra){
		this.regraValidacao = regraValidacao;
		this.explicaoRegra = explicaoRegra;
	}
	
	public String getRegraValidacao(){
		return this.regraValidacao;
	}
	
	public String getExplicacaoRegra(){
		return this.explicaoRegra;
	}
	
}
