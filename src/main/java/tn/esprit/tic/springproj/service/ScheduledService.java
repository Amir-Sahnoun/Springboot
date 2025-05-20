package tn.esprit.tic.springproj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tic.springproj.model.Commande;
import tn.esprit.tic.springproj.repository.CommandeRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ScheduledService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Scheduled(cron = "*/30 * * * * ?")
    public void notifyPendingOrders() {
        LocalDateTime cutoffTime = LocalDateTime.now().minusHours(24);
        List<Commande> oldOrders = commandeRepository.findOrdersBefore(cutoffTime);

        for (Commande order : oldOrders) {
            // Logic to send notification
            System.out.println("Notification sent for order ID: " + order.getIdCommande());
        }
    }
}