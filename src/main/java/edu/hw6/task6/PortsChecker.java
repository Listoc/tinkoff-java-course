package edu.hw6.task6;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings({"MagicNumber", "MultipleStringLiterals", "RegexpSinglelineJava"})
public class PortsChecker {
    private static final Map<Integer, String> SERVICES_BY_PORTS;

    static {
        SERVICES_BY_PORTS = new LinkedHashMap<>();
        SERVICES_BY_PORTS.put(135, "EPMAP");
        SERVICES_BY_PORTS.put(137, "NETBIOS-NS");
        SERVICES_BY_PORTS.put(138, "NETBIOS-DGM");
        SERVICES_BY_PORTS.put(139, "NETBIOS-SSN");
        SERVICES_BY_PORTS.put(445, "MICROSOFT-DS");
        SERVICES_BY_PORTS.put(1900, "Microsoft SSDP Enables discovery of UPnP devices");
        SERVICES_BY_PORTS.put(3702, "WS-Discovery");
        SERVICES_BY_PORTS.put(5050, "Yahoo! Messenger");
        SERVICES_BY_PORTS.put(5353, "Multicast DNS");
        SERVICES_BY_PORTS.put(5355, "LLMNR");
    }

    public static void getPorts() {
        System.out.println("Протокол Порт  Сервис");
        for (var port : SERVICES_BY_PORTS.keySet()) {
            try (var ignored = new ServerSocket(port)) {
                System.out.printf("%-8s %d\n", "TCP", port);
            } catch (Exception e) {
                System.out.printf("%-8s %-5d %s\n", "TCP", port, SERVICES_BY_PORTS.get(port));
            }

            try (var ignored = new DatagramSocket(port)) {
                System.out.printf("%-8s %d\n", "UDP", port);
            } catch (Exception e) {
                System.out.printf("%-8s %-5d %s\n", "UDP", port, SERVICES_BY_PORTS.get(port));
            }
        }
    }

    private PortsChecker() {}
}
