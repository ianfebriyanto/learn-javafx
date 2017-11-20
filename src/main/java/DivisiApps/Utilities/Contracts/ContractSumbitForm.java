package DivisiApps.Utilities.Contracts;

public interface ContractSumbitForm<T> {
    void onSuccess();
    void onError(T e);
}
