package simuladores;

import usuario.Usuario;

public class Evento1Usuario implements Evento{

	private Usuario _usuario;
	private int _CantPaquetesComprados;
	private final int _tamanoAlbum;
	

	public Evento1Usuario(int tamanoAlbum) {
		_tamanoAlbum = tamanoAlbum;
		_usuario = new Usuario(_tamanoAlbum);
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
