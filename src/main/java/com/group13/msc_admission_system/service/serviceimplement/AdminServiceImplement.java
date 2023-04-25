package com.group13.msc_admission_system.service.serviceimplement;

import com.group13.msc_admission_system.common.Message;
import com.group13.msc_admission_system.common.ResourceType;
import com.group13.msc_admission_system.dto.LoginCredentials;
import com.group13.msc_admission_system.dto.UserRequestDTO;
import com.group13.msc_admission_system.exception.MyInvalidInputException;
import com.group13.msc_admission_system.exception.MyResourceAlreadyExistException;
import com.group13.msc_admission_system.exception.MyResourceNotFoundException;
import com.group13.msc_admission_system.model.Admin;
import com.group13.msc_admission_system.repository.AdminRepository;
import com.group13.msc_admission_system.service.serviceinterface.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;

@Service
public class AdminServiceImplement implements AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImplement(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    //===========================================REGISTRATION================================================================
    @Override
    public void register(UserRequestDTO userRequestDTO) {
        if(adminRepository.findByEmail(userRequestDTO.getEmail())!=null)
            throw new MyResourceAlreadyExistException(Message.resourceAlreadyExist(ResourceType.EMAIL));

        Admin admin = new Admin(userRequestDTO);

        adminRepository.save(admin);
    }
    //===========================================LOGIN================================================================
    @Override
    public void login(LoginCredentials loginCredentials) {
        Admin admin = adminRepository.findByEmailAndPassword(loginCredentials.getEmail(), loginCredentials.getPassword());

        if (admin==null)                                        //THROW EXCEPTION IF ADMIN NOT FOUND
            throw new MyResourceNotFoundException(Message.resourceNotFound(ResourceType.ADMIN));
    }


}
