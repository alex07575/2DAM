package MansionZombie;
import MansionZombie.Personajes;


public class Zombie extends Personajes{

    protected int habitacion;
    
    public Zombie(int habitacion) {
        this.habitacion = habitacion;
        this.puntosVida = (int) (Math.random() * 2) + 2 + (habitacion - 1);
        this.puntosAtaque = (int) (Math.random() * 2) + 2 + (habitacion - 1);
    }

    public int getHabitacion() {
        return habitacion;
    }

    @Override
    public String toString() {
        return "\nZombie{" + "habitacion=" + habitacion + '}';
    }

    
}
