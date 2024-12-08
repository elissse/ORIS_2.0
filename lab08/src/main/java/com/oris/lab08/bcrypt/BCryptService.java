package com.oris.lab08.bcrypt;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
    В БД храним хеши паролей
    hash(пароль + соль)

    {username, salt, hash}

 */
public class BCryptService {

    /*

        $2a$10$vbTbv1q9.wzvcdZG0/bH/ur7q82gCHjhziHaEfJv9HRHtoMWfzIQO

        $2a$
        10$ 2^10
        salt 22 Base64 код соли
        hash 31 Base64 код хеша (пароль + соли)
    */

    public static void main(String[] args) {
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        String salt = BCrypt.gensalt(10);
        //System.out.println(bCrypt.encode("password"));

        System.out.println(bCrypt.encode("password"));
        System.out.println(bCrypt.matches(
                "password",
                "$2a$10$jkvJF9D7SSZUkCYBwBVKSuBEpyRtz1cbAFN7UMJZ2.1k0/6oajzjS"
        ));
        System.out.println(bCrypt.matches(
                "password",
                "$2a$10$eVd7gUyIfgYKYWW0/eUwoek9h1LqwMXDgzPK7liSY79CtzPCDjTNi"
        ));
        System.out.println(salt);
        String salt2 = BCrypt.gensalt(10);
        String hash2 = BCrypt.hashpw("password",salt2);
        System.out.println(BCrypt.checkpw("password",hash2));
        String hash = BCrypt.hashpw("password", salt);
        System.out.println(BCrypt.checkpw("password", hash));

/*
        String salt = BCrypt.gensalt(10);
        System.out.println(salt);
        String hash = BCrypt.hashpw("password", salt);
        System.out.println(hash);
        $2a$10$eVd7gUyIfgYKYWW0/eUwoek9h1LqwMXDgzPK7liSY79CtzPCDjTNi
        $2a$10$eh8/xxDh0RVh3Jz/7K9zUOEEYPHTdLBSAd/6tRRVP/2YNjB8mc8Hq

*/


    }


}
/*
        String salt = BCrypt.gensalt(10);
        System.out.println(salt);
        String hash = BCrypt.hashpw("password", salt);
        System.out.println(hash);

        System.out.println(bCrypt.encode("password"));

        System.out.println(bCrypt.matches("password",
                "$2a$10$gxCDGtAffcdxxK.P4.DuSu8weUIzoa75vcmcbK.m5sispqoA.WLKW"));

 */
