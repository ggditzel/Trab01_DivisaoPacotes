package br.ufsc.ine5605.cargo;

import java.util.ArrayList;

import br.ufsc.ine5605.RegrasValidacao;
import br.ufsc.ine5605.Tela;
import br.ufsc.ine5605.horario.ControladorHorario;
import br.ufsc.ine5605.horario.Horario;

public class TelaCargo extends Tela {

	/**
	 * Solicita via Scanner os dados necessarios para o cadastro de um novo
	 * usuario, retornando as informacoes em uma classe temporaria a ser
	 * utilizada pelo ControladorCargo
	 * 
	 * @return DadosCadastroCargo(int codigo, String nome, boolean ehGerencial,
	 *         boolean possuiAcesso, ArrayList horariosPermitidos)
	 */
	public DadosCadastroCargo incluirCargo() {
		int codigo = -1;
		String nome;
		boolean cargoJaExiste = false;
		boolean ehGerencial = false;
		boolean possuiAcesso = true;
		ArrayList<Horario> horariosPermitidos = new ArrayList<Horario>();
		boolean respostaOK = false;
		System.out.println("==== Digite os dados solicitados ====");
		
		// Valida entrada de codigo para o cargo
		do {
			System.out.println("Digite um codigo para o cargo (numero inteiro positivo): ");
			codigo = leInteiroPositivo();
			if (ControladorCargo.getInstance().findCargoByCodigo(codigo) != null) {
				System.out.println("Já existe cargo com este codigo, favor digitar um codigo diferente");
				cargoJaExiste = true;
			} else {
				cargoJaExiste = false;
			}
		} while (cargoJaExiste);

		// pergunta o nome para o cargo, segundo regra definida no enum RegraValidacaoNomes
		nome = perguntaNomeCargo();
		
		// Valida digitacao de "s", "S", "n" ou "N" apenas
		do {
			respostaOK = false;
			try {
				System.out.println("Este cargo eh gerencial (s/n)? ");
				ehGerencial = super.verificaSN(leitor.nextLine());
				respostaOK = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				respostaOK = false;
			}

		} while (!respostaOK);

		// Valida digitacao de "s", "S", "n" ou "N" apenas
		if (!ehGerencial) {
			do {
				respostaOK = false;
				try {
					System.out.println("Este cargo possui acesso ao financeiro (s/n)? ");
					possuiAcesso = super.verificaSN(leitor.nextLine());
					respostaOK = true;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					respostaOK = false;
				}

			} while (!respostaOK);

			if (possuiAcesso) {
				System.out.println("Este cargo necessita do cadastro de horario de acesso");
				horariosPermitidos = ControladorHorario.getInstance().iniciaCadastro();
			}

		}
		return new DadosCadastroCargo(codigo, nome, ehGerencial, possuiAcesso, horariosPermitidos);

	}

	/**
	 * Solicita ao usuario o codigo do cargo a ser excluido
	 * 
	 * @return codigo do cargo a ser excluido
	 */
	public int excluirCargo() {
		System.out.println("Digite o codigo do cargo a ser excluido (numero inteiro positivo)");
		return leInteiroPositivo();
	}
	
	public int alterarHorarios() {
		System.out.println("Digite o codigo do cargo que tera alteracao de horarios (numero inteiro positivo)");
		return leInteiroPositivo();
	}


