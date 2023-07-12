package utilitarios;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class Conectar {
    private static final String usuario = "wander";
    private static final String senha = "11080801";
    private static final String url = "jdbc:mysql://localhost/clinica";

            public static Connection getConectar() {
                Connection conec = null;

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conec = DriverManager.getConnection(url, usuario, senha);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error ao conectar o banco de dados "+ ex.getMessage());
                }

                return conec;
            };
}