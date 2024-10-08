package com.komanrudden.multiauthapi.controller;

import com.komanrudden.multiauthapi.model.payload.UserResponse;
import com.komanrudden.multiauthapi.security.CurrentUser;
import com.komanrudden.multiauthapi.security.UserPrincipal;
import com.komanrudden.multiauthapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(userService.getUserInfoById(userPrincipal.getId()));
    }

}
