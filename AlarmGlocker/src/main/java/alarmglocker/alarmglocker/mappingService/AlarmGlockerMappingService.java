package alarmglocker.alarmglocker.mappingService;

import alarmglocker.alarmglocker.AlarmGlocker.AlarmGlockerDto;
import alarmglocker.alarmglocker.AlarmGlocker.AlarmGlockerEntity;
import alarmglocker.alarmglocker.User.UserEntity;
import alarmglocker.alarmglocker.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlarmGlockerMappingService {

    @Autowired
    private UserRepository userRepository;
    public AlarmGlockerEntity alarmGlockerDtoToEntity (AlarmGlockerDto dto){
        Optional<UserEntity> getUser = this.userRepository.findById(dto.getUserId());

        if (getUser.isPresent()){
            AlarmGlockerEntity entity = new AlarmGlockerEntity();
            entity.setUser(getUser.get());
            entity.setDate(dto.getDate());
            entity.setOne(dto.isOne());
            entity.setLabel(dto.getLabel());
            entity.setRecurring(dto.isRecurring());
            return entity;
        }
        return null;
    }

    public AlarmGlockerDto alarmGlockerEntityToDto(AlarmGlockerEntity entity) {
        AlarmGlockerDto dto = new AlarmGlockerDto();

        if (entity.getUser() != null) {
            dto.setUserId(entity.getUser().getId());
        }

        dto.setDate(entity.getDate());
        dto.setOne(entity.isOne());
        dto.setLabel(entity.getLabel());
        dto.setRecurring(entity.isRecurring());
        return dto;
    }
}
