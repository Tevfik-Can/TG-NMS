

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestCommandController {

    @Autowired
    private SNMPHandler snmpHandler;

    @Autowired
    private KafkaPublisher kafkaPublisher;

    @PostMapping("/snmp/{ip}/get")
    public String performSnmpGet(@PathVariable String ip, @RequestParam String oid) {
        return snmpHandler.snmpGet(ip, oid);
    }

    @PostMapping("/event")
    public String publishEvent(@RequestParam String message) {
        kafkaPublisher.sendMessage("device-events", message);
        return "Event published.";
    }
}
