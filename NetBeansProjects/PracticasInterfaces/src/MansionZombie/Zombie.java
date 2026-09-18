package MansionZombie;
import MansionZombie.Personajes;


public class Zombie extends Personajes{

    private int habitacion;
    
    public Zombie(int habitacion) {
        this.habitacion = habitacion;
        this.puntosVida = (int) (Math.random() * 2) + 2 + (habitacion - 1);
        this.puntosAtaque = (int) (Math.random() * 2) + 2 + (habitacion - 1);
    }

    public int getHabitacion() {
        return habitacion;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }

    public int getPuntosAtaque() {
        return puntosAtaque;
    }

    @Override
    public String toString() {
        return "/nZombie{" + "habitacion=" + habitacion + '}';
    }

    
}
