package services;

import database.H2Database;
import models.ShortLink;

import java.time.LocalDateTime;
import java.util.UUID;

public class LinkManager {
    private final String domain;
    private final int maxHours;
    private final int maxVisits;

    public LinkManager(String domain, int maxHours, int maxVisits) {
        this.domain = domain;
        this.maxHours = maxHours;
        this.maxVisits = maxVisits;
    }

    public ShortLink createShortLink(String originalUrl, String userUuid, int userMaxVisits, int userLifetime) {
        String shortUrl = domain + "/" + UUID.randomUUID().toString().substring(0, 6);

        int finalMaxVisits = Math.max(userMaxVisits, maxVisits);
        int finalLifetime = Math.min(userLifetime, maxHours);

        LocalDateTime expiryTime = LocalDateTime.now().plusHours(finalLifetime);

        ShortLink shortLink = new ShortLink(shortUrl, originalUrl, userUuid, finalMaxVisits, expiryTime);
        H2Database.saveLink(shortLink);

        return shortLink;
    }

    public void visitLink(String shortUrl) {
        ShortLink link = H2Database.getLink(shortUrl);
        if (link == null) {
            System.out.println("Ссылка не существует!");
            return;
        }

        if (!link.canVisit()) {
            if (link.isExpired()) {
                System.out.println("Закончился срок действия ссылки.");
            } else {
                System.out.println("Закончился лимит переходов по ссылке.");
            }
            return;
        }

        link.incrementVisits();

        try {
            System.out.println("Перенаправление на: " + link.getOriginalUrl());
            java.awt.Desktop.getDesktop().browse(new java.net.URI(link.getOriginalUrl()));
        } catch (Exception e) {
            System.err.println("Произошла ошибка при открытии ссылки: " + e.getMessage());
        }
    }

    public void updateMaxVisits(String shortUrl, int newLimit, String userUuid) {
        ShortLink link = H2Database.getLink(shortUrl);
        if (link != null && link.getUserUuid().equals(userUuid)) {
            link.setMaxVisits(newLimit);
            System.out.println("Обновление лимита переходов.");
        } else {
            System.out.println("Вы не можете редактировать данную ссылку.");
        }
    }

    public void deleteLink(String shortUrl, String userUuid) {
        ShortLink link = H2Database.getLink(shortUrl);
        if (link != null && link.getUserUuid().equals(userUuid)) {
            H2Database.deleteLink(shortUrl);
            System.out.println("Ссылка удалена.");
        } else {
            System.out.println("Вы не можете удалить эту ссылку.");
        }
    }
}
