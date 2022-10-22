package usuario;

import java.util.List;

import album.Album;
import paquete.Paquete;

public class Usuario {

	@SuppressWarnings("unused")
	//private String _nombre;
	private Album _album;
	private int[] _repetidas;
	private int _contadorSobresComprados;
	private final int _cantFiguritas;

	
	public Usuario(int cantFiguritas) {
		
		_album= new Album(cantFiguritas);
		_repetidas= new int[cantFiguritas];
		_contadorSobresComprados=0;
		_cantFiguritas = cantFiguritas;
	}
	
	public void ComprarPaquete() {
		Paquete paquete = new Paquete(_cantFiguritas);
		List<Integer> repetidasEnPaquete = _album.procesaFiguritas(paquete);
		agregaRepetidasAColeccionDeRepetidas(repetidasEnPaquete);
		_contadorSobresComprados++;
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
	
	public boolean AlbumCompleto() {
		return _album.albumCompleto();
	}
	
	public int[] MisRepetidas() {
		return _repetidas.clone();
	}
	
	public List<Integer> MisFaltantes(){
		return _album.cualesFaltan();
	}
	
	public void EntregaFiguRepetida(int numFigu) {
		_repetidas[numFigu] = _repetidas[numFigu]--;
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
}
