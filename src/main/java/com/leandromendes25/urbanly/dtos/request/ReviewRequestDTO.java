package com.leandromendes25.urbanly.dtos.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;


public record ReviewRequestDTO(@Min(0) @Max(5) Integer ratings, String comments) {
}
