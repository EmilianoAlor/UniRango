package scr;

import java.util.Objects;

/**
 * Ejercicio 01 Implementar la clase Rango, que representará los conceptos de
 * intervalos numéricos. Un rango puede ser abierto a izquierda, a derecha,
 * abierto o cerrado. Más información: Wikipedia Deberá cumplir con la siguiente
 * especificación: 1. Un rango deberá poder crearse en sus cuatro combinaciones
 * posibles: [1.0, 2.0] [1.0, 2.0) (1.0, 2.0] (1.0, 2.0) HECHO!! 2. Dado que
 * tener un constructor tan complejo puede ser perjudicial, implementar cuatro
 * métodos estáticos que permitan la creación de estas combinaciones. HECHO!! 3.
 * Teniendo los métodos estáticos, será buena idea hacer el constructor privado,
 * ya que sólamente se accederá a él por los métodos estáticos. HECHO!! 4. Se
 * debe poder consultar si un número está dentro de un rango. HECHO!! 5. Se debe
 * poder consultar si un rango está dentro de un rango. HECHO!! 6. Se debe poder
 * consultar si hay intersección entre dos rangos. HECHO!! 7. Se debe poder
 * comparar por igualdad los rangos. HECHO!! 8. Se debe poder ordenar rangos
 * mediante su inicio. Si empatan, se resuelve el empate por su fin. Si empatan,
 * los rangos cerrados irán primero que los abiertos. ¿ORDENAR EN DONDE? 9. Se
 * debe poder imprimir un rango en formato cadena de caracteres. HECHO!! 10. Un
 * rango es inmutable: no puede modificarse una vez creado. HECHO!! 11.
 * Proporcionar un método estático que devuelva un rango que abarque a todos los
 * otros rangos. HECHO!! 12. Se deben poder sumar rangos, utilizando el inicio
 * del primero y el fin del segundo. ¿Sumar como ? 13. Se debe poder calcular un
 * rango intersección, que en caso de no existir tal intersección retornará
 * (0.0, 0.0) HECHO!!! 14. Se debe poder desplazar un rango con un número
 * escalar ¿como desplazar... no eran inmutable?
 **/

public class Rango implements Comparable<Rango> {

	
	/**10. Un rango es inmutable: no puede modificarse una vez creado.
	**/
	private  boolean  inicioAbierto;
	private  double  rangoInicio;
	private  boolean  finalAbierto;
	private  double  rangoFinal;

	private static boolean inicioAbiertoTOTAL;
	private static double rangoIniciaTOTAL;
	private static boolean finalAbiertoTOTAL;
	private static double rangoFinalTOTAL;

	public boolean isInicioAbierto() {
		return inicioAbierto;
	}

	public double getRangoInicio() {
		return rangoInicio;
	}

	public boolean isFinalAbierto() {
		return finalAbierto;
	}

	public double getRangoFinal() {
		return rangoFinal;
	}
	/**
	 * 3. Teniendo los métodos estáticos, será buena idea hacer el constructor privado, ya que
	sólamente se accederá a él por los métodos estáticos
	 * @param inicioAbierto
	 * @param rangoInicio
	 * @param finalAbierto
	 * @param rangoFinal
	 */
	private Rango(boolean inicioAbierto, double rangoInicio, boolean finalAbierto, double rangoFinal) {

		if (rangoInicio < rangoFinal || rangoInicio == rangoFinal) {
			this.inicioAbierto = inicioAbierto;
			this.rangoInicio = rangoInicio;
			this.finalAbierto = finalAbierto;
			this.rangoFinal = rangoFinal;

			if (Rango.rangoIniciaTOTAL == 0 && Rango.rangoFinalTOTAL == 0) {
				Rango.inicioAbiertoTOTAL = inicioAbierto;
				Rango.rangoIniciaTOTAL = rangoInicio;
				Rango.rangoFinalTOTAL = rangoFinal;
				Rango.finalAbiertoTOTAL = finalAbierto;
			} else {
				if (Rango.rangoIniciaTOTAL > rangoInicio) {
					Rango.rangoIniciaTOTAL = rangoInicio;
					Rango.inicioAbiertoTOTAL = inicioAbierto;
				} else {
					if (Rango.rangoIniciaTOTAL == rangoInicio && Rango.inicioAbiertoTOTAL == true
							&& inicioAbierto == false)
						Rango.inicioAbiertoTOTAL = false; // si tienen el mismo inicio y el nuevo esta cerrado, cambia el
															// estado del inicio a
															// cerrado.
				}

				if (Rango.rangoFinalTOTAL < rangoFinal) {
					Rango.rangoFinalTOTAL = rangoFinal;
					Rango.finalAbiertoTOTAL = finalAbierto;
				} else {
					if (Rango.rangoFinalTOTAL == rangoFinal && Rango.finalAbiertoTOTAL == true && finalAbierto == false)
						Rango.finalAbiertoTOTAL = false; // si tienen el mismo final y el nuevo esta cerrado, cambia el
														// estado del inicio a
														// cerrado.
				}

			}
		}
	}

