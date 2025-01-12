import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class URLShortener {

    private static final Map<String, String> urlMap = new HashMap<>();
    private static final String baseURL = "http://127.0.0.1:8080/";


    public static String generateShortURL() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder url = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            url.append(characters.charAt(random.nextInt(characters.length())));
        } 

        return url.toString();
    }

    public static String urlShortener(String originalURL) { 
        
        for (Map.Entry<String, String> entry : urlMap.entrySet()) {
            if (entry.getValue().equals(originalURL)) {
                return entry.getKey();
            }
        }

        String shortURL = generateShortURL();
        urlMap.put(shortURL, originalURL);
        return shortURL;
    }

    

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Server is Running on Port 8080...");

        while (true) {
            Socket client = server.accept();
            InputStreamReader isr = new InputStreamReader(client.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            OutputStream oStream = client.getOutputStream();
            String line = reader.readLine();

            if (line == null) { 
                client.close();
                continue;
            }

            if (line.startsWith("GET / ")) {
                String html = serveHTML("../public/index.html");
                String response = response(200, html);
                oStream.write(response.getBytes("UTF-8"));
            }

           else if (line.startsWith("GET /shorten?url=")) {
            String url = line.split(" ")[1].split("=")[1];
            String decodedURL = URLDecoder.decode(url, "UTF-8");
            String shortURL = urlShortener(decodedURL);
            String html = serveHTML("../public/shortener.html");
            String updatedHTML = html.replace("%SHORT_URL%", baseURL + shortURL);
            String response = response(200, updatedHTML);
            oStream.write(response.getBytes("UTF-8"));
           }

           else if (line.startsWith("GET /")) {
            String url = line.split(" ")[1].substring(1);
            String originalURL = urlMap.get(url);

            if (originalURL != null) {
                String response = "HTTP 302 Found\r\n" + "Location: " + originalURL + "\r\n\r\n";
                oStream.write(response.getBytes("UTF-8"));
            } else {
                String html = serveHTML("../public/404.html");
                String response = response(404, html);
                oStream.write(response.getBytes("UTF-8"));
            }

           }

            client.close();
        }
    }


    private static String serveHTML(String fileName) throws FileNotFoundException, IOException {
        StringBuilder htmBuilder = new StringBuilder();
        String htmlLine;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((htmlLine = reader.readLine()) != null) {
                htmBuilder.append(htmlLine).append("\n");
            }
            return htmBuilder.toString();
        }
    }

    private static String response (int statusCode, String html) {
        String response ;
        if (statusCode == 200) {
            response = "HTTP/1.1 200 OK\r\n" + "Content-Type:text/html\r\n\r\n" + html;
            return response;
        } 
        else if (statusCode == 404) {
            response = "HTTP/1.1 404 Not Found\r\n" + "Content-Type:text/html\r\n\r\n" + html;
            return response;
        }
        return null;
    }
}