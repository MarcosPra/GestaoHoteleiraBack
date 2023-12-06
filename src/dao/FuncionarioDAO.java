package dao;
import dao.connection.ConexaoMySQL;
import model.Funcionario;
import model.Pessoa;


import java.sql.*;
import java.util.ArrayList;
    public class FuncionarioDAO {

        public Boolean inserir(Funcionario funcionario) {

                try {
                    // Inserir na tabela pessoa
                    String sqlPessoa = "INSERT INTO pessoa (nome_completo, data_nascimento, documento, pais, estado, cidade) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparacaoPessoa = ConexaoMySQL.get().prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS);
                    preparacaoPessoa.setString(1, funcionario.getNomeCompleto());
                    preparacaoPessoa.setDate(2, Date.valueOf(funcionario.getDataNascimento()));
                    preparacaoPessoa.setString(3, funcionario.getDocumento());
                    preparacaoPessoa.setString(4, funcionario.getPais());
                    preparacaoPessoa.setString(5, funcionario.getEstado());
                    preparacaoPessoa.setString(6, funcionario.getCidade());

                    int contLinhasAfetadasPessoa = preparacaoPessoa.executeUpdate();
                    ResultSet generatedKeys = preparacaoPessoa.getGeneratedKeys();
                    Long idPessoa = null;
                    if (generatedKeys.next()) {
                        idPessoa = generatedKeys.getLong(1);
                    }

                    if (idPessoa != null) {
                        String sqlFuncionario = "INSERT INTO funcionario (id_pessoa, cargo, salario) VALUES (?, ?, ?)";
                        PreparedStatement preparacaoFuncionario = ConexaoMySQL.get().prepareStatement(sqlFuncionario);
                        preparacaoFuncionario.setLong(1, idPessoa);
                        preparacaoFuncionario.setString(2, funcionario.getCargo());
                        preparacaoFuncionario.setDouble(3, funcionario.getSalario());

                        int contLinhasAfetadasFuncionario = preparacaoFuncionario.executeUpdate();

                        return contLinhasAfetadasFuncionario > 0;
                    } else {
                        // Tratar o caso em que não foi possível obter o ID da pessoa
                        return false;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }


        public ArrayList<Funcionario> selecionarTodos() {
            try {
                String sql = "SELECT f.id, p.nome_completo, p.data_nascimento, p.documento, p.pais, p.estado, p.cidade, " +
                        "f.cargo, f.salario FROM funcionario f " +
                        "JOIN pessoa p ON f.id_pessoa = p.id ORDER BY f.id";
                Statement stmt = ConexaoMySQL.get().createStatement();
                ResultSet resultado = stmt.executeQuery(sql);

                ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
                while (resultado.next()) {
                    Funcionario funcionario = new Funcionario(
                            resultado.getLong("id"),
                            resultado.getString("nome_completo"),
                            resultado.getDate("data_nascimento").toLocalDate(),
                            resultado.getString("documento"),
                            resultado.getString("pais"),
                            resultado.getString("estado"),
                            resultado.getString("cidade"),
                            resultado.getString("cargo"),
                            resultado.getDouble("salario")
                    );
                    listaFuncionarios.add(funcionario);
                }

                return listaFuncionarios;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        public Boolean atualizar(Funcionario funcionario) {
            try {
                String sql = "UPDATE funcionario SET " +
                        "cargo = ?, " +
                        "salario = ? " +
                        "WHERE id = ?";
                PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
                preparacao.setString(1, funcionario.getCargo());
                preparacao.setDouble(2, funcionario.getSalario());
                preparacao.setLong(3, funcionario.getId());

                int contLinhasAfetadas = preparacao.executeUpdate();

                return contLinhasAfetadas > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public Boolean deletar(Long id) {
            try {
                String sql = "DELETE FROM funcionario WHERE id = ?";
                PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
                preparacao.setLong(1, id);

                int contLinhasAfetadas = preparacao.executeUpdate();
                return contLinhasAfetadas > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }


        public Funcionario selecionarPorId(Long id) {
            try {
                String sql = "SELECT * FROM funcionario WHERE id = ?";
                PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
                preparacao.setLong(1, id);

                ResultSet resultado = preparacao.executeQuery();
                if (resultado.next()) {
                    Funcionario funcionario = new Funcionario(
                            resultado.getString("nome_completo"),
                            resultado.getDate("data_nascimento").toLocalDate(),
                            resultado.getString("documento"),
                            resultado.getString("pais"),
                            resultado.getString("estado"),
                            resultado.getString("cidade"),
                            resultado.getString("cargo"),
                            resultado.getDouble("salario")
                    );
                    return funcionario;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
