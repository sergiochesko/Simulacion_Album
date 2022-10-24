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
			
			for(Usuario usuario : _ListaUsuarios) {
				if(usuario.AlbumCompleto()==false) {
					usuario.ComprarPaquete();
					_CantPaquetesComprados++;
					IntercambioRepetidas(usuario);
				}
			}
		}
		
		return _CantPaquetesComprados;
	}
	



	public void IntercambioRepetidas(Usuario usuario1) {
		System.out.println(usuario1.toString());
		System.out.println("paq: "+usuario1.CantSobresComprados());
		System.out.println("fal: "+usuario1.MisFaltantes().size());
		System.out.println("rep: "+usuario1.cantRepetidas());
		System.out.println(usuario1._album.ToString());
		System.out.println(usuario1.repetidasToString());
		
		
		int[] repetidasU1 = usuario1.MisRepetidas();
		
	//	List<Integer> faltantesU1 = usuario1.MisFaltantes();
		
		for(int i=0; i < repetidasU1.length; i++) if(repetidasU1[i] > 0) {
			//System.out.println("*****intento cambiar********");
			int numFigu1 = i;
			List<Integer> faltantesU1 = usuario1.MisFaltantes();
			Tupla tupla = UsuarioParaCambiarla( numFigu1, faltantesU1);
			//System.out.println("buscaTupla--------------------*******************");
			if(tupla!=null) {
				System.out.println("-----------------------------------------------------------------------------------------");
				Usuario usuario2 = tupla._usuario;
				Integer numFigu2 = tupla._numero;
				intercambian(usuario1, usuario2, numFigu1, numFigu2);
				
				System.out.println(usuario1._album.ToString());
				System.out.println(usuario2._album.ToString());
				System.out.println(usuario1.repetidasToString());
				System.out.println(usuario2.repetidasToString());
				repetidasU1[numFigu1] = repetidasU1[numFigu1] -1;
				
			}
			else {
				//System.out.println("no encuentro usuario");
			}
		}
		
	}
	


	private void intercambian(Usuario usuario1, Usuario usuario2, int i, int j) {
		System.out.println("intercambia "+i);
		System.out.println("intercambia "+j);
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
	
	private Tupla UsuarioParaCambiarla(int numFigu, List<Integer> faltantesU1) {
		
		for(Usuario usuario2 : _ListaUsuarios) if(!usuario2.YaLaTengo(numFigu)) {
			
			for(Integer numFaltatne : faltantesU1) {
				
				if (usuario2.MisRepetidas()[numFaltatne]>0) {
					
					return new Tupla(numFaltatne,usuario2);
				}
			}
		}
		return null;
	}
}
