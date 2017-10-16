package br.ufsc.ine5605.horario;

import java.util.ArrayList;

import br.ufsc.ine5605.Tela;




public class TelaHorario extends Tela{

    
    public TelaHorario(){
        
    }

    /**
	 * Solicita a digitacao de uma hora, no formato (hh:mm) ate que seja digitada corretamente, que sera adicionada ao inicio do horario
	 * @return
	 * 	String correspondente a hora digitada
	 */
    public String perguntaInicio() {
        System.out.println("Insira o início do horario (hh:mm): ");
        return leHora();
    }
     /**
	 * Solicita a digitacao de uma hora, no formato (hh:mm) ate que seja digitada corretamente, que sera adicionada ao fim do horario
	 * @return
	 * 	String correspondente a hora digitada
	 */
    public String perguntaFim() {
        System.out.println("Insira o fim do horario (hh:mm): ");
        return leHora();
    }
	/**
	* Imprime no console as opcoes do menu adicionar horario e recebe a resposta do usuario
	* @return
	*	int referente a opcao escolhida pelo usuario
	*/
	public int mostraOpcoesAdicionar() {
			System.out.println("1- Adicionar horario de acesso \n"
							 + "0- Voltar");
		return super.leInteiroPositivoAte(1);
	}
	/**
	* Imprime no console os horarios de um ArrayList de horarios, afirmando ao usuario que os horarios estao cadastrados
	* @param lista
	*	Lista que sera impressa
	*/
	public void listaHorarios(ArrayList<Horario> lista) {
		System.out.println("Horarios cadastrados:");
		mostraLista(lista);
		System.out.println("");
	}
	/**
	* Imprime no console os horarios de um ArrayList de horarios
	* @param lista
	*	Lista que sera impressa
	*/
	public void mostraLista(ArrayList<Horario> lista){
		for(int i = 0; i < lista.size(); i++){
			System.out.println("" + (int) (i+1) + "- " + lista.get(i).toString());
		}
	}
	/**
	* Imprime no console a tela para remover horarios de uma lista e recebe a resposta do usuario
	* @param lista
	*	Lista que sera impressa
	* @return
	*	int referente a opcao do usuario
	*/	
	public int mostraListaRemover(ArrayList<Horario> lista) {
		System.out.println("Selecione um horario:");
		mostraLista(lista);
		System.out.println("0- Voltar");
		return super.leInteiroPositivoAte(lista.size() + 1);
	}
	/**
	* Imprime no console a tela para adicionar horarios de uma lista e recebe a resposta do usuario
	* @param lista
	*	Lista que sera impressa
	* @return
	*	int referente a opcao do usuario
	*/
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
	/**
	* Imprime no console as opcoes do menu editar horario e recebe a resposta do usuario
	* @return
	*	int referente a opcao escolhida pelo usuario
	*/
	public int mostraOpcoesEditar() {
		System.out.println("1- Adicionar horario de acesso \n"
				 + "2- Remover horario de acesso \n"
				 + "0- Voltar");
		return super.leInteiroPositivoAte(2);
	}
	/**
	* Imprime no console a tela para adicionar o primeiro horario de uma lista e recebe a resposta do usuario
	* @param lista
	*	Lista que sera impressa
	* @return
	*	int referente a opcao do usuario
	*/
	public int mostraListaAdicionarPrimeiroHorario(
			ArrayList<Horario> lista) {
		System.out.println("Selecione um horario:");
		mostraLista(lista);
		System.out.println("0- Adicionar novo horario \n");
		return super.leInteiroPositivoAte(lista.size());
	}

	
	

	
}
