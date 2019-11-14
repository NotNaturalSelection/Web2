package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ErrorBean implements Serializable {

    public ErrorBean() {
    }

    private Set<String> errors = new HashSet<>();

    public Set<String> getErrors() {
        return errors;
    }

    public void setErrors(Set<String> errors) {
        this.errors = errors;
    }

    public void addError(String text){
        errors.add(text);
    }

    public void clearErrors(){
        errors.clear();
    }
}
