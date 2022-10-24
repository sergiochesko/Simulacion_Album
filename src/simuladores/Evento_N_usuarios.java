package simuladores;

import java.util.ArrayList;
import java.util.List;

import usuario.Usuario;

public class Evento_N_usuarios implements Evento{

	public List<Usuario> _ListaUsuarios;
	private int _CantPaquetesComprados;
	private final int _tamanoAlbum;
	private int _cantIntercambios;
	
	public Evento_N_usuarios(int cantUsuarios, int tamanoAlbum) {
		_tamanoAlbum = tamanoAlbum;
		iniciaUsuarios(cantUsuarios);
		_CantPaquetesComprados=0;
		_cantIntercambios=0;
	}
	
	
	@Override
	public int correSimulacion() {
		while(todosCompletaron() == false) {
			
			for(Usuario usuario : _ListaUsuarios) {
				if(usuario.AlbumCompleto()==false) {
					List<Integer> repetidasENPaquete = usuario.ComprarPaquete();
					donoMisRepetidas(repetidasENPaquete, usuario);
					_CantPaquetesComprados++;
				}
			}
		}
		
		return _CantPaquetesComprados;
	}
	

	@Override
	public int getCantRepetidas() {
		int contador = 0;
		for (Usuario usuario : _ListaUsuarios) {
			contador = contador + usuario.cantRepetidas();
		}
		return contador;
	}
	
	@Override
	public int getCantPaquetesComprados() {
		
		return _CantPaquetesComprados;
	}
	
	public int getCantPaquetesCompradosPromUsuario() {
		
		return _CantPaquetesComprados/_ListaUsuarios.size();
	}
	
	public int getCantIntercambios() {
		
		return _cantIntercambios;
	}
	
	
	
	private void iniciaUsuarios(int cantUsuarios) {
		_ListaUsuarios = new ArrayList<Usuario>();
		
		for(int i=0;i<cantUsuarios;i++) {
			_ListaUsuarios.add(new Usuario(_tamanoAlbum));
		}
	}
	
	public boolean todosCompletaron() {
		for(Usuario us : _ListaUsuarios) {
			if(!us.AlbumCompleto()) {
				return false;
			}
		}
		return true;
	}
	
	private void donoMisRepetidas(List<Integer> repetidasENPaquete, Usuario donante) {
		for(Integer numFigu : repetidasENPaquete) {
			
			boolean figuDisponibleParaCambiar= true; //uso como bandera para saber si ya la regaló, para no volver a regalarla
			
			for(Usuario usuarioReceptor : _ListaUsuarios) {
				
				if(figuDisponibleParaCambiar && !usuarioReceptor.YaLaTengo(numFigu) ) {
					usuarioReceptor.AgregoUnaFigurita(numFigu);
					donante.EntregaFiguRepetida(numFigu);
					figuDisponibleParaCambiar=false;
					_cantIntercambios++;
				}
			}
		}
	}
	
}
