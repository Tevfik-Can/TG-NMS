
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        // Obtain the Spring ApplicationContext
        org.springframework.context.ApplicationContext context = SpringApplication.run(Main.class, args);

        // Get the SNMPHandler bean from Spring
        SNMPHandler snmpHandler = context.getBean(SNMPHandler.class);

        // Call the SNMP GET function
        String response = snmpHandler.snmpGet("192.168.100.1", "1.3.6.1.2.1.1.1.0");

        // Print the result
        System.out.println("SNMP Response: " + response);

         // Write response to file in project root
        String projectRoot = Paths.get("").toAbsolutePath().toString(); 
        String filePath = projectRoot + "/snmp_output.txt";

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("SNMP Response: " + response + System.lineSeparator());
            System.out.println("Output written to: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
