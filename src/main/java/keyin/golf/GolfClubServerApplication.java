package keyin.golf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;

@SpringBootApplication
@EntityScan(basePackages = {"keyin.golf.Member", "keyin.golf.Tournament"})
public class GolfClubServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GolfClubServerApplication.class, args);

        try {
            String serviceType = "_golfclubserver._tcp.local.";
            String serviceName = "GolfClubServer";
            int port = 8080;

            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            ServiceInfo serviceInfo = ServiceInfo.create(serviceType, serviceName, port, "GolfClub Server");
            jmdns.registerService(serviceInfo);

            System.out.println("Service registered: " + serviceType + " at port " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}