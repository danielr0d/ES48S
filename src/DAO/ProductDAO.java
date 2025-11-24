package DAO;

import Model.ProductModel;
import Util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    

    public boolean insert(ProductModel product) {
        String sql = "INSERT INTO products (name, price) VALUES (?, ?)";
        
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao inserir produto: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(ProductModel product) {
        String sql = "UPDATE products SET name = ?, price = ? WHERE id = ?";
        
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setInt(3, product.getId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar produto: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao deletar produto: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public ProductModel findById(int id) {
        String sql = "SELECT id, name, price FROM products WHERE id = ?";
        
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ProductModel product = new ProductModel(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price")
                    );
                    return product;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar produto por ID: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }

    public ProductModel findByName(String name) {
        String sql = "SELECT id, name, price FROM products WHERE name = ?";
        
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ProductModel product = new ProductModel(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price")
                    );
                    return product;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar produto por nome: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }

    public List<ProductModel> findAll() {
        List<ProductModel> products = new ArrayList<>();
        String sql = "SELECT id, name, price FROM products ORDER BY id";
        
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                ProductModel product = new ProductModel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price")
                );
                products.add(product);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
            e.printStackTrace();
        }
        
        return products;
    }

    public int count() {
        String sql = "SELECT COUNT(*) FROM products";
        
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao contar produtos: " + e.getMessage());
            e.printStackTrace();
        }
        
        return 0;
    }
}

