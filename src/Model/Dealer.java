package Model;

class Dealer extends Participante {
    private boolean revelarCartas;  

    public Dealer() {
        super();
        this.revelarCartas = false;  
    }

    @Override
    public int decidirAcao() {
        if (calculaPontos() < 17) {
            receberCarta(Baralho.giveCard());
            return 1;  
        }
        return 0;  
    }
    
    
    public void revelarSegundaCarta() {
        this.revelarCartas = true;
        mostrarCartas();  
        
        
        while (calculaPontos() < 17) {
            receberCarta(Baralho.giveCard());  
            mostrarCartas();  
        }
    }

   
    public void mostrarCartas() {
        if (revelarCartas) {
            System.out.println("CHAMA");
        } else {
            
            System.out.println("CHAMA OU NAO CHAMA");
        }
    }

    
    public void reset() {
        this.revelarCartas = false; 
    }
}