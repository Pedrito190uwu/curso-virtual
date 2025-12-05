package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.Conexion;
import model.Cursos;

public class CursosRepository {
    public void insertarCurso(Cursos cursos) {
        String sql = "INSERT INTO CURSO (TITULO, DESCRIPCION, FK_ID_CATEGORIA) VALUES (?, ?, ?)";
        try (Connection conexion = Conexion.getConnection()){
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);

            preparedStatement.setString(1, cursos.getTitulo());
            preparedStatement.setString(2, cursos.getDescripcion());
            preparedStatement.setInt(3, cursos.getFkidCategoria());

            preparedStatement.executeUpdate();
            System.out.print("Curso insertada exitosamente");

        } catch (Exception e) {
            System.out.print("Ocurrio un error" + e);
            e.printStackTrace();
        }
    }

    public List<Cursos> listarCursos() {
        String sql = "SELECT * FROM CURSO";
        List<Cursos> cursos = new ArrayList<>();

        try (Connection connection = Conexion.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                cursos.add(new Cursos(
                    resultSet.getInt("ID_CURSO"),
                    resultSet.getString("TITULO"),
                    resultSet.getString("DESCRIPCION"),
                    resultSet.getInt("FK_ID_CATEGORIA")));
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return cursos;
    }

    /* Buscar categoria por Id */
    public Cursos listCursos(Integer idCurso) {
    String sql = "SELECT * FROM CURSO WHERE ID_CURSO = ?";

    Cursos cursos = null;

    try (Connection connection = Conexion.getConnection()) {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idCurso);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            cursos = new Cursos(
                resultSet.getInt("ID_CURSO"),
                    resultSet.getString("TITULO"),
                    resultSet.getString("DESCRIPCION"),
                    resultSet.getInt("FK_ID_CATEGORIA"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

        return cursos;
    }

    /* Actualizar*/
    public Cursos actualizarCursos(int idCurso, String nuevoTitulo, String nuevaDescripcion, int fkIdCategoriaNuevo) {
    String sql = "UPDATE CURSO SET TITULO = ?, DESCRIPCION = ?, FK_ID_CATEGORIA = ? WHERE ID_CURSO = ?";

    try (Connection connection = Conexion.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

        preparedStatement.setString(1, nuevoTitulo);
        preparedStatement.setString(2, nuevaDescripcion);
        preparedStatement.setInt(3, fkIdCategoriaNuevo);
        preparedStatement.setInt(4, idCurso);

        int filas = preparedStatement.executeUpdate();

        if (filas > 0) {
            return new Cursos(idCurso, nuevoTitulo, nuevaDescripcion, fkIdCategoriaNuevo);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

        return null;
    }

    /*  Eliminar */
    public int eliminarCursos(int idCurso) {
    String sql = "DELETE FROM CURSO WHERE ID_CURSO = ?";

    try (Connection connection = Conexion.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setInt(1, idCurso);

        return preparedStatement.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
    }
}
