package Model;

public class Dealer extends Participante {

    public Dealer() {
        super();
    }

    @Override
    public int decidirAcao() {
        
        if (pontos < 17) {
            return 1;  
        }
        return 0;  
    }

    public void jogar() {
        
        while (pontos < 17) {
            Carta novaCarta = Baralho.giveCard();  
            receberCarta(novaCarta);  

            
            if (pontos > 21) {
                System.out.println("Dealer passou do limite com " + pontos + " pontos!");
                break;  
            }
        }

        
        mostrarCartas();
    }

    public void mostrarCartas() {
        System.out.println("Cartas do Dealer:");
        for (Carta carta : mao) {
            System.out.println("Carta recebida: " + carta.getValor());
        }
        System.out.println("Total de pontos: " + pontos);
    }

    public void reset() {
        mao.clear();  
        restartPoints();
    }
}