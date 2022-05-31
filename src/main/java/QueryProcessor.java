import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.List;

public class QueryProcessor {
    // FILL IN CODE:
    // Add instance variables(s) as needed
    private static HashMap<String, HotelInfo> hotelList;
    // Constructor - it should take a parameter - a reference to the class that stores hotels
    public QueryProcessor(HashMap<String, HotelInfo> hotelList) {
        QueryProcessor.hotelList = hotelList;
    }

    public void processQueries() {
        System.out.println("Enter a query:");
        System.out.println("show hotels");
        System.out.println("search");
        System.out.println("reserve hotelId date numberOfNights");
        System.out.println("review hotelId rating username");
        System.out.println("exit");
        Scanner sc = new Scanner(System.in);
        String query = sc.nextLine();
        while (!query.equals("exit")) {
            // FILL IN CODE to read and parse user's query and print a response
            String[] querys = query.split(" ");
            switch (querys[0]) {
                case "show":
                    showHotels();
                    break;
                case "search":
                    System.out.println("Enter the name of the hotel: ");
                    String hotelName = sc.nextLine();
                    searchHotel(hotelName);
                    break;
                case "reserve":
                    System.out.println("reserve hotelId date numberOfNights");
                    break;
                case "review":
                    System.out.println("review hotelId rating username");
                    break;
                default:
                    break;
            }
            query = sc.nextLine();
        }
        sc.close();
    }

    public static void printLines(List<String> sout_strs) {
        for (String str : sout_strs) {
            System.out.println(str);
        }
    }
    /**
     * Shwo all hotel's name, ID and address. 
     */
    public static void showHotels() {
        for (HotelInfo hotel : hotelList.values()) {
            printLines(HotelsUtil.getHotelInfo(hotel, false));
            System.out.println();
        }
    }

    /**
     * Search hotel by name, print out hotel's information and reviews. 
     * @param hotelName
     */
    public static void searchHotel(String hotelName) {
        HotelInfo hotel = hotelList.get(HotelsUtil.getHotelIDbyName(hotelList, hotelName));
        if (hotel != null) {
            List<String> sout_strs = HotelsUtil.getHotelInfo(hotel, true);
            printLines(sout_strs);
        }
        else
        {
            System.out.println("No such hotel.");
        }
    }

    public static void main(String[] args) throws IOException {
        // FILL IN CODE:
        String hotelFilename = args[0]; // the path to the json file with hotels is given as a command line argument

        // Create an instance of the class that stores hotels and load hotels from the json file
        HashMap<String, HotelInfo> hotelList = HotelsUtil.getHotelListFromJson(hotelFilename);


        hotelList.get("9491356").addReview("Steven", "05-2022", "4", "Just so so. Breakfast is nice");
        hotelList.get("9491356").addReview("Luna", "05-2022", "1", "I need cooooooffeeeee");



        // Add a parameter to the QueryProcessor constructor:
        // pass a reference to your class that contains hotels
        QueryProcessor qp = new QueryProcessor(hotelList);
        qp.processQueries();

        // Note that you are expected to create additional classes and methods

    }



}
