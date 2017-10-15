package br.ufsc.ine5605;

public class TelaPrincipal extends Tela {
	
	public void mostraMensagem(String mensagem){
		System.out.println(mensagem);
	}

	@Override
	public int mostraMenu(String[] opcoes) {
		return super.mostraMenu(opcoes);
	}
}
