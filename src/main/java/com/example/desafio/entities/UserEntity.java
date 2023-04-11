package com.example.desafio.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;


@Entity
@Data
@Table(name = "usuario")
public class UserEntity implements Serializable {
    public UserEntity(Long idUsuario, String nomeUsuario, String email) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idUsuario;

    private String nomeUsuario;

    private String email;

    @OneToMany(cascade = ALL)
    @JoinColumn(name = "id_endereco")
    private List<AddressEntity> listaEndereco;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<AddressEntity> getListaEndereco() {
		return listaEndereco;
	}
    
}
