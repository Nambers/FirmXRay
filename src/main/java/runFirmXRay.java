import ghidra.util.InvalidNameException;
import ghidra.util.exception.CancelledException;
import ghidra.util.exception.DuplicateNameException;
import ghidra.util.exception.VersionException;
import main.Constant;
import main.Main;

import java.io.File;
import java.io.IOException;

public class runFirmXRay {
    public static void main(String[] args) throws InvalidNameException, DuplicateNameException, CancelledException, IOException, VersionException {
        new File("./cache/").mkdir();
        new File("./logs/").mkdir();
        new File("./output/").mkdir();
        new File("./out/").mkdir();
        new File("./base/").mkdir();
        Constant.DIRECTORY_NAME = new File("./cache/").getAbsolutePath();
        Main.main(new String[]{"F:\\Temp_projects\\BLE\\nrf52832_xxaa.bin", "Nordic"});
    }
}
