package MansionZombie;

public abstract class Personajes {
    public int puntosVida;
    public int puntosAtaque;

    @Override
    public String toString() {
        return  "/npuntosVida=" + puntosVida + ", puntosAtaque=" + puntosAtaque + '}';
    }
    
    
}
