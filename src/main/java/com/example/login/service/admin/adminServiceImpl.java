package com.example.login.service.admin;

import com.example.login.model.Doctor;
import com.example.login.model.User;
import com.example.login.repository.AppUserRepo;
import com.example.login.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class adminServiceImpl implements adminService {
    @Autowired
    private DoctorRepository doctorRepository;



    @Autowired
    private AppUserRepo appUserRepo;
    @Override
    public boolean addDoctor(Doctor doctor) {
        try {
            //get user by email id
            User user = appUserRepo.findByEmail(doctor.getEmail());
            if(user == null)
            {
                // user is not then add
                doctorRepository.save(doctor);
                return true;

            }
            else {
                return false;
            }



        }catch (Exception e){
            return false;

        }




    }
}
