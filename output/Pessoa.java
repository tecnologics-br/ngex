package br.com.empresa.softy.model;

@Entity
@Table(name="pessoa")
public class Pessoa {

   @Column(name = "id")
   private Integer id;

   @Column(name = "nome")
   private String nome;

   @Column(name = "data_nascimento")
   private LocalDate dataNascimento;

   @Column(name = "estudante")
   private boolean estudante;

   @Column(name = "salario")
   private Double salario;

   @Column(name = "municipio_id")
   private Municipio municipio;

}