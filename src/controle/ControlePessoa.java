package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Pessoa;
import util.BancoDados;

public class ControlePessoa {
    
    public static Pessoa BuscarPorID(long idPessoa) {
        try {
            Connection conn = BancoDados.getConexao();
            String sql = "SELECT * FROM tb_pessoa WHERE id = ?; ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, idPessoa);
            final ResultSet rs = ps.executeQuery();

            Pessoa p = new Pessoa();
            if (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setNomeCompleto(rs.getString("nome_completo"));
                p.setCpf(rs.getString("cpf"));
                p.setDataNascimento(rs.getDate("data_nascimento"));
                p.setEscolaridade(rs.getString("escolaridade"));
                p.setSexo(rs.getString("sexo"));
                p.setEmail(rs.getString("email"));
                p.setTelefone(rs.getString("telefone"));
                p.setCep(rs.getString("cep"));
                p.setLogradouro(rs.getString("logradouro"));
                p.setNumero(rs.getString("numero"));
                p.setBairro(rs.getString("bairro"));
                p.setCidade(rs.getString("cidade"));
                p.setUf(rs.getString("uf"));
            }
            return p;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

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
            ps.setString(4, p.getEscolaridade());
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

    public static List<Pessoa> ListarPaciente() {
        try {

            Connection conn = BancoDados.getConexao();
            String sql = "SELECT * FROM tb_pessoa; ";
            PreparedStatement ps = conn.prepareStatement(sql);
            List<Pessoa> lista = new ArrayList();
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Pessoa p = new Pessoa();
                p.setId(rs.getInt("id"));
                p.setNomeCompleto(rs.getString("nome_completo"));
                p.setCpf(rs.getString("cpf"));
                p.setDataNascimento(rs.getDate("data_nascimento"));
                p.setEscolaridade("escolaridade");
                p.setSexo(rs.getString("sexo"));
                p.setEmail(rs.getString("email"));
                p.setTelefone(rs.getString("telefone"));
                p.setCep(rs.getString("cep"));
                p.setLogradouro(rs.getString("logradouro"));
                p.setNumero(rs.getString("numero"));
                p.setBairro(rs.getString("bairro"));
                p.setCidade(rs.getString("cidade"));
                p.setUf(rs.getString("uf"));
                p.setDataCadastro(rs.getTimestamp("data_cadastro"));
                lista.add(p);
            }
            return lista;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static boolean Excluir(long id) {
        try {
            Connection conn = BancoDados.getConexao();
            String sql = "DELETE FROM tb_pessoa  WHERE id = ?; ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            int linhasafetadas = ps.executeUpdate();
            if (linhasafetadas > 0) {
                System.out.println("Apagou!!");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    public static boolean Atualizar(Pessoa cat) {
        try {
            Connection conn = BancoDados.getConexao(); //conectar com o bando de dados e enviar os dados salvos da classe Contato.
            String sql = "UPDATE tb_pessoa ";
            sql += " SET nome_completo = ?, ";
            sql += " cpf = ?, ";
            sql += " data_nascimento = ?, ";
            sql += " escolaridade = ?, ";
            sql += " sexo = ?, ";
            sql += " email = ?, ";
            sql += " telefone = ?, ";
            sql += " cep = ?, ";
            sql += " logradouro = ?, ";
            sql += " numero = ?, ";
            sql += " bairro = ?, ";
            sql += " cidade = ?, ";
            sql += " uf = ? ";
            sql += " WHERE id = ?; ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cat.getNomeCompleto());
            ps.setString(2, cat.getCpf());
            ps.setDate(3, cat.getDataNascimento());
            ps.setString(4, cat.getEscolaridade());
            ps.setString(5, cat.getSexo());
            ps.setString(6, cat.getEmail());
            ps.setString(7, cat.getTelefone());
            ps.setString(8, cat.getCep());
            ps.setString(9, cat.getLogradouro());
            ps.setString(10, cat.getNumero());
            ps.setString(11, cat.getBairro());
            ps.setString(12, cat.getCidade());
            ps.setString(13, cat.getUf());
            ps.setLong(14, cat.getId());
            int linhasafetadas = ps.executeUpdate();
            if (linhasafetadas > 0) {
                System.out.println("atualizou!");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

}
