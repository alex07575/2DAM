package MansionZombie;

public class Juego {
    int puntosVidaMaximos = 20;
    int busqRes = 3;
    int habitacion = 1;
    int zombiesHabitacion = 1;
    boolean salida = false;
    int puntosVida ;
    int puntosAtaque = 4;
    boolean botiquin = false;
    int cantidadArmas = 0;
    int cantidadProtecciones = 0;
    protected int maxHabit;

    public void elegirDif(int dificultad) {

        if (dificultad == 1) {
            maxHabit = 5;
        } else if (dificultad == 2) {
            maxHabit = 10;
        } else {
            System.out.println("No válido. ");
            return;
        }
    }

    public int getMaxHabit() {
        return maxHabit;
    }

    public int getBusqRes() {
        return busqRes;
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

    
    
    public void combate() {
        Juego j = new Juego();
        Superviviente v = new Superviviente();
        Zombie z = new Zombie(habitacion);
        System.out.println("!COMBATE!");
        boolean zVivo = true;
        while (v.getPuntosVida() > 0 && zVivo) {
            //Ataca el superv
            int ataqueS = (int) (Math.random() * v.getPuntosAtaque() + 1);

            //Si encuentra arma le sube el ataque
            ataqueS += j.getCantidadArmas();

            //El z pilla
            z.setPuntosVida(z.getPuntosVida() - ataqueS);
            System.out.println("El vivo hace " + ataqueS + " puntos de daño.");

            //Para reducir el número de zombies
            if (z.getPuntosVida() <= 0) {
                zombiesHabitacion--;
                System.out.println("Uno menos.");
                zVivo = false;
            } else {
                //EL zombie ataca
                int ataqueZ = (int) (Math.random() * z.getPuntosAtaque()) + 1;
                int dañoRec = ataqueZ - j.getCantidadProtecciones();
                if (dañoRec < 0) {
                    dañoRec = 0;
                }
                //Daño del z
                System.out.println("El zombie hace " + dañoRec + " puntos de daño.");
                v.setPuntosVida(v.getPuntosVida() - dañoRec);
                System.out.println("Me han quitado " + dañoRec + " puntos de vida. ");

            }

            // Comprobamos si ha muerto el superviviente
            if (v.getPuntosVida() <= 0) {
                System.out.println("!El superviviente ha muerto!");
            }

            //Vemos si la palma definitivo el z
            if (z.getPuntosVida() <= 0) {
                System.out.println("Murió del todo");
            }
        }
        //Comprobar si puedo pasar de habitación
        if (zombiesHabitacion == 0) {
            System.out.println("!Ya no hay zombies, siguiente!");
        }
    }

    public void buscar() {
        if (zombiesHabitacion > 0) {
            System.out.println("!Todavía hay zombies!");
            return;
        }

        if (busqRes <= 0) {
            System.out.println("No quedan búsquedas.");
            return;
        }

        busqRes--;

        int dado = (int) (Math.random() * 100) + 1;

        if (dado <= 75) {

            System.out.println("!Has hecho ruido!");

            int ruido = (int) (Math.random() * 100) + 1;

            if (ruido <= 40) {
                System.out.println("No pasa nada.");
            } else if (ruido <= 80) {
                zombiesHabitacion++;
                System.out.println("!Ha aparecido un zombie!");
            } else {
                zombiesHabitacion += 2;
                System.out.println("!Han aparecido dos zombies!");
            }
        } else if (dado <= 90) {
            if (!botiquin) {
                botiquin = true;
                System.out.println("Tienes un botiquín. ");
            } else {
                System.out.println("Ya tienes un botiquín. ");
            }
        } else if (dado <= 95) {
            cantidadProtecciones++;
            System.out.println("Has encontrado algo para protegerte. ");
        } else {
            cantidadArmas++;
            System.out.println("Has encontrado un arma. ");
        }
        System.out.println("Búsquedas restantes: " + busqRes);
    }

    public void avanzar() {

        if (zombiesHabitacion > 0) {
            System.out.println(""
                    + "!Todavía hay zombies en la habitación!");
            return;
        }

        if (habitacion == maxHabit) {
            salida = true;
            System.out.println("!SALIR DE LA MANSIÓN!");
        } else {
            habitacion++;
            zombiesHabitacion = 1;
            busqRes = 3;

            System.out.println("Avanzas a la habitación " + habitacion);
        }
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
