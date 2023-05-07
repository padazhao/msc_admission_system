package com.group13.msc_admission_system.service.serviceimplement;

import com.group13.msc_admission_system.common.MyMessage;
import com.group13.msc_admission_system.common.ResourceType;
import com.group13.msc_admission_system.dto.LoginCredentials;
import com.group13.msc_admission_system.dto.UserRequestDTO;
import com.group13.msc_admission_system.exception.MyResourceAlreadyExistException;
import com.group13.msc_admission_system.exception.MyResourceNotFoundException;
import com.group13.msc_admission_system.model.Admin;
import com.group13.msc_admission_system.repository.AdminRepository;
import com.group13.msc_admission_system.service.serviceinterface.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            throw new MyResourceAlreadyExistException(MyMessage.resourceAlreadyExist(ResourceType.EMAIL));

        Admin admin = new Admin(userRequestDTO);

        adminRepository.save(admin);
    }
    //===========================================LOGIN================================================================
    @Override
    public void login(LoginCredentials loginCredentials) {
        Admin admin = adminRepository.findByEmailAndPassword(loginCredentials.getEmail(), loginCredentials.getPassword());

        if (admin==null)                                        //THROW EXCEPTION IF ADMIN NOT FOUND
            throw new MyResourceNotFoundException(MyMessage.resourceNotFound(ResourceType.ADMIN));
    }
    //===========================================getAdminInfoByEmail================================================================
    @Override
    public Admin getAdminInfoByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
    //===========================================getAdminInfoByEmail================================================================
    @Override
    public Admin getAdminInfo(Long adminId) {
        return adminRepository.findById(adminId).orElseThrow(
                () -> new MyResourceNotFoundException(MyMessage.resourceNotFound(ResourceType.ADMIN, adminId)));
    }
    //===========================================getAllAdminInfo================================================================
    @Override
    public List<Admin> getAllAdminInfo() {
        return adminRepository.findAll();
    }


}
