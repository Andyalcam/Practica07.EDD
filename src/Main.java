import java.util.Scanner;
import java.util.InputMismatchException;
public class Main {
	public static void main(String[] args){

		boolean excep = true, repe;
        Scanner in = new Scanner(System.in);
        Scanner on = new Scanner(System.in);
        int opc;
        String tupla = "",a,b;

        System.out.println("*** BIENVENIDO ***");

        while (excep) {
            try {
                System.out.println("\n\t\t*** Menu ***");
                System.out.println("--------------------------------------------");
                System.out.println("1. Insertar formulas");
                System.out.println("2. Salir");
                System.out.println("--------------------------------------------");
                System.out.println("Ingresa una opcion del menu: ");
                opc = in.nextInt();

                switch (opc) {
                    case 1:
                        System.out.println("Ingresa el elemento que quieres insertar separado por puntos. Ej: H2.O");
                        repe = true;
                        while (repe) {
                            try {
                                tupla = on.nextLine().trim();
                                System.out.println(tupla);
                                repe = false;
                            } catch (Exception e) {
                                System.out.println("\t" + e + " Intentalo de nuevo. Sigue el ejemplo :)");
                            }
                        }
                    break;
                    case 2:
                        System.out.println("\tHasta luego :)" + "\n");
                        excep = false;
                        break;
                    default:
                        System.out.println("\tElige una opcion de menu plis :c");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\tDebes ingresar un número\tIntentalo de nuevo");
                in.next();
                excep = true;
            }
        }
	}	
}