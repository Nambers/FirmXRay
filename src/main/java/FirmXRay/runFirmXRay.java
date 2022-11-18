package FirmXRay;

import FirmXRay.main.Constant;
import FirmXRay.main.Logger;
import FirmXRay.main.Main;
import ghidra.util.InvalidNameException;
import ghidra.util.exception.CancelledException;
import ghidra.util.exception.DuplicateNameException;
import ghidra.util.exception.VersionException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

public class runFirmXRay {
    public static void main(String[] args) throws InvalidNameException, DuplicateNameException, CancelledException, IOException, VersionException {
        System.out.println("Working Directory = " + new File("").getAbsolutePath());
        var cache = new File("./cache/");
        // clear cache
        if (cache.exists()) {
            try (Stream<Path> walk = Files.walk(Path.of("./cache/"))) {
                walk.sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            }
        }
        cache.mkdir();
        new File("./logs/").mkdir();
        new File("./output/").mkdir();
        new File("./out/").mkdir();
        new File("./base/").mkdir();
        Logger.log = true;
        Constant.DIRECTORY_NAME = new File("./cache/").getAbsolutePath();
        Main.main(new String[]{"F:\\Temp_projects\\BLE\\nrf52832_xxaa.bin", "Nordic"});
    }
}
