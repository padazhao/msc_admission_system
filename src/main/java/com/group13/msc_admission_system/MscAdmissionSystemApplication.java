package com.group13.msc_admission_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class MscAdmissionSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MscAdmissionSystemApplication.class, args);
    }

}
