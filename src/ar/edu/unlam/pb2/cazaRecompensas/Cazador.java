package ar.edu.unlam.pb2.cazaRecompensas;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collection;
import java.util.HashMap;

public abstract class Cazador {
    protected String nombre;
    protected int experiencia;
    protected Map<String, IProfugo> capturados = new HashMap<>();

    public Cazador(String nombre, int experiencia) {
        this.nombre = nombre;
        this.experiencia = experiencia;
    }

    public String getNombre() {
        return nombre;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public Collection<IProfugo> getCapturados() {
        return capturados.values();
    }

    public void capturarEn(Zona zona) {
    	if (zona == null) {
            throw new ZonaInvalidaException("La zona no puede ser nula.");
        }
        if (zona.getProfugos() == null || zona.getProfugos().isEmpty()) {
            throw new ZonaSinProfugosException("La zona no contiene prófugos.");
        }
        List<IProfugo> intimidados = new ArrayList<>();
        List<IProfugo> capturadosEnZona = new ArrayList<>();

        for (IProfugo p : new ArrayList<>(zona.getProfugos())) {
            if (puedeCapturar(p)) {
                capturados.put(p.getNombre(), p);
                capturadosEnZona.add(p);
            } else {
                intimidar(p);
                intimidados.add(p);
            }
        }

        zona.eliminarProfugos(capturadosEnZona);
        sumarExperiencia(intimidados, capturadosEnZona.size());
    }

    protected void sumarExperiencia(List<IProfugo> intimidados, int capturados) {
        int minHabilidad = intimidados.stream()
                .mapToInt(IProfugo::getHabilidad)
                .min().orElse(0);
        experiencia += minHabilidad + 2 * capturados;
    }

    protected boolean puedeCapturar(IProfugo p) {
        return experiencia > p.getInocencia() && condicionCapturaEspecifica(p);
    }

    protected abstract boolean condicionCapturaEspecifica(IProfugo p);
    protected abstract void intimidar(IProfugo p);
}
