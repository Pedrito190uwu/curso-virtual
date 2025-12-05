package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.Conexion;
import model.Categorias;


public class CategoriasRepository {
    public void insertarCategoria(Categorias categoria) {
        String sql = "INSERT INTO CATEGORIA (NOM_CATEGORIA) VALUES (?)";
        try (Connection conexion = Conexion.getConnection()){
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);

            preparedStatement.setString(1, categoria.getNomCategoria());

            preparedStatement.executeUpdate();
            System.out.print("Categoria insertada exitosamente");

        } catch (Exception e) {
            System.out.print("Ocurrio un error" + e);
            e.printStackTrace();
        }
    }

    public List<Categorias> listarCategorias() {
        String sql = "SELECT * FROM CATEGORIA";
        List<Categorias> categorias = new ArrayList<>();

        try (Connection connection = Conexion.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                categorias.add(new Categorias(
                    resultSet.getInt("ID_CATEGORIA"),
                    resultSet.getString("NOM_CATEGORIA")));
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return categorias;
    }

    /* Buscar categoria por Id */
    public Categorias listCategorias(Integer idCategoria) {
    String sql = "SELECT * FROM CATEGORIA WHERE ID_CATEGORIA = ?";

    Categorias categorias = null;

    try (Connection connection = Conexion.getConnection()) {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idCategoria);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            categorias = new Categorias(
                resultSet.getInt("ID_CATEGORIA"),
                resultSet.getString("NOM_CATEGORIA"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

        return categorias;
    }

    /* Actualizar*/
    public Categorias actualizarCategorias(int idCategoria, String nuevoNombre) {
    String sql = "UPDATE CATEGORIA SET NOM_CATEGORIA = ? WHERE ID_CATEGORIA = ?";

    try (Connection connection = Conexion.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

        preparedStatement.setString(1, nuevoNombre);
        preparedStatement.setInt(2, idCategoria);

        int filas = preparedStatement.executeUpdate();

        if (filas > 0) {
            return new Categorias(idCategoria, nuevoNombre);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

        return null;
    }

    /*  Eliminar */
    public int eliminarCategorias(int idCategoria) {
    String sql = "DELETE FROM CATEGORIA WHERE ID_CATEGORIA = ?";

    try (Connection connection = Conexion.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setInt(1, idCategoria);

        return preparedStatement.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
    }
}
