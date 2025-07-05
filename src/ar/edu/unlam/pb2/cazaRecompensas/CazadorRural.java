package ar.edu.unlam.pb2.cazaRecompensas;

public class CazadorRural extends Cazador {
    public CazadorRural(String nombre, int experiencia) {
        super(nombre, experiencia);
    }


    @Override
    protected boolean condicionCapturaEspecifica(IProfugo p) {
        return !p.esNervioso();
    }

    @Override
    protected void intimidar(IProfugo p) {
        p.disminuirInocencia(2);
        p.calmar();
    }
}