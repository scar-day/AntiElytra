package dev.scarday.AntiElytra;

import eu.okaeri.configs.OkaeriConfig;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Configuration extends OkaeriConfig {
    @Data
    @EqualsAndHashCode(callSuper = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Command extends OkaeriConfig {
        List<String> aliases = List.of("ae");
        String permission = "antielytra.reload";
    }
    private Command command = new Command();

    List<String> worlds = List.of("world_the_end");

    String bypassPermission = "antielytra.bypass";

    @Data
    @EqualsAndHashCode(callSuper = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Messages extends OkaeriConfig {
        String noElytra = "<red>✘ Здесь элитры не работают!";
        String disabledWorld = "<red>✘ Внимание! В этом мире элитры не работают!";

        String reloaded = "<green>Конфигурация успешно перезагружена!";
    }

    private Messages messages = new Messages();
}
