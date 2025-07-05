package ar.edu.unlam.pb2.cazaRecompensas;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.HashMap;

public class Zona {
    private String nombre;
    private Map<String, IProfugo> profugos;

    public Zona(String nombre, List<IProfugo> listaProfugos) {
        this.nombre = nombre;
        this.profugos = new HashMap<>();
        for (IProfugo p : listaProfugos) {
            profugos.put(p.getNombre(), p);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public Collection<IProfugo> getProfugos() {
        return profugos.values();
    }

    public void eliminarProfugos(Collection<IProfugo> capturados) {
        for (IProfugo p : capturados) {
            profugos.remove(p.getNombre());
        }
    }
}
