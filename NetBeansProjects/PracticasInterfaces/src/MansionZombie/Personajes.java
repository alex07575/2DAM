package MansionZombie;

public abstract class Personajes {
    protected int puntosVida;
    protected int puntosAtaque;

    public int getPuntosVida() {
        return puntosVida;
    }

    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }

    public int getPuntosAtaque() {
        return puntosAtaque;
    }

     public void setPuntosAtaque(int puntosAtaque) {
        this.puntosAtaque = puntosAtaque;
    }
    

    @Override
    public String toString() {
        return  "/npuntosVida=" + puntosVida + ", puntosAtaque=" + puntosAtaque + '}';
    }
    
    
}
