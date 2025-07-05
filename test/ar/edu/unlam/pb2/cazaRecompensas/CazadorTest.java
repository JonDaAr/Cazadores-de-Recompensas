package ar.edu.unlam.pb2.cazaRecompensas;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class CazadorTest {

    private Profugo profugoNerviosoHabil = new Profugo("Juan", 10, 60, true);
    private Profugo profugoTranquiloInhabil = new Profugo("Carlos", 10, 40, false);
    private Profugo profugoIntermedio = new Profugo("Ana", 20, 45, false);

    private Zona zona;

    @Before
    public void setUp() {
        zona = new Zona("Zona 1", Arrays.asList(
                profugoNerviosoHabil,
                profugoTranquiloInhabil,
                profugoIntermedio
        ));
    }
    
    @Test
    public void noSeDuplicanCapturasSiVuelveACapturarZonaSinCambios() {
        CazadorSigiloso cazador = new CazadorSigiloso("Sigilo", 50);

        IProfugo p1 = new Profugo("A", 10, 30, false);
        IProfugo p2 = new Profugo("B", 5, 20, false);

        Zona zona = new Zona("Zona1", Arrays.asList(p1, p2));

        cazador.capturarEn(zona);

        Zona zonaRepetida = new Zona("Zona1", Arrays.asList(
            new Profugo("A", 10, 30, false),
            new Profugo("B", 5, 20, false)
        ));
        cazador.capturarEn(zonaRepetida);

        System.out.println("Capturados: " + cazador.getCapturados().stream()
            .map(IProfugo::getNombre)
            .toList());

        assertEquals(2, cazador.getCapturados().size());
    }


    @Test
    public void cazadorUrbanoCapturaCorrectamenteYIntimidaAlResto() {
        IProfugo profugoTranquiloInhabil = new Profugo("Carlos", 10, 40, false);
        IProfugo profugoIntermedio = new Profugo("Ana", 35, 45, false);
        IProfugo profugoNerviosoHabil = new Profugo("Juan", 8, 60, true);

        Zona zona = new Zona("ZonaTest", Arrays.asList(profugoTranquiloInhabil, profugoIntermedio, profugoNerviosoHabil));
        CazadorUrbano cazador = new CazadorUrbano("Urbano", 30);

        cazador.capturarEn(zona);

        assertTrue(cazador.getCapturados().contains(profugoTranquiloInhabil));
        assertEquals(1, cazador.getCapturados().size());

        assertFalse(zona.getProfugos().contains(profugoTranquiloInhabil));

        assertEquals(6, profugoNerviosoHabil.getInocencia());
        assertFalse(profugoNerviosoHabil.esNervioso());

        assertEquals(33, profugoIntermedio.getInocencia());
        assertFalse(profugoIntermedio.esNervioso());

        assertEquals(77, cazador.getExperiencia());
    }



    @Test
    public void cazadorRuralCapturaLosNoNerviososYCalmaAlResto() {
        CazadorRural cazador = new CazadorRural("Rural", 30);
        cazador.capturarEn(zona);

        assertTrue(cazador.getCapturados().stream()
            .anyMatch(p -> p.getNombre().equals("Ana")));
        assertTrue(cazador.getCapturados().stream()
            .anyMatch(p -> p.getNombre().equals("Carlos")));
        assertEquals(2, cazador.getCapturados().size());

        assertFalse(profugoNerviosoHabil.esNervioso());

        assertEquals(30 + 64, cazador.getExperiencia());
    }


    @Test
    public void cazadorSigilosoSoloCapturaConHabilidadMenorA50() {
        IProfugo profugoTranquiloInhabil = new Profugo("Carlos", 10, 30, false); 
        IProfugo profugoIntermedio = new Profugo("Ana", 8, 45, false);
        IProfugo profugoNerviosoHabil = new Profugo("Juan", 10, 60, true);

        Zona zona = new Zona("Ciudad", Arrays.asList(
            profugoTranquiloInhabil,
            profugoIntermedio,
            profugoNerviosoHabil
        ));

        CazadorSigiloso cazador = new CazadorSigiloso("Sigiloso", 30);
        cazador.capturarEn(zona);

        System.out.println("Capturados:");
        cazador.getCapturados().forEach(p -> System.out.println("→ " + p.getNombre()));

        assertTrue(cazador.getCapturados().stream()
            .anyMatch(p -> p.getNombre().equals("Carlos")));

        assertTrue(cazador.getCapturados().stream()
            .anyMatch(p -> p.getNombre().equals("Ana")));

        assertEquals(2, cazador.getCapturados().size());

       
        assertEquals(8, profugoNerviosoHabil.getInocencia());
        assertEquals(60, profugoNerviosoHabil.getHabilidad());

        assertEquals(94, cazador.getExperiencia());
    }


    @Test
    public void noCapturaPorInocenciaAltaAunqueCumplaCondicionEspecifica() {
        Profugo dificil = new Profugo("Imposible", 100, 10, false);
        Zona zonaDificil = new Zona("Zona 2", Arrays.asList(dificil));
        CazadorUrbano cazador = new CazadorUrbano("Pepe", 20);

        cazador.capturarEn(zonaDificil);

        assertTrue(zonaDificil.getProfugos().contains(dificil));
        assertFalse(cazador.getCapturados().contains(dificil));

        assertEquals(98, dificil.getInocencia());
    }


}
