package com.libraryapp.libraryapp.ejb;
import com.libraryapp.libraryapp.common.UserDto;
import com.libraryapp.libraryapp.entities.User;
import com.libraryapp.libraryapp.entities.UserGroup;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
@Stateless
public class UsersBean {
    private static final Logger LOG = Logger.getLogger(UsersBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;
    @Inject
    PasswordBean passwordBean;
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
            userDtos.add(new UserDto(user.getId(),user.getUsername(),user.getRole(), user.getEmail()));
        }
        return userDtos;
    }
    public void createUser(String username, String email, String password, String role, Collection<String> groups) {
        LOG.info("createUser");
        User newUser=new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordBean.convertToSha256(password));
        newUser.setRole(role);
        newUser.setEmail(email);
        entityManager.persist(newUser);
        assignGroupsToUser(username,groups);
    }
    private void assignGroupsToUser(String username, Collection<String> groups) {
        LOG.info("assignGroupsToUser");
        for (String group:groups){
            UserGroup userGroup=new UserGroup();
            userGroup.setUsername(username);
            userGroup.setUserGroup(group);
            entityManager.persist(userGroup);
        }
    }
}