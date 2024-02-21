package wildberries.Services;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import wildberries.entity.PriceEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Configuration
@PropertySource("classpath:application.properties")
public class PriceService {

    @Value("${price.url}")
    public String priceUrl;

    @Value("${price.token}")
    public String priceToken;

    public List<List<Object>> getPrices() throws IOException {
        System.out.println(priceUrl);
        String rawResponse = createGetRequest(priceUrl, "eyJhbGciOiJFUzI1NiIsImtpZCI6IjIwMjMxMjI1djEiLCJ0eXAiOiJKV1QifQ.eyJlbnQiOjEsImV4cCI6MTcyMzQxNzAxOCwiaWQiOiI5MDQxODZmZi04NGZiLTRiN2ItYTc2MS1iNTIwMDIzNWYxMGMiLCJpaWQiOjIyMzMzMjQyLCJvaWQiOjExNzk1MzksInMiOjEwNzM3NDE4MzIsInNpZCI6IjAwN2NlNTFkLTJiYTYtNGQ5ZC1iZjBjLTM0ZTRiMjA2Mjc2NyIsInQiOmZhbHNlLCJ1aWQiOjIyMzMzMjQyfQ.gvg80xf37uSlMFzka3S8TuXl078Cz8IYK-t7qu0du51EXEMhcWXCM0eEkm4yJimNULVKo1I6R8Y3IZm2L5YWBQ").execute().parseAsString();
        System.out.println(rawResponse);
        //
        Gson gson = new Gson();
        PriceEntity[] prices = gson.fromJson(rawResponse, PriceEntity[].class);
        System.out.println(prices[0]);
        //
        LinkedList<List<Object>> result = new LinkedList<>();
        result.add(Arrays.asList("nmId", "price", "discount", "promoCode"));
        for (PriceEntity price : prices) {
            List<Object> currentPrice = new LinkedList<>();
            currentPrice.addAll(Arrays.asList(price.getNmId(),
                    price.getPrice(),
                    price.getDiscount(),
                    price.getPromoCode()));
            result.add(currentPrice);
        }
        //
        return result;
    }

    private HttpRequest createGetRequest(String url, String token) throws IOException {
        HttpRequestFactory requestFactory
                = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(
                new GenericUrl(url));
        HttpHeaders headers = request.getHeaders();
        headers.set("Authorization", token);
        request.setRequestMethod("GET");
        return request;
    }
}
