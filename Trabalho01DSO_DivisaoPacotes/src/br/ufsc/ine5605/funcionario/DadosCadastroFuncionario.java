package br.ufsc.ine5605.funcionario;

import br.ufsc.ine5605.cargo.Cargo;

public class DadosCadastroFuncionario {
		public int matricula;
		public String nome;
		public Cargo cargo;
		public String telefone;
		public String dataNascimento;
		public int salario;
		public int numeroAcessosNegados;
		
		
		public DadosCadastroFuncionario(int matricula, String nome, Cargo cargo, String telefone, String dataNascimento, int salario) {
			this.matricula = matricula;
			this.nome = nome;
			this.cargo = cargo;
			this.telefone = telefone;
			this.dataNascimento = dataNascimento;
			this.salario = salario;
			this.numeroAcessosNegados = 0;
		}
}
