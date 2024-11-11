package br.grupointegrado.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "nm_user", nullable = false, length = 100)
    private String nomeUser;

    @Column(name = "email_user", nullable = false, unique = true, length = 100)
    private String emailUser;

    @Column(name = "senha_user", nullable = false, length = 255)
    private String senhaUser;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getSenhaUser() {
        return senhaUser;
    }

    public void setSenhaUser(String senhaUser) {
        this.senhaUser = senhaUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", nomeUser='" + nomeUser + '\'' +
                ", emailUser='" + emailUser + '\'' +
                ", senhaUser='" + senhaUser + '\'' +
                '}';
    }
}
