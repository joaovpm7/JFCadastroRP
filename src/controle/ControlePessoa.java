package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Pessoa;
import util.BancoDados;

public class ControlePessoa {

    
    public static boolean Cadastrar(Pessoa p) {
        try {
            Connection conn = BancoDados.getConexao(); //conectar com o bando de dados e enviar os dados salvos da classe Contato.
            String sql = "INSERT INTO tb_pessoa";
            sql += " (nome_completo, cpf, data_nascimento, escolaridade, sexo, email, telefone, cep, logradouro, numero, bairro, cidade, uf)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getNomeCompleto());
            ps.setString(2, p.getCpf());
            ps.setDate(3, p.getDataNascimento());
            ps.setString(4, p.getEscolariodade());
            ps.setString(5, p.getSexo());
            ps.setString(6, p.getEmail());
            ps.setString(7, p.getTelefone());
            ps.setString(8, p.getCep());
            ps.setString(9, p.getLogradouro());
            ps.setString(10, p.getNumero());
            ps.setString(11, p.getBairro());
            ps.setString(12, p.getCidade());
            ps.setString(13, p.getUf());
            int linhasafetadas = ps.executeUpdate();
            if (linhasafetadas > 0) {
                final ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    final int lastId = rs.getInt(1);
                    System.out.println("O numero do id é:"
                            + lastId);
                    return true;

                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
}
