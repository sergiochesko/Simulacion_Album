package usuario;

import java.util.ArrayList;
import java.util.List;

import album.Album;
import auxiliares.GeneradorRandom;
import paquete.Paquete;

public class Usuario {


	public Album _album;
	private int[] _repetidas;
	private int _contadorSobresComprados;
	private final int _tamanoAlbum;

	
	public Usuario(int tamanoAlbum) {
		
		_album= new Album(tamanoAlbum);
		_repetidas= new int[tamanoAlbum];
		_contadorSobresComprados=0;
		_tamanoAlbum = tamanoAlbum;
	}
	
	public List<Integer> ComprarPaquete() {
		Paquete.setGenerador(new GeneradorRandom());
		Paquete paquete = new Paquete(_tamanoAlbum);
		List<Integer> repetidasEnPaquete = _album.procesaFiguritas(paquete);
		agregaRepetidasAColeccionDeRepetidas(repetidasEnPaquete);
		_contadorSobresComprados++;
		
		return repetidasEnPaquete;
	}
	
	public void AgregoUnaFigurita(int numFigu) {
		_album.procesaUnaFigurita(numFigu);
	}
	
	private void agregaRepetidasAColeccionDeRepetidas(List<Integer> repetidasEnPaquete) {
		
		for(Integer figu : repetidasEnPaquete) {
			_repetidas[figu] = _repetidas[figu]+1;
		}
	}
	
	public boolean YaLaTengo(int numeFigu) {
		return _album.YaTieneFigurita(numeFigu);
	}
	
	public boolean AlbumCompleto() { // O(1)
		return _album.albumCompleto();
	}
	
	public int[] MisRepetidas() {
		return _repetidas.clone();
	}
	
	public List<Integer> MisFaltantes(){
		return _album.cualesFaltan();
	}
	
	public void EntregaFiguRepetida(int numFigu) {
		_repetidas[numFigu] = _repetidas[numFigu]-1;
	}
	
	public int CantSobresComprados() {
		return _contadorSobresComprados;
	}
	
	public int cantRepetidas() {
		int ret=0;
		for(int i=0;i<_repetidas.length;i++) {
			ret = ret + _repetidas[i];
		}
		return ret;
	}
	
	public ArrayList<Integer> misRepetidas2(){
		ArrayList<Integer> misRepetidas = new ArrayList<Integer>();
		for(int i=0; i<_repetidas.length;i++) {
			if(_repetidas[i]!=0) {
				misRepetidas.add(i);
			}
		}
		return misRepetidas;
	}
	
	public String repetidasToString() {
		StringBuilder str = new StringBuilder();
		str.append("[");
		for(int i=0;i < _repetidas.length; i++ ) {
			str.append(_repetidas[i]).append(" , ");
		}
		str.append("]");
		
		return str.toString();
	}
}
