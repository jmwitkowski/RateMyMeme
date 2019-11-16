package pl.sda.ratemymeme.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sda.ratemymeme.exception.RoleNotFoundException;
import pl.sda.ratemymeme.model.Role;
import pl.sda.ratemymeme.repository.RoleRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RoleServiceTest {
    private final Role role = new Role("ADMIN");
    private final RoleRepository roleRepositoryMock = mock(RoleRepository.class);
    private final RoleService roleService = new RoleService(roleRepositoryMock);

    @Test
    public void shouldGetRoleById() {
        //Given
        when(roleRepositoryMock.findById(1)).thenReturn(Optional.of(role));
        //When
        Role roleExpected = roleService.getRoleById(1);
        //Then
        assertThat(roleExpected).isEqualTo(role);
    }

    @Test
    public void shouldThrowEsceptionWhenThereIsNoRoleWithId() {
        //Given
        when(roleRepositoryMock.findById(1)).thenReturn(Optional.empty());
        //Then
        Assertions.assertThrows(RoleNotFoundException.class, () -> roleService.getRoleById(1));
    }
}