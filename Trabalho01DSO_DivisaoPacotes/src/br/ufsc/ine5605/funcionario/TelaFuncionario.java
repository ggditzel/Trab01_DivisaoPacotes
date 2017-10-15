package br.ufsc.ine5605.funcionario;

import br.ufsc.ine5605.RegrasValidacao;
import br.ufsc.ine5605.Tela;
import br.ufsc.ine5605.cargo.Cargo;
import br.ufsc.ine5605.cargo.ControladorCargo;

public class TelaFuncionario extends Tela {

	public DadosCadastroFuncionario IncluirFuncionario() {
			int matricula = -1;
			String nome;
			Cargo cargo;
			String telefone;
			String dataNascimento;
			int salario;
			
			mostraMensagem("==== Digite os dados solicitados ====");
			matricula = pedeMatricula();
			nome = pedeNome();
			
			perguntarCriarCargo();
			if(ControladorCargo.getInstance().getListaCargos().size() ==0) {
				mostraMensagem("Ainda ão ha um cargo cadastrado, iniciando criação de novo cargo");
				ControladorCargo.getInstance().incluirCargo();
				
			}
			cargo = pedeCargo();
			
			
			
			
			
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

	private void perguntarCriarCargo()  {
		mostraMensagem("Deseja criar um novo cargo agora?(S/N)");
		boolean eaee = false;
		do {
			String resposta =leitor.nextLine();
		
			try {
				if(super.verificaSN(resposta)) {
					ControladorCargo.getInstance().incluirCargo();
					eaee = true;
				}
				else {
					eaee = true;
					
				}
			} catch (Exception e) {
				mostraMensagem("Digite apenas 's' ou 'n'");
				eaee = false;
			}
		}while (!eaee);
			
			
	}
	
	private Cargo pedeCargo() {
		boolean eae = false;
		Cargo cargotemp = null;
		
		ControladorFuncionario.getInstance().printaListaCargoNomeCodigoHorario();
		
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

}

