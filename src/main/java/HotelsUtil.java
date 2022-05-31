import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HotelsUtil {
    /**
     * Parse Json data, return Hotel HashMap
     * @param hotelFilename JSON file path
     * @return A HashMap contains all hotels object
     * @throws IOException
     */
    public static HashMap<String, HotelInfo> getHotelListFromJson(String hotelFilename) throws IOException {
        HashMap<String, HotelInfo> HotelList = new HashMap<String, HotelInfo>();
        // Initial GSON builder
        Gson gson = new GsonBuilder().create();
        // Set Json file path
        Path path = new File(hotelFilename).toPath();
        // Put data into reader buffer 
        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            // Use GSON fill temp HotelList
            HotelList hotels = gson.fromJson(reader, HotelList.class); 
            // Put each hotel object into HashMap
            for (HotelInfo hotel : hotels.sr) {
                HotelList.put(hotel.getId(), hotel);
            }
        }
        return HotelList;
    }
    /**
     * Return a list strings contain hotel's information. Use showReviews to add reviews into the list. 
     * @param hotel
     * @param showReviews
     * @return List of String for print use. Eash string indicate a line to print out. 
     */
    public static List<String> getHotelInfo(HotelInfo hotel, boolean showReviews) {
        List<String> sout_strs = new ArrayList<>();
        // add first line text: Hotel name + : + hotel ID
        sout_strs.add(new StringBuilder().append(hotel.getName()).append(": ").append(hotel.getId()).toString());
        // add second line text: Hotel address
        sout_strs.add(new StringBuilder().append(hotel.getCity()).append(" ").append(hotel.getAddress()).toString());
        // when showReviews is true and review list not empty, add reviews into return list.  
        if (hotel.getReviewList() != null) {
            if (showReviews && !hotel.getReviewList().isEmpty()) {
                sout_strs.add("Reviews:");
                // get review list
                List<Review> reviewList = hotel.getReviewList();
                for (Review review : reviewList) {
                    // First line of review: Guest name + date
                    sout_strs.add(new StringBuilder()
                            .append("Review by ").append(review.getGuestName())
                            .append(" on ").append(review.getDate()).toString());
                    // Second line of review: Rating
                    sout_strs.add(new StringBuilder().append("Rating: ").append(review.getRating()).toString());
                    // Third line of review: Review text
                    sout_strs.add(review.getReview());
                    sout_strs.add("-----------------------------------");
                }
            }
        }        
        return sout_strs;
    }

    

    public static String getHotelIDbyName(HashMap<String, HotelInfo> hotelList, String hotelName) {
        for (HotelInfo hotel : hotelList.values()) {
            if (hotel.getName().equals(hotelName)) {
                return hotel.getId();
            }
        }
        return null;
    }
}

class HotelList {
    public List<HotelInfo> sr;
}

