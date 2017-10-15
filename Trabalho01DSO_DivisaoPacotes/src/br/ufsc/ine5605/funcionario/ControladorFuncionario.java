package br.ufsc.ine5605.funcionario;

import java.util.ArrayList;

import br.ufsc.ine5605.cargo.ControladorCargo;

public class ControladorFuncionario {
	private ArrayList<Funcionario> listaFuncionarios;
	private static ControladorFuncionario instancia;
	private TelaFuncionario tela;

	/****** OPCOES DO MENU PRINCIPAL ******/
	private final String[] opcoesMenuPrincipal = { "Voltar", "Listar Funcionarios Cadastrados", "Cadastrar Funcionario",
			"Excluir Funcionario", "Alterar Cadastro Funcionario" };

	/****** OPCOES DO MENU PARA EDICAO DOS FUNCIONARIOS ******/
	private final String[] opcoesMenuEditarFuncionario = { "Voltar", "Alterar Matricula", "Alterar Nome",
			"Alterar Cargo", "Alterar Telefone", "Alterar dataNascimento", "Alterar Salario" };

	private ControladorFuncionario() {
		this.listaFuncionarios = new ArrayList<>();
		tela = new TelaFuncionario();
	}

	public static ControladorFuncionario getInstance() {
		if (instancia == null) {
			instancia = new ControladorFuncionario();
		}
		return instancia;
	}

	public void incluirFuncionario() {

		DadosCadastroFuncionario funcionario = tela.IncluirFuncionario();

		if (findFuncionarioByMatricula(funcionario.matricula) == null
				&& findFuncionarioByNome(funcionario.nome) == null) {
			listaFuncionarios.add(new Funcionario(funcionario.matricula, funcionario.nome, funcionario.cargo,
					funcionario.telefone, funcionario.dataNascimento, funcionario.salario));

			tela.mostraMensagem("Funcionario cadastrado com sucesso.");
		} else {
			tela.mostraMensagem(
					"Já existe cadastro com esta matrícula e ou com este nome no sistema, cheque a lista e tente novamente");
		}

	}

	/**
	 * Chama a tela que mostra o menu principal (relacionado a Funcionarios)
	 */
	public void mostraMenu() {
		int opcao = -1;
		do {
			opcao = tela.mostraMenu(opcoesMenuPrincipal);
			switch (opcao) {
			case 0:
				break;
			case 1:
				printarFuncionariosCompleto();
				break;
			case 2:
				incluirFuncionario();
				break;
			case 3:
				excluirFuncionario();
				break;
			case 4:
				alterarCadastroFuncionario();
				break;
			}
		} while (opcao != 0);
	}

