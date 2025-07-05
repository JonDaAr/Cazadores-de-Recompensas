package ar.edu.unlam.pb2.cazaRecompensas;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class AgenciaTest {

    @Test
    public void reportesFuncionanCorrectamente() {
        Cazador urbano = new CazadorUrbano("Pedro", 100);
        Cazador rural = new CazadorRural("Maria", 80);

        IProfugo p1 = new Profugo("A", 10, 30, false);
        IProfugo p2 = new Profugo("B", 5, 90, true);
        IProfugo p3 = new ArtesMarcialesAvanzadas(new Profugo("C", 15, 45, false));

        Zona zona1 = new Zona("Z1", Arrays.asList(p1, p2));
        Zona zona2 = new Zona("Z2", Arrays.asList(p3));

        urbano.capturarEn(zona1);
        rural.capturarEn(zona1);
        rural.capturarEn(zona2);

        Agencia agencia = new Agencia();
        agencia.registrarCazador(urbano);
        agencia.registrarCazador(rural);

        assertEquals(3, agencia.getTodosLosCapturados().size());
        assertEquals(90, (int) agencia.getMasHabilCapturado().get().getHabilidad());
        assertEquals("Maria", agencia.getCazadorConMasCapturas().get().getNombre());
    }
}