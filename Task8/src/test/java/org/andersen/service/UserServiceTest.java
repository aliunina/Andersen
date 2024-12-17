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
    void successAddCorrectUser() {
        User user = new User(1L, "Peter", UserStatus.ACTIVATED);
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(userService.addUser(user), user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void failAddNullUser() {
        assertThrows(NullPointerException.class, () -> userService.addUser(null));
        verify(userRepository, never()).save(null);
    }

    @Test
    void failAddBlankNameUser() {
        User user = new User(" ", UserStatus.DEACTIVATED);
        assertThrows(EmptyUserNameException.class, () -> userService.addUser(user));
        verify(userRepository, never()).save(user);
    }

    @Test
    void successGetExistingUser() {
        User user = new User(1L, "Peter", UserStatus.ACTIVATED);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        User result = userService.getUserById(user.getId());
        assertEquals(user, result);
        verify(userRepository, times(1)).findById(user.getId());
    }

    @Test
    void failGetNonExistentUser() {
        User user = new User(100L, "Maria", UserStatus.DEACTIVATED);
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(user.getId()));
        verify(userRepository, times(1)).findById(user.getId());
    }

    @Test
    void failGetNullIdUser() {
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(null));
        verify(userRepository, times(1)).findById(null);
    }

    @Test
    void successDeleteExistingUser() {
        User user = new User(1L, "Peter", UserStatus.ACTIVATED);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);
        userService.deleteUser(user.getId());
        verify(userRepository, times(1)).findById(user.getId());
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void failDeleteNonExistentUser() {
        User user = new User(100L, "Maria", UserStatus.DEACTIVATED);
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(user.getId()));
        verify(userRepository, never()).deleteById(user.getId());
    }

    @Test
    void failDeleteNullIdUser() {
        assertThrows(NullPointerException.class, () -> userService.deleteUser(null));
        verify(userRepository, never()).deleteById(null);
    }

    @Test
    void successActivateUser_activationEnabled() {
        User user = new User(1L, "Peter", UserStatus.DEACTIVATED);
        doNothing().when(userRepository).updateUserStatus(user.getId());
        assertDoesNotThrow(() -> userService.activateUser(user.getId()));
        verify(userRepository, times(1)).updateUserStatus(user.getId());
    }

    @Test
    void failActivateUser_activationDisabled() {
        User user = new User(1L, "Peter", UserStatus.ACTIVATED);
        assertThrows(UserActivationIsDisabledException.class, () -> userService.activateUser(user.getId()));
        verify(userRepository, never()).updateUserStatus(user.getId());
    }

    @Test
    void failActivateNotExistentUser() {
        User user = new User(100L, "Maria", UserStatus.DEACTIVATED);
        assertThrows(UserNotFoundException.class, () -> userService.activateUser(user.getId()));
        verify(userRepository, times(1)).updateUserStatus(user.getId());
    }
}