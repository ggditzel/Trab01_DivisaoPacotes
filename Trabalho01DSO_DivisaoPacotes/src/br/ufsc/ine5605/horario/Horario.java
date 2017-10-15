package br.ufsc.ine5605.horario;


public class Horario {
    private Hora inicio;
    private Hora fim;

    public Horario(Hora inicio, Hora fim) {
        this.inicio = inicio;
        this.fim = fim;
    }
    
    public Horario(int horaInicio,int minInicio, int horaFim, int minFim){
        this.inicio = new Hora(horaInicio, minInicio);
        this.fim = new Hora(horaFim, minFim);
    }
    
    public Hora getInicio() {
        return inicio;
    }

    public void setInicio(Hora inicio) {
        this.inicio = inicio;
    }

    public Hora getFim() {
        return fim;
    }

    public void setFim(Hora fim) {
        this.fim = fim;
    }  
    
    /**
     * Verifica se a hora esta no intervalo 
     * @param horario
     * 	horario que sera comparado
     * @return
     * true, se a hora pertencer, ou false, se a hora nao estiver no intervalo
     */
    public boolean contem(Hora horario){
        if(inicio.vemAntes(fim)){
            return inicio.vemAntes(horario) && fim.vemDepois(horario);
        }else return inicio.vemAntes(horario) || fim.vemDepois(horario);
    }
    
    @Override
    public String toString(){
        return inicio.toString() + " - " + fim.toString();
    }
 
}
