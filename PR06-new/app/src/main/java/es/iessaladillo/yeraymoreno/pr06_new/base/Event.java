package es.iessaladillo.yeraymoreno.pr06_new.base;

public class Event<T> {

    private final T content;
    private boolean hasBeenHandled = false;

    public Event(T content) {
        this.content = content;
    }

    public T getContentIfNotHandled() {
        if (hasBeenHandled) return null;
        hasBeenHandled = true;
        return content;
    }

}