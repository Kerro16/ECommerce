package com.example.demo.service;

import com.example.demo.data.ApiResponse;
import com.example.demo.model.AppUser;
import com.example.demo.model.CustomerData;
import com.example.demo.model.Role;
import com.example.demo.repository.IAppUserRepository;
import com.example.demo.repository.ICustomerDataRepository;
import com.example.demo.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserService implements IAppUserService {

    private final IAppUserRepository userRepository;
    private final IRoleRepository roleRepository;

    private final ICustomerDataRepository customerDataRepository;

    @Override
    public AppUser saveUser(AppUser user) { // Se crean usuario con role User por defecto
        log.info("Guardando nuevo usuario {} en la base de datos", user.getUsername());
        Role role = roleRepository.findByName("User");
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public AppUser saveAdmin(AppUser user) {
        log.info("Guardando nuevo admin {} en la base de datos", user.getUsername());
        Role role = roleRepository.findByName("Admin");
        user.setRole(role);
        return userRepository.save(user);
    }


    @Override
    public AppUser getUser(String username) {
        log.info("Buscando admin {} en la base de datos", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public ApiResponse<String> getRoleByUser(Long id) {
        log.info("Buscando al usuario {} en la base de datos ", id);

        Optional<AppUser> user = userRepository.findById(id);
        if (user.isEmpty()) return new ApiResponse<String>(false, null, "usuario no encontrado");

        return new ApiResponse<String>(true, user.get().getRole().getName(), null);

    }

    @Override
    public List<AppUser> getUsers() {
        log.info("Buscando todos los admins de la base de datos");
        return userRepository.findAll();
    }

    @Override
    public ApiResponse<Long> register(CustomerData data) {
        log.info("Creando nuevo usuario");
        //Validar que el username no esta en la bd
        Optional<AppUser> userBD = Optional.ofNullable(userRepository.findByUsername(data.getUser().getUsername()));
        if (!userBD.isEmpty()) {
            return new ApiResponse(false, null, "usuario ya registrado");
        }
        AppUser user = saveUser(new AppUser(null, data.getUser().getUsername(), data.getUser().getPassword(), null));
        CustomerData customer = customerDataRepository.save(
                new CustomerData(
                        null, user,
                        data.getFull_name(),
                        data.getBilling_address(),
                        data.getDefault_shipping_address(),
                        data.getCountry(),
                        data.getPhone()));
        return new ApiResponse<Long>(true, user.getId(), null);
    }

    @Override
    public ApiResponse<Long> login(AppUser user) {
        log.info("Buscando en la base de datos al usuario {}", user.getUsername());
        Optional<AppUser> userDB = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));
        if (!userDB.isEmpty()) {
            if (user.getPassword().equals(userDB.get().getPassword())) {
                //Generar token y devolver token
                return new ApiResponse<Long>(true, userDB.get().getId(), null);
            }
        }
        return new ApiResponse<Long>(false, null, "Usuario o contraseña incorrecta");
    }
}
