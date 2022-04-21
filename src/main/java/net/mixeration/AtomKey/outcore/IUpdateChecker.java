package net.mixeration.AtomKey.outcore;

public interface IUpdateChecker {

    static void checkUpdates() {}
    void onFailCheck();
    void onSuccessCheck(String lastVersion);
}