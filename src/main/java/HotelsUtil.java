import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

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
}

class HotelList {
    public List<HotelInfo> sr;
}

