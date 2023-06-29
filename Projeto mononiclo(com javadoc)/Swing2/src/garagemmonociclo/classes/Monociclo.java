package garagemmonociclo.classes;

import java.sql.Connection;
import garagemmonociclo.conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe que representa um Monociclo.
 * 
 * @author Gustavo
 * @author Lud
 * @author Adryano
 */
public class Monociclo {
    private int idProduto;
    private String marca;
    private String modelo;
    private String cor;
    private int anoFabricacao;

    /**
     * Obtém o ID do produto.
     *
     * @return O ID do produto.
     */
    public int getIdProduto() {
        return idProduto;
    }

    /**
     * Define o ID do produto.
     *
     * @param idProduto O ID do produto.
     */
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    /**
     * Obtém a marca do monociclo.
     *
     * @return A marca do monociclo.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Define a marca do monociclo.
     *
     * @param marca A marca do monociclo.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Obtém o modelo do monociclo.
     *
     * @return O modelo do monociclo.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Define o modelo do monociclo.
     *
     * @param modelo O modelo do monociclo.
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtém a cor do monociclo.
     *
     * @return A cor do monociclo.
     */
    public String getCor() {
        return cor;
    }

    /**
     * Define a cor do monociclo.
     *
     * @param cor A cor do monociclo.
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

    /**
     * Obtém o ano de fabricação do monociclo.
     *
     * @return O ano de fabricação do monociclo.
     */
    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    /**
     * Define o ano de fabricação do monociclo.
     *
     * @param anoFabricacao O ano de fabricação do monociclo.
     */
    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    /**
     * Cadastra um monociclo.
     *
     * @param idProduto       O ID do produto.
     * @param marca           A marca do monociclo.
     * @param modelo          O modelo do monociclo.
     * @param cor             A cor do monociclo.
     * @param anoFabricacao   O ano de fabricação do monociclo.
     * @return true se o cadastro foi realizado com sucesso, false caso contrário.
     * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
     */
    public boolean cadastrarMonociclo(int idProduto, String marca, String modelo, String cor, int anoFabricacao) {

        Connection conexao = null;
        try {
            conexao = Conexao.conectaBanco();

            String sql = "INSERT INTO monociclos (id_produto, marca, modelo, cor, anofabricacao) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setInt(1, idProduto);
            ps.setString(2, marca);
            ps.setString(3, modelo);
            ps.setString(4, cor);
            ps.setInt(5, anoFabricacao);
            int totalRegistrosAfetados = ps.executeUpdate();
            if (totalRegistrosAfetados == 0) {
                System.out.println("Nao foi feito o cadastro!");
                return false;
            }
            System.out.println("Cadastro realizado!");
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar monociclo: " + erro.toString());
            return false;
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }

    /**
     * Consulta um monociclo pelo ID do produto.
     *
     * @param idProduto O ID do produto a ser consultado.
     * @return true se o monociclo foi encontrado, false caso contrário.
     * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
     */
    public boolean consultarMonociclo(int idProduto) {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaBanco();
            String sql = "SELECT * FROM monociclos WHERE id_produto=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, idProduto);
            ResultSet rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("Monociclo não cadastrado!");
                return false;
            } else {
                while (rs.next()) {
                    this.idProduto = rs.getInt("id_produto");
                    this.marca = rs.getString("marca");
                    this.modelo = rs.getString("modelo");
                    this.cor = rs.getString("cor");
                    this.anoFabricacao = rs.getInt("anofabricacao");
                }
                return true;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar o monociclo: " + erro.toString());
            return false;
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }

    /**
     * Atualiza os dados de um monociclo.
     *
     * @param idProduto       O ID do produto a ser atualizado.
     * @param marca           A nova marca do monociclo.
     * @param modelo          O novo modelo do monociclo.
     * @param cor             A nova cor do monociclo.
     * @param anoFabricacao   O novo ano de fabricação do monociclo.
     * @return true se a atualização foi realizada com sucesso, false caso contrário.
     * @throws SQLException se ocorrer um erro durante a execução da consulta SQL.
     */
    public boolean atualizarMonociclo(int idProduto, String marca, String modelo, String cor, int anoFabricacao) {
        if (!consultarMonociclo(idProduto))
            return false;
        else {
            Connection conexao = null;
            try {
                conexao = Conexao.conectaBanco();
                String sql = "UPDATE monociclos SET marca=?, modelo=?, cor=?, anofabricacao=? WHERE id_produto=?";
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setString(1, marca);
                ps.setString(2, modelo);
                ps.setString(3, cor);
                ps.setInt(4, anoFabricacao);
                ps.setInt(5, idProduto);
                int totalRegistrosAfetados = ps.executeUpdate();
                if (totalRegistrosAfetados == 0)
                    System.out.println("Não foi feita a atualização!");
                else
                    System.out.println("Atualização realizada!");
                return true;
            } catch (SQLException erro) {
                System.out.println("Erro ao atualizar monociclo: " + erro.toString());
                return false;
            } finally {
                Conexao.fechaConexao(conexao);
            }
        }
    }

    /**
     * Remove um monociclo pelo ID do produto.
     *
     * @param idProduto O ID do produto a ser removido.
     * @return true se a remoção foi realizada com sucesso, false caso contrário.
     * @throws SQLException se ocorrer um erro durante a execução da consulta SQL. 
     */
    public boolean removerMonociclo(int idProduto) {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaBanco();
            String sql = "DELETE FROM monociclos WHERE id_produto=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, idProduto);
            int totalRegistrosAfetados = ps.executeUpdate();
            if (totalRegistrosAfetados == 0) {
                System.out.println("Nao foi feito a remoção");
                return false;
            }
            System.out.println("Remocao realizada!");
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao remover monociclo: " + erro.toString());
            return false;
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }
}
