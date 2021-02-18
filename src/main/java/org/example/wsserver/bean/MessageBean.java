package org.example.wsserver.bean;

import java.util.Objects;

public class MessageBean {
    private String id;
    private String message;
    private long date;

    public MessageBean(String id, String message, long date) {
        this.id = id;
        this.message = message;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageBean that = (MessageBean) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
