package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        // Создание HTTP-сервера на порту 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Создание контекста для обработки запросов
        server.createContext("/", new MyHandler());

        // Запуск сервера
        server.setExecutor(null); // используется пул потоков по умолчанию
        server.start();
        System.out.println("Server is running on port 8080");
    }

    // Обработчик запросов
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            // Отправка ответа клиенту
            String response = "Hello, this is a simple Java HTTP server!";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}