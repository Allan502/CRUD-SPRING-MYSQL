package CRUD_SPRING_MYSQL.service;

import CRUD_SPRING_MYSQL.model.Usuario;
import CRUD_SPRING_MYSQL.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuario(Long idUsuario) {
        return usuarioRepository.findById(idUsuario).orElse(null);
    }


    public Usuario saveUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario usuario) {
        Usuario existingUser = getUsuario(id);

        if (existingUser != null) {
            existingUser.setName(usuario.getName());
            existingUser.setUsername(usuario.getUsername());
            existingUser.setPassword(usuario.getPassword());
            return usuarioRepository.save(existingUser); // Guarda los cambios en la base de datos
        }
        return null;
    }

    public void deleteUsuario(Long id){
         usuarioRepository.deleteById(id);
    }
}
