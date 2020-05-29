package br.com.empresa.softy.resources;

import java.io.InputStream;
import java.net.URI;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.empresa.softy.model.Pessoa;
import br.com.empresa.softy.repository.PessoaRepository;

@RestController
@RequestMapping("pessoas")
public class PessoaResource {

   @Autowired
   private PessoaRepository pessoaRepository;

   @GetMapping
   public List<Pessoa> findAll() {
      return pessoaRepository.findAll();
   }

   @PostMapping
   public ResponseEntity<Pessoa> salvar(@Valid @RequestBody Pessoa pessoa) {

      Pessoa pessoaSalvo = pessoaRepository.save(pessoa);

      URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
            .buildAndExpand(pessoaSalvo.getId()).toUri();

      return ResponseEntity.created(location).body(pessoaSalvo);
   }

   @PutMapping("/{id}")
   public ResponseEntity<Pessoa> atualizar(@PathVariable Integer id, @Valid @RequestBody Pessoa pessoa) {

      try {

         Pessoa pessoaSalvo = clienteRepository.findById(id).get();

         BeanUtils.copyProperties(pessoa, pessoaSalvo, "id");
         clienteRepository.save(clienteSalvo);

         return ResponseEntity.ok(pessoaSalvo);

      } catch (NoSuchElementException e) {
         return ResponseEntity.notFound().build();
      }
   }

   @GetMapping("/{id}")
   public ResponseEntity<Pessoa> findById(@PathVariable Integer id) {
      try {
         Pessoa pessoa = pessoaRepository.findById(id).get();
         return ResponseEntity.ok(pessoa);
      } catch (NoSuchElementException e) {
         return ResponseEntity.notFound().build();
      }
   }

   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void remove(@PathVariable Integer id) {
      pessoaRepository.deleteById(id);
   }

}