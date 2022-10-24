package album;

import java.util.ArrayList;
import java.util.List;

import paquete.Paquete;

public class Album {

	private boolean[] _album;
	private int _cantFiguFaltantes;
	private boolean _completo;
	
	public Album(int tamanoAlbum) {
		if (tamanoAlbum <= 0) {
			throw new IllegalArgumentException("El album debe ser de almenos una posicion");
		}
		else {
		_album = new boolean[tamanoAlbum];
		_completo = false;
		_cantFiguFaltantes=tamanoAlbum;
		}
	}
	
	public boolean YaTieneFigurita(int numFigu) {
		if (numFigu < 0 || numFigu >= _album.length) {
			throw new IllegalArgumentException("Numero de figurita Incorrecto");
		}
		else {
			return _album[numFigu];
		}
	}
	
	
	private void pegaFigurita(int numFigu) {
		if (numFigu < 0 || numFigu >= _album.length) {
			throw new IllegalArgumentException("Numero de figurita Incorrecto");
		}
		else {
			_album[numFigu] = true;
			_cantFiguFaltantes--;
			if(_cantFiguFaltantes==0) {
				_completo=true;
			}
		}
	}
	
	
	/*
	 * procesa la lista de figuritas de un paquete, pega las que no tiene y devuelve una lista de repetidas
	 */
	public List<Integer> procesaFiguritas(Paquete paquete) {
		
		List<Integer> repetidas = new ArrayList<Integer>();
		int[] arrayFigu = paquete.dameFiguritasEnPaquete();
		for(int i=0; i<arrayFigu.length; i++) {
			int figurita = arrayFigu[i];
			if(!YaTieneFigurita(figurita)) {
				pegaFigurita(figurita);
			}
			else {
				repetidas.add(figurita);
			}
		}
		return repetidas;
	}
	
	
	public void procesaUnaFigurita(int numFigu) {
		if(!YaTieneFigurita(numFigu)) {
			pegaFigurita(numFigu);
		}
		else throw new IllegalArgumentException("Ya tiene esa figurita: "+ numFigu);
	}
	
	

	
	public boolean albumCompleto() {
		return _completo;
	}
	
	
	public List<Integer> cualesFaltan(){
		List<Integer> faltantes = new ArrayList<Integer>();
		
		for(int i=0; i<_album.length;i++) {
			if(!YaTieneFigurita(i)) {
				faltantes.add(i);
			}
		}
		
		return faltantes;
		
	}
	
	public String ToString() {
		StringBuilder str = new StringBuilder();
		str.append("[");
		for(int i=0;i < _album.length; i++ ) {
			str.append(_album[i]).append(" , ");
		}
		str.append("]");
		
		return str.toString();
	}

	
}
