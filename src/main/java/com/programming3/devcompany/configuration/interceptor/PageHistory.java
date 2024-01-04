package com.programming3.devcompany.configuration.interceptor;

import java.time.LocalDateTime;

public class PageHistory {
    private String page;
    private LocalDateTime timestamp;

    public PageHistory(String page, LocalDateTime timestamp) {
        this.page = page;
        this.timestamp = timestamp;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
