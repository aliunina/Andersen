package org.andersen.service;

import lombok.RequiredArgsConstructor;
import org.andersen.exception.EmptyUserNameException;
import org.andersen.exception.UserActivationIsDisabledException;
import org.andersen.exception.UserNotFoundException;
import org.andersen.model.user.User;
import org.andersen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Value("${app.activate-user.enabled}")
    private final boolean isActivationEnabled;

    @Transactional
    public User addUser(User user) {
        if (user == null) {
            throw new NullPointerException();
        }
        if (user.getName().isBlank()) {
            throw new EmptyUserNameException();
        }
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException());
    }

    @Transactional
    public void deleteUser(Long id){
        if (id == null) {
            throw new NullPointerException("User id is null");
        }
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException());
        userRepository.delete(user);
    }

    @Transactional
    public void activateUser(long id) throws UserActivationIsDisabledException {
        if (isActivationEnabled) {
            userRepository.updateUserStatus(id);
        } else {
            throw new UserActivationIsDisabledException();
        }
    }
}
