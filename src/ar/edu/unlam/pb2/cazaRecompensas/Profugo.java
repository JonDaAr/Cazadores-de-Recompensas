package ar.edu.unlam.pb2.cazaRecompensas;

public class Profugo implements IProfugo {
    private String nombre;
    private int inocencia;
    private int habilidad;
    private boolean nervioso;

    public Profugo(String nombre, int inocencia, int habilidad, boolean nervioso) {
    	if (nombre == null || nombre.isBlank()) {
            throw new ProfugoInvalidoException("El nombre del prófugo no puede ser nulo ni vacío.");
        }
        if (inocencia < 0 || habilidad < 0) {
            throw new ProfugoInvalidoException("La inocencia y habilidad deben ser valores positivos.");
        }
    	this.nombre = nombre;
        this.inocencia = Math.max(0, inocencia);
        this.habilidad = Math.min(100, Math.max(1, habilidad));
        this.nervioso = nervioso;
    }

    @Override 
    public String getNombre() { return nombre; }
    @Override 
    public int getInocencia() { return inocencia; }
    @Override 
    public int getHabilidad() { return habilidad; }
    @Override 
    public boolean esNervioso() { return nervioso; }

    @Override 
    public void disminuirInocencia(int valor) {
        inocencia = Math.max(0, inocencia - valor);
    }

    @Override 
    public void disminuirHabilidad(int valor) {
        habilidad = Math.max(0, habilidad - valor);
    }

    @Override 
    public void volverNervioso() {
        nervioso = true;
    }

    @Override 
    public void calmar() {
        nervioso = false;
    }
}
