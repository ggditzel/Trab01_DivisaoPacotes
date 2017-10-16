package br.ufsc.ine5605.funcionario;

import br.ufsc.ine5605.RegrasValidacao;
import br.ufsc.ine5605.Tela;
import br.ufsc.ine5605.cargo.Cargo;
import br.ufsc.ine5605.cargo.ControladorCargo;

public class TelaFuncionario extends Tela {

	/**
	 * Recebe String para ser listada ao chamar o metodo pedeCargo, solicita via Scanner os dados necessarios para o cadastro de um novo
	 * Funcionario, podendo cadastrar também um novo cargo, retornando as informacoes em uma classe temporaria a ser
	 * utilizada pelo ControladorFuncionario
	 * 
	 * @return DadosCadastroFuncionario(int matricula,String nome,Cargo cargo,String telefone,String dataNascimento,int  salario)
	 */
	public DadosCadastroFuncionario IncluirFuncionario(String listaC) {
			int matricula = -1;
			String nome;
			Cargo cargo;
			String telefone;
			String dataNascimento;
			int salario;
			
			mostraMensagem("==== Digite os dados solicitados ====");
			matricula = pedeMatricula();
			nome = pedeNome();
			cargo = pedeCargo(listaC);
			
			

			
			
			mostraMensagem("Digite o telefone do funcionario(Apenas numeros)");
			
			boolean eae = false;
			do{
				telefone = leitor.nextLine();
				if(!telefone.matches("^[0-9]+$")) {
			
				mostraMensagem("Digite o telefone do funcionario(APENAS NUMEROS)");
				}
				else {
					eae = true;
				}
				
			}while (!eae);
			
			mostraMensagem("digite a data de nascimento do funcionario(No formato ddMMaaaa) ");
			dataNascimento = leitor.nextLine();
			do {
				eae = false;
			
			if (!dataNascimento.matches("^[0-9]{8}$")){
				mostraMensagem("digite a data de nascimento do funcionario(No formato ddMMaaaa");
				dataNascimento = leitor.nextLine();
			}
				else {
					eae = true;
				}
				}while (!eae);
			
			mostraMensagem("digite o salario do Funcionario");
			salario = leInteiroPositivo();
			
			return new DadosCadastroFuncionario(matricula, nome, cargo, telefone, dataNascimento,  salario);

}


	/*
	 * lista os cargos cadastrados com horarios de acesso, pede e valida o codigo de cargo existente
	 */
	private Cargo pedeCargo(String listaC) {
		boolean eae = false;
		Cargo cargotemp = null;
		
		mostraMensagem(listaC);
		
	 mostraMensagem("Digite o codigo de um dos cargos da lista");
	 do{
		 
		 int codcarg = leInteiroPositivo();
		 if (ControladorCargo.getInstance().findCargoByCodigo(codcarg) != null){
			 cargotemp = ControladorCargo.getInstance().findCargoByCodigo(codcarg);
			 eae = true;
		 }
		 else {
			 mostraMensagem("Digite o codigo de um dos cargos da lista!");
		 }
	 }while (!eae);
	 
	 return cargotemp;
	 
	 	
	}


	public void mostraMensagem(String mensagem) {
		System.out.println(mensagem);
	}
	/*
	 * pede via console e valida a matricula
	 */
	public int pedeMatricula() {
		boolean eae = false;
		int matricula = 0;
		do {	
	mostraMensagem("Digite a matricula do Funcionario(APENAS NUMEROS)");
	 matricula = leInteiroPositivo();
		if(ControladorFuncionario.getInstance().findFuncionarioByMatricula(matricula) == null) {	
		eae = true;
		
	}	else { mostraMensagem("Tente outro número de Matricula, este ja está em uso");
		}
		} while (!eae);
		return matricula;
	
}
	/*
	 * pede via console e valida o nome, verificar classe RegrasValidacao
	 */
	public String pedeNome() {
		boolean eae = false;
		String nome = "";
		do {
		mostraMensagem("Digite o nome do Funcionario");
		String nomet = leitor.nextLine();	
		if (validaNome(nomet, RegrasValidacao.VALIDA_NOME_FUNCIONARIO.getRegraValidacao())) {
			nome = nomet;	
			eae = true;
		}
		else {
			mostraMensagem(RegrasValidacao.VALIDA_NOME_FUNCIONARIO.getExplicacaoRegra());
		}
		}while (!eae);
		return nome;
	}
	/**
	 * recebe um int e printa que ela não foi encontrada
	 * @param a
	 */
	public void mensagemNexisteMat(int a){
		System.out.println("-Funcionario de matricula '"+ a + "'nao encontrado, tente novamente-");
	}
	
	/*
	 * Printa "Funcionario de matricula '"+a+"'Selecionado para operação"
	 * a = int
	 */
	public void printaNumMatOk(int a) {
		System.out.println("Funcionario de matricula '"+a+"'Selecionado para operação");
	}
	/*
	 * Printa("Matricula não cadastrada, tente novamente"
	 */
	public void printaMatriculaInvalida() {
		System.out.println("Matricula não cadastrada, tente novamente");
	}
	/*
	 * Printa "Digite a matricula do Funcionario para a operação selecionada"
	 */
	public void digiteSuaMAt() {
		System.out.println("Digite a matricula do Funcionario para a operação selecionada");
	}
	
}

