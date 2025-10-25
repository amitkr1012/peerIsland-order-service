package com.peerIsland.order.mngt.service.app.exception;

import com.peerIsland.order.mngt.service.app.dto.ResultStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

import static com.peerIsland.order.mngt.service.app.dto.Status.FAILED;

@ControllerAdvice
public class PeerIslandOrderExceptionAdvice {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResultStatus> handleNotFound(NoSuchElementException ex) {
        ResultStatus rs=new ResultStatus();
        rs.setStatus(FAILED);
        rs.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
    }

    @ExceptionHandler(InvalidOrderOperationException.class)
    public ResponseEntity<ResultStatus> handleConflict(InvalidOrderOperationException ex) {
        ResultStatus rs=new ResultStatus();
        rs.setStatus(FAILED);
        rs.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rs);
    }

    @ExceptionHandler(InvalidStatusException.class)
    public ResponseEntity<ResultStatus> handleBadRequest(InvalidStatusException ex) {
        ResultStatus rs=new ResultStatus();
        rs.setStatus(FAILED);
        rs.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rs);
    }
}
