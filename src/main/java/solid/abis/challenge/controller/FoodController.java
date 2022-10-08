package solid.abis.challenge.controller;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private URL url = new URL("https://fineli.fi/fineli/api/v1/components");

    public FoodController() throws MalformedURLException {
    }

    @GetMapping("")
    public String foods() {
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int resCode = conn.getResponseCode();
            if (resCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + resCode);
            } else {
                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                scanner.close();

                JSONParser parse = new JSONParser();
                JSONArray dataObj = (JSONArray) parse.parse(inline);

                System.out.println(dataObj.toString());

                return dataObj.toString();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
