package br.ufsc.ine5605.horario;

import java.util.ArrayList;


public class Hora {
    private int hora;
    private int minuto;
    
    public Hora(int hora, int minuto){
        if(hora < 24 && minuto < 60){
            this.hora = hora;
            this.minuto = minuto;
        }
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }
    
    /**
     * Verifica se a hora antecede a hora do parametro
     * @param horario
     * 	hora que sera comparada
     * @return
     * true, se a hora anteceder ou for igual, ou false, se a hora suceder
     */
    public boolean vemAntes(Hora horario){
        return hora < horario.getHora() || (hora == horario.getHora() && minuto <= horario.getMinuto());

    }
    
    /**
     * Verifica se a hora sucede a hora do parametro
     * @param horario
     * 	hora que sera comparada
     * @return
     * true, se a hora suceder ou for igual, ou false, se a hora anteceder
     */
     public boolean vemDepois(Hora horario){
        return hora > horario.getHora() || (hora == horario.getHora() && minuto >= horario.getMinuto());
    }
     
    @Override
    public String toString() {
        if(hora >= 10 && minuto >= 10) {
        	return "" + hora + ":" + minuto;
        }
        if(hora >= 10) {
        	return "" + hora + ":0" + minuto;
        }
        if(minuto >= 10) {
        	return "0" + hora + ":" + minuto;
        } else {
        	return "0" + hora + ":0" + minuto;
        }
        
    }

	public boolean estaPresente(ArrayList<Horario> horarios) {
		for(Horario h: horarios) {
			if(h.contem(this)) {
				return true;
			}
		}
		return false;
	}
    
}
