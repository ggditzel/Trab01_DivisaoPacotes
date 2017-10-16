package br.ufsc.ine5605.acesso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.ufsc.ine5605.Tela;

public class TelaTentativaAcesso extends Tela {
	/**
	* Imprime o motivo do acesso ter sido negado
	*/
	public void mostraNegacao(MotivoNegacaoAcesso motivo) {
		System.out.println(motivo.getNome());
	}
	/**
	* Imprime no console as opcoes do menu Tentativas de acesso e recebe a resposta do usuario
	* @return
	*	int referente a opcao escolhida pelo usuario
	*/
	public int mostraOpcoes() {
		System.out.println("1- Usar horario e data local");
		System.out.println("2- Definir horario e data (para testes)");
		System.out.println("0- Voltar");
		return super.leInteiroPositivoAte(2);
	}
	/**
	 * Solicita a digitacao de uma hora, no formato (hh:mm) ate que seja digitada corretamente
	 * @return
	 * 	String correspondente a hora digitada
	 */
	public String perguntaHora() {
		String hora = "";
		boolean respostaOK = false;
		System.out.println("Digite o horario: ");
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
	 * Solicita a digitacao de uma matricula, ate que seja digitada corretamente
	 * @return
	 * 	String correspondente a matricula digitada
	 */
	public int perguntaMatricula() {
		System.out.println("Digite sua matricula:"); 
		return leInteiroPositivo();
	}
	/**
	 * Solicita a digitacao de uma data, no formato (dd/mm/aaaa) ate que seja digitada corretamente
	 * @return
	 * 	String correspondente a data digitada
	 */
	public String perguntaData() {
		String data = "";
		boolean respostaOK = false;
		System.out.println("Digite a data do acesso (dd/mm/aaaa): ");
		do{
			data = leitor.nextLine();
			if(dataEhValida(data) ) {
				respostaOK = true;
				if(data.charAt(1) == '/') {
					data = "0"+ data;
				}
				if(data.charAt(4) == '/') {
					data = data.substring(0, 3) + "0" + data.substring(3);
				}
				if(data.length() != 10) {
					respostaOK = false;
					System.out.println("Formato de data invalido.\nDigite novamente");
				}
			} else {
				System.out.println("Formato de data invalido.\nDigite novamente");
				respostaOK = false; // redundante
			}
		}while(!respostaOK);
		
		return data;
	}
	/**
	* Verifica se a data esta no formato (dd/mm/aaaa)
	* @param text
	*	String que sera verificada
	* @return
	*	true, se a String estiver no formato correto, ou false, se nao estiver
	*/
	private boolean dataEhValida(String text) {
		   
	    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	    df.setLenient(false);
	    try {
	        df.parse(text);
	        return true;
	    } catch (ParseException ex) {
	        return false;
	    }
	}
	/**
	* Imprime mensagem de confirmacao do acesso
	*/
	public void confirmaAcesso() {
		System.out.println("Acesso permitido.");
		
	}
	/**
	* Imprime no console as opcoes do menu de relatorio das tentativas de acesso e recebe a resposta do usuario
	* @return
	*	int referente a opcao escolhida pelo usuario
	*/
	public int mostraMenuTentativas() {
		System.out.println("Escolha uma opcao: ");
		System.out.println(
				"1- Listar todas as tentativas de acesso\n" +
				"2- Listar todas as tentativas de acesso negadas\n" +
				"3- Listar todos os acessos\n" +
				"4- Listar todos as tentativas de acesso a partir de uma matricula\n" +
				"5- Listar tentativas de acesso negadas a partir de uma matricula\n" +
				"6- Listar os acessos a partir de uma matricula\n" +
				"7- Listar tentativas de acesso negadas por um motivo\n" +
				"0- Voltar"
				);
		

		return leInteiroPositivoAte(7);
	}
	/**
	* Imprime no console os horarios de um ArrayList de tentativas de acesso, ou informa que nao ha tentativas
	* @param lista
	*	Lista que sera impressa
	*/
	public void listaTentativas(ArrayList<TentativaAcesso> tentativas) {
		if(tentativas.isEmpty()){
			System.out.println("Nao ha tentativas de acesso");
		} else {
			for(TentativaAcesso t: tentativas){
				System.out.println(t.toString());
			}
		}
		System.out.println("Pressione enter para voltar");
		leitor.nextLine();
		
	}
	/**
	 * Imprime no console uma lista com os motivos de negacao de acesso e recebe do usuario um int referente ao motivo
	 * @return
	 * 	int correspondente ao motivo digitado
	 */
	public int perguntaMotivo() {
		int i = 0;
		for(MotivoNegacaoAcesso m: MotivoNegacaoAcesso.values()) {
			i++;
			System.out.println(""+ i + "- " + m.getNome());
		}
		return leInteiroPositivoAte(i);
	}
	
}
