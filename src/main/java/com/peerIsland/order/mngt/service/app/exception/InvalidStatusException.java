package com.peerIsland.order.mngt.service.app.exception;

public class InvalidStatusException extends RuntimeException {
    public InvalidStatusException(String status) {
        super("Invalid Status field name "+status);
    }
}
