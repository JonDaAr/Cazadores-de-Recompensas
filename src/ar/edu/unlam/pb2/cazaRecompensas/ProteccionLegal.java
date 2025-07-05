package ar.edu.unlam.pb2.cazaRecompensas;

public class ProteccionLegal extends Entrenamiento {
    public ProteccionLegal(IProfugo base) {
        super(base);
    }

    @Override
    public int getInocencia() {
        return Math.max(40, base.getInocencia());
    }

    @Override
    public void disminuirInocencia(int valor) {
        base.disminuirInocencia(valor);
    }
}
