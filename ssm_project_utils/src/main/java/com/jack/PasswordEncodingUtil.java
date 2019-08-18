package com.jack;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncodingUtil  {

    private static PasswordEncoder encoder=new BCryptPasswordEncoder (  );

    public String getPasswordEncoder(String passsword){

        String encode = encoder.encode ( passsword );
        return encode;

    }

    public static void main (String[] args) {
        PasswordEncodingUtil pu=new PasswordEncodingUtil ();
        String passwordEncoder = pu.getPasswordEncoder ( "123" );
        System.out.println (passwordEncoder);//$2a$10$wTju37b7LODEzKlhYudFfuE9cMMInacW5bFAOkz1L/AywaLsLMHoG
    }
}
