package ro.siit.mytripsapp.controller.validator;

public class JsonResponse {

    private Boolean valid;
    private String message;

    public JsonResponse(Boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
