package br.ufsc.ine5605;

import br.ufsc.ine5605.acesso.ControladorTentativaAcesso;
import br.ufsc.ine5605.cargo.ControladorCargo;
import br.ufsc.ine5605.funcionario.ControladorFuncionario;

public class ControladorPrincipal {
	private TelaPrincipal tela;

	private final String[] opcoesMenuPrincipal = { "Sair", "Gerenciar Sistema", "Iniciar Terminal de Acesso" };

	// para alterar horarios, apenas dentro das operacoes com cargos
	private final String[] opcoesGerenciarSistema = { "Voltar", "Operacoes com Cargos", "Operacoes com Funcionarios",
														"Relatorios de Acesso"};

	ControladorPrincipal() {
		tela = new TelaPrincipal();
	}

	public void inicia() {
		int opcao = -1;
		do {
			opcao = tela.mostraMenu(opcoesMenuPrincipal);
			switch (opcao) {
			case 0: // sair
				break;
			case 1: // gerenciar sistema
				gerenciarSistema();
				break;
			case 2: // iniciar terminal de acesso
				iniciarTerminal();
			}
		} while (opcao != 0);
	}

	private void gerenciarSistema() {
		int opcao = -1;
		do {
			opcao = tela.mostraMenu(opcoesGerenciarSistema);
			switch (opcao) {
			case 0: // voltar
				break;
			case 1: // operacoes com cargos
				ControladorCargo.getInstance().mostraMenu();
				break;
			case 2: // operacoes com funcionarios
				ControladorFuncionario.getInstance().mostraMenu();
				break;
			case 3: // relatiorios de acesso
				ControladorTentativaAcesso.getInstance().menuRelatorioTentativas();
				break;
			}
		} while (opcao != 0);
	}

	private void iniciarTerminal() {
		ControladorTentativaAcesso.getInstance().iniciaTentativa();
	}
}