	public void mostraMenuEd() {
		int opcao = -1;
		boolean eae = false;
		do {
			eae = false;

			opcao = tela.mostraMenu(opcoesMenuEditarFuncionario);
			switch (opcao) {
			case 0:
				break;

			case 1:
				printarFuncionariosCompleto();
				boolean eae1 = false;
				tela.mostraMensagem(
						"Digite a matricula(vide lista) do funcionario cuja matricula sera alterada");
				int matEnt = 0;
				int matNov = 0;
				do {
					matEnt = tela.leInteiroPositivo();
					if (findFuncionarioByMatricula(matEnt) == null) {
						tela.mostraMensagem("Esta matrícula não está cadastrada, tente novamente");
						printarFuncionariosCompleto();
					}
					tela.mostraMensagem("Funcionario de matricula '" + matEnt + "' selecionado, digite a nova matricula");
					matNov = tela.leInteiroPositivo();
					if (findFuncionarioByMatricula(matNov) != null) {
						tela.mostraMensagem(
								"Este numero de matricula ja esta vinculado a outro cadastro, tente novamente");

					} else {
						findFuncionarioByMatricula(matEnt).setMatricula(matNov);
						eae1 = true;
						tela.mostraMensagem("Matricula alterada com sucesso");

					}

				} while (!eae1);
				break;

			case 2:
				printarFuncionariosCompleto();
				tela.mostraMensagem("Digite a matricula(vide lista) do funcionario");

				alteraNome();
				break;
			case 3:
				printarFuncionariosCompleto();
				Funcionario funcA = null;
				do {
					tela.mostraMensagem("Digite a matricula do funcionario cujo cargo será alterado");
					int maat = tela.leInteiroPositivo();
					if (findFuncionarioByMatricula(maat) != null) {
						funcA = findFuncionarioByMatricula(maat);
						eae = true;
					} else {
						tela.mostraMensagem("Matricula invalida");
					}
				} while (!eae);
				boolean eae2 = false;
				do {

					tela.mostraMensagem("Digite o código do cargo da lista");
					printaListaCargoNomeCodigoHorario();

					int novoCc = tela.leInteiroPositivo();
					if (ControladorCargo.getInstance().findCargoByCodigo(novoCc) == null) {
						tela.mostraMensagem("Codigo de cargo invalido, selecione codigo da lista");
					} else {
						funcA.setCargo(ControladorCargo.getInstance().findCargoByCodigo(novoCc));
						eae2 = true;
					}
				} while (!eae2);
				break;
			case 4:
				printarFuncionariosCompleto();
				tela.mostraMensagem("Digite a matricula do funcionario cujo telefone será alterado");
				int mat = tela.leInteiroPositivo();
				eae = false;
				do {
					
					if (findFuncionarioByMatricula(mat) != null) {

						tela.mostraMensagem("Digite o novo telefone do funcionario:");
						String telefone = tela.leitor.nextLine();
						if (telefone.matches("^[0-9]+$")) {
							tela.mostraMensagem("Telefone alterado com sucesso");
							findFuncionarioByMatricula(mat).setTelefone(telefone);
							
							eae = true;
						}
						else {

							System.out.println("DIGITE APENAS NUMEROS");
					}

						}
						else {
							tela.mostraMensagem("Matricula invalida, cheque a lista e digite novamente");
							
							mat = tela.leInteiroPositivo();
							
						}
					}while (!eae);
				break;

			case 5:
				printarFuncionariosCompleto();
				tela.mostraMensagem("Digite a matricula do funcionario cuja Data de Nascimento será alterada");
				 mat = tela.leInteiroPositivo();
				eae = false;
				do {
					
					if (findFuncionarioByMatricula(mat) != null) {

						System.out.println("digite a data de nascimento do funcionario(No formato ddMMaaaa)");
						String dataNascimento = tela.leitor.nextLine();
						if (dataNascimento.matches("^[0-9]{8}$")) {

							findFuncionarioByMatricula(mat).setDataNascimento(dataNascimento);
							tela.mostraMensagem("Data de nascimento alterada para :"
									+ (formataDataPraPrintar(findFuncionarioByMatricula(mat).getDataNascimento())));
							eae = true;
						}
					} else {
						tela.mostraMensagem("Digite uma matricula valida");
						mat = tela.leInteiroPositivo();
					}
				} while (!eae);
				break;
				
			case 6:
				printarFuncionariosCompleto();
				tela.mostraMensagem("Digite a matricula do funcionario cujo salário será alterado");
				mat = tela.leInteiroPositivo();
				eae = false;
				do {
					
					if (findFuncionarioByMatricula(mat) != null) {
						System.out.println("digite o salario do Funcionario");
						int salario = tela.leInteiroPositivo();
						findFuncionarioByMatricula(mat).setSalario(salario);
						tela.mostraMensagem("Salário alterado para: " + salario + " com sucesso.");
						eae = true;
					} else {
						tela.mostraMensagem("Digite uma matricula valida");
						mat = tela.leInteiroPositivo();
					}

				} while (!eae);

			}
		} while (!eae);
	
	}


	private void alteraNome() {

		boolean eae = false;
		do {
			int mat = tela.leInteiroPositivo();

			if (findFuncionarioByMatricula(mat) != null) {
				tela.mostraMensagem("Funcionario de matricula " + mat + " selecionado para alteração de nome.");
				String nomeT = tela.pedeNome();
				findFuncionarioByMatricula(mat).setNome(nomeT);
				printarFuncionariosCompleto();
				eae = true;
			} else {
				tela.mostraMensagem("Matricula Invalida, cheque novamente a lista");
				printarFuncionariosCompleto();
			}

		} while (!eae);

	}

	private void alterarCadastroFuncionario() {
		mostraMenuEd();

	}

