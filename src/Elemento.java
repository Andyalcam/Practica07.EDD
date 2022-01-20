public class Elemento{

	String nombre;
	String abre;
	Double peso;

	public Elemento(String nombre, String abre, Double peso){
		this.nombre = nombre;
		this.abre = abre;
		this.peso = peso;
	}

	public String getNombre(){
		return this.nombre;
	}

	public String getAbre(){
		return this.abre;
	}

	public Double getPeso(){
		return this.peso;
	}

	public String toString(){
		return ("Elemento: " + getNombre() + " - " + getAbre() + " - Peso: " + getPeso());
	}

}