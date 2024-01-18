package Game;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovePieceResponse {
    private boolean isSuccess;
    private String message;

    public MovePieceResponse(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public MovePieceResponse() {
    }
}
