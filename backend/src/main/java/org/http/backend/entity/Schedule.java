package org.http.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.http.backend.util.ShowRoom;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    private String id;
    private Date date;
    private String time;
    private ShowRoom showRoom;
}
