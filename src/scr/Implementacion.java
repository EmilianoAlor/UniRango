package scr;

import java.util.Arrays;

public class Implementacion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rango R = Rango.RangoAbiertoAbierto(5, 7);
		
		System.out.println(R);
		
		Rango R2 = Rango.RangoCerradoCerrado(5, 7);
		
		System.out.println(R2);
		
		Rango[] Lista =  { Rango.RangoAbiertoAbierto(5,5),
						   Rango.RangoAbiertoAbierto(4,4),
						   Rango.RangoAbiertoAbierto(3,3)};
		
		for (Rango rango : Lista) {
			System.out.println(rango);
		}		
		
		Arrays.sort(Lista);
		
		System.out.println("Cambio de orden");
		
		for (Rango rango : Lista) {
			System.out.println(rango);
		}
		
	}

}
