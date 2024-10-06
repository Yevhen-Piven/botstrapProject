package dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.yevhenpiven.bootstrapproject.entity.Role;
import com.yevhenpiven.bootstrapproject.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int id;
    private String name;
    private Set<String> roles;

    public UserDto(User user) {
        this.id = user.getUserId();
        this.name = user.getUsername();
        this.roles = user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toSet());
    }
}
