package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yevhenpiven.bootstrapproject.entity.Role;
import com.yevhenpiven.bootstrapproject.entity.User;
import com.yevhenpiven.bootstrapproject.repository.RoleRepository;
import com.yevhenpiven.bootstrapproject.repository.UserRepository;
import com.yevhenpiven.bootstrapproject.service.UserService;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;
    private Role role;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        role = new Role();
        role.setRoleName("ROLE_USER");
    }

    @Test
    void testSaveUser() {
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.saveUser(user);

        assertEquals("encodedPassword", user.getPassword());

        assertEquals(user, savedUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testFindById() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findById(1);

        assertEquals(user, foundUser.get());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(1);
        verify(userRepository, times(1)).deleteById(1);
    }
}
