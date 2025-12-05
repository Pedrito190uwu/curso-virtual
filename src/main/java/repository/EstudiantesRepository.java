package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.Conexion;
import model.Estudiantes;

public class EstudiantesRepository {

    /* Registrar con varios cursos */
    public boolean insertarEstudianteConCursos(Estudiantes estudiante, List<Integer> listaCursos) {

        if (estudiante.getNomEstudiante() == null || estudiante.getNomEstudiante().trim().isEmpty()) {
            System.out.println("Error: el nombre no puede estar vacío.");
            return false;
        }

        if (estudiante.getEmailEstudiante() == null || estudiante.getEmailEstudiante().trim().isEmpty()) {
            System.out.println("Error: el email no puede estar vacío.");
            return false;
        }

        if (verificarEmailExistente(estudiante.getEmailEstudiante())) {
            System.out.println("Error: ya existe un estudiante con ese email.");
            return false;
        }

        for (Integer idCurso : listaCursos) {
            if (!verificarExistenciaCurso(idCurso)) {
                System.out.println("Error: el curso con ID " + idCurso + " no existe.");
                return false;
            }
        }

        String consultaEstudiante = "INSERT INTO estudiante (nom_estudiante, email_estudiante) VALUES (?, ?)";
        String consultaEstudianteCurso = "INSERT INTO estudiante_curso (id_estudiante, id_curso) VALUES (?, ?)";

        try (Connection conexion = Conexion.getConnection()) {

            PreparedStatement instruccionEstudiante = conexion.prepareStatement(
                    consultaEstudiante, PreparedStatement.RETURN_GENERATED_KEYS);

            instruccionEstudiante.setString(1, estudiante.getNomEstudiante());
            instruccionEstudiante.setString(2, estudiante.getEmailEstudiante());
            instruccionEstudiante.executeUpdate();

            ResultSet clavesGeneradas = instruccionEstudiante.getGeneratedKeys();
            int idGenerado = 0;

            if (clavesGeneradas.next()) {
                idGenerado = clavesGeneradas.getInt(1);
            }

            for (Integer idCurso : listaCursos) {
                PreparedStatement instruccionCursos = conexion.prepareStatement(consultaEstudianteCurso);
                instruccionCursos.setInt(1, idGenerado);
                instruccionCursos.setInt(2, idCurso);
                instruccionCursos.executeUpdate();
            }

            System.out.println("Estudiante registrado con sus cursos correctamente.");
            return true;

        } catch (Exception excepcion) {
            System.out.println("Ocurrió un error al registrar estudiante: " + excepcion);
            return false;
        }
    }

    /* Verificar Email */
    private boolean verificarEmailExistente(String email) {

        String consultaSQL = "SELECT email_estudiante FROM estudiante WHERE email_estudiante = ?";

        try (Connection conexion = Conexion.getConnection();
            PreparedStatement instruccion = conexion.prepareStatement(consultaSQL)) {

            instruccion.setString(1, email);
            ResultSet resultado = instruccion.executeQuery();

            return resultado.next();

        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    /* Verificar existencia de curso */
    private boolean verificarExistenciaCurso(int idCurso) {

        String consultaSQL = "SELECT id_curso FROM curso WHERE id_curso = ?";

        try (Connection conexion = Conexion.getConnection();
            PreparedStatement instruccion = conexion.prepareStatement(consultaSQL)) {

            instruccion.setInt(1, idCurso);
            ResultSet resultado = instruccion.executeQuery();

            return resultado.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public List<Estudiantes> listarEstudiantes() {

        List<Estudiantes> lista = new ArrayList<>();
        String consultaSQL = "SELECT * FROM estudiante";

        try (Connection conexion = Conexion.getConnection();
            Statement declaracion = conexion.createStatement();
            ResultSet resultados = declaracion.executeQuery(consultaSQL)) {

            while (resultados.next()) {

                Estudiantes estudiante = new Estudiantes(
                        resultados.getInt("id_estudiante"),
                        resultados.getString("nom_estudiante"),
                        resultados.getString("email_estudiante")
                );

                lista.add(estudiante);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Estudiantes buscarEstudiantePorId(int idEstudiante) {

        String consultaSQL = "SELECT * FROM estudiante WHERE id_estudiante = ?";
        Estudiantes estudiante = null;

        try (Connection conexion = Conexion.getConnection();
            PreparedStatement instruccion = conexion.prepareStatement(consultaSQL)) {

            instruccion.setInt(1, idEstudiante);
            ResultSet resultado = instruccion.executeQuery();

            if (resultado.next()) {
                estudiante = new Estudiantes(
                        resultado.getInt("id_estudiante"),
                        resultado.getString("nom_estudiante"),
                        resultado.getString("email_estudiante")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return estudiante;
    }

    /* Listar cursos por estudiante */
    public List<Integer> listarCursosDeEstudiante(int idEstudiante) {

        List<Integer> listaCursos = new ArrayList<>();

        String consultaSQL = "SELECT id_curso FROM estudiante_curso WHERE id_estudiante = ?";

        try (Connection conexion = Conexion.getConnection();
            PreparedStatement instruccion = conexion.prepareStatement(consultaSQL)) {

            instruccion.setInt(1, idEstudiante);
            ResultSet resultado = instruccion.executeQuery();

            while (resultado.next()) {
                listaCursos.add(resultado.getInt("id_curso"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaCursos;
    }

    public boolean actualizarEstudiante(int idEstudiante, String nuevoNombre, String nuevoEmail) {

        if (nuevoNombre.trim().isEmpty() || nuevoEmail.trim().isEmpty()) {
            System.out.println("Error: nombre y email no pueden estar vacíos.");
            return false;
        }

        String consultaSQL = "UPDATE estudiante SET nom_estudiante = ?, email_estudiante = ? WHERE id_estudiante = ?";

        try (Connection conexion = Conexion.getConnection();
            PreparedStatement instruccion = conexion.prepareStatement(consultaSQL)) {

            instruccion.setString(1, nuevoNombre);
            instruccion.setString(2, nuevoEmail);
            instruccion.setInt(3, idEstudiante);

            return instruccion.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int eliminarEstudiante(int idEstudiante) {

        String consultaSQL = "DELETE FROM estudiante WHERE id_estudiante = ?";

        try (Connection conexion = Conexion.getConnection();
            PreparedStatement instruccion = conexion.prepareStatement(consultaSQL)) {

            instruccion.setInt(1, idEstudiante);
            return instruccion.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}