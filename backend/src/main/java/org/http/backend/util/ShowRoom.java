package org.http.backend.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.http.backend.entity.Booking;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShowRoom {

    private String name;
    private List<Boolean> seats;
    private List<Booking> bookings;
}
