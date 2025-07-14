package dev.scarday.AntiElytra;

import eu.okaeri.configs.OkaeriConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Configuration extends OkaeriConfig {
    private List<String> worlds = List.of("world_the_end");

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Messages extends OkaeriConfig {
        private String noElytra = "<red>✘ Здесь элитры не работают!";
        private String disabledWorld = "<red>✘ Внимание! В этом мире элитры не работают!";

        // TODO: reload, etc...
    }

    private Messages messages = new Messages();
}
