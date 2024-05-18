package com.hackfest.RecommendationSystem.service;

import com.hackfest.RecommendationSystem.dto.MovieDto;
import com.hackfest.RecommendationSystem.dto.RecommendationDTO;
import com.hackfest.RecommendationSystem.repository.MovieRepository;
import com.hackfest.RecommendationSystem.utils.ThreadLocalUtil;
import com.recombee.api_client.RecombeeClient;
import com.recombee.api_client.api_requests.*;
import com.recombee.api_client.bindings.Item;
import com.recombee.api_client.bindings.Recommendation;
import com.recombee.api_client.bindings.RecommendationResponse;
import com.recombee.api_client.bindings.SearchResponse;
import com.recombee.api_client.exceptions.ApiException;
import com.recombee.api_client.util.Region;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.hackfest.RecommendationSystem.constants.RecommendationConstants.USER_MAP;

@Service
public class RecommendationService {
    @Value("${recombee.databaseId}")
    private String databaseId;
    @Value("${recombee.token}")
    private String token;

    RecombeeClient client = null;
    private final MovieRepository movieRepository;
    @Autowired
    ThreadLocalUtil threadLocalUtil;

    public RecommendationService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostConstruct
    public void init() {
        client = new RecombeeClient(databaseId,
                token).setRegion(Region.EU_WEST);
    }

    public String resetDatabase() {
        try {
            client.send(new ResetDatabase()); // Clear everything from the database
            return "Reset Successfully";
        } catch (ApiException e) {
            return "Reset failed";
        }
    }

    public String addUsers() {
        USER_MAP.keySet().forEach(it -> {
            try {
                client.send(new AddUser(it));
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        });
        return "Users Added";
    }

    public String createDatabase() {
        try {
// client.send(new ResetDatabase()); // Clear everything from the database
            client.send(new AddItemProperty("price", "double"));
            client.send(new AddItemProperty("num-cores", "int"));
            client.send(new AddItemProperty("description", "string"));
            client.send(new AddItemProperty("image", "image"));

            // Prepare requests for setting a catalog of computers
            final ArrayList<Request> requests = new ArrayList<>();
            final int NUM = 100;
            final Random rand = new Random();

            for (int i = 0; i < NUM; i++) {
                final String itemId = String.format("computer-%s", i);
                final SetItemValues req = new SetItemValues(
                        itemId,
                        //values:
                        new HashMap<>() {{
                            put("price", 600.0 + 400 * rand.nextDouble());
                            put("num-cores", 1 + rand.nextInt(7));
                            put("description", "Great computer");
                            put("image", String.format("https://examplesite.com/products/%s.jpg", itemId));
                        }}
                ).setCascadeCreate(true); // Use cascadeCreate for creating item
                requests.add(req);
            }
            client.send(new Batch(requests)); // Send catalog to the recommender system

            // Generate some random purchases of items by users
            final double PROBABILITY_PURCHASED = 0.02;
            ArrayList<Request> addPurchaseRequests = new ArrayList<>();
            for (int i = 0; i < NUM; i++)
                for (int j = 0; j < NUM; j++)
                    if (rand.nextDouble() < PROBABILITY_PURCHASED) {
                        AddPurchase req = new AddPurchase(String.format("user-%s", i), String.format("computer-%s", j))
                                .setCascadeCreate(true); //use cascadeCreate to create the users
                        addPurchaseRequests.add(req);
                    }
            client.send(new Batch(addPurchaseRequests)); // Send purchases to the recommender system


            // Get 5 recommendations for user-42, who is currently viewing computer-6
            // Recommend only computers that have at least 3 cores
            RecommendationResponse recommendationResponse = client.send(
                    new RecommendItemsToItem("computer-6", "user-42", 5)
                            .setFilter(" 'num-cores'>=3 "));
            System.out.println("Recommended items with at least 3 processor cores:");
            for (Recommendation rec : recommendationResponse) System.out.println(rec.getId());

            // Recommend only items that are more expensive than currently viewed item (up-sell)
            recommendationResponse = client.send(new RecommendItemsToItem("computer-6", "user-42", 5)
                    .setFilter(" 'price' > context_item[\"price\"] "));

            System.out.println("Recommended up-sell items:");
            for (Recommendation rec : recommendationResponse) System.out.println(rec.getId());


            // Filters, boosters and other settings can be set also in the Admin UI (admin.recombee.com)
            // when scenario is specified
            recommendationResponse = client.send(
                    new RecommendItemsToItem("computer-6", "user-42", 5).setScenario("product_detail")
            );

            // Perform personalized full-text search with a user's search query (e.g. "computers")
            SearchResponse searchResponse = client.send(
                    new SearchItems("user-42", "computers", 5)
            );
            System.out.println("Search matches:");
            for (Recommendation rec : searchResponse) System.out.println(rec.getId());
            return "database Created";
        } catch (ApiException e) {
            e.printStackTrace();
            //Use fallback
            return "Creation failed";
        }
    }

    public String userViewedMovies(String movieId) {
        try {
            String username = threadLocalUtil.getRequestTokenDetails();
            ArrayList<Request> addPurchaseRequests = new ArrayList<>();
            AddPurchase req = new AddPurchase(username, movieId)
                    .setCascadeCreate(true);
            addPurchaseRequests.add(req);
            client.send(new Batch(addPurchaseRequests)); // Send purchases to the recommender system
            return "Movie Viewed";
        } catch (ApiException e) {
            return "Creation Failed";
        }
    }

    public RecommendationDTO recommendations(Integer pageNo) throws ApiException {
        String username = threadLocalUtil.getRequestTokenDetails();
        RecommendationResponse recommendations = client.send(new RecommendItemsToUser(username, pageNo * 10).setReturnProperties(false));
        return new RecommendationDTO(pageNo, movieRepository.findAllByIdIn(Arrays.asList(recommendations.getIds())));
    }

    public String createMovieDatabase() {
        try {
            client.send(new AddItemProperty("title", "string"));
            client.send(new AddItemProperty("genres", "set"));
            client.send(new AddItemProperty("voteAverage", "double"));
            client.send(new AddItemProperty("popularity", "double"));
            return "Creation Completed";
        } catch (ApiException e) {
            return "Creation Failed";
        }
    }

    public String populateDataOnRecombee() {
        List<MovieDto> movieDtoList = movieRepository.findAll().stream().map(MovieDto::new).toList();
        try {
            final ArrayList<Request> moviesData = new ArrayList<>();
            for (MovieDto movie : movieDtoList) {
                final SetItemValues movieData = new SetItemValues(
                        movie.getId(),
                        new HashMap<>() {{
                            put("title", movie.getTitle());
                            put("genres", movie.getGenres());
                            put("voteAverage", movie.getVoteAverage());
                            put("popularity", movie.getPopularity());
                        }}
                ).setCascadeCreate(true); // Use cascadeCreate for creating item
                moviesData.add(movieData);
            }
            client.send(new Batch(moviesData));
            return "Item Added";
        } catch (ApiException e) {
            return "Item Failed";
        }
    }

    public Item[] getAllData() {
        try {
            return client.send(new ListItems().setCount(10).setReturnProperties(true));
        } catch (ApiException e) {
            return null;
        }
    }
}
