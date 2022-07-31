package com.example.olioht;

import java.io.Serializable;

public class Films implements Serializable {
    private String title;
    private String event_id;
    private String id;
    private String comment;

    public Films() {
        this.title = title;
        this.event_id = event_id;
        this.id = id;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return title + ";" + event_id + ";" + id + ";" + comment;
    }

    // --Commented out by Inspection (31.7.2022 11.50):public String getTitle() {return title;}

    // --Commented out by Inspection (31.7.2022 11.50):// --Commented out by Inspection (31.7.2022 11.50):public String getEventId() {return event_id;}

    public String getId() {return id;}

    // --Commented out by Inspection (31.7.2022 11.50):public String getComment() {return comment;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

