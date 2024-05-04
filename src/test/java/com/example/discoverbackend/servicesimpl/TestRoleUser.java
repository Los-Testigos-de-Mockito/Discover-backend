package com.example.discoverbackend.servicesimpl;

import com.example.discoverbackend.entities.RoleApplication;
import com.example.discoverbackend.entities.RoleUser;
import com.example.discoverbackend.entities.Usuario;
import com.example.discoverbackend.repositories.RoleRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestRoleUser {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;
    @Test
    public void testRoleUserCreation() {
        Usuario user = new Usuario();
        RoleApplication role = new RoleApplication("User");
        RoleUser roleUser = new RoleUser(user, role);

        Assert.assertNotNull(roleUser);
        Assert.assertEquals(role, roleUser.getRole());
    }

}
