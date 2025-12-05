package model;

public class Cursos {
    private int idCurso;
    private String titulo;
    private String descripcion;
    private int fkidCategoria;


    public Cursos (String titulo, String descripcion, int fkidCategoria){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fkidCategoria = fkidCategoria;
    }

    public Cursos (int idCurso, String titulo, String descripcion, int fkidCategoria){
        this.idCurso = idCurso;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fkidCategoria = fkidCategoria;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getFkidCategoria() {
        return fkidCategoria;
    }
}
