package src.conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static final String URL = "jdbc:mysql://mainline.proxy.rlwy.net:22920/sistema";
    public static final String USER = "root";
    public static final String PASSWORD = "UIsuCmquBSNabpodMaOvgWwDKUISfrxb";

    private static Connection conexaoBanco;

    public static Connection getConexao() {
        try {
            if (conexaoBanco == null) {
                conexaoBanco = DriverManager.getConnection(URL, USER, PASSWORD);
                return conexaoBanco;
            } else {
                return conexaoBanco;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
