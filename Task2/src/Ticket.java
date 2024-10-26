import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Ticket {
    private short id;
    private String concertHall;
    private short eventCode;
    private Timestamp time;
    private Timestamp creationTime;
    private boolean isPromo;
    private char stadiumSector;
    private float maxBackpackWeightKg;
    private BigDecimal price;

    public Ticket(short id) {
        this.id = id;
        this.creationTime = new Timestamp(new Date().getTime());
    }

    public Ticket(short id, String concertHall, short eventCode, Timestamp time) {
        this.id = id;
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.time = time;
        this.creationTime = new Timestamp(new Date().getTime());
    }

    public Ticket(short id, String concertHall, short eventCode, Timestamp time,
                  boolean isPromo, char stadiumSector, float maxBackpackWeightKg, BigDecimal price) {
        this.id = id;
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.time = time;
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxBackpackWeightKg = maxBackpackWeightKg;
        this.price = price;
        this.creationTime = new Timestamp(new Date().getTime());
    }

    public short getId() {
        return id;
    }

    public String getConcertHall() {
        return concertHall;
    }

    public short getEventCode() {
        return eventCode;
    }

    public Timestamp getTime() {
        return time;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public boolean isPromo() {
        return isPromo;
    }

    public char getStadiumSector() {
        return stadiumSector;
    }

    public float getMaxBackpackWeightKg() {
        return maxBackpackWeightKg;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setId(short id) {
        this.id = id;
    }

    public void setConcertHall(String concertHall) {
        this.concertHall = concertHall;
    }

    public void setEventCode(short eventCode) {
        this.eventCode = eventCode;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setPromo(boolean promo) {
        isPromo = promo;
    }

    public void setStadiumSector(char stadiumSector) {
        this.stadiumSector = stadiumSector;
    }

    public void setMaxBackpackWeightKg(float maxBackpackWeightKg) {
        this.maxBackpackWeightKg = maxBackpackWeightKg;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && eventCode == ticket.eventCode &&
                isPromo == ticket.isPromo && stadiumSector == ticket.stadiumSector &&
                Float.compare(ticket.maxBackpackWeightKg, maxBackpackWeightKg) == 0 &&
                concertHall.equals(ticket.concertHall) && time.equals(ticket.time) &&
                creationTime.equals(ticket.creationTime) && price.equals(ticket.price);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode=" + eventCode +
                ", time=" + time +
                ", isPromo=" + isPromo +
                ", stadiumSector=" + stadiumSector +
                ", maxBackpackWeightKg=" + maxBackpackWeightKg +
                ", price=" + price +
                ", creationTime=" + creationTime +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, concertHall, eventCode, time,
                isPromo, stadiumSector, maxBackpackWeightKg);
    }
}
