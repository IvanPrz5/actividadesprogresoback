package com.example.actividadesProgreso.Security.Auth;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.actividadesProgreso.Entity.Usuarios.UsuariosEntity;
import com.example.actividadesProgreso.Entity.Usuarios.DTO.UsuariosDTO;
import com.example.actividadesProgreso.Repository.Usuarios.UsuariosRepository;
import com.example.actividadesProgreso.Security.Config.TokenUtils;
import com.example.actividadesProgreso.Security.Config.UserDetailImp;
import com.example.actividadesProgreso.Security.Utils.ResultObjectResponse;
import com.example.actividadesProgreso.Service.Usuarios.UsuariosService;


@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RestController
@RequestMapping
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired 
    UsuariosService usuariosService;

    @Autowired
    UsuariosRepository usuariosRepository;

    @PostMapping("/login")
    public ResultObjectResponse authenticateUser(@RequestBody AuthCredentials authCredentials) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authCredentials.getEmail(), authCredentials.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            if (authentication.getPrincipal() != null) {
                UserDetailImp userDetails = (UserDetailImp) authentication.getPrincipal();
                String token = TokenUtils.createToken(userDetails.getNombre(), userDetails.getUsername() + userDetails.getAuthorities());
                // String token = TokenUtils.createToken(userDetails.getNombre(), userDetails.getUsername());
                Long idUsuario = userDetails.getId();
                List<UsuariosDTO> usuario = usuariosService.getByIdAndStatus(idUsuario, true);

                HashMap<String, Object> response = new HashMap<>();
                response.put("token", token);
                response.put("usuario", usuario);

                return new ResultObjectResponse(1, false, "Success", response);
            } else {
                return new ResultObjectResponse(0, true, "Verifique los datos de acceso e intentelo nuevamente.", null);
            }
        } catch (Exception ex) {
            return new ResultObjectResponse(0, true, "Verifique los datos de acceso e intentelo nuevamente aqui.", null);
        }
    }

    @PostMapping("/register")
    public ResultObjectResponse registerUser(@Valid @RequestBody AuthRegisterCredentials authRegisterCredentials){
        try {
            var email = authRegisterCredentials.getEmail();
            if(emailExist(email).isPresent()){
                return new ResultObjectResponse(2, true, "El email ya existe  " + email, null);
            }else{
                UsuariosEntity usuariosEntity = new UsuariosEntity();
                usuariosEntity.setNombre(authRegisterCredentials.getNombre());
                usuariosEntity.setApPaterno(authRegisterCredentials.getApPaterno());
                // usuariosEntity.setApMaterno(authRegisterCredentials.getApMaterno());
                usuariosEntity.setTelefono(authRegisterCredentials.getTelefono());
                usuariosEntity.setEmail(authRegisterCredentials.getEmail());
                usuariosEntity.setPassword(new BCryptPasswordEncoder().encode(authRegisterCredentials.getPassword()));
                usuariosEntity.setStatus(true);
                usuariosRepository.save(usuariosEntity);
                
                Optional<UsuariosEntity> usuario = usuariosRepository.findOneByEmail(authRegisterCredentials.getEmail());
                if(usuario.isPresent()){
                    usuariosService.insertIntoUserRoles(usuario.get().getId(), 2L);
                }

                return new ResultObjectResponse(1, false, "El usuario se registro con exito", null);
            }
        } catch (Exception e) {
            return new ResultObjectResponse(0, true, "El usuario no se registro", null);
        }
    }

    private Optional<UsuariosEntity> emailExist(String email){
        return usuariosRepository.findOneByEmail(email);
    }
}
