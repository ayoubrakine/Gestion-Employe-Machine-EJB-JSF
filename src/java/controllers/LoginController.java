package controllers;

import entities.Admin;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import services.LoginFacade;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController {

    @EJB
    private LoginFacade loginFacade;

    private String username;
    private String password;

    // Getters and Setters for username and password

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    private boolean showInvalidCredentials; // Nouvel attribut pour afficher le message d'erreur

    public boolean isShowInvalidCredentials() {
        return showInvalidCredentials;
    }

    public void setShowInvalidCredentials(boolean showInvalidCredentials) {
        this.showInvalidCredentials = showInvalidCredentials;
    }

    public String login() {
        Admin admin = loginFacade.authenticate(username, password);
        if (admin != null) {
            redirectTo("/faces/employe/List.xhtml");
            return null;
        } else {
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Échec de la connexion", "Vérifiez vos identifiants"));
            showInvalidCredentials = true; // Affiche le message d'erreur
            return null;
        }
    }

    private void redirectTo(String path) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(externalContext.getRequestContextPath() + path);
        } catch (IOException e) {
            // Handle the exception (e.g., log it)
        }
    }
}
