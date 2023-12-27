package com.libraryapp.libraryapp.common;

import java.time.LocalDate;

public class LoanDto {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;

    public LoanDto(Long id, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}