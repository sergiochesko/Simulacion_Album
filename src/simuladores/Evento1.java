package simuladores;

import usuario.Usuario;

public class Evento1 {

	private Usuario _usuario;
	private int _CantPaquetesComprados;
	private final int _cantFiguritas;
	
	/*
	 * cantFiguritas
	 */
	public Evento1(int cantFiguritas) {
		_cantFiguritas = cantFiguritas;
		_usuario = new Usuario(_cantFiguritas);
		_CantPaquetesComprados=0;
	}
	
	public int correSimulacion() {
		while (!_usuario.AlbumCompleto()) {
			_usuario.ComprarPaquete();
		}
		_CantPaquetesComprados = _usuario.CantSobresComprados();
		return _CantPaquetesComprados;
	}
	
	public int getCantRepetidas() {
		return _usuario.cantRepetidas();
	}
	
	public boolean getAlbumCompleto() {
		return _usuario.AlbumCompleto();
	}
	
	public int getCantPaquetesComprados() {
		return _CantPaquetesComprados;
	}
	
	
}
