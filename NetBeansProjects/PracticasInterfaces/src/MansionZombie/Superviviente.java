package MansionZombie;
import MansionZombie.Personajes;

public class Superviviente extends Personajes {

    protected int habitacion;
    protected final int puntosVidaMaximos;
    protected boolean botiquin;
    protected int cantidadArmas;
    protected int cantidadProtecciones;
    protected int zombiesHabitacion = 1;
    protected boolean salida = false;

    public Superviviente() {
        this.puntosVidaMaximos = 20;
        this.puntosVida = 20;
        this.puntosAtaque = 4;
        this.botiquin = false;
        this.cantidadArmas = 0;
        this.cantidadProtecciones = 0;
        this.habitacion = 1;
    }

    public int getPuntosVidaMaximos() {
        return puntosVidaMaximos;
    }

    public boolean isBotiquin() {
        return botiquin;
    }

    public int getCantidadArmas() {
        return cantidadArmas;
    }

    public int getCantidadProtecciones() {
        return cantidadProtecciones;
    }

    @Override
    public String toString() {
        return "\nSuperviviente{" + "habitacion=" + habitacion + ", puntosVidaMaximos=" + puntosVidaMaximos + ", botiquin=" + botiquin + ", cantidadArmas=" + cantidadArmas + ", cantidadProtecciones=" + cantidadProtecciones + '}';
    }

    public void curarse() {
        if (zombiesHabitacion > 0) {
            System.out.println("¡Todavía hay zombies!");
            return;
        }

        if (!botiquin) {
            System.out.println("No tienes botiquín.");
            return;
        }

        puntosVida += 4;

        if (puntosVida > puntosVidaMaximos) {
            puntosVida = puntosVidaMaximos;
        }

        botiquin = false;

        System.out.println("Te has curado.");
        System.out.println("Vida actual: " + puntosVida);
    }
}
