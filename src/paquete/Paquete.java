package paquete;

import java.util.Random;

public class Paquete {
	
	int[] _figuritas;
	
	public Paquete(int tamanoAlbum) {
		if (tamanoAlbum <= 0) {
			throw new IllegalArgumentException("El album debe ser de almenos una posicion");
		}
		else {
			_figuritas = creaCombinacionRandomFiguritas(tamanoAlbum);
		}
		
		
	}

	private int[] creaCombinacionRandomFiguritas(int tamanoAlbum) {
		// TODO Auto-generated method stub
		Random numAleatorio = new Random();
		int[] figuritas = new int[5];
		for(int i=0; i<5; i++) {
			figuritas[i] = numAleatorio.nextInt(tamanoAlbum);
		}
		return figuritas;
	}
	
	public int[] dameFiguritasEnPaquete() {
		return _figuritas;
	}
	
	
	
	

}
