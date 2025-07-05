package ar.edu.unlam.pb2.cazaRecompensas;

public abstract class Entrenamiento implements IProfugo {
    protected IProfugo base;

    public Entrenamiento(IProfugo base) {
        this.base = base;
    }

    @Override 
    public String getNombre() { return base.getNombre(); }
    @Override 
    public int getInocencia() { return base.getInocencia(); }
    @Override 
    public int getHabilidad() { return base.getHabilidad(); }
    @Override 
    public boolean esNervioso() { return base.esNervioso(); }

    @Override 
    public void disminuirInocencia(int valor) { base.disminuirInocencia(valor); }
    @Override 
    public void disminuirHabilidad(int valor) { base.disminuirHabilidad(valor); }
    @Override 
    public void volverNervioso() { base.volverNervioso(); }
    @Override 
    public void calmar() { base.calmar(); }
}
