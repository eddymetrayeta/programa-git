package conexion1jdbc;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion1JDBC {

    public static void main(String[] args) {
        // Configuración de la conexión
        String usuario = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/productos";

        Connection conexion = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            // Conexión a la base de datos
            conexion = DriverManager.getConnection(url, usuario, password);
            System.out.println("¡Conexión exitosa a la base de datos!");

            // El Statement
            statement = conexion.createStatement();

            // Insertar datos en la tabla datos_productos
            String sqlInsert = "INSERT INTO datos_productos (ID_producto, Nombre, Categoria, Precio) " +
                               "VALUES (12, 'pizza 3 quesos', 'pizza', 14)";
            statement.executeUpdate(sqlInsert);
            System.out.println("¡Datos insertados correctamente!");

            // Actualizar datos en la tabla datos_productos
            String sqlUpdate = "UPDATE datos_productos SET Nombre = 'hamburguesa sencilla', Categoria = 'Hamburguesa' " +
                               "WHERE ID_producto = 3";
            statement.executeUpdate(sqlUpdate);
            System.out.println("¡Datos actualizados correctamente!");

            // Eliminar datos de la tabla productos
            String sqlDelete = "DELETE FROM datos_productos WHERE ID_producto = 2";
            statement.executeUpdate(sqlDelete);
            System.out.println("¡Producto eliminado correctamente!");

            // Seleccionar todos los datos de la tabla datos_productos
            String sqlSelect = "SELECT * FROM datos_productos";
            rs = statement.executeQuery(sqlSelect);

            // Procesar y mostrar los resultados
            System.out.println("Listado de productos:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("ID_producto") + 
                                   ", Nombre: " + rs.getString("Nombre") + 
                                   ", Categoría: " + rs.getString("Categoria") + 
                                   ", Precio: " + rs.getDouble("Precio"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Conexion1JDBC.class.getName()).log(Level.SEVERE, "Error en la conexión", ex);
        } finally {
            // Cerrar los recursos
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion1JDBC.class.getName()).log(Level.SEVERE, "Error al cerrar recursos", ex);
            }
        }
    }
}
