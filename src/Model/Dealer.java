package Model;

class Dealer extends Participante{

	public Dealer() {
		super();
	}
	
	@Override
    public void decidirAcao() {
        if (calculaPontos() < 17) {
            receberCarta(Baralho.giveCard());
        }
    }
}
