package com.juanma.profit.persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericDAO<T, ID> {
    protected static final String URL = "jdbc:postgresql://localhost:5432/Profit_DB";
    protected static final String USER = "postgres";
    protected static final String PASSWORD = "lolateamo123";
    
    protected abstract String getInsertQuery();
    protected abstract String getSelectAllQuery();
    protected abstract String getDeleteQuery();
    protected abstract void setInsertParameters(PreparedStatement pstmt, T entity) throws SQLException;
    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;
    
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    protected void handleSQLException(SQLException e) {
        System.err.println("Error de base de datos:");
        System.err.println("Mensaje: " + e.getMessage());
        System.err.println("Código error: " + e.getErrorCode());
        System.err.println("Estado SQL: " + e.getSQLState());
    }
    
    public void insert(T entity) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(getInsertQuery())) {
            
            setInsertParameters(pstmt, entity);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    
    public List<T> findAll() {
        List<T> entities = new ArrayList<>();
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(getSelectAllQuery())) {
            
            while (rs.next()) {
                entities.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return entities;
    }
    
    public void delete(ID id) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(getDeleteQuery())) {
            
            pstmt.setObject(1, id);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    
    protected void executeInTransaction(TransactionOperation operation) {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            operation.execute(conn);
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    handleSQLException(ex);
                }
            }
            handleSQLException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    handleSQLException(e);
                }
            }
        }
    }
    
    @FunctionalInterface
    public interface TransactionOperation {
        void execute(Connection conn) throws SQLException;
    }
}