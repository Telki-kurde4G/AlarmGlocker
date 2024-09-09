package alarmglocker.alarmglocker.AlarmGlocker;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alarmGlocker")
public class AlarmGlockerController {

    @Autowired
    private AlarmGlockerService alarmGlockerService;

    @PostMapping("/creatAlarmGlocker")
    public ResponseEntity<AlarmGlockerDto> createAlarmGlocker(@Valid @RequestBody AlarmGlockerDto dto) {
        AlarmGlockerDto createdAlarm = alarmGlockerService.createAlarmGlocker(dto);
        if (createdAlarm != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAlarm);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<AlarmGlockerDto>> getAllAlarms() {
        List<AlarmGlockerDto> alarms = alarmGlockerService.getAllAlarms();
        return ResponseEntity.ok(alarms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlarmGlockerDto> getAlarmById(@PathVariable Long id) {
        AlarmGlockerDto alarm = alarmGlockerService.getAlarmById(id);
        if (alarm != null){
            return ResponseEntity.ok(alarm);
        }
         return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlarmGlockerDto> updateAlarmGlocker(@RequestBody AlarmGlockerDto dto) {
        AlarmGlockerDto updatedAlarm = alarmGlockerService.updateAlarmGlocker(dto);
        if ( updatedAlarm != null ){
            return ResponseEntity.ok(updatedAlarm);
        }
         return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlarmGlocker(@PathVariable Long id) {
        if (alarmGlockerService.deleteAlarmGlocker(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
