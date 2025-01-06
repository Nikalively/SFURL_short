package models;

import java.time.LocalDateTime;

public class ShortLink {
    private String shortUrl;
    private String originalUrl;
    private String userUuid;
    private int maxVisits;
    private int visitCount;
    private LocalDateTime expiryTime;

    public ShortLink(String shortUrl, String originalUrl, String userUuid, int maxVisits, LocalDateTime expiryTime) {
        this.shortUrl = shortUrl;
        this.originalUrl = originalUrl;
        this.userUuid = userUuid;
        this.maxVisits = maxVisits;
        this.visitCount = 0;
        this.expiryTime = expiryTime;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public int getMaxVisits() {
        return maxVisits;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void incrementVisits() {
        visitCount++;
    }

    public void setMaxVisits(int maxVisits) {
        this.maxVisits = maxVisits;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryTime);
    }

    public boolean canVisit() {
        return visitCount < maxVisits && !isExpired();
    }
}