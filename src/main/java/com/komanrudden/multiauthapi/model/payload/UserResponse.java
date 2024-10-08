package com.komanrudden.multiauthapi.model.payload;

import com.komanrudden.multiauthapi.model.enums.AuthProvider;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    private AuthProvider authProvider;
    private String name;
    private String imageUrl;
}