	/**
	 * Recebe uma lista de cargos para mostrar na tela, mostrando codigo, nome,
	 * status gerencial, status de acesso e horarios, se o cargo permitir cadastro.
	 * 
	 * @param lista Lista de cargos a ser mostrada
	 */
	public void listarCargos(ArrayList<Cargo> lista) {
		if (!lista.isEmpty()) {
			System.out.println("\n=== Cargos Cadastrados ===");
			for (Cargo c : lista) {
				System.out.println("\nCodigo: " + c.getCodigo() + "; " + "Nome: " + c.getNome() + "; " + "Cargo Gerencial? "
						+ converteBooleanSimNao(c.getEhGerencial()) + "; "); // + "Necessita cadastro para acesso? "
				//+ converteBooleanSimNao(c.getPossuiAcesso()) + ".");
				if (c.getEhGerencial()) {
					System.out.println("Gerentes podem acessar a qualquer hora.");
				} else if (!c.getPossuiAcesso()) {
					System.out.println("Este cargo nao possui permissao de acesso.");
				} else {
					System.out.println("Necessita cadastro para acesso? " + converteBooleanSimNao(c.getPossuiAcesso()) + ".");
					System.out.println("Horarios permitidos para acesso: ");
					if (!c.getHorariosPermitidos().isEmpty()){
						ControladorHorario.getInstance().listaHorarios(c);
					} else {
						System.out.println("Esta cargo ainda nao possui horarios de acesso cadastrados");
					}
				}
			}
		} else {
			System.out.println("Ainda nao ha cargos cadastrados no sistema");
		}
		System.out.println("");
	}

	private String converteBooleanSimNao(boolean b) {
		if (b) {
			return "Sim";
		} else {
			return "Nao";
		}
	}

	/**
	 * Solicita o codigo do Cargo a ter o nome alterado, e na sequencia solicita
	 * o novo nome.
	 * 
	 * @return Retorna uma classe temporaria com o codigo e o novo nome, para
	 *         ser utilizada pelo controlador
	 */
	public DadosAlteraDescricao alterarDescricao() {
		int codigo = -1;
		String novoNome = "";

		System.out.println("Digite o codigo do cargo a ser alterado (numero inteiro positivo): ");
		codigo = leInteiroPositivo();

		novoNome = perguntaNomeCargo();
		return new DadosAlteraDescricao(codigo, novoNome);
	}

	/**
	 * Altera o status gerencial ou o status de acesso, dependendo do parametro
	 * passado
	 * 
	 * @param status
	 *            String "gerencial" ou "acesso", de acordo com o status a ser
	 *            editado
	 * @return
	 */
	public DadosAlteraStatus alterarStatus() {
		int codigo = -1;
		boolean novoStatusGerencial = false;
		boolean novoStatusAcesso = false;
		boolean respostaOK = false;

		System.out.println("Digite o codigo do cargo a ser alterado (numero inteiro positivo): ");
		codigo = leInteiroPositivo();
		
		do {
			respostaOK = false;
			try {
				System.out.println("Este cargo sera gerencial (s/n)?");
				novoStatusGerencial = super.verificaSN(leitor.nextLine());
				respostaOK = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				respostaOK = false;
			}
		} while (!respostaOK);

		do {
			respostaOK = false;
			try {
				if (!novoStatusGerencial){
					System.out.println("Este cargo tera permissao de acesso (s/n)?");
					novoStatusAcesso = super.verificaSN(leitor.nextLine());
				}
				respostaOK = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				respostaOK = false;
			}

		} while (!respostaOK);

		return new DadosAlteraStatus(codigo, novoStatusGerencial, novoStatusAcesso);
	}

	public void mostraMensagem(String mensagem) {
		System.out.println(mensagem);
	}
	
	
	@Override
	public int mostraMenu(String[] opcoes) {
		return super.mostraMenu(opcoes);
	}
	
	private String perguntaNomeCargo(){
		String nome = "";
		boolean respostaOK = false;
		do {
			respostaOK = false;
			System.out.println("Digite um nome para o cargo: ");
			nome = leitor.nextLine();
			if (validaNome(nome, RegrasValidacao.VALIDA_NOME_CARGO.getRegraValidacao())){
				respostaOK = true;
			} else {
				System.out.println(RegrasValidacao.VALIDA_NOME_CARGO.getExplicacaoRegra());
			}
		} while (!respostaOK);
		return nome;
	}
	
}
