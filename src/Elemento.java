/**
* Clase que crea objetos para guardar un elemento de la tabla periodica
* @author Andrea Alvarado Camacho
* @author Alfonso Mondragón Segoviano
* @version 1.0
*/
public class Elemento{

	String nombre;
	String abre;
	Double peso;

	/**
	 * Constructor con parametros
	 * @param nombre - cadena con el nombre del elemento
	 * @param abre - abreviatura del elemento
	 * @param peso - double con el peso atomico del elemento
	 */
	public Elemento(String nombre, String abre, Double peso){
		this.nombre = nombre;
		this.abre = abre;
		this.peso = peso;
	}

	/**
	 * Metodo para recuperar el nombre del elemento
	 * @return string - cadena con el nombre del elemento
	 */
	public String getNombre(){
		return this.nombre;
	}

	/**
	 * Metodo para recuperar la abreviatura del elemento
	 * @return string - cadena con la abreviatura del elemento
	 */
	public String getAbre(){
		return this.abre;
	}

	/**
	 * Metodo para recuperar el peso del elemento
	 * @return double con el valor del peso atomico
	 */
	public Double getPeso(){
		return this.peso;
	}

	/**
	 * Metodo para imprimir en terminal el objeto Elemento
	 * @return string - cadena con todos los atributos del Elemento
	 */
	public String toString(){
		return ("Elemento: " + getNombre() + " - " + getAbre() + " - Peso: " + getPeso());
	}

}