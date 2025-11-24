package DAO;

import Model.UserModel;
import Util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public boolean insert(UserModel user) {
        String sql = "INSERT INTO users (username, email) VALUES (?, ?)";
        
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao inserir usuário: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(UserModel user) {
        String sql = "UPDATE users SET username = ?, email = ? WHERE id = ?";
        
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setInt(3, user.getId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao deletar usuário: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public UserModel findById(int id) {
        String sql = "SELECT id, username, email FROM users WHERE id = ?";
        
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    UserModel user = new UserModel(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("email")
                    );
                    return user;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário por ID: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }

    public UserModel findByUsername(String username) {
        String sql = "SELECT id, username, email FROM users WHERE username = ?";
        
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    UserModel user = new UserModel(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("email")
                    );
                    return user;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário por username: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }

    public List<UserModel> findAll() {
        List<UserModel> users = new ArrayList<>();
        String sql = "SELECT id, username, email FROM users ORDER BY id";
        
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                UserModel user = new UserModel(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email")
                );
                users.add(user);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
            e.printStackTrace();
        }
        
        return users;
    }

    public int count() {
        String sql = "SELECT COUNT(*) FROM users";
        
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao contar usuários: " + e.getMessage());
            e.printStackTrace();
        }
        
        return 0;
    }
}

