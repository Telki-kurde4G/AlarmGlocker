package alarmglocker.alarmglocker.User;

import alarmglocker.alarmglocker.exceptions.RessourceFoundException;
import alarmglocker.alarmglocker.exceptions.RessourceNotFoundException;
import alarmglocker.alarmglocker.mappingService.AlarmGlockerMappingService;
import alarmglocker.alarmglocker.mappingService.UserMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private AlarmGlockerMappingService alamGlockerMappingService;

    private UserMappingService userMappingService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(AlarmGlockerMappingService alamGlockerMappingService, UserMappingService userMappingService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.alamGlockerMappingService = alamGlockerMappingService;
        this.userMappingService = userMappingService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDto> getUserDtoList (){
        var getUserDto = this.userMappingService.userEntityListToDtoList(this.userRepository.findAll());
        return getUserDto;
    }

    public GetUserDto getUserDtoById(Long id){
        Optional<UserEntity> entity = this.userRepository.findById(id);
        if (entity.isEmpty()){
            throw new RessourceNotFoundException("user by id " + id + "can not found");
        }

        GetUserDto dto = new GetUserDto();
        dto.setUserName(entity.get().getUserName());
        dto.setEmail(entity.get().getEmail());
        return dto;

    }

    public UserDto CreatUser(UserDto getDto){
        Optional<UserEntity> existingUser = userRepository.findByEmail(getDto.getEmail());
        if (existingUser.isPresent()) {
            throw new RessourceFoundException("User the this email exist!");
        }

        UserEntity setUserEntity = this.userMappingService.userDtoToEntity(getDto);
        this.userRepository.save(setUserEntity);

        return this.userMappingService.userEntityToDto(setUserEntity);
    }
    public UserDto updateUser(UserDto getDto) {
        Long id = getDto.getId();
        Optional<UserEntity> existingUser = this.userRepository.findById(id);
        if (existingUser.isEmpty()) {
            throw new RessourceNotFoundException("User with ID " + id  + " not found");
        }

        UserEntity userEntity = existingUser.get();
        userEntity.setUserName(getDto.getUserName());
        userEntity.setEmail(getDto.getEmail());
        userEntity.setPasswordHash(passwordEncoder.encode(getDto.getPasswordHash()));
        this.userRepository.save(userEntity);

        return this.userMappingService.userEntityToDto(userEntity);
    }

    public void deleteUserById(Long id) {
        Optional<UserEntity> existingUser = this.userRepository.findById(id);
        if (existingUser.isEmpty()) {
            throw new RessourceNotFoundException("User with ID " + id + " not found");
        }

        this.userRepository.deleteById(id);
    }

}
