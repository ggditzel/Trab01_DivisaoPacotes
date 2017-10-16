package br.ufsc.ine5605.funcionario;

import java.util.ArrayList;

import br.ufsc.ine5605.cargo.Cargo;
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

	/**
	 * retorna a instancia de ControladorFuncionario, caso não haja uma o método
	 * cria uma nova e a retorna
	 * 
	 * @return ControladorFuncionario atual ou New ControladorFuncionario
	 */
	public static ControladorFuncionario getInstance() {
		if (instancia == null) {
			instancia = new ControladorFuncionario();
		}
		return instancia;
	}

	/**
	 * Chama a tela de criação de funcionario, com regras de validação, caso não
	 * haja Cargos criados o método irá chamar a tela de inclusão de cargos recebe
	 * um objeto DadosCadastroFuncionario(int matricula,String nome,Cargo
	 * cargo,String telefone,String dataNascimento,int salario) do metodo interno e
	 * insere um novo funcionario com os parametros de DadosCadastroFuncionario na
	 * listaFuncionarios.
	 */
	public void incluirFuncionario() {
		if (ControladorCargo.getInstance().getListaCargos().size() == 0) {
			tela.mostraMensagem("Ainda ão ha um cargo cadastrado, iniciando criação de novo cargo ");
			ControladorCargo.getInstance().incluirCargo();

		}
		ArrayList<Cargo> listaC = ControladorCargo.getInstance().getListaCargos();

		DadosCadastroFuncionario funcionario = tela.IncluirFuncionario(listaCargosFormatada(listaC));

		listaFuncionarios.add(new Funcionario(funcionario.matricula, funcionario.nome, funcionario.cargo,
				funcionario.telefone, funcionario.dataNascimento, funcionario.salario));
	}

	/**
	 * Chama a tela que mostra o menu principal (relacionado a Funcionarios) opcoes
	 * : "Voltar", "Listar Funcionarios Cadastrados", "Cadastrar Funcionario",
	 * "Excluir Funcionario", "Alterar Cadastro Funcionario"
	 */
	public void mostraMenu() {
		int opcao = -1;
		do {
			opcao = tela.mostraMenu(opcoesMenuPrincipal);
			switch (opcao) {
			case 0:
				break;
			case 1:
				tela.mostraMensagem(retornalistaPraPrintar());
				break;
			case 2:
				if (ControladorCargo.getInstance().getListaCargos() == null) {
					tela.mostraMensagem("Ainda ão ha um cargo cadastrado, iniciando criação de novo cargo");
					ControladorCargo.getInstance().incluirCargo();
				}
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

	/**
	 * Chama a tela que mostra o menu de edicao para funcionarios opcoes: "Voltar",
	 * "Alterar Matricula", "Alterar Nome", "Alterar Cargo", "Alterar Telefone",
	 * "Alterar dataNascimento", "Alterar Salario"
	 */
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
				tela.mostraMensagem(retornalistaPraPrintar());
				boolean eae1 = false;
				tela.mostraMensagem("Digite a matricula(vide lista) do funcionario cuja matricula sera alterada");
				int matEnt = 0;
				int matNov = 0;
				do {
					matEnt = tela.leInteiroPositivo();
					if (findFuncionarioByMatricula(matEnt) == null) {
						tela.mensagemNexisteMat(matEnt);
						tela.mostraMensagem(retornalistaPraPrintar());
					}
					tela.mostraMensagem(
							"Funcionario de matricula '" + matEnt + "' selecionado, digite a nova matricula");
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
				tela.mostraMensagem(retornalistaPraPrintar());
				tela.digiteSuaMAt();

				alteraNome();
				break;
			case 3:
				tela.mostraMensagem(retornalistaPraPrintar());

				do {
					tela.digiteSuaMAt();
					int maat = tela.leInteiroPositivo();
					if (findFuncionarioByMatricula(maat) != null) {
						tela.mostraMensagem(
								listaCargosFormatada(ControladorCargo.getInstance().getListaCargos()));
						tela.mostraMensagem("digite o codigo do novo cargo do funcionario");
						int novoC = tela.leInteiroPositivo();
						if (ControladorCargo.getInstance().findCargoByCodigo(novoC) != null) {
							findFuncionarioByMatricula(maat)
									.setCargo(ControladorCargo.getInstance().findCargoByCodigo(novoC));
						} else {
							tela.mostraMensagem("Codigo nao encontrado");
						}
						eae = true;
					} else {
						tela.printaMatriculaInvalida();
					}
				} while (!eae);
				break;
			case 4:
				tela.mostraMensagem(retornalistaPraPrintar());
				tela.digiteSuaMAt();
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
						} else {

							System.out.println("DIGITE APENAS NUMEROS");
						}

					} else {
						tela.printaMatriculaInvalida();

						mat = tela.leInteiroPositivo();

					}
				} while (!eae);
				break;

			case 5:
				tela.mostraMensagem(retornalistaPraPrintar());
				tela.digiteSuaMAt();
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
						tela.printaMatriculaInvalida();
						mat = tela.leInteiroPositivo();
					}
				} while (!eae);
				break;

			case 6:
				tela.mostraMensagem(retornalistaPraPrintar());
				tela.digiteSuaMAt();
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
						tela.printaMatriculaInvalida();
						mat = tela.leInteiroPositivo();
					}

				} while (!eae);

			}
		} while (!eae);

	}

	/**
	 * pede via console uma matricula, valida esta matricula e chama um metodo da
	 * tela para setar novo nome, em seguida printa a lista geral para confirmacao
	 */
	private void alteraNome() {

		boolean eae = false;
		do {
			int mat = tela.leInteiroPositivo();

			if (findFuncionarioByMatricula(mat) != null) {
				tela.printaNumMatOk(mat);
				String nomeT = tela.pedeNome();
				findFuncionarioByMatricula(mat).setNome(nomeT);
				tela.mostraMensagem(retornalistaPraPrintar());
				eae = true;
			} else {
				tela.printaMatriculaInvalida();
				tela.mostraMensagem(retornalistaPraPrintar());
			}

		} while (!eae);

	}

	/**
	 * chama o menu de edicao de funcionarios cadastrados
	 */
	private void alterarCadastroFuncionario() {
		mostraMenuEd();

	}

	/**
	 * remove um funcionario do ArrayList ao digitar matricula de um funcionario
	 */
	public void excluirFuncionario() {
		int a = 1;
		do {
			tela.digiteSuaMAt();

			tela.mostraMensagem(retornalistaPraPrintar());
			int matricula = tela.leInteiroPositivo();
			if (findFuncionarioByMatricula(matricula) != null) {
				Funcionario funcionario = findFuncionarioByMatricula(matricula);
				listaFuncionarios.remove(funcionario);
				tela.mostraMensagem("Funcionaro de Matricula " + matricula + " foi removido do sistema.");
				a--;
			} else
				tela.printaMatriculaInvalida();
		} while (a == 1);
	}

	/**
	 * retorna ArrayList de funcionarios
	 * @return	ArrayList<Funcionario>
	 */
	public ArrayList<Funcionario> listarFuncionarios() {
		return listaFuncionarios;
	}
	/**
	 *  retorna ArrayList para imprimir na tela
	 * @param codigo
	 * @return
	 */
	public ArrayList<Funcionario> listarFuncionariosPorCargo(int codigo) {
		ArrayList<Funcionario> listaPorCargo = new ArrayList<>();
		for (Funcionario f : listaFuncionarios) {
			if (f.getCargo().getCodigo() == codigo) {
				listaPorCargo.add(f);
			}
		}
		return listaPorCargo; //
	}
	/**
	 * recebe uma matricula e retorna o funcionario com esta matricula se houver
	 * @param matricula
	 * @return 
	 */
	public Funcionario findFuncionarioByMatricula(int matricula) {
		Funcionario funcionario = null;
		for (Funcionario f : listaFuncionarios) {
			if (f.getMatricula() == matricula) {
				funcionario = f;
			}
		}
		return funcionario;
	}
	/**
	 * recebe String nome e retorna funcionario com este nome se houver
	 * @param nome
	 * @return 
	 */
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
	 * formata a lista de cargos da forma: Codigo Cargo (a) + Nome Cargo (a) Horarios de acesso
	 * do (a)
	 */

	public String listaCargosFormatada(ArrayList<Cargo> listinha) {
		String listaForm = "";
		listaForm = "===Lista de Cargos possui " + ControladorCargo.getInstance().getListaCargos().size() + " cargos==="
				+ "\n";

		for (int a = 0; a < ControladorCargo.getInstance().getListaCargos().size(); a++) {
			listaForm = listaForm + "  ---/Código :"
					+ (ControladorCargo.getInstance().getListaCargos().get(a).getCodigo());
			listaForm = listaForm + "  ---/Cargo: "
					+ (ControladorCargo.getInstance().getListaCargos().get(a).getNome());
			if (ControladorCargo.getInstance().getListaCargos().get(a).getEhGerencial()) {
				listaForm = listaForm + " ----CARGO COM ACESSO IRRESTRITO AO FINANCEIRO----";
			} else {
				listaForm = listaForm + "Este cargo pode acessar o financeiro durante o intervalo :"
						+ (ControladorCargo.getInstance().getListaCargos().get(a).getHorariosPermitidos().toString())
						+ "\n";

			}
		}
		return listaForm;

	}
	/*
	 * Recebe a já validada String de 8 numeros e formata--> XY/ZW/ABCD
	 */
	public String formataDataPraPrintar(String a) {
		String dataPraPrintar;

		dataPraPrintar = a.substring(0, 2) + "/";
		dataPraPrintar = dataPraPrintar + a.substring(2, 4) + "/";
		dataPraPrintar = dataPraPrintar + a.substring(4);
		return dataPraPrintar;

	}
/*
 * retorna lista completa de funcionarios com todos os dados(menos tentativas de acesso)para ser printada 
 */
	public String retornalistaPraPrintar() {
		String aa = "";
		if (listaFuncionarios.size() == 0) {
			aa = "===Lista de Funcionários vazia===";

		}

		for (int a = 0; a < listaFuncionarios.size(); a++) {
			Funcionario fTemp = (listarFuncionarios().get(a));
			aa = aa + "\n"
					+ ("---/NOME " + (fTemp.getNome()) + "  ---/MATRICULA :" + (fTemp.getMatricula()) + "  ---/CARGO : "
							+ (fTemp.getCargo().getNome()) + "  ---/TELEFONE : " + (fTemp.getTelefone())
							+ "  ---/DATA NASCIMENTO : " + (formataDataPraPrintar(fTemp.getDataNascimento()))
							+ "  ---/SALARIO : " + (fTemp.getSalario()));

		}
		return aa;
	}

}
