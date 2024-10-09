package Model;

import java.util.List;
import java.util.ArrayList;


abstract class Participante {
	
    protected List<Carta> mao = new ArrayList<>();
    int pontos = 0;
    /**
     * Adiciona uma nova carta à mão do participante.
     * 
     * @param carta A carta a ser adicionada à mão do participante.
     */
    public void receberCarta(Carta carta) {
        mao.add(carta);
        pontos += carta.getValor();
        System.out.printf("\n");
        System.out.println("Carta recebida: " + carta.getNome());
        System.out.println("Pontos: " + pontos);
        System.out.printf("\n");
    }
    
    public int calculaPontos() {
        int pontos = 0;
        for (Carta carta : mao) {
            pontos += carta.getValor();
        }
        return pontos;
    }
    
    public boolean estourou() {
    	return pontos > 21;
    }
    
    public abstract int decidirAcao();
}
