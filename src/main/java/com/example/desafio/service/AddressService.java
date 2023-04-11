package com.example.desafio.service;

import com.example.desafio.entities.AddressEntity;
import com.example.desafio.entities.UserEntity;
import com.example.desafio.repository.AddressRepository;
import com.example.desafio.repository.UserRepository;
import com.example.desafio.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

	@Autowired
    private AddressRepository repository;

    public AddressEntity findById(Long id) {

        Optional<AddressEntity> obj=repository.findById(id);
        return obj.get();
    }
    public AddressEntity validationForAddressCriation(AddressEntity obj){
        try{
            List<AddressEntity> objUser = repository.findAll();
            if(!objUser.contains(obj))
                return repository.save(obj);

        }catch (Exception e) {
            e.getMessage();
        }

        return obj;
    }
    public AddressEntity deleteById(Long id){
        Optional<AddressEntity> obj = repository.findById(id);
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
}
