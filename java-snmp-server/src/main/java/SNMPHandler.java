package com.tgnms.snmpserver;

import org.snmp4j.*;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.springframework.stereotype.Service;

@Service
public class SNMPHandler {

    public String snmpGet(String ip, String oid) {
        String result = "No Response";
        try {
            Address targetAddress = GenericAddress.parse("udp:" + ip + "/161");
            TransportMapping<?> transport = new DefaultUdpTransportMapping();
            transport.listen();

            CommunityTarget target = new CommunityTarget();
            target.setCommunity(new OctetString("public"));
            target.setAddress(targetAddress);
            target.setRetries(2);
            target.setTimeout(1500);
            target.setVersion(SnmpConstants.version2c);

            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(oid)));
            pdu.setType(PDU.GET);

            Snmp snmp = new Snmp(transport);
            ResponseEvent event = snmp.get(pdu, target);

            if (event != null && event.getResponse() != null) {
                result = event.getResponse().get(0).getVariable().toString();
            }
            snmp.close();
        } catch (Exception e) {
            result = "Error: " + e.getMessage();
        }
        return result;
    }
}
