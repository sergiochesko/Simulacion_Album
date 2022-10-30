package paquete;

import java.util.Random;

import auxiliares.Generador;

public class Paquete {
	
	int[] _figuritas;
	private static Generador _random;
	
	public Paquete(int tamanoAlbum) {
		if (tamanoAlbum <= 0) {
			throw new IllegalArgumentException("El album debe ser de almenos una posicion");
		}
		else {
			_figuritas = creaCombinacionRandomFiguritas(tamanoAlbum);
		}
		
		
	}

	private int[] creaCombinacionRandomFiguritas(int tamanoAlbum) {
		
		//Random numAleatorio = new Random();
		int[] figuritas = new int[5];
		for(int i=0; i<5; i++) {
			figuritas[i] = _random.nextInt(tamanoAlbum);
		}
		return figuritas;
	}
	
	public int[] dameFiguritasEnPaquete() {
		return _figuritas;
	}
	
	// Setter para el generador
	public static void setGenerador(Generador generador)
	{
		_random = generador;
	}

	
	
	

}
