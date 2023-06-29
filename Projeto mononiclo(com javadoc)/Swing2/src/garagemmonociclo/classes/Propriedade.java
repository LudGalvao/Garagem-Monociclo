package garagemmonociclo.classes;

import java.sql.Connection;
import garagemmonociclo.conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe que representa a Propriedade de um produto em uma garagem.
 * @author Gustavo
 * @author Lud
 * @author Adryano
 */
public class Propriedade {
    private int idPropriedade;
    private int idPessoa;
    private int idProduto;
    private String dataAquisicao;
    private String descricao;

    /**
     * Obtém o ID da propriedade.
     *
     * @return o ID da propriedade.
     */
    public int getIdPropriedade() {
        return idPropriedade;
    }

    /**
     * Define o ID da propriedade.
     *
     * @param idPropriedade o ID da propriedade.
     */
    public void setIdPropriedade(int idPropriedade) {
        this.idPropriedade = idPropriedade;
    }

    /**
     * Obtém o ID da pessoa.
     *
     * @return o ID da pessoa.
     */
    public int getIdPessoa() {
        return idPessoa;
    }

    /**
     * Define o ID da pessoa.
     *
     * @param idPessoa o ID da pessoa.
     */
    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    /**
     * Obtém o ID do produto.
     *
     * @return o ID do produto.
     */
    public int getIdProduto() {
        return idProduto;
    }

    /**
     * Define o ID do produto.
     *
     * @param idProduto o ID do produto.
     */
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    /**
     * Obtém a data de aquisição.
     *
     * @return a data de aquisição.
     */
    public String getDataAquisicao() {
        return dataAquisicao;
    }

    /**
     * Define a data de aquisição.
     *
     * @param dataAquisicao a data de aquisição.
     */
    public void setDataAquisicao(String dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    /**
     * Obtém a descrição da propriedade.
     *
     * @return a descrição da propriedade.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição da propriedade.
     *
     * @param descricao a descrição da propriedade.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Cadastra uma propriedade.
     *
     * @param idPropriedade  O ID da propriedade.
     * @param idPessoa       O ID da pessoa.
     * @param idProduto      O ID do produto.
     * @param dataAquisicao  A data de aquisição.
     * @param descricao      A descrição da propriedade.
     * @return true se o cadastro foi realizado com sucesso, false caso contrário.
     * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
     */
    public boolean cadastrarPropriedade(int idPropriedade, int idPessoa, int idProduto, String dataAquisicao, String descricao) {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaBanco();
            String sql = "INSERT INTO propriedades (id_propriedade, id_pessoa, id_produto, dataaquisicao, descricao) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, idPropriedade);
            ps.setInt(2, idPessoa);
            ps.setInt(3, idProduto);
            ps.setString(4, dataAquisicao);
            ps.setString(5, descricao);
            int totalRegistrosAfetados = ps.executeUpdate();
            if (totalRegistrosAfetados == 0) {
                System.out.println("Não foi feito o cadastro!");
                return false;
            }
            System.out.println("Cadastro realizado!");
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar propriedade: " + erro.toString());
            return false;
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }

    /**
     * Consulta uma propriedade pelo seu ID.
     *
     * @param idPropriedade O ID da propriedade a ser consultada.
     * @return true se a propriedade foi encontrada, false caso contrário.
     * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
     */
    public boolean consultarPropriedade(int idPropriedade) {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaBanco();
            String sql = "SELECT * FROM propriedades WHERE id_propriedade=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, idPropriedade);
            ResultSet rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("A propriedade não foi encontrada!");
                return false;
            } else {
                while (rs.next()) {
                    this.idPropriedade = rs.getInt("id_propriedade");
                    this.idPessoa = rs.getInt("id_pessoa");
                    this.idProduto = rs.getInt("id_produto");
                    this.dataAquisicao = rs.getString("dataaquisicao");
                    this.descricao = rs.getString("descricao");
                }
                return true;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar propriedade: " + erro.toString());
            return false;
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }

    /**
     * Atualiza uma propriedade.
     *
     * @param idPropriedade  O ID da propriedade a ser atualizada.
     * @param idPessoa       O novo ID da pessoa.
     * @param idProduto      O novo ID do produto.
     * @param dataAquisicao  A nova data de aquisição.
     * @param descricao      A nova descrição da propriedade.
     * @return true se a atualização foi realizada com sucesso, false caso contrário.
     * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
     */
    public boolean atualizarPropriedade(int idPropriedade, int idPessoa, int idProduto, String dataAquisicao, String descricao) {
        if (!consultarPropriedade(idPropriedade))
            return false;
        else {
            Connection conexao = null;
            try {
                conexao = Conexao.conectaBanco();
                String sql = "UPDATE propriedades SET id_pessoa =?, id_produto =?, dataaquisicao =?, descricao =? WHERE id_propriedade =?";
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setInt(1, idPessoa);
                ps.setInt(2, idPropriedade);
                ps.setString(3, dataAquisicao);
                ps.setString(4, descricao);
                ps.setInt(5, idPropriedade);
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
     * Remove uma propriedade pelo seu ID.
     *
     * @param idPropriedade O ID da propriedade a ser removida.
     * @return true se a remoção foi realizada com sucesso, false caso contrário.
     * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
     */
    public boolean removerPropriedade(int idPropriedade) {
        if (!consultarPropriedade(idPropriedade))
            return false;
        else {
            Connection conexao = null;
            try {
                conexao = Conexao.conectaBanco();
                String sql = "DELETE FROM propriedades WHERE id_propriedade=?";
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setInt(1, idPropriedade);
                int totalRegistrosAfetados = ps.executeUpdate();
                if (totalRegistrosAfetados == 0)
                    System.out.println("Não foi feita a remoção!");
                else
                    System.out.println("Remoção realizada!");
                return true;
            } catch (SQLException erro) {
                System.out.println("Erro ao remover propriedade: " + erro.toString());
                return false;
            } finally {
                Conexao.fechaConexao(conexao);
            }
        }
    }

    /**
     * Consulta as propriedades de uma pessoa pelo seu ID.
     *
     * @param idPessoa O ID da pessoa.
     * @return true se a pessoa possui propriedades, false caso contrário.
     * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
     */
    public boolean consultarPropriedadePessoa(int idPessoa) {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaBanco();
            String sql = "SELECT * FROM propriedades WHERE id_pessoa=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, idPessoa);
            ResultSet rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("A pessoa não possui propriedades!");
                return false;
            } else {
                while (rs.next()) {
                    this.idPropriedade = rs.getInt("id_propriedade");
                    this.idPessoa = rs.getInt("id_pessoa");
                    this.idProduto = rs.getInt("id_produto");
                    this.dataAquisicao = rs.getString("dataaquisicao");
                    this.descricao = rs.getString("descricao");
                }
                return true;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar propriedade: " + erro.toString());
            return false;
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }
}

