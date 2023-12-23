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
        for (User user : users) {
            userDtos.add(new UserDto(user.getId(), user.getUsername(), user.getRole(), user.getEmail()));
        }
        return userDtos;
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
    public void deleteUser(Long userId) {
        LOG.info("deleteUser");
            User userToDelete = entityManager.find(User.class, userId);
            if (userToDelete != null) {
                entityManager.remove(userToDelete);
            } else {
                LOG.warning("User not found with ID: " + userId);
            }
    }

    public Collection<String> findUsernamesByUserIds(Collection<Long> userIds) {
        List<String> usernames=
                entityManager.createQuery("SELECT u.username from User u where  u.id in :userIds", String.class)
                        .setParameter("userIds",userIds)
                        .getResultList();
        return usernames;
    }

    public UserDto findById(Long userId) {
        LOG.info("findById");
        User user = entityManager.find(User.class, userId);
        if (user != null) {
            Collection<String> groups = getUserGroups(user.getUsername());
            return new UserDto(user.getId(), user.getUsername(), user.getRole(), user.getEmail());
        }
        return null;
    }
    private Collection<String> getUserGroups(String username) {
        TypedQuery<UserGroup> query = entityManager.createQuery("SELECT ug FROM UserGroup ug WHERE ug.username = :username", UserGroup.class);
        query.setParameter("username", username);
        List<UserGroup> userGroups = query.getResultList();

        List<String> groups = new ArrayList<>();
        for (UserGroup userGroup : userGroups) {
            groups.add(userGroup.getUserGroup());
        }
        return groups;
    }

    private void removeGroupsFromUser(String username) {
        LOG.info("removeGroupsFromUser");
        List<UserGroup> userGroups = entityManager.createQuery("SELECT ug FROM UserGroup ug WHERE ug.username = :username", UserGroup.class)
                .setParameter("username", username)
                .getResultList();

        for (UserGroup userGroup : userGroups) {
            entityManager.remove(userGroup);
        }
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
    public void updateUser(Long userId,String username, String email, String password, String role, Collection<String> groups) {
        LOG.info("updateUser");
        User user=entityManager.find(User.class,userId);
        if(user!=null){
            removeGroupsFromUser(user.getUsername());
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(passwordBean.convertToSha256(password));
            user.setRole(role);
            assignGroupsToUser(username,groups);
        }
    }
}