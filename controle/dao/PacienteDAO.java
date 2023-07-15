package controle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import controle.Paciente;
import utilitarios.Conectar;

public class PacienteDAO {
    public void cadastrar(Paciente p) {
        Connection con = Conectar.getConectar();
        String sql = "INSERT INTO paciente (nome, cpf, email, sexo, telefone, datanasc) VALUES(?,?,?,?,?,?)";

        try (PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setString(1, p.getNome());
            smt.setString(2, p.getCpf());
            smt.setString(3, p.getEmail());
            smt.setString(4, p.getSexo());
            smt.setString(5, p.getTelefone());
            smt.setString(6, p.getDatanasc());
            smt.executeUpdate();
            smt.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Conexao estabelecida com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error no cadastro!");
        }
        ;
    }

    public void atualizar(Paciente p) {

        Connection con = Conectar.getConectar();
        String sql = "UPDATE paciente SET nome = ?, cpf = ?, email = ?, sexo = ?, telefone = ?, datanasc = ? WHERE id_paciente = ?";
        try (PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setString(1, p.getNome());
            smt.setString(2, p.getCpf());
            smt.setString(3, p.getEmail());
            smt.setString(4, p.getSexo());
            smt.setString(5, p.getTelefone());
            smt.setString(6, p.getDatanasc());
            smt.setInt(7, p.getId_paciente());
            smt.executeUpdate();
            smt.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Atualizacao feita com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error ao atualizar");
        }
    }

    public void excluir(Paciente p) {
        Connection con = Conectar.getConectar();
        String sql = "DELETAR FROM paciente WHERE id_paciente = ?";
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja escluir o paciente " + p.getNome() + "?", "EXCLUIR",
                JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            try (PreparedStatement smt = con.prepareStatement(sql)) {
                smt.setInt(1, p.getId_paciente());
                smt.executeUpdate();
                smt.close();
                con.close();
                JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error ao tentar excluir");
            }
        }

    }

    public List<Paciente> listarTodos() {
        Connection con = Conectar.getConectar();
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM paciente ORDER BY nome";
        try (PreparedStatement smt = con.prepareStatement(sql)) {
            ResultSet resultado = smt.executeQuery();
            while (resultado.next()) {
                Paciente p = new Paciente();
                p.setId_paciente(resultado.getInt("id_paciente"));
                p.setNome(resultado.getString("nome"));
                p.setCpf(resultado.getString("cpf"));
                p.setDatanasc(resultado.getString("datanasc"));
                p.setSexo(resultado.getString("sexo"));
                p.setTelefone(resultado.getString("telefone"));
                lista.add(p);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error ao tentar buscar o registro");
        }
        return listarTodos();

    }
}
