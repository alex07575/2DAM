package MansionZombie;
import MansionZombie.Personaje;

public class Superviviente extends Personaje {


    protected final int puntosVidaMaximos;
    

    public Superviviente() {
        this.puntosVidaMaximos = 20;
        this.puntosVida = 20;
        this.puntosAtaque = 4;

    }

    public int getPuntosVidaMaximos() {
        return puntosVidaMaximos;
    }


    @Override
    public String toString() {
        return "\nSuperviviente{" +  ", puntosVidaMaximos=" + puntosVidaMaximos;
    }

 
}
