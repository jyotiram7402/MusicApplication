package org.gfg.musicapplication.services;

import org.gfg.musicapplication.model.User;
import org.gfg.musicapplication.model.UserType;
import org.gfg.musicapplication.repositories.UserCacheRepository;
import org.gfg.musicapplication.repositories.UserRepository;
import org.gfg.musicapplication.requests.UserSignupRequest;
import org.gfg.musicapplication.response.GenericMessages;
import org.gfg.musicapplication.response.SignUpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCacheRepository userCacheRepository;

    @Autowired
    private PasswordEncoder encoder;

    private UserType getUserType(String typeOfUser){
        if(typeOfUser.equalsIgnoreCase(UserType.ADMIN.getUserType())){
            return UserType.ADMIN;
        }
        return UserType.NONSUBSCRIBER;
    }
    public SignUpResponse userSignUp(UserSignupRequest userSignupRequest, String typeOfUser) {
        if(userRepository.findByMail(userSignupRequest.getUserEmail()) != null){
            return SignUpResponse.builder().message(GenericMessages.ENTRYALREADYPRESENT.getMessage()).build();
        }
        User userInDb = userSignupRequest.toUser();
        userInDb.setPassword(encoder.encode(userSignupRequest.getPassword()));
        UserType userType = getUserType(typeOfUser);
        userInDb.setUserType(userType);
        try{
             userInDb = userRepository.save(userInDb);
        }catch (Exception e){
            e.printStackTrace();
            return SignUpResponse.builder().message(GenericMessages.SIGNUPFAILURE.getMessage()).build();
        }
        userCacheRepository.set(userInDb);
        return SignUpResponse.builder().
                message(GenericMessages.SIGNUPSUCCESS.getMessage()).
                email(userInDb.getMail()).
                userId(userInDb.getPk()).
                build();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if(userCacheRepository.get(email) != null){
            return userCacheRepository.get(email);
        }
        return userRepository.findByMail(email);
    }
}
