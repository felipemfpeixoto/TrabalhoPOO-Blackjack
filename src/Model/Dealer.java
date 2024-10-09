package Model;

class Dealer extends Participante{

	public Dealer() {
		super();
	}
	
	@Override
    public int decidirAcao() {
        if (calculaPontos() < 17) {
            receberCarta(Baralho.giveCard());
        }
        return 1;
    }
}
