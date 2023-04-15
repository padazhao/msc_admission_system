package com.group13.msc_admission_system.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class ApplicantResponseDTO implements Serializable {

        //USED TO EXPOSE "WANTED" DATA TO REST API

        private Long id;
        private String userName;
        private String email;
        private String gender;
        private int phoneNumber;
        private LocalDate dateOfBirth;
        private int age;

}
