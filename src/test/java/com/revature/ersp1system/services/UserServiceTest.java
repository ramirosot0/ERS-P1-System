package com.revature.ersp1system.services;

import com.revature.ersp1system.models.Role;
import com.revature.ersp1system.models.User;
import com.revature.ersp1system.repositories.UserDAO;
import java.util.Optional;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

public class UserServiceTest {
    private static UserService userService;
    private static UserDAO userDAO;
    private User GENERIC_EMPLOYEE_1;

    public UserServiceTest() {
    }

//    @BeforeClass
//    public static void setUpBeforeClass() throws Exception {
//        userService = new UserService();
//        userDAO = (UserDAO)Mockito.mock(UserDAO.class);
//    }
//
//    @Before
//    public void setUp() throws Exception {
//        this.GENERIC_EMPLOYEE_1 = new User(1, "genericEmployee1", "genericPassword", Role.EMPLOYEE);
//    }
//
//    @Test
//    public void testGetByUsernamePassesWhenUsernameExists() {
//        Mockito.when(userDAO.getByUsername(Matchers.anyString())).thenReturn(Optional.of(this.GENERIC_EMPLOYEE_1));
//        Assert.assertEquals(Optional.of(this.GENERIC_EMPLOYEE_1), userService.getByUsername(this.GENERIC_EMPLOYEE_1.getUsername()));
//        ((UserDAO)Mockito.verify(userDAO)).getByUsername(this.GENERIC_EMPLOYEE_1.getUsername());
//    }
}