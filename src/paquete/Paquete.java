package paquete;

import java.util.Random;

public class Paquete {
	
	int[] _figuritas;
	
	public Paquete(int cantFiguritasTotalAlbum) {
		if (cantFiguritasTotalAlbum <= 0) {
			throw new IllegalArgumentException("El album debe ser de almenos una posicion");
		}
		else {
			_figuritas = creaCombinacionRandomFiguritas(cantFiguritasTotalAlbum);
		}
		
		
	}

	private int[] creaCombinacionRandomFiguritas(int cantFiguritasTotalAlbum) {
		// TODO Auto-generated method stub
		Random numAleatorio = new Random();
		int[] figuritas = new int[5];
		for(int i=0; i<5; i++) {
			figuritas[i] = numAleatorio.nextInt(cantFiguritasTotalAlbum);
		}
		return figuritas;
	}
	
	public int[] dameFiguritasEnPaquete() {
		return _figuritas;
	}
	
	
	
	

}
