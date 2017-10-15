package br.ufsc.ine5605.horario;

import java.util.ArrayList;

import br.ufsc.ine5605.Tela;




public class TelaHorario extends Tela{

    
    public TelaHorario(){
        
    }

    /**
	 * Solicita a digitacao de uma hora, no formato (hh:mm) ate que seja digitada corretamente
	 * @return
	 * 	String correspondente a hora digitada
	 */
    public String perguntaInicio() {
        System.out.println("Insira o início do horario (hh:mm): ");
        return leHora();
    }
    public String perguntaFim() {
        System.out.println("Insira o fim do horario (hh:mm): ");
        return leHora();
    }

	public int mostraOpcoesAdicionar() {
			System.out.println("1- Adicionar horario de acesso \n"
							 + "0- Voltar");
		return super.leInteiroPositivoAte(1);
	}
	
	public void listaHorarios(ArrayList<Horario> lista) {
		System.out.println("Horarios cadastrados:");
		for(int i = 0; i < lista.size(); i++){
			System.out.println("" + (int) (i+1) + "- " + lista.get(i).toString());
		}
		System.out.println("");
	}
	
	public void mostraLista(ArrayList<Horario> lista){
		for(int i = 0; i < lista.size(); i++){
			System.out.println("" + (int) (i+1) + "- " + lista.get(i).toString());
		}
	}
	
	public int mostraListaRemover(ArrayList<Horario> lista) {
		System.out.println("Selecione um horario:");
		mostraLista(lista);
		System.out.println("0- Voltar");
		return super.leInteiroPositivoAte(lista.size() + 1);
	}
	
	public int mostraListaAdicionar(ArrayList<Horario> lista) {
		System.out.println("Selecione um horario:");
		mostraLista(lista);
		System.out.println("" + (int) (lista.size() + 1) + "- Adicionar novo horario \n" +
				  "0- Voltar");
		return super.leInteiroPositivoAte(lista.size()+1);
	}
	
	
	
	/**
	 * Solicita a digitacao de uma hora, no formato (hh:mm) ate que seja digitada corretamente
	 * @return
	 * 	String correspondente a hora digitada
	 */
	private String leHora() {
		String hora = "";
		boolean respostaOK = false;
		do{
			hora = leitor.nextLine();
			if(hora.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]") ) {
				respostaOK = true;
				if(hora.charAt(1) == ':') {
					hora = "0"+ hora;
				}
			} else {
				System.out.println("Formato de hora invalido.\nDigite novamente");
				respostaOK = false; // redundante
			}
		}while(!respostaOK);
		
		return hora;
	}

	/**
	 * Imprime um aviso para o usuario
	 * @param aviso
	 * 	mensagem que sera impressa
	 */
	public void mostraAviso(String aviso) {
		System.out.println(aviso);
	}

	public int mostraOpcoesEditar() {
		System.out.println("1- Adicionar horario de acesso \n"
				 + "2- Remover horario de acesso \n"
				 + "0- Voltar");
		return super.leInteiroPositivoAte(2);
	}

	public int mostraListaAdicionarPrimeiroHorario(
			ArrayList<Horario> lista) {
		System.out.println("Selecione um horario:");
		mostraLista(lista);
		System.out.println("0- Adicionar novo horario \n");
		return super.leInteiroPositivoAte(lista.size());
	}

	
	

	
}
