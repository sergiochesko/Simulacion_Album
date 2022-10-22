package usuario;

import static org.junit.Assert.*;

import org.junit.Test;

public class UsuarioTest {

	@Test
	public void testComprarPaquete() {
		Usuario usuario = creaUsuario(638);
		
		usuario.ComprarPaquete();
		
		assertTrue(usuario.CantSobresComprados()==1);
		
	}
	
	@Test
	public void testAgregoUnaFigurita() {
		Usuario usuario = creaUsuario(638);
		
		usuario.AgregoUnaFigurita(0);
		assertTrue(usuario.YaLaTengo(0));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAgregoUnaFiguritaQueYatengo() {
		Usuario usuario = creaUsuario(638);
		
		usuario.AgregoUnaFigurita(10);
		usuario.AgregoUnaFigurita(10);
	}
	
	@Test
	public void testComprarPaqueteRepetidas() {
		Usuario usuario = creaUsuario(5); //album de 5 figuritas
		
		usuario.AgregoUnaFigurita(0);
		usuario.AgregoUnaFigurita(1);
		usuario.AgregoUnaFigurita(2);
		usuario.AgregoUnaFigurita(3);
		usuario.AgregoUnaFigurita(4);
		
		usuario.ComprarPaquete();
		
		System.out.println(usuario.cantRepetidas());
		assertTrue(usuario.cantRepetidas()==5);
		
	}
	
	@Test
	public void testYaLaTengo() {
		Usuario usuario = creaUsuario(638);
		
		usuario.AgregoUnaFigurita(10);
		
		assertTrue(usuario.YaLaTengo(10));
		assertFalse(usuario.YaLaTengo(11));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testYaLaTengoFueraDeRango() {
		Usuario usuario = creaUsuario(638);
		
		usuario.AgregoUnaFigurita(10);
		usuario.YaLaTengo(800);
	}

	@Test
	public void testAlbumCompleto() {
		Usuario usuario = creaUsuario(5); //album de 5 figuritas
		
		usuario.AgregoUnaFigurita(0);
		usuario.AgregoUnaFigurita(1);
		usuario.AgregoUnaFigurita(2);
		usuario.AgregoUnaFigurita(3);
		usuario.AgregoUnaFigurita(4);
		
		assertTrue(usuario.AlbumCompleto());
	}
	
	@Test
	public void testAlbumInCompletoPorUna() {
		Usuario usuario = creaUsuario(5); //album de 5 figuritas
		
		usuario.AgregoUnaFigurita(0);
		usuario.AgregoUnaFigurita(1);
		usuario.AgregoUnaFigurita(2);
		usuario.AgregoUnaFigurita(3);
		
		assertFalse(usuario.AlbumCompleto());
	}
	
	@Test
	public void testMisFaltantes() {
		Usuario usuario = creaUsuario(638); //album 
		
		usuario.AgregoUnaFigurita(0);
		usuario.AgregoUnaFigurita(1);
		usuario.AgregoUnaFigurita(2);
		usuario.AgregoUnaFigurita(3);
		
		assertTrue(usuario.MisFaltantes().size()==638-4);
	}
	

/*


	*/
	
	private Usuario creaUsuario(int tamanoAlbum) {
		return new Usuario(tamanoAlbum);
	}
	
}
