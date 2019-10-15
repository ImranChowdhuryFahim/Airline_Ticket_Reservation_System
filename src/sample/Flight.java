package sample;

public class Flight implements Comparable<Flight> {
            public Flight(String FName, String From, String To, Integer fare, String SeatClass, String departureTime){
            FlightName = FName;
            this.From = From;
            this.To = To;
            this.fare = fare;
            this.SeatClass = SeatClass;
            this.DepartureTime = departureTime;
        }
        private String FlightName;
        private String From;
        private String To;
        private Integer fare; //in dollar
        private String SeatClass;
        private String DepartureTime;

        public String getFlightName()
        {
            return FlightName;
        }
        public String getFrom() {return To;}
        public String getTo() {return From;}
        public String getDepartureTime() {return DepartureTime;}
        public Integer getFare(){ return fare; }


    @Override
    public int compareTo(Flight o) {
        return getFare().compareTo(o.getFare());
    }
}