	/**
	 * 2. Dado que tener un constructor tan complejo puede ser perjudicial, implementar cuatro
		métodos estáticos que permitan la creación de estas combinaciones.

	 * @param rangoInicio
	 * @param rangoFinal
	 * @return
	 */
	public static Rango RangoAbiertoAbierto(double rangoInicio, double rangoFinal) {
		return new Rango(true, rangoInicio, true, rangoFinal);
	}

	public static Rango RangoAbiertoCerrado(double rangoInicio, double rangoFinal) {
		return new Rango(true, rangoInicio, false, rangoFinal);
	}

	public static Rango RangoCerradoAbierto(double rangoInicio, double rangoFinal) {
		return new Rango(false, rangoInicio, true, rangoFinal);
	}

	public static Rango RangoCerradoCerrado(double rangoInicio, double rangoFinal) {
		return new Rango(false, rangoInicio, false, rangoFinal);
	}

	/**
	 * 4. Se debe poder consultar si un número está dentro de un rango.
	 * @param valor
	 * @return
	 */
	public boolean valorEstaEnRango(double valor) {
		// Determinar si esta entre medio de los 2 valores del rango.
		if (this.rangoInicio < valor && valor < this.rangoFinal)
			return true;

		// Solo si esta cerrado el Inicio del rango , pregunto por el punto inicial si
		// es igual al valor.
		if (this.inicioAbierto == false && this.rangoInicio == valor)
			return true;

		// Solo si esta cerrado el Final del rango , pregunto por el punto final si es
		// igual al valor.
		if (this.finalAbierto == false && this.rangoFinal == valor)
			return true;

		return false;
	}

	
	/**
	 * 5. Se debe poder consultar si un rango está dentro de un rango
	 * @param rango
	 * @return
	 */
	public boolean rangoNuevoEstaAdentro(Rango rango) {

		if (this.inicioAbierto == true && this.finalAbierto == true && this.rangoInicio < rango.getRangoInicio()
				&& rango.getRangoInicio() < this.rangoFinal && this.rangoInicio < rango.getRangoFinal()
				&& rango.getRangoFinal() < this.rangoFinal)
			return true;

		if (this.inicioAbierto == false && this.finalAbierto == true && this.rangoInicio <= rango.getRangoInicio()
				&& rango.getRangoInicio() < this.rangoFinal && this.rangoInicio < rango.getRangoFinal()
				&& rango.getRangoFinal() < this.rangoFinal)
			return true;

		if (this.inicioAbierto == true && this.finalAbierto == false && this.rangoInicio < rango.getRangoInicio()
				&& rango.getRangoInicio() < this.rangoFinal && this.rangoInicio < rango.getRangoFinal()
				&& rango.getRangoFinal() <= this.rangoFinal)
			return true;

		if (this.inicioAbierto == false && this.finalAbierto == false && this.rangoInicio <= rango.getRangoInicio()
				&& rango.getRangoInicio() < this.rangoFinal && this.rangoInicio < rango.getRangoFinal()
				&& rango.getRangoFinal() <= this.rangoFinal)
			return true;

		return false;
	}

	
	/**
	 * 6. Se debe poder consultar si hay intersección entre dos rangos.

	 * @param segundoRango
	 * @return
	 */
	public boolean hayInterseccion(Rango segundoRango) {

		if (segundoRango.getRangoFinal() < this.rangoInicio)
			return false; // no tienen interseccion, la segunda esta a la izquierda en la recta numerica.
		else if (segundoRango.getRangoFinal() == this.rangoInicio && this.inicioAbierto == false
				&& segundoRango.isFinalAbierto() == false)
			return true; // tienen un punto de interseccion.

		if (this.rangoFinal < segundoRango.getRangoInicio())
			return false; // no tienen interseccion, la segunda esta a la derecha en la recta numerica.
		else if (this.rangoFinal == segundoRango.getRangoInicio() && this.finalAbierto == false
				&& segundoRango.isInicioAbierto() == false)
			return true; // tienen un punto de interseccion.

		return true;
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(finalAbierto, inicioAbierto, rangoFinal, rangoInicio);
	}
	/**
	 * 7. Se debe poder comparar por igualdad los rangos.

	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rango other = (Rango) obj;
		return finalAbierto == other.finalAbierto && inicioAbierto == other.inicioAbierto
				&& Double.doubleToLongBits(rangoFinal) == Double.doubleToLongBits(other.rangoFinal)
				&& Double.doubleToLongBits(rangoInicio) == Double.doubleToLongBits(other.rangoInicio);
	}

//	public boolean esIgual(Rango segundoRango) {
//		if (this.inicioAbierto == segundoRango.isInicioAbierto() && this.finalAbierto == segundoRango.isFinalAbierto()
//				&& this.rangoInicio == segundoRango.getRangoInicio() && this.rangoFinal == segundoRango.getRangoFinal())
//			return true;
//		else
//			return false;
//
//	}
	
	/**
	 * 8. Se debe poder ordenar rangos mediante su inicio. Si empatan, se resuelve
	 * el empate por su fin. Si empatan, los rangos cerrados irán primero que los
	 * abiertos.
	 */
	
