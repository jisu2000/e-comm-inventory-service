package org.jisu.e_comm_inventory_service.controller;

import org.jisu.e_comm_inventory_service.external.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class TestController {

    @Autowired
    private AuthService authService;

    @GetMapping("/user")
    public ResponseEntity<?> getUserFromToken(@RequestHeader("Authorization") String authHeader) throws JsonMappingException, JsonProcessingException{
        return new ResponseEntity<>(authService.getUserFromHeader(authHeader),HttpStatus.OK);
    }
}
