package com.yevhenpiven.bootstrapproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.yevhenpiven.bootstrapproject.entity.Role;
import com.yevhenpiven.bootstrapproject.repository.RoleRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            Role adminRole = new Role();
            adminRole.setRoleName("ADMIN");

            Role studentRole = new Role();
            studentRole.setRoleName("STUDENT");

            Role teacherRole = new Role();
            teacherRole.setRoleName("TEACHER");

            Role staffRole = new Role();
            staffRole.setRoleName("STAFF");

            roleRepository.save(adminRole);
            roleRepository.save(studentRole);
            roleRepository.save(teacherRole);
            roleRepository.save(staffRole);
        }
    }
}
