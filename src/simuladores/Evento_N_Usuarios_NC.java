package simuladores;

import java.util.ArrayList;
import java.util.List;

import auxiliares.Tupla;
import usuario.Usuario;

public class Evento_N_Usuarios_NC implements Evento{

	public List<Usuario> _ListaUsuarios;
	private int _CantPaquetesComprados;
	private final int _tamanoAlbum;
	private int _cantIntercambios;
	
	public Evento_N_Usuarios_NC(int cantUsuarios, int tamanoAlbum) {
		_tamanoAlbum = tamanoAlbum;
		iniciaUsuarios(cantUsuarios);
		_CantPaquetesComprados=0;
		_cantIntercambios=0;
	}
	
	
	@Override
	public int correSimulacion() {
		while(todosCompletaron() == false) {
			
			for(Usuario usuario : _ListaUsuarios) { //O(n4)
				if(usuario.AlbumCompleto()==false) {
					usuario.ComprarPaquete();
					_CantPaquetesComprados++;
					IntercambioRepetidas(usuario); //O(n3)
				}
			}
		}
		
		return _CantPaquetesComprados;
	}
	



	public void IntercambioRepetidas(Usuario usuario1) { 

		
		int[] repetidasU1 = usuario1.MisRepetidas();
		
		for(int i=0; i < repetidasU1.length; i++) if(repetidasU1[i] > 0) { 

			int numFigu1 = i;

			Tupla tupla = UsuarioParaCambiarla2( numFigu1, usuario1);

			if(tupla!=null) { // O(1)
				
				Usuario usuario2 = tupla._usuario;
				Integer numFigu2 = tupla._numero;
				intercambian(usuario1, usuario2, numFigu1, numFigu2); 
				
				repetidasU1[numFigu1] = repetidasU1[numFigu1] -1;
				
			}
		}
	}
	
	private Tupla UsuarioParaCambiarla2(int numFigu, Usuario usuario1) {
		
		for(Usuario usuario2 : _ListaUsuarios) if(!usuario2.AlbumCompleto() && !usuario2.YaLaTengo(numFigu)) { 
			
			int[] repetidasU2 = usuario2.MisRepetidas();
			
			for(int numOtraFigu=1; numOtraFigu<repetidasU2.length; numOtraFigu++) {
				
				if(!usuario1.YaLaTengo(numOtraFigu) && repetidasU2[numOtraFigu] > 0) {
					return new Tupla(numOtraFigu,usuario2);
				}
				
			}
		}
		return null;
	}
	


	private void intercambian(Usuario usuario1, Usuario usuario2, int i, int j) {

		usuario1.EntregaFiguRepetida(i);
		usuario2.EntregaFiguRepetida(j);
		usuario1.AgregoUnaFigurita(j);
		usuario2.AgregoUnaFigurita(i);
		_cantIntercambios++;
		
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
	

}
