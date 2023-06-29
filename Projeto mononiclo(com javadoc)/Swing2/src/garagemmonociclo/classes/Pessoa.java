package garagemmonociclo.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import garagemmonociclo.conexao.Conexao;

/**
 * Classe que representa uma Pessoa.
 *
 * @author Gustavo
 * @author Lud
 * @author Adryano
 */
public class Pessoa {
    private int idPessoa;
    private String nome;
    private String sobrenome;
    private String dataNascimento;

    /**
     * Obtém o ID da pessoa.
     *
     * @return O ID da pessoa.
     */
    public int getId() {
        return idPessoa;
    }

    /**
     * Define o ID da pessoa.
     *
     * @param idPessoa O ID da pessoa.
     */
    public void setId(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    /**
     * Obtém o nome da pessoa.
     *
     * @return O nome da pessoa.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da pessoa.
     *
     * @param nome O nome da pessoa.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o sobrenome da pessoa.
     *
     * @return O sobrenome da pessoa.
     */
    public String getSobrenome() {
        return sobrenome;
    }

    /**
     * Define o sobrenome da pessoa.
     *
     * @param sobrenome O sobrenome da pessoa.
     */
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    /**
     * Obtém a data de nascimento da pessoa.
     *
     * @return A data de nascimento da pessoa.
     */
    public String getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Define a data de nascimento da pessoa.
     *
     * @param dataNascimento A data de nascimento da pessoa.
     */
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * Cadastra uma pessoa.
     *
     * @param idPessoa         O ID da pessoa.
     * @param nome             O nome da pessoa.
     * @param sobrenome        O sobrenome da pessoa.
     * @param dataNascimento   A data de nascimento da pessoa.
     * @return true se o cadastro foi realizado com sucesso, false caso contrário.
     * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
     */
    public boolean cadastrarPessoa(int idPessoa, String nome, String sobrenome, String dataNascimento) throws SQLException {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaBanco();
            String sql = "INSERT INTO pessoas (id_pessoa, nome, sobrenome, datanascimento) Values(?, ?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, idPessoa);
            ps.setString(2, nome);
            ps.setString(3, sobrenome);
            ps.setString(4, dataNascimento);
            int totalRegistrosAfetados = ps.executeUpdate();
            if (totalRegistrosAfetados == 0) {
                System.out.println("Não foi feito o cadastro!");
                return false;
            }
            System.out.println("Cadastro realizado!");
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar Pessoa: " + erro.toString());
            return false;
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }

    /**
     * Consulta uma pessoa pelo ID.
     *
     * @param idPessoa O ID da pessoa a ser consultada.
     * @return true se a consulta foi realizada com sucesso, false caso contrário.
     * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
     */
    public boolean consultarPessoa(int idPessoa) throws SQLException {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaBanco();
            String sql = "SELECT * FROM pessoas where id_pessoa=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, idPessoa);
            ResultSet rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("Pessoa não cadastrada!");
                return false;
            } else {
                while (rs.next()) {
                    this.idPessoa = rs.getInt("id_pessoa");
                    this.nome = rs.getString("nome");
                    this.sobrenome = rs.getString("sobrenome");
                    this.dataNascimento = rs.getString("datanascimento");
                }
                return true;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar pessoa: " + erro.toString());
            return false;
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }

    /**
     * Atualiza os dados de uma pessoa.
     *
     * @param idPessoa         O ID da pessoa.
     * @param nome             O nome da pessoa.
     * @param sobrenome        O sobrenome da pessoa.
     * @param dataNascimento   A data de nascimento da pessoa.
     * @return true se a atualização foi realizada com sucesso, false caso contrário.
     * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
     */
    public boolean atualizarPessoa(int idPessoa, String nome, String sobrenome, String dataNascimento) throws SQLException {
        if (!consultarPessoa(idPessoa))
            return false;
        else {
            Connection conexao = null;
            try {
                conexao = Conexao.conectaBanco();
                String sql = "UPDATE pessoas SET nome=?, sobrenome=?, datanascimento=? WHERE id_pessoa=?";
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setString(1, nome);
                ps.setString(2, sobrenome);
                ps.setString(3, dataNascimento);
                ps.setInt(4, idPessoa);
                int totalRegistrosAfetados = ps.executeUpdate();
                if (totalRegistrosAfetados == 0)
                    System.out.println("Não foi feita a atualização!");
                else
                    System.out.println("Atualização realizada!");
                return true;
            } catch (SQLException erro) {
                System.out.println("Erro ao atualizar pessoa: " + erro.toString());
                return false;
            } finally {
                Conexao.fechaConexao(conexao);
            }
        }
    }

    /**
     * Remove uma pessoa pelo ID.
     *
     * @param idPessoa O ID da pessoa a ser removida.
     * @return true se a remoção foi realizada com sucesso, false caso contrário.
     * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
     */
    public boolean removerPessoa(int idPessoa) throws SQLException {
        if (!consultarPessoa(idPessoa))
            return false;
        else {
            Connection conexao = null;
            try {
                conexao = Conexao.conectaBanco();
                String sql = "DELETE FROM pessoas WHERE id_pessoa=?";
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setInt(1, idPessoa);
                int totalRegistrosAfetados = ps.executeUpdate();
                if (totalRegistrosAfetados == 0)
                    System.out.println("Não foi feita a remoção!");
                else
                    System.out.println("Remoção realizada!");
                return true;
            } catch (SQLException erro) {
                System.out.println("Erro ao remover pessoa: " + erro.toString());
                return false;
            } finally {
                Conexao.fechaConexao(conexao);
            }
        }
    }
}

