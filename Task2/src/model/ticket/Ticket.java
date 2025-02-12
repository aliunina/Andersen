package model.ticket;

import model.Identifier;
import validation.NullValidator;
import validation.NullableWarning;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Ticket extends Identifier {
	@NullableWarning
	private String id;
	private String concertHall;
	@NullableWarning
	private String eventCode;
	private Timestamp time;
	private Timestamp creationTime;
	private boolean isPromo;
	private StadiumSector stadiumSector;
	private float maxBackpackWeightKg;
	private BigDecimal price = BigDecimal.ZERO;

	public Ticket() {
		this.creationTime = this.generateCreationTime();
		NullValidator.checkNulls(this);
	}

	public Ticket(String id, String concertHall, String eventCode, Timestamp time) {
		this.id = id;
		this.concertHall = concertHall;
		this.eventCode = eventCode;
		this.time = time;
		this.creationTime = this.generateCreationTime();
		NullValidator.checkNulls(this);
	}

	public Ticket(String id, String concertHall, String eventCode, Timestamp time, boolean isPromo,
			StadiumSector stadiumSector, float maxBackpackWeightKg, BigDecimal price) {
		this(id, concertHall, eventCode, time);
		this.isPromo = isPromo;
		this.stadiumSector = stadiumSector;
		this.maxBackpackWeightKg = maxBackpackWeightKg;
		this.price = price;
	}

	public String getId() {
		return this.id;
	}

	public StadiumSector getStadiumSector() {
		return stadiumSector;
	}

	public void setStadiumSector(StadiumSector stadiumSector) {
		this.stadiumSector = stadiumSector;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public void setPrice(BigDecimal price) {
		try {
			if (price.signum() == -1) {
				throw new IllegalArgumentException("Price can't be negative");
			}
			this.price = price;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Ticket ticket = (Ticket) o;
		return id.equals(ticket.id) && eventCode.equals(ticket.eventCode) && isPromo == ticket.isPromo
				&& stadiumSector == ticket.stadiumSector
				&& Float.compare(ticket.maxBackpackWeightKg, maxBackpackWeightKg) == 0
				&& concertHall.equals(ticket.concertHall) && time.equals(ticket.time)
				&& creationTime.equals(ticket.creationTime) && price.equals(ticket.price);
	}

	@Override
	public String toString() {
		return "Ticket{" + "id=" + id + ", concertHall='" + concertHall + '\'' + ", eventCode=" + eventCode + ", time="
				+ time + ", isPromo=" + isPromo + ", stadiumSector=" + stadiumSector + ", maxBackpackWeightKg="
				+ maxBackpackWeightKg + ", price=" + price + ", creationTime=" + creationTime + '}';
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, concertHall, eventCode, time, isPromo, stadiumSector, maxBackpackWeightKg, price);
	}

	private Timestamp generateCreationTime() {
		return new Timestamp(new Date().getTime());
	}

	public void share(String phone) {
		System.out.printf("Share ticket %s by phone - %s\n", this.id, phone);
	}

	public void share(String phone, String email) {
		System.out.printf("Share ticket %s by phone - %s and email - %s\n", this.id, phone, email);
	}
}
