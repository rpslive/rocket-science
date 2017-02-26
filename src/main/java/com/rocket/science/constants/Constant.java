package com.rocket.science.constants;

/**
 * Created by shamimh on 25/02/17.
 */
public class Constant {


    public class GoogleAPIConstant{

        public static final String API_KEY = "AIzaSyDjxzGNyOGfcQZ0A5q_DGwueTgGgeA-6Tk";
        public static final int MAX_RETRY = 3;


    }


    public class DriverConstants{

        public static final int NUMBER_OF_N_DRIVERS = 3;
        public static final String cabId = "cabId";
        public static final String driverId = "driverId";
        public static final String lat = "lat";
        public static final String lon = "lon";
        public static final String Status= "status";

    }

    public class TrackingService {
        public static final String TrackingServiceURI = "http://localhost:8080/rs/tracker/nearby";
    }

    public class DriverManagement {
        public static final String DriverManagementURI = "http://localhost:8085/dms/driver/locking";
    }
}
