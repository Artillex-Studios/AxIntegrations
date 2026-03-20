package com.artillexstudios.axintegrations.utils;

import com.artillexstudios.axintegrations.Integration;
import com.artillexstudios.axintegrations.IntegrationType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

public class PackageScanner {

    public static List<Class<? extends Integration>> scan(IntegrationType type) {
        List<Class<? extends Integration>> list = new ArrayList<>();
        try {
            File jarFile = new File(type.getClass().getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI());
            try (JarFile jar = new JarFile(jarFile)) {
                String path = "com.artillexstudios.axintegrations.integrations".replace('.', '/');

                jar.stream()
                        .filter(e -> e.getName().endsWith(".class") && e.getName().startsWith(path))
                        .forEach(e -> {
                            String className = e.getName().replace('/', '.').replace(".class", "");
                            try {
                                Class<?> clazz = Class.forName(className);
                                if (type.getClazz().isAssignableFrom(clazz) && !clazz.equals(type.getClazz())) {
                                    list.add((Class<? extends Integration>) clazz);
                                }
                            } catch (Throwable ex) {
                                ex.printStackTrace();
                            }
                        });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
