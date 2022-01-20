import java.nio.charset.StandardCharsets;
import java.util.Arrays;
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
                    throw new IllegalArgumentException("Ingresa lo que se te pide");
                }

            }
        }
        return arrAux;
    }

	public Double calcular(String formula){

        String[] elementos = formula.split(",");

        //System.out.println("Elementos de la formula: " + Arrays.toString(elementos));

        Double pesoTotal = 0.0;
        String[] aux = new String[2];
        for (String elemento : elementos) {
            aux = mol(elemento);
            int moleculas = Integer.parseInt(aux[1]);
            Double peso = map.get(aux[0]).getPeso();
            pesoTotal += peso*moleculas;
        }

        return pesoTotal;

		/*Double peso1 = map.get(elem1).getPeso();
		Double peso2 = map.get(elem2).getPeso();

		//System.out.println("peso1: "+peso1+" peso2: "+peso2);

		Double total = peso1 + peso2;
		return total;*/
	}


	public static void main(String[] args){

		Calculadora calculadora = new Calculadora();

		boolean excep = true, repe;
        Scanner in = new Scanner(System.in);
        Scanner on = new Scanner(System.in);
        int opc;
        String tupla = "",a,b;

        System.out.println("*** BIENVENIDO ***");

        //calculadora.calcular("H2,O");

        System.out.println(calculadora.calcular("Li10,H2"));

        /*while (excep) {
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
                                calculadora.calcular(tupla);
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
        }*/
	}	
}