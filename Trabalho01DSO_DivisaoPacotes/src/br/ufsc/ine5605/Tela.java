package br.ufsc.ine5605;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Tela {
	public Scanner leitor = new Scanner(System.in);
	
	/**
	 * Solicita a digitacao de um numero inteiro, entre 0 e o limite, ate que seja digitado corretamente
	 * @param limite
	 * 	valor maximo aceitavel
	 * @return
	 * 	Valor inteiro positivo lido do teclado
	 */
	public int leInteiroPositivoAte(int limite) {
		int codigo = -1;
		boolean respostaOK = false;
		do {
			try {
				codigo = leitor.nextInt();
				if (codigo >= 0 && codigo <= limite) {
					respostaOK = true;
				} else {
					System.out.println("\nDigite um numero inteiro ate " + limite +"\n");
					respostaOK = false;
				}
			} catch (InputMismatchException e) {
				System.out.println("\nDigite um numero inteiro ate " + limite + "\n");
			}
			leitor.nextLine();
		} while (!respostaOK);
		
		return codigo;
	}
	
	/**
	 * Recebe um array de String, sendo que cada String do array sera mostrado na tela como opcao de um menu.
	 * Valida a escolha do usuario, aceitando apenas numeros correspondentes aos indices do array. Caso
	 * o usuario digite algo diferente de um numero inteiro, trata a excecao e solicita nova digitacao.
	 * 
	 * @param opcoes String[]
	 * @return opcao int que representa o item escolhido do menu
	 */
	public int mostraMenu(String[] opcoes) {
		boolean respostaOK = false;
		int opcao = 0;

		do {
			respostaOK = false;
			try {
				for (int i = 0; i < opcoes.length; i++) {
					System.out.println("" + i + " - " + opcoes[i]);
				}

				opcao = leitor.nextInt();
				if (opcao < 0 || opcao > opcoes.length - 1) {
					respostaOK = false;
				} else {
					respostaOK = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("\nDigite apenas numeros\n");
				respostaOK = false;
			}
			leitor.nextLine(); // limpa o buffer

		} while (!respostaOK);
		return opcao;
	}
	
	/**
	 * Solicita a digitacao de um numero inteiro positivo, ate que seja digitado corretamente
	 * @return
	 * 	Valor inteiro positivo lido do teclado
	 */
	public int leInteiroPositivo() {
		int codigo = -1;
		boolean respostaOK = false;
		do {
			try {
				codigo = leitor.nextInt();
				if (codigo > 0) {
					respostaOK = true;
				} else {
					respostaOK = false;
				}
			} catch (InputMismatchException e) {
				System.out.println("\nDigite um numero inteiro positivo\n");
			}
			leitor.nextLine();
		} while (!respostaOK);
		return codigo;
	}
	
	/**
	 * Verifica se o usuario digitou "s", "n", "S" ou "N"; gera excecao caso
	 * tenha sido digitado algo nao permitido
	 * 
	 * @param resposta
	 *            Resposta digitada pelo usuario
	 * @return "true", caso digitado "s" ou "S"; "false", caso digitado "n" ou
	 *         "N"
	 * @throws Exception
	 *             no caso de algo nao permitido ter sido digitado
	 */
	public boolean verificaSN(String resposta) throws Exception {
		boolean opcao = false;
		if (resposta.equalsIgnoreCase("s")) {
			opcao = true;
		} else if (resposta.equalsIgnoreCase("n")) {
			opcao = false;
		} else {
			throw new Exception("\nDigite apenas uma letra, 's' ou 'n' \n");
		}
		return opcao;
	}
	
	/**
	 * Valida um nome (string) de acordo com a regra definida
	 * @param nome nome que sera validado
	 * @param regra regra segundo a qual o nome sera validado
	 * @return "true" ou "false"
	 */
	public boolean validaNome(String nome, String regra) {
		return nome.matches(regra);
	}
	
}
