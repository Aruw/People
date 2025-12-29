package com.aruw.people.dto;

public class DeletePersonPayload {

    private final String message;
    private final Long deletedId;
    private final boolean success;

    public DeletePersonPayload(boolean success, String message, Long deletedId) {
        this.success = success;
        this.message = message;
        this.deletedId = deletedId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Long getDeletedId() {
        return deletedId;
    }

    public static DeletePersonPayload success(Long id) {
        return new DeletePersonPayload(true, "Person deleted successfully.", id);
    }

    public static DeletePersonPayload failure(String message) {
        return new DeletePersonPayload(false, message, null);
    }

}