package com.libraryapp.libraryapp.ejb;

import com.libraryapp.libraryapp.common.UserDto;
import com.libraryapp.libraryapp.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UsersBean {
    private static final Logger LOG = Logger.getLogger(UsersBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<UserDto> findAllUsers(){
        LOG.info("findAllUsers");
        try{
            TypedQuery<User> typedQuery = entityManager.createQuery("SELECT u FROM User u", User.class);
            List<User> users = typedQuery.getResultList();
            return copyUserToDto(users);
        }catch (Exception ex){
            throw new EJBException(ex);
        }
    }

    private List<UserDto> copyUserToDto(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();

        for (var user : users
        ) {
            userDtos.add(new UserDto(user.getId(),user.getUsername(),user.getRole()));
        }
        return userDtos;
    }

}
