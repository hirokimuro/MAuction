package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MAuctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MAuctionApplication.class, args);
	}

}
//public class MAuctionApplication implements CommandLineRunner {
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    public static void main(String[] args) {
//        SpringApplication.run(MAuctionApplication.class, args);
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public void run(String... args) {
//
//        String password = "123";
//        String digest = passwordEncoder.encode(password);
//        System.out.println("ハッシュ値 = " + digest);
//
//        if (passwordEncoder.matches(password, digest)) {
//            System.out.println("一致したよ");
//            return;
//        }
//        System.out.println("一致しなかったよ");
//    }
//}