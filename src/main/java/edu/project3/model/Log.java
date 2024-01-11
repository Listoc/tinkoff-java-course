package edu.project3.model;

import java.time.OffsetDateTime;

public record Log(
    String ip,
    String name,
    OffsetDateTime time,
    RequestMethod method,
    String resource,
    int status,
    int bytesSent
) {
}
