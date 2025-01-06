package main;

import models.ShortLink;
import services.LinkManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkManager linkManager = new LinkManager("clck.ru", 48, 50);

        System.out.print("Приветствуем Вас в сервисе для создания коротких ссылок! Введите ваш UUID или нажмите Enter для создания нового UUID: ");
        String userUuid = scanner.nextLine();
        if (userUuid.isEmpty()) {
            userUuid = java.util.UUID.randomUUID().toString();
            System.out.println("Ваш новый UUID: " + userUuid);
        }

        while (true) {
            System.out.println("\n1. Создать короткую ссылку");
            System.out.println("2. Переход по короткой ссылке");
            System.out.println("3. Выйти из сервиса");
            System.out.println("4. Изменить лимит переходов по ссылке");
            System.out.println("5. Удалить ссылку");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Введите длинную ссылку: ");
                String originalUrl = scanner.nextLine();

                System.out.print("Введите лимит переходов по ссылке: ");
                int maxVisits = scanner.nextInt();

                System.out.print("Введите время жизни ссылки в часах: ");
                int lifetimeHours = scanner.nextInt();
                scanner.nextLine();

                ShortLink link = linkManager.createShortLink(originalUrl, userUuid, maxVisits, lifetimeHours);
                System.out.println("Короткая ссылка: " + link.getShortUrl());
            } else if (choice == 2) {
                System.out.print("Введите короткую ссылку: ");
                String shortUrl = scanner.nextLine();
                linkManager.visitLink(shortUrl);
            } else if (choice == 3) {
                System.out.println("Благодарим за использование сервиса!");
                break;
            } else if (choice == 4) {
                System.out.print("Введите короткую ссылку: ");
                String shortUrl = scanner.nextLine();

                System.out.print("Введите новый лимит переходов по ссылке: ");
                int newLimit = scanner.nextInt();
                scanner.nextLine();

                linkManager.updateMaxVisits(shortUrl, newLimit, userUuid);
            } else if (choice == 5) {
                System.out.print("Введите короткую ссылку: ");
                String shortUrl = scanner.nextLine();

                linkManager.deleteLink(shortUrl, userUuid);
            } else {
                System.out.println("Ошибка! Пожалуйста, попробуйте снова.");
            }
        }
    }
}