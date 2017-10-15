package br.ufsc.ine5605.funcionario;

import br.ufsc.ine5605.cargo.Cargo;

public class Funcionario {
	private int matricula;
	private String nome;
	private Cargo cargo;
	private String telefone;
	private String dataNascimento;
	private int salario;
	private int numeroAcessosNegados;
	
	
	public Funcionario(int matricula, String nome, Cargo cargo, String telefone, String dataNascimento, int salario) {
		this.matricula = matricula;
		this.nome = nome;
		this.cargo = cargo;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.salario = salario;
		this.numeroAcessosNegados = 0;
	}
	public int getMatricula() {
		return this.matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Cargo getCargo() {
		return this.cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public String getTelefone() {
		return this.telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getDataNascimento() {
		return this.dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public int getSalario() {
		return this.salario;
	}
	public void setSalario(int salario) {
		this.salario = salario;
	}
	public int getNumeroAcessosNegados() {
		return this.numeroAcessosNegados;
	}
	public void setNumeroAcessosNegados(int numeroAcessosNegados) {
		this.numeroAcessosNegados = numeroAcessosNegados;
	}

}
