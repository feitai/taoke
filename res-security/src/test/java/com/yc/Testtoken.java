package com.yc;


import com.yc.ressecurity.config.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SecurityApp.class)
@Slf4j
public class Testtoken {


    @Autowired
    private JwtTokenUtil jw;


    @Test
    public void testToken(){


        UserDetails ud = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {return null;}

            @Override
            public String getPassword() {return "$2a$10$WLM6zEXiDDPLx1HN.QxvA.IXM15gUKDOikMOnC6yUy6ag/7XmN7Ca";}

            @Override
            public String getUsername() {return "a";}

            @Override
            public boolean isAccountNonExpired() {return false;}

            @Override
            public boolean isAccountNonLocked() {return false;}

            @Override
            public boolean isCredentialsNonExpired() {return false;}

            @Override
            public boolean isEnabled() {return true;}
        };


        String  token = jw.generateToken( ud );
        System.out.println(token);


        Claims c = jw.getAllClaimsFromToken(token);

        System.out.println();
        System.out.println();
        System.out.println("subject: " + c.get("sub"));
        System.out.println("expire time: " + c.get("exp"));
        System.out.println("issue time: " + c.get("iat"));
        System.out.println("password: " + c.get("pwd"));
        System.out.println("user id: " + c.get("userid"));
        System.out.println("username: " + c.get("username"));
    }

}
