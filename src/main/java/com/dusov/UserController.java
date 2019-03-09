package com.dusov;

import com.dusov.User;
import com.dusov.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path ="/demo")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    
}
