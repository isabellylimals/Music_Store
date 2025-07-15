package src.conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static final String URL = "jdbc:mysql://mainline.proxy.rlwy.net:22920/sistema";
    public static final String USER = "root";
    public static final String PASSWORD = "UIsuCmquBSNabpodMaOvgWwDKUISfrxb";

    public static Connection getConexao() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
