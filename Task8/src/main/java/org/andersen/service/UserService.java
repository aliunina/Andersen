package org.andersen.service;

import lombok.RequiredArgsConstructor;
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

    @Value("${app.conditionalBean.available}")
    private boolean isActivationEnabled;

    @Transactional
    public User addUser(User user){
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException());
    }

    @Transactional
    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException());
        userRepository.delete(user);
    }

    public void activateUser(long id) throws UserActivationIsDisabledException {
        if (isActivationEnabled) {
            userRepository.updateUserStatus(id);
        } else {
            throw new UserActivationIsDisabledException();
        }
    }
}
