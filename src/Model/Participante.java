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
        System.out.println("Carta recebida: " + carta.getNome() + " de " + carta.getNaipe());
        System.out.println("Pontuação: " + pontos);
    }
    
    public int calculaPontos() {
        int pontos = 0;
        for (Carta carta : mao) {
            pontos += carta.getValor();
        }
        return pontos;
    }
    
    public abstract void decidirAcao();
}
