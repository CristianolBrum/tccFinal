/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import bean.LoginBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Aluno;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import model.Documentos;

/**
 *
 * @author Estudos
 */
public class AlunoBD {
    
    public void inserirAluno(Aluno objAluno) throws  SQLException{
         
        try {
            PreparedStatement pstm;
            Connection con = ConnBD.getConnection();

            con.setAutoCommit(false);
            pstm = con.prepareStatement("INSERT INTO pessoa (tipo, nome, telefone,cpf,rga,email,senha) VALUES (?, ?, ?, ?, ?, ?, ?)");
            pstm.setInt(1, objAluno.getTipo());
            pstm.setString(2, objAluno.getNome());            
            pstm.setString(3, objAluno.getTelefone());
            pstm.setString(4, objAluno.getCpf());
            pstm.setString(5, objAluno.getRga());
            pstm.setString(6, objAluno.getEmail());
            pstm.setString(7, objAluno.getSenha());
            pstm.executeUpdate();
            
            
            con.commit();
            
    
    }catch (SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    public void removerAluno(Aluno objAluno) throws  SQLException{
        String sql = "DELETE FROM pessoa WHERE id = ?";
        PreparedStatement ps = ConnBD.getConnection().prepareStatement(sql);
        ps.setInt(1, objAluno.getId());
        ps.execute();
        ps.close();
        ConnBD.getConnection().close();
     }
    
    public void alterarAluno(Aluno objAluno) throws  SQLException{
        String sql = "UPDATE pessoa SET tipo = ? ,nome = ?, telefone = ?, cpf = ?, rga = ?, email = ?, senha = ? WHERE id = ?";
        PreparedStatement ps = ConnBD.getConnection().prepareStatement(sql);
        ps.setInt(1, objAluno.getTipo());
        ps.setString(2, objAluno.getNome());
        ps.setString(3, objAluno.getTelefone());
        ps.setString(4, objAluno.getCpf());
        ps.setString(5, objAluno.getRga());
        ps.setString(6, objAluno.getEmail());
        ps.setString(7, objAluno.getSenha());
        ps.setInt(8, objAluno.getId());
        ps.execute();
        ps.close();
        ConnBD.getConnection().close();
    }
    public List<Aluno> listarAluno() throws  SQLException{
    List<Aluno> lstAluno = new ArrayList<Aluno>();       
    String sql = "SELECT * FROM pessoa";
    PreparedStatement ps = ConnBD.getConnection().prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
    while(rs.next()){
        Aluno objAluno = new Aluno();
        objAluno.setId(rs.getInt("id"));
        objAluno.setId(rs.getInt("tipo"));
        objAluno.setNome(rs.getString("nome"));
        objAluno.setTelefone(rs.getString("telefone"));
        objAluno.setCpf(rs.getString("cpf"));
        objAluno.setRga(rs.getString("rga"));
        objAluno.setEmail(rs.getString("email"));
        objAluno.setSenha(rs.getString("senha"));
        lstAluno.add(objAluno);
    }
    
    ps.close();
    ConnBD.getConnection().close();
    return lstAluno;
    }
    
     public List<Aluno> listarUsuario(int id) throws  SQLException{
    List<Aluno> lstAluno = new ArrayList<Aluno>();  
     
    String sql = "SELECT * FROM pessoa where id = ?";
    PreparedStatement ps = ConnBD.getConnection().prepareStatement(sql);
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
   
         
    while(rs.next()){
        Aluno objAluno = new Aluno();
        objAluno.setId(rs.getInt("id"));
        objAluno.setId(rs.getInt("tipo"));
        objAluno.setNome(rs.getString("nome"));
        objAluno.setTelefone(rs.getString("telefone"));
        objAluno.setCpf(rs.getString("cpf"));
        objAluno.setRga(rs.getString("rga"));
        objAluno.setEmail(rs.getString("email"));
        objAluno.setSenha(rs.getString("senha"));
        lstAluno.add(objAluno);
    }
    
    ps.close();
    ConnBD.getConnection().close();
    return lstAluno;
    }

   
  
    
}