	@Override
	public int compareTo(Rango o) {
		// TODO Auto-generated method stub
		
		if (this.rangoInicio > o.rangoInicio)
			return 1;
		else if (this.rangoInicio < o.rangoInicio)
			return -1;
		else {
			// si son iguales
			if (this.rangoFinal > o.rangoFinal)
				return 1;
			else if (this.rangoFinal < o.rangoFinal)
				return -1;
			else {
				if (this.inicioAbierto == false && o.inicioAbierto == true)
					return 1;
				else {
					if (this.inicioAbierto == true && o.inicioAbierto == false)
						return -1;
					else
						return 0;
				}
			}
		}
	}
	
	/**
	 * 9. Se debe poder imprimir un rango en formato cadena de caracteres.

	 */
	
	@Override
	public String toString() {
		String resultado;
		if (this.inicioAbierto == true)
			resultado = "(";
		else
			resultado = "[";

		resultado = resultado.concat(this.rangoInicio + " " + this.rangoFinal);

		if (this.finalAbierto == true)
			resultado = resultado.concat(")");
		else
			resultado = resultado.concat("]");

		return resultado;
	}

	/**
	 * 11. Proporcionar un método estático que devuelva un rango que abarque a todos los otros
		rangos
	 * 
	 * @return
	 */
	public static Rango DevolverRangoTotal() {
		if (inicioAbiertoTOTAL == true && finalAbiertoTOTAL == true)
			return Rango.RangoAbiertoAbierto(rangoIniciaTOTAL, rangoFinalTOTAL);

		if (inicioAbiertoTOTAL == false && finalAbiertoTOTAL == true)
			return Rango.RangoCerradoAbierto(rangoIniciaTOTAL, rangoFinalTOTAL);

		if (inicioAbiertoTOTAL == true && finalAbiertoTOTAL == false)
			return Rango.RangoAbiertoCerrado(rangoIniciaTOTAL, rangoFinalTOTAL);

		return Rango.RangoCerradoCerrado(rangoIniciaTOTAL, rangoFinalTOTAL);

	}

	/**
	 * 12. Se deben poder sumar rangos,
	 *  utilizando el inicio del primero y el fin del segundo.
	 */
	public Rango sumarRangos(Rango ran1, Rango ran2)
	{
		return new Rango(ran1.inicioAbierto,ran1.rangoInicio, ran2.finalAbierto, ran2.rangoFinal);
	}
	
	
	/**13. Se debe poder calcular un rango intersección, que en caso de no existir tal intersección
		retornará (0.0, 0.0)
	 * 
	 * @param Rango2
	 * @return
	 */
	
	public Rango DevolverIntercepsion(Rango Rango2) {
		if (this.hayInterseccion(Rango2) == true) {
			double inicioInterseccion;
			boolean inicioInterAbierto;
			double finalInterseccion;
			boolean finalInterAbierto;

			if (this.rangoInicio > Rango2.getRangoInicio()) {
				inicioInterseccion = this.rangoInicio;
				inicioInterAbierto = this.inicioAbierto;
			} else {
				if (this.rangoInicio < Rango2.getRangoInicio()) {
					inicioInterseccion = Rango2.getRangoInicio();
					inicioInterAbierto = Rango2.isInicioAbierto();
				} else {
					inicioInterseccion = this.rangoInicio;
					// IGUAL
					if (this.inicioAbierto != Rango2.isInicioAbierto())
						inicioInterAbierto = true;
					else
						inicioInterAbierto = false;
				}
			}

			if (this.rangoFinal < Rango2.getRangoFinal()) {
				finalInterseccion = this.rangoFinal;
				finalInterAbierto = this.finalAbierto;
			} else {
				if (this.rangoFinal > Rango2.getRangoFinal()) {
					finalInterseccion = Rango2.getRangoFinal();
					finalInterAbierto = Rango2.isFinalAbierto();
				} else {
					finalInterseccion = this.rangoFinal;
					// IGUAL
					if (this.finalAbierto != Rango2.isFinalAbierto())
						finalInterAbierto = true;
					else
						finalInterAbierto = false;
				}
			}

			return new Rango(inicioInterAbierto, inicioInterseccion, finalInterAbierto, finalInterseccion);

		}

		return Rango.RangoAbiertoAbierto(0, 0);

	}
	
	/**
	 * 14. Se debe poder desplazar un rango con un número escalar
	 * @param escalar
	 * @return
	 */
	
	public Rango desplazarPorEscalar(double escalar) {
		
		return new Rango(this.inicioAbierto,this.rangoInicio + escalar, this.finalAbierto,this.rangoFinal + escalar);
		
//		this.rangoInicio = this.rangoInicio + escalar;
//		this.rangoFinal = this.rangoFinal + escalar;
	}

	

}
