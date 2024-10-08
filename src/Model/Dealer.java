package Model;

class Dealer extends Participante{

	public Dealer() {
		super();
	}
	
	@Override
    public void decidirAcao() {
		
        if (calculaPontos() < 17) {					// O dealer segue a regra: pedir até ter 17.
            receberCarta(Baralho.giveCard());
        }
        
    }
}
