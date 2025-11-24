package DAO;

import Model.SupplierModel;
import Util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SupplierDAO {

    public boolean insert(SupplierModel supplier) {
        String sql = "INSERT INTO suppliers (name, phone) VALUES (?, ?)";
        
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, supplier.getName());
            pstmt.setString(2, supplier.getPhone());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao inserir fornecedor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(SupplierModel supplier) {
        String sql = "UPDATE suppliers SET name = ?, phone = ? WHERE id = ?";
        
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, supplier.getName());
            pstmt.setString(2, supplier.getPhone());
            pstmt.setInt(3, supplier.getId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar fornecedor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM suppliers WHERE id = ?";
        
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao deletar fornecedor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public SupplierModel findById(int id) {
        String sql = "SELECT id, name, phone FROM suppliers WHERE id = ?";
        
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    SupplierModel supplier = new SupplierModel(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("phone")
                    );
                    return supplier;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar fornecedor por ID: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }

    public SupplierModel findByName(String name) {
        String sql = "SELECT id, name, phone FROM suppliers WHERE name = ?";
        
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    SupplierModel supplier = new SupplierModel(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("phone")
                    );
                    return supplier;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar fornecedor por nome: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }

    public List<SupplierModel> findAll() {
        List<SupplierModel> suppliers = new ArrayList<>();
        String sql = "SELECT id, name, phone FROM suppliers ORDER BY id";
        
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                SupplierModel supplier = new SupplierModel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone")
                );
                suppliers.add(supplier);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar fornecedores: " + e.getMessage());
            e.printStackTrace();
        }
        
        return suppliers;
    }

    public int count() {
        String sql = "SELECT COUNT(*) FROM suppliers";
        
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao contar fornecedores: " + e.getMessage());
            e.printStackTrace();
        }
        
        return 0;
    }
}

