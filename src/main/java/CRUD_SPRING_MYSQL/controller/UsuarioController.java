package CRUD_SPRING_MYSQL.controller;

import CRUD_SPRING_MYSQL.model.Usuario;
import CRUD_SPRING_MYSQL.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getUsuarios(Usuario usuario){
        List<Usuario> usuarios = userService.getUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable Long id){
        Usuario usuario = userService.getUsuario(id);
        if (usuario != null){
            ResponseEntity.ok(usuario);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
    }

    @PostMapping
    public ResponseEntity<?> postUsuario(@RequestBody Usuario usuario){
        Usuario usuario1 = userService.saveUsuario(usuario);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();
        return ResponseEntity.created(location).body(usuario1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario updatedUser = userService.updateUsuario(id, usuario);
        if(updatedUser != null){
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id){
        userService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