	public void excluirFuncionario() {
		tela.mostraMensagem("Procure na lista e em seguida digite a Matricula do funcionario que será excluído");
		printarFuncionariosCompleto();
		int matricula = tela.leInteiroPositivo();
		if (findFuncionarioByMatricula(matricula) != null) {
			Funcionario funcionario = findFuncionarioByMatricula(matricula);
			listaFuncionarios.remove(funcionario);
			tela.mostraMensagem("Funcionaro de Matricula " + matricula + " foi removido do sistema.");
		} else
			tela.mostraMensagem("Não existe Funcionário cadastrado com este número de matrícula");
	}

	public ArrayList<Funcionario> listarFuncionarios() {
		return listaFuncionarios; // retorna para imprimir na tela
	}

	public ArrayList<Funcionario> listarFuncionariosPorCargo(int codigo) {
		ArrayList<Funcionario> listaPorCargo = new ArrayList<>();
		for (Funcionario f : listaFuncionarios) {
			if (f.getCargo().getCodigo() == codigo) {
				listaPorCargo.add(f);
			}
		}
		return listaPorCargo; // retorna para imprimir na tela
	}

	public Funcionario findFuncionarioByMatricula(int matricula) {
		Funcionario funcionario = null;
		for (Funcionario f : listaFuncionarios) {
			if (f.getMatricula() == matricula) {
				funcionario = f;
			}
		}
		return funcionario;
	}

	public Funcionario findFuncionarioByNome(String nome) {
		Funcionario funcionario = null;
		for (Funcionario f : listaFuncionarios) {
			if (f.getNome().equals(nome)) {
				funcionario = f;
			}
		}
		return funcionario;
	}

	/*
	 * Printa lista no formato: Codigo Cargo (a) + Nome Cargo (a) Horarios de acesso
	 * do (a)
	 */

	public void printaListaCargoNomeCodigoHorario() {

		tela.mostraMensagem(
				"===Lista de Cargos possui " + ControladorCargo.getInstance().getListaCargos().size() + " cargos===");

		for (int a = 0; a < ControladorCargo.getInstance().getListaCargos().size(); a++) {
			tela.mostraMensagem(
					"  ---/Código :" + (ControladorCargo.getInstance().getListaCargos().get(a).getCodigo()));
			tela.mostraMensagem("  ---/Cargo: " + (ControladorCargo.getInstance().getListaCargos().get(a).getNome()));
			if (ControladorCargo.getInstance().getListaCargos().get(a).getEhGerencial()) {
				tela.mostraMensagem("----CARGO COM ACESSO IRRESTRITO AO FINANCEIRO----");
			} else {
				tela.mostraMensagem("Este cargo pode acessar o financeiro durante o intervalo :"
						+ (ControladorCargo.getInstance().getListaCargos().get(a).getHorariosPermitidos().toString()));

			}
			tela.mostraMensagem("==============================================");
		}

	}

	public void adicionaCargo() {
		// recebe o 0 da tela dps de imprimir a lista
		ControladorCargo.getInstance().incluirCargo();
	}

	public String formataDataPraPrintar(String a) {
		String dataPraPrintar;

		dataPraPrintar = a.substring(0, 2) + "/";
		dataPraPrintar = dataPraPrintar + a.substring(2, 4) + "/";
		dataPraPrintar = dataPraPrintar + a.substring(4);
		return dataPraPrintar;
	}

	public void printarFuncionariosCompleto() {
		if (listaFuncionarios.size() == 0) {
			tela.mostraMensagem("===Lista de Funcionários vazia===");

		}
		for (int a = 0; a < listaFuncionarios.size(); a++) {
			Funcionario fTemp = (listarFuncionarios().get(a));
			tela.mostraMensagem("---/NOME " + (fTemp.getNome()) + "  ---/MATRICULA :" + (fTemp.getMatricula())
					+ "  ---/CARGO : " + (fTemp.getCargo().getNome()) + "  ---/TELEFONE : " + (fTemp.getTelefone())
					+ "  ---/DATA NASCIMENTO : " + (formataDataPraPrintar(fTemp.getDataNascimento()))
					+ "  ---/SALARIO : " + (fTemp.getSalario()));
			tela.mostraMensagem("==============================================");
		}
	}

}
