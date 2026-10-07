package alumnos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class NotasVariosSubDirectorios {

    public static void main(String[] args) {
        File alumno1 = new File("C:\\RepoGitHub\\2DAM\\NetBeansProjects\\PracticasServiciosYProcesos\\ALUMNOS\\ALUMNO1\\notas.txt");
        File alumno2 = new File("C:\\RepoGitHub\\2DAM\\NetBeansProjects\\PracticasServiciosYProcesos\\ALUMNOS\\ALUMNO2\\notas.txt");
        File alumno3 = new File("C:\\RepoGitHub\\2DAM\\NetBeansProjects\\PracticasServiciosYProcesos\\ALUMNOS\\ALUMNO3\\notas.txt");
        double media1 = calcularMedia(alumno1);
        double media2 = calcularMedia(alumno2);
        double media3 = calcularMedia(alumno3);
        System.out.println("Alumno1: " + media1);
        System.out.println("Alumno2: " + media2);
        System.out.println("Alumno3: " + media3);
        double sumaMate = calcularNota(alumno1, "MATE") + calcularNota(alumno2, "MATE") + calcularNota(alumno3, "MATE");
        double sumaLengua = calcularNota(alumno1, "LENGUA") + calcularNota(alumno2, "LENGUA") + calcularNota(alumno3, "LENGUA");
        double sumaEduca = calcularNota(alumno1, "EDUCA") + calcularNota(alumno2, "EDUCA") + calcularNota(alumno3, "EDUCA");
        System.out.println();
        System.out.println("MEDIA POR MODULO");
        System.out.println("MATE: " + sumaMate / 3);
        System.out.println("LENGUA: " + sumaLengua / 3);
        System.out.println("EDUCA: " + sumaEduca / 3);
    }

    public static double calcularMedia(File fichero) {
        double suma = 0;
        int cantidad = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(" ");
                double nota = Double.parseDouble(datos[1].replace(",", "."));
                suma += nota;
                cantidad++;
            }
        } catch (IOException e) {
            System.out.println("Error leyendo el fichero.");
        }
        return suma / cantidad;
    }

    public static double calcularNota(File fichero, String modulo) {
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(" ");
                if (datos[0].equals(modulo)) {
                    return Double.parseDouble(datos[1].replace(",", "."));
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo el fichero.");
        }
        return 0;
    }
}