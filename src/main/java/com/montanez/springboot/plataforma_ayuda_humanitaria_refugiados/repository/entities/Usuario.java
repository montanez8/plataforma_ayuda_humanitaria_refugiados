//package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.repository.entities;
//
//import java.util.Collection;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
//import jakarta.persistence.ManyToMany;
//
//@Entity
//public class Usuario implements UserDetails {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
////    private String nombre;
//    private String apellidos;
//    private String email;
//    private String password;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
//    private Set<Rol> roles;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roles.stream()
//                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
//                .collect(Collectors.toList());
//    }
//}
