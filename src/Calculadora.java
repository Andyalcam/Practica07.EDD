import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.NumberFormatException;
/**
* Clase que simula una calculadora de peso molar
* @author Andrea Alvarado Camacho
* @author Alfonso Mondragón Segoviano
* @version 1.0
*/
public class Calculadora {

	Map<String, Elemento> map;

	/**
     * Constructor por omision
     */
    public Calculadora(){
		leerTabla("src/tabla-periodica.txt");
	}

	/**
     * Metodo para llenar un Mapa a partir de un archivo .txt
     * @param ruta - Cadena con la ruta especificada del archivo
     */
    public void leerTabla(String ruta){
		map = new AbstractHashMap<>(2999);

        try {
            FileReader fr = new FileReader(ruta);
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
            System.out.println("Tabla no encontrada");
        }catch (IOException e) {
        	System.out.println("Tabla no válida");
        }
	}

    /**
     * Metodo que separa una formula en su elemento y sus moleculas
     * @param elemento - String con la formula completa
     * @return String[] - arreglo de dos elemento, uno es la abreviatura del elemento y el segundo el numero de moleculas que tiene el elemento en la formula
     */
    public String[] mol(String elemento){
        String[] arrAux = new String[2];
        if(elemento.length() == 1){
            arrAux[0] = elemento;
            arrAux[1] = "1";
        }else{
            byte[] bytes = elemento.getBytes(StandardCharsets.US_ASCII);
            for (int i = 1; i < bytes.length; i++) {
                if(bytes[i] > 96 && bytes[i] < 123){
                    //Es una letra
                    if(i == elemento.length()-1) { //Por si es un elemento de 2 o 3 letras y lleva solamente 1 molecula
                        arrAux[0] = elemento.substring(0,i+1);
                        arrAux[1] = "1";
                    }
                }else if(bytes[i] > 48 && bytes[i] < 58){ //Para saber cuantas moleculas tiene un elemento
                    //Empiezan los numeros
                    arrAux[0] = elemento.substring(0,i);
                    arrAux[1] = elemento.substring(i);
                    break;
                }else{
                    throw new IllegalArgumentException("Ingresa una fórmula válida");
                }
            }
        }
        return arrAux;
    }

	/**
     * Metodo que calcula el peso molecular de una fórmula quimica
     * @param formula - String con la fórmula química a calcular
     * @return double con el valor total del peso
     */
    public Double calcular(String formula){

        String[] elementos = formula.split(",");

        Double pesoTotal = 0.0;
        String[] aux = new String[2];
        for (String elemento : elementos) {
            aux = mol(elemento);
            int moleculas = Integer.parseInt(aux[1]);
            Double peso = map.get(aux[0]).getPeso();
            pesoTotal += peso*moleculas;
        }
        return pesoTotal;
	}


	public static void main(String[] args){

		Calculadora calculadora = new Calculadora();

		boolean excep = true, repe, buclesito;
        Scanner in = new Scanner(System.in);
        Scanner on = new Scanner(System.in);
        int opc;
        String formula = "",menu = "";

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
                        repe = true;
                        while (repe) {
                            try {
                                System.out.println("Ingresa tu fórmula separada por comas. Ej: H2,O");
                                formula = on.nextLine().trim();
                                System.out.println("El peso molecular total es: " + calculadora.calcular(formula));
                                System.out.println("\n¿Deseas salir al menú?\tEscribe Si para confirmar o No para continuar ingresando fórmulas");
                                buclesito = true;
                                while (buclesito) {
                                    menu = on.nextLine();
                                    if(menu.equalsIgnoreCase("si") || menu.equalsIgnoreCase("chi")){
                                        buclesito = false;
                                        repe = false;
                                    }else if(menu.equalsIgnoreCase("no") || menu.equalsIgnoreCase("ño")){
                                        buclesito = false;
                                    }else{
                                        System.out.println("Ingresa solamente si o no");
                                        repe = true;
                                    }
                                }
                            } catch (NullPointerException e) {
                                System.out.println("\t Fórmula no válida. Elemento no encontrado");
                                repe = false;
                            } catch (NumberFormatException e) {
                                System.out.println("\tDebes separar los elementos por comas");
                                repe = false;
                            } catch (IllegalArgumentException e) {
                                System.out.println("\tDebes ingresar un número natural positivo");
                                repe = false;
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
            }
        }
	}	
}