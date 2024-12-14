package org.andersen.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.andersen.exception.EmptyUserNameException;
import org.andersen.exception.UserActivationIsDisabledException;
import org.andersen.exception.UserNotFoundException;
import org.andersen.model.user.User;
import org.andersen.model.user.UserStatus;
import org.andersen.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testAddUser_userPassed_success() {
        User user = new User(1L, "Peter", UserStatus.ACTIVATED);
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(userService.addUser(user), user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testAddUser_nullPassed_error() {
        assertThrows(NullPointerException.class, () -> userService.addUser(null));
        verify(userRepository, never()).save(null);
    }

    @Test
    void testАddUser_blankNamePassed_error() {
        User user = new User(" ", UserStatus.DEACTIVATED);
        assertThrows(EmptyUserNameException.class, () -> userService.addUser(user));
        verify(userRepository, never()).save(user);
    }

    @Test
    void testGetUser_userFound_success() {
        User user = new User(1L, "Peter", UserStatus.ACTIVATED);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        User result = userService.getUserById(user.getId());
        assertEquals(user, result);
        verify(userRepository, times(1)).findById(user.getId());
    }

    @Test
    void testGetUser_userNotFound_error() {
        User user = new User(100L, "Maria", UserStatus.DEACTIVATED);
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(user.getId()));
        verify(userRepository, times(1)).findById(user.getId());
    }

    @Test
    void testGetUser_nullIdPassed_error() {
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(null));
        verify(userRepository, times(1)).findById(null);
    }

    @Test
    void testGetUserWithTickets_userFound_success() {
        User user = new User(1L, "Peter", UserStatus.ACTIVATED);
        when(userRepository.findUserWithTicketsById(user.getId())).thenReturn(Optional.of(user));
        assertEquals(user, userService.getUserWithTicketsById(user.getId()));
        verify(userRepository, times(1)).findUserWithTicketsById(user.getId());
    }

    @Test
    void testGetUserWithTickets_userNotFound_error() {
        User user = new User(100L, "Maria", UserStatus.DEACTIVATED);
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(user.getId()));
        verify(userRepository, times(1)).findById(user.getId());
    }

    @Test
    void testDeleteUser_userFound_success() {
        User user = new User(1L, "Peter", UserStatus.ACTIVATED);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);
        userService.deleteUser(user.getId());
        verify(userRepository, times(1)).findById(user.getId());
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void testDeleteUser_userNotFound_error() {
        User user = new User(100L, "Maria", UserStatus.DEACTIVATED);
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(user.getId()));
        verify(userRepository, never()).deleteById(user.getId());
    }

    @Test
    void testDeleteUser_nullIdPassed_error() {
        assertThrows(NullPointerException.class, () -> userService.deleteUser(null));
        verify(userRepository, never()).deleteById(null);
    }

    @Test
    void testActivateUser_activationEnabled_success() {
        User user = new User(1L, "Peter", UserStatus.DEACTIVATED);
        doNothing().when(userRepository).updateUserStatus(user.getId());
        assertDoesNotThrow(() -> userService.activateUser(user.getId()));
        verify(userRepository, times(1)).updateUserStatus(user.getId());
    }

    @Test
    void testActivateUser_activationEnabled_userNotFound_error() {
        User user = new User(100L, "Maria", UserStatus.DEACTIVATED);
        assertThrows(UserNotFoundException.class, () -> userService.activateUser(user.getId()));
        verify(userRepository, times(1)).updateUserStatus(user.getId());
    }

    @Test
    void testActivateUser_activationDisabled_error() {
        User user = new User(1L, "Peter", UserStatus.ACTIVATED);
        assertThrows(UserActivationIsDisabledException.class, () -> userService.activateUser(user.getId()));
        verify(userRepository, never()).updateUserStatus(user.getId());
    }
}