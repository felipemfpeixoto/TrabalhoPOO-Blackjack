package Model;

import java.util.List;

public class ModelAPI {
    private Dealer dealer;
    private Jogador jogador;
    private Baralho baralho;

    public ModelAPI() {
        baralho = new Baralho();
        dealer = new Dealer();
        jogador = new Jogador(new Banca());
        iniciarNovoJogo();
    }

    public void iniciarNovoJogo() {
        dealer.restartPoints();
        jogador.restartPoints();
        iniciaJogada();
    }

    private void iniciaJogada() {
        dealInicial();
    }

    private void dealInicial() {
        recebemCartas();
        recebemCartas();
    }

    private void recebemCartas() {
        dealer.receberCarta(baralho.giveCard());
        jogador.receberCarta(baralho.giveCard());
    }

    // Métodos públicos para obter as cartas do dealer e do jogador
    public List<Carta> getCartasDealer() {
        return dealer.getMao(); // Assumindo que `getMao()` retorna uma lista de cartas
    }

    public List<Carta> getCartasJogador() {
        return jogador.getMao(); // Assumindo que `getMao()` retorna uma lista de cartas
    }
}
