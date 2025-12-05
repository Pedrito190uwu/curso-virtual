package model;

public class Estudiantes {
    private int idEstudiante;
    private String nomEstudiante;
    private String emailEstudiante;
    

    public Estudiantes (String nomEstudiante, String emailEstudiante){
        this.nomEstudiante = nomEstudiante;
        this.emailEstudiante = emailEstudiante;
    }

    public Estudiantes (int idEstudiante, String nomEstudiante, String emailEstudiante){
        this.idEstudiante = idEstudiante;
        this.nomEstudiante = nomEstudiante;
        this.emailEstudiante = emailEstudiante;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public String getNomEstudiante() {
        return nomEstudiante;
    }

    public String getEmailEstudiante() {
        return emailEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public void setNomEstudiante(String nomEstudiante) {
        this.nomEstudiante = nomEstudiante;
    }

    public void setEmailEstudiante(String emailEstudiante) {
        this.emailEstudiante = emailEstudiante;
    }
}
