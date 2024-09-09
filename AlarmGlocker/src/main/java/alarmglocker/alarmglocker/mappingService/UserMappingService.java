package alarmglocker.alarmglocker.mappingService;

import alarmglocker.alarmglocker.User.UserDto;
import alarmglocker.alarmglocker.User.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserMappingService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserDto userEntityToDto (UserEntity entity){
        UserDto dto = new UserDto();
        dto.setUserName(entity.getUserName());
        dto.setEmail(entity.getEmail());
        dto.setPasswordHash(entity.getPasswordHash());
        return dto;
    }

    public UserEntity userDtoToEntity ( UserDto dto){
        UserEntity entity = new UserEntity();
        entity.setUserName(dto.getUserName());
        entity.setEmail(dto.getEmail());
        String encryptedPassword = passwordEncoder.encode(dto.getPasswordHash());
        entity.setPasswordHash(encryptedPassword);
        return entity;
    }

    public List<UserDto> userEntityListToDtoList (List<UserEntity> entityList){
        LinkedList<UserDto> userDtos = new LinkedList<>();
        for (UserEntity entity : entityList){
            UserDto dto = new UserDto();
            dto.setUserName(entity.getUserName());
            dto.setEmail(entity.getEmail());
            userDtos.add(dto);
        }
        return userDtos;

    }
}
