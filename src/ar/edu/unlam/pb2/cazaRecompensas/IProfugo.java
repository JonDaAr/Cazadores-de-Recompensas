package ar.edu.unlam.pb2.cazaRecompensas;

public interface IProfugo {
    String getNombre();
    int getInocencia();
    int getHabilidad();
    boolean esNervioso();

    void disminuirInocencia(int valor);
    void disminuirHabilidad(int valor);
    void volverNervioso();
    void calmar();
}
