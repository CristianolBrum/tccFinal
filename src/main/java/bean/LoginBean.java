package bean;

import bd.ConnBD;
import java.io.Serializable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Simple login bean.
 *
 * @author itcuties
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 7765876811740798583L;

    private boolean loggedIn;
    private String password;
    private String uname;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    @ManagedProperty(value = "#{navigationBean}")
    private NavigationBean navigationBean;

    
    public String doLogin() throws SQLException {
        PreparedStatement ps = null;
        try {
            Connection con2 = ConnBD.getConnection();
            ps = con2.prepareStatement(
                    "select id, nome, senha from pessoa where nome= ? and senha= ? ");
            ps.setString(1, this.uname);
            ps.setString(2, this.password);
            

            ResultSet rs = ps.executeQuery();
            if (rs.next()) // found
            {
                this.id = rs.getInt(1);
                this.uname = rs.getString(2);
                this.password = rs.getString(3);
                loggedIn = true;
                return navigationBean.redirectToWelcome();
            } else {
                return navigationBean.toLogin();
            }
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return navigationBean.toLogin();
        } finally {
            ConnBD.getConnection().close();
        }
    }

    /**
     * Logout operation.
     *
     * @return
     */
    public String doLogout() {
        // Set the paremeter indicating that user is logged in to false
        loggedIn = false;

        // Set logout message
        FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return navigationBean.redirectToLogin();
    }

	// ------------------------------
    // Getters & Setters 
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void setNavigationBean(NavigationBean navigationBean) {
        this.navigationBean = navigationBean;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
