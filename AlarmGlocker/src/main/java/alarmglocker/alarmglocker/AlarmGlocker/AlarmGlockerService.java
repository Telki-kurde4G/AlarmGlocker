package alarmglocker.alarmglocker.AlarmGlocker;
import alarmglocker.alarmglocker.mappingService.AlarmGlockerMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlarmGlockerService {
    @Autowired
    private AlarmGlockerRepository alarmGlockerRepository;

    @Autowired
    private AlarmGlockerMappingService alarmGlockerMappingService;

    @Transactional
    public AlarmGlockerDto createAlarmGlocker(AlarmGlockerDto dto) {
        AlarmGlockerEntity entity = alarmGlockerMappingService.alarmGlockerDtoToEntity(dto);
        if (entity == null) {
            return null;
        }
        AlarmGlockerEntity savedEntity = alarmGlockerRepository.save(entity);
        return alarmGlockerMappingService.alarmGlockerEntityToDto(savedEntity);
    }

    public List<AlarmGlockerDto> getAllAlarms() {
        List<AlarmGlockerEntity> entities = alarmGlockerRepository.findAll();
        return entities.stream()
                .map(alarmGlockerMappingService::alarmGlockerEntityToDto)
                .toList();
    }

    public AlarmGlockerDto getAlarmById(Long id) {
        Optional<AlarmGlockerEntity> entityOpt = alarmGlockerRepository.findById(id);
        if (entityOpt.isPresent()){
            return alarmGlockerMappingService.alarmGlockerEntityToDto(entityOpt.get());
        }
        return null;
    }

    @Transactional
    public AlarmGlockerDto updateAlarmGlocker(AlarmGlockerDto dto) {
        Optional<AlarmGlockerEntity> entityOpt = alarmGlockerRepository.findById(dto.getId());
        if (entityOpt.isPresent()) {
            AlarmGlockerEntity entity = entityOpt.get();
            this.alarmGlockerMappingService.alarmGlockerEntityToDto(entity);

            AlarmGlockerEntity updatedEntity = alarmGlockerRepository.save(entity);
            return alarmGlockerMappingService.alarmGlockerEntityToDto(updatedEntity);
        }
        return null;
    }

    @Transactional
    public boolean deleteAlarmGlocker(Long id) {
        if (alarmGlockerRepository.existsById(id)) {
            alarmGlockerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
