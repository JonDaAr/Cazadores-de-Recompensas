package ar.edu.unlam.pb2.cazaRecompensas;

public class ArtesMarcialesAvanzadas extends Entrenamiento {
    public ArtesMarcialesAvanzadas(IProfugo base) {
        super(base);
    }

    @Override
    public int getHabilidad() {
        return Math.min(100, base.getHabilidad() * 2);
    }
}
