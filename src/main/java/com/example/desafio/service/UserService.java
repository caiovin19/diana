package com.example.desafio.service;

import com.example.desafio.entities.UserEntity;
import com.example.desafio.repository.UserRepository;
import com.example.desafio.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository repository ;

    public List<UserEntity> findAll(){
        return repository.findAll();
    }
    public UserEntity findById(Long id) {

        Optional<UserEntity> obj=repository.findById(id);
        return obj.get();
    }

    public UserEntity updateUserByEntity(UserEntity obj){
        return repository.save(obj);
    }

    public UserEntity deleteById(Long id){
        Optional<UserEntity> obj = repository.findById(id);
        try{
            obj = repository.findById(id);
            if(obj.isPresent()){
                repository.delete(obj.get());
            }
        }catch (Exception e){
            e.getMessage();
        }

        return obj.get();
    }

    public UserEntity validationForCriation(UserEntity obj){
        try{
            List<UserEntity> objUser = repository.findAll();
            if(!objUser.contains(obj))
                return repository.save(obj);

        }catch (Exception e) {
            e.getMessage();
        }

        return obj;
    }
}
