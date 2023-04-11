package com.example.desafio.controller;

import com.example.desafio.entities.AddressEntity;
import com.example.desafio.entities.UserEntity;
import com.example.desafio.service.AddressService;
import com.example.desafio.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/endereco")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AddressEntity> getAddress(@PathVariable Long id) throws Exception{
        AddressEntity objAddress = addressService.findById(id);
        return ResponseEntity.ok().body(objAddress);

    }
    @PostMapping(value = "/{idUser}")
    public ResponseEntity<AddressEntity> createAddress(@PathVariable Long idUser,@RequestBody AddressEntity userObj){
        UserEntity objUser = userService.findById(idUser);
        if(!ObjectUtils.isEmpty(objUser)){
            addressService.validationForAddressCriation(userObj);
            return ResponseEntity.status(201).body(userObj);
        }
        return ResponseEntity.status(400).build();
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable Long id){
        addressService.deleteById(id);
        return ResponseEntity.status(200).build();

    }

}
