package ar.edu.unlam.pb2.cazaRecompensas;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class SistemaTest {

	@Test
	public void noSeDuplicanCapturasSiVuelveACapturarZonaSinCambios() {
		List<IProfugo> fugitivos = Arrays.asList(new Profugo("Ringo", 10, 30, false),
				new Profugo("Bingo", 5, 20, false));

		Zona zona = new Zona("Puerto", fugitivos);
		CazadorUrbano cazador = new CazadorUrbano("Joe", 50);

		cazador.capturarEn(zona);
		int capturasIniciales = cazador.getCapturados().size();

		Zona zonaRepetida = new Zona("Puerto",
				Arrays.asList(new Profugo("Ringo", 10, 30, false), new Profugo("Bingo", 5, 20, false)));
		cazador.capturarEn(zonaRepetida);

		int capturasFinales = cazador.getCapturados().size();
		assertEquals(2, capturasIniciales);
		assertEquals(capturasIniciales, capturasFinales);
	}

	@Test
	public void artesMarcialesDuplicaHabilidadPeroNoExcede100() {
		IProfugo p = new Profugo("Luciano", 25, 55, true);
		IProfugo entrenado = new ArtesMarcialesAvanzadas(p);

		assertEquals(100, entrenado.getHabilidad());
	}

	@Test
	public void proteccionLegalNuncaBajaInocenciaDebajoDe40() {
		IProfugo p = new Profugo("Sofia", 35, 50, false);
		IProfugo entrenado = new ProteccionLegal(p);

		entrenado.disminuirInocencia(10);
		assertEquals(40, entrenado.getInocencia());
	}

	@Test
	public void entrenamientoEliteSiempreCalmado() {
		IProfugo p = new Profugo("Marcela", 20, 40, true);
		IProfugo elite = new EntrenamientoElite(p);

		elite.volverNervioso();
		assertFalse(elite.esNervioso());
	}

	@Test
	public void combinacionDeEntrenamientos() {
		IProfugo p = new Profugo("Leo", 30, 20, true);
		IProfugo entrenado = new ProteccionLegal(new EntrenamientoElite(new ArtesMarcialesAvanzadas(p)));

		assertEquals(40, entrenado.getInocencia());
		assertFalse(entrenado.esNervioso());
		assertEquals(40, entrenado.getHabilidad());
	}

	@Test
	public void capturasYIntimidacionesFuncionanCorrectamente() {
		IProfugo p1 = new Profugo("Juan", 10, 60, true);
		IProfugo p2 = new Profugo("Carlos", 5, 40, false);

		Zona zona = new Zona("Centro", Arrays.asList(p1, p2));
		Cazador urbano = new CazadorUrbano("Pedro", 50);

		urbano.capturarEn(zona);

		assertEquals(1, urbano.getCapturados().size());
		assertTrue(zona.getProfugos().contains(p1));
		assertFalse(p1.esNervioso());
		assertTrue(p1.getInocencia() < 10);
	}

	@Test
	public void cazadorAumentaExperienciaCorrectamente() {
		IProfugo p1 = new Profugo("Juan", 10, 30, false);
		IProfugo p2 = new Profugo("Ana", 15, 10, false);

		Zona zona = new Zona("Norte", Arrays.asList(p1, p2));
		Cazador urbano = new CazadorUrbano("Luis", 20);

		urbano.capturarEn(zona);

		assertTrue(urbano.getExperiencia() > 20);
	}

}
