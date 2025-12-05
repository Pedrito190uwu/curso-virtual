package model;

public class Categorias {
    private int idCategoria;
    private String nomCategoria;


    public Categorias (String nomCategoria){
        this.nomCategoria = nomCategoria;
    }

    public Categorias (int idCategoria, String nomCategoria){
        this.idCategoria = idCategoria;
        this.nomCategoria = nomCategoria;
    }


    public int getIdCategoria() {
        return idCategoria;
    }

    public String getNomCategoria() {
        return nomCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}

