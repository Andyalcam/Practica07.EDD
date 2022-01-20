import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Calculadora {

	Map<String, Elemento> map;

	public Calculadora(){
		leerTabla();
	}

	public void leerTabla(){
		map = new AbstractHashMap<>(2999);

        try {
            FileReader fr = new FileReader("src/tabla-periodica.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea=br.readLine()) != null){
            	String nombre = linea.split(",")[0];
            	String abre = linea.split(",")[1];
            	Double peso = Double.parseDouble(linea.split(",")[2]);
            	Elemento elemento = new Elemento(nombre,abre,peso);
                map.put(elemento.getAbre(),elemento);
            }
        }catch (FileNotFoundException e){
            System.out.println(e + "Tabla no encontrada");
        }catch (IOException e) {
        	System.out.println("Tabla no válida");
        }
	}

	public Double calcular(String elem1, String elem2){
		Double peso1 = map.get(elem1).getPeso();
		Double peso2 = map.get(elem2).getPeso();

		//System.out.println("peso1: "+peso1+" peso2: "+peso2);

		Double total = peso1 + peso2;
		return total;
	}


	public static void main(String[] args){

		Calculadora calculadora = new Calculadora();

		boolean excep = true, repe;
        Scanner in = new Scanner(System.in);
        Scanner on = new Scanner(System.in);
        int opc;
        String tupla = "",a,b;

        System.out.println("*** BIENVENIDO ***");

        System.out.println(calculadora.calcular("H","Li"));

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
                        System.out.println("Ingresa tu formula separada por puntos. Ej: H2.O");
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