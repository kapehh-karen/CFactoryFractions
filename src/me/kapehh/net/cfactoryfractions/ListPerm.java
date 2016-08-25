package me.kapehh.net.cfactoryfractions;

/**
 * Created by karen on 25.08.2016.
 */
public enum ListPerm {
    HEROES("cfactoryfractions.fraction.heroes"),
    OUTCAST("cfactoryfractions.fraction.outcast"),
    ADMIN("cfactoryfractions.admin");

    // -- enum class --

    private final String text;

    ListPerm(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
