package service;

import java.util.Objects;

public abstract class NamedSingletonService {

    public abstract String getNameService();

    public boolean equals(Object o) {
        if (o instanceof NamedSingletonService service) {
            return Objects.equals(this.getNameService(), service.getNameService());
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.getNameService());
    }
}
