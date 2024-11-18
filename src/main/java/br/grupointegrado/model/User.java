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

    @Column(name = "data_nascimento", nullable = false, length = 15)
    private String dataNascimento;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User [idUser=" + idUser + ", nomeUser=" + nomeUser + ", emailUser=" + emailUser + ", dataNascimento="
                + dataNascimento + ", phone=" + phone + "]";
    }

}
