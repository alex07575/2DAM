package MansionZombie;

import MansionZombie.Personajes;

public class Superviviente extends Personajes {

    private int habitacion;
    private int puntosVidaMaximos;
    private boolean botiquin;
    private int cantidadArmas;
    private int cantidadProtecciones;

    public Superviviente() {
        this.puntosVidaMaximos = 20;
        this.puntosVida = 20;
        this.puntosAtaque = 4;
        this.botiquin = false;
        this.cantidadArmas = 0;
        this.cantidadProtecciones = 0;
    }

    public int getPuntosVidaMaximos() {
        return puntosVidaMaximos;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public int getPuntosAtaque() {
        return puntosAtaque;
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
        return "/nSuperviviente{" + "habitacion=" + habitacion + ", puntosVidaMaximos=" + puntosVidaMaximos + ", botiquin=" + botiquin + ", cantidadArmas=" + cantidadArmas + ", cantidadProtecciones=" + cantidadProtecciones + '}';
    }

    public void combate(Superviviente v, Zombie z) {
        int contadorZ = 1;
        while (v.getPuntosVida() > 0 && z.getPuntosVida() > 0) {
            System.out.println("!COMBATE!");
            //Ataca el vivo
            int ataqueS = (int) (Math.random() * v.getPuntosAtaque() + 1);
            //Si encuentra arma le sube el ataque
            ataqueS += v.getCantidadArmas();
            //El z pilla
            z.setPuntosVida(z.getPuntosVida() - ataqueS);
            System.out.println("El vivo hace " + ataqueS + " puntos de daño.");
            //EL zombie ataca
            int ataqueZ = (int) (Math.random() * z.getPuntosAtaque()) + 1;
            int dañoRec = ataqueZ - v.getCantidadProtecciones();
            //Daño del z
            System.out.println("El zombie hace " + dañoRec + " puntos de daño.");
            if (dañoRec < 0) {
                dañoRec = 0;
            }
            System.out.println("Me han quitado" + dañoRec + "puntos de vida. ");
            //Vemos si la palma definitivo el z
            if (z.getPuntosVida() <= 0) {
                System.out.println("Murió del todo");
            }
            //Para reducir el número de zombies
            if (z.getPuntosVida() <= 0) {
                contadorZ = -1;
                System.out.println("Uno menos.");
            }
            //Comprobar si puedo pasar de habitación
            if (contadorZ == 0) {
                System.out.println("!Ya no hay zombies, siguiente!");
            }
        }
    }

    public void buscar() {

    }

    public void avanzar() {

    }

    public void curarse() {

    }

}
