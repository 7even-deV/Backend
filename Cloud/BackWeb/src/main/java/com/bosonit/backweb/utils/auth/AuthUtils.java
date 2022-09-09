package com.bosonit.backweb.utils.auth;

import java.util.Base64;
import java.util.UUID;

public class AuthUtils {

    // Get client Id from token using base64 encoding and return it.
    public static UUID getId(String token) {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] chunks = token.split("\\.");
        String payload = new String(decoder.decode(chunks[1]));
        String[] chunksPayload = payload.split("\\,");

        return UUID.fromString(chunksPayload[0].substring(8, chunksPayload[0].length() - 1));
    }

    // Get client email from token using base64 encoding and return it.
    public static String getEmail(String token) {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] chunks = token.split("\\.");
        String payload = new String(decoder.decode(chunks[1]));
        String[] chunksPayload = payload.split("\\,");

        return chunksPayload[1].substring(7, chunksPayload[1].length() - 1);
    }
}
