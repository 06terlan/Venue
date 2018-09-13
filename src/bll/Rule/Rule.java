package bll.Rule;

public interface Rule<T> {

    void validate(T data) throws RuleException;

}
