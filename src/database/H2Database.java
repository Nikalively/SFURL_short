package database;

import models.ShortLink;

import java.util.HashMap;
import java.util.Map;

public class H2Database {
    private static final Map<String, ShortLink> links = new HashMap<>();

    public static void saveLink(ShortLink link) {
        links.put(link.getShortUrl(), link);
    }

    public static ShortLink getLink(String shortUrl) {
        return links.get(shortUrl);
    }

    public static void deleteLink(String shortUrl) {
        links.remove(shortUrl);
    }

    public static Map<String, ShortLink> getAllLinks() {
        return links;
    }

    public static void cleanUpExpiredLinks() {
        links.values().removeIf(ShortLink::isExpired);
    }
}
