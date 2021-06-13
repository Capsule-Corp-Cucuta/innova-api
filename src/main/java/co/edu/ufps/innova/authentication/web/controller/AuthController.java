package co.edu.ufps.innova.authentication.web.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.ufps.innova.authentication.web.security.JWTUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import co.edu.ufps.innova.authentication.domain.dto.AuthenticationRequest;
import co.edu.ufps.innova.authentication.domain.dto.AuthenticationResponse;
import org.springframework.security.authentication.BadCredentialsException;
import co.edu.ufps.innova.authentication.domain.service.InnovaUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@RestController
@Api(tags = "auth")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JWTUtil jwtUtil;
    private final InnovaUserDetailsService service;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    @ApiOperation("Login")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden")
    })
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            UserDetails userDetails = service.loadUserByUsername(request.getEmail());
            return new ResponseEntity<>(new AuthenticationResponse(
                    jwtUtil.generateToken(userDetails), userDetails.getAuthorities()), HttpStatus.OK
            );
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
