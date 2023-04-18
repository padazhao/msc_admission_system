package com.group13.msc_admission_system.service.serviceimplement;

import com.group13.msc_admission_system.common.Message;
import com.group13.msc_admission_system.common.ResourceType;
import com.group13.msc_admission_system.dto.ApplicantRequestDTO;
import com.group13.msc_admission_system.dto.ApplicantResponseDTO;
import com.group13.msc_admission_system.exception.MyResourceNotFoundException;
import com.group13.msc_admission_system.model.Applicant;
import com.group13.msc_admission_system.repository.ApplicantRepository;
import com.group13.msc_admission_system.service.serviceinterface.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplicantServiceImplement implements ApplicantService {
    @Autowired
    private ApplicantRepository applicantRepository;

    public ApplicantServiceImplement(ApplicantRepository applicantRepository) {
        super();
        this.applicantRepository = applicantRepository;
    }

    @Override
    public void register(ApplicantRequestDTO applicantRequestDTO) throws Exception{
        if(applicantRepository.findByEmail(applicantRequestDTO.getEmail()).isPresent()){
            throw new Exception("Email is already exists");
        }
        Applicant applicant = new Applicant(applicantRequestDTO);
        applicantRepository.save(applicant);
    }

    @Override
    public Applicant findByEmailAndPassword(String email, String password) {
        return applicantRepository.findByEmailAndPassword(email,password);
    }
    @Transactional
    @Override
    public ApplicantResponseDTO updateApplicant(Long id, ApplicantRequestDTO applicantRequestDTO) {
        Applicant update = applicantRepository.findById(id).orElseThrow(
                () -> new MyResourceNotFoundException(Message.resourceNotFound(ResourceType.APPLICANT, id)));

//        if (MyUtils.isNotEmptyAndNotNull(userRequestDTO.getFirstName())) {
//            update.setFirstName(userRequestDTO.getFirstName());
//        }
//        if (MyUtils.isNotEmptyAndNotNull(userRequestDTO.getLastName())) {
//            update.setLastName(userRequestDTO.getLastName());
//        }
//        if (userRequestDTO.getAddressId() != null) {
//            Address newAddress = addressService.getAddress(userRequestDTO.getAddressId());
//            update.getAddress().add(newAddress);
//        }
//        if (MyUtils.isNotEmptyAndNotNull(userRequestDTO.getEmail())) {
//            update.setEmail(userRequestDTO.getEmail());
//        }
//        if (MyUtils.isNotEmptyAndNotNull(userRequestDTO.getGender())) {
//            Gender gender = new StringToEnumConverter().convert(userRequestDTO.getGender()); //CONVERTS GENDER INPUT TO ENUM TYPE GENDER
//            update.setGender(gender);
//        }
//        if (userRequestDTO.getPhoneNumber() > 0) {
//            update.setPhoneNumber(userRequestDTO.getPhoneNumber());
//        }
//        if (MyUtils.isNotEmptyAndNotNull(userRequestDTO.getUserName())) {
//            update.setUserName(userRequestDTO.getUserName());
//        }
//        userRepository.save(update);
//
//        System.out.println( Message.updated); // TODO: 4/30/2022 Use logs
//
//        return userMapper.userToUserResponseDTO(update);

        return null;
    }
}
