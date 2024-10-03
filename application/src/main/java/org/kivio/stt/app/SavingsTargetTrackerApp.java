package org.kivio.stt.app;

import org.apache.meecrowave.Meecrowave;

public class SavingsTargetTrackerApp {
    public static void main(String[] args) {
        Meecrowave.Builder builder = new Meecrowave.Builder();
        builder.setHttpPort(8080);
        builder.setJsonpPrettify(true);

        try(final Meecrowave meecrowave = new Meecrowave(builder)) {
            meecrowave.bake().await();
        }
    }
}
