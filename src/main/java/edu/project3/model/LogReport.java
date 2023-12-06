package edu.project3.model;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("MagicNumber")
public record LogReport(
    Map<String, String> generalInfo,
    Map<Integer, Long> responseCodes,
    Map<String, Long> requestedResources,
    Map<RequestMethod, Long> methods,
    Map<String, Long> ip) {
    public final static Map<Integer, String> RESPONSE_CODE_NAMES;

    static {
        RESPONSE_CODE_NAMES = new HashMap<>();
        RESPONSE_CODE_NAMES.put(100, "Continue");
        RESPONSE_CODE_NAMES.put(101, "Switching Protocol");
        RESPONSE_CODE_NAMES.put(102, "Processing");
        RESPONSE_CODE_NAMES.put(103, "Early Hints");
        RESPONSE_CODE_NAMES.put(200, "OK");
        RESPONSE_CODE_NAMES.put(201, "Created");
        RESPONSE_CODE_NAMES.put(202, "Accepted");
        RESPONSE_CODE_NAMES.put(203, "Non-Authoritative Information");
        RESPONSE_CODE_NAMES.put(204, "No Content");
        RESPONSE_CODE_NAMES.put(205, "Reset Content");
        RESPONSE_CODE_NAMES.put(206, "Partial Content");
        RESPONSE_CODE_NAMES.put(300, "Multiple Choice");
        RESPONSE_CODE_NAMES.put(301, "Moved Permanently");
        RESPONSE_CODE_NAMES.put(302, "Found");
        RESPONSE_CODE_NAMES.put(303, "See Other");
        RESPONSE_CODE_NAMES.put(304, "Not Modified");
        RESPONSE_CODE_NAMES.put(305, "Use Proxy");
        RESPONSE_CODE_NAMES.put(306, "Switch Proxy");
        RESPONSE_CODE_NAMES.put(307, "Temporary Redirect");
        RESPONSE_CODE_NAMES.put(308, "Permanent Redirect");
        RESPONSE_CODE_NAMES.put(400, "Bad Request");
        RESPONSE_CODE_NAMES.put(401, "Unauthorized");
        RESPONSE_CODE_NAMES.put(402, "Payment Required");
        RESPONSE_CODE_NAMES.put(403, "Forbidden");
        RESPONSE_CODE_NAMES.put(404, "Not Found");
        RESPONSE_CODE_NAMES.put(405, "Method Not Allowed");
        RESPONSE_CODE_NAMES.put(406, "Not Acceptable");
        RESPONSE_CODE_NAMES.put(407, "Proxy Authentication Required");
        RESPONSE_CODE_NAMES.put(408, "Request Timeout");
        RESPONSE_CODE_NAMES.put(409, "Conflict");
        RESPONSE_CODE_NAMES.put(410, "Gone");
        RESPONSE_CODE_NAMES.put(411, "Length Required");
        RESPONSE_CODE_NAMES.put(412, "Precondition Failed");
        RESPONSE_CODE_NAMES.put(413, "Request Entity Too Large");
        RESPONSE_CODE_NAMES.put(414, "Request-URI Too Long");
        RESPONSE_CODE_NAMES.put(415, "Unsupported Media Type");
        RESPONSE_CODE_NAMES.put(416, "Requested Range Not Satisfiable");
        RESPONSE_CODE_NAMES.put(417, "Expectation Failed");
        RESPONSE_CODE_NAMES.put(500, "Internal Server Error");
        RESPONSE_CODE_NAMES.put(501, "Not Implemented");
        RESPONSE_CODE_NAMES.put(502, "Bad Gateway");
        RESPONSE_CODE_NAMES.put(503, "Service Unavailable");
        RESPONSE_CODE_NAMES.put(504, "Gateway Timeout");
        RESPONSE_CODE_NAMES.put(505, "HTTP Version Not Supported");
    }
}
