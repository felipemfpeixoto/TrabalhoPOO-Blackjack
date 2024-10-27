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
    
    public void restartPoints() {
    	pontos = 0;
    }
    
    public int calculaPontos() {
        int pontos = 0;
        for (Carta carta : mao) {
            pontos += carta.getValor();
        }
        return pontos;
    }
    
    public boolean estourou() {
        if (pontos > 21) {
            // Verifica se existe um Ás na mão do jogador
            for (Carta carta : mao) {
                if (carta.getNome().equalsIgnoreCase("Ás") && carta.getValor() == 11) {
                    System.out.println("Ás encontrado! Alterando valor para 1.");
                    carta.setValor(1);
                    pontos = calculaPontos(); // Recalcula os pontos após a mudança
                    break;
                }
            }
        }
        return pontos > 21;
    }
    
    public boolean checkBlackJack() {
    	return (mao.size() == 2) && (pontos == 21);
    }
    
    public List<Carta> getMao() {
    	return this.mao;
    }
    
    public abstract int decidirAcao();
}
