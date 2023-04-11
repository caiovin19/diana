package com.example.desafio.controller;

import com.example.desafio.entities.AddressEntity;
import com.example.desafio.entities.UserEntity;
import com.example.desafio.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping(value = "/usuario")
@RequiredArgsConstructor
public class UserController {

@Autowired
    private  UserService userService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long id) throws Exception{
        UserEntity objUser = userService.findById(id);
        return ResponseEntity.status(200).body(objUser);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() throws Exception{
        List<UserEntity> allUsers = userService.findAll();
        return ResponseEntity.status(200).body(allUsers);
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userObj){
        userService.validationForCriation(userObj);
        return ResponseEntity.status(201).body(userObj);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity userObj){
        UserEntity objUser = userService.findById(id);
        if(!ObjectUtils.isEmpty(objUser)){
            objUser.setIdUsuario(id);
            userService.updateUserByEntity(userObj);
            return ResponseEntity.status(204).body(userObj);
        }
        return ResponseEntity.status(400).build();
    }
    @PutMapping(value = "/{idUsuario}/{idEndereco}")
    public ResponseEntity<UserEntity> updateUserAndAddressById(
            @PathVariable Long idUsuario,
            @PathVariable Long idEndereco,
            @RequestBody UserEntity userObj){
        UserEntity objUser = userService.findById(idUsuario);
        if(!ObjectUtils.isEmpty(objUser)){
            List<UserEntity> usuario = new ArrayList<>();
            usuario.add(objUser);
            Long idEnderecoExistente;
            for(UserEntity u: usuario){
                List<AddressEntity> endereco = u.getListaEndereco();
                for(AddressEntity e: endereco){
                    idEnderecoExistente = e.getIdEndereco();
                    if(idEnderecoExistente.equals(idEndereco)){
                        userService.updateUserByEntity(userObj);
                        return ResponseEntity.status(204).body(userObj);
                    }
                }
            }
        }
        return ResponseEntity.status(400).build();
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.status(200).build();

    }
}
