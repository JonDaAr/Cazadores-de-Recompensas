package ar.edu.unlam.pb2.cazaRecompensas;

import static org.junit.Assert.*;

import org.junit.Test;

public class EntrenamientoTest {

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
}