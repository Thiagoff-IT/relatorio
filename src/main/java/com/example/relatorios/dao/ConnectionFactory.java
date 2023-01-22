/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.relatorios.dao;

import com.example.relatorios.dao.expeptions.DAOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author razer
 */
public class ConnectionFactory implements AutoCloseable {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String SERVER = "localhost";
    private static final String BD = "WEBII";
    private static final String USUARIO_BD = "root";
    private static final String SENHA_BD = "root";
    private static final String URL = "jdbc:mysql://" + SERVER + "/" + BD + 
            "?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    
    private Connection con = null;
    public Connection getConnection() throws DAOException {  
       if (con == null) {
          try {
             Class.forName(DRIVER);  
             con = DriverManager.getConnection(URL, USUARIO_BD, SENHA_BD);
          }
          catch(ClassNotFoundException e) {
             throw new DAOException("Driver do banco não encontrado: " + DRIVER, e);
          }
          catch(SQLException e) {
             throw new DAOException("Erro conectando ao BD: " + URL + "/" + USUARIO_BD + "/" + SENHA_BD, e);
          }
      }
      return con;
   } 
    @Override
    public void close() {
        if (con!=null) {
            try { 
                con.close(); 
            }
            catch(Exception e) { 
                System.out.println("Erro fechando a conexão. IGNORADO");
                e.printStackTrace(); 
            }
            finally {
                con = null;
            }
        }
    }
    
}
