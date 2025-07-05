package ar.edu.unlam.pb2.cazaRecompensas;

import java.util.*;

public class Agencia {
    private List<Cazador> cazadores = new ArrayList<>();

    public void registrarCazador(Cazador cazador) {
        if (cazador == null) {
            throw new IllegalArgumentException("El cazador no puede ser nulo.");
        }
        if (cazadores.contains(cazador)) {
            throw new CazadorDuplicadoException("Ya existe un cazador con ese nombre.");
        }
        cazadores.add(cazador);
    }


    public List<IProfugo> getTodosLosCapturados() {
        Map<String, IProfugo> unicos = new HashMap<>();
        for (Cazador c : cazadores) {
            for (IProfugo p : c.getCapturados()) {
                unicos.put(p.getNombre(), p);
            }
        }
        return new ArrayList<>(unicos.values());
    }

    public Optional<IProfugo> getMasHabilCapturado() {
        return getTodosLosCapturados().stream()
                .max(Comparator.comparingInt(IProfugo::getHabilidad));
    }

    public Optional<Cazador> getCazadorConMasCapturas() {
        return cazadores.stream()
                .max(Comparator.comparingInt(c -> c.getCapturados().size()));
    }
}
