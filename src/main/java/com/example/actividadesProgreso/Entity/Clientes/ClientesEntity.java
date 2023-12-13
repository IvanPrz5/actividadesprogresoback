package com.example.actividadesProgreso.Entity.Clientes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.actividadesProgreso.Entity.Empresas.EmpresasEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Clientes")
public class ClientesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @NonNull
    @Column(length = 1000)
    private String descripcion;
    @Column
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "idEmpresa")
    private EmpresasEntity idEmpresa;
}
