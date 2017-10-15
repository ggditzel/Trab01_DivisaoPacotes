package br.ufsc.ine5605.horario;

import java.util.ArrayList;

import br.ufsc.ine5605.cargo.Cargo;
import br.ufsc.ine5605.cargo.ControladorCargo;
import br.ufsc.ine5605.cargo.ICargo;


public class ControladorHorario {
    private ArrayList<Horario> horariosAcesso;
    private TelaHorario tela;
    private static ControladorHorario instancia;
     

    private ControladorHorario() {
        horariosAcesso = new ArrayList<>();
        tela = new TelaHorario();
    }
    public static ControladorHorario getInstance(){
    	if(instancia == null){
    		instancia = new ControladorHorario();
    	}
    	return instancia;
    }
     

    /**
     * Realiza o cadastro de horarios para um cargo
     * @return
     *	ArrayList que contem os horarios que serao adicionados ao cargo
     */
    public ArrayList<Horario> iniciaCadastro(){
    	ArrayList<Horario> horariosCargo = new ArrayList<>();
    	if(horariosAcesso.isEmpty()){
    		horariosCargo.add(cadastrarHorario());
    	} else {
    		int opcao = tela.mostraListaAdicionarPrimeiroHorario(horariosAcesso);
    		if(opcao == 0){
    			horariosCargo.add(cadastrarHorario());
    		} else {
    			horariosCargo.add(horariosAcesso.get(opcao-1));
    		}
    	}
    	tela.listaHorarios(horariosCargo);
    	int opcao;
    	do{
    		opcao = tela.mostraOpcoesAdicionar();
    		if(opcao == 1){
    			if(!diferenca(horariosAcesso, horariosCargo).isEmpty()){
    				opcao = tela.mostraListaAdicionar(diferenca(horariosAcesso, horariosCargo));
    				if(opcao == 0){
    					break;
    				}else if(opcao == diferenca(horariosAcesso, horariosCargo).size() + 1) {
    					horariosCargo.add(cadastrarHorario());
    				} else {
    					horariosCargo.add(diferenca(horariosAcesso, horariosCargo).get(opcao-1));
    				}
    			} else {
    				horariosCargo.add(cadastrarHorario());
    			}
    			tela.listaHorarios(horariosCargo);
    		} else {
    			break;
    		}
    	}while(true);
    	
    	return horariosCargo;

    }
    //TODO
    //string no controlador
    /**
     * Cadastra um horario novo, adicionando-o no ArrayList da classe, repetindo ate a entrada ser valida
     * @return
     *	Horario novo que foi cadastrado
     */
    private Horario cadastrarHorario() {
    	boolean respostaOK = false;
    	Horario novo = null;
    	do{
    		String inicio = tela.perguntaInicio();
    		Hora horarioInicio = converte(inicio);
    		String fim = tela.perguntaFim();
    		Hora horarioFim = converte(fim);
    		if(possuiHorario(horarioInicio, horarioFim)){
    			tela.mostraAviso("Horario ja cadastrado.");
    			respostaOK = false; //Redundant
    		} else {
    			novo = new Horario(horarioInicio, horarioFim);
    			horariosAcesso.add(novo);
            	respostaOK = true;
    		}
    	} while(!respostaOK);
    	return novo;
    }
    
    /**
     * Adiciona horarios para um cargo
     * @param cargo
     *	Cargo o qual sera adicionado os horarios
     */
    public void adicionarHorariosCargo(ICargo cargo) {
    	ArrayList<Horario> horariosCargo = cargo.getHorariosPermitidos();
    	if(horariosAcesso.isEmpty()){
    		horariosCargo.add(cadastrarHorario());
    	} else {
    		int opcao = tela.mostraListaAdicionar(horariosAcesso);
		if(opcao == 0){
			return;
		} else if(opcao == horariosAcesso.size() + 1) {
    			horariosCargo.add(cadastrarHorario());
    		} else {
    			horariosCargo.add(horariosAcesso.get(opcao-1));
    		}
    	}
    	tela.listaHorarios(horariosCargo);
    	int opcao;
    	do{
    		opcao = tela.mostraOpcoesAdicionar();
    		if(opcao == 1){
    			if(!diferenca(horariosAcesso, horariosCargo).isEmpty()){
    				opcao = tela.mostraListaAdicionar(diferenca(horariosAcesso, horariosCargo));
    				if(opcao == 0) {
    					horariosCargo.add(cadastrarHorario());
    				} else {
    					horariosCargo.add(horariosAcesso.get(opcao-1));
    				}
    			} else {
    				horariosCargo.add(cadastrarHorario());
    			}
    			tela.listaHorarios(horariosCargo);
    		} else {
    			break;
    		}
    	}while(true);
    	
    }
    
