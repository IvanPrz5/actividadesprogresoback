package com.example.actividadesProgreso.Service.Usuarios;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.actividadesProgreso.Entity.Actividades.Interface.UsuariosInterface;
import com.example.actividadesProgreso.Entity.Usuarios.UsuariosEntity;
import com.example.actividadesProgreso.Entity.Usuarios.DTO.UsuariosDTO;
import com.example.actividadesProgreso.Entity.Usuarios.Interface.EmpresasInterface;
import com.example.actividadesProgreso.Entity.Usuarios.Interface.IdAndDescripcionInterface;
import com.example.actividadesProgreso.Repository.Usuarios.UsuariosRepository;
import com.example.actividadesProgreso.Service.Areas.AreasService;
import com.example.actividadesProgreso.Service.Empresas.EmpresasService;

import org.springframework.core.io.ByteArrayResource;

@Service
public class UsuariosService {

    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    RolesService rolesService;

    @Autowired
    AreasService areasService;

    @Autowired
    EmpresasService empresasService;

    public String subirImagen(MultipartFile multipartFile, UsuariosEntity doc) {
            try {
                if (!multipartFile.isEmpty()) {
                String rutaCarpeta = System.getProperty("user.dir")
                        + "\\src\\main\\java\\com\\example\\actividadesProgreso\\Archivos\\ImagenesPerfil\\" +
                        doc.getId();
                FileUtils.forceMkdir(new File(rutaCarpeta));

                Optional<UsuariosEntity> usuarioId = usuariosRepository.findById(doc.getId());
                UsuariosEntity usuarios = usuarioId.get();
                byte[] bytes = multipartFile.getBytes();
                Path path = Paths.get(rutaCarpeta + "/" + multipartFile.getOriginalFilename());
                Files.write(path, bytes);
                
                usuarios.setIcon("http://189.250.143.56:8080/actividadesProgreso-0.0.1-SNAPSHOT/Usuarios/img/" + doc.getId());
                // usuarios.setIcon("http://172.16.50.244:8080/actividadesProgreso-0.0.1-SNAPSHOT/Usuarios/img/" + doc.getId());
                usuarios.setRutaAvatar(path.toString());
                usuariosRepository.save(usuarios);

                return "OK";
            } 
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    public ResponseEntity<ByteArrayResource> getImagen(Long id) {
        try {
            Optional<UsuariosEntity> usuarios = usuariosRepository.findById(id);
            Path file = Paths.get(usuarios.get().getRutaAvatar());
            byte[] bytes = Files.readAllBytes(file);

            ByteArrayResource byteArrayResource = new ByteArrayResource(bytes);

            return ResponseEntity.ok()
                    .contentLength(bytes.length)
                    .contentType(MediaType.parseMediaType("image/jpeg"))
                    .body(byteArrayResource);
        } catch (Exception e) {
            return null;
        }
    }

    public List<UsuariosEntity> insertIntoUserRoles(Long idUser, Long idRole) {
        try {
            return usuariosRepository.insertIntoUserRoles(idUser, idRole);
        } catch (Exception e) {
            return null;
        }
    }

    public List<UsuariosInterface> getUsuariosByEmpresa(Long id) {
        try {
            return usuariosRepository.findByEmpresaQuery(id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<UsuariosDTO> getByIdAndStatus(Long id, Boolean status) {
        List<UsuariosEntity> usuariosEntity = usuariosRepository.findByIdAndStatus(id, status);
        List<IdAndDescripcionInterface> roles = rolesService.getRolesByIdUsuario(id);
        List<IdAndDescripcionInterface> areas = areasService.getAreasByIdUsuario(id);
        List<EmpresasInterface> empresas = empresasService.getEmpresasByIdUsuario(id);
        List<UsuariosDTO> usuariosDTO = usuariosEntity.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
        usuariosDTO.get(0).setRoles(roles);
        usuariosDTO.get(0).setAreas(areas);
        usuariosDTO.get(0).setEmpresas(empresas);
        return usuariosDTO;
    }

    public List<UsuariosDTO> getById(Long id) {
        List<UsuariosEntity> usuariosEntity = usuariosRepository.findByIdAndStatus(id, true);
        return usuariosEntity.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    public UsuariosDTO entityToDTO(UsuariosEntity usuario) {
        return new UsuariosDTO(
                usuario);
    }

    public List<UsuariosInterface> getUsuariosDisponibles(Long idActividad, Long idEmpresa) {
        try {
            return usuariosRepository.findByIdActividadAndEmpresaQuery(idActividad, idEmpresa);
        } catch (Exception e) {
            return null;
        }
    }

    // PutMapping
    public ResponseEntity<UsuariosEntity> editUsuario(Long id, UsuariosEntity object) {
        Optional<UsuariosEntity> usuarioId = usuariosRepository.findById(id);
        if (usuarioId.isPresent()) {
            UsuariosEntity usuariosEntity = usuarioId.get();
            usuariosEntity.setNombre(object.getNombre());
            usuariosEntity.setApPaterno(object.getApPaterno());
            usuariosEntity.setTelefono(object.getTelefono());
            usuariosEntity.setFechaNacimiento(object.getFechaNacimiento());
            usuariosRepository.save(usuariosEntity);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<UsuariosEntity> cambiarContraseña(Long id, UsuariosEntity object) {
        Optional<UsuariosEntity> usuarioId = usuariosRepository.findById(id);
        if (usuarioId.isPresent()) {
            UsuariosEntity usuariosEntity = usuarioId.get();
            usuariosEntity.setPassword(new BCryptPasswordEncoder().encode(object.getPassword()));
            usuariosRepository.save(usuariosEntity);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
