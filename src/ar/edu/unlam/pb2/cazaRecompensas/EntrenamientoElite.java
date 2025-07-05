package ar.edu.unlam.pb2.cazaRecompensas;

public class EntrenamientoElite extends Entrenamiento {
    public EntrenamientoElite(IProfugo base) {
        super(base);
    }

    @Override
    public boolean esNervioso() {
        return false;
    }

    @Override
    public void volverNervioso() {

    }
}
