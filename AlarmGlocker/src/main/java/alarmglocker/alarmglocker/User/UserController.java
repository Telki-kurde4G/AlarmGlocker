package alarmglocker.alarmglocker.User;

import alarmglocker.alarmglocker.mappingService.AlarmGlockerMappingService;
import alarmglocker.alarmglocker.mappingService.UserMappingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alarmGlocker/user")
public class UserController {

    private AlarmGlockerMappingService alamGlockerMappingService;

    private UserMappingService userMappingService;

    private UserRepository userRepository;

    private UserService userService;

    @Autowired
    public UserController(AlarmGlockerMappingService alamGlockerMappingService, UserMappingService userMappingService, UserRepository userRepository, UserService userService) {
        this.alamGlockerMappingService = alamGlockerMappingService;
        this.userMappingService = userMappingService;
        this.userRepository = userRepository;
        this.userService = userService;
    }


    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto dto){
        var respone = this.userService.CreatUser(dto);
        return new ResponseEntity<>(respone, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUserList(){
        List<UserDto> getUserDto = this.userService.getUserDtoList();
        return new ResponseEntity<>(getUserDto, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetUserDto> getUserDtoById (@PathVariable Long id){
        var response = this.userService.getUserDtoById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto dto) {
        var response = this.userService.updateUser(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        this.userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
