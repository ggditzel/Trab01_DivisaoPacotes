package br.ufsc.ine5605.acesso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.ufsc.ine5605.Tela;

public class TelaTentativaAcesso extends Tela {

	public void mostraNegacao(MotivoNegacaoAcesso motivo) {
		System.out.println(motivo.getNome());
	}

	public int mostraOpcoes() {
		System.out.println("1- Usar horario e data local");
		System.out.println("2- Definir horario e data (para testes)");
		System.out.println("0- Voltar");
		return super.leInteiroPositivoAte(2);
	}

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

	public int perguntaMatricula() {
		System.out.println("Digite sua matricula:"); 
		return leInteiroPositivo();
	}

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

	public void confirmaAcesso() {
		System.out.println("Acesso permitido.");
		
	}

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

	public int perguntaMotivo() {
		int i = 0;
		for(MotivoNegacaoAcesso m: MotivoNegacaoAcesso.values()) {
			i++;
			System.out.println(""+ i + "- " + m.getNome());
		}
		return leInteiroPositivoAte(i);
	}
	
}