    /**
     * Remove horarios de um cargo, caso o horario nao possua horarios chama o metodo adicionarHorarioCargo 
     * @param cargo
     * 	Cargo o qual serao removidos os horarios
     */
    public void removerHorariosCargo(ICargo cargo){
    	ArrayList<Horario> horariosCargo = cargo.getHorariosPermitidos();
	do{	
		if(horariosCargo.isEmpty()){
    			tela.mostraAviso("Este cargo nao possui horarios, voce precisa adicionar");
    			adicionarHorariosCargo(cargo);
   			break;
    		} else {
   			int opcao = tela.mostraListaRemover(horariosCargo);
    			if(opcao == 0){
    				break;
    			} else {
    				Horario aSerRemovido = horariosCargo.get(opcao - 1);
    				horariosCargo.remove(aSerRemovido);
    				if(!horarioEhUtilizado(aSerRemovido)) {
    					horariosAcesso.remove(aSerRemovido);
    				}
    				tela.mostraAviso("Horario removido com sucesso");
    			}
    		}	
    	}while(true);
    }
    
    public void editaHorariosCargo(ICargo cargo){
    	int opcao = tela.mostraOpcoesEditar();
    	switch(opcao){
    		case 0:
    			break;
    		case 1:
    			adicionarHorariosCargo(cargo);
    			break;
    		case 2:
    			removerHorariosCargo(cargo);
    			break;
    	}
    }
    
//TODO
    //javadoc
    public void listaHorarios(ICargo cargo){
    	tela.mostraLista(cargo.getHorariosPermitidos());
    }
    
    //TODO
    //javadoc incompleto
    /**
     * Faz a diferenca de 2 ArrayLists  
     * @param lista1
     * 	lista da qual sera tirada a diferenca
     * @param lista2
     * 	lista que tira
     * @return
     *	ArrayList que possui os elementos que pertencem a lista1 mas nao pertencem a lista2
     */
    private ArrayList<Horario> diferenca(ArrayList<Horario> lista1, ArrayList<Horario> lista2) {
    	
    	ArrayList<Horario> diferenca = new ArrayList<>();
    	diferenca.addAll(lista1);
    	diferenca.removeAll(lista2);
    	return diferenca;
    	
    }
    
    /**
     * Verifica se um horario ja pertence a lista de horarios 
     * @param inicio
     * 	inicio do horario que sera comparado
     * @param fim
     * 	final do horario que sera comparado
     * @return
     * true, se o horario ja estiver cadastrado, ou false, se a hora nao estiver cadastrado ainda
     */
    private boolean possuiHorario(Hora inicio, Hora fim) {
    	for(Horario i: horariosAcesso) {
    		if(i.getInicio().toString().equals(inicio.toString()) && i.getFim().toString().equals(fim.toString())){
    			return true;
    		}
    	}
    	return false;
    }
    
    //talvez precise usar o metodo possuiHorario
    //nao testado ainda
    private boolean horarioEhUtilizado(Horario horario){
    	for(Cargo c: ControladorCargo.getInstance().getListaCargos()) {
    		if(c.getHorariosPermitidos().contains(horario)){
    			return true;
    		}
    	}
    	return false;
    }
	public static Hora converte(String hora) {
		return new Hora(Integer.parseInt(hora.substring(0,2)), Integer.parseInt(hora.substring(3)));
	}
    
    
    
}
