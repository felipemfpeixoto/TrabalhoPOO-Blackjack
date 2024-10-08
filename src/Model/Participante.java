package Model;

import java.util.List;

/**
 * A classe abstrata Participante representa um jogador genérico no jogo de Blackjack,
 * tanto para o Dealer quanto para os Jogadores. Esta classe fornece métodos básicos
 * para receber cartas, calcular pontos e decidir ações.
 */
abstract class Participante {
	
    /**
     * Lista de cartas que o participante tem em sua mão.
     * Cada participante (dealer ou jogador) mantém um conjunto de cartas.
     */
    protected List<Carta> mao;
    
    /**
     * Adiciona uma nova carta à mão do participante.
     * 
     * @param carta A carta a ser adicionada à mão do participante.
     */
    public void receberCarta(Carta carta) {
        mao.add(carta);
    }
    
    /**
     * Calcula o total de pontos da mão do participante.
     * A soma dos valores de todas as cartas na mão é retornada.
     * 
     * <p>Nota: No Blackjack, valores como Ás podem ter múltiplos comportamentos,
     * mas esta implementação inicial simplesmente soma o valor das cartas
     * sem levar isso em conta.</p>
     *
     * @return O total de pontos na mão do participante.
     */
    public int calculaPontos() {
        int pontos = 0;
        for (Carta carta : mao) {
            pontos += carta.getValor();
        }
        return pontos;
    }
    
/**
     * Método abstrato para decidir a ação do participante no jogo.
     * Este método deve ser implementado por todas as classes que estendem Participante,
     * definindo a lógica de decisão para pedir carta ou parar.
     */
    public abstract void decidirAcao();
}
