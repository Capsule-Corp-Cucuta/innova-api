package co.edu.ufps.innova.user.web.controller;

import java.util.List;
import io.swagger.annotations.*;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import co.edu.ufps.innova.user.domain.dto.User;
import org.springframework.web.bind.annotation.*;
import co.edu.ufps.innova.user.domain.service.UserService;
import co.edu.ufps.innova.authentication.domain.dto.PasswordChange;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    @ApiOperation("Save a new User")
    @ApiResponse(code = 201, message = "Created")
    public ResponseEntity<User> save(@RequestBody User user) {
        return new ResponseEntity<>(service.save(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update an existing User")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<HttpStatus> update(@PathVariable String id, @RequestBody User user) {
        return service.update(id, user) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    @ApiOperation("List all Users")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get an User by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<User> findById(@PathVariable String id) {
        return service.findById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/email/{email}")
    @ApiOperation("Get an User by email")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<User> findByEmail(@PathVariable String email) {
        return service.findByEmail(email)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete an exiting User")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<HttpStatus> delete(@PathVariable String id) {
        return service.delete(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}/change-state")
    @ApiOperation("Change the state of the an existing User")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<HttpStatus> changeState(@PathVariable String id) {
        return service.changeState(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}/change-password")
    @ApiOperation("Change the password of the an existing User")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<HttpStatus> changePassword(@PathVariable("id") String id, @RequestBody PasswordChange passwordChange) {
        return service.changePassword(id, passwordChange.getOldPassword(), passwordChange.getNewPassword())
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/recover-password")
    @ApiOperation("Change the password of the an existing User and send to him an email with the new password")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<HttpStatus> recoverPassword(@RequestBody String email) {
        JsonObject jsonObject = JsonParser.parseString(email).getAsJsonObject();
        return service.recoverPassword(jsonObject.get("email").getAsString())
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
