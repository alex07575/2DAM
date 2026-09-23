package MansionZombie;

import java.util.Scanner;

public class Principal extends Juego {

    public static void main(String[] args) {
        Juego j = new Juego();
        Scanner sc = new Scanner(System.in);
        System.out.println("===BIENVENIDO A LA MANSIÓN ZOMBIE===");
        System.out.println("Elige la dificultad, 1=fácil o 2=difícil:");
        int respuesta = sc.nextInt();
        j.elegirDif(respuesta);
        int opcion = 0;
        
        while(opcion != 5){
            System.out.println("\n=== MENÚ ===");
            System.out.println("1. Combatir");
            System.out.println("2. Buscar");
            System.out.println("3. Avanzar / Salir");
            System.out.println("4. Curarse");
            System.out.println("5. Salir del juego");
            System.out.println("Elige una opción:");

            opcion = sc.nextInt();

            if (opcion == 1) {
                j.combate();
            }

            if (opcion == 2) {
                j.buscar();
            }

            if (opcion == 3) {
                j.avanzar();
            }

            if (opcion == 4) {
                j.curarse();
            }
        }

        sc.close();
        }
    }
